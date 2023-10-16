package gov.cdac.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSTemplateMaster;
import gov.cdac.services.SMSService;
import gov.cdac.services.SMSServiceFactory;

/**
 * 
 * @author shanurj
 *
 */
@Controller
@RequestMapping("/")
public class SMSController {
	private final Logger log = LogManager.getLogger(SMSController.class);

	private SMSServiceFactory smsServiceFactory;

	/**
	 * Constructor based autowiring
	 * 
	 * @param smsServiceFactory
	 */
	public SMSController(SMSServiceFactory smsServiceFactory) {
		this.smsServiceFactory = smsServiceFactory;
	}

	/**
	 * Display page controller.
	 * 
	 * @param reqType Allowed values: icg, casb, afcat
	 * @return
	 */
	@GetMapping("smsSent/{reqType:icg|casb|afcat|icgOfficer}")
	public ModelAndView smsHomePage(@PathVariable String reqType) {
		System.out.println(smsServiceFactory.getPageData(reqType));
		return new ModelAndView("SMSHome").addAllObjects(smsServiceFactory.getPageData(reqType));
	}
	
	@GetMapping("smsSent")
	public ModelAndView smsHomePage() {
		return new ModelAndView("SMSHome").addAllObjects(smsServiceFactory.getPageData("icg"));
	}

	/**
	 * Post mapping for sending sms
	 * 
	 * @param reqType
	 * @param smsModel
	 * @param bindingResult
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("smsSent/{reqType:icg|casb|afcat|icgOfficer}/sendSMS/{smsSendType}")
	public ModelAndView icgSmsSendCall(@PathVariable String reqType, @PathVariable int smsSendType, @Valid @ModelAttribute SMSModel smsModel,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("redirect:../");
		if (bindingResult.hasErrors()) {
			return mav;
		}
		try {
			log.info("Calling send sms service!");
			if(smsSendType == 2 || smsSendType == 3) {
				String[] strArr = smsModel.getScheduleList().split(",");
				List<Timestamp> listOfTimestamp = new ArrayList<Timestamp>();
				for(String s : strArr) {
					LocalDateTime dateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
					listOfTimestamp.add(Timestamp.valueOf(dateTime));
				}
				smsModel.setSmsScheduleDatetime(listOfTimestamp);
			}
			
			int candidateCount = 0;
			List<Long> appCredIds = null;
			List<String> mobileNumbers = null;

			String smsType = "";
			
			if (!smsModel.getTestMobileNumbers().isEmpty()) {
				log.info("Quick sms called");
				mobileNumbers = Arrays.asList(smsModel.getTestMobileNumbers().split(","));
				candidateCount = mobileNumbers.size();
				smsType = "Quick";
			}
			// For bulk sms via csv
			else if (!smsModel.getAppCredfile().getOriginalFilename().isEmpty()
					&& FilenameUtils.getExtension(smsModel.getAppCredfile().getOriginalFilename()).equals("csv")) {
				log.info("Bulk sms through csv called");
				if(smsModel.getIsAppCredFile()) {
					appCredIds = SMSService.readCSV1(smsModel.getAppCredfile());
					candidateCount = appCredIds.size();
				}else {
					mobileNumbers = SMSService.readCSV2(smsModel.getAppCredfile());
					candidateCount = mobileNumbers.size();
				}
				smsType = "CSV";
			} else if (smsModel.getCentreId() != null && !smsModel.getSlotIds().isEmpty()) {
				log.info("Bulk sms through centre and slot called");
				appCredIds = smsServiceFactory.getAppCredFromCentreAndSlot(smsModel.getCentreId(),
						smsModel.getSlotIds(), reqType);
				candidateCount = appCredIds.size();
				log.info(candidateCount);
				smsType = "CentreWise";
			}
			smsModel.setSmsSentType(smsSendType); //Quick | Quick-Schedule | Schedule
			Long smsScheduleId = smsServiceFactory.addSMSSentEntry(smsModel, candidateCount, smsType, appCredIds, mobileNumbers, reqType);
			
//			if(smsSendType == 1 || smsSendType == 2) {
//					smsServiceFactory.sendQuickBulkSMS(mobileNumbers, appCredIds, candidateCount, reqType, smsScheduleId);
//			}
			if(smsType.equalsIgnoreCase("Quick")) {
				redirectAttributes.addFlashAttribute("quickSMS", "SMS sent to candidates: " + candidateCount);
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return mav;
	}


	/**
	 * Post mapping for adding template
	 * 
	 * @param reqType     Allowed values: icg, afcat, casb
	 * @param addTemplate
	 * @return
	 */
	@PostMapping("smsSent/{reqType:icg|casb|afcat|icgOfficer}/addTemplate")
	public ModelAndView addTemplate(@PathVariable String reqType, @ModelAttribute SMSTemplateMaster addTemplate) {
		System.out.println("reqType "+reqType+" : addTemplate "+addTemplate.getTemplateMasterId());
		smsServiceFactory.addSMSTemplate(addTemplate, reqType);
		return new ModelAndView("redirect:../");
	}

	/**
	 * Returns candidate count according to centre and slot selected
	 * 
	 * @param reqType  Allowed values: icg, afcat, casb
	 * @param centreId
	 * @param slotIds
	 * @return
	 */
	@PostMapping("smsSent/{reqType:icg|casb|afcat|icgOfficer}/checkCount/{centreId}")
	public @ResponseBody String icgCheckCount(@PathVariable String reqType, @PathVariable Integer centreId,
			@RequestParam List<Integer> slotIds) {
		return smsServiceFactory.centreSlotCountDetails(centreId, slotIds, reqType);
	}
	
	@GetMapping("smsSent/{reqType:icg|casb|afcat|icgOfficer}/downloadExcel/{smsScheduleId}")
	public void downloadExcel(@PathVariable String reqType, @PathVariable String smsScheduleId, HttpServletResponse response) throws NumberFormatException, IOException {
		smsServiceFactory.extractReport(response, Long.parseLong(smsScheduleId), reqType);
		//return new ResponseEntity<String>(HttpStatus.OK);	
	}

}
