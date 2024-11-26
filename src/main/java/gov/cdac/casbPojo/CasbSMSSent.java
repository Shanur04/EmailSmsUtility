package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gov.cdac.projection.BatchSummary;

@Entity
@Table(name = "sms_sent")
@JsonIgnoreProperties({ "SMSNotSentDetails" })
public class CasbSMSSent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "sms_sent_sms_sent_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_sent_sms_sent_id_seq")
	@Column(name = "sms_sent_id")
	private Long SMSSentId;

	@ManyToOne
	@JoinColumn(name = "batch_id")
	private BatchDetail batchMaster;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private CasbExamMaster casbExamMaster;

	@Column(name = "candidates_count")
	private Integer candidateCount;

	@Column(name = "message")
	private String message;

	@ManyToOne
	@JoinColumn(name = "sms_reason_master_id")
	private CasbSMSReasonMaster casbSMSReasonMaster;

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

	@OneToMany(mappedBy = "casbSMSSent")
	private List<CasbSMSNotSentDetails> casbSMSNotSentDetails;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "casbSMSSent", cascade = CascadeType.ALL)
	private List<CasbSMSScheduleDetail> casbSMSScheduleDetails = new ArrayList<>();

	@OneToOne(mappedBy = "casbSMSSent")
	private CasbSMSReportDetail casbSMSReportDetail;
    
    public CasbSMSSent() {
    }

	public CasbSMSSent(Long sMSSentId, BatchDetail batchMaster, CasbExamMaster casbExamMaster, Integer candidateCount,
			String message, CasbSMSReasonMaster casbSMSReasonMaster, Integer numberOfSMSUnits,
			Timestamp smsStartDatetime, Timestamp smsEndDatetime, int smsSentType, Boolean smsSentStatus,
			Timestamp recordTracking, String reqType, String templateMasterId,
			List<CasbSMSNotSentDetails> casbSMSNotSentDetails, List<CasbSMSScheduleDetail> casbSMSScheduleDetails,
			CasbSMSReportDetail casbSMSReportDetail) {
		super();
		SMSSentId = sMSSentId;
		this.batchMaster = batchMaster;
		this.casbExamMaster = casbExamMaster;
		this.candidateCount = candidateCount;
		this.message = message;
		this.casbSMSReasonMaster = casbSMSReasonMaster;
		this.numberOfSMSUnits = numberOfSMSUnits;
		this.smsStartDatetime = smsStartDatetime;
		this.smsEndDatetime = smsEndDatetime;
		this.smsSentType = smsSentType;
		this.smsSentStatus = smsSentStatus;
		this.recordTracking = recordTracking;
		this.reqType = reqType;
		this.templateMasterId = templateMasterId;
		this.casbSMSNotSentDetails = casbSMSNotSentDetails;
		this.casbSMSScheduleDetails = casbSMSScheduleDetails;
		this.casbSMSReportDetail = casbSMSReportDetail;
	}

	public CasbSMSSent(BatchSummary batchSummary, Integer casbExamMasterId, int candidateCount, String message,
			Integer smsReasonMasterId, boolean smsSentStatus, int smsSentType, String reqType, String templateMasterId) {
		this.batchMaster = new BatchDetail(batchSummary.getBatchId());
		this.casbExamMaster = new CasbExamMaster(casbExamMasterId);
		this.casbSMSReasonMaster = new CasbSMSReasonMaster(smsReasonMasterId);
		this.candidateCount = candidateCount;
		this.message = message;
		this.numberOfSMSUnits = Double.valueOf(Math.ceil((float) message.length() / 160)).intValue() * candidateCount;
		this.smsStartDatetime = new Timestamp(System.currentTimeMillis());
		this.smsSentStatus = smsSentStatus;
		this.smsSentType = smsSentType;
		this.reqType = reqType;
		this.templateMasterId = templateMasterId;
		
	}

	public Long getSMSSentId() {
		return SMSSentId;
	}

	public void setSMSSentId(Long sMSSentId) {
		SMSSentId = sMSSentId;
	}

	public BatchDetail getBatchMaster() {
		return batchMaster;
	}

	public void setBatchMaster(BatchDetail batchMaster) {
		this.batchMaster = batchMaster;
	}

	public CasbExamMaster getCasbExamMaster() {
		return casbExamMaster;
	}

	public void setCasbExamMaster(CasbExamMaster casbExamMaster) {
		this.casbExamMaster = casbExamMaster;
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

	public CasbSMSReasonMaster getCasbSMSReasonMaster() {
		return casbSMSReasonMaster;
	}

	public void setCasbSMSReasonMaster(CasbSMSReasonMaster casbSMSReasonMaster) {
		this.casbSMSReasonMaster = casbSMSReasonMaster;
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

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
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

	public List<CasbSMSNotSentDetails> getCasbSMSNotSentDetails() {
		return casbSMSNotSentDetails;
	}

	public void setCasbSMSNotSentDetails(List<CasbSMSNotSentDetails> casbSMSNotSentDetails) {
		this.casbSMSNotSentDetails = casbSMSNotSentDetails;
	}

	public List<CasbSMSScheduleDetail> getCasbSMSScheduleDetails() {
		return casbSMSScheduleDetails;
	}

	public void setCasbSMSScheduleDetails(List<CasbSMSScheduleDetail> casbSMSScheduleDetails) {
		this.casbSMSScheduleDetails = casbSMSScheduleDetails;
	}

	public CasbSMSReportDetail getCasbSMSReportDetail() {
		return casbSMSReportDetail;
	}

	public void setCasbSMSReportDetail(CasbSMSReportDetail casbSMSReportDetail) {
		this.casbSMSReportDetail = casbSMSReportDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CasbSMSSent [SMSSentId=" + SMSSentId + ", batchMaster=" + batchMaster + ", casbExamMaster="
				+ casbExamMaster + ", candidateCount=" + candidateCount + ", message=" + message
				+ ", casbSMSReasonMaster=" + casbSMSReasonMaster + ", numberOfSMSUnits=" + numberOfSMSUnits
				+ ", smsStartDatetime=" + smsStartDatetime + ", smsEndDatetime=" + smsEndDatetime + ", smsSentType="
				+ smsSentType + ", smsSentStatus=" + smsSentStatus + ", recordTracking=" + recordTracking + ", reqType="
				+ reqType + ", templateMasterId=" + templateMasterId + ", casbSMSNotSentDetails="
				+ casbSMSNotSentDetails + ", casbSMSScheduleDetails=" + casbSMSScheduleDetails
				+ ", casbSMSReportDetail=" + casbSMSReportDetail + "]";
	}


	public void getHelperScheduler(CasbSMSScheduleDetail casbSMSScheduleDetail) {
		this.casbSMSScheduleDetails.add(casbSMSScheduleDetail);
		casbSMSScheduleDetail.setCasbSMSSent(this);
	}
	
}
