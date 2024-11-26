package gov.cdac.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TestEmailBulkModel {
	
	private String mailServerHost;
	private String mailReason;
	private String mailServerPort;
	private Boolean starttls;
	private String socketFactoryPort;
	private String emailContent;
	private String emailSubject;
	private ArrayList<EmailId> obj;
	private Integer postRadio;
	
	private String pageType;
	
	private int sentType;
	
	private String scheduleList;
	
	private Boolean isAppCredIdList;
	
	private List<Timestamp> emailScheduleDatetime;
	
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public ArrayList<EmailId> getObj() {
		return obj;
	}
	public void setObj(ArrayList<EmailId> obj) {
		this.obj = obj;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
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
	
	
	public Integer getPostRadio() {
		return postRadio;
	}
	public void setPostRadio(Integer postRadio) {
		this.postRadio = postRadio;
	}
	
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public int getSentType() {
		return sentType;
	}
	public void setSentType(int sentType) {
		this.sentType = sentType;
	}
	public String getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(String scheduleList) {
		this.scheduleList = scheduleList;
	}
	public Boolean getIsAppCredIdList() {
		return isAppCredIdList;
	}
	public void setIsAppCredIdList(Boolean isAppCredIdList) {
		this.isAppCredIdList = isAppCredIdList;
	}
	public List<Timestamp> getEmailScheduleDatetime() {
		return emailScheduleDatetime;
	}
	public void setEmailScheduleDatetime(List<Timestamp> emailScheduleDatetime) {
		this.emailScheduleDatetime = emailScheduleDatetime;
	}
	@Override
	public String toString() {
		return "TestEmailBulkModel [mailServerHost=" + mailServerHost + ", mailReason=" + mailReason
				+ ", mailServerPort=" + mailServerPort + ", starttls=" + starttls + ", socketFactoryPort="
				+ socketFactoryPort + ", emailContent=" + emailContent + ", emailSubject=" + emailSubject + ", obj="
				+ obj + ", postRadio=" + postRadio + "]";
	}
	
}
