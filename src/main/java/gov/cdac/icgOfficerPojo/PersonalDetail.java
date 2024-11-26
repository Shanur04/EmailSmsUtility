package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the personal_details database table.
 * 
 */
@Entity
@Table(name = "personal_details")
@NamedQuery(name = "PersonalDetail.findAll", query = "SELECT p FROM PersonalDetail p")
@JsonIgnoreProperties({"paymentDetails"})
public class PersonalDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERSONALDETAILSID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONALDETAILSID_GENERATOR")
	@Column(name = "personal_details_id")
	private Long personalDetailsId;

	@Column(name = "aadhaar_card_number")
	private String aadhaarCardNumber;

	@Column(name = "aadhaar_card_number_consent_accept")
	private Boolean aadhaarCardNumberConsentAccept;

	@Column(name = "age_relaxation")
	private Boolean ageRelaxation;

	@ColumnTransformer(forColumn = "alternate_mobile", read = "pgp_sym_decrypt(alternate_mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')", write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name = "alternate_mobile")
	private String alternateMobile;

	@Column(name = "candidate_employment_experience")
	private String candidateEmploymentExperience;

	@Column(name = "candidate_employment_org")
	private String candidateEmploymentOrg;

	@Column(name = "community_certificate_path")
	private String communityCertificatePath;

	@Column(name = "community_certificate_validity_type")
	private String communityCertificateValidityType;

	@Column(name = "cpl_date_of_issue")
	private Timestamp cplDateOfIssue;

	@Column(name = "cpl_issuing_authority")
	private String cplIssuingAuthority;

	@Column(name = "cpl_number")
	private String cplNumber;

	@Column(name = "cpl_path")
	private String cplPath;

	@Column(name = "cpl_validity_of_license")
	private Timestamp cplValidityOfLicense;

	@Column(name = "date_of_birth")
	private Timestamp dateOfBirth;

	@Column(name = "date_of_birth_proof_path")
	private String dateOfBirthProofPath;

	@Column(name = "date_of_issue_community_certificate")
	private Timestamp dateOfIssueCommunityCertificate;

	@Column(name = "disclaimer_accepted")
	private Boolean disclaimerAccepted;

	@Column(name = "emailid_primary")
	private String emailidPrimary;

	@Column(name = "emailid_secondary")
	private String emailidSecondary;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "fee_waiver_claimed")
	private Boolean feeWaiverClaimed;

	@Column(name = "gender")
	private String gender;

	@Column(name = "icg_present_unit")
	private String icgPresentUnit;

	@Column(name = "icg_service_number")
	private String icgServiceNumber;

	@Column(name = "identification_mark1")
	private String identificationMark1;

	@Column(name = "identification_mark2")
	private String identificationMark2;

	@Column(name = "is_disclaimer_for_promotions")
	private Boolean isDisclaimerForPromotions;

	@Column(name = "is_fsb_admitcard_downloaded")
	private Boolean isFsbAdmitcardDownloaded;

	@Column(name = "is_fsb_attempted")
	private Boolean isFsbAttempted;

	@Column(name = "is_pabt_qualified")
	private String isPabtQualified;

	@Column(name = "is_personal_info_confirmed")
	private Boolean isPersonalInfoConfirmed;

	@Column(name = "is_psb_admitcard_downloaded")
	private Boolean isPsbAdmitcardDownloaded;

	@Column(name = "is_psb_attempted")
	private Boolean isPsbAttempted;

	@Column(name = "is_ssb_attempted")
	private Boolean isSsbAttempted;

	@Column(name = "left_thumb_impression_path")
	private String leftThumbImpressionPath;

	@Column(name = "marital_status")
	private String maritalStatus;

	@ColumnTransformer(forColumn = "mobile", read = "pgp_sym_decrypt(mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')", write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "name")
	private String name;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "photo_identity_path")
	private String photoIdentityPath;

	@Column(name = "pabt_qualified_batch")
	private String pabtQualifiedBatch;

	@Column(name = "pabt_qualified_year")
	private String pabtQualifiedYear;

	@Column(name = "pabt_roll_or_batch_number")
	private String pabtRollOrBatchNumber;

	@Column(name = "photo_identity_card_number")
	private String photoIdentityCardNumber;

	@Column(name = "photo_path")
	private String photoPath;

	@Column(name = "renewed_caste_certificate_path")
	private String renewedCasteCertificatePath;

	@Column(name = "right_thumb_impression_path")
	private String rightThumbImpressionPath;

	@Column(name = "roll_count")
	private Long rollCount;

	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	@Column(name = "self_declaration_date")
	private Timestamp selfDeclarationDate;

	@Column(name = "self_declaration_form_path")
	private String selfDeclarationFormPath;

	@Column(name = "self_declaration_place")
	private String selfDeclarationPlace;

	@Column(name = "service_certificate_path")
	private String serviceCertificatePath;

	@Column(name = "service_noc_certificate_path")
	private String serviceNocCertificatePath;

	@Column(name = "signature_path")
	private String signaturePath;

	@Column(name = "validity_of_community_certificate")
	private Timestamp validityOfCommunityCertificate;
	
	@Column(name = "govt_sector_servicerank")
	private String govtSectorServiceRank;
	
	@Column(name = "matriculation_certificate_path")
	private String matriculationCertificatePath;

	@Column(name = "written_test_section_master_id")
	private Integer writtenTestSectionMasterId;

	// bi-directional many-to-one association to PaymentDetail
	@OneToMany(mappedBy = "personalDetail")
	private List<PaymentDetail> paymentDetails;

	@ManyToOne
	@JoinColumn(name = "applicant_cred_id")
	private ApplicantCredential applicantCredential;

	@ManyToOne
	@JoinColumn(name = "candidate_employment_type_id")
	private CandidateEmploymentType candidateEmploymentType;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CasteCategoryDetail casteCategoryDetail;

	// bi-directional many-to-one association to DobCertificateType
	@ManyToOne
	@JoinColumn(name = "dob_certificate_type_id")
	private DobCertificateType dobCertificateType;

	@ManyToOne
	@JoinColumn(name = "education_level_category_id")
	private EducationLevelCategory educationLevelCategory;

	@ManyToOne
	@JoinColumn(name = "icg_employment_type_id")
	private IcgEmploymentType IcgEmploymentType;

	// bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name = "post_id")
	private IcgPost icgPost;

	@ManyToOne
	@JoinColumn(name = "photo_identity_card_type_master_id")
	private PhotoIdentityCardTypeMaster photoIdentityCardTypeMaster;

	// mapping for govt employment
	@ManyToOne
	@JoinColumn(name = "govt_employment_type_master_id")
	private GovtEmploymentType GovtEmploymentType;

	@Column(name = "registration_no")
	private String registrationNo;

	@Column(name = "is_phase1withinrange")
	private Boolean isPhase1withinrange;

	@Column(name = "is_seatallocation_done")
	private Boolean isSeatallocationDone;

	public PersonalDetail() {

	}

	public Long getPersonalDetailsId() {
		return personalDetailsId;
	}

	public void setPersonalDetailsId(Long personalDetailsId) {
		this.personalDetailsId = personalDetailsId;
	}

	public String getAadhaarCardNumber() {
		return aadhaarCardNumber;
	}

	public void setAadhaarCardNumber(String aadhaarCardNumber) {
		this.aadhaarCardNumber = aadhaarCardNumber;
	}

	public Boolean getAadhaarCardNumberConsentAccept() {
		return aadhaarCardNumberConsentAccept;
	}

	public void setAadhaarCardNumberConsentAccept(Boolean aadhaarCardNumberConsentAccept) {
		this.aadhaarCardNumberConsentAccept = aadhaarCardNumberConsentAccept;
	}

	public Boolean getAgeRelaxation() {
		return ageRelaxation;
	}

	public void setAgeRelaxation(Boolean ageRelaxation) {
		this.ageRelaxation = ageRelaxation;
	}

	public String getAlternateMobile() {
		return alternateMobile;
	}

	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	public String getCandidateEmploymentExperience() {
		return candidateEmploymentExperience;
	}

	public void setCandidateEmploymentExperience(String candidateEmploymentExperience) {
		this.candidateEmploymentExperience = candidateEmploymentExperience;
	}

	public String getCandidateEmploymentOrg() {
		return candidateEmploymentOrg;
	}

	public void setCandidateEmploymentOrg(String candidateEmploymentOrg) {
		this.candidateEmploymentOrg = candidateEmploymentOrg;
	}

	public String getCommunityCertificatePath() {
		return communityCertificatePath;
	}

	public void setCommunityCertificatePath(String communityCertificatePath) {
		this.communityCertificatePath = communityCertificatePath;
	}

	public String getCommunityCertificateValidityType() {
		return communityCertificateValidityType;
	}

	public void setCommunityCertificateValidityType(String communityCertificateValidityType) {
		this.communityCertificateValidityType = communityCertificateValidityType;
	}

	public Timestamp getCplDateOfIssue() {
		return cplDateOfIssue;
	}

	public void setCplDateOfIssue(Timestamp cplDateOfIssue) {
		this.cplDateOfIssue = cplDateOfIssue;
	}

	public String getCplIssuingAuthority() {
		return cplIssuingAuthority;
	}

	public void setCplIssuingAuthority(String cplIssuingAuthority) {
		this.cplIssuingAuthority = cplIssuingAuthority;
	}

	public String getCplNumber() {
		return cplNumber;
	}

	public void setCplNumber(String cplNumber) {
		this.cplNumber = cplNumber;
	}

	public String getCplPath() {
		return cplPath;
	}

	public void setCplPath(String cplPath) {
		this.cplPath = cplPath;
	}

	public Timestamp getCplValidityOfLicense() {
		return cplValidityOfLicense;
	}

	public void setCplValidityOfLicense(Timestamp cplValidityOfLicense) {
		this.cplValidityOfLicense = cplValidityOfLicense;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirthProofPath() {
		return dateOfBirthProofPath;
	}

	public void setDateOfBirthProofPath(String dateOfBirthProofPath) {
		this.dateOfBirthProofPath = dateOfBirthProofPath;
	}

	public Timestamp getDateOfIssueCommunityCertificate() {
		return dateOfIssueCommunityCertificate;
	}

	public void setDateOfIssueCommunityCertificate(Timestamp dateOfIssueCommunityCertificate) {
		this.dateOfIssueCommunityCertificate = dateOfIssueCommunityCertificate;
	}

	public Boolean getDisclaimerAccepted() {
		return disclaimerAccepted;
	}

	public void setDisclaimerAccepted(Boolean disclaimerAccepted) {
		this.disclaimerAccepted = disclaimerAccepted;
	}

	public String getEmailidPrimary() {
		return emailidPrimary;
	}

	public void setEmailidPrimary(String emailidPrimary) {
		this.emailidPrimary = emailidPrimary;
	}

	public String getEmailidSecondary() {
		return emailidSecondary;
	}

	public void setEmailidSecondary(String emailidSecondary) {
		this.emailidSecondary = emailidSecondary;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Boolean getFeeWaiverClaimed() {
		return feeWaiverClaimed;
	}

	public void setFeeWaiverClaimed(Boolean feeWaiverClaimed) {
		this.feeWaiverClaimed = feeWaiverClaimed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIcgPresentUnit() {
		return icgPresentUnit;
	}

	public void setIcgPresentUnit(String icgPresentUnit) {
		this.icgPresentUnit = icgPresentUnit;
	}

	public String getIcgServiceNumber() {
		return icgServiceNumber;
	}

	public void setIcgServiceNumber(String icgServiceNumber) {
		this.icgServiceNumber = icgServiceNumber;
	}

	public String getIdentificationMark1() {
		return identificationMark1;
	}

	public void setIdentificationMark1(String identificationMark1) {
		this.identificationMark1 = identificationMark1;
	}

	public String getIdentificationMark2() {
		return identificationMark2;
	}

	public void setIdentificationMark2(String identificationMark2) {
		this.identificationMark2 = identificationMark2;
	}

	public Boolean getIsDisclaimerForPromotions() {
		return isDisclaimerForPromotions;
	}

	public void setIsDisclaimerForPromotions(Boolean isDisclaimerForPromotions) {
		this.isDisclaimerForPromotions = isDisclaimerForPromotions;
	}

	public Boolean getIsFsbAdmitcardDownloaded() {
		return isFsbAdmitcardDownloaded;
	}

	public void setIsFsbAdmitcardDownloaded(Boolean isFsbAdmitcardDownloaded) {
		this.isFsbAdmitcardDownloaded = isFsbAdmitcardDownloaded;
	}

	public Boolean getIsFsbAttempted() {
		return isFsbAttempted;
	}

	public void setIsFsbAttempted(Boolean isFsbAttempted) {
		this.isFsbAttempted = isFsbAttempted;
	}

	public String getIsPabtQualified() {
		return isPabtQualified;
	}

	public void setIsPabtQualified(String isPabtQualified) {
		this.isPabtQualified = isPabtQualified;
	}

	public Boolean getIsPersonalInfoConfirmed() {
		return isPersonalInfoConfirmed;
	}

	public void setIsPersonalInfoConfirmed(Boolean isPersonalInfoConfirmed) {
		this.isPersonalInfoConfirmed = isPersonalInfoConfirmed;
	}


	public Boolean getIsPsbAdmitcardDownloaded() {
		return isPsbAdmitcardDownloaded;
	}

	public void setIsPsbAdmitcardDownloaded(Boolean isPsbAdmitcardDownloaded) {
		this.isPsbAdmitcardDownloaded = isPsbAdmitcardDownloaded;
	}

	public Boolean getIsPsbAttempted() {
		return isPsbAttempted;
	}

	public void setIsPsbAttempted(Boolean isPsbAttempted) {
		this.isPsbAttempted = isPsbAttempted;
	}

	public Boolean getIsSsbAttempted() {
		return isSsbAttempted;
	}

	public void setIsSsbAttempted(Boolean isSsbAttempted) {
		this.isSsbAttempted = isSsbAttempted;
	}

	public String getLeftThumbImpressionPath() {
		return leftThumbImpressionPath;
	}

	public void setLeftThumbImpressionPath(String leftThumbImpressionPath) {
		this.leftThumbImpressionPath = leftThumbImpressionPath;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhotoIdentityPath() {
		return photoIdentityPath;
	}

	public void setPhotoIdentityPath(String photoIdentityPath) {
		this.photoIdentityPath = photoIdentityPath;
	}

	public String getPabtQualifiedBatch() {
		return pabtQualifiedBatch;
	}

	public void setPabtQualifiedBatch(String pabtQualifiedBatch) {
		this.pabtQualifiedBatch = pabtQualifiedBatch;
	}

	public String getPabtQualifiedYear() {
		return pabtQualifiedYear;
	}

	public void setPabtQualifiedYear(String pabtQualifiedYear) {
		this.pabtQualifiedYear = pabtQualifiedYear;
	}

	public String getPabtRollOrBatchNumber() {
		return pabtRollOrBatchNumber;
	}

	public void setPabtRollOrBatchNumber(String pabtRollOrBatchNumber) {
		this.pabtRollOrBatchNumber = pabtRollOrBatchNumber;
	}

	public String getPhotoIdentityCardNumber() {
		return photoIdentityCardNumber;
	}

	public void setPhotoIdentityCardNumber(String photoIdentityCardNumber) {
		this.photoIdentityCardNumber = photoIdentityCardNumber;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}


	public String getRenewedCasteCertificatePath() {
		return renewedCasteCertificatePath;
	}

	public void setRenewedCasteCertificatePath(String renewedCasteCertificatePath) {
		this.renewedCasteCertificatePath = renewedCasteCertificatePath;
	}

	public String getRightThumbImpressionPath() {
		return rightThumbImpressionPath;
	}

	public void setRightThumbImpressionPath(String rightThumbImpressionPath) {
		this.rightThumbImpressionPath = rightThumbImpressionPath;
	}

	public Long getRollCount() {
		return rollCount;
	}

	public void setRollCount(Long rollCount) {
		this.rollCount = rollCount;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Timestamp getSelfDeclarationDate() {
		return selfDeclarationDate;
	}

	public void setSelfDeclarationDate(Timestamp selfDeclarationDate) {
		this.selfDeclarationDate = selfDeclarationDate;
	}

	public String getSelfDeclarationFormPath() {
		return selfDeclarationFormPath;
	}

	public void setSelfDeclarationFormPath(String selfDeclarationFormPath) {
		this.selfDeclarationFormPath = selfDeclarationFormPath;
	}

	public String getSelfDeclarationPlace() {
		return selfDeclarationPlace;
	}

	public void setSelfDeclarationPlace(String selfDeclarationPlace) {
		this.selfDeclarationPlace = selfDeclarationPlace;
	}

	public String getServiceCertificatePath() {
		return serviceCertificatePath;
	}

	public void setServiceCertificatePath(String serviceCertificatePath) {
		this.serviceCertificatePath = serviceCertificatePath;
	}

	public String getServiceNocCertificatePath() {
		return serviceNocCertificatePath;
	}

	public void setServiceNocCertificatePath(String serviceNocCertificatePath) {
		this.serviceNocCertificatePath = serviceNocCertificatePath;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public Timestamp getValidityOfCommunityCertificate() {
		return validityOfCommunityCertificate;
	}

	public void setValidityOfCommunityCertificate(Timestamp validityOfCommunityCertificate) {
		this.validityOfCommunityCertificate = validityOfCommunityCertificate;
	}

	public Integer getWrittenTestSectionMasterId() {
		return writtenTestSectionMasterId;
	}

	public void setWrittenTestSectionMasterId(Integer writtenTestSectionMasterId) {
		this.writtenTestSectionMasterId = writtenTestSectionMasterId;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public ApplicantCredential getApplicantCredential() {
		return applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public CandidateEmploymentType getCandidateEmploymentType() {
		return candidateEmploymentType;
	}

	public void setCandidateEmploymentType(CandidateEmploymentType candidateEmploymentType) {
		this.candidateEmploymentType = candidateEmploymentType;
	}

	public CasteCategoryDetail getCasteCategoryDetail() {
		return casteCategoryDetail;
	}

	public void setCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		this.casteCategoryDetail = casteCategoryDetail;
	}

	public DobCertificateType getDobCertificateType() {
		return dobCertificateType;
	}

	public void setDobCertificateType(DobCertificateType dobCertificateType) {
		this.dobCertificateType = dobCertificateType;
	}

	public EducationLevelCategory getEducationLevelCategory() {
		return educationLevelCategory;
	}

	public void setEducationLevelCategory(EducationLevelCategory educationLevelCategory) {
		this.educationLevelCategory = educationLevelCategory;
	}

	public IcgEmploymentType getIcgEmploymentType() {
		return IcgEmploymentType;
	}

	public void setIcgEmploymentType(IcgEmploymentType icgEmploymentType) {
		IcgEmploymentType = icgEmploymentType;
	}

	public IcgPost getIcgPost() {
		return icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	public PhotoIdentityCardTypeMaster getPhotoIdentityCardTypeMaster() {
		return photoIdentityCardTypeMaster;
	}

	public void setPhotoIdentityCardTypeMaster(PhotoIdentityCardTypeMaster photoIdentityCardTypeMaster) {
		this.photoIdentityCardTypeMaster = photoIdentityCardTypeMaster;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Boolean getIsPhase1withinrange() {
		return isPhase1withinrange;
	}

	public void setIsPhase1withinrange(Boolean isPhase1withinrange) {
		this.isPhase1withinrange = isPhase1withinrange;
	}

	public Boolean getIsSeatallocationDone() {
		return isSeatallocationDone;
	}

	public void setIsSeatallocationDone(Boolean isSeatallocationDone) {
		this.isSeatallocationDone = isSeatallocationDone;
	}
	
	

	public GovtEmploymentType getGovtEmploymentType() {
		return GovtEmploymentType;
	}

	public void setGovtEmploymentType(GovtEmploymentType govtEmploymentType) {
		GovtEmploymentType = govtEmploymentType;
	}

	
	public String getGovtSectorServiceRank() {
		return govtSectorServiceRank;
	}

	public void setGovtSectorServiceRank(String govtSectorServiceRank) {
		this.govtSectorServiceRank = govtSectorServiceRank;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMatriculationCertificatePath() {
		return matriculationCertificatePath;
	}

	public void setMatriculationCertificatePath(String matriculationCertificatePath) {
		this.matriculationCertificatePath = matriculationCertificatePath;
	}


	
	
	
}