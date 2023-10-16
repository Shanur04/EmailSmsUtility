package gov.cdac.afcatPojo;

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
@Table(name = "sms_schedule_detail")
public class AfcatSMSScheduleDetail {

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sms_sent_id")
	@JsonIgnore
	private AfcatSMSSent afcatSMSSent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sms_schedule_status_id")
	private AfcatSMSStatus afcatSmsScheduleStatus;

	@OneToOne(mappedBy = "afcatSMSScheduleDetail")
	private AfcatSMSReportDetail afcatSMSReportDetail;

	public AfcatSMSScheduleDetail() {
		// TODO Auto-generated constructor stub
	}

	public AfcatSMSScheduleDetail(Long smsScheduleDetailId, Timestamp smsScheduleStartDate,
			Timestamp smsScheduleEndDate, long smsTotalCount, long smsSuccessCount, long smsFailureCount,
			String smsType) {
		super();
		this.smsScheduleDetailId = smsScheduleDetailId;
		this.smsScheduleStartDate = smsScheduleStartDate;
		this.smsScheduleEndDate = smsScheduleEndDate;
		this.smsTotalCount = smsTotalCount;
		this.smsSuccessCount = smsSuccessCount;
		this.smsFailureCount = smsFailureCount;
		this.smsType = smsType;
	}

	public AfcatSMSScheduleDetail(Timestamp smsScheduleStartDate, long smsTotalCount, String smsType) {
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

	public AfcatSMSSent getAfcatSMSSent() {
		return afcatSMSSent;
	}

	public void setAfcatSMSSent(AfcatSMSSent afcatSMSSent) {
		this.afcatSMSSent = afcatSMSSent;
	}

	public AfcatSMSStatus getAfcatSmsScheduleStatus() {
		return afcatSmsScheduleStatus;
	}

	public void setAfcatSmsScheduleStatus(AfcatSMSStatus afcatSmsScheduleStatus) {
		this.afcatSmsScheduleStatus = afcatSmsScheduleStatus;
	}

	public AfcatSMSReportDetail getAfcatSMSReportDetail() {
		return afcatSMSReportDetail;
	}

	public void setAfcatSMSReportDetail(AfcatSMSReportDetail afcatSMSReportDetail) {
		this.afcatSMSReportDetail = afcatSMSReportDetail;
	}

	@Override
	public String toString() {
		return "AfcatSMSScheduleDetail [smsScheduleDetailId=" + smsScheduleDetailId + ", smsScheduleStartDate="
				+ smsScheduleStartDate + ", smsScheduleEndDate=" + smsScheduleEndDate + ", smsTotalCount="
				+ smsTotalCount + ", smsSuccessCount=" + smsSuccessCount + ", smsFailureCount=" + smsFailureCount
				+ ", smsType=" + smsType + "]";
	}

}