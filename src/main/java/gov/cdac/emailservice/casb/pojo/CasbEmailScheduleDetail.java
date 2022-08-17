package gov.cdac.emailservice.casb.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "email_schedule_detail")
public class CasbEmailScheduleDetail {
	@Id
	@SequenceGenerator(name = "email_schedule_detail_email_schedule_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_schedule_detail_email_schedule_id_seq")
	@Column(name = "email_schedule_id")
	private Long emailScheduleDetailId;

	@Column(name = "email_schedule_start_date")
	private Timestamp emailScheduleStartDate;

	@Column(name = "email_schedule_end_date")
	private Timestamp emailScheduleEndDate;

	@Column(name = "email_total_count")
	private long emailTotalCount;

	@Column(name = "email_success_count")
	private long emailSuccessCount;

	@Column(name = "email_failure_count")
	private long emailFailureCount;

	@Column(name = "email_type")
	private String emailType;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "email_sent_id")
	@JsonIgnore
	private CasbEmailSent emailSent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "email_schedule_status_id")
	private CasbEmailStatus emailScheduleStatus;

	@OneToOne(mappedBy = "emailScheduleDetail")
	private CasbEmailReportDetail emailReportDetail;

	public CasbEmailScheduleDetail() {
		// TODO Auto-generated constructor stub
	}

	public CasbEmailScheduleDetail(Timestamp emailScheduleStartDate, long emailTotalCount, String emailType) {
		super();
		this.emailScheduleStartDate = emailScheduleStartDate;
		this.emailTotalCount = emailTotalCount;
		this.emailType = emailType;
	}

	public CasbEmailScheduleDetail(Long emailScheduleDetailId, Timestamp emailScheduleStartDate,
			Timestamp emailScheduleEndDate, long emailTotalCount, long emailSuccessCount, long emailFailureCount,
			String emailType) {
		super();
		this.emailScheduleDetailId = emailScheduleDetailId;
		this.emailScheduleStartDate = emailScheduleStartDate;
		this.emailScheduleEndDate = emailScheduleEndDate;
		this.emailTotalCount = emailTotalCount;
		this.emailSuccessCount = emailSuccessCount;
		this.emailFailureCount = emailFailureCount;
		this.emailType = emailType;
	}

	public Long getEmailScheduleDetailId() {
		return emailScheduleDetailId;
	}

	public void setEmailScheduleDetailId(Long emailScheduleDetailId) {
		this.emailScheduleDetailId = emailScheduleDetailId;
	}

	public Timestamp getEmailScheduleStartDate() {
		return emailScheduleStartDate;
	}

	public void setEmailScheduleStartDate(Timestamp emailScheduleStartDate) {
		this.emailScheduleStartDate = emailScheduleStartDate;
	}

	public Timestamp getEmailScheduleEndDate() {
		return emailScheduleEndDate;
	}

	public void setEmailScheduleEndDate(Timestamp emailScheduleEndDate) {
		this.emailScheduleEndDate = emailScheduleEndDate;
	}

	public long getEmailTotalCount() {
		return emailTotalCount;
	}

	public void setEmailTotalCount(long emailTotalCount) {
		this.emailTotalCount = emailTotalCount;
	}

	public long getEmailSuccessCount() {
		return emailSuccessCount;
	}

	public void setEmailSuccessCount(long emailSuccessCount) {
		this.emailSuccessCount = emailSuccessCount;
	}

	public long getEmailFailureCount() {
		return emailFailureCount;
	}

	public void setEmailFailureCount(long emailFailureCount) {
		this.emailFailureCount = emailFailureCount;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public CasbEmailSent getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(CasbEmailSent emailSent) {
		this.emailSent = emailSent;
	}

	public CasbEmailStatus getEmailScheduleStatus() {
		return emailScheduleStatus;
	}

	public void setEmailScheduleStatus(CasbEmailStatus emailScheduleStatus) {
		this.emailScheduleStatus = emailScheduleStatus;
	}

	public CasbEmailReportDetail getEmailReportDetail() {
		return emailReportDetail;
	}

	public void setEmailReportDetail(CasbEmailReportDetail emailReportDetail) {
		this.emailReportDetail = emailReportDetail;
	}


}
