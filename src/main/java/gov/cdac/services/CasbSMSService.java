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

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gov.cdac.casbPojo.CasbApplicantCredential;
import gov.cdac.casbPojo.CasbSMSReasonMaster;
import gov.cdac.casbPojo.CasbSMSReportDetail;
import gov.cdac.casbPojo.CasbSMSScheduleDetail;
import gov.cdac.casbPojo.CasbSMSSent;
import gov.cdac.casbPojo.CasbSMSStatus;
import gov.cdac.casbPojo.CasbSMSTemplateMaster;
import gov.cdac.casbRepository.ApplicantCentreAllotmentRepository;
import gov.cdac.casbRepository.BatchMasterRepository;
import gov.cdac.casbRepository.CasbSMSReportDetailRepository;
import gov.cdac.casbRepository.CasbSMSScheduleRepository;
import gov.cdac.casbRepository.CasbSMSSentRepository;
import gov.cdac.casbRepository.CentreMasterRepository;
import gov.cdac.casbRepository.ExamMasterRepository;
import gov.cdac.casbRepository.ExamSlotRepository;
import gov.cdac.casbRepository.PersonalDetailRepository;
import gov.cdac.casbRepository.SMSReasonMasterRepository;
import gov.cdac.casbRepository.SMSStatusRepository;
import gov.cdac.casbRepository.SMSTemplateMasterRepository;
import gov.cdac.excel.UserExcelExporter;
import gov.cdac.excel.UserMobileExcelExporter;
import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;
import gov.cdac.projection.ApplicantMobileSummary;
import gov.cdac.projection.CentreSlotCandidateCount;

@Service("casbSMSService")
public class CasbSMSService implements SMSService {
	private final Logger log = LogManager.getLogger(CasbSMSService.class);
	private static final java.util.logging.Logger CASBSMSSENTLOGGER = java.util.logging.Logger
			.getLogger(CasbSMSService.class.getName());

	private SMSTemplateMasterRepository SMSTemplateMasterRepository;
	private SMSReasonMasterRepository SMSReasonMasterRepository;
	private CasbSMSSentRepository SMSSentRepository;
	private BatchMasterRepository batchMasterRepository;
	private ExamMasterRepository examMasterRepository;
	private SMSSendService SMSSendService;
	private PersonalDetailRepository personalDetailRepository;
	private CasbSMSScheduleRepository casbSMSScheduleRepository;

	@Autowired
	private CentreMasterRepository centreMasterRepository;
	@Autowired
	private ExamSlotRepository examSlotRepository;
	@Autowired
	private ApplicantCentreAllotmentRepository acaRepository;
	@Autowired
	@Qualifier("casbSmsMobileNoExcelsheetServiceImpl")
	private SmsMobileNoExcelsheetService excelService;
	@Autowired
	private CasbSMSReportDetailRepository casbSMSReportDetailRepository;
	@Autowired
	private SMSStatusRepository smsStatusRepository;

	private static final int MAX_LIMIT = 30000;
	public static String STATUS = null;

	public static int COUNT = 0;

	private FileHandler fh;

	public CasbSMSService(SMSTemplateMasterRepository SMSTemplateMasterRepository,
			SMSReasonMasterRepository SMSReasonMasterRepository, CasbSMSSentRepository SMSSentRepository,
			BatchMasterRepository batchMasterRepository, ExamMasterRepository examMasterRepository,
			SMSSendService SMSSendService, PersonalDetailRepository personalDetailRepository,
			CasbSMSScheduleRepository SMSScheduleRepository) {
		this.SMSTemplateMasterRepository = SMSTemplateMasterRepository;
		this.SMSReasonMasterRepository = SMSReasonMasterRepository;
		this.SMSSentRepository = SMSSentRepository;
		this.batchMasterRepository = batchMasterRepository;
		this.examMasterRepository = examMasterRepository;
		this.SMSSendService = SMSSendService;
		this.personalDetailRepository = personalDetailRepository;
		this.casbSMSScheduleRepository = SMSScheduleRepository;
	}

	public Map<String, Object> getPageData() {
		Map<String, Object> pageDataMap = new HashMap<>();
		Integer examMasterId = examMasterRepository.getActiveExamId();
		pageDataMap.put("smsTemplates", SMSTemplateMasterRepository.findAllByOrderBySMSTemplateMasterIdDesc());
		pageDataMap.put("smsReasons", SMSReasonMasterRepository.findAllByOrderBySMSReason());
		pageDataMap.put("smsSent", SMSSentRepository.findSMSSentSummary());
		pageDataMap.put("centres", centreMasterRepository.findAllCentres(examMasterId));
		System.out.println("--> "+examSlotRepository.findByOrderByCode());
		pageDataMap.put("slots", examSlotRepository.findByOrderByCode());
		pageDataMap.put("addTemplate", new CasbSMSTemplateMaster());
		pageDataMap.put("smsModel", new SMSModel());
		pageDataMap.put("status", STATUS);
		pageDataMap.put("pageTitle", "CASB SMS Service");
		pageDataMap.put("pageType", "casb");
		return pageDataMap;
	}

