package gov.cdac.emailservice.Threads;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gov.cdac.emailservice.services.MailServiceImpl;

public class MailThreadExcel extends Thread {
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
	private String emailReason = null;
	private Set<String> mailNotSentSet = new HashSet<>();
	private Set<String> mailSentSet = new HashSet<>();
	
	
	public MailThreadExcel() {
		super();
	}

	public MailThreadExcel(String mailServerHost, String mailServerPort, Boolean starttls, String socketFactoryPort,
			String mailUserName, String mailpassword, String emailSubject, String emailContent,
			ArrayList<String> onehundredSubset, ArrayList<File> fileArray, String emailAttachmentDirFromPropFile,
			String emailReason) {
		super();

		centerWiseSendEmail.info("inside constructor of " + Thread.currentThread().getName());
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
		this.mailUserName = mailUserName;
		this.mailpassword = mailpassword;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
		centerWiseSendEmail.info("onehundredSubset.size() : " + onehundredSubset.size());

		this.onehundredSubset.addAll(onehundredSubset);
		this.fileArray = fileArray;
		this.emailAttachmentDirFromPropFile = emailAttachmentDirFromPropFile;
		this.emailReason = emailReason;
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

	public String getEmailReason() {
		return emailReason;
	}

	public void setEmailReason(String emailReason) {
		this.emailReason = emailReason;
	}

	public Set<String> getMailNotSentSet() {
		return mailNotSentSet;
	}

	public void setMailNotSentSet(Set<String> mailNotSentSet) {
		this.mailNotSentSet = mailNotSentSet;
	}

	public Set<String> getMailSentSet() {
		return mailSentSet;
	}

	public void setMailSentSet(Set<String> mailSentSet) {
		this.mailSentSet = mailSentSet;
	}


	private ArrayList<String> onehundredSubset = new ArrayList<String>();

	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		centerWiseSendEmail.info("inside run of " + threadName);
		centerWiseSendEmail.info("oneHundredEmailIds.size() of " + threadName + " is " + onehundredSubset.size());
		String emailId = null;
		int mailNotSentCounter = 0;
		

		for (String singleEmailFormHundred : onehundredSubset) {
			emailId = singleEmailFormHundred;
		
			if (!MailServiceImpl.sendMailCenterWise(mailServerHost, mailServerPort, starttls, socketFactoryPort,
					mailUserName, mailpassword, emailId, emailSubject, emailContent, fileArray, true,
					emailAttachmentDirFromPropFile, null)) {
				centerWiseSendEmail.error("Mail Not Sent to : " + emailId + " due to Exception");
				centerWiseSendEmail.info(emailId);
				mailNotSentSet.add(emailId);
				mailNotSentCounter++;
			} else {
				mailSentSet.add(emailId);
			}
		}
		centerWiseSendEmail.error("mailNotSentCounter in " + threadName + " Batch : " + mailNotSentCounter);
		centerWiseSendEmail.info("mailSentCounter in " + threadName + " Batch : " + mailSentSet.size());
	}
}
