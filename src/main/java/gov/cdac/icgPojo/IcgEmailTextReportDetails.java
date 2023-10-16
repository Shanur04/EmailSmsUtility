package gov.cdac.icgPojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "email_text_report_details")
public class IcgEmailTextReportDetails {
	@Id
	@SequenceGenerator(name = "email_text_report_details_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_text_report_details_id_seq")
	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "report_path")
	private String reportPath;
	
//	 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//	 private List<IcgEmailSent> emailSentList = new ArrayList<>();

	public IcgEmailTextReportDetails() {
		// TODO Auto-generated constructor stub
	}

	public IcgEmailTextReportDetails(String emailId, String path) {
		super();
		this.emailId = emailId;
		this.reportPath = path;
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