	public void addSMSReasonMaster(SMSReasonMaster smsReasonMaster) {
		SMSReasonMasterRepository.save(new CasbSMSReasonMaster(smsReasonMaster.getSMSReasonMasterId()));
	}

	public void addSMSTemplate(SMSTemplateMaster smsTemplateMaster) {
		SMSTemplateMasterRepository.save(new CasbSMSTemplateMaster(smsTemplateMaster));
	}

	public List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds) {
		return acaRepository.findAppCredIdByCentreIdAndExamSlotId(centreId, slotIds);
	}

	@Async
	public void generateReport(List<String> mobileNos, List<Long> appCredIds, Long smsSentId, Long smsScheduleId,
			String msg, String smsType, int smsSentType, String reqType) {

		CasbSMSReportDetail report = casbSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId,
				smsScheduleId);
		if (report == null) {
			UserExcelExporter exp = new UserExcelExporter(appCredIds, mobileNos, msg, smsType, smsSentType, reqType);
			try {
				Path path = exp.export1();

				CasbSMSReportDetail asrd = new CasbSMSReportDetail();
				asrd.setFilePath(path.toString());
				CasbSMSReportDetail report2 = new CasbSMSReportDetail();
				report2.setFilePath(path.toString());
				report2.setCasbSMSSent(SMSSentRepository.findById(smsSentId).get());
				report2.setReportDetail(true);

				report2.setCasbSMSScheduleDetail(casbSMSScheduleRepository.findById(smsScheduleId).get());
				casbSMSReportDetailRepository.save(report2);
				excelService.saveData(path.toString(), smsType, smsSentType);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			report.setReportDetail(true);
			casbSMSReportDetailRepository.save(report);
		}
	}

	@Async
	public void sendQuickSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, Long smsSentId,
			Long smsScheduleId) {
		COUNT = 0;

		CasbSMSSent casbSMSSent = SMSSentRepository.findById(smsSentId).get();
		CasbSMSScheduleDetail casbSMSScheduleDetail = casbSMSScheduleRepository.findById(smsScheduleId).get();
		CasbSMSStatus status = smsStatusRepository.findById(4L).get();
		casbSMSScheduleDetail.setCasbSmsScheduleStatus(status);
		casbSMSScheduleRepository.save(casbSMSScheduleDetail);

		mobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(null, casbSMSSent, number, casbSMSSent.getTemplateMasterId());
			CASBSMSSENTLOGGER.info("SMS Sent To : " + number);
		});

		Timestamp end = new Timestamp(System.currentTimeMillis());
		casbSMSScheduleDetail.setSmsScheduleEndDate(end);
		status = smsStatusRepository.findById(1L).get();
		casbSMSScheduleDetail.setCasbSmsScheduleStatus(status);

		STATUS = null;
		SMSSentRepository.save(casbSMSSent);
		casbSMSScheduleRepository.save(casbSMSScheduleDetail);
		log.info("SMS Sent for SMSSent Id: " + casbSMSSent.getSMSSentId());

		generateReport(mobileList, appCredIds, smsSentId, smsScheduleId, casbSMSSent.getMessage(),
				casbSMSScheduleDetail.getSmsType(), casbSMSSent.getSmsSentType(), casbSMSSent.getReqType());

		System.out.println("Report generated! : SMS Sent Id (" + smsSentId + ")");
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

		CasbSMSSent casbSMSSent = SMSSentRepository.findById(smsSentId).get();
		CasbSMSScheduleDetail casbSMSScheduleDetail = casbSMSScheduleRepository.findById(smsScheduleId).get();
		CasbSMSStatus status = smsStatusRepository.findById(4L).get();
		casbSMSScheduleDetail.setCasbSmsScheduleStatus(status);
		casbSMSScheduleRepository.save(casbSMSScheduleDetail);

		log.info("Sending message: " + casbSMSSent.getMessage());

		applicantMobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(new CasbApplicantCredential(number.getAppCredId()), casbSMSSent,
					number.getMobile(), casbSMSSent.getTemplateMasterId());
		});

		generateReport(mobileList, appCredIds, smsSentId, smsScheduleId, casbSMSSent.getMessage(),
				casbSMSScheduleDetail.getSmsType(), casbSMSSent.getSmsSentType(), casbSMSSent.getReqType());

		Timestamp end = new Timestamp(System.currentTimeMillis());

		casbSMSScheduleDetail.setSmsScheduleEndDate(end);
		status = smsStatusRepository.findById(1L).get();
		casbSMSScheduleDetail.setCasbSmsScheduleStatus(status);

		SMSSentRepository.save(casbSMSSent);
		casbSMSScheduleRepository.save(casbSMSScheduleDetail);
		log.info("SMS Sent for SMSSent Id: " + casbSMSSent.getSMSSentId());
		STATUS = null;
	}

	public Long addSMSSentEntry(SMSModel smsModel, int candidateCount, List<Long> appCredIds, List<String> mobileNos,
			String reqType, String smsType) {

		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			fh = new FileHandler("D:/EmailSMS/sms_sent_logs/agniveer/" + smsModel.getSmsReasonMasterId() + "_"
					+ dtf.format(now) + ".log");
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			CASBSMSSENTLOGGER.addHandler(fh);

			// the following statement is used to log any messages
			CASBSMSSENTLOGGER.info(" *** CASB ***");
			CASBSMSSENTLOGGER.info("Date & Time : " + dtf.format(now));
			CASBSMSSENTLOGGER.info("SMS Content : " + smsModel.getSmsContent());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		CasbSMSSent casbsmsSent = SMSSentRepository
				.save(new CasbSMSSent(batchMasterRepository.findActiveBatch(), examMasterRepository.getActiveExamId(),
						candidateCount, smsModel.getSmsContent(), smsModel.getSmsReasonMasterId(), false,
						smsModel.getSmsSentType(), reqType, smsModel.getTemplateMasterId()));
		System.out.println("SMS_Sent_Type:" + smsModel.getSmsSentType());

		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			CasbSMSScheduleDetail scheduleDetail = new CasbSMSScheduleDetail(new Timestamp(System.currentTimeMillis()),
					candidateCount, smsType);
			CasbSMSStatus status = smsStatusRepository.findById(3L).get();
			scheduleDetail.setCasbSmsScheduleStatus(status);
			casbsmsSent.getHelperScheduler(scheduleDetail);
		}
		if (smsModel.getSmsSentType() == 2 || smsModel.getSmsSentType() == 3) {
			for (int i = 0; i < smsModel.getSmsScheduleDatetime().size(); i++) {
				Timestamp later = smsModel.getSmsScheduleDatetime().get(i);
				CasbSMSScheduleDetail scheduleDetail = new CasbSMSScheduleDetail(later, candidateCount, smsType);
				CasbSMSStatus status = smsStatusRepository.findById(3L).get();
				scheduleDetail.setCasbSmsScheduleStatus(status);
				casbsmsSent.getHelperScheduler(scheduleDetail);
			}
		}
		SMSSentRepository.save(casbsmsSent);

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
				mobileNos2.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + modCount)));
			}
		} else {
			mobileNos2.addAll(mobileNos);
		}
		CASBSMSSENTLOGGER.info("Candidate Count : " + mobileNos2.size() + "\n\n");

		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			sendQuickSMS(mobileNos2, appCredIds, candidateCount, casbsmsSent.getSMSSentId(),
					casbSMSScheduleRepository.getFirstScheduler(casbsmsSent.getSMSSentId()));
		} else {
			generateReport(mobileNos2, appCredIds, casbsmsSent.getSMSSentId(),
					casbSMSScheduleRepository.getFirstScheduler(casbsmsSent.getSMSSentId()), casbsmsSent.getMessage(),
					smsType, casbsmsSent.getSmsSentType(), reqType);
		}
		return casbSMSScheduleRepository.getFirstScheduler(casbsmsSent.getSMSSentId());
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

//	public void sendQuickSMS(List<String> mobileList, int candidateCount, String templateId) {
//		COUNT = 0;
//		CasbSMSSent smsSent = SMSSentRepository.findTop1ByOrderBySMSSentIdDesc();
//		mobileList.forEach(number -> {
//			SMSSendService.sendSingleSMS(null, smsSent, number, templateId);
//		});
//
//		log.info("SMS Sent for SMSSent Id: " + smsSent.getSMSSentId());
//		STATUS = null;
//	}

	@Async
	public void sendSMSAsync(List<String> mobileNos, List<Long> appCredIds, Long smsScheduleDetailId) {
		try {
			CasbSMSScheduleDetail assd = casbSMSScheduleRepository.findById(smsScheduleDetailId).get();
			CasbSMSSent ass = SMSSentRepository.findById(assd.getCasbSMSSent().getSMSSentId()).get();

			List<String> nos1 = new ArrayList<>();
			List<Long> nos2 = new ArrayList<>();

			if (mobileNos != null) {
				nos1.addAll(mobileNos);
			} else {
				CasbSMSReportDetail reportGenerated = casbSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(
						ass.getSMSSentId(), casbSMSScheduleRepository.getFirstScheduler(ass.getSMSSentId()));
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
		Long smsSentId = (casbSMSScheduleRepository.findById(smsScheduleId).get()).getCasbSMSSent().getSMSSentId();
		CasbSMSReportDetail report = casbSMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId,
				smsScheduleId);
		UserMobileExcelExporter exp = new UserMobileExcelExporter(report.getFilePath());
		exp.extractFile(response);
	}

}
