package gov.cdac.icgPojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sms_report_details")
public class SMSReportDetail {
	@Id
	@SequenceGenerator(name = "sms_report_details_report_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_report_details_report_id_seq")
	@Column(name = "report_id")
	private Long reportId;

	@Column(name="file_path")
	private String filePath;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="sms_sent_id", referencedColumnName="sms_sent_id")	
	private SMSSent smsSent;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="sms_schedule_id", referencedColumnName="sms_schedule_id")
	private SMSScheduleDetail smsScheduleDetail;
	
	@Column(name="report_status")
	private Boolean reportDetail;
	
	public SMSReportDetail() {
		// TODO Auto-generated constructor stub
	}

	public SMSReportDetail(Long reportId, String filePath, Boolean reportDetail) {
		super();
		this.reportId = reportId;
		this.filePath = filePath;
		this.reportDetail = reportDetail;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public SMSSent getSmsSent() {
		return this.smsSent;
	}

	public void setSmsSent(SMSSent smsSent) {
		this.smsSent = smsSent;
	}

	public SMSScheduleDetail getSmsScheduleDetail() {
		return smsScheduleDetail;
	}

	public void setSmsScheduleDetail(SMSScheduleDetail smsScheduleDetail) {
		this.smsScheduleDetail = smsScheduleDetail;
	}
	
	public Boolean getReportDetail() {
		return reportDetail;
	}
	
	public void setReportDetail(Boolean reportDetail) {
		this.reportDetail = reportDetail;
	}
}
