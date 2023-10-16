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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gov.cdac.excel.UserMobileExcelExporter;
import gov.cdac.icgPojo.IcgSMSReasonMaster;
import gov.cdac.icgPojo.IcgSMSTemplateMaster;
import gov.cdac.icgPojo.SMSReportDetail;
import gov.cdac.icgPojo.SMSScheduleDetail;
import gov.cdac.icgPojo.SMSSent;
import gov.cdac.icgPojo.SMSStatus;
import gov.cdac.icgRepositories.ApplicantCentreAllotmentRepository;
import gov.cdac.icgRepositories.BatchMasterRepository;
import gov.cdac.icgRepositories.CentreMasterRepository;
import gov.cdac.icgRepositories.ExamMasterRepository;
import gov.cdac.icgRepositories.ExamSlotRepository;
import gov.cdac.icgRepositories.PersonalDetailRepository;
import gov.cdac.icgRepositories.SMSReasonMasterRepository;
import gov.cdac.icgRepositories.SMSReportDetailRepository;
import gov.cdac.icgRepositories.SMSScheduleRepository;
import gov.cdac.icgRepositories.SMSSentRepository;
import gov.cdac.icgRepositories.SMSStatusRepository;
import gov.cdac.icgRepositories.SMSTemplateMasterRepository;
import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;
import gov.cdac.projection.CentreSlotCandidateCount;

@Service("icgSMSService")
public class ICGSMSService implements SMSService {
    private final Logger log = LogManager.getLogger(ICGSMSService.class);
	private static final java.util.logging.Logger ICGSMSSENTLOGGER = java.util.logging.Logger.getLogger(ICGSMSService.class.getName());


    private gov.cdac.icgRepositories.SMSTemplateMasterRepository SMSTemplateMasterRepository;
    private SMSReasonMasterRepository SMSReasonMasterRepository;
    private SMSSentRepository SMSSentRepository;
    private BatchMasterRepository batchMasterRepository;
    private ExamMasterRepository examMasterRepository;
    private SMSSendService SMSSendService;
    private PersonalDetailRepository personalDetailRepository;

    @Autowired
    private CentreMasterRepository centreMasterRepository;
    @Autowired
    private ExamSlotRepository examSlotRepository;
    @Autowired
    private ApplicantCentreAllotmentRepository acaRepository;
	@Autowired
	@Qualifier("icgSmsMobileNoExcelsheetServiceImpl")
	private SmsMobileNoExcelsheetService excelService;
	
	@Autowired
	private SMSReportDetailRepository SMSReportDetailRepository;
	
	@Autowired
	private SMSStatusRepository smsStatusRepository;
	@Autowired
	private SMSScheduleRepository icgSMSScheduleRepository;

	@Autowired
	private ApplicationContext applicationContext;
	
    private static final int MAX_LIMIT = 3;
    public static String STATUS = null;

    public static int COUNT = 0;
    
    private FileHandler fh; 

    @Autowired
    public ICGSMSService(SMSTemplateMasterRepository SMSTemplateMasterRepository,
	    SMSReasonMasterRepository SMSReasonMasterRepository, SMSSentRepository SMSSentRepository,
	    BatchMasterRepository batchMasterRepository, ExamMasterRepository examMasterRepository,
	    SMSSendService SMSSendService, PersonalDetailRepository personalDetailRepository) {
	this.SMSTemplateMasterRepository = SMSTemplateMasterRepository;
	this.SMSReasonMasterRepository = SMSReasonMasterRepository;
	this.SMSSentRepository = SMSSentRepository;
	this.batchMasterRepository = batchMasterRepository;
	this.examMasterRepository = examMasterRepository;
	this.SMSSendService = SMSSendService;
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
	pageDataMap.put("pageTitle", "ICG SMS Service");
	pageDataMap.put("pageType", "icg");
	return pageDataMap;
    }

    public void addSMSReasonMaster(SMSReasonMaster smsReasonMaster) {
	SMSReasonMasterRepository.save(new IcgSMSReasonMaster(smsReasonMaster.getSMSReasonMasterId()));
    }

    public void addSMSTemplate(SMSTemplateMaster smsTemplateMaster) {
	SMSTemplateMasterRepository.save(new IcgSMSTemplateMaster(smsTemplateMaster));
    }

