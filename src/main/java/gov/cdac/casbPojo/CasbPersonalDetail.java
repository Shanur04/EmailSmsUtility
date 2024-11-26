package gov.cdac.casbPojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the personal_details database table.
 * 
 */
@Entity
@Table(name = "personal_details")
@JsonIgnoreProperties(value = { "casbGroupSubjects", "examCasbGroupSlots", "applicantCredential" })
public class CasbPersonalDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_details_seq")
    @SequenceGenerator(name = "personal_details_seq", sequenceName = "personal_details_personal_details_id_seq", allocationSize = 1)
    @Column(name = "personal_details_id")
    private Long personalDetailsId;

    @Column(name = "aadhaar_card_number")
    private String aadhaarCardNumber;

    private Double chest;

    private String commendation;

    @Column(name = "corresp_address_district")
    private String correspAddressDistrict;

    @Column(name = "corresp_address_country")
    private String correspAddressCountry;

    @Column(name = "corresp_address_house_street")
    private String correspAddressHouseStreet;

    @Column(name = "corresp_address_pincode")
    private BigDecimal correspAddressPincode;

    @Column(name = "corresp_address_state")
    private String correspAddressState;

    @Column(name = "corresp_address_tehsil")
    private String correspAddressTehsil;

    @Column(name = "corresp_address_village_town")
    private String correspAddressVillageTown;

    @Column(name = "corresp_address_post_office")
    private String correspAddressPostOffice;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "diploma_institute_name")
    private String diplomaInstituteName;

    @Column(name = "diploma_or_twelfth")
    private String diplomaOrTwelfth;

    @Column(name = "email_id_primary")
    private String emailIdPrimary;

    @Column(name = "email_id_secondary")
    private String emailIdSecondary;

    @Column(name = "english_percentage")
    private Integer englishPercentage;

    @Column(name = "english_subject_marksheet")
    private String englishSubjectMarksheet;

    private Boolean flag;

    @Column(name = "gross_percentage")
    private Integer grossPercentage;

    private Double height;

    @Column(name = "hsc_additional_marksheet")
    private String hscAdditionalMarksheet;

    @Column(name = "hsc_or_diploma_marksheet")
    private String hscOrDiplomaMarksheet;

    @Column(name = "identification_mark")
    private String identificationMark;

    @Column(name = "is_commendation")
    private Boolean isCommendation;

    @Column(name = "is_discharged")
    private Boolean isDischarged;

    @Column(name = "\"is_Improvement\"")
    private Boolean is_Improvement;

    @Column(name = "is_ncc")
    private Boolean isNcc;

    @Column(name = "is_nce_of_iaf")
    private Boolean isNceOfIaf;

    @Column(name = "is_pcb")
    private Boolean isPcb;

    @Column(name = "is_soafp")
    private Boolean isSoafp;

    @Column(name = "is_english_passed")
    private Boolean isEnglishPassed;

    @Column(name = "is_twelfth_or_diploma_passed")
    private Boolean isTwelfthOrDiplomaPassed;

    @Column(name = "marital_status")
    private Boolean maritalStatus;

    @Column(name = "maths_percentage")
    private Integer mathsPercentage;

    private String mobile;

    private String name;

    private String nationality;

    @Column(name = "nce_service_certificate")
    private String nceServiceCertificate;

    @Column(name = "nce_service_number")
    private String nceServiceNumber;

    @Column(name = "nce_unit_posted_present")
    private String nceUnitPostedPresent;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "parent_signature")
    private String parentSignature;

    @Column(name = "pcb_percentage")
    private Integer pcbPercentage;

    @Column(name = "permnt_address_district")
    private String permntAddressDistrict;

    @Column(name = "permnt_address_country")
    private String permntAddressCountry;

    @Column(name = "permnt_address_house_street")
    private String permntAddressHouseStreet;

    @Column(name = "permnt_address_pincode")
    private BigDecimal permntAddressPincode;

    @Column(name = "permnt_address_state")
    private String permntAddressState;

    @Column(name = "permnt_address_tehsil")
    private String permntAddressTehsil;

    @Column(name = "permnt_address_village_town")
    private String permntAddressVillageTown;

    @Column(name = "permnt_address_post_office")
    private String permntAddressPostOffice;

    private String photo;

    @Column(name = "physics_percentage")
    private Integer physicsPercentage;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    private Integer shoes;

    private String signature;

    @Column(name = "ssc_passing_certificate")
    private String sscPassingCertificate;

    @Column(name = "thumb_impression")
    private String thumbImpression;

    @Column(name = "ncc_grade")
    private String nccGrade;

    @Column(name = "ncc_certificate_number")
    private String nccCertificateNumber;

    @Column(name = "discharged_organization")
    private String dischargedOrganization;

    public Boolean getIsEnglishPassed() {
	return isEnglishPassed;
    }

    public void setIsEnglishPassed(Boolean isEnglishPassed) {
	this.isEnglishPassed = isEnglishPassed;
    }

    public String getNccCertificateNumber() {
	return nccCertificateNumber;
    }

    public void setNccCertificateNumber(String nccCertificateNumber) {
	this.nccCertificateNumber = nccCertificateNumber;
    }

    private Double waist;

    @Column(name = "disclaimer_accepted")
    private Boolean disclaimerAccepted;

    @Column(name = "declaration_date")
    private Date declarationDate;

    @Column(name = "declaration_place")
    private String declarationPlace;

    @Column(name = "is_permanent_corresp_address_same")
    private Boolean isPermanentCorrespAddressSame;

    @Column(name = "registration_no", unique = true)
    private Long registrationNumber;

    // bi-directional many-to-one association to ApplicantCredential
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_cred_id")
    private CasbApplicantCredential casbApplicantCredential;

    // bi-directional many-to-one association to CasbGroup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casb_group_id")
    private CasbGroup casbGroup;

    public CasbPersonalDetail() {
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

    /*
     * public Integer getChest() { return this.chest; }
     * 
     * public void setChest(Integer chest) { this.chest = chest; }
     */

    public Double getChest() {
	return chest;
    }

    public void setChest(Double chest) {
	this.chest = chest;
    }

    public String getCommendation() {
	return this.commendation;
    }

    public void setCommendation(String commendation) {
	this.commendation = commendation;
    }

    /*
     * public String getCorrespAddressCity() { return this.correspAddressCity; }
     * 
     * public void setCorrespAddressCity(String correspAddressCity) {
     * this.correspAddressCity = correspAddressCity; }
     */

    public String getCorrespAddressDistrict() {
	return correspAddressDistrict;
    }

    public void setCorrespAddressDistrict(String correspAddressDistrict) {
	this.correspAddressDistrict = correspAddressDistrict;
    }

    public String getCorrespAddressCountry() {
	return this.correspAddressCountry;
    }

    public void setCorrespAddressCountry(String correspAddressCountry) {
	this.correspAddressCountry = correspAddressCountry;
    }

    public String getCorrespAddressHouseStreet() {
	return this.correspAddressHouseStreet;
    }

    public void setCorrespAddressHouseStreet(String correspAddressHouseStreet) {
	this.correspAddressHouseStreet = correspAddressHouseStreet;
    }

    public BigDecimal getCorrespAddressPincode() {
	return this.correspAddressPincode;
    }

    public void setCorrespAddressPincode(BigDecimal correspAddressPincode) {
	this.correspAddressPincode = correspAddressPincode;
    }

    public String getCorrespAddressState() {
	return this.correspAddressState;
    }

    public void setCorrespAddressState(String correspAddressState) {
	this.correspAddressState = correspAddressState;
    }

    public String getCorrespAddressTehsil() {
	return this.correspAddressTehsil;
    }

    public void setCorrespAddressTehsil(String correspAddressTehsil) {
	this.correspAddressTehsil = correspAddressTehsil;
    }

    public String getCorrespAddressVillageTown() {
	return this.correspAddressVillageTown;
    }

    public void setCorrespAddressVillageTown(String correspAddressVillageTown) {
	this.correspAddressVillageTown = correspAddressVillageTown;
    }

    public String getCorrespAddressPostOffice() {
	return correspAddressPostOffice;
    }

    public void setCorrespAddressPostOffice(String correspAddressPostOffice) {
	this.correspAddressPostOffice = correspAddressPostOffice;
    }

    public Date getDateOfBirth() {
	return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getDiplomaInstituteName() {
	return this.diplomaInstituteName;
    }

    public void setDiplomaInstituteName(String diplomaInstituteName) {
	this.diplomaInstituteName = diplomaInstituteName;
    }

    public String getDiplomaOrTwelfth() {
	return this.diplomaOrTwelfth;
    }

    public void setDiplomaOrTwelfth(String diplomaOrTwelfth) {
	this.diplomaOrTwelfth = diplomaOrTwelfth;
    }

    /*
     * public Integer getDiplomaTradeStreamMappingId() { return
     * this.diplomaTradeStreamMappingId; }
     * 
     * public void setDiplomaTradeStreamMappingId(Integer
     * diplomaTradeStreamMappingId) { this.diplomaTradeStreamMappingId =
     * diplomaTradeStreamMappingId; }
     */

    public String getEmailIdPrimary() {
	return this.emailIdPrimary;
    }

    public void setEmailIdPrimary(String emailIdPrimary) {
	this.emailIdPrimary = emailIdPrimary;
    }

    public String getEmailIdSecondary() {
	return this.emailIdSecondary;
    }

    public void setEmailIdSecondary(String emailIdSecondary) {
	this.emailIdSecondary = emailIdSecondary;
    }

    public Integer getEnglishPercentage() {
	return this.englishPercentage;
    }

    public void setEnglishPercentage(Integer englishPercentage) {
	this.englishPercentage = englishPercentage;
    }

    public String getEnglishSubjectMarksheet() {
	return this.englishSubjectMarksheet;
    }

    public void setEnglishSubjectMarksheet(String englishSubjectMarksheet) {
	this.englishSubjectMarksheet = englishSubjectMarksheet;
    }

    public Boolean getFlag() {
	return this.flag;
    }

    public void setFlag(Boolean flag) {
	this.flag = flag;
    }

    public Integer getGrossPercentage() {
	return this.grossPercentage;
    }

    public void setGrossPercentage(Integer grossPercentage) {
	this.grossPercentage = grossPercentage;
    }

    /*
     * public BigDecimal getHeight() { return this.height; }
     * 
     * public void setHeight(BigDecimal height) { this.height = height; }
     */

    public Double getHeight() {
	return height;
    }

    public void setHeight(Double height) {
	this.height = height;
    }

    public String getHscAdditionalMarksheet() {
	return this.hscAdditionalMarksheet;
    }

    public void setHscAdditionalMarksheet(String hscAdditionalMarksheet) {
	this.hscAdditionalMarksheet = hscAdditionalMarksheet;
    }

    public String getHscOrDiplomaMarksheet() {
	return this.hscOrDiplomaMarksheet;
    }

    public void setHscOrDiplomaMarksheet(String hscOrDiplomaMarksheet) {
	this.hscOrDiplomaMarksheet = hscOrDiplomaMarksheet;
    }

    public String getIdentificationMark() {
	return this.identificationMark;
    }

    public void setIdentificationMark(String identificationMark) {
	this.identificationMark = identificationMark;
    }

    public Boolean getIsCommendation() {
	return this.isCommendation;
    }

    public void setIsCommendation(Boolean isCommendation) {
	this.isCommendation = isCommendation;
    }

    public Boolean getIsDischarged() {
	return this.isDischarged;
    }

    public void setIsDischarged(Boolean isDischarged) {
	this.isDischarged = isDischarged;
    }

    public Boolean getIs_Improvement() {
	return this.is_Improvement;
    }

    public void setIs_Improvement(Boolean is_Improvement) {
	this.is_Improvement = is_Improvement;
    }

    public Boolean getIsNcc() {
	return this.isNcc;
    }

    public void setIsNcc(Boolean isNcc) {
	this.isNcc = isNcc;
    }

    public Boolean getIsNceOfIaf() {
	return this.isNceOfIaf;
    }

    public void setIsNceOfIaf(Boolean isNceOfIaf) {
	this.isNceOfIaf = isNceOfIaf;
    }

    public Boolean getIsPcb() {
	return this.isPcb;
    }

    public void setIsPcb(Boolean isPcb) {
	this.isPcb = isPcb;
    }

    public Boolean getIsSoafp() {
	return this.isSoafp;
    }

    public void setIsSoafp(Boolean isSoafp) {
	this.isSoafp = isSoafp;
    }

    public Boolean getIsTwelfthOrDiplomaPassed() {
	return this.isTwelfthOrDiplomaPassed;
    }

    public void setIsTwelfthOrDiplomaPassed(Boolean isTwelfthOrDiplomaPassed) {
	this.isTwelfthOrDiplomaPassed = isTwelfthOrDiplomaPassed;
    }

    public Boolean getMaritalStatus() {
	return this.maritalStatus;
    }

    public void setMaritalStatus(Boolean maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public Integer getMathsPercentage() {
	return this.mathsPercentage;
    }

    public void setMathsPercentage(Integer mathsPercentage) {
	this.mathsPercentage = mathsPercentage;
    }

    public String getMobile() {
	return this.mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
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

    public String getNceServiceCertificate() {
	return this.nceServiceCertificate;
    }

    public void setNceServiceCertificate(String nceServiceCertificate) {
	this.nceServiceCertificate = nceServiceCertificate;
    }

    public String getNceServiceNumber() {
	return this.nceServiceNumber;
    }

    public void setNceServiceNumber(String nceServiceNumber) {
	this.nceServiceNumber = nceServiceNumber;
    }

    public String getNceUnitPostedPresent() {
	return this.nceUnitPostedPresent;
    }

    public void setNceUnitPostedPresent(String nceUnitPostedPresent) {
	this.nceUnitPostedPresent = nceUnitPostedPresent;
    }

    public String getParentName() {
	return this.parentName;
    }

    public void setParentName(String parentName) {
	this.parentName = parentName;
    }

    public String getParentSignature() {
	return this.parentSignature;
    }

    public void setParentSignature(String parentSignature) {
	this.parentSignature = parentSignature;
    }

    public Integer getPcbPercentage() {
	return this.pcbPercentage;
    }

    public void setPcbPercentage(Integer pcbPercentage) {
	this.pcbPercentage = pcbPercentage;
    }

    /*
     * public String getPermntAddressCity() { return this.permntAddressCity; }
     * 
     * public void setPermntAddressCity(String permntAddressCity) {
     * this.permntAddressCity = permntAddressCity; }
     */

    public void setPermntAddressDistrict(String permntAddressDistrict) {
	this.permntAddressDistrict = permntAddressDistrict;
    }

    public void setPermntAddressCountry(String permntAddressCountry) {
	this.permntAddressCountry = permntAddressCountry;
    }

    public String getPermntAddressPostOffice() {
	return permntAddressPostOffice;
    }

    public void setPermntAddressPostOffice(String permntAddressPostOffice) {
	this.permntAddressPostOffice = permntAddressPostOffice;
    }

    public String getPermntAddressCountry() {
	return this.permntAddressCountry;
    }

    public String getPermntAddressDistrict() {
	return permntAddressDistrict;
    }

    public String getPermntAddressHouseStreet() {
	return this.permntAddressHouseStreet;
    }

    public void setPermntAddressHouseStreet(String permntAddressHouseStreet) {
	this.permntAddressHouseStreet = permntAddressHouseStreet;
    }

    public BigDecimal getPermntAddressPincode() {
	return this.permntAddressPincode;
    }

    public void setPermntAddressPincode(BigDecimal permntAddressPincode) {
	this.permntAddressPincode = permntAddressPincode;
    }

    public String getPermntAddressState() {
	return this.permntAddressState;
    }

    public void setPermntAddressState(String permntAddressState) {
	this.permntAddressState = permntAddressState;
    }

    public String getPermntAddressTehsil() {
	return this.permntAddressTehsil;
    }

    public void setPermntAddressTehsil(String permntAddressTehsil) {
	this.permntAddressTehsil = permntAddressTehsil;
    }

    public String getPermntAddressVillageTown() {
	return this.permntAddressVillageTown;
    }

    public void setPermntAddressVillageTown(String permntAddressVillageTown) {
	this.permntAddressVillageTown = permntAddressVillageTown;
    }

    public String getPhoto() {
	return this.photo;
    }

    public void setPhoto(String photo) {
	this.photo = photo;
    }

    public Integer getPhysicsPercentage() {
	return this.physicsPercentage;
    }

    public void setPhysicsPercentage(Integer physicsPercentage) {
	this.physicsPercentage = physicsPercentage;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public Integer getShoes() {
	return this.shoes;
    }

    public void setShoes(Integer shoes) {
	this.shoes = shoes;
    }

    public String getSignature() {
	return this.signature;
    }

    public void setSignature(String signature) {
	this.signature = signature;
    }

    public String getSscPassingCertificate() {
	return this.sscPassingCertificate;
    }

    public void setSscPassingCertificate(String sscPassingCertificate) {
	this.sscPassingCertificate = sscPassingCertificate;
    }

    public String getThumbImpression() {
	return this.thumbImpression;
    }

    public void setThumbImpression(String thumbImpression) {
	this.thumbImpression = thumbImpression;
    }

    public Long getRegistrationNumber() {
	return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
	this.registrationNumber = registrationNumber;
    }

    /*
     * public Integer getWaist() { return this.waist; }
     * 
     * public void setWaist(Integer waist) { this.waist = waist; }
     */

    public Double getWaist() {
	return waist;
    }

    public void setWaist(Double waist) {
	this.waist = waist;
    }

    public Boolean getDisclaimerAccepted() {
	return disclaimerAccepted;
    }

    public void setDisclaimerAccepted(Boolean disclaimerAccepted) {
	this.disclaimerAccepted = disclaimerAccepted;
    }

    public Date getDeclarationDate() {
	return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
	this.declarationDate = declarationDate;
    }

    public String getDeclarationPlace() {
	return declarationPlace;
    }

    public void setDeclarationPlace(String declarationPlace) {
	this.declarationPlace = declarationPlace;
    }

    public String getNccGrade() {
	return nccGrade;
    }

    public void setNccGrade(String nccGrade) {
	this.nccGrade = nccGrade;
    }

    public String getDischargedOrganization() {
	return dischargedOrganization;
    }

    public void setDischargedOrganization(String dischargedOrganization) {
	this.dischargedOrganization = dischargedOrganization;
    }

    public CasbApplicantCredential getApplicantCredential() {
	return this.casbApplicantCredential;
    }

    public void setApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
	this.casbApplicantCredential = casbApplicantCredential;
    }

    public CasbGroup getCasbGroup() {
	return this.casbGroup;
    }

    public void setCasbGroup(CasbGroup casbGroup) {
	this.casbGroup = casbGroup;
    }

    public Boolean getIsPermanentCorrespAddressSame() {
	return isPermanentCorrespAddressSame;
    }

    public void setIsPermanentCorrespAddressSame(Boolean isPermanentCorrespAddressSame) {
	this.isPermanentCorrespAddressSame = isPermanentCorrespAddressSame;
    }

}