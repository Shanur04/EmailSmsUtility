package gov.cdac.Threads;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import gov.cdac.casbRepository.EmailSentRepository;
import gov.cdac.casbRepository.ExamMasterRepository;
import gov.cdac.services.ApplicantHallTicketService;
import gov.cdac.services.MailServiceImpl;

public class CasbMailThread extends Thread{
	private String mailUserName = null;
	private String mailPassword = null;
	private String mailSubject = null;
	private String mailContent = null;
	private ArrayList<File> fileArrayList = null;
	private String tempFileDir = null;
	private String sendAdmitCardsRadio = null;
	private String tempFileDirFromPropertyFile = null;
	
	private String mailServerHost = null;
	
	private String mailServerPort = null;
	private Boolean starttls = false;
	private String socketFactoryPort = null;
	private String mailReason = null;
	private Set<String> mailNotSentSet = new HashSet<>();
	private Set<String> mailSentSet = new HashSet<>();
	
	private ApplicantHallTicketService applicantHallTicketService = null;	
	private ExamMasterRepository examMasterRepository= null;
	private EmailSentRepository emailSentRepository = null;
	
	
	public CasbMailThread() {
		super();
	}
	
	public ExamMasterRepository getExamMasterRepository() {
		return examMasterRepository;
	}

	public void setExamMasterRepository(ExamMasterRepository examMasterRepository) {
		this.examMasterRepository = examMasterRepository;
	}


	public EmailSentRepository getEmailSentRepository() {
		return emailSentRepository;
	}

	public void setEmailSentRepository(EmailSentRepository emailSentRepository) {
		this.emailSentRepository = emailSentRepository;
	}
	
	public String getMailReason() {
		return mailReason;
	}


	public void setMailReason(String mailReason) {
		this.mailReason = mailReason;
	}
	

	public String getMailServerHost() {
		return mailServerHost;
	}


	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}


	public String getMailServerPort() {
		return mailServerPort;
	}


	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public ApplicantHallTicketService getApplicantHallTicketService() {
		return applicantHallTicketService;
	}


	public void setApplicantHallTicketService(ApplicantHallTicketService applicantHallTicketService) {
		this.applicantHallTicketService = applicantHallTicketService;
	}
	
	public Boolean getStarttls() {
		return starttls;
	}


	public void setStarttls(Boolean starttls) {
		this.starttls = starttls;
	}


	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}


	public void setSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}
	
	public String getTempFileDirFromPropertyFile() {
		return tempFileDirFromPropertyFile;
	}

	public void setTempFileDirFromPropertyFile(String tempFileDirFromPropertyFile) {
		this.tempFileDirFromPropertyFile = tempFileDirFromPropertyFile;
	}


	public String getSendAdmitCardsRadio() {
		return sendAdmitCardsRadio;
	}


	public void setSendAdmitCardsRadio(String sendAdmitCardsRadio) {
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
	}


	public String getTempFileDir() {
		return tempFileDir;
	}


	public void setTempFileDir(String tempFileDir) {
		this.tempFileDir = tempFileDir;
	}

	public ArrayList<File> getFileArrayList() {
		return fileArrayList;
	}

	public void setFileArrayList(ArrayList<File> fileArrayList) {
		this.fileArrayList = fileArrayList;
	}

	public String getMailContent() {
		return mailContent;
	}

	/**
	 * 
	 * @return String mailUserName
	 */
	public String getMailUserName() {
		return mailUserName;
	}

	/**
	 * 
	 * @param mailUserName username of the mail server
	 */
	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}


	/**
	 * 
	 * @return String password
	 */
	public String getMailPassword() {
		return mailPassword;
	}


	/**
	 * 
	 * @param mailPassword password of the username
	 */
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}


	/**
	 * 
	 * @return String mail subject
	 */
	public String getMailSubject() {
		return mailSubject;
	}


	/**
	 * 
	 * @param mailSubject contains mail subject
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}


	/**
	 * 
	 * @return boolean boolean value
	 */
	public boolean isMailShouldBeSend() {
		return mailShouldBeSend;
	}


	/**
	 * 
	 * @param mailShouldBeSend boolean flag
	 */
	public void setMailShouldBeSend(boolean mailShouldBeSend) {
		this.mailShouldBeSend = mailShouldBeSend;
	}


	/**
	 * 
	 * @return String admit card file path
	 */
	public String getAdmitCardFilePathFromPropertyFile() {
		return admitCardFilePathFromPropertyFile;
	}

	public Set<String> getMailSentSet() {
		return mailSentSet;
	}

	public void setMailSentSet(Set<String> mailSentSet) {
		this.mailSentSet = mailSentSet;
	}
	
	public Set<String> getMailNotSentSet() {
		return mailNotSentSet;
	}

	public void setMailNotSentSet(Set<String> mailNotSentSet) {
		this.mailNotSentSet = mailNotSentSet;
	}

	/**
	 * 
	 * @param admitCardFilePathFromPropertyFile contains admit card file path 
	 */
	public void setAdmitCardFilePathFromPropertyFile(String admitCardFilePathFromPropertyFile) {
		this.admitCardFilePathFromPropertyFile = admitCardFilePathFromPropertyFile;
	}

	private boolean mailShouldBeSend = false;
	
	private Map<String, String> oneHundredEmailIdsAndHallTicketNumbers = new LinkedHashMap<>();
	
	private String admitCardFilePathFromPropertyFile;
	
	/**
	 * 
	 * @param mailServer mailServer
	 * @param mailUserName sender
	 * @param mailPassword password of the sender account
	 * @param mailSubject subject of the mail
	 * @param oneHundredEmailIdsAndHallTicketNumbers Map<String, String> contains emailid and hallticket number
	 * @param admitCardFilePathFromPropertyFile file path of the admit card
	 * @param mailShouldBeSend boolean value for sending the mail
	 * @param applicantHallTicketService ApplicantHallTicketService
	 */
	public CasbMailThread(String mailServerHost,String mailServerPort,Boolean starttls,String socketFactoryPort, String mailUserName, String mailPassword, String mailSubject,String mailContent,
			Map<String, String> oneHundredEmailIdsAndHallTicketNumbers,String admitCardFilePathFromPropertyFile,ArrayList<File> fileArrayList,
			boolean mailShouldBeSend, ApplicantHallTicketService applicantHallTicketService,String sendAdmitCardsRadio,String tempFileDirFromPropertyFile, String mailReason,ExamMasterRepository examMasterRepository,EmailSentRepository emailSentRepository) {
		super();
		centerWiseSendEmail.info("inside constructor of " + Thread.currentThread().getName());
		
		this.mailUserName = mailUserName;
		this.mailPassword = mailPassword;
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
		this.fileArrayList = fileArrayList;
		this.admitCardFilePathFromPropertyFile = admitCardFilePathFromPropertyFile;
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
		this.tempFileDirFromPropertyFile = tempFileDirFromPropertyFile;
		centerWiseSendEmail.info("oneHundredEmailIdsAndHallTicketNumbers.size() : " + oneHundredEmailIdsAndHallTicketNumbers.size());
		
		this.oneHundredEmailIdsAndHallTicketNumbers.putAll(oneHundredEmailIdsAndHallTicketNumbers);
		this.admitCardFilePathFromPropertyFile = admitCardFilePathFromPropertyFile;
		this.mailShouldBeSend = mailShouldBeSend;
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
		this.applicantHallTicketService = applicantHallTicketService;
		this.mailReason = mailReason;
		this.examMasterRepository =examMasterRepository;
		this.emailSentRepository = emailSentRepository;	
		
	}

	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");
	
	private static final Logger centerWiseMailNotSentEmailLogger = Logger.getLogger("CenterWiseMailNotSentEmailIds");
	
	/**
	 * run method of the MailThread class
	 */
	@Override
	public void run()
	{
		String threadName = Thread.currentThread().getName();
		centerWiseSendEmail.info("inside run of " + threadName);
		
	
		
		centerWiseSendEmail.info("oneHundredEmailIdsAndHallTicketNumbers.size() of " + threadName + " is " + oneHundredEmailIdsAndHallTicketNumbers.size());
		String emailId = null;
		String hallTicketNumber = null;
		String centerCodeFromHallTicketNumber = null;
		String admitCardFilePath = null;
		
		
		int count = 1;
		int mailNotSentCounter = 0;
		
		for(Map.Entry<String, String> emailIdAndHallTicketNumber : oneHundredEmailIdsAndHallTicketNumbers.entrySet())
		{
			emailId = emailIdAndHallTicketNumber.getKey();
			hallTicketNumber = emailIdAndHallTicketNumber.getValue();
			centerCodeFromHallTicketNumber = hallTicketNumber.substring(hallTicketNumber.length()-12, 11);
			centerWiseSendEmail.info(threadName +" " + count + " " + emailId + "/" + hallTicketNumber + "/" + centerCodeFromHallTicketNumber);
			count++;
			
			
			admitCardFilePath = getAdmitCardFilePathFromHallTicketNumberAndCenterCode(hallTicketNumber, centerCodeFromHallTicketNumber);
			
			centerWiseSendEmail.info("admitCardFilePath : " + admitCardFilePath);
			
			if(admitCardFilePath == null)
			{
				centerWiseSendEmail.error("Admit card of Hall Ticket Number " + hallTicketNumber + " with EmailId " + emailId + " is not present");
				centerWiseMailNotSentEmailLogger.info(emailId);
				mailNotSentSet.add(emailId);
				mailNotSentCounter++;
			}
			else
			{
				if(sendAdmitCardsRadio.equalsIgnoreCase("yes"))
				{
					if(!MailServiceImpl.sendMailCenterWise(mailServerHost,mailServerPort,starttls,socketFactoryPort, mailUserName, mailPassword, emailId, mailSubject ,mailContent, fileArrayList, mailShouldBeSend,tempFileDirFromPropertyFile,admitCardFilePath))	
					{
						centerWiseSendEmail.error("Mail Not Sent to : " + emailId + " due to Exception");
						centerWiseMailNotSentEmailLogger.info(emailId);
						mailNotSentSet.add(emailId);
						mailNotSentCounter++;
					}
					else
					{
						mailSentSet.add(emailId);						
					}
				}
				else
				{
					if(!MailServiceImpl.sendMailCenterWise(mailServerHost,mailServerPort,starttls,socketFactoryPort, mailUserName, mailPassword, emailId, mailSubject ,mailContent, fileArrayList, mailShouldBeSend,tempFileDirFromPropertyFile,null))	
					{
						centerWiseSendEmail.error("Mail Not Sent to : " + emailId + " due to Exception");
						centerWiseMailNotSentEmailLogger.info(emailId);
						mailNotSentSet.add(emailId);
						mailNotSentCounter++;
					}
					else
					{
						mailSentSet.add(emailId);
					}
				}
					
				
			}
		}
		
		centerWiseSendEmail.error("mailNotSentCounter in " + threadName + " Batch : " + mailNotSentCounter);
		centerWiseSendEmail.info("mailSentCounter in " + threadName + " Batch : " + mailSentSet.size());
		
		centerWiseSendEmail.info("updating emailSent Flag - " + threadName);
		applicantHallTicketService.updateEmailSentFlag(mailSentSet, true);      //updating email send flag in database
				
	}
	
	/**
	 * 
	 * @param hallTicketNumber contains hallticket number
	 * @param centerCode contains centre code
	 * @return String file path of the admit card based on the supplied parameters
	 */
	public String getAdmitCardFilePathFromHallTicketNumberAndCenterCode(String hallTicketNumber, String centerCode) {

		String filePathToReturn = null;
		String fileNameWithOutExt = null;
		
		File dir = new File(admitCardFilePathFromPropertyFile + centerCode);
		File[] list = dir.listFiles();

		if(list!=null)
		{
			for (File file : list)
			{
				fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
				if (file.isDirectory())
				{
					getAdmitCardFilePathFromHallTicketNumberAndCenterCode(hallTicketNumber, centerCode);
				}
				else if (hallTicketNumber.equalsIgnoreCase(fileNameWithOutExt))
				{
					filePathToReturn = dir.getAbsolutePath() + File.separator + file.getName();
					break;
				}
			}
		}

		return filePathToReturn;
	}
	
	/*public boolean updateEmailSentFlag(Set<String> mailSentSet, boolean flagToSet)
	{
		ApplicantHallTicketServiceImpl applicantHallTicketServiceImpl = new ApplicantHallTicketServiceImpl();
		return applicantHallTicketServiceImpl.updateEmailSentFlag(mailSentSet, flagToSet);
	}*/

}
