package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the request_details database table.
 * 
 */
@Entity
@Table(name="request_details")
@NamedQuery(name="RequestDetail.findAll", query="SELECT r FROM RequestDetail r")
@JsonIgnoreProperties({"requestAttachmentFiles"})
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REQUEST_DETAILS_REQUESTDETAILSID_GENERATOR" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUEST_DETAILS_REQUESTDETAILSID_GENERATOR")
	@Column(name="request_details_id")
	private Integer requestDetailsId;

	@Column(name="atttachment_path")
	private String atttachmentPath;

	@Column(name="candidate_query")
	private String candidateQuery;

	@Column(name="email_sent_datetime")
	private Timestamp emailSentDatetime;

	@Column(name="expected_change_details")
	private String expectedChangeDetails;

	@Column(name="is_attachment")
	private Boolean isAttachment;

	@Column(name="is_email_sent")
	private Boolean isEmailSent;

	@Column(name="is_request_approved")
	private Boolean isRequestApproved;

	@Column(name="is_sms_sent")
	private Boolean isSmsSent;

	@Column(name="reason_for_change")
	private String reasonForChange;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	private String remark;

	@Column(name="request_by")
	private Integer requestBy;

	@Column(name="request_from")
	private String requestFrom;

	@Column(name="request_received_date")
	private Timestamp requestReceivedDate;

	@Column(name="sms_sent_datetime")
	private Timestamp smsSentDatetime;

	//bi-directional many-to-one association to RequestAttachmentFile
	@OneToMany(mappedBy="requestDetail")
	private List<RequestAttachmentFile> requestAttachmentFiles;

	//bi-directional many-to-one association to AllocationChangeLevelMaster
	@ManyToOne
	@JoinColumn(name="allocation_change_level_master_id") 
	private AllocationChangeLevelMaster allocationChangeLevelMaster;
	 

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to RequestCategoryMaster
	@ManyToOne
	@JoinColumn(name="request_category_master_id")
	private RequestCategoryMaster requestCategoryMaster;

	//bi-directional many-to-one association to RequestTypeMaster
	@ManyToOne
	@JoinColumn(name="request_type_master_id")
	private RequestTypeMaster requestTypeMaster;

	public RequestDetail() {
	}

	public Integer getRequestDetailsId() {
		return this.requestDetailsId;
	}

	public void setRequestDetailsId(Integer requestDetailsId) {
		this.requestDetailsId = requestDetailsId;
	}

	public String getAtttachmentPath() {
		return this.atttachmentPath;
	}

	public void setAtttachmentPath(String atttachmentPath) {
		this.atttachmentPath = atttachmentPath;
	}

	public String getCandidateQuery() {
		return this.candidateQuery;
	}

	public void setCandidateQuery(String candidateQuery) {
		this.candidateQuery = candidateQuery;
	}

	public Timestamp getEmailSentDatetime() {
		return this.emailSentDatetime;
	}

	public void setEmailSentDatetime(Timestamp emailSentDatetime) {
		this.emailSentDatetime = emailSentDatetime;
	}

	public String getExpectedChangeDetails() {
		return this.expectedChangeDetails;
	}

	public void setExpectedChangeDetails(String expectedChangeDetails) {
		this.expectedChangeDetails = expectedChangeDetails;
	}

	public Boolean getIsAttachment() {
		return this.isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	public Boolean getIsEmailSent() {
		return this.isEmailSent;
	}

	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	public Boolean getIsRequestApproved() {
		return this.isRequestApproved;
	}

	public void setIsRequestApproved(Boolean isRequestApproved) {
		this.isRequestApproved = isRequestApproved;
	}

	public Boolean getIsSmsSent() {
		return this.isSmsSent;
	}

	public void setIsSmsSent(Boolean isSmsSent) {
		this.isSmsSent = isSmsSent;
	}

	public String getReasonForChange() {
		return this.reasonForChange;
	}

	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRequestBy() {
		return this.requestBy;
	}

	public void setRequestBy(Integer requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestFrom() {
		return this.requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public Timestamp getRequestReceivedDate() {
		return this.requestReceivedDate;
	}

	public void setRequestReceivedDate(Timestamp requestReceivedDate) {
		this.requestReceivedDate = requestReceivedDate;
	}

	public Timestamp getSmsSentDatetime() {
		return this.smsSentDatetime;
	}

	public void setSmsSentDatetime(Timestamp smsSentDatetime) {
		this.smsSentDatetime = smsSentDatetime;
	}

	public List<RequestAttachmentFile> getRequestAttachmentFiles() {
		return this.requestAttachmentFiles;
	}

	public void setRequestAttachmentFiles(List<RequestAttachmentFile> requestAttachmentFiles) {
		this.requestAttachmentFiles = requestAttachmentFiles;
	}

	public RequestAttachmentFile addRequestAttachmentFile(RequestAttachmentFile requestAttachmentFile) {
		getRequestAttachmentFiles().add(requestAttachmentFile);
		requestAttachmentFile.setRequestDetail(this);

		return requestAttachmentFile;
	}

	public RequestAttachmentFile removeRequestAttachmentFile(RequestAttachmentFile requestAttachmentFile) {
		getRequestAttachmentFiles().remove(requestAttachmentFile);
		requestAttachmentFile.setRequestDetail(null);

		return requestAttachmentFile;
	}

	/*
	 * public AllocationChangeLevelMaster getAllocationChangeLevelMaster() { return
	 * this.allocationChangeLevelMaster; }
	 * 
	 * public void setAllocationChangeLevelMaster(AllocationChangeLevelMaster
	 * allocationChangeLevelMaster) { this.allocationChangeLevelMaster =
	 * allocationChangeLevelMaster; }
	 */

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public RequestCategoryMaster getRequestCategoryMaster() {
		return this.requestCategoryMaster;
	}

	public void setRequestCategoryMaster(RequestCategoryMaster requestCategoryMaster) {
		this.requestCategoryMaster = requestCategoryMaster;
	}

	public RequestTypeMaster getRequestTypeMaster() {
		return this.requestTypeMaster;
	}

	public void setRequestTypeMaster(RequestTypeMaster requestTypeMaster) {
		this.requestTypeMaster = requestTypeMaster;
	}

}