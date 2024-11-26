package gov.cdac.icgPojo;

import java.sql.Timestamp;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sms_schedule_detail")
public class SMSScheduleDetail {
	@Id
	@SequenceGenerator(name = "sms_schedule_detail_sms_schedule_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_schedule_detail_sms_schedule_id_seq")
	@Column(name = "sms_schedule_id")
	private Long smsScheduleDetailId;

	@Column(name = "sms_schedule_start_date")
	private Timestamp smsScheduleStartDate;

	@Column(name = "sms_schedule_end_date")
	private Timestamp smsScheduleEndDate;

	@Column(name = "sms_total_count")
	private long smsTotalCount;

	@Column(name = "sms_success_count")
	private long smsSuccessCount;

	@Column(name = "sms_failure_count")
	private long smsFailureCount;

	@Column(name = "sms_type")
	private String smsType;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sms_sent_id")
	@JsonIgnore
	private SMSSent smsSent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sms_schedule_status_id")
	private SMSStatus smsScheduleStatus;

	@OneToOne(mappedBy = "smsScheduleDetail")
	private SMSReportDetail SMSReportDetail;

	public SMSScheduleDetail() {
		// TODO Auto-generated constructor stub
	}

	public SMSScheduleDetail(Long smsScheduleDetailId, Timestamp smsScheduleStartDate, Timestamp smsScheduleEndDate,
			long smsTotalCount, long smsSuccessCount, long smsFailureCount, String smsType) {
		super();
		this.smsScheduleDetailId = smsScheduleDetailId;
		this.smsScheduleStartDate = smsScheduleStartDate;
		this.smsScheduleEndDate = smsScheduleEndDate;
		this.smsTotalCount = smsTotalCount;
		this.smsSuccessCount = smsSuccessCount;
		this.smsFailureCount = smsFailureCount;
		this.smsType = smsType;
	}

	public SMSScheduleDetail(Timestamp smsScheduleStartDate, long smsTotalCount, String smsType) {
		super();
		this.smsScheduleStartDate = smsScheduleStartDate;
		this.smsTotalCount = smsTotalCount;
		this.smsType = smsType;
	}

	public Long getSmsScheduleDetailId() {
		return smsScheduleDetailId;
	}

	public void setSmsScheduleDetailId(Long smsScheduleDetailId) {
		this.smsScheduleDetailId = smsScheduleDetailId;
	}

	public Timestamp getSmsScheduleStartDate() {
		return smsScheduleStartDate;
	}

	public void setSmsScheduleStartDate(Timestamp smsScheduleStartDate) {
		this.smsScheduleStartDate = smsScheduleStartDate;
	}

	public Timestamp getSmsScheduleEndDate() {
		return smsScheduleEndDate;
	}

	public void setSmsScheduleEndDate(Timestamp smsScheduleEndDate) {
		this.smsScheduleEndDate = smsScheduleEndDate;
	}

	public long getSmsTotalCount() {
		return smsTotalCount;
	}

	public void setSmsTotalCount(long smsTotalCount) {
		this.smsTotalCount = smsTotalCount;
	}

	public long getSmsSuccessCount() {
		return smsSuccessCount;
	}

	public void setSmsSuccessCount(long smsSuccessCount) {
		this.smsSuccessCount = smsSuccessCount;
	}

	public long getSmsFailureCount() {
		return smsFailureCount;
	}

	public void setSmsFailureCount(long smsFailureCount) {
		this.smsFailureCount = smsFailureCount;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public SMSReportDetail getSMSReportDetail() {
		return SMSReportDetail;
	}

	public SMSSent getSmsSent() {
		return this.smsSent;
	}

	public void setSmsSent(SMSSent smsSent) {
		this.smsSent = smsSent;
	}

	public SMSStatus getSmsScheduleStatus() {
		return this.smsScheduleStatus;
	}

	public void setSmsScheduleStatus(SMSStatus smsScheduleStatus) {
		this.smsScheduleStatus = smsScheduleStatus;
	}

	public void setSMSReportDetail(SMSReportDetail sMSReportDetail) {
		SMSReportDetail = sMSReportDetail;
	}

	@Override
	public String toString() {
		return "SMSScheduleDetail [smsScheduleDetailId=" + smsScheduleDetailId + ", smsScheduleStartDate="
				+ smsScheduleStartDate + ", smsScheduleEndDate=" + smsScheduleEndDate + ", smsTotalCount="
				+ smsTotalCount + ", smsSuccessCount=" + smsSuccessCount + ", smsFailureCount=" + smsFailureCount
				+ ", smsType=" + smsType;
	}
}