package gov.cdac.emailservice.services;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import gov.cdac.emailservice.Threads.CasbMailThread;
import gov.cdac.emailservice.Threads.MailThreaRejected;
import gov.cdac.emailservice.Threads.MailThreadExcel;
import gov.cdac.emailservice.casb.pojo.CasbEmailAttachmentFileDetail;
import gov.cdac.emailservice.casb.pojo.CasbEmailReportDetail;
import gov.cdac.emailservice.casb.pojo.CasbEmailScheduleDetail;
import gov.cdac.emailservice.casb.pojo.CasbEmailSent;
import gov.cdac.emailservice.casb.pojo.CasbEmailStatus;
import gov.cdac.emailservice.casb.pojo.CasbEmailTextReportDetails;
import gov.cdac.emailservice.casb.repository.CentreMasterRepository;
import gov.cdac.emailservice.casb.repository.EmailAttachmentFileDetailRepository;
import gov.cdac.emailservice.casb.repository.EmailReasonMasterRepository;
import gov.cdac.emailservice.casb.repository.EmailReportDetailRepository;
import gov.cdac.emailservice.casb.repository.EmailScheduleDetailRepository;
import gov.cdac.emailservice.casb.repository.EmailSentRepository;
import gov.cdac.emailservice.casb.repository.EmailStatusRepository;
import gov.cdac.emailservice.casb.repository.EmailTextReportDetailsRepository;
import gov.cdac.emailservice.casb.repository.ExamMasterRepository;
import gov.cdac.emailservice.casb.repository.ExamSlotRepository;
import gov.cdac.emailservice.excel.UserExcelExporter;
import gov.cdac.emailservice.models.EmailModel;
import gov.cdac.emailservice.models.ReportInfo;
import gov.cdac.emailservice.models.TestEmailBulkModel;
import gov.cdac.emailservice.projection.EmailProjection;

@Service("casbMailService")
@PropertySource("classpath:mailCredentials.properties")
@PropertySource("classpath:filePath.properties")
public class CasbMailService implements MailService {
	 private ExamMasterRepository examMasterRepository;
	    private CentreMasterRepository centreMasterRepository;
	    private ExamSlotRepository examSlotRepository;
	    private EmailReasonMasterRepository emailReasonMasterRepository;
	    private EmailSentRepository emailSentRepository;
		private EmailScheduleDetailRepository emailScheduleDetailRepository;
		private EmailStatusRepository emailStatusRepository;
		private EmailReportDetailRepository emailReportDetailRepository;
		private EmailTextReportDetailsRepository emailTextReportDetailsRepository;
		private EmailAttachmentFileDetailRepository emailAttachmentFileDetailRepository;
	    private FileUploadService fileUploadService;
		private ApplicantHallTicketService applicantHallTicketService;

		@Autowired
		@Qualifier("casbEmailExcelsheetServiceImpl")
		private EmailExcelsheetService excelService;
		
		@Value("${casb.mail.userName}")
		private String mailUserName;

		@Value("${casb.mail.password}")
		private String mailPassword;

		@Value("${filePath.casbEmailAttachmentsDir}")
		private String emailAttachmentDirFromPropertyFile;
		
		@Value("${filePath.casbTextReportDownloadDir}")
		private String casbTextReportDownloadDir;

		@Value("${mail.mailShouldBeSend}")
		private boolean mailShouldBeSend;

		@Value("${filePath.admitCardPath}")
		private String admitCardFilePathFromPropertyFile;
		
		@Value("${filePath.downloadExcelReportDownloadDir}")
		private String downloadExcelReportDownloadDir;

		private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");
		private static final Logger acknowledgementOfSentEmail = Logger.getLogger("CenterWiseSendEmail");

		private ArrayList<String> notSentEmailIds = new ArrayList<String>();

		private ArrayList<String> SentEmailIds = new ArrayList<String>();

	    public static String STATUS = null;

	    public static int COUNT = 0;
	    
		private final int ARBITARY_SIZE = 1048;

	    public CasbMailService(ExamMasterRepository examMasterRepository, CentreMasterRepository centreMasterRepository,
				ExamSlotRepository examSlotRepository, EmailReasonMasterRepository emailReasonMasterRepository, 
				EmailSentRepository emailSentRepository, EmailScheduleDetailRepository emailScheduleDetailRepository,
				EmailStatusRepository emailStatusRepository, EmailReportDetailRepository emailReportDetailRepository,
				EmailTextReportDetailsRepository emailTextReportDetailsRepository,
				EmailAttachmentFileDetailRepository emailAttachmentFileDetailRepository,
				FileUploadService fileUploadService, 
				ApplicantHallTicketService applicantHallTicketService) {
			this.examMasterRepository = examMasterRepository;
			this.centreMasterRepository = centreMasterRepository;
			this.examSlotRepository = examSlotRepository;
			this.emailReasonMasterRepository = emailReasonMasterRepository;
			this.emailSentRepository= emailSentRepository;
			this.emailScheduleDetailRepository = emailScheduleDetailRepository;
			this.emailStatusRepository = emailStatusRepository;
			this.emailReportDetailRepository = emailReportDetailRepository;
			this.emailTextReportDetailsRepository = emailTextReportDetailsRepository;
			this.emailAttachmentFileDetailRepository = emailAttachmentFileDetailRepository;
			this.fileUploadService = fileUploadService;
			this.applicantHallTicketService = applicantHallTicketService;
		}

		public Map<String, Object> getPageData() {
		Map<String, Object> pageDataMap = new HashMap<>();
		Integer examMasterId = examMasterRepository.getActiveExamId();
		pageDataMap.put("emailReasons", emailReasonMasterRepository.findAllByOrderByEmailReason());
		System.out.println("emailSentRepository.findEmailSentSummary() : "+emailSentRepository.findEmailSentSummary());
		pageDataMap.put("emailSent", emailSentRepository.findEmailSentSummary());
		pageDataMap.put("centres", centreMasterRepository.findAllCentres(examMasterId));
		pageDataMap.put("slots", examSlotRepository.findByOrderByCode());
		pageDataMap.put("emailModel", new EmailModel());
		pageDataMap.put("status", STATUS);
		pageDataMap.put("pageTitle", "CASB Email Service");
		pageDataMap.put("pageType", "casb");
		pageDataMap.put("reports", emailTextReportDetailsRepository.getAllReportPaths());
		return pageDataMap;
	    }

		@Override
		public void extractExcelReport(Long scheduleId, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
			Long emailSentId = (emailScheduleDetailRepository.findById(scheduleId).get()).getEmailSent().getEmailSentId();
			CasbEmailReportDetail report = emailReportDetailRepository.findByEmailSentIdAndEmailScheduleId(emailSentId, scheduleId);
			System.out.println("Path : "+report.getFilePath());
			UserExcelExporter exp = new UserExcelExporter(report.getFilePath());
			exp.extractFile();			
		}
		
		@Override
		public void extractTextReport(String path, HttpServletRequest  httpRequest, HttpServletResponse httpResponse) throws IOException {
			File mainFile = new File(path);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

			if (mainFile.isFile()) {
				System.out.println("is File : "+mainFile.getName());

				if (mainFile.getName().indexOf(".txt") > -1) {
					System.out.println("text");
					httpResponse.setContentType("text/plain");
				}else {
					FileInputStream in = new FileInputStream(mainFile);

					File newFile = new File(
							downloadExcelReportDownloadDir+"file_" + java.time.LocalDateTime.now().format(format)
									+ mainFile.getName().substring(mainFile.getName().lastIndexOf(".")));
					System.out.println("\n\nnewFile"+newFile.getAbsolutePath());
					FileOutputStream out = new FileOutputStream(newFile);

					try {
						int n;
						while ((n = in.read()) != -1) {
							out.write(n);
						}
					} finally {
						if (in != null) {
							in.close();
						}
						if (out != null) {
							out.close();
						}
					}
					
					return;
				}
				httpResponse.setHeader("Content-disposition", "attachment; filename=" + mainFile.getName());
				httpResponse.setHeader("Content-Transfer-Encoding", "binary");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(httpResponse.getOutputStream());
					FileInputStream fis = new FileInputStream(mainFile.getAbsolutePath());

					int len;
					byte[] buf = new byte[ARBITARY_SIZE];

					while ((len = fis.read(buf)) > 0) {
						bos.write(buf, 0, len);
					}
					bos.close();
					httpResponse.flushBuffer();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;

			}else {
				System.out.println("Given path is a Directory path");
				return;
			}
		}
		
		public void uploadEmailInTable(ArrayList<String> emailIds){
			Long count = emailTextReportDetailsRepository.count() + 1;
			for (String emailId : emailIds) {
				System.out.println("emailId"+emailId);
				System.out.println(emailTextReportDetailsRepository.findReportPathByEmailId(emailId));
				if (emailTextReportDetailsRepository.findReportPathByEmailId(emailId) == null) {
					System.out.println("not null");
					String path1 = casbTextReportDownloadDir + count;
					File theDir1 = new File(path1);
					if (!theDir1.exists()) {
						theDir1.mkdirs();
					}
					
					System.out.println("Path : "+path1);

					CasbEmailTextReportDetails casbEmailTextReportDetails = new CasbEmailTextReportDetails(emailId, path1);

					emailTextReportDetailsRepository.save(casbEmailTextReportDetails);
					count++;
				}
			}
		}
		
		@Async
		public void generateReport(List<String> emailIds, List<Long> appCredIds, Long emailSentId, Long emailScheduleId,
				String subject, String body, int sentType, String reqType) {
			System.out.println("Report generated for : "+emailSentId+" | "+emailScheduleId);
			CasbEmailReportDetail report = emailReportDetailRepository.findByEmailSentIdAndEmailScheduleId(emailSentId, emailScheduleId);
			if (report == null) {
				System.out.println("report is null : 1st scheduler..!!!");
				UserExcelExporter exp = new UserExcelExporter(appCredIds, emailIds, subject, body, sentType, reqType);
				try {
					Path path = exp.export1();

					CasbEmailReportDetail report2 = new CasbEmailReportDetail();
					report2.setFilePath(path.toString());
					report2.setEmailSent(emailSentRepository.findById(emailSentId).get());
					report2.setReportDetail(true);
					report2.setEmailScheduleDetail(emailScheduleDetailRepository.findById(emailScheduleId).get());
					
					emailReportDetailRepository.save(report2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				report.setReportDetail(true);
				emailReportDetailRepository.save(report);
			}
		}
		
		
		
		//AishwaryaG
		public String addEmailSentEntry(EmailModel emailModel, String emailType, String reqType, HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.setAttribute("disableEnableButtons", "disable");
			notSentEmailIds.clear();
			SentEmailIds.clear();
			ArrayList<File> fileArray = fileUploadService.findFiles(reqType);
			// server host
			if (emailModel.getMailServerHost().isEmpty() || emailModel.getMailServerHost().length() > 20) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
					System.out.println("File Name:"+files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server host is not entered properly or is null");
				clearCache();
				return "mail server host is not entered properly or is null";
			}
			// server port
			if (emailModel.getMailServerPort().isEmpty() || emailModel.getMailServerPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
					System.out.println("File Name:"+files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server port is not entered properly or is null");
				clearCache();
				return "mail server port is not entered properly or is null";
			}
			// socket factory port
			if (emailModel.getSocketFactoryPort().isEmpty() || emailModel.getSocketFactoryPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail socket factory port is not entered properly or is null");
				clearCache();
				return "mail socket factory port is not entered properly or is null";
			}
			// email content
			if (Jsoup.parse(emailModel.getEmailContent()).text().isEmpty()) {
				clearCache();
				return "Please enter email content";
			}
			// email subject
			if (emailModel.getEmailSubject().isEmpty()) {
				clearCache();
				return "Please enter email subject";
			}
			
			//*********************************************************//
			List<String> emailIds = new ArrayList<>();
			CasbEmailSent emailSent = null;
			
			if(emailModel.getCenters() != null && emailModel.getSlots() != null) {
				String[] slotsArray = emailModel.getSlots().split(",");
				ArrayList<String> slotsArrayList = new ArrayList<String>(Arrays.asList(slotsArray));

				String[] centersArray = emailModel.getCenters().split(",");
				ArrayList<String> centersArrayList = new ArrayList<String>(Arrays.asList(centersArray));

				Map<String, String> emailIdsAndHallTicketNumbersToReturn = new LinkedHashMap<>();

				List<Object[]> allEmailIdsAndHallTicketNumbers = new ArrayList<Object[]>();

				if (emailModel.getExam().equals("regularExam")) {
					allEmailIdsAndHallTicketNumbers = applicantHallTicketService
							.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(slotsArrayList, centersArrayList);
				} else if (emailModel.getExam().equals("reExam")) {
					allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(slotsArrayList,
								centersArrayList);
				} else {
					clearCache();
					return "Please choose exam type correctly";
				}
			
				for (Object[] emailIdAndHallTicketNumber : allEmailIdsAndHallTicketNumbers) {
					String emailId = (String) emailIdAndHallTicketNumber[0];
					String hallTicketNumber = (String) emailIdAndHallTicketNumber[1];
					emailIdsAndHallTicketNumbersToReturn.put(emailId, hallTicketNumber);
					emailIds.add(emailId);
				}
				for (Map.Entry<String, String> emailIdAndHallTicketNumber : emailIdsAndHallTicketNumbersToReturn.entrySet()) {
					centerWiseSendEmail.info(emailIdAndHallTicketNumber.getKey() + "/" + emailIdAndHallTicketNumber.getValue());
				}
				centerWiseSendEmail.info("allEmailIdsAndHallTicketNumbers.size() : " + allEmailIdsAndHallTicketNumbers.size());
			
				emailSent = emailSentRepository.save(new CasbEmailSent(examMasterRepository.getOne(1), emailIds.size(), 
						emailModel.getEmailSubject(), emailModel.getEmailContent(), emailModel.getMailReason(),
						new Timestamp(System.currentTimeMillis()), emailModel.getSentType(), false, emailModel.getPageType(),
						emailModel.getMailServerHost(), emailModel.getMailServerPort(),
						emailModel.getStarttls(), emailModel.getSocketFactoryPort(),
						emailModel.getSendAdmitCardsRadio(), emailModel.getSlots(), emailModel.getCenters(),
						emailModel.getEmailBreathingTime(), emailModel.getEmailsPerBatch()));
			}else {
				// *********************************************************//
				if (emailModel.getObj() != null) {
					for (int i = 0; i < emailModel.getObj().size(); i++) {
						emailIds.add(emailModel.getObj().get(i).getEmail_id());
					}
				} else {
					emailIds = new ArrayList<String>(Arrays.asList(emailModel.getTestEmailIds().split(",")));
				}
				
				emailSent = emailSentRepository.save(new CasbEmailSent(examMasterRepository.getOne(1),
						emailIds.size(), emailModel.getEmailSubject(), emailModel.getEmailContent(),
						new Timestamp(System.currentTimeMillis()), emailModel.getSentType(), false, emailModel.getPageType(),
						emailModel.getMailReason(), emailModel.getMailServerHost(), emailModel.getMailServerPort(),
						emailModel.getStarttls(), emailModel.getSocketFactoryPort()));

			}

			session.setAttribute("totalCandidates", emailIds.size());
			session.setAttribute("totalAttachments", fileArray.size());
			
			if (emailIds.size() > 0) {
			
				if (fileArray.size() > 0) {
					emailSent.setIsAttachment(true);
					emailSent.setAttachmentCount(fileArray.size());
				}
		
				ArrayList<String> attachmentPath = new ArrayList<>();
				
				for (File file : fileArray) {
					attachmentPath.add(file.getAbsolutePath());
				}
				if (attachmentPath.size() > 0) {
					emailSent.setAttachmentPath(attachmentPath.toString());
				} else {
					emailSent.setAttachmentPath(null);
				}
				
				if (emailModel.getSentType() == 1 || emailModel.getSentType() == 2) {
					CasbEmailScheduleDetail scheduleDetail = new CasbEmailScheduleDetail(new Timestamp(System.currentTimeMillis()),
							emailIds.size(), emailType);

					CasbEmailStatus status = emailStatusRepository.findById(3L).get();
					scheduleDetail.setEmailScheduleStatus(status);
					emailScheduleDetailRepository.save(scheduleDetail);
					emailSent.getHelperScheduler(scheduleDetail);
				}
				if (emailModel.getSentType() == 2 || emailModel.getSentType() == 3) {
					for (int i = 0; i < emailModel.getEmailScheduleDatetime().size(); i++) {
						Timestamp later = emailModel.getEmailScheduleDatetime().get(i);

						CasbEmailScheduleDetail scheduleDetail = new CasbEmailScheduleDetail(later, emailIds.size(), emailType);
						CasbEmailStatus status = emailStatusRepository.findById(3L).get();
						scheduleDetail.setEmailScheduleStatus(status);
						emailScheduleDetailRepository.save(scheduleDetail);	
						emailSent.getHelperScheduler(scheduleDetail);
					}
				}

				try {
					emailSentRepository.save(emailSent);
					centerWiseSendEmail.info("Entry saved in email_sent table - " + emailModel.getEmailSubject());
				} catch (Exception e) {
					centerWiseSendEmail.error("Exception While saving entity in email_sent table ");
					e.printStackTrace();
				}
				
				centerWiseSendEmail.info("Saving attachments in CasbUPLOAD folder");
				try {
					fileUploadService.createDirectoryNew(emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId());
					for (File fileToSave : fileArray) {
						if (fileUploadService.saveFiles(fileToSave, emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId() + File.separator, reqType)) {
							CasbEmailAttachmentFileDetail fileDetail = new CasbEmailAttachmentFileDetail();
							fileDetail.setFilePath(emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId() + File.separator + fileToSave.getName());
							fileDetail.setEmailSent(emailSent);
							emailAttachmentFileDetailRepository.save(fileDetail);
							centerWiseSendEmail
									.info("Attachment saved in CasbUPLOAD/CasbEmailAttachments folder :" + fileToSave.getName());
						} else {
							centerWiseSendEmail.info("Unable to save attachment in CasbUPLOAD/CasbEmailAttachments folder :"
									+ fileToSave.getName());
						}
					}
				} catch (Exception e) {
					centerWiseSendEmail.info("Some error occurred while creating attachment file in email sent id folder in CasbUPLOAD folder");
					e.printStackTrace();
				}
				
				if(emailModel.getSentType() == 1 || emailModel.getSentType() == 2) {
					sendEmailAsync(emailIds, null, emailScheduleDetailRepository.findFirstSchedulerByEmailSentId(emailSent.getEmailSentId()));
				}else {
					generateReport(emailIds, null, emailSent.getEmailSentId(), emailScheduleDetailRepository.findFirstSchedulerByEmailSentId(emailSent.getEmailSentId()),
						emailModel.getEmailSubject(), emailModel.getEmailContent(), emailModel.getSentType(), reqType);
				}
				clearCache();
			}else {
				return "List of Email is Empty";
			}
			session.setAttribute("whichTabDataToShow", "bulkExcelEmail");
			session.setAttribute("disableEnableButtons", "enable");
			session.setAttribute("host", emailModel.getMailServerHost());
			return "success";
		}
		
		@Async
		public void sendEmailAsync(List<String> emailIdList, List<Long> appCredIdList, Long emailScheduleDeailId) {
			try {
				CasbEmailScheduleDetail emailScheduleDetail = emailScheduleDetailRepository.findById(emailScheduleDeailId).get();
				CasbEmailSent emailSent = emailSentRepository.findById(emailScheduleDetail.getEmailSent().getEmailSentId()).get();
				
				List<String> emailIdsFromFile = new ArrayList<>();
				List<Long> appCredIdsFromFile = new ArrayList<>();
				if(emailIdList != null) {
					emailIdsFromFile.addAll(emailIdList);
				}else {
					Long emailScheduleDetailId = emailScheduleDetailRepository.findFirstSchedulerByEmailSentId(emailSent.getEmailSentId());
					CasbEmailReportDetail reportGenerated = emailReportDetailRepository.findByEmailSentIdAndEmailScheduleId(emailSent.getEmailSentId(), emailScheduleDetailId);
					
					emailIdsFromFile.addAll(excelService.getExcel(Paths.get(reportGenerated.getFilePath()), true));
					
					if (appCredIdList == null) {
						List<String> list = excelService.getExcel(Paths.get(reportGenerated.getFilePath()), false);
						for (int i = 0; i < list.size(); i++)
							appCredIdsFromFile.add(Long.parseLong(list.get(i)));
					} else {
						appCredIdsFromFile.addAll(appCredIdList);
					}
				}
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						if(emailSent.getCenters() != null && emailSent.getSlots() != null) {
							
							sendEmails(emailSent.getBody(), emailSent.getSubject(), emailSent.getSendAdmitCardsRadio(), emailSent.getSlots(),
									emailSent.getCenters(), emailSent.getMailServerHost(), emailSent.getMailServerPort(),
									emailSent.getStarttls(), emailSent.getExam().getExamName(), emailSent.getReasonForEmail(),
									emailSent.getEmailBreathingTime(), emailSent.getEmailsPerBatch(), emailSent.getSocketFactoryPort(), emailSent.getEmailSentId(), emailScheduleDetail.getEmailScheduleDetailId());
						}else {
							cIMultiple(emailIdsFromFile, appCredIdsFromFile, emailSent.getCandidatesCount(),
								emailSent.getEmailSentId(), emailScheduleDetail.getEmailScheduleDetailId());
						}
					}
				});
				thread.start();
				
				}catch(Exception e) {
					e.printStackTrace();
				}		
		}
		
		@Async
		public void sendTestEmailsService(List<String> emailIdList, List<Long> appCredIdsFromFile, int candidatesCount, Long emailSentId, Long emailScheduleDeailId) {

			CasbEmailScheduleDetail emailScheduleDetail = emailScheduleDetailRepository.findById(emailScheduleDeailId).get();
			CasbEmailSent emailSent = emailSentRepository.findById(emailSentId).get();
			
			ArrayList<File> fileArray = fileUploadService.findFiles(emailSentId, emailSent.getReqType());
			emailScheduleDetailRepository.save(emailScheduleDetail);
			notSentEmailIds.clear();
			SentEmailIds.clear();
			
			emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(4L).get());
			if (emailIdList.size() > 0) {
				for (String emailId : emailIdList) {
					centerWiseSendEmail.info("inside email id list");
					if (!MailingService.sendMailCenterWise(emailSent.getMailServerHost(), emailSent.getMailServerPort(), emailSent.getStarttls(), emailSent.getSocketFactoryPort(),
							mailUserName,mailPassword, emailId, emailSent.getSubject(), emailSent.getBody(), fileArray, mailShouldBeSend,
							emailAttachmentDirFromPropertyFile+emailSentId+File.separator, null)) {
						centerWiseSendEmail.error("Mail Not Sent to : " + emailId + " due to Exception");
						notSentEmailIds.add(emailId);
					} else {
						centerWiseSendEmail.info("Mail sent successfully to : " + emailId);
						SentEmailIds.add(emailId);
					}
				}
				emailScheduleDetail.setEmailScheduleEndDate(new Timestamp(System.currentTimeMillis()));
				emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(1L).get());
				emailSentRepository.save(emailSent);
				emailScheduleDetailRepository.save(emailScheduleDetail);

				generateReport(emailIdList, appCredIdsFromFile, emailSentId, emailScheduleDeailId,
						emailSent.getSubject(), emailSent.getBody(), emailSent.getEmailSentType(), emailSent.getReqType());
				
//				uploadEmailInTable(new ArrayList<>(emailIdList));
//				for (String id : emailIdList) {
//					System.out.println("\n\n id : " + id);
//					try {
//						writeHelpContent(emailSentId, id, SentEmailIds.contains(id), fileArray);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
				clearCache();
			} else {
				clearCache();
			}
		}
		
		@Async
		public void cIMultiple(List<String> emailIdList, List<Long> appCredIdsFromFile, int candidatesCount, Long emailSentId, Long emailScheduleDeailId) {
			CasbEmailScheduleDetail emailScheduleDetail = emailScheduleDetailRepository.findById(emailScheduleDeailId).get();
			CasbEmailSent emailSent = emailSentRepository.findById(emailSentId).get();
			
			ArrayList<File> fileArray = fileUploadService.findFiles(emailSentId, emailSent.getReqType());
			emailScheduleDetailRepository.save(emailScheduleDetail);
			notSentEmailIds.clear();
			SentEmailIds.clear();
			
			emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(4L).get());

			if (emailIdList.size() > 0) {
				ArrayList<String> oneThousandSubset = new ArrayList<String>();
				ArrayList<String> oneHundredSubset = new ArrayList<String>();
				int count = 1;
				int outerExitCounter = 0;
				for (String emailIdcounter : emailIdList) {
					outerExitCounter++;
					oneThousandSubset.add(emailIdcounter);
					count++;
					if ((count > 500) || (outerExitCounter >= emailIdList.size())) {
						centerWiseSendEmail.info("count : " + count + " outerExitCounter : " + outerExitCounter);
						count = 1;
						int oneThousandSubSetOfList = oneThousandSubset.size();
						centerWiseSendEmail.info("oneThousandSubSetOfMapSize : " + oneThousandSubSetOfList);
						int mailThreadSize = (int) (Math.ceil((double) oneThousandSubSetOfList / 50));
						centerWiseSendEmail.info("mailThreadSize : " + mailThreadSize);
						MailThreadExcel[] mailThreadArray = new MailThreadExcel[mailThreadSize];
						centerWiseSendEmail.info("mailThreadArray.length : " + mailThreadArray.length);

						int innerCount = 1;
						int arrayIndex = 0;
						int exitCounter = 0;
						for (String string1 : oneThousandSubset) {
							exitCounter++;
							oneHundredSubset.add(string1);
							innerCount++;
							if ((innerCount > 50) || (exitCounter >= oneThousandSubSetOfList)) {
								centerWiseSendEmail.info("innerCount : " + innerCount + " exitCounter : " + exitCounter);
								innerCount = 1;
								centerWiseSendEmail.info("arrayIndex : " + arrayIndex);
								centerWiseSendEmail.info("oneHundredSubSetOfMap.size() : " + oneHundredSubset.size());

								//Host : mailgw-dr.noida.cdac.in | Port : 25 or 587
								//Host : 10.226.70.121 | Port : 25
								//Host : 10.226.70.121
								mailThreadArray[arrayIndex] = new MailThreadExcel("smtp.cdac.in",
										"587", emailSent.getStarttls(),
										emailSent.getSocketFactoryPort(), mailUserName, mailPassword,
										emailSent.getSubject(), emailSent.getBody(),
										oneHundredSubset, fileArray, emailAttachmentDirFromPropertyFile+emailSentId+File.separator,
										emailSent.getReasonForEmail());
								mailThreadArray[arrayIndex].setName("Thread-" + arrayIndex);
								oneHundredSubset.clear();
								arrayIndex++;
							}
						}
						for (int i = 0; i < mailThreadArray.length; i++) {
							mailThreadArray[i].start();
						}

						for (int i = 0; i < mailThreadArray.length; i++) {
							try {
								mailThreadArray[i].join();
								SentEmailIds.addAll(mailThreadArray[i].getMailSentSet());
								System.out.println("Last Email Id : "+SentEmailIds.get(SentEmailIds.size()-1) + "\nCount : "+outerExitCounter);
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							}
						}

						if (outerExitCounter < emailIdList.size()) {
							try {
								centerWiseSendEmail.info("Waiting for 3 minute after 500 mails");
								Thread.sleep(3000 * 60);
								centerWiseSendEmail.info("waiting completed");
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							}
						}
						oneHundredSubset.clear();
						oneThousandSubset.clear();
					}
				}
				
				emailScheduleDetail.setEmailScheduleEndDate(new Timestamp(System.currentTimeMillis()));
				emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(1L).get());
				emailSentRepository.save(emailSent);
				emailScheduleDetailRepository.save(emailScheduleDetail);

				generateReport(emailIdList, appCredIdsFromFile, emailSentId, emailScheduleDeailId,
						emailSent.getSubject(), emailSent.getBody(), emailSent.getEmailSentType(), emailSent.getReqType());
				
//				uploadEmailInTable(new ArrayList<>(emailIdList));
//				for (String id : emailIdList) {
//					try {
//						writeHelpContent(emailSentId, id, SentEmailIds.contains(id), fileArray);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
			
			}
		}
		
		@Override
		public void sendEmails(String emailContent, String emailSubject, String sendAdmitCardsRadio, String slots,
				String centers, String mailServerHost, String mailServerPort, Boolean starttls, String exam,
				String mailReason, String emailBreathingTime, String emailsPerBatch, String socketFactoryPort,
				Long emailSentId, Long emailScheduleDeailId) {
			
			CasbEmailScheduleDetail emailScheduleDetail = emailScheduleDetailRepository.findById(emailScheduleDeailId).get();
			CasbEmailSent emailSent = emailSentRepository.findById(emailSentId).get();
			
			ArrayList<File> fileArray = fileUploadService.findFiles("casb");
			emailScheduleDetailRepository.save(emailScheduleDetail);
			notSentEmailIds.clear();
			SentEmailIds.clear();
			
			emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(4L).get());

			String[] slotsArray = slots.split(",");

			List<String> slotsList = Arrays.asList(slotsArray);
			ArrayList<String> slotsArrayList = new ArrayList<String>(slotsList);

			String[] centersArray = centers.split(",");

			List<String> centersList = Arrays.asList(centersArray);
			ArrayList<String> centersArrayList = new ArrayList<String>(centersList);

			Map<String, String> emailIdsAndHallTicketNumbersToReturn = new LinkedHashMap<>();

			List<Object[]> allEmailIdsAndHallTicketNumbers = new ArrayList<Object[]>();

			if (exam.equals("regularExam")) {
				allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(slotsArrayList, centersArrayList);
			} else if (exam.equals("reExam")) {
				allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(slotsArrayList,
								centersArrayList);
			} else {
				clearCache();
			}
			
			List<String> emailIdList = new ArrayList<String>();

			for (Object[] emailIdAndHallTicketNumber : allEmailIdsAndHallTicketNumbers) {
				String emailId = (String) emailIdAndHallTicketNumber[0];
				String hallTicketNumber = (String) emailIdAndHallTicketNumber[1];
				emailIdsAndHallTicketNumbersToReturn.put(emailId, hallTicketNumber);
				emailIdList.add(emailId);
			}
			for (Map.Entry<String, String> emailIdAndHallTicketNumber : emailIdsAndHallTicketNumbersToReturn.entrySet()) {
				centerWiseSendEmail.info(emailIdAndHallTicketNumber.getKey() + "/" + emailIdAndHallTicketNumber.getValue());
			}
			centerWiseSendEmail.info("allEmailIdsAndHallTicketNumbers.size() : " + allEmailIdsAndHallTicketNumbers.size());
			Map<String, String> oneThousandSubSetOfMap = new LinkedHashMap<>();
			Map<String, String> oneHundredSubSetOfMap = new LinkedHashMap<>();
			int count = 1;
			int outerExitCounter = 0;
					
			if (emailIdsAndHallTicketNumbersToReturn.size() > 0) {
				for (Map.Entry<String, String> emailIdAndHallTicketNumber : emailIdsAndHallTicketNumbersToReturn
						.entrySet()) {
					outerExitCounter++;
					oneThousandSubSetOfMap.put(emailIdAndHallTicketNumber.getKey(), emailIdAndHallTicketNumber.getValue());
					count++;
					if ((count > Integer.valueOf(emailsPerBatch))
							|| (outerExitCounter >= allEmailIdsAndHallTicketNumbers.size())) {
						centerWiseSendEmail.info("count : " + count + " outerExitCounter : " + outerExitCounter);
						count = 1;
						int oneThousandSubSetOfMapSize = oneThousandSubSetOfMap.size();
						centerWiseSendEmail.info("oneThousandSubSetOfMapSize : " + oneThousandSubSetOfMapSize);
						int mailThreadSize = (int) (Math
								.ceil((double) oneThousandSubSetOfMapSize / (Integer.valueOf(emailsPerBatch) / 10)));
						centerWiseSendEmail.info("mailThreadSize : " + mailThreadSize);
						CasbMailThread[] mailThreadArray = new CasbMailThread[mailThreadSize];
						centerWiseSendEmail.info("mailThreadArray.length : " + mailThreadArray.length);

						int innerCount = 1;
						int arrayIndex = 0;
						int exitCounter = 0;
						for (Map.Entry<String, String> oneThousandSubSetOfMapEntry : oneThousandSubSetOfMap.entrySet()) {
							exitCounter++;
							oneHundredSubSetOfMap.put(oneThousandSubSetOfMapEntry.getKey(),
									oneThousandSubSetOfMapEntry.getValue());
							innerCount++;
							if ((innerCount > (Integer.valueOf(emailsPerBatch) / 10))
									|| (exitCounter >= oneThousandSubSetOfMapSize)) {
								centerWiseSendEmail.info("innerCount : " + innerCount + " exitCounter : " + exitCounter);
								innerCount = 1;
								centerWiseSendEmail.info("arrayIndex : " + arrayIndex);
								centerWiseSendEmail.info("oneHundredSubSetOfMap.size() : " + oneHundredSubSetOfMap.size());
								mailThreadArray[arrayIndex] = new CasbMailThread(mailServerHost, mailServerPort, starttls,
										socketFactoryPort, mailUserName, mailPassword, emailSubject, emailContent,
										oneHundredSubSetOfMap, admitCardFilePathFromPropertyFile, fileArray,
										mailShouldBeSend, applicantHallTicketService, sendAdmitCardsRadio,
										emailAttachmentDirFromPropertyFile, mailReason, examMasterRepository,
										emailSentRepository);
								mailThreadArray[arrayIndex].setName("Thread-" + arrayIndex);
								oneHundredSubSetOfMap.clear();
								arrayIndex++;
							}
						}
						centerWiseSendEmail.info("outside oneThousand for loop");
						oneThousandSubSetOfMap.clear();

						for (int i = 0; i < mailThreadArray.length; i++) {
							mailThreadArray[i].start();
						}

						for (int i = 0; i < mailThreadArray.length; i++) {
							try {
								mailThreadArray[i].join();
								SentEmailIds.addAll(mailThreadArray[i].getMailSentSet());
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendTestEmails-join");
								e.printStackTrace();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside InterruptedException of sendTestEmails-join");
								e.printStackTrace();
							}
						}

						if (outerExitCounter < allEmailIdsAndHallTicketNumbers.size()) {
							try {
								centerWiseSendEmail.info("Waiting for 3 minute after 500 mails");
								Thread.sleep(Integer.valueOf(emailBreathingTime) * 1000 * 60);
								centerWiseSendEmail.info("waiting completed");
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendTestEmails-sleep");
								e.printStackTrace();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside InterruptedException of sendTestEmails-sleep");
								e.printStackTrace();
							}
						}
					}
				}
				centerWiseSendEmail.info("E-MAIL sending completed");
				
				emailScheduleDetail.setEmailScheduleEndDate(new Timestamp(System.currentTimeMillis()));
				emailScheduleDetail.setEmailScheduleStatus(emailStatusRepository.findById(1L).get());
				emailSentRepository.save(emailSent);
				emailScheduleDetailRepository.save(emailScheduleDetail);

				generateReport(emailIdList, null, emailSentId, emailScheduleDeailId, emailSent.getSubject(),
						emailSent.getBody(), emailSent.getEmailSentType(), emailSent.getReqType());

//				uploadEmailInTable(new ArrayList<>(emailIdList));
//				for (String id : emailIdList) {
//					try {
//						writeHelpContent(emailSentId, id, SentEmailIds.contains(id), fileArray);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
				clearCache();
			} else {
				clearCache();
			}
		}
		
		public Boolean clearCache() {
			ArrayList<File> fileArray = fileUploadService.findFiles("casb");
			try {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
			} catch (Exception e) {
				centerWiseSendEmail.info("Unable to delete temporary Attachments in ICGUPLOAD folder");
				e.printStackTrace();
				return false;
			}
			centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
			return true;
		}

		public ResponseEntity<?> cIMultiple(TestEmailBulkModel testEmailBulkModel, HttpServletRequest request) {
			ArrayList<File> fileArray = fileUploadService.findFiles("casb");
			// validations //
			if (testEmailBulkModel.getMailServerHost().isEmpty() || testEmailBulkModel.getMailServerHost().length() > 20) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server host is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail server host is not entered properly or is null", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getMailServerPort().isEmpty() || testEmailBulkModel.getMailServerPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server port is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail server port is not entered properly or is null", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getSocketFactoryPort().isEmpty()
					|| testEmailBulkModel.getSocketFactoryPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail socket factory port is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail socket factory port is not entered properly or is null",
						HttpStatus.NOT_FOUND);
			}
			if (Jsoup.parse(testEmailBulkModel.getEmailContent()).text().isEmpty()) {
				clearCache();
				return new ResponseEntity<>("Please enter email content", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getEmailSubject().isEmpty()) {
				clearCache();
				return new ResponseEntity<>("Please enter email subject", HttpStatus.NOT_FOUND);
			}

			HttpSession session = request.getSession();
			session.setAttribute("disableEnableButtons", "disable");
			notSentEmailIds.clear();
			SentEmailIds.clear();
			ArrayList<String> emailIds = new ArrayList<>();
			testEmailBulkModel.getObj().forEach(email -> emailIds.add(email.getEmail_id()));
			session.setAttribute("totalCandidates", emailIds.size());
			if (emailIds.size() > 0) {
				ArrayList<String> oneThousandSubset = new ArrayList<String>();
				ArrayList<String> oneHundredSubset = new ArrayList<String>();
				int count = 1;
				int outerExitCounter = 0;
				for (String emailIdcounter : emailIds) {
					outerExitCounter++;
					oneThousandSubset.add(emailIdcounter);
					count++;
					if ((count > 100) || (outerExitCounter >= emailIds.size())) {
						centerWiseSendEmail.info("count : " + count + " outerExitCounter : " + outerExitCounter);
						count = 1;
						int oneThousandSubSetOfList = oneThousandSubset.size();
						centerWiseSendEmail.info("oneThousandSubSetOfMapSize : " + oneThousandSubSetOfList);
						int mailThreadSize = (int) (Math.ceil((double) oneThousandSubSetOfList / 10));
						centerWiseSendEmail.info("mailThreadSize : " + mailThreadSize);
						MailThreadExcel[] mailThreadArray = new MailThreadExcel[mailThreadSize];
						centerWiseSendEmail.info("mailThreadArray.length : " + mailThreadArray.length);

						int innerCount = 1;
						int arrayIndex = 0;
						int exitCounter = 0;
						for (String string1 : oneThousandSubset) {
							exitCounter++;
							oneHundredSubset.add(string1);
							innerCount++;
							if ((innerCount > 10) || (exitCounter >= oneThousandSubSetOfList)) {
								centerWiseSendEmail.info("innerCount : " + innerCount + " exitCounter : " + exitCounter);
								innerCount = 1;
								centerWiseSendEmail.info("arrayIndex : " + arrayIndex);
								centerWiseSendEmail.info("oneHundredSubSetOfMap.size() : " + oneHundredSubset.size());
								mailThreadArray[arrayIndex] = new MailThreadExcel(testEmailBulkModel.getMailServerHost(),
										testEmailBulkModel.getMailServerPort(), testEmailBulkModel.getStarttls(),
										testEmailBulkModel.getSocketFactoryPort(), mailUserName, mailPassword,
										testEmailBulkModel.getEmailSubject(), testEmailBulkModel.getEmailContent(),
										oneHundredSubset, fileArray, emailAttachmentDirFromPropertyFile+1+File.separator,
										testEmailBulkModel.getMailReason());
								mailThreadArray[arrayIndex].setName("Thread-" + arrayIndex);
								oneHundredSubset.clear();
								arrayIndex++;
							}
						}
						centerWiseSendEmail.info("outside oneThousand for loop");
						for (int i = 0; i < mailThreadArray.length; i++) {
							mailThreadArray[i].start();
						}

						for (int i = 0; i < mailThreadArray.length; i++) {
							try {
								mailThreadArray[i].join();
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							}
						}

						if (outerExitCounter < emailIds.size()) {
							try {
								centerWiseSendEmail.info("Waiting for 1 minute after 100 mails");
								Thread.sleep(1000 * 60);
								centerWiseSendEmail.info("waiting completed");
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							}
						}
					}
				}

				session.setAttribute("totalAttachments", fileArray.size());

				CasbEmailSent emailSent = new CasbEmailSent();
				emailSent.setExam(examMasterRepository.getOne(1));
				emailSent.setCandidatesCount(emailIds.size());
				emailSent.setSubject(testEmailBulkModel.getEmailSubject());
				emailSent.setBody(testEmailBulkModel.getEmailContent());
				emailSent.setReasonForEmail(testEmailBulkModel.getMailReason());
				if (fileArray.size() > 0) {
					emailSent.setIsAttachment(true);
					emailSent.setAttachmentCount(fileArray.size());
				}

				ArrayList<String> attachmentPath = new ArrayList<>();
				for (File file : fileArray) {
					attachmentPath.add(file.getAbsolutePath());
				}
				if (attachmentPath.size() > 0) {
					emailSent.setAttachmentPath(attachmentPath.toString());
				} else {
					emailSent.setAttachmentPath(null);
				}
				emailSent.setRecordTracking(new Timestamp(System.currentTimeMillis()));

				// updating entry in email_sent table
				centerWiseSendEmail.info("Saving entry in email_sent table - " + testEmailBulkModel.getEmailSubject());

				try {
					emailSentRepository.save(emailSent);
					centerWiseSendEmail.info("Entry saved in email_sent table - " + testEmailBulkModel.getEmailSubject());
				} catch (Exception e) {
					centerWiseSendEmail.error("Exception While saving entity in email_sent table");
					e.printStackTrace();
				}

				centerWiseSendEmail.info("Saving attachments in ICGUPLOAD folder");
				try {
					fileUploadService.createDirectoryNew(emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId());
					for (File fileToSave : fileArray) {
						fileUploadService.saveFiles(fileToSave,
								emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId() + "\\", "casb");
					}
					centerWiseSendEmail.info("Attachments saved in ICGUPLOAD folder");

				} catch (Exception e) {
					centerWiseSendEmail.info(
							"Some error occurred while creating attachment file in email sent id folder in ICGUPLOAD folder");
					e.printStackTrace();
				}
				clearCache();
			} else {
				clearCache();
			}
			session.setAttribute("whichTabDataToShow", "bulkExcelEmail");
			session.setAttribute("disableEnableButtons", "enable");
			session.setAttribute("host", testEmailBulkModel.getMailServerHost());
			return new ResponseEntity<>(true, HttpStatus.OK);
		}

		@Override
		public ResponseEntity<?> sendMultipleEmailOfRejected(TestEmailBulkModel testEmailBulkModel,
				HttpServletRequest request) {
			ArrayList<File> fileArray = fileUploadService.findFiles("casb");

			System.out.println(testEmailBulkModel.toString());
//			testEmailBulkModel.getObj().forEach(ee->System.out.println(ee.getEmail_id()));

			if (testEmailBulkModel.getMailServerHost().isEmpty() || testEmailBulkModel.getMailServerHost().length() > 20) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server host is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail server host is not entered properly or is null", HttpStatus.NOT_FOUND);
			}

			if (testEmailBulkModel.getMailServerPort().isEmpty() || testEmailBulkModel.getMailServerPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail server port is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail server port is not entered properly or is null", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getSocketFactoryPort().isEmpty()
					|| testEmailBulkModel.getSocketFactoryPort().length() > 4) {
				for (File files : fileArray) {
					fileUploadService.deleteFile(emailAttachmentDirFromPropertyFile + files.getName());
				}
				centerWiseSendEmail.info("Temporary Attachments deleted in ICGUPLOAD folder");
				centerWiseSendEmail.error("mail socket factory port is not entered properly or is null");
				clearCache();
				return new ResponseEntity<>("mail socket factory port is not entered properly or is null",
						HttpStatus.NOT_FOUND);
			}
			if (Jsoup.parse(testEmailBulkModel.getEmailContent()).text().isEmpty()) {
				clearCache();
				return new ResponseEntity<>("Please enter email content", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getEmailSubject().isEmpty()) {
				clearCache();
				return new ResponseEntity<>("Please enter email subject", HttpStatus.NOT_FOUND);
			}
			if (testEmailBulkModel.getPostRadio()==null) {
				clearCache();
				return new ResponseEntity<>("Please Check Radio Button Is Selected Or Not", HttpStatus.NOT_FOUND);
			}

			
			HttpSession session = request.getSession();
			session.setAttribute("disableEnableButtons", "disable");
			notSentEmailIds.clear();
			SentEmailIds.clear();
			List<EmailProjection> allEmailIdsAndHallTicketNumbers;
			if (testEmailBulkModel.getPostRadio() == 1) {
				allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOfRejectedCandidate();
			} else if (testEmailBulkModel.getPostRadio() == 2) {
				allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateMultiPost();

			} else if (testEmailBulkModel.getPostRadio() == 3) {
				allEmailIdsAndHallTicketNumbers = applicantHallTicketService
						.getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateAllPost();
			} else {
				clearCache();
				return new ResponseEntity<>("Please Select Post Radio Check Box", HttpStatus.NOT_FOUND);
			}

//			HashMap<String, String> allEmailWithReason = new LinkedHashMap<>();
			HashMap<String, ArrayList<String>> allEmailWithReason = new LinkedHashMap<>();

			if (!allEmailIdsAndHallTicketNumbers.isEmpty() && allEmailIdsAndHallTicketNumbers.get(0).getEmailId() != null) {
//				System.out.println("in if");
//				System.out.println(allEmailIdsAndHallTicketNumbers.get().get(0));
//				System.out.println("The Size Is "+allEmailIdsAndHallTicketNumbers.get().size());
				for (int i = 0; i < allEmailIdsAndHallTicketNumbers.size(); i++) {
//					System.out.println("put in map" + allEmailIdsAndHallTicketNumbers.get(i).getEmailId());
//					System.out.println("put in map" + allEmailIdsAndHallTicketNumbers.get(i).getRejectedReason());
//					System.out.println("put in map" + allEmailIdsAndHallTicketNumbers.get(i).getHindiReason());
//					allEmailWithReason.put(allEmailIdsAndHallTicketNumbers.get(i).getEmailId(),
//							allEmailIdsAndHallTicketNumbers.get(i).getRejectedReason());
					allEmailWithReason.put(allEmailIdsAndHallTicketNumbers.get(i).getEmailId(), new ArrayList<String>());
					allEmailWithReason.get(allEmailIdsAndHallTicketNumbers.get(i).getEmailId())
							.add(allEmailIdsAndHallTicketNumbers.get(i).getRejectedReason());
					allEmailWithReason.get(allEmailIdsAndHallTicketNumbers.get(i).getEmailId())
							.add(allEmailIdsAndHallTicketNumbers.get(i).getHindiReason());
				}
			} else {
				return new ResponseEntity<>("No Rejected Candidates Is There", HttpStatus.NOT_FOUND);
			}

//			allEmailWithReason.entrySet().stream().forEach(e -> System.out.println(e));

			session.setAttribute("totalCandidates", allEmailWithReason.size());

//			ArrayList<String> emailIds = new ArrayList<>();
//			testEmailBulkModel.getObj().forEach(email -> emailIds.add(email.getEmail_id()));
//			session.setAttribute("totalCandidates", allEmailIdsAndHallTicketNumbers.size());
			System.out.println("The Size Is " + allEmailWithReason.size());

//			for (Map.Entry<String, String> email : allEmailWithReason.entrySet()) {
//				System.out.println("email " + email.getKey());
//				System.out.println("Reason Is " + email.getValue());
//			}

			if (!allEmailWithReason.isEmpty()) {
//				ArrayList<String> oneThousandSubset = new ArrayList<>();
//				ArrayList<String> oneHundredSubset = new ArrayList<>();
				HashMap<String, ArrayList<String>> oneThousandSubset = new LinkedHashMap<>();
				HashMap<String, ArrayList<String>> oneHundredSubset = new LinkedHashMap<>();
				int count = 1;
				int outerExitCounter = 0;
				for (Entry<String, ArrayList<String>> emailIdcounter : allEmailWithReason.entrySet()) {
					outerExitCounter++;
					oneThousandSubset.put(emailIdcounter.getKey(), emailIdcounter.getValue());
					count++;
					if ((count > 100) || (outerExitCounter >= allEmailWithReason.size())) {
						centerWiseSendEmail.info("count : " + count + " outerExitCounter : " + outerExitCounter);
						count = 1;
						int oneThousandSubSetOfList = oneThousandSubset.size();
						centerWiseSendEmail.info("oneThousandSubSetOfMapSize : " + oneThousandSubSetOfList);
						int mailThreadSize = (int) (Math.ceil((double) oneThousandSubSetOfList / 10));
						centerWiseSendEmail.info("mailThreadSize : " + mailThreadSize);
//						MailThreadExcel[] mailThreadArray = new MailThreadExcel[mailThreadSize];
						MailThreaRejected[] mailThreadArray = new MailThreaRejected[mailThreadSize];
						centerWiseSendEmail.info("mailThreadArray.length : " + mailThreadArray.length);

						int innerCount = 1;
						int arrayIndex = 0;
						int exitCounter = 0;
						for (Entry<String, ArrayList<String>> string1 : oneThousandSubset.entrySet()) {
							exitCounter++;
							oneHundredSubset.put(string1.getKey(), string1.getValue());
							innerCount++;
							if ((innerCount > 10) || (exitCounter >= oneThousandSubSetOfList)) {
								centerWiseSendEmail.info("innerCount : " + innerCount + " exitCounter : " + exitCounter);
								innerCount = 1;
								centerWiseSendEmail.info("arrayIndex : " + arrayIndex);
								centerWiseSendEmail.info("oneHundredSubSetOfMap.size() : " + oneHundredSubset.size());
								mailThreadArray[arrayIndex] = new MailThreaRejected(testEmailBulkModel.getMailServerHost(),
										testEmailBulkModel.getMailServerPort(), testEmailBulkModel.getStarttls(),
										testEmailBulkModel.getSocketFactoryPort(), mailUserName, mailPassword,
										testEmailBulkModel.getEmailSubject(), testEmailBulkModel.getEmailContent(),
										oneHundredSubset, fileArray, emailAttachmentDirFromPropertyFile, session,
										testEmailBulkModel.getMailReason(), applicantHallTicketService);
								mailThreadArray[arrayIndex].setName("Thread-" + arrayIndex);
								oneHundredSubset.clear();
								arrayIndex++;
							}
						}
						centerWiseSendEmail.info("outside oneThousand for loop");
						for (int i = 0; i < mailThreadArray.length; i++) {
							mailThreadArray[i].start();
						}
						for (int i = 0; i < mailThreadArray.length; i++) {
							try {
								mailThreadArray[i].join();
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-join");
								e.printStackTrace();
								clearCache();
							}
						}
						if (outerExitCounter < allEmailWithReason.size()) {
							try {
								centerWiseSendEmail.info("Waiting for 1 minute after 100 mails");
								Thread.sleep(1000 * 60);
								centerWiseSendEmail.info("waiting completed");
							} catch (InterruptedException e) {
								centerWiseSendEmail.info("inside InterruptedException of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							} catch (Exception e) {
								centerWiseSendEmail.info("inside Exception of sendBulkEmailsFromExcel-sleep");
								e.printStackTrace();
								clearCache();
							}
						}
					}
				}
				session.setAttribute("totalAttachments", fileArray.size());
				CasbEmailSent emailSent = new CasbEmailSent();
				emailSent.setExam(examMasterRepository.getOne(1));
				emailSent.setCandidatesCount(allEmailWithReason.size());
				emailSent.setSubject(testEmailBulkModel.getEmailSubject());
				emailSent.setBody(testEmailBulkModel.getEmailContent());
				emailSent.setReasonForEmail(testEmailBulkModel.getMailReason());
				if (fileArray.size() > 0) {
					emailSent.setIsAttachment(true);
					emailSent.setAttachmentCount(fileArray.size());
				}
				ArrayList<String> attachmentPath = new ArrayList<>();
				for (File file : fileArray) {
					attachmentPath.add(file.getAbsolutePath());
				}
				if (attachmentPath.size() > 0) {
					emailSent.setAttachmentPath(attachmentPath.toString());
				} else {
					emailSent.setAttachmentPath(null);
				}
				emailSent.setRecordTracking(new Timestamp(System.currentTimeMillis()));

				// updating entry in email_sent table
				centerWiseSendEmail.info("Saving entry in email_sent table - " + testEmailBulkModel.getEmailSubject());

				try {
					emailSentRepository.save(emailSent);
					centerWiseSendEmail.info("Entry saved in email_sent table - " + testEmailBulkModel.getEmailSubject());
				} catch (Exception e) {
					centerWiseSendEmail.error("Exception While saving entity in email_sent table");
					e.printStackTrace();
				}
				centerWiseSendEmail.info("Saving attachments in ICGUPLOAD folder");
				try {
					fileUploadService.createDirectoryNew(emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId());
					for (File fileToSave : fileArray) {
						fileUploadService.saveFiles(fileToSave,
								emailAttachmentDirFromPropertyFile + emailSent.getEmailSentId() + "\\", "casb");
					}
					centerWiseSendEmail.info("Attachments saved in ICGUPLOAD folder");

				} catch (Exception e) {
					centerWiseSendEmail.info(
							"Some error occurred while creating attachment file in email sent id folder in ICGUPLOAD folder");
					e.printStackTrace();
				}
				clearCache();
			} else {
				clearCache();
				return new ResponseEntity<>("No Rejected Candidates Found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}

		public String writeHelpContent(Long emailSentId, String emailId, Boolean isSentSuccessfully,
				ArrayList<File> fileArray) throws IOException {
			CasbEmailSent casbEmailSent = emailSentRepository.findById(emailSentId).get();
			acknowledgementOfSentEmail
					.info("ICGMailService : writeHelpContent : Candidate Count : " + casbEmailSent.getCandidatesCount());
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
			String reportFolderPath = emailTextReportDetailsRepository.findReportPathByEmailId(emailId);

			File file = new File(reportFolderPath + "\\" + emailSentId+"_"+casbEmailSent.getReasonForEmail() + "-"
					+ java.time.LocalDateTime.now().format(format)+".txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

			bw.write("Exam : " + casbEmailSent.getReqType() + "\n");
			bw.write("Candidate Count : " + casbEmailSent.getCandidatesCount() + "\n");
			for (CasbEmailScheduleDetail casbEmailScheduleDetail : casbEmailSent.getEmailScheduleDetails()) {
				bw.write("Date And Time : " + casbEmailScheduleDetail.getEmailScheduleStartDate() + "\n");
			}
			bw.newLine();
			bw.write("Email Id : " + emailId + "\n");
			bw.write("Email Sent Status : " + isSentSuccessfully + "\n");
			bw.write("Email Reason : " + casbEmailSent.getReasonForEmail() + "\n\n");
			bw.write("Email Subject : \n" + casbEmailSent.getSubject() + "\n");
			bw.write("Email Body : \n" + Jsoup.parse(casbEmailSent.getBody()).wholeText() + "\n");
			bw.close();
//			File uploadDir = new File(path2 + "\\upload");
//			if (!uploadDir.exists()) {
//				uploadDir.mkdirs();
//				for (File uploadDoc : fileArray) {
//					System.out.println(uploadDoc.getName());
//					File newUploadDoc = new File(path2 + "\\upload\\" + uploadDoc.getName());
//					FileUtils.copyFile(uploadDoc, newUploadDoc);
//				}
//			}
			return "Successfully write into help page file";
		}

		@Override
		public List<ReportInfo> searchListOfPathByEmailId(String emailId) {
			String folderPath = null;
			try {
				folderPath = emailTextReportDetailsRepository.findReportPathByEmailId(emailId);
				File folder = new File(folderPath);
				System.out.println("folder : "+folder.getAbsolutePath());
				List<ReportInfo> reportInfo = new ArrayList<>();
				for (final File fileEntry : folder.listFiles()) {
					if (fileEntry.isFile()) {
						reportInfo.add(new ReportInfo(fileEntry.getName(), fileEntry.getAbsolutePath()));
					}
				}
				return reportInfo;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return null;
		}

		@Override
		public List<ReportInfo> searchListOfPathInFolder(String folderPath) {
			String str = folderPath.substring(folderPath.lastIndexOf('\\')+1);
			Long emailSentId = Long.parseLong(str.substring(0, str.indexOf("_")));
			System.out.println("emailAttachmentDirFromPropertyFile : "+emailAttachmentDirFromPropertyFile);
			File folder = new File(emailAttachmentDirFromPropertyFile+emailSentId);
			List<ReportInfo> reportInfo = new ArrayList<>();
			for (final File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {
					reportInfo.add(new ReportInfo(fileEntry.getName(), fileEntry.getAbsolutePath()));
				}
			}
			return reportInfo;
		}

		@Override
		public String getPath(String emailId, String filePath) {
			if(!emailId.equals("null") && !emailId.equalsIgnoreCase("undefined") && !emailId.equalsIgnoreCase("")) {
				System.out.println(emailTextReportDetailsRepository.getEmailTextReportDetailByEmailId(emailId));
				filePath =  casbTextReportDownloadDir + 1 + "\\" + filePath;
			}else {
				filePath =  emailAttachmentDirFromPropertyFile + filePath.substring(0, filePath.indexOf("-")) + "/" + filePath.substring(filePath.indexOf("-")+1);

			}
			System.out.println("File Path : "+filePath);
			return filePath;
		}


}
