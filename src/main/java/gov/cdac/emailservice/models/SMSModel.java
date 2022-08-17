package gov.cdac.emailservice.models;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;


public class SMSModel {

	@NotBlank
	private String templateMasterId;
	@NotNull
	private Integer smsReasonMasterId;
	@NotBlank
	private String smsContent;

	private String testMobileNumbers;

	private MultipartFile appCredfile;

	private Integer centreId;

	private List<Integer> slotIds;
	

//	private List<SmsScheduleDatetime> smsScheduleDatetime;
	private List<Timestamp> smsScheduleDatetime;
	
	private String scheduleList;
	
	private int repetationCount;

	private double period;

	private int smsSentType;

	public SMSModel() {
	}

	public String getTemplateMasterId() {
		return templateMasterId;
	}

	public void setTemplateMasterId(String templateMasterId) {
		this.templateMasterId = templateMasterId;
	}

	public Integer getSmsReasonMasterId() {
		return smsReasonMasterId;
	}

	public void setSmsReasonMasterId(Integer smsReasonMasterId) {
		this.smsReasonMasterId = smsReasonMasterId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getTestMobileNumbers() {
		return testMobileNumbers;
	}

	public void setTestMobileNumbers(String testMobileNumbers) {
		this.testMobileNumbers = testMobileNumbers;
	}

	public MultipartFile getAppCredfile() {
		return appCredfile;
	}

	public void setAppCredfile(MultipartFile appCredfile) {
		this.appCredfile = appCredfile;
	}

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public List<Integer> getSlotIds() {
		return slotIds;
	}

	public void setSlotIds(List<Integer> slotIds) {
		this.slotIds = slotIds;
	}

//	public void setSmsScheduleDatetime(List<SmsScheduleDatetime> smsScheduleDatetime) {
//		System.out.println("set date time");
//		this.smsScheduleDatetime = smsScheduleDatetime;
//	}
//	
//	public List<SmsScheduleDatetime> getSmsScheduleDatetime() {
//		return smsScheduleDatetime;
//	}
	
	public void setSmsScheduleDatetime(List<Timestamp> smsScheduleDatetime) {
		this.smsScheduleDatetime = smsScheduleDatetime;
	}
	
	public List<Timestamp> getSmsScheduleDatetime() {
		return smsScheduleDatetime;
	}

	public int getRepetationCount() {
		return repetationCount;
	}

	public void setRepetationCount(int repetationCount) {
		this.repetationCount = repetationCount;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public int getSmsSentType() {
		return smsSentType;
	}

	public void setSmsSentType(int smsSentType) {
		this.smsSentType = smsSentType;
	}

	public String getScheduleList() {
		return scheduleList;
	}
	
	public void setScheduleList(String scheduleList) {
		this.scheduleList = scheduleList;
	}
	
}
