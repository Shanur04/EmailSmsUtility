package gov.cdac.afcatPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gov.cdac.projection.BatchSummary;


@Entity
@Table(name = "sms_sent")
@JsonIgnoreProperties({ "SMSNotSentDetails" })
public class AfcatSMSSent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sms_sent_sms_sent_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_sent_sms_sent_id_seq")
	@Column(name = "sms_sent_id")
	private Long SMSSentId;

	@ManyToOne
	@JoinColumn(name = "batch_id")
	private AfcatBatchDetail batchMaster;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private AfcatExamMaster afcatExamMaster;

	@Column(name = "candidates_count")
	private Integer candidateCount;

	@Column(name = "message")
	private String message;

	@ManyToOne
	@JoinColumn(name = "sms_reason_master_id")
	private AfcatSMSReasonMaster afcatSMSReasonMaster;

	@Column(name = "number_of_sms_units")
	private Integer numberOfSMSUnits;

	@Column(name = "sms_start_datetime")
	private Timestamp smsStartDatetime;

	@Column(name = "sms_end_datetime")
	private Timestamp smsEndDatetime;

	@Column(name = "sms_sent_type")
	private int smsSentType;

	@Column(name = "sms_sent_status")
	private Boolean smsSentStatus;

	@UpdateTimestamp
	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	@Column(name = "req_type")
	private String reqType;

	@Column(name = "template_id")
	private String templateMasterId;

	@OneToMany(mappedBy = "afcatSMSSent")
	private List<AfcatSMSNotSentDetails> afcatSMSNotSentDetails;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "afcatSMSSent", cascade = CascadeType.ALL)
	private List<AfcatSMSScheduleDetail> afcatSMSScheduleDetails = new ArrayList<>();

	@OneToOne(mappedBy = "afcatSMSSent")
	private AfcatSMSReportDetail afcatSMSReportDetail;

	public AfcatSMSSent() {
	}

	public AfcatSMSSent(BatchSummary batchSummary, Integer afcatExamMasterId, Integer candidateCount, String message,
			Integer smsReasonMasterId, Boolean smsSentStatus, int smsSentType, String reqType,
			String templateMasterId) {
		this.batchMaster = new AfcatBatchDetail(batchSummary.getBatchId());
		this.afcatExamMaster = new AfcatExamMaster(afcatExamMasterId);
		this.afcatSMSReasonMaster = new AfcatSMSReasonMaster(smsReasonMasterId);
		this.candidateCount = candidateCount;
		this.message = message;
		this.numberOfSMSUnits = Double.valueOf(Math.ceil((float) message.length() / 160)).intValue() * candidateCount;
		this.smsStartDatetime = new Timestamp(System.currentTimeMillis());
		this.smsSentStatus = smsSentStatus;
		this.smsSentType = smsSentType;
		this.reqType = reqType;
		this.templateMasterId = templateMasterId;
	}

	public AfcatSMSSent(Long SMSSentId, AfcatBatchDetail batchMaster, AfcatExamMaster afcatExamMaster,
			Integer candidateCount, String message, AfcatSMSReasonMaster afcatSMSReasonMaster, Integer numberOfSMSUnits,
			Timestamp smsStartDatetime, Timestamp smsEndDatetime, int smsSentType, Boolean smsSentStatus,
			Timestamp recordTracking, String reqType) {
		super();
		this.SMSSentId = SMSSentId;
		this.batchMaster = batchMaster;
		this.afcatExamMaster = afcatExamMaster;
		this.candidateCount = candidateCount;
		this.message = message;
		this.afcatSMSReasonMaster = afcatSMSReasonMaster;
		this.numberOfSMSUnits = numberOfSMSUnits;
		this.smsStartDatetime = smsStartDatetime;
		this.smsEndDatetime = smsEndDatetime;
		this.smsSentType = smsSentType;
		this.smsSentStatus = smsSentStatus;
		this.recordTracking = recordTracking;
		this.reqType = reqType;
	}

	public int getSmsSentType() {
		return smsSentType;
	}

	public void setSmsSentType(int smsSentType) {
		this.smsSentType = smsSentType;
	}

	public Boolean getSmsSentStatus() {
		return smsSentStatus;
	}

	public void setSmsSentStatus(Boolean smsSentStatus) {
		this.smsSentStatus = smsSentStatus;
	}

	public AfcatSMSReportDetail getAfcatSMSReportDetail() {
		return afcatSMSReportDetail;
	}

	public void setAfcatSMSReportDetail(AfcatSMSReportDetail afcatSMSReportDetail) {
		this.afcatSMSReportDetail = afcatSMSReportDetail;
	}

	public Long getSMSSentId() {
		return SMSSentId;
	}

	public void setSMSSentId(Long sMSSentId) {
		SMSSentId = sMSSentId;
	}

	public AfcatBatchDetail getBatchMaster() {
		return batchMaster;
	}

	public void setBatchMaster(AfcatBatchDetail batchMaster) {
		this.batchMaster = batchMaster;
	}

	public Integer getCandidateCount() {
		return candidateCount;
	}

	public void setCandidateCount(Integer candidateCount) {
		this.candidateCount = candidateCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNumberOfSMSUnits() {
		return numberOfSMSUnits;
	}

	public void setNumberOfSMSUnits(Integer numberOfSMSUnits) {
		this.numberOfSMSUnits = numberOfSMSUnits;
	}

	public Timestamp getSmsStartDatetime() {
		return smsStartDatetime;
	}

	public void setSmsStartDatetime(Timestamp smsStartDatetime) {
		this.smsStartDatetime = smsStartDatetime;
	}

	public Timestamp getSmsEndDatetime() {
		return smsEndDatetime;
	}

	public void setSmsEndDatetime(Timestamp smsEndDatetime) {
		this.smsEndDatetime = smsEndDatetime;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public AfcatExamMaster getAfcatExamMaster() {
		return afcatExamMaster;
	}

	public void setAfcatExamMaster(AfcatExamMaster afcatExamMaster) {
		this.afcatExamMaster = afcatExamMaster;
	}

	public AfcatSMSReasonMaster getAfcatSMSReasonMaster() {
		return afcatSMSReasonMaster;
	}

	public void setAfcatSMSReasonMaster(AfcatSMSReasonMaster afcatSMSReasonMaster) {
		this.afcatSMSReasonMaster = afcatSMSReasonMaster;
	}

	public List<AfcatSMSNotSentDetails> getAfcatSMSNotSentDetails() {
		return afcatSMSNotSentDetails;
	}

	public void setAfcatSMSNotSentDetails(List<AfcatSMSNotSentDetails> afcatSMSNotSentDetails) {
		this.afcatSMSNotSentDetails = afcatSMSNotSentDetails;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getTemplateMasterId() {
		return templateMasterId;
	}

	public void setTemplateMasterId(String templateMasterId) {
		this.templateMasterId = templateMasterId;
	}

	public List<AfcatSMSScheduleDetail> getAfcatSMSScheduleDetails() {
		return afcatSMSScheduleDetails;
	}

	public void setAfcatSMSScheduleDetails(List<AfcatSMSScheduleDetail> afcatSMSScheduleDetails) {
		this.afcatSMSScheduleDetails = afcatSMSScheduleDetails;
		
		for(AfcatSMSScheduleDetail sd : afcatSMSScheduleDetails) {
			sd.setAfcatSMSSent(this);
		}
	}

	public void getHelperScheduler(AfcatSMSScheduleDetail afcatSMSScheduleDetail) {
		this.afcatSMSScheduleDetails.add(afcatSMSScheduleDetail);
		afcatSMSScheduleDetail.setAfcatSMSSent(this);
	}
}