    public List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds) {
	return acaRepository.findAppCredIdByCentreIdAndExamSlotId(centreId, slotIds);
    }

	public void generateReport(List<String> mobileNos, List<Long> appCredIds, Long smsSentId, Long smsScheduleId,
			String msg, String smsType, int smsSentType, String reqType) {
		SMSReportDetail report = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId, smsScheduleId);
		if (report == null) {
			System.out.println("New Report!");
			UserMobileExcelExporter exp = new UserMobileExcelExporter(appCredIds, mobileNos, msg, smsType, smsSentType, reqType);
			try {
				Path path = exp.export1();

				SMSReportDetail report2 = new SMSReportDetail();
				report2.setFilePath(path.toString());
				report2.setSmsSent(SMSSentRepository.findById(smsSentId).get());
				report2.setReportDetail(true);
				report2.setSmsScheduleDetail(icgSMSScheduleRepository.findById(smsScheduleId).get());
				
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
		SMSSent icgSMSSent = SMSSentRepository.findById(smsSentId).get();
		SMSScheduleDetail icgSMSScheduleDetail = icgSMSScheduleRepository.findById(smsScheduleId).get();
		SMSStatus status = smsStatusRepository.findById(4L).get();
		icgSMSScheduleDetail.setSmsScheduleStatus(status);
		icgSMSScheduleRepository.save(icgSMSScheduleDetail);

		mobileList.forEach(number -> {
			SMSSendService.sendSingleSMS(null, icgSMSSent, number, icgSMSSent.getTemplateMasterId());
			ICGSMSSENTLOGGER.info("SMS Sent To : " + number);
		});
		//------------------------------------------
		
		/*int splitCount = mobileList.size() / MAX_LIMIT;
		int modCount = mobileList.size() % MAX_LIMIT;
		int j = 0;
		COUNT = 0;

		List<String> mobileList2 = new ArrayList<String>();
		for (int i = 1; i <= splitCount; i++, j += MAX_LIMIT) {
			STATUS = "Fetching mobile details from: " + j + " - " + (j + MAX_LIMIT-1) + " of " + mobileList.size();
			log.info(STATUS);
			mobileList2.clear();
			mobileList2.addAll(mobileList.subList(j, (MAX_LIMIT*i)));
			mobileList2.forEach(number -> {
				SMSSendService.sendSingleSMS(null, icgSMSSent, number, icgSMSSent.getTemplateMasterId());
			});
			
			try {
				System.out.println("Waiting .....");
				Thread.sleep(20000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (modCount != 0) {
			STATUS = "Fetching details from: " + j + " - " + (j + modCount-1) + " of " + mobileList.size();
			log.info(STATUS);
			mobileList2.clear();
			mobileList2.addAll(mobileList.subList(j, j+modCount));
			
			mobileList2.forEach(number -> {
				SMSSendService.sendSingleSMS(null, icgSMSSent, number, icgSMSSent.getTemplateMasterId());
			});
		}
		
		Multithreading...
				if (mobileList.size() > 0) {
					ArrayList<String> oneThousandSubset = new ArrayList<String>();
					ArrayList<String> oneHundredSubset = new ArrayList<String>();
					int outerCount = 1;
					int outerExitCounter = 0;
					for (String mobileNo1 : mobileList) {
						outerExitCounter++;
						oneThousandSubset.add(mobileNo1);
						outerCount++;
						if ((outerCount > 6) || (outerExitCounter >= mobileList.size())) {
							log.info("outer count : " + outerCount + " outerExitCounter : " + outerExitCounter);
							outerCount = 1;
							int oneThousandSubSetSize = oneThousandSubset.size();
						
							String threadSize = "3";
							int smsThreadSize = (int) (Math.ceil((double) oneThousandSubSetSize / Integer.parseInt(threadSize)));
							SmsThreadExcel[] smsThreadArray = new SmsThreadExcel[smsThreadSize];
							
							int innerCount = 1;
							int arrayIndex = 0;
							int exitCounter = 0;
							for (String mobileNo2 : oneThousandSubset) {
								exitCounter++;
								oneHundredSubset.add(mobileNo2);
								innerCount++;
								if ((innerCount > Integer.parseInt(threadSize)) || (exitCounter >= oneThousandSubSetSize)) {
									log.info("innerCount : " + innerCount + " exitCounter : " + exitCounter);
									innerCount = 1;
									//log.info("oneHundredSubSetOfMap.size() : " + oneHundredSubset.size());
									log.info("\nMobile Nos :"+oneHundredSubset);
									smsThreadArray[arrayIndex] = new SmsThreadExcel(icgSMSSent, icgSMSSent.getTemplateMasterId(), oneHundredSubset);
									applicationContext.getAutowireCapableBeanFactory().autowireBean(smsThreadArray[arrayIndex]);
									smsThreadArray[arrayIndex].setName("MyThread-" + arrayIndex);
									oneHundredSubset.clear();
									arrayIndex++;
								}
							}					
							
						   final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
						   executor.setCorePoolSize(10);
						   executor.setMaxPoolSize(20);
						   executor.setQueueCapacity(100);
						   executor.setThreadNamePrefix("ImageThread-");
						   executor.initialize();

						   for (int i = 0; i < smsThreadArray.length; i++) {
								//smsThreadArray[i].start();
								smsThreadArray[i].start();
							}
							for (int i = 0; i < smsThreadArray.length; i++) {
								try {
									smsThreadArray[i].join();
								} catch (InterruptedException e) {
									log.info("inside InterruptedException of sendQuickSMS-join");
									e.printStackTrace();
								} catch (Exception e) {
									log.info("inside Exception of sendQuickSMS-join");
									e.printStackTrace();
								}
							}

							if (outerExitCounter < mobileList.size()) {
								try {
									log.info("Waiting for 2 minute after 6 mails...");
									Thread.sleep(1000 * 60);
								} catch (InterruptedException e) {
									log.info("inside InterruptedException of sendQuickSMS-sleep");
									e.printStackTrace();
								} catch (Exception e) {
									log.info("inside Exception of sendQuickSMS-sleep");
									e.printStackTrace();
								}
							}
							oneThousandSubset.clear();
							oneHundredSubset.clear();
						}
					}
				}
		
		*/
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
		
			COUNT = 0;
			SMSSent SMSSent = SMSSentRepository.findById(smsSentId).get();
			SMSScheduleDetail SMSScheduleDetail = icgSMSScheduleRepository.findById(smsScheduleId).get();

			SMSStatus status = smsStatusRepository.findById(4L).get();
			SMSScheduleDetail.setSmsScheduleStatus(status);
			icgSMSScheduleRepository.save(SMSScheduleDetail);

			log.info("Sending message: " + SMSSent.getMessage());

			mobileNoList.forEach(number -> {
				SMSSendService.sendSingleSMS(null, SMSSent, number, SMSSent.getTemplateMasterId());
			});

			generateReport(mobileNoList, appCredIds, smsSentId, smsScheduleId, SMSSent.getMessage(),
					SMSScheduleDetail.getSmsType(), SMSSent.getSmsSentType(), SMSSent.getReqType());

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
	        fh = new FileHandler("D:/EmailSMS/sms_sent_logs/icg/"+smsModel.getSmsReasonMasterId()+"_"+dtf.format(now)+".log");
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	        
	        ICGSMSSENTLOGGER.addHandler(fh);
	        
	        // the following statement is used to log any messages  
	        ICGSMSSENTLOGGER.info(" *** ICG ***");  
	        ICGSMSSENTLOGGER.info("Date & Time : "+dtf.format(now)); 
	        ICGSMSSENTLOGGER.info("SMS Content : "+smsModel.getSmsContent()); 
	        
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 

		SMSSent smsSent = SMSSentRepository
				.save(new SMSSent(batchMasterRepository.findActiveBatch(), examMasterRepository.getActiveExamId(),
						candidateCount, smsModel.getSmsContent(), smsModel.getSmsReasonMasterId(), false,
						smsModel.getSmsSentType(), reqType, smsModel.getTemplateMasterId()));
		if (smsModel.getSmsSentType() == 1 || smsModel.getSmsSentType() == 2) {
			SMSScheduleDetail scheduleDetail = new SMSScheduleDetail(new Timestamp(System.currentTimeMillis()),
					candidateCount, smsType);

			SMSStatus status = smsStatusRepository.findById(3L).get();
			scheduleDetail.setSmsScheduleStatus(status);
			icgSMSScheduleRepository.save(scheduleDetail);
			smsSent.getHelperScheduler(scheduleDetail);
		}
		if (smsModel.getSmsSentType() == 2 || smsModel.getSmsSentType() == 3) {
			for (int i = 0; i < smsModel.getSmsScheduleDatetime().size(); i++) {
				Timestamp later = smsModel.getSmsScheduleDatetime().get(i);

				SMSScheduleDetail scheduleDetail = new SMSScheduleDetail(later, candidateCount, smsType);
				SMSStatus status = smsStatusRepository.findById(3L).get();
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
				mobileNos2.addAll(personalDetailRepository.findMobileByAppCredId2(appCredIds.subList(j, j + modCount)));
			}

		} else {
			mobileNos2.addAll(mobileNos);
		}

		ICGSMSSENTLOGGER.info("Candidate Count : "+mobileNos2.size()+"\n\n"); 

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
			SMSScheduleDetail assd = icgSMSScheduleRepository.findById(smsScheduleDetailId).get();
			SMSSent ass = SMSSentRepository.findById(assd.getSmsSent().getSMSSentId()).get();
			
			List<String> nos1 = new ArrayList<>();
			List<Long> nos2 = new ArrayList<>();

			if (mobileNos != null) {
				nos1.addAll(mobileNos);
			} else {
				SMSReportDetail reportGenerated = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(
						ass.getSMSSentId(), icgSMSScheduleRepository.getFirstScheduler(ass.getSMSSentId()));
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
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void extractExcelReport(HttpServletResponse response, Long smsScheduleId) throws IOException {
		Long smsSentId = (icgSMSScheduleRepository.findById(smsScheduleId).get()).getSmsSent().getSMSSentId();
		SMSReportDetail report = SMSReportDetailRepository.findBySmsSentIdAndSmsScheduleId(smsSentId, smsScheduleId);
		UserMobileExcelExporter exp = new UserMobileExcelExporter(report.getFilePath());
		exp.extractFile(response);			
	}

}
