package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the applicant_credential database table.
 * 
 */
@Entity
@Table(name="applicant_credential")
@NamedQuery(name="ApplicantCredential.findAll", query="SELECT a FROM ApplicantCredential a")
@JsonIgnoreProperties({"additionalDetails","applicantCentreAllotments","applicantPhase1PreferencesMappings","applicantHalltickets"
	,"applicantPhase2PreferencesMappings","communicationDetails","paymentDetails","bank1RefundDetails","bank2RefundDetails","chargebackDetails",
	"paymentTransactionDetails","personalDetails","qualificationDetails","qualificationMasters",
	"requestAttachmentFiles","requestDetails","applicantPsbFsbSsbDetails","smsNotSentDetails"})

public class ApplicantCredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANTCREDID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANTCREDID_GENERATOR")
	@Column(name="applicant_cred_id")
	private Long applicantCredId;

	@Column(name="application_submitted_datetime")
	private Timestamp applicationSubmittedDatetime;

	@Column(name="duplicate_emailids")
	private String duplicateEmailids;

	private String emailid;
	
	@Column(name="is_email_sentto_rejected_candidate")
	private Boolean isEmailSenttoRejectedCandidate;

	@Column(name="is_rejected")
	private Boolean isRejected;

	private String password;

	@Column(name="password_reset_status")
	private Boolean passwordResetStatus;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="rejected_reason")
	private String rejectedReason;
	
	@Column(name="rejected_reason_in_hindi")
	private String rejectedReasonInHindi;

	//bi-directional many-to-one association to AdditionalDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<AdditionalDetail> additionalDetails;

	//bi-directional many-to-one association to ApplicantCentreAllotment
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantCentreAllotment> applicantCentreAllotments;
	
	//bi-directional many-to-one association to ApplicantType
	@ManyToOne
	@JoinColumn(name="applicant_type_id")
	private ApplicantType applicantType;

	//bi-directional many-to-one association to ApplicantPhase1PreferencesMapping
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantPhase1PreferencesMapping> applicantPhase1PreferencesMappings;
	
	//bi-directional many-to-one association to Bank1RefundDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<Bank1RefundDetail> bank1RefundDetails;

	//bi-directional many-to-one association to Bank2RefundDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<Bank2RefundDetail> bank2RefundDetails;
	
	//bi-directional many-to-one association to ChargebackDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<ChargebackDetail> chargebackDetails;

	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	//bi-directional many-to-one association to RegistrationStatus
	@ManyToOne
	@JoinColumn(name="registration_status_id")
	private RegistrationStatus registrationStatus;

	//bi-directional many-to-one association to ApplicantHallticket
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantHallticket> applicantHalltickets;

	//bi-directional many-to-one association to ApplicantPhase2PreferencesMapping
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings;
	
	//bi-directional many-to-one association to CommunicationDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<CommunicationDetail> communicationDetails;

	//bi-directional many-to-one association to PaymentDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<PaymentDetail> paymentDetails;

	//bi-directional many-to-one association to PaymentTransactionDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<PaymentTransactionDetail> paymentTransactionDetails;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<PersonalDetail> personalDetails;
	
	//bi-directional many-to-one association to QualificationDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<QualificationDetail> qualificationDetails;

	//bi-directional many-to-one association to QualificationMaster
	@OneToMany(mappedBy="applicantCredential")
	private List<QualificationMaster> qualificationMasters;
	
	//bi-directional many-to-one association to RequestAttachmentFile
	@OneToMany(mappedBy="applicantCredential") 
	private List<RequestAttachmentFile> requestAttachmentFiles;
	
	//bi-directional many-to-one association to RequestDetail
	@OneToMany(mappedBy="applicantCredential") 
	private List<RequestDetail>  requestDetails;
	
	//bi-directional many-to-one association to ApplicantPsbFsbSsbDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails;
	
	//bi-directional many-to-one association to SmsNotSentDetail
	@OneToMany(mappedBy="applicantCredential")
	private List<SmsNotSentDetail> smsNotSentDetails;

	public ApplicantCredential() {
	}

	public Long getApplicantCredId() {
		return this.applicantCredId;
	}
	
	public void setApplicantCredId(Long applicantCredId) {
		this.applicantCredId = applicantCredId;
	}

	public ApplicantCredential(Long applicantCredId) {
		this.applicantCredId = applicantCredId;
	}

	public Timestamp getApplicationSubmittedDatetime() {
		return this.applicationSubmittedDatetime;
	}

	public void setApplicationSubmittedDatetime(Timestamp applicationSubmittedDatetime) {
		this.applicationSubmittedDatetime = applicationSubmittedDatetime;
	}

	public String getDuplicateEmailids() {
		return this.duplicateEmailids;
	}

	public void setDuplicateEmailids(String duplicateEmailids) {
		this.duplicateEmailids = duplicateEmailids;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Boolean getIsRejected() {
		return this.isRejected;
	}

	public void setIsRejected(Boolean isRejected) {
		this.isRejected = isRejected;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPasswordResetStatus() {
		return this.passwordResetStatus;
	}

	public void setPasswordResetStatus(Boolean passwordResetStatus) {
		this.passwordResetStatus = passwordResetStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRejectedReason() {
		return this.rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public List<AdditionalDetail> getAdditionalDetails() {
		return this.additionalDetails;
	}

	public void setAdditionalDetails(List<AdditionalDetail> additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public AdditionalDetail addAdditionalDetail(AdditionalDetail additionalDetail) {
		getAdditionalDetails().add(additionalDetail);
		additionalDetail.setApplicantCredential(this);

		return additionalDetail;
	}

	public AdditionalDetail removeAdditionalDetail(AdditionalDetail additionalDetail) {
		getAdditionalDetails().remove(additionalDetail);
		additionalDetail.setApplicantCredential(null);

		return additionalDetail;
	}

	public List<ApplicantCentreAllotment> getApplicantCentreAllotments() {
		return this.applicantCentreAllotments;
	}

	public void setApplicantCentreAllotments(List<ApplicantCentreAllotment> applicantCentreAllotments) {
		this.applicantCentreAllotments = applicantCentreAllotments;
	}

	public ApplicantCentreAllotment addApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().add(applicantCentreAllotment);
		applicantCentreAllotment.setApplicantCredential(this);

		return applicantCentreAllotment;
	}

	public ApplicantCentreAllotment removeApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().remove(applicantCentreAllotment);
		applicantCentreAllotment.setApplicantCredential(null);

		return applicantCentreAllotment;
	}

	public List<ApplicantPhase1PreferencesMapping> getApplicantPhase1PreferencesMappings() {
		return applicantPhase1PreferencesMappings;
	}

	public void setApplicantPhase1PreferencesMappings(
			List<ApplicantPhase1PreferencesMapping> applicantPhase1PreferencesMappings) {
		this.applicantPhase1PreferencesMappings = applicantPhase1PreferencesMappings;
	}

	public ApplicantPhase1PreferencesMapping addApplicantPhase1PreferencesMapping(ApplicantPhase1PreferencesMapping applicantPhase1PreferencesMapping) {
		getApplicantPhase1PreferencesMappings().add(applicantPhase1PreferencesMapping);
		applicantPhase1PreferencesMapping.setApplicantCredential(this);

		return applicantPhase1PreferencesMapping;
	}

	public ApplicantPhase1PreferencesMapping removeApplicantPhase1PreferencesMapping(ApplicantPhase1PreferencesMapping applicantPhase1PreferencesMapping) {
		getApplicantPhase1PreferencesMappings().remove(applicantPhase1PreferencesMapping);
		applicantPhase1PreferencesMapping.setApplicantCredential(null);

		return applicantPhase1PreferencesMapping;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

	public RegistrationStatus getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public List<ApplicantHallticket> getApplicantHalltickets() {
		return this.applicantHalltickets;
	}

	public void setApplicantHalltickets(List<ApplicantHallticket> applicantHalltickets) {
		this.applicantHalltickets = applicantHalltickets;
	}

	public ApplicantHallticket addApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().add(applicantHallticket);
		applicantHallticket.setApplicantCredential(this);

		return applicantHallticket;
	}

	public ApplicantHallticket removeApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().remove(applicantHallticket);
		applicantHallticket.setApplicantCredential(null);

		return applicantHallticket;
	}

	public List<ApplicantPhase2PreferencesMapping> getApplicantPhase2PreferencesMappings() {
		return this.applicantPhase2PreferencesMappings;
	}

	public void setApplicantPhase2PreferencesMappings(List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings) {
		this.applicantPhase2PreferencesMappings = applicantPhase2PreferencesMappings;
	}

	public ApplicantPhase2PreferencesMapping addApplicantPhase2PreferencesMapping(ApplicantPhase2PreferencesMapping applicantPhase2PreferencesMapping) {
		getApplicantPhase2PreferencesMappings().add(applicantPhase2PreferencesMapping);
		applicantPhase2PreferencesMapping.setApplicantCredential(this);

		return applicantPhase2PreferencesMapping;
	}

	public ApplicantPhase2PreferencesMapping removeApplicantPhase2PreferencesMapping(ApplicantPhase2PreferencesMapping applicantPhase2PreferencesMapping) {
		getApplicantPhase2PreferencesMappings().remove(applicantPhase2PreferencesMapping);
		applicantPhase2PreferencesMapping.setApplicantCredential(null);

		return applicantPhase2PreferencesMapping;
	}

	public List<CommunicationDetail> getCommunicationDetails() {
		return this.communicationDetails;
	}

	public void setCommunicationDetails(List<CommunicationDetail> communicationDetails) {
		this.communicationDetails = communicationDetails;
	}

	public CommunicationDetail addCommunicationDetail(CommunicationDetail communicationDetail) {
		getCommunicationDetails().add(communicationDetail);
		communicationDetail.setApplicantCredential(this);

		return communicationDetail;
	}

	public CommunicationDetail removeCommunicationDetail(CommunicationDetail communicationDetail) {
		getCommunicationDetails().remove(communicationDetail);
		communicationDetail.setApplicantCredential(null);

		return communicationDetail;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return this.paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public PaymentDetail addPaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().add(paymentDetail);
		paymentDetail.setApplicantCredential(this);

		return paymentDetail;
	}

	public PaymentDetail removePaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().remove(paymentDetail);
		paymentDetail.setApplicantCredential(null);

		return paymentDetail;
	}

	public List<PaymentTransactionDetail> getPaymentTransactionDetails() {
		return this.paymentTransactionDetails;
	}

	public void setPaymentTransactionDetails(List<PaymentTransactionDetail> paymentTransactionDetails) {
		this.paymentTransactionDetails = paymentTransactionDetails;
	}

	public PaymentTransactionDetail addPaymentTransactionDetail(PaymentTransactionDetail paymentTransactionDetail) {
		getPaymentTransactionDetails().add(paymentTransactionDetail);
		paymentTransactionDetail.setApplicantCredential(this);

		return paymentTransactionDetail;
	}

	public PaymentTransactionDetail removePaymentTransactionDetail(PaymentTransactionDetail paymentTransactionDetail) {
		getPaymentTransactionDetails().remove(paymentTransactionDetail);
		paymentTransactionDetail.setApplicantCredential(null);

		return paymentTransactionDetail;
	}

	public List<PersonalDetail> getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setApplicantCredential(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setApplicantCredential(null);

		return personalDetail;
	}

	public List<QualificationDetail> getQualificationDetails() {
		return this.qualificationDetails;
	}

	public void setQualificationDetails(List<QualificationDetail> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}

	public QualificationDetail addQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().add(qualificationDetail);
		qualificationDetail.setApplicantCredential(this);

		return qualificationDetail;
	}

	public QualificationDetail removeQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().remove(qualificationDetail);
		qualificationDetail.setApplicantCredential(null);

		return qualificationDetail;
	}

	public List<QualificationMaster> getQualificationMasters() {
		return this.qualificationMasters;
	}

	public void setQualificationMasters(List<QualificationMaster> qualificationMasters) {
		this.qualificationMasters = qualificationMasters;
	}

	public QualificationMaster addQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().add(qualificationMaster);
		qualificationMaster.setApplicantCredential(this);

		return qualificationMaster;
	}

	public QualificationMaster removeQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().remove(qualificationMaster);
		qualificationMaster.setApplicantCredential(null);

		return qualificationMaster;
	}

	public List<RequestAttachmentFile> getRequestAttachmentFiles() {
		return this.requestAttachmentFiles;
	}

	public void setRequestAttachmentFiles(List<RequestAttachmentFile> requestAttachmentFiles) {
		this.requestAttachmentFiles = requestAttachmentFiles;
	}

	public RequestAttachmentFile addRequestAttachmentFile(RequestAttachmentFile requestAttachmentFile) {
		getRequestAttachmentFiles().add(requestAttachmentFile);
		requestAttachmentFile.setApplicantCredential(this);

		return requestAttachmentFile;
	}

	public RequestAttachmentFile removeRequestAttachmentFile(RequestAttachmentFile requestAttachmentFile) {
		getRequestAttachmentFiles().remove(requestAttachmentFile);
		requestAttachmentFile.setApplicantCredential(null);

		return requestAttachmentFile;
	}

	public List<RequestDetail> getRequestDetails() {
		return this.requestDetails;
	}

	public void setRequestDetails(List<RequestDetail> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public RequestDetail addRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().add(requestDetail);
		requestDetail.setApplicantCredential(this);

		return requestDetail;
	}

	public RequestDetail removeRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().remove(requestDetail);
		requestDetail.setApplicantCredential(null);

		return requestDetail;
	}

	public Boolean getIsEmailSenttoRejectedCandidate() {
		return isEmailSenttoRejectedCandidate;
	}

	public void setIsEmailSenttoRejectedCandidate(Boolean isEmailSenttoRejectedCandidate) {
		this.isEmailSenttoRejectedCandidate = isEmailSenttoRejectedCandidate;
	}

	public String getRejectedReasonInHindi() {
		return rejectedReasonInHindi;
	}

	public void setRejectedReasonInHindi(String rejectedReasonInHindi) {
		this.rejectedReasonInHindi = rejectedReasonInHindi;
	}

	public ApplicantType getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(ApplicantType applicantType) {
		this.applicantType = applicantType;
	}

	public List<Bank1RefundDetail> getBank1RefundDetails() {
		return bank1RefundDetails;
	}

	public void setBank1RefundDetails(List<Bank1RefundDetail> bank1RefundDetails) {
		this.bank1RefundDetails = bank1RefundDetails;
	}

	public List<Bank2RefundDetail> getBank2RefundDetails() {
		return bank2RefundDetails;
	}

	public void setBank2RefundDetails(List<Bank2RefundDetail> bank2RefundDetails) {
		this.bank2RefundDetails = bank2RefundDetails;
	}

	public List<ChargebackDetail> getChargebackDetails() {
		return chargebackDetails;
	}

	public void setChargebackDetails(List<ChargebackDetail> chargebackDetails) {
		this.chargebackDetails = chargebackDetails;
	}

	public List<ApplicantPsbFsbSsbDetail> getApplicantPsbFsbSsbDetails() {
		return applicantPsbFsbSsbDetails;
	}

	public void setApplicantPsbFsbSsbDetails(List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails) {
		this.applicantPsbFsbSsbDetails = applicantPsbFsbSsbDetails;
	}

	public List<SmsNotSentDetail> getSmsNotSentDetails() {
		return smsNotSentDetails;
	}

	public void setSmsNotSentDetails(List<SmsNotSentDetail> smsNotSentDetails) {
		this.smsNotSentDetails = smsNotSentDetails;
	}
	
	

}