package gov.cdac.casbPojo;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sms_schedule_detail")
public class CasbSMSScheduleDetail {

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
	private CasbSMSSent casbSMSSent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sms_schedule_status_id")
	private CasbSMSStatus casbSmsScheduleStatus;

	@OneToOne(mappedBy = "casbSMSScheduleDetail")
	private CasbSMSReportDetail casbSMSReportDetail;

	public CasbSMSScheduleDetail() {
		// TODO Auto-generated constructor stub
	}

	public CasbSMSScheduleDetail(Long smsScheduleDetailId, Timestamp smsScheduleStartDate,
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

	public CasbSMSScheduleDetail(Timestamp smsScheduleStartDate, long smsTotalCount, String smsType) {
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

	public CasbSMSSent getCasbSMSSent() {
		return casbSMSSent;
	}

	public void setCasbSMSSent(CasbSMSSent casbSMSSent) {
		this.casbSMSSent = casbSMSSent;
	}

	public CasbSMSStatus getCasbSmsScheduleStatus() {
		return casbSmsScheduleStatus;
	}

	public void setCasbSmsScheduleStatus(CasbSMSStatus casbSmsScheduleStatus) {
		this.casbSmsScheduleStatus = casbSmsScheduleStatus;
	}

	public CasbSMSReportDetail getCasbSMSReportDetail() {
		return casbSMSReportDetail;
	}

	public void setCasbSMSReportDetail(CasbSMSReportDetail casbSMSReportDetail) {
		this.casbSMSReportDetail = casbSMSReportDetail;
	}

	@Override
	public String toString() {
		return "CasbSMSScheduleDetail [smsScheduleDetailId=" + smsScheduleDetailId + ", smsScheduleStartDate="
				+ smsScheduleStartDate + ", smsScheduleEndDate=" + smsScheduleEndDate + ", smsTotalCount="
				+ smsTotalCount + ", smsSuccessCount=" + smsSuccessCount + ", smsFailureCount=" + smsFailureCount
				+ ", smsType=" + smsType + "]";
	}

}
