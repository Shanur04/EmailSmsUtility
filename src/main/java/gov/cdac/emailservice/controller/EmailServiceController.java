package gov.cdac.emailservice.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import gov.cdac.emailservice.models.CentreModel;
import gov.cdac.emailservice.models.EmailModel;
import gov.cdac.emailservice.models.ReportInfo;
import gov.cdac.emailservice.models.TestEmailBulkModel;
import gov.cdac.emailservice.services.FileUploadService;
import gov.cdac.emailservice.services.MailServiceFactory;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class EmailServiceController {
	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");

	@Value("${filePath.icgEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileICG;
	
	@Value("${filePath.icgOfficerEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileICGOfficer;
	
	@Value("${filePath.afcatEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileAfcat;
	
	@Value("${filePath.casbEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileCASB;
	
	private MailServiceFactory mailServiceFactory;	
	private FileUploadService fileUploadService;

	/**
	 * Constructor based autowiring
	 * 
	 * @param mailServiceFactory
	 */
	public EmailServiceController(MailServiceFactory mailServiceFactory, FileUploadService fileUploadService) {
		this.mailServiceFactory = mailServiceFactory;
		this.fileUploadService = fileUploadService;
	}

	/**
	 * Display page controller.
	 * 
	 * @param reqType Allowed values: icg, casb, afcat
	 * @return
	 */
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}")
	public ModelAndView emailHomePage(@PathVariable String reqType) {
		return new ModelAndView("MailHome").addAllObjects(mailServiceFactory.getPageData(reqType));
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/bulkEmailWithAdmitCardsAndOtherAttachments")
	public ModelAndView bulkEmailWithAdmitCardsAndOtherAttachments(@PathVariable String reqType, Model model, Authentication auth,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("emailId", auth.getName());
		return new ModelAndView("bulkEmailWithAdmitCardsAndOtherAttachments").addAllObjects(mailServiceFactory.getPageData(reqType)); 
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/bulkEmailWithExcelSheet")
	public ModelAndView bulkEmailWithExcelSheet(@PathVariable String reqType, Model model, Authentication auth, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("emailId", auth.getName());
		return new ModelAndView("BulkEmailFromExcel").addAllObjects(mailServiceFactory.getPageData(reqType)); 
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/singleTestEmail")
	public ModelAndView singleTestEmail(@PathVariable String reqType, Model model, Authentication auth, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("emailId", auth.getName());
		return new ModelAndView("singleTestEmail").addAllObjects(mailServiceFactory.getPageData(reqType)); 
	}

	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/bulkEmailWithExcelSheetForRejected")
	public ModelAndView bulkEmailWithExcelSheetForRejected(@PathVariable String reqType, Model model, Authentication auth,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("emailId", auth.getName());
		return new ModelAndView("BulkEmailFromExcelForRejected").addAllObjects(mailServiceFactory.getPageData(reqType)); 
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/viewConfigureBulkEmail")
	public ModelAndView ConfigureBulkEmailPage(@PathVariable String reqType, Model model, Authentication auth, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("emailId", auth.getName());
		if (session.getAttribute("disableEnableButtons") == null) {
			session.setAttribute("disableEnableButtons", "enable");
		} else {
			session.setAttribute("disableEnableButtons", session.getAttribute("disableEnableButtons"));
		}
		return new ModelAndView("MailHome").addAllObjects(mailServiceFactory.getPageData(reqType));
	}
	
	@RequestMapping(value = "{reqType:icg|casb|afcat|icgOfficer}/upload", method = RequestMethod.POST)
	public @ResponseBody ArrayList<String> handleFileUpload(@PathVariable String reqType, MultipartHttpServletRequest request) throws IOException {
		Iterator<String> iterator = request.getFileNames();
		fileUploadService.createDirectory(reqType);
		ArrayList<String> arr = new ArrayList<String>();
		while (iterator.hasNext()) {
			String fileName = iterator.next();
			MultipartFile file = request.getFile(fileName);
			///ICGUpload/Cycle3/ICGEmailAttachments/
			Path filepath= null;
			if(reqType.equalsIgnoreCase("icg"))
				filepath = Paths.get(emailAttachmentDirFromPropertyFileICG, file.getOriginalFilename());
			else if(reqType.equalsIgnoreCase("icgOfficer"))
				filepath = Paths.get(emailAttachmentDirFromPropertyFileICGOfficer, file.getOriginalFilename());
			else if(reqType.equalsIgnoreCase("afcat"))
				filepath = Paths.get(emailAttachmentDirFromPropertyFileAfcat, file.getOriginalFilename());
			else
				filepath = Paths.get(emailAttachmentDirFromPropertyFileCASB, file.getOriginalFilename());
			arr.add(file.getOriginalFilename());
			try (OutputStream os = Files.newOutputStream(filepath)) {
				os.write(file.getBytes());
			} catch (Exception e) {
				centerWiseSendEmail.error("some error occurred while saving temporary file in ICGUPLOAD folder");
			}
			centerWiseSendEmail.info("file is saved in Upload Folder");
		}
		System.out.println("file is saved in Upload Folder : \n"+arr.get(0));
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="{reqType:icg|casb|afcat|icgOfficer}/load", method = RequestMethod.POST)
	public @ResponseBody ArrayList<JSONObject> load(@PathVariable String reqType) throws IOException {
		ArrayList<File> filesUploaded = new ArrayList<File>();
		ArrayList<JSONObject> jsonobjectarraylist = new ArrayList<JSONObject>();
		filesUploaded = fileUploadService.findFiles(reqType);
		for (File file : filesUploaded) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", file.getName());
			Path paths = file.toPath();
			jsonObject.put("file", file);
			jsonObject.put("size", file.length());
			jsonobjectarraylist.add(jsonObject);
		}
		return jsonobjectarraylist;
	}
	
	@RequestMapping(value = "{reqType:icg|casb|afcat|icgOfficer}/sendCenterwiseTestEmails", method = RequestMethod.POST)
	public ResponseEntity<?> sendBulkEmail(@PathVariable String reqType, @Valid @RequestBody EmailModel emailModel, BindingResult result,
			HttpServletRequest request, Model model) throws JsonProcessingException, IOException {
		
		if(emailModel.getSentType() == 2 || emailModel.getSentType() == 3) {
			String[] strArr = emailModel.getScheduleList().split(",");
			List<Timestamp> listOfTimestamp = new ArrayList<Timestamp>();
			for(String s : strArr) {
				LocalDateTime dateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				listOfTimestamp.add(Timestamp.valueOf(dateTime));
			}
			emailModel.setEmailScheduleDatetime(listOfTimestamp);
		}
		emailModel.setPageType(reqType);
		
		String res = mailServiceFactory.addEmailDataEntry(emailModel, "TestEmail", reqType, request);
		if(res.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("{reqType:icg|casb|afcat|icgOfficer}/sendTestEmails")
	public String sendTestEmails(@PathVariable String reqType, @Valid @ModelAttribute EmailModel emailModel, HttpServletRequest request) {
		if(emailModel.getSentType() == 2 || emailModel.getSentType() == 3) {
			String[] strArr = emailModel.getScheduleList().split(",");
			List<Timestamp> listOfTimestamp = new ArrayList<Timestamp>();
			for(String s : strArr) {
				LocalDateTime dateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				listOfTimestamp.add(Timestamp.valueOf(dateTime));
			}
			emailModel.setEmailScheduleDatetime(listOfTimestamp);
		}	
		emailModel.setPageType(reqType);
		return mailServiceFactory.addEmailDataEntry(emailModel, "TestEmail", reqType, request);
	}
	
	@RequestMapping(value = "{reqType:icg|casb|afcat|icgOfficer}/sendBulkTestEmails", method = RequestMethod.POST)
	public ResponseEntity<?> cIMultiple(@PathVariable String reqType, @Valid @RequestBody EmailModel emailModel, BindingResult result,
			HttpServletRequest request, Model model) throws JsonProcessingException, IOException {
		
		if(emailModel.getSentType() == 2 || emailModel.getSentType() == 3) {
			String[] strArr = emailModel.getScheduleList().split(",");
			List<Timestamp> listOfTimestamp = new ArrayList<Timestamp>();
			for(String s : strArr) {
				LocalDateTime dateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
				listOfTimestamp.add(Timestamp.valueOf(dateTime));
			}
			emailModel.setEmailScheduleDatetime(listOfTimestamp);
		}
		emailModel.setPageType(reqType);
		
		String res = mailServiceFactory.addEmailDataEntry(emailModel, "TestEmail", reqType, request);
		if(res.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "{reqType:icg|casb|afcat|icgOfficer}/sendBulkEmailWithExcelForRejected", method = RequestMethod.POST)
	public ResponseEntity<?> sendMultipleEmailOfRejected(@PathVariable String reqType, @Valid @RequestBody TestEmailBulkModel testEmailBulkModel,
			BindingResult result, HttpServletRequest request, Model model) throws JsonProcessingException, IOException {
		
		return mailServiceFactory.sendMultipleEmailOfRejected(testEmailBulkModel, reqType, request);
	}

	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/downloadExcel/{scheduleId}")
	public ResponseEntity<String> downloadExcel(@PathVariable String reqType, @PathVariable String scheduleId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws NumberFormatException, IOException {
		
		mailServiceFactory.extractReport(Long.parseLong(scheduleId), reqType, httpRequest, httpResponse);
		return new ResponseEntity<String>(HttpStatus.OK);	
	}

	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/searchByEmailId/{emailId}")
	public ResponseEntity<List<ReportInfo>> searchByEmailId(@PathVariable String reqType, @PathVariable String emailId) throws NumberFormatException, IOException {
		List<ReportInfo> reportInfo = mailServiceFactory.searchByEmailId(emailId, reqType);
		if(reportInfo == null) {
			reportInfo = new ArrayList<ReportInfo>();
			return new ResponseEntity<List<ReportInfo>>(reportInfo, HttpStatus.OK);	
		}
		return new ResponseEntity<List<ReportInfo>>(reportInfo, HttpStatus.OK);	
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/getUploadData/")
	public ResponseEntity<List<ReportInfo>> getUploadedData(@PathVariable String reqType, @RequestParam("path") String path) throws NumberFormatException, IOException {
		
		List<ReportInfo> reportInfo = mailServiceFactory.getUploadDocPaths(path, reqType);
		if(reportInfo == null) {
			reportInfo = new ArrayList<ReportInfo>();
			return new ResponseEntity<List<ReportInfo>>(reportInfo, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<List<ReportInfo>>(reportInfo, HttpStatus.OK);		
	}
	
/*	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/downloadTextFile/{emailId}/{filePath}")
	public ResponseEntity<?> downloadTextFile(@PathVariable String reqType, @PathVariable String emailId, @PathVariable String filePath, HttpServletRequest  httpRequest, HttpServletResponse httpResponse) 
			throws NumberFormatException, IOException {
		
		System.out.println("download path : "+filePath);

		
		filePath = mailServiceFactory.getPath(emailId, filePath, reqType);

		System.out.println("\n\nfilePath : "+filePath);
		Path path = Paths.get(filePath);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Applicant Credential Id Not Found", HttpStatus.NOT_FOUND);
		}
		if(!filePath.endsWith(".txt")) {
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
					.header("CONTENT_DISPOSITION", "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		}else {
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/plain"))
					.header("CONTENT_DISPOSITION", "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		}
	}
*/	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/downloadTextFile")
	public ResponseEntity<String> downloadTextFile2(@PathVariable String reqType, @RequestParam("path") String path, HttpServletRequest  httpRequest, HttpServletResponse httpResponse) throws NumberFormatException, IOException {
		
		mailServiceFactory.extractTextReport(path, reqType, httpRequest, httpResponse);
		return new ResponseEntity<String>("Success", HttpStatus.OK);	
	}
	
	@GetMapping("{reqType:icg|casb|afcat|icgOfficer}/populateCenters/")
	public ResponseEntity<List<CentreModel>> populateListOfCentres(@PathVariable String reqType) throws NumberFormatException, IOException {
		
		List<CentreModel> centreList = mailServiceFactory.populateListOfCentres(reqType);
		
		return new ResponseEntity<List<CentreModel>>(centreList, HttpStatus.OK);
	}
	
	
}