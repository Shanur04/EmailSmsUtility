package gov.cdac.emailservice.Threads;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gov.cdac.emailservice.services.ApplicantHallTicketService;
import gov.cdac.emailservice.services.MailServiceImpl;

public class MailThreaRejected extends Thread {

	private String mailServerHost = null;
	private String mailServerPort = null;
	private Boolean starttls = false;
	private String socketFactoryPort = null;
	private String mailUserName = null;
	private String mailpassword = null;
	private String emailSubject = null;
	private String emailContent = null;
	private ArrayList<File> fileArray = new ArrayList<File>();
	private String emailAttachmentDirFromPropFile = null;
	HttpSession session = null;
	private String emailReason = null;
	
	private ApplicantHallTicketService applicantHallTicketService = null;
	
	public MailThreaRejected() {
	}

	HashMap<String, ArrayList<String>> oneHundredSubset = new LinkedHashMap<>();

	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");

	public MailThreaRejected(String mailServerHost2, String mailServerPort2, Boolean starttls2,
			String socketFactoryPort2, String mailUserName2, String mailPassword2, String emailSubject2,
			String emailContent2, HashMap<String, ArrayList<String>> oneHundredSubset, ArrayList<File> fileArray2,
			String emailAttachmentDirFromPropertyFile, HttpSession session2, String mailReason, ApplicantHallTicketService applicantHallTicketService2) {
		// TODO Auto-generated constructor stub
		super();

		centerWiseSendEmail.info("inside constructor of " + Thread.currentThread().getName());

		this.mailServerHost = mailServerHost2;
		this.mailServerPort = mailServerPort2;
		this.starttls = starttls2;
		this.socketFactoryPort = socketFactoryPort2;
		this.mailUserName = mailUserName2;
		this.mailpassword = mailPassword2;
		this.emailSubject = emailSubject2;
		this.emailContent = emailContent2;
		centerWiseSendEmail.info("onehundredSubset.size() : " + oneHundredSubset.size());

		this.oneHundredSubset.putAll(oneHundredSubset);
		this.fileArray = fileArray2;
		this.emailAttachmentDirFromPropFile = emailAttachmentDirFromPropertyFile;
		this.session = session2;
		this.emailReason = mailReason;
		this.applicantHallTicketService=applicantHallTicketService2;
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

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailpassword() {
		return mailpassword;
	}

	public void setMailpassword(String mailpassword) {
		this.mailpassword = mailpassword;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public ArrayList<File> getFileArray() {
		return fileArray;
	}

	public void setFileArray(ArrayList<File> fileArray) {
		this.fileArray = fileArray;
	}

	public String getEmailAttachmentDirFromPropFile() {
		return emailAttachmentDirFromPropFile;
	}

	public void setEmailAttachmentDirFromPropFile(String emailAttachmentDirFromPropFile) {
		this.emailAttachmentDirFromPropFile = emailAttachmentDirFromPropFile;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getEmailReason() {
		return emailReason;
	}

	public void setEmailReason(String emailReason) {
		this.emailReason = emailReason;
	}
	

	public ApplicantHallTicketService getApplicantHallTicketService() {
		return applicantHallTicketService;
	}

	public void setApplicantHallTicketService(ApplicantHallTicketService applicantHallTicketService) {
		this.applicantHallTicketService = applicantHallTicketService;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		centerWiseSendEmail.info("inside run of " + threadName);
		centerWiseSendEmail.info("oneHundredEmailIds.size() of " + threadName + " is " + oneHundredSubset.size());
		String emailId = null;
		int mailNotSentCounter = 0;
		Set<String> mailNotSentSet = new HashSet<>();
		Set<String> mailSentSet = new HashSet<>();

		for (Entry<String, ArrayList<String>> singleEmailFormHundred : oneHundredSubset.entrySet()) {
			emailId = singleEmailFormHundred.getKey();
//			this.emailContent+=singleEmailFormHundred.getValue();
			String newEmailContent= this.emailContent.replace("$$", singleEmailFormHundred.getValue().get(0));
			String afterEngilshContent=newEmailContent.replace("^^", singleEmailFormHundred.getValue().get(1));
//			System.out.println("The EMail Content Is "+newEmailContent);
//			System.out.println("The after Email Content Is "+afterEngilshContent);
//			System.out.println("in Mail Thread Email Is " + singleEmailFormHundred.getKey() + " Content Is "
//					+ singleEmailFormHundred.getValue()+"And Email Content Is "+newEmailContent);
			if (!MailServiceImpl.sendMailCenterWise(mailServerHost, mailServerPort, starttls, socketFactoryPort,
					mailUserName, mailpassword, emailId, emailSubject, afterEngilshContent, fileArray, true,
					emailAttachmentDirFromPropFile, null)) {
				centerWiseSendEmail.error("Mail Not Sent to : " + emailId + " due to Exception");
				centerWiseSendEmail.info(emailId);
				mailNotSentSet.add(emailId);
				session.setAttribute("notSentEmailIds", mailNotSentSet);
				mailNotSentCounter++;
			} else {
				mailSentSet.add(emailId);
				session.setAttribute("mailSent", mailSentSet.size());
			}
			newEmailContent="";
			afterEngilshContent="";
		}
		centerWiseSendEmail.error("mailNotSentCounter in " + threadName + " Batch : " + mailNotSentCounter);
		centerWiseSendEmail.info("mailSentCounter in " + threadName + " Batch : " + mailSentSet.size());
		
		applicantHallTicketService.updateEmailSentFlagInApplicantCredential(mailSentSet, true);      //updating email send flag in database
		
	}

}
