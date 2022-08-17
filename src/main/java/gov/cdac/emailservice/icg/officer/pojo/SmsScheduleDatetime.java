package gov.cdac.emailservice.icg.officer.pojo;

import java.sql.Timestamp;

public class SmsScheduleDatetime {
	public Timestamp scheduleDateTime;
	
	public SmsScheduleDatetime() {
		// TODO Auto-generated constructor stub
	}
	
	public SmsScheduleDatetime(Timestamp scheduleDateTime) {
		super();
		this.scheduleDateTime = scheduleDateTime;
	}

	public void setScheduleDateTime(Timestamp scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public Timestamp getScheduleDateTime() {
		return scheduleDateTime;
	}
}
