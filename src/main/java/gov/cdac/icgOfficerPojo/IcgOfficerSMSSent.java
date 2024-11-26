package gov.cdac.icgOfficerPojo;

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
@JsonIgnoreProperties({ "SmsNotSentDetail" })
public class IcgOfficerSMSSent implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sms_sent_sms_sent_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_sent_sms_sent_id_seq")
	@Column(name = "sms_sent_id")
    private Long SMSSentId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchMaster batchMaster;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamMaster examMaster;

    @Column(name = "candidates_count")
    private Integer candidateCount;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "sms_reason_master_id")
    private SmsReasonMaster IcgSMSReasonMaster;

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
	
    @OneToMany(mappedBy = "SMSSent")
    private List<SmsNotSentDetail> SMSNotSentDetails;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "smsSent", cascade = CascadeType.ALL)
	private List<IcgOfficerSMSScheduleDetail> SMSScheduleDetails = new ArrayList<>();

	@OneToOne(mappedBy = "icgOfficerSMSSent")
	private IcgOfficerSMSReportDetail SMSReportDetail;
   
	public IcgOfficerSMSSent() {
	}

	public IcgOfficerSMSSent(Long SMSSentId, BatchMaster batchMaster, ExamMaster icgOfficerExamMaster,
			Integer candidateCount, String message, SmsReasonMaster icgOfficerSMSReasonMaster, Integer numberOfSMSUnits,
			Timestamp smsStartDatetime, Timestamp smsEndDatetime, int smsSentType, Boolean smsSentStatus,
			Timestamp recordTracking, String reqType) {
		super();
		this.SMSSentId = SMSSentId;
		this.batchMaster = batchMaster;
		this.examMaster = icgOfficerExamMaster;
		this.candidateCount = candidateCount;
		this.message = message;
		this.IcgSMSReasonMaster = icgOfficerSMSReasonMaster;
		this.numberOfSMSUnits = numberOfSMSUnits;
		this.smsStartDatetime = smsStartDatetime;
		this.smsEndDatetime = smsEndDatetime;
		this.smsSentType = smsSentType;
		this.smsSentStatus = smsSentStatus;
		this.recordTracking = recordTracking;
		this.reqType = reqType;
	}

	public IcgOfficerSMSSent(BatchSummary batchSummary, Integer casbExamMasterId, Integer candidateCount, String message,
			Integer smsReasonMasterId, Boolean smsSentStatus, int smsSentType, String reqType,
			String templateMasterId) {
		this.batchMaster = new BatchMaster(batchSummary.getBatchId());
		this.examMaster = new ExamMaster(casbExamMasterId);
		this.IcgSMSReasonMaster = new SmsReasonMaster(smsReasonMasterId);
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

	public BatchMaster getBatchMaster() {
		return batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

	public ExamMaster getIcgOfficerExamMaster() {
		return this.examMaster;
	}

	public void setIcgOfficerExamMaster(ExamMaster icgOfficerExamMaster) {
		this.examMaster = icgOfficerExamMaster;
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

	public SmsReasonMaster getIcgOfficerSMSReasonMaster() {
		return IcgSMSReasonMaster;
	}

	public void setIcgOfficerSMSReasonMaster(SmsReasonMaster icgOfficerSMSReasonMaster) {
		this.IcgSMSReasonMaster = icgOfficerSMSReasonMaster;
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

	public List<SmsNotSentDetail> getGetIcgOfficerSMSNotSentDetails() {
		return this.getGetIcgOfficerSMSNotSentDetails();
	}

	public void setGetIcgOfficerSMSNotSentDetails(List<SmsNotSentDetail> getIcgOfficerSMSNotSentDetails) {
		this.setGetIcgOfficerSMSNotSentDetails(getIcgOfficerSMSNotSentDetails);
	}

	public List<IcgOfficerSMSScheduleDetail> getIcgOfficerSMSScheduleDetails() {
		return SMSScheduleDetails;
	}

	public void setIcgOfficerSMSScheduleDetails(List<IcgOfficerSMSScheduleDetail> icgOfficerSMSScheduleDetails) {
		this.SMSScheduleDetails = icgOfficerSMSScheduleDetails;
	}

	public IcgOfficerSMSReportDetail getIcgOfficerSMSReportDetail() {
		return SMSReportDetail;
	}

	public void setIcgOfficerSMSReportDetail(IcgOfficerSMSReportDetail icgOfficerSMSReportDetail) {
		this.SMSReportDetail = icgOfficerSMSReportDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void getHelperScheduler(IcgOfficerSMSScheduleDetail icgOfficerSMSScheduleDetail) {
		this.SMSScheduleDetails.add(icgOfficerSMSScheduleDetail);
		icgOfficerSMSScheduleDetail.setSmsSent(this);
	}


}
