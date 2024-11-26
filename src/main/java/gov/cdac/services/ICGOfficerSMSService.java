package gov.cdac.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gov.cdac.excel.UserMobileExcelExporter;
import gov.cdac.icgOfficerPojo.ApplicantCredential;
import gov.cdac.icgOfficerPojo.IcgOfficerSMSReportDetail;
import gov.cdac.icgOfficerPojo.IcgOfficerSMSScheduleDetail;
import gov.cdac.icgOfficerPojo.IcgOfficerSMSSent;
import gov.cdac.icgOfficerPojo.IcgOfficerSMSStatus;
import gov.cdac.icgOfficerPojo.SmsReasonMaster;
import gov.cdac.icgOfficerPojo.SmsTemplateMaster;
import gov.cdac.icgOfficerRepository.IcgOfficerApplicantCentreAllotmentRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerBatchMasterRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerCentreMasterRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerExamMasterRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerExamSlotRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerPersonalDetailRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSReasonMasterRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSReportDetailRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSScheduleRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSSentRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSStatusRepository;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSTemplateMasterRepository;
import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;
import gov.cdac.projection.ApplicantMobileSummary;
import gov.cdac.projection.CentreSlotCandidateCount;
import jakarta.servlet.http.HttpServletResponse;

@Service("icgOfficerSMSService")
public class ICGOfficerSMSService implements SMSService {
	private final Logger log = LogManager.getLogger(ICGOfficerSMSService.class);
	private static final java.util.logging.Logger ICGOFFSMSSENTLOGGER = java.util.logging.Logger.getLogger(ICGOfficerSMSService.class.getName());

	private IcgOfficerSMSTemplateMasterRepository SMSTemplateMasterRepository;
	private IcgOfficerSMSReasonMasterRepository SMSReasonMasterRepository;
	private IcgOfficerSMSSentRepository SMSSentRepository;
	private IcgOfficerBatchMasterRepository batchMasterRepository;
	private IcgOfficerExamMasterRepository examMasterRepository;
	private SMSSendService SMSSendService;
	private IcgOfficerPersonalDetailRepository personalDetailRepository;
	private static final int MAX_LIMIT = 30000;
	public static String STATUS = null;
	public static int COUNT = 0;
	
	private FileHandler fh;

	@Autowired
	private IcgOfficerCentreMasterRepository centreMasterRepository;
	@Autowired
	private IcgOfficerExamSlotRepository examSlotRepository;
	@Autowired
	private IcgOfficerApplicantCentreAllotmentRepository acaRepository;
	@Autowired
	@Qualifier("icgOfficerSmsMobileNoExcelsheetServiceImpl")
	private SmsMobileNoExcelsheetService excelService;
	@Autowired
	private IcgOfficerSMSReportDetailRepository SMSReportDetailRepository;
	@Autowired
	private IcgOfficerSMSStatusRepository smsStatusRepository;
	@Autowired
	private IcgOfficerSMSScheduleRepository icgSMSScheduleRepository;

	public ICGOfficerSMSService(IcgOfficerSMSTemplateMasterRepository sMSTemplateMasterRepository,
			IcgOfficerSMSReasonMasterRepository sMSReasonMasterRepository,
			IcgOfficerSMSSentRepository sMSSentRepository, IcgOfficerBatchMasterRepository batchMasterRepository,
			IcgOfficerExamMasterRepository examMasterRepository,
			SMSSendService sMSSendService,
			IcgOfficerPersonalDetailRepository personalDetailRepository) {
		this.SMSTemplateMasterRepository = sMSTemplateMasterRepository;
		this.SMSReasonMasterRepository = sMSReasonMasterRepository;
		this.SMSSentRepository = sMSSentRepository;
		this.batchMasterRepository = batchMasterRepository;
		this.examMasterRepository = examMasterRepository;
		this.SMSSendService = sMSSendService;
		this.personalDetailRepository = personalDetailRepository;
	}

	public Map<String, Object> getPageData() {
		Map<String, Object> pageDataMap = new HashMap<>();
		Integer examMasterId = examMasterRepository.getActiveExamId();
		pageDataMap.put("smsTemplates", SMSTemplateMasterRepository.findAllByOrderBySMSTemplateMasterIdDesc());
		pageDataMap.put("smsReasons", SMSReasonMasterRepository.findAllByOrderBySMSReason());
		pageDataMap.put("smsSent", SMSSentRepository.findSMSSentSummary());
		pageDataMap.put("centres", centreMasterRepository.findAllCentres(examMasterId));
		pageDataMap.put("slots", examSlotRepository.findByExamMasterExamIdOrderByExamSlotCode(examMasterId));
		pageDataMap.put("addTemplate", new SMSTemplateMaster());
		pageDataMap.put("smsModel", new SMSModel());
		pageDataMap.put("status", STATUS);
		pageDataMap.put("pageTitle", "ICG officer SMS Service");
		pageDataMap.put("pageType", "icgOfficer");
		return pageDataMap;
	}

	public void addSMSReasonMaster(SMSReasonMaster smsReasonMaster) {
		SMSReasonMasterRepository.save(new SmsReasonMaster(smsReasonMaster.getSMSReasonMasterId()));
	}

	public void addSMSTemplate(SMSTemplateMaster smsTemplateMaster) {
		SMSTemplateMasterRepository.save(new SmsTemplateMaster(smsTemplateMaster));
	}

	public List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds) {
		return acaRepository.findAppCredIdByCentreIdAndExamSlotId(centreId, slotIds);
	}

	@Async
	public void generateReport(List<String> mobileNos, List<Long> appCredIds, Long smsSentId, Long smsScheduleId,
			String msg, String smsType, int smsSentType, String reqType) {
		IcgOfficerSMSReportDetail report = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId,
				smsScheduleId);
		if (report == null) {
			UserMobileExcelExporter exp = new UserMobileExcelExporter(appCredIds, mobileNos, msg, smsType, smsSentType, reqType);
			try {
				Path path = exp.export1();

				IcgOfficerSMSReportDetail report2 = new IcgOfficerSMSReportDetail();
				report2.setFilePath(path.toString());
				report2.setIcgOfficerSMSSent(SMSSentRepository.findById(smsSentId).get());
				report2.setReportDetail(true);
				report2.setIcgOfficerSMSScheduleDetail(icgSMSScheduleRepository.findById(smsScheduleId).get());
				
				SMSReportDetailRepository.save(report2);
				excelService.saveData(path.toString(), smsType, smsSentType);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			report.setReportDetail(true);
			SMSReportDetailRepository.save(report);
		}
	}

	@Async
	public void sendQuickSMS(List<String> mobileList, List<Long> appCredIdList, int candidateCount, Long smsSentId,
			Long smsScheduleId) {
		COUNT = 0;

		IcgOfficerSMSSent icgSMSSent = SMSSentRepository.findById(smsSentId).get();
		IcgOfficerSMSScheduleDetail icgSMSScheduleDetail = icgSMSScheduleRepository.findById(smsScheduleId).get();
		IcgOfficerSMSStatus status = smsStatusRepository.findById(4L).get();
		icgSMSScheduleDetail.setSmsScheduleStatus(status);
		icgSMSScheduleRepository.save(icgSMSScheduleDetail);

		mobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(null, icgSMSSent, number, icgSMSSent.getTemplateMasterId());
			ICGOFFSMSSENTLOGGER.info("SMS Sent To : " + number);
		});

		Timestamp end = new Timestamp(System.currentTimeMillis());
		icgSMSScheduleDetail.setSmsScheduleEndDate(end);

		status = smsStatusRepository.findById(1L).get();
		icgSMSScheduleDetail.setSmsScheduleStatus(status);

		STATUS = null;
		SMSSentRepository.save(icgSMSSent);
		icgSMSScheduleRepository.save(icgSMSScheduleDetail);
		log.info("ICG SMS Sent for SMSSent Id: " + icgSMSSent.getSMSSentId());
		
		generateReport(mobileList, appCredIdList, smsSentId, smsScheduleId, icgSMSSent.getMessage(),
				icgSMSScheduleDetail.getSmsType(), icgSMSSent.getSmsSentType(), icgSMSSent.getReqType());
		
		System.out.println("Report generated! : SMS Sent Id ("+smsSentId+")");
	}

	public void sendBulkSMS(List<String> mobileNoList, List<Long> appCredIds, int candidateCount, Long smsSentId,
			Long smsScheduleId) {
		int splitCount = candidateCount / MAX_LIMIT;
		int modCount = candidateCount % MAX_LIMIT;
		int j = 0;
		COUNT = 0;

		List<ApplicantMobileSummary> applicantMobileList = new ArrayList<>();

		for (int i = 0; i < splitCount; i++, j += MAX_LIMIT) {
			STATUS = "Fetching mobile details from: " + j + " - " + (j + MAX_LIMIT) + " of " + candidateCount;
			log.info(STATUS);
			applicantMobileList
					.addAll(personalDetailRepository.findMobileByAppCredId(appCredIds.subList(j, j + MAX_LIMIT)));
		}

		if (modCount != 0) {
			STATUS = "Fetching details from: " + j + " - " + (j + modCount) + " of " + candidateCount;
			log.info(STATUS);
			applicantMobileList
					.addAll(personalDetailRepository.findMobileByAppCredId(appCredIds.subList(j, j + modCount)));
		}

		IcgOfficerSMSSent SMSSent = SMSSentRepository.findById(smsSentId).get();
		IcgOfficerSMSScheduleDetail SMSScheduleDetail = icgSMSScheduleRepository.findById(smsScheduleId).get();

		IcgOfficerSMSStatus status = smsStatusRepository.findById(4L).get();
		SMSScheduleDetail.setSmsScheduleStatus(status);
		icgSMSScheduleRepository.save(SMSScheduleDetail);

		log.info("Sending message: " + SMSSent.getMessage());

		applicantMobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(
					new ApplicantCredential(number.getAppCredId()), SMSSent,
					number.getMobile(), SMSSent.getTemplateMasterId());
		});

		generateReport(null, appCredIds, smsSentId, smsScheduleId, SMSSent.getMessage(), SMSScheduleDetail.getSmsType(),
				SMSSent.getSmsSentType(), SMSSent.getReqType());

		Timestamp end = new Timestamp(System.currentTimeMillis());

		SMSScheduleDetail.setSmsScheduleEndDate(end);
		status = smsStatusRepository.findById(1L).get();
		SMSScheduleDetail.setSmsScheduleStatus(status);

		SMSSentRepository.save(SMSSent);
		icgSMSScheduleRepository.save(SMSScheduleDetail);
		log.info("SMS Sent for SMSSent Id: " + SMSSent.getSMSSentId());
		STATUS = null;
	}

	public Long addSMSSentEntry(SMSModel smsModel, int candidateCount, List<Long> appCredIds, List<String> mobileNos,
			String reqType, String smsType) {

		try {  
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
			 LocalDateTime now = LocalDateTime.now();  
	        fh = new FileHandler("D:/EmailSMS/sms_sent_logs/icg_officer/"+smsModel.getSmsReasonMasterId()+"_"+dtf.format(now)+".log");
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        
	        ICGOFFSMSSENTLOGGER.addHandler(fh);
	        
	        // the following statement is used to log any messages  
	        ICGOFFSMSSENTLOGGER.info(" *** ICG OFFICER ***");  
	        ICGOFFSMSSENTLOGGER.info("Date & Time : "+dtf.format(now)); 
	        ICGOFFSMSSENTLOGGER.info("SMS Content : "+smsModel.getSmsContent()); 
	        
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 
		
		IcgOfficerSMSSent smsSent = SMSSentRepository.save(
				new IcgOfficerSMSSent(batchMasterRepository.findActiveBatch(), examMasterRepository.getActiveExamId(),
						candidateCount, smsModel.getSmsContent(), smsModel.getSmsReasonMasterId(), false,
						smsModel.getSmsSentType(), reqType, smsModel.getTemplateMasterId()));

		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			IcgOfficerSMSScheduleDetail scheduleDetail = new IcgOfficerSMSScheduleDetail(
					new Timestamp(System.currentTimeMillis()), candidateCount, smsType);

			IcgOfficerSMSStatus status = smsStatusRepository.findById(3L).get();
			scheduleDetail.setSmsScheduleStatus(status);
			icgSMSScheduleRepository.save(scheduleDetail);
			smsSent.getHelperScheduler(scheduleDetail);
		}
		if (smsModel.getSmsSentType() == 2 || smsModel.getSmsSentType() == 3) {
			for (int i = 0; i < smsModel.getSmsScheduleDatetime().size(); i++) {
				Timestamp later = smsModel.getSmsScheduleDatetime().get(i);

				IcgOfficerSMSScheduleDetail scheduleDetail = new IcgOfficerSMSScheduleDetail(later, candidateCount,
						smsType);
				IcgOfficerSMSStatus status = smsStatusRepository.findById(3L).get();
				scheduleDetail.setSmsScheduleStatus(status);
				icgSMSScheduleRepository.save(scheduleDetail);
				smsSent.getHelperScheduler(scheduleDetail);
			}
		}
		SMSSentRepository.save(smsSent);
		List<String> mobileNos2 = new ArrayList<String>();

		if (appCredIds != null) {
			
			int splitCount = candidateCount / MAX_LIMIT;
			int modCount = candidateCount % MAX_LIMIT;
			int j = 0;
			COUNT = 0;

			for (int i = 0; i < splitCount; i++, j += MAX_LIMIT) {
				STATUS = "Fetching mobile details from: " + j + " - " + (j + MAX_LIMIT) + " of " + candidateCount;
				log.info(STATUS);
				mobileNos2.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + MAX_LIMIT)));
			}
			if (modCount != 0) {
				STATUS = "Fetching details from: " + j + " - " + (j + modCount) + " of " + candidateCount;
				log.info(STATUS);
				System.out.println("\n\nappCredIds : "+appCredIds.subList(j, j + modCount));
				mobileNos2.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + modCount)));
			}

		} else {
			mobileNos2.addAll(mobileNos);
		}
		ICGOFFSMSSENTLOGGER.info("Candidate Count : "+mobileNos2.size()+"\n\n"); 

		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			sendQuickSMS(mobileNos2, appCredIds, candidateCount, smsSent.getSMSSentId(), icgSMSScheduleRepository.getFirstScheduler(smsSent.getSMSSentId()));
		}else {
			generateReport(mobileNos2, appCredIds, smsSent.getSMSSentId(), icgSMSScheduleRepository.getFirstScheduler(smsSent.getSMSSentId()), smsSent.getMessage(), smsType, smsSent.getSmsSentType(), reqType);
		}
		
		return icgSMSScheduleRepository.getFirstScheduler(smsSent.getSMSSentId());
	}

	public String centreSlotCountDetails(Integer centreId, List<Integer> slotIds) {
		List<CentreSlotCandidateCount> centreSlotCandidateCount = acaRepository
				.findCandidateCountByCentreIdAndExamSlotId(centreId, slotIds);
		StringBuilder stringBuilder = new StringBuilder();
		int totalCount = 0;
		for (CentreSlotCandidateCount count : centreSlotCandidateCount) {
			stringBuilder.append(count.getSlot() + " : " + count.getCount() + "<br/>");
			totalCount += count.getCount();
		}
		stringBuilder.append("Total : " + totalCount);
		return stringBuilder.toString();
	}

	@Async
	public void sendSMSAsync(List<String> mobileNos, List<Long> appCredIds, Long smsScheduleDetailId) {
		try {
			IcgOfficerSMSScheduleDetail assd = icgSMSScheduleRepository.findById(smsScheduleDetailId).get();
			IcgOfficerSMSSent ass = SMSSentRepository.findById(assd.getSmsSent().getSMSSentId()).get();
			
			List<String> nos1 = new ArrayList<>();
			List<Long> nos2 = new ArrayList<>();

			if (mobileNos != null) {
				nos1.addAll(mobileNos);
			} else {
				IcgOfficerSMSReportDetail reportGenerated = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(
						ass.getSMSSentId(), (icgSMSScheduleRepository.getFirstScheduler(ass.getSMSSentId())));
				nos1.addAll(excelService.getExcel(Paths.get(reportGenerated.getFilePath()), true));

				if (appCredIds == null) {
					List<String> list = excelService.getExcel(Paths.get(reportGenerated.getFilePath()), false);
					for (int i = 0; i < list.size(); i++) {
						nos2.add(Long.parseLong(list.get(i)));
					}
				} else {
					nos2.addAll(appCredIds);
				}
			}
			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					sendQuickSMS(nos1, nos2, ass.getCandidateCount(), ass.getSMSSentId(), smsScheduleDetailId);
				}
			});
			thread.start();
		} catch (Exception e) {
			System.out.println();
		}
	}

	@Override
	public void extractExcelReport(HttpServletResponse response, Long smsScheduleId) throws IOException {
		Long smsSentId = (icgSMSScheduleRepository.findById(smsScheduleId).get()).getSmsSent().getSMSSentId();
		IcgOfficerSMSReportDetail report = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId, smsScheduleId);
		UserMobileExcelExporter exp = new UserMobileExcelExporter(report.getFilePath());
		exp.extractFile(response);	
	}

}
