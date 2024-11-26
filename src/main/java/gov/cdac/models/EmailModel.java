package gov.cdac.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class EmailModel {	
	@NotBlank
	private String emailContent;
	
	@NotBlank
	private String emailSubject;
	
	@NotBlank
	private String testEmailIds;
	
	private String sendAdmitCardsRadio;
	
	private String slots;

	private String centers;
		
	private String mailServerHost;
	
	private String mailServerPort;

	private Boolean starttls;
		
	private String exam;	
	
	private String mailReason;
	
	private String emailBreathingTime;
	
	private String emailsPerBatch;
	
	private String socketFactoryPort;
	
	private ArrayList<EmailId> obj;
	
	private Integer postRadio;
	
	private String pageType;
	
	private int sentType;
	
	private String scheduleList;
	
	private Boolean isAppCredIdList;
	
	private List<Timestamp> emailScheduleDatetime;


	public EmailModel() {
		// TODO Auto-generated constructor stub
	}
	
	public EmailModel(@NotBlank String emailContent, @NotBlank String emailSubject,	String mailServerHost, String mailServerPort, Boolean starttls, String socketFactoryPort, String mailReason,
			String pageType, int sentType) {
		super();
		this.emailContent = emailContent;
		this.emailSubject = emailSubject;
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
		this.mailReason = mailReason;
		this.pageType = pageType;
		this.sentType = sentType;
	}

	public EmailModel(@NotBlank String emailContent, @NotBlank String emailSubject, String sendAdmitCardsRadio,
			String slots, String centers, String mailServerHost, String mailServerPort, Boolean starttls, String exam,
			String mailReason, String emailBreathingTime, String emailsPerBatch, String socketFactoryPort, int sentType,
			String scheduleList, Boolean isAppCredIdList) {
		super();
		this.emailContent = emailContent;
		this.emailSubject = emailSubject;
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
		this.slots = slots;
		this.centers = centers;
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.exam = exam;
		this.mailReason = mailReason;
		this.emailBreathingTime = emailBreathingTime;
		this.emailsPerBatch = emailsPerBatch;
		this.socketFactoryPort = socketFactoryPort;
		this.sentType = sentType;
		this.scheduleList = scheduleList;
		this.isAppCredIdList = isAppCredIdList;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getTestEmailIds() {
		return testEmailIds;
	}

	public void setTestEmailIds(String testEmailIds) {
		this.testEmailIds = testEmailIds;
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

	public String getMailReason() {
		return mailReason;
	}

	public void setMailReason(String mailReason) {
		this.mailReason = mailReason;
	}
	
	public String getPageType() {
		return pageType;
	}
	
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	
	public void setSentType(int sentType) {
		this.sentType = sentType;
	}
	
	public int getSentType() {
		return sentType;
	}
	
	public List<Timestamp> getEmailScheduleDatetime() {
		return emailScheduleDatetime;
	}
	public void setEmailScheduleDatetime(List<Timestamp> emailScheduleDatetime) {
		this.emailScheduleDatetime = emailScheduleDatetime;
	}
	
	public String getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(String scheduleList) {
		this.scheduleList = scheduleList;
	}

	public String getSendAdmitCardsRadio() {
		return sendAdmitCardsRadio;
	}

	public void setSendAdmitCardsRadio(String sendAdmitCardsRadio) {
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
	}

	public String getSlots() {
		return slots;
	}

	public void setSlots(String slots) {
		this.slots = slots;
	}

	public String getCenters() {
		return centers;
	}

	public void setCenters(String centers) {
		this.centers = centers;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getEmailBreathingTime() {
		return emailBreathingTime;
	}

	public void setEmailBreathingTime(String emailBreathingTime) {
		this.emailBreathingTime = emailBreathingTime;
	}

	public String getEmailsPerBatch() {
		return emailsPerBatch;
	}

	public void setEmailsPerBatch(String emailsPerBatch) {
		this.emailsPerBatch = emailsPerBatch;
	}

	public Boolean getIsAppCredIdList() {
		return isAppCredIdList;
	}

	public void setIsAppCredIdList(Boolean isAppCredIdList) {
		this.isAppCredIdList = isAppCredIdList;
	}
	
	public ArrayList<EmailId> getObj() {
		return obj;
	}
	
	public void setObj(ArrayList<EmailId> obj) {
		this.obj = obj;
	}
	
	public Integer getPostRadio() {
		return postRadio;
	}
	
	public void setPostRadio(Integer postRadio) {
		this.postRadio = postRadio;
	}
}
