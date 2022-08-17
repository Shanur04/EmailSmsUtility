package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the personal_details database table.
 * 
 */
@Entity
@Table(name="personal_details")
@NamedQuery(name="PersonalDetail.findAll", query="SELECT p FROM PersonalDetail p")
@JsonIgnoreProperties({"paymentDetails"})
public class PersonalDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="personal_details_id")
	private Long personalDetailsId;

	@Column(name="aadhaar_card_number")
	private String aadhaarCardNumber;

	@Column(name="aadhaar_card_number_consent_accept")
	private Boolean aadhaarCardNumberConsentAccept;

	@Column(name="age_relaxation")
	private Boolean ageRelaxation;

	@Column(name="alternate_mobile")
	private String alternateMobile;

	@Column(name="candidate_employment_experience")
	private String candidateEmploymentExperience;

	@Column(name="candidate_employment_org")
	private String candidateEmploymentOrg;

	@Column(name="candidate_employment_type_id")
	private Integer candidateEmploymentTypeId;

	@Column(name="community_certificate_path")
	private String communityCertificatePath;

	@Column(name="date_of_birth")
	private Timestamp dateOfBirth;

	@Column(name="date_of_birth_proof_path")
	private String dateOfBirthProofPath;

	@Column(name="date_of_issue_community_certificate")
	private Timestamp dateOfIssueCommunityCertificate;

	@Column(name="disclaimer_accepted")
	private Boolean disclaimerAccepted;

	@Column(name="dob_certificate_type_id")
	private Integer dobCertificateTypeId;

	@Column(name="education_level_category_id")
	private Integer educationLevelCategoryId;

	@Column(name="emailid_primary")
	private String emailidPrimary;

	@Column(name="emailid_secondary")
	private String emailidSecondary;

	@Column(name="father_name")
	private String fatherName;

	@Column(name="fee_waiver_claimed")
	private Boolean feeWaiverClaimed;

	private String gender;

	@Column(name="icg_employment_type_id")
	private Integer icgEmploymentTypeId;

	@Column(name="icg_post_combination_id")
	private Integer icgPostCombinationId;

	@Column(name="icg_present_unit")
	private String icgPresentUnit;

	@Column(name="icg_service_number")
	private String icgServiceNumber;

	@Column(name="identification_mark")
	private String identificationMark;

	@Column(name="identification_mark2")
	private String identificationMark2;

	@Column(name="is_disclaimer_for_promotions")
	private Boolean isDisclaimerForPromotions;

	@Column(name="is_phase1withinrange")
	private Boolean isPhase1withinrange;

	@Column(name="is_seatallocation_done")
	private Boolean isSeatallocationDone;

	@Column(name="marital_status")
	private String maritalStatus;
	
	@ColumnTransformer(forColumn = "mobile", read = "pgp_sym_decrypt(mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")			
	private String mobile;

	@Column(name="mother_name")
	private String motherName;

	private String name;

	private String nationality;

	@Column(name="parent_signature_path")
	private String parentSignaturePath;

	@Column(name="photo_identity_card_number")
	private String photoIdentityCardNumber;

	@Column(name="photo_identity_card_type_master_id")
	private Integer photoIdentityCardTypeMasterId;

	@Column(name="photo_identity_path")
	private String photoIdentityPath;

	@Column(name="photo_path")
	private String photoPath;
	
	@Column(name="registration_no")
	private String registrationNo;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="renewed_caste_certificate_path")
	private String renewedCasteCertificatePath;

	@Column(name="self_declaration_date")
	private Timestamp selfDeclarationDate;

	@Column(name="self_declaration_form_path")
	private String selfDeclarationFormPath;

	@Column(name="self_declaration_place")
	private String selfDeclarationPlace;

	@Column(name="service_certificate_path")
	private String serviceCertificatePath;

	@Column(name="signature_path")
	private String signaturePath;

	@Column(name="thumb_impression_path")
	private String thumbImpressionPath;

	@Column(name="validity_of_community_certificate")
	private Timestamp validityOfCommunityCertificate;

	//bi-directional many-to-one association to PaymentDetail
	@OneToMany(mappedBy="personalDetail")
	private List<PaymentDetail> paymentDetails;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to CasteCategoryDetail
	@ManyToOne
	@JoinColumn(name="category_id")
	private CasteCategoryDetail casteCategoryDetail;
 
	public PersonalDetail() {
	}       

	public String getRegistrationNo() {
		return registrationNo;
	} 

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Long getPersonalDetailsId() {
		return this.personalDetailsId;
	}

	public void setPersonalDetailsId(Long personalDetailsId) {
		this.personalDetailsId = personalDetailsId;
	}

	public String getAadhaarCardNumber() {
		return this.aadhaarCardNumber;
	}

	public void setAadhaarCardNumber(String aadhaarCardNumber) {
		this.aadhaarCardNumber = aadhaarCardNumber;
	}

	public Boolean getAadhaarCardNumberConsentAccept() {
		return this.aadhaarCardNumberConsentAccept;
	}

	public void setAadhaarCardNumberConsentAccept(Boolean aadhaarCardNumberConsentAccept) {
		this.aadhaarCardNumberConsentAccept = aadhaarCardNumberConsentAccept;
	}

	public Boolean getAgeRelaxation() {
		return this.ageRelaxation;
	}

	public void setAgeRelaxation(Boolean ageRelaxation) {
		this.ageRelaxation = ageRelaxation;
	}

	public String getAlternateMobile() {
		return this.alternateMobile;
	}

	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	public String getCandidateEmploymentExperience() {
		return this.candidateEmploymentExperience;
	}

	public void setCandidateEmploymentExperience(String candidateEmploymentExperience) {
		this.candidateEmploymentExperience = candidateEmploymentExperience;
	}

	public String getCandidateEmploymentOrg() {
		return this.candidateEmploymentOrg;
	}

	public void setCandidateEmploymentOrg(String candidateEmploymentOrg) {
		this.candidateEmploymentOrg = candidateEmploymentOrg;
	}

	public Integer getCandidateEmploymentTypeId() {
		return this.candidateEmploymentTypeId;
	}

	public void setCandidateEmploymentTypeId(Integer candidateEmploymentTypeId) {
		this.candidateEmploymentTypeId = candidateEmploymentTypeId;
	}

	public String getCommunityCertificatePath() {
		return this.communityCertificatePath;
	}

	public void setCommunityCertificatePath(String communityCertificatePath) {
		this.communityCertificatePath = communityCertificatePath;
	}

	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirthProofPath() {
		return this.dateOfBirthProofPath;
	}

	public void setDateOfBirthProofPath(String dateOfBirthProofPath) {
		this.dateOfBirthProofPath = dateOfBirthProofPath;
	}

	public Timestamp getDateOfIssueCommunityCertificate() {
		return this.dateOfIssueCommunityCertificate;
	}

	public void setDateOfIssueCommunityCertificate(Timestamp dateOfIssueCommunityCertificate) {
		this.dateOfIssueCommunityCertificate = dateOfIssueCommunityCertificate;
	}

	public Boolean getDisclaimerAccepted() {
		return this.disclaimerAccepted;
	}

	public void setDisclaimerAccepted(Boolean disclaimerAccepted) {
		this.disclaimerAccepted = disclaimerAccepted;
	}

	public Integer getDobCertificateTypeId() {
		return this.dobCertificateTypeId;
	}

	public void setDobCertificateTypeId(Integer dobCertificateTypeId) {
		this.dobCertificateTypeId = dobCertificateTypeId;
	}

	public Integer getEducationLevelCategoryId() {
		return this.educationLevelCategoryId;
	}

	public void setEducationLevelCategoryId(Integer educationLevelCategoryId) {
		this.educationLevelCategoryId = educationLevelCategoryId;
	}

	public String getEmailidPrimary() {
		return this.emailidPrimary;
	}

	public void setEmailidPrimary(String emailidPrimary) {
		this.emailidPrimary = emailidPrimary;
	}

	public String getEmailidSecondary() {
		return this.emailidSecondary;
	}

	public void setEmailidSecondary(String emailidSecondary) {
		this.emailidSecondary = emailidSecondary;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Boolean getFeeWaiverClaimed() {
		return this.feeWaiverClaimed;
	}

	public void setFeeWaiverClaimed(Boolean feeWaiverClaimed) {
		this.feeWaiverClaimed = feeWaiverClaimed;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getIcgEmploymentTypeId() {
		return this.icgEmploymentTypeId;
	}

	public void setIcgEmploymentTypeId(Integer icgEmploymentTypeId) {
		this.icgEmploymentTypeId = icgEmploymentTypeId;
	}

	public Integer getIcgPostCombinationId() {
		return this.icgPostCombinationId;
	}

	public void setIcgPostCombinationId(Integer icgPostCombinationId) {
		this.icgPostCombinationId = icgPostCombinationId;
	}

	public String getIcgPresentUnit() {
		return this.icgPresentUnit;
	}

	public void setIcgPresentUnit(String icgPresentUnit) {
		this.icgPresentUnit = icgPresentUnit;
	}

	public String getIcgServiceNumber() {
		return this.icgServiceNumber;
	}

	public void setIcgServiceNumber(String icgServiceNumber) {
		this.icgServiceNumber = icgServiceNumber;
	}

	public String getIdentificationMark() {
		return this.identificationMark;
	}

	public void setIdentificationMark(String identificationMark) {
		this.identificationMark = identificationMark;
	}

	public String getIdentificationMark2() {
		return this.identificationMark2;
	}

	public void setIdentificationMark2(String identificationMark2) {
		this.identificationMark2 = identificationMark2;
	}

	public Boolean getIsDisclaimerForPromotions() {
		return this.isDisclaimerForPromotions;
	}

	public void setIsDisclaimerForPromotions(Boolean isDisclaimerForPromotions) {
		this.isDisclaimerForPromotions = isDisclaimerForPromotions;
	}

	public Boolean getIsPhase1withinrange() {
		return this.isPhase1withinrange;
	}

	public void setIsPhase1withinrange(Boolean isPhase1withinrange) {
		this.isPhase1withinrange = isPhase1withinrange;
	}

	public Boolean getIsSeatallocationDone() {
		return this.isSeatallocationDone;
	}

	public void setIsSeatallocationDone(Boolean isSeatallocationDone) {
		this.isSeatallocationDone = isSeatallocationDone;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getParentSignaturePath() {
		return this.parentSignaturePath;
	}

	public void setParentSignaturePath(String parentSignaturePath) {
		this.parentSignaturePath = parentSignaturePath;
	}

	public String getPhotoIdentityCardNumber() {
		return this.photoIdentityCardNumber;
	}

	public void setPhotoIdentityCardNumber(String photoIdentityCardNumber) {
		this.photoIdentityCardNumber = photoIdentityCardNumber;
	}

	public Integer getPhotoIdentityCardTypeMasterId() {
		return this.photoIdentityCardTypeMasterId;
	}

	public void setPhotoIdentityCardTypeMasterId(Integer photoIdentityCardTypeMasterId) {
		this.photoIdentityCardTypeMasterId = photoIdentityCardTypeMasterId;
	}

	public String getPhotoIdentityPath() {
		return this.photoIdentityPath;
	}

	public void setPhotoIdentityPath(String photoIdentityPath) {
		this.photoIdentityPath = photoIdentityPath;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRenewedCasteCertificatePath() {
		return this.renewedCasteCertificatePath;
	}

	public void setRenewedCasteCertificatePath(String renewedCasteCertificatePath) {
		this.renewedCasteCertificatePath = renewedCasteCertificatePath;
	}

	public Timestamp getSelfDeclarationDate() {
		return this.selfDeclarationDate;
	}

	public void setSelfDeclarationDate(Timestamp selfDeclarationDate) {
		this.selfDeclarationDate = selfDeclarationDate;
	}

	public String getSelfDeclarationFormPath() {
		return this.selfDeclarationFormPath;
	}

	public void setSelfDeclarationFormPath(String selfDeclarationFormPath) {
		this.selfDeclarationFormPath = selfDeclarationFormPath;
	}

	public String getSelfDeclarationPlace() {
		return this.selfDeclarationPlace;
	}

	public void setSelfDeclarationPlace(String selfDeclarationPlace) {
		this.selfDeclarationPlace = selfDeclarationPlace;
	}

	public String getServiceCertificatePath() {
		return this.serviceCertificatePath;
	}

	public void setServiceCertificatePath(String serviceCertificatePath) {
		this.serviceCertificatePath = serviceCertificatePath;
	}

	public String getSignaturePath() {
		return this.signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public String getThumbImpressionPath() {
		return this.thumbImpressionPath;
	}

	public void setThumbImpressionPath(String thumbImpressionPath) {
		this.thumbImpressionPath = thumbImpressionPath;
	}

	public Timestamp getValidityOfCommunityCertificate() {
		return this.validityOfCommunityCertificate;
	}

	public void setValidityOfCommunityCertificate(Timestamp validityOfCommunityCertificate) {
		this.validityOfCommunityCertificate = validityOfCommunityCertificate;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return this.paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public PaymentDetail addPaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().add(paymentDetail);
		paymentDetail.setPersonalDetail(this);

		return paymentDetail;
	}

	public PaymentDetail removePaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().remove(paymentDetail);
		paymentDetail.setPersonalDetail(null);

		return paymentDetail;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public CasteCategoryDetail getCasteCategoryDetail() {
		return this.casteCategoryDetail;
	}

	public void setCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		this.casteCategoryDetail = casteCategoryDetail;
	}

	@Override
	public String toString() {
		return "PersonalDetail [personalDetailsId=" + personalDetailsId + ", aadhaarCardNumber=" + aadhaarCardNumber
				+ ", aadhaarCardNumberConsentAccept=" + aadhaarCardNumberConsentAccept + ", ageRelaxation="
				+ ageRelaxation + ", alternateMobile=" + alternateMobile + ", candidateEmploymentExperience="
				+ candidateEmploymentExperience + ", candidateEmploymentOrg=" + candidateEmploymentOrg
				+ ", candidateEmploymentTypeId=" + candidateEmploymentTypeId + ", communityCertificatePath="
				+ communityCertificatePath + ", dateOfBirth=" + dateOfBirth + ", dateOfBirthProofPath="
				+ dateOfBirthProofPath + ", dateOfIssueCommunityCertificate=" + dateOfIssueCommunityCertificate
				+ ", disclaimerAccepted=" + disclaimerAccepted + ", dobCertificateTypeId=" + dobCertificateTypeId
				+ ", educationLevelCategoryId=" + educationLevelCategoryId + ", emailidPrimary=" + emailidPrimary
				+ ", emailidSecondary=" + emailidSecondary + ", fatherName=" + fatherName + ", feeWaiverClaimed="
				+ feeWaiverClaimed + ", gender=" + gender + ", icgEmploymentTypeId=" + icgEmploymentTypeId
				+ ", icgPostCombinationId=" + icgPostCombinationId + ", icgPresentUnit=" + icgPresentUnit
				+ ", icgServiceNumber=" + icgServiceNumber + ", identificationMark=" + identificationMark
				+ ", identificationMark2=" + identificationMark2 + ", isDisclaimerForPromotions="
				+ isDisclaimerForPromotions + ", isPhase1withinrange=" + isPhase1withinrange + ", isSeatallocationDone="
				+ isSeatallocationDone + ", maritalStatus=" + maritalStatus + ", mobile=" + mobile + ", motherName="
				+ motherName + ", name=" + name + ", nationality=" + nationality + ", parentSignaturePath="
				+ parentSignaturePath + ", photoIdentityCardNumber=" + photoIdentityCardNumber
				+ ", photoIdentityCardTypeMasterId=" + photoIdentityCardTypeMasterId + ", photoIdentityPath="
				+ photoIdentityPath + ", photoPath=" + photoPath + ", registrationNo=" + registrationNo
				+ ", recordTracking=" + recordTracking + ", renewedCasteCertificatePath=" + renewedCasteCertificatePath
				+ ", selfDeclarationDate=" + selfDeclarationDate + ", selfDeclarationFormPath="
				+ selfDeclarationFormPath + ", selfDeclarationPlace=" + selfDeclarationPlace
				+ ", serviceCertificatePath=" + serviceCertificatePath + ", signaturePath=" + signaturePath
				+ ", thumbImpressionPath=" + thumbImpressionPath + ", validityOfCommunityCertificate="
				+ validityOfCommunityCertificate + ", paymentDetails=" + paymentDetails + ", applicantCredential="
				+ applicantCredential + ", casteCategoryDetail=" + casteCategoryDetail + "]";
	}
	
	

}