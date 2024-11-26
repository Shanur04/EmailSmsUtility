package gov.cdac.casbPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_text_report_details")
public class CasbEmailTextReportDetails {

	@Id
	@SequenceGenerator(name = "email_text_report_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_text_report_details_id_seq")
	@Column(name = "id")
	private Long reportId;

	@Column(name="email_id")
	private String emailId;
	
	@Column(name="report_path")
	private String reportPath;
	
	public CasbEmailTextReportDetails() {
	}

	public CasbEmailTextReportDetails(String emailId, String reportPath) {
		super();
		this.emailId = emailId;
		this.reportPath = reportPath;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
}
