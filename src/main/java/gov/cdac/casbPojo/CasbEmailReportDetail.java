package gov.cdac.casbPojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_report_details")
public class CasbEmailReportDetail {
	@Id
	@SequenceGenerator(name = "email_report_details_report_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_report_details_report_id_seq")
	@Column(name = "report_id")
	private Long reportId;

	@Column(name="file_path")
	private String filePath;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="email_sent_id", referencedColumnName="email_sent_id")	
	private CasbEmailSent emailSent;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="email_schedule_id", referencedColumnName="email_schedule_id")
	private CasbEmailScheduleDetail emailScheduleDetail;
	
	@Column(name="report_status")
	private Boolean reportDetail;
	
	public CasbEmailReportDetail() {
		
	}

	public CasbEmailReportDetail(Long reportId, String filePath, Boolean reportDetail) {
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
	
	public Boolean getReportDetail() {
		return reportDetail;
	}
	
	public void setReportDetail(Boolean reportDetail) {
		this.reportDetail = reportDetail;
	}

	public CasbEmailSent getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(CasbEmailSent emailSent) {
		this.emailSent = emailSent;
	}

	public CasbEmailScheduleDetail getEmailScheduleDetail() {
		return emailScheduleDetail;
	}

	public void setEmailScheduleDetail(CasbEmailScheduleDetail emailScheduleDetail) {
		this.emailScheduleDetail = emailScheduleDetail;
	}

}
