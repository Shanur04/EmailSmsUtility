package gov.cdac.emailservice.afcat.pojo;

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
public class AfcatSMSReportDetail {
	

	@Id
	@SequenceGenerator(name = "sms_report_details_report_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_report_details_report_id_seq")
	@Column(name = "report_id")
	private Long reportId;

	@Column(name="file_path")
	private String filePath;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="sms_sent_id", referencedColumnName="sms_sent_id")
	private AfcatSMSSent afcatSMSSent;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="sms_schedule_id", referencedColumnName="sms_schedule_id")
	private AfcatSMSScheduleDetail afcatSMSScheduleDetail;
	
	@Column(name="report_status")
	private Boolean reportDetail;
	
	public AfcatSMSReportDetail() {
		// TODO Auto-generated constructor stub
	}

	public AfcatSMSReportDetail(Long reportId, String filePath, Boolean reportDetail) {
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

	public AfcatSMSSent getAfcatSMSSent() {
		return afcatSMSSent;
	}

	public void setAfcatSMSSent(AfcatSMSSent afcatSMSSent) {
		this.afcatSMSSent = afcatSMSSent;
	}
	
	public AfcatSMSScheduleDetail getAfcatSMSScheduleDetail() {
		return afcatSMSScheduleDetail;
	}
	
	public void setAfcatSMSScheduleDetail(AfcatSMSScheduleDetail afcatSMSScheduleDetail) {
		this.afcatSMSScheduleDetail = afcatSMSScheduleDetail;
	}
	
	public Boolean getReportDetail() {
		return reportDetail;
	}
	
	public void setReportDetail(Boolean reportDetail) {
		this.reportDetail = reportDetail;
	}
	
	@Override
	public String toString() {
		return "AfcatSMSReportDetail [reportId=" + reportId + ", filePath=" + filePath +"]";
	}

}
