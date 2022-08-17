package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties({"applicantCentreAllotments,applicantCityPreferencesMappings,applicantHalltickets,paymentDetails,personalDetails"})
public class ApplicantCredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="applicant_cred_id")
	private Long applicantCredId;

	@Column(name="application_submitted_datetime")
	private Timestamp applicationSubmittedDatetime;

	@Column(name="batch_id")
	private Integer batchId;

	@Column(name="duplicate_emailids")
	private String duplicateEmailids;

	private String emailid;

	@Column(name="is_rejected")
	private Boolean isRejected;

	private String password;

	@Column(name="password_reset_status")
	private Boolean passwordResetStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="registration_status_id")
	private Integer registrationStatusId;

	@Column(name="rejected_reason")
	private String rejectedReason;
	
	@Column(name = "rejected_reason_hindi")
	private String rejectedReasonHindi;
	
//	@Column(name = "is_emailsentto_duplicate_candidates")
//	private Boolean isEmailSentToDuplicateCandidates;
	
	@Column(name = "is_email_sentto_rejected_candidate")
	private Boolean isEmailSentToDuplicateCandidates;

	//bi-directional many-to-one association to ApplicantCentreAllotment
	@JsonIgnore
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantCentreAllotment> applicantCentreAllotments;

	//bi-directional many-to-one association to ApplicantCityPreferencesMapping
	@JsonIgnore
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantCityPreferencesMapping> applicantCityPreferencesMappings;

	//bi-directional many-to-one association to ApplicantHallticket
	@JsonIgnore
	@OneToMany(mappedBy="applicantCredential")
	private List<ApplicantHallticket> applicantHalltickets;

	//bi-directional many-to-one association to PaymentDetail
	@JsonIgnore
	@OneToMany(mappedBy="applicantCredential")
	private List<PaymentDetail> paymentDetails;

	//bi-directional many-to-one association to PersonalDetail
	@JsonIgnore
	@OneToMany(mappedBy="applicantCredential")
	private List<PersonalDetail> personalDetails;

	public ApplicantCredential() {
	}

    public ApplicantCredential(Long applicantCredId) {
	this.applicantCredId = applicantCredId;
    }

	public Long getApplicantCredId() {
		return this.applicantCredId;
	}

	public void setApplicantCredId(Long applicantCredId) {
		this.applicantCredId = applicantCredId;
	}

	public Timestamp getApplicationSubmittedDatetime() {
		return this.applicationSubmittedDatetime;
	}

	public void setApplicationSubmittedDatetime(Timestamp applicationSubmittedDatetime) {
		this.applicationSubmittedDatetime = applicationSubmittedDatetime;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
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

	public Integer getRegistrationStatusId() {
		return this.registrationStatusId;
	}

	public void setRegistrationStatusId(Integer registrationStatusId) {
		this.registrationStatusId = registrationStatusId;
	}

	public String getRejectedReason() {
		return this.rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
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

	public List<ApplicantCityPreferencesMapping> getApplicantCityPreferencesMappings() {
		return this.applicantCityPreferencesMappings;
	}

	public void setApplicantCityPreferencesMappings(List<ApplicantCityPreferencesMapping> applicantCityPreferencesMappings) {
		this.applicantCityPreferencesMappings = applicantCityPreferencesMappings;
	}

	public ApplicantCityPreferencesMapping addApplicantCityPreferencesMapping(ApplicantCityPreferencesMapping applicantCityPreferencesMapping) {
		getApplicantCityPreferencesMappings().add(applicantCityPreferencesMapping);
		applicantCityPreferencesMapping.setApplicantCredential(this);

		return applicantCityPreferencesMapping;
	}

	public ApplicantCityPreferencesMapping removeApplicantCityPreferencesMapping(ApplicantCityPreferencesMapping applicantCityPreferencesMapping) {
		getApplicantCityPreferencesMappings().remove(applicantCityPreferencesMapping);
		applicantCityPreferencesMapping.setApplicantCredential(null);

		return applicantCityPreferencesMapping;
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

	

}