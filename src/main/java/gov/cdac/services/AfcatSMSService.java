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

import gov.cdac.afcatPojo.AfcatApplicantCredential;
import gov.cdac.afcatPojo.AfcatSMSReasonMaster;
import gov.cdac.afcatPojo.AfcatSMSReportDetail;
import gov.cdac.afcatPojo.AfcatSMSScheduleDetail;
import gov.cdac.afcatPojo.AfcatSMSSent;
import gov.cdac.afcatPojo.AfcatSMSStatus;
import gov.cdac.afcatPojo.AfcatSMSTemplateMaster;
import gov.cdac.afcatRepository.AfcatSMSReportDetailRepository;
import gov.cdac.afcatRepository.ApplicantCentreAllotmentRepository;
import gov.cdac.afcatRepository.BatchMasterRepository;
import gov.cdac.afcatRepository.CentreMasterRepository;
import gov.cdac.afcatRepository.ExamMasterRepository;
import gov.cdac.afcatRepository.ExamSlotRepository;
import gov.cdac.afcatRepository.PersonalDetailRepository;
import gov.cdac.afcatRepository.SMSReasonMasterRepository;
import gov.cdac.afcatRepository.SMSScheduleRepository;
import gov.cdac.afcatRepository.SMSSentRepository;
import gov.cdac.afcatRepository.SMSStatusRepository;
import gov.cdac.afcatRepository.SMSTemplateMasterRepository;
import gov.cdac.excel.UserMobileExcelExporter;
import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;
import gov.cdac.projection.ApplicantMobileSummary;
import gov.cdac.projection.CentreSlotCandidateCount;
import jakarta.servlet.http.HttpServletResponse;

@Service("afcatSMSService")
public class AfcatSMSService implements SMSService {
	private final Logger log = LogManager.getLogger(AfcatSMSService.class);
	private static final java.util.logging.Logger AFCATSMSSENTLOGGER = java.util.logging.Logger.getLogger(AfcatSMSService.class.getName());

	private SMSTemplateMasterRepository SMSTemplateMasterRepository;
	private SMSReasonMasterRepository SMSReasonMasterRepository;
	private SMSSentRepository SMSSentRepository;
	private BatchMasterRepository batchMasterRepository;
	private ExamMasterRepository examMasterRepository;
	private SMSSendService SMSSendService;
	private PersonalDetailRepository personalDetailRepository;
	private SMSScheduleRepository SMSScheduleRepository;

	@Autowired
	private CentreMasterRepository centreMasterRepository;
	@Autowired
	private ExamSlotRepository examSlotRepository;
	@Autowired
	private ApplicantCentreAllotmentRepository acaRepository;
	@Autowired
	@Qualifier("afcatSmsMobileNoExcelsheetServiceImpl")
	private SmsMobileNoExcelsheetService excelService;
	@Autowired
	private AfcatSMSReportDetailRepository afcatSMSReportDetailRepository;
	@Autowired
	private SMSStatusRepository smsStatusRepository;

	private static final int MAX_LIMIT = 30000;
	public static String STATUS = null;

	public static int COUNT = 0;

	private FileHandler fh;
	 
	public AfcatSMSService(SMSTemplateMasterRepository SMSTemplateMasterRepository,
			SMSReasonMasterRepository SMSReasonMasterRepository, SMSSentRepository SMSSentRepository,
			BatchMasterRepository batchMasterRepository, ExamMasterRepository examMasterRepository,
			SMSSendService SMSSendService, PersonalDetailRepository personalDetailRepository,
			SMSScheduleRepository SMSScheduleRepository) {
		this.SMSTemplateMasterRepository = SMSTemplateMasterRepository;
		this.SMSReasonMasterRepository = SMSReasonMasterRepository;
		this.SMSSentRepository = SMSSentRepository;
		this.batchMasterRepository = batchMasterRepository;
		this.examMasterRepository = examMasterRepository;
		this.SMSSendService = SMSSendService;
		this.personalDetailRepository = personalDetailRepository;
		this.SMSScheduleRepository = SMSScheduleRepository;
	}

	public Map<String, Object> getPageData() {
		Map<String, Object> pageDataMap = new HashMap<>();
		Integer examMasterId = examMasterRepository.getActiveExamId();
		pageDataMap.put("smsTemplates", SMSTemplateMasterRepository.findAllByOrderBySMSTemplateMasterIdDesc());
		pageDataMap.put("smsReasons", SMSReasonMasterRepository.findAllByOrderBySMSReason());
		pageDataMap.put("smsSent", SMSSentRepository.findSMSSentSummary());
		pageDataMap.put("centres", centreMasterRepository.findAllCentres(examMasterId));
		pageDataMap.put("slots", examSlotRepository.findByOrderByCode());
		pageDataMap.put("addTemplate", new AfcatSMSTemplateMaster());
		pageDataMap.put("smsModel", new SMSModel());
		pageDataMap.put("status", STATUS);
		pageDataMap.put("pageTitle", "AFCAT SMS Service");
		pageDataMap.put("pageType", "afcat");
		return pageDataMap;
	}

	public void addSMSReasonMaster(SMSReasonMaster smsReasonMaster) {
		SMSReasonMasterRepository.save(new AfcatSMSReasonMaster(smsReasonMaster.getSMSReasonMasterId()));
	}

	public void addSMSTemplate(SMSTemplateMaster smsTemplateMaster) {
		SMSTemplateMasterRepository.save(new AfcatSMSTemplateMaster(smsTemplateMaster));
	}

	public List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds) {
		return acaRepository.findAppCredIdByCentreIdAndExamSlotId(centreId, slotIds);
	}

	@Async
	public void generateReport(List<String> mobileNos, List<Long> appCredIds, Long smsSentId, Long smsScheduleId,
			String msg, String smsType, int smsSentType, String reqType) {
		
		AfcatSMSReportDetail report = afcatSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId,
				smsScheduleId);			
		if (report == null) {
			UserMobileExcelExporter exp = new UserMobileExcelExporter(appCredIds, mobileNos, msg, smsType, smsSentType, reqType);
			try {
				Path path = exp.export1();

				AfcatSMSReportDetail asrd = new AfcatSMSReportDetail();
				asrd.setFilePath(path.toString());
				AfcatSMSReportDetail report2 = new AfcatSMSReportDetail();
				report2.setFilePath(path.toString());
				report2.setAfcatSMSSent(SMSSentRepository.findById(smsSentId).get());
				report2.setReportDetail(true);

				report2.setAfcatSMSScheduleDetail(SMSScheduleRepository.findById(smsScheduleId).get());
				afcatSMSReportDetailRepository.save(report2);
				excelService.saveData(path.toString(), smsType, smsSentType);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			report.setReportDetail(true);
			afcatSMSReportDetailRepository.save(report);
		}
	}

	@Async
	public void sendQuickSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, Long smsSentId,
			Long smsScheduleId) {
		COUNT = 0;

		AfcatSMSSent afcatSMSSent = SMSSentRepository.findById(smsSentId).get();
		AfcatSMSScheduleDetail afcatSMSScheduleDetail = SMSScheduleRepository.findById(smsScheduleId).get();
		AfcatSMSStatus status = smsStatusRepository.findById(4L).get();
		afcatSMSScheduleDetail.setAfcatSmsScheduleStatus(status);
		SMSScheduleRepository.save(afcatSMSScheduleDetail);
		
		mobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(null, afcatSMSSent, number, afcatSMSSent.getTemplateMasterId());
			AFCATSMSSENTLOGGER.info("SMS Sent To : " + number);
		});

		Timestamp end = new Timestamp(System.currentTimeMillis());
		afcatSMSScheduleDetail.setSmsScheduleEndDate(end);
		status = smsStatusRepository.findById(1L).get();
		afcatSMSScheduleDetail.setAfcatSmsScheduleStatus(status);

		STATUS = null;
		SMSSentRepository.save(afcatSMSSent);
		SMSScheduleRepository.save(afcatSMSScheduleDetail);
		log.info("SMS Sent for SMSSent Id: " + afcatSMSSent.getSMSSentId());
		
		generateReport(mobileList, appCredIds, smsSentId, smsScheduleId, afcatSMSSent.getMessage(),
				afcatSMSScheduleDetail.getSmsType(), afcatSMSSent.getSmsSentType(), afcatSMSSent.getReqType());
		
		System.out.println("Report generated! : SMS Sent Id ("+smsSentId+")");
	}
	
	public void sendBulkSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, Long smsSentId,
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

		AfcatSMSSent afcatSMSSent = SMSSentRepository.findById(smsSentId).get();
		AfcatSMSScheduleDetail afcatSMSScheduleDetail = SMSScheduleRepository.findById(smsScheduleId).get();
		AfcatSMSStatus status = smsStatusRepository.findById(4L).get();
		afcatSMSScheduleDetail.setAfcatSmsScheduleStatus(status);
		SMSScheduleRepository.save(afcatSMSScheduleDetail);

		log.info("Sending message: " + afcatSMSSent.getMessage());

		applicantMobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(new AfcatApplicantCredential(number.getAppCredId()), afcatSMSSent,
					number.getMobile(), afcatSMSSent.getTemplateMasterId());
		});

		generateReport(mobileList, appCredIds, smsSentId, smsScheduleId, afcatSMSSent.getMessage(),
				afcatSMSScheduleDetail.getSmsType(), afcatSMSSent.getSmsSentType(), afcatSMSSent.getReqType());

		Timestamp end = new Timestamp(System.currentTimeMillis());

		afcatSMSScheduleDetail.setSmsScheduleEndDate(end);
		status = smsStatusRepository.findById(1L).get();
		afcatSMSScheduleDetail.setAfcatSmsScheduleStatus(status);

		SMSSentRepository.save(afcatSMSSent);
		SMSScheduleRepository.save(afcatSMSScheduleDetail);
		log.info("SMS Sent for SMSSent Id: " + afcatSMSSent.getSMSSentId());
		STATUS = null;
	}

	public Long addSMSSentEntry(SMSModel smsModel, int candidateCount, List<Long> appCredIds, List<String> mobileNos,
			String reqType, String smsType) {
		try {  
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
			 LocalDateTime now = LocalDateTime.now();  
	        fh = new FileHandler("D:/EmailSMS/sms_sent_logs/afcat/"+smsModel.getSmsReasonMasterId()+"_"+dtf.format(now)+".log");
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        
	        AFCATSMSSENTLOGGER.addHandler(fh);
	        
	        // the following statement is used to log any messages  
	        AFCATSMSSENTLOGGER.info(" *** AFCAT ***");  
	        AFCATSMSSENTLOGGER.info("Date & Time : "+dtf.format(now)); 
	        AFCATSMSSENTLOGGER.info("SMS Content : "+smsModel.getSmsContent()); 
	        
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 
		
		AfcatSMSSent afcatsmsSent = SMSSentRepository
				.save(new AfcatSMSSent(batchMasterRepository.findActiveBatch(), examMasterRepository.getActiveExamId(),
						candidateCount, smsModel.getSmsContent(), smsModel.getSmsReasonMasterId(), false,
						smsModel.getSmsSentType(), reqType, smsModel.getTemplateMasterId()));
		
		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			AfcatSMSScheduleDetail scheduleDetail = new AfcatSMSScheduleDetail(
					new Timestamp(System.currentTimeMillis()), candidateCount, smsType);

			AfcatSMSStatus status = smsStatusRepository.findById(3L).get();
			scheduleDetail.setAfcatSmsScheduleStatus(status);
			afcatsmsSent.getHelperScheduler(scheduleDetail);
		}
		if (smsModel.getSmsSentType() == 2 || smsModel.getSmsSentType() == 3) {
			for (int i = 0; i < smsModel.getSmsScheduleDatetime().size(); i++) {
				Timestamp later = smsModel.getSmsScheduleDatetime().get(i);

				AfcatSMSScheduleDetail scheduleDetail = new AfcatSMSScheduleDetail(later, candidateCount, smsType);
				AfcatSMSStatus status = smsStatusRepository.findById(3L).get();
				scheduleDetail.setAfcatSmsScheduleStatus(status);
				afcatsmsSent.getHelperScheduler(scheduleDetail);
			}
		}
		SMSSentRepository.save(afcatsmsSent);

		List<String> mobileNos2 = new ArrayList<String>();

		if (appCredIds != null) {

			int splitCount = candidateCount / MAX_LIMIT;
			int modCount = candidateCount % MAX_LIMIT;
			int j = 0;
			COUNT = 0;

			for (int i = 0; i < splitCount; i++, j += MAX_LIMIT) {
				STATUS = "Fetching mobile details from: " + j + " - " + (j + MAX_LIMIT) + " of " + candidateCount;
				log.info(STATUS);
				mobileNos2
						.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + MAX_LIMIT)));
			}
			if (modCount != 0) {
				STATUS = "Fetching details from: " + j + " - " + (j + modCount) + " of " + candidateCount;
				log.info(STATUS);
				mobileNos2
						.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + modCount)));
			}
		} else {
			mobileNos2.addAll(mobileNos);
		}

		AFCATSMSSENTLOGGER.info("Candidate Count : "+mobileNos2.size()+"\n\n"); 
		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			sendQuickSMS(mobileNos2, appCredIds, candidateCount, afcatsmsSent.getSMSSentId(), SMSScheduleRepository.getFirstScheduler(afcatsmsSent.getSMSSentId()));
		}else {
			generateReport(mobileNos2, appCredIds, afcatsmsSent.getSMSSentId(), SMSScheduleRepository.getFirstScheduler(afcatsmsSent.getSMSSentId()), afcatsmsSent.getMessage(), smsType, afcatsmsSent.getSmsSentType(), reqType);
		}
		
		return SMSScheduleRepository.getFirstScheduler(afcatsmsSent.getSMSSentId()); 
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
			AfcatSMSScheduleDetail assd = SMSScheduleRepository.findById(smsScheduleDetailId).get();
			AfcatSMSSent ass = SMSSentRepository.findById(assd.getAfcatSMSSent().getSMSSentId()).get();

			List<String> nos1 = new ArrayList<>();
			List<Long> nos2 = new ArrayList<>();

			if (mobileNos != null) {
				nos1.addAll(mobileNos);
			} else {
				AfcatSMSReportDetail reportGenerated = afcatSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(
						ass.getSMSSentId(), SMSScheduleRepository.getFirstScheduler(ass.getSMSSentId()));
				try {
					nos1.addAll(excelService.getExcel(Paths.get(reportGenerated.getFilePath()), true));

					if (appCredIds == null) {
						List<String> list = excelService.getExcel(Paths.get(reportGenerated.getFilePath()), false);
						for (int i = 0; i < list.size(); i++) {
							nos2.add(Long.parseLong(list.get(i)));
						}
					} else {
						nos2.addAll(appCredIds);
					}
				} catch (IOException e) {
					e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	@Override
	public void extractExcelReport(HttpServletResponse response, Long smsScheduleId) throws IOException {
		Long smsSentId = (SMSScheduleRepository.findById(smsScheduleId).get()).getAfcatSMSSent().getSMSSentId();
		AfcatSMSReportDetail report = afcatSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId, smsScheduleId);
		UserMobileExcelExporter exp = new UserMobileExcelExporter(report.getFilePath());
		exp.extractFile(response);			
	}

}
