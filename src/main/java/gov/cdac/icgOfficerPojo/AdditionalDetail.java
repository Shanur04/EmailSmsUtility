package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * The persistent class for the additional_details database table.
 * 
 */
@Entity
@Table(name="additional_details")
@NamedQuery(name="AdditionalDetail.findAll", query="SELECT a FROM AdditionalDetail a")
public class AdditionalDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADDITIONALDETAILSID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDITIONALDETAILSID_GENERATOR")
	@Column(name="additional_details_id")
	private Long additionalDetailsId;
	
	//NCC
	@Column(name="is_ncc_abc_certificate")
	private Boolean isNccAbcCertificate;
	
	@Column(name="ncc_certificate_acquired_year")
	private String nccCertificateAcquiredYear;

	@Column(name="ncc_certificate_from")
	private String nccCertificateFrom;

	@Column(name="ncc_certificate_number")
	private String nccCertificateNumber;
	
	@Column(name="ncc_certificate_path")
	private String nccCertificatePath;

	@Column(name="ncc_certificate_type")
	private String nccCertificateType;

	@Column(name="ncc_unit_name")
	private String nccUnitName;
	
	//Criminal Proceeding
	@NotNull
	@Column(name="any_proceedings_pending_before_criminal_court_in_india")
	private Boolean anyProceedingsPendingBeforeCriminalCourtInIndia;
	
	@NotNull
	@Column(name="any_warrant_as_witness_issued_and_pending_court_of_law")
	private Boolean anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce;

	@NotNull
	@Column(name="any_warrant_issued_and_pending_court_law_for_time_being_force")
	private Boolean anyWarrantIssuedAndPendingCourtLawForTimeBeingForce;
	
	@NotNull
	@Column(name="warrant_for_arrest_issued_by_court_for_time_being_in_force")
	private Boolean warrantForArrestIssuedByCourtForTimeBeingInForce;
	
	//convicted by a court
	@NotNull
	@Column(name="convicted_by_court_in_india_for_offence_sentenced_to_imprsnment")
	private Boolean convictedByCourtInIndiaForOffenceSentencedToImprsnment;
	
	@NotNull
	@Column(name="applied_in_higher_court_for_acquittal_against_conviction_of_law")
	private Boolean appliedInHigherCourtForAcquittalAgainstConvictionOfLaw;

	//Debarred
	@NotNull
	@Column(name="debarred_by_any_govt_agency_for_recruitment")
	private Boolean debarredByAnyGovtAgencyForRecruitment;
	
	@Column(name="year_debarred_by_any_govt_agency_for_recruitment")
	private String yearDebarredByAnyGovtAgencyForRecruitment;
	
	@Column(name="reason_debarred_by_any_govt_agency_for_recruitment")
	private String reasonDebarredByAnyGovtAgencyForRecruitment;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;
	
	public AdditionalDetail() {
	}

	public Long getAdditionalDetailsId() {
		return this.additionalDetailsId;
	}

	public void setAdditionalDetailsId(Long additionalDetailsId) {
		this.additionalDetailsId = additionalDetailsId;
	}
	
	
	public Boolean getAnyProceedingsPendingBeforeCriminalCourtInIndia() {
		return this.anyProceedingsPendingBeforeCriminalCourtInIndia;
	}

	public void setAnyProceedingsPendingBeforeCriminalCourtInIndia(Boolean anyProceedingsPendingBeforeCriminalCourtInIndia) {
		this.anyProceedingsPendingBeforeCriminalCourtInIndia = anyProceedingsPendingBeforeCriminalCourtInIndia;
	}

	public Boolean getAnyWarrantIssuedAndPendingCourtLawForTimeBeingForce() {
		return this.anyWarrantIssuedAndPendingCourtLawForTimeBeingForce;
	}

	public void setAnyWarrantIssuedAndPendingCourtLawForTimeBeingForce(Boolean anyWarrantIssuedAndPendingCourtLawForTimeBeingForce) {
		this.anyWarrantIssuedAndPendingCourtLawForTimeBeingForce = anyWarrantIssuedAndPendingCourtLawForTimeBeingForce;
	}

	public Boolean getAppliedInHigherCourtForAcquittalAgainstConvictionOfLaw() {
		return this.appliedInHigherCourtForAcquittalAgainstConvictionOfLaw;
	}

	public void setAppliedInHigherCourtForAcquittalAgainstConvictionOfLaw(Boolean appliedInHigherCourtForAcquittalAgainstConvictionOfLaw) {
		this.appliedInHigherCourtForAcquittalAgainstConvictionOfLaw = appliedInHigherCourtForAcquittalAgainstConvictionOfLaw;
	}

	
	public Boolean getConvictedByCourtInIndiaForOffenceSentencedToImprsnment() {
		return this.convictedByCourtInIndiaForOffenceSentencedToImprsnment;
	}

	public void setConvictedByCourtInIndiaForOffenceSentencedToImprsnment(Boolean convictedByCourtInIndiaForOffenceSentencedToImprsnment) {
		this.convictedByCourtInIndiaForOffenceSentencedToImprsnment = convictedByCourtInIndiaForOffenceSentencedToImprsnment;
	}

	public Boolean getDebarredByAnyGovtAgencyForRecruitment() {
		return this.debarredByAnyGovtAgencyForRecruitment;
	}

	public void setDebarredByAnyGovtAgencyForRecruitment(Boolean debarredByAnyGovtAgencyForRecruitment) {
		this.debarredByAnyGovtAgencyForRecruitment = debarredByAnyGovtAgencyForRecruitment;
	}

	
	public String getNccCertificateAcquiredYear() {
		return this.nccCertificateAcquiredYear;
	}

	public void setNccCertificateAcquiredYear(String nccCertificateAcquiredYear) {
		this.nccCertificateAcquiredYear = nccCertificateAcquiredYear;
	}

	public String getNccCertificateFrom() {
		return this.nccCertificateFrom;
	}

	public void setNccCertificateFrom(String nccCertificateFrom) {
		this.nccCertificateFrom = nccCertificateFrom;
	}

	public String getNccCertificateNumber() {
		return this.nccCertificateNumber;
	}

	public void setNccCertificateNumber(String nccCertificateNumber) {
		this.nccCertificateNumber = nccCertificateNumber;
	}

	public String getNccCertificateType() {
		return this.nccCertificateType;
	}

	public void setNccCertificateType(String nccCertificateType) {
		this.nccCertificateType = nccCertificateType;
	}

	public String getNccUnitName() {
		return this.nccUnitName;
	}

	public void setNccUnitName(String nccUnitName) {
		this.nccUnitName = nccUnitName;
	}

	
	public String getReasonDebarredByAnyGovtAgencyForRecruitment() {
		return this.reasonDebarredByAnyGovtAgencyForRecruitment;
	}

	public void setReasonDebarredByAnyGovtAgencyForRecruitment(String reasonDebarredByAnyGovtAgencyForRecruitment) {
		this.reasonDebarredByAnyGovtAgencyForRecruitment = reasonDebarredByAnyGovtAgencyForRecruitment;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Boolean getWarrantForArrestIssuedByCourtForTimeBeingInForce() {
		return this.warrantForArrestIssuedByCourtForTimeBeingInForce;
	}

	public void setWarrantForArrestIssuedByCourtForTimeBeingInForce(Boolean warrantForArrestIssuedByCourtForTimeBeingInForce) {
		this.warrantForArrestIssuedByCourtForTimeBeingInForce = warrantForArrestIssuedByCourtForTimeBeingInForce;
	}

	public String getYearDebarredByAnyGovtAgencyForRecruitment() {
		return this.yearDebarredByAnyGovtAgencyForRecruitment;
	}

	public void setYearDebarredByAnyGovtAgencyForRecruitment(String yearDebarredByAnyGovtAgencyForRecruitment) {
		this.yearDebarredByAnyGovtAgencyForRecruitment = yearDebarredByAnyGovtAgencyForRecruitment;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public Boolean getIsNccAbcCertificate() {
		return isNccAbcCertificate;
	}

	public void setIsNccAbcCertificate(Boolean isNccAbcCertificate) {
		this.isNccAbcCertificate = isNccAbcCertificate;
	}


	public String getNccCertificatePath() {
		return nccCertificatePath;
	}

	public void setNccCertificatePath(String nccCertificatePath) {
		this.nccCertificatePath = nccCertificatePath;
	}

	public Boolean getAnyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce() {
		return anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce;
	}

	public void setAnyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce(
			Boolean anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce) {
		this.anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce = anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce;
	}

	@Override
	public String toString() {
		return "AdditionalDetail [additionalDetailsId=" + additionalDetailsId + ", isNccAbcCertificate="
				+ isNccAbcCertificate + ", nccCertificateAcquiredYear=" + nccCertificateAcquiredYear
				+ ", nccCertificateFrom=" + nccCertificateFrom + ", nccCertificateNumber=" + nccCertificateNumber
				+ ", nccCertificatePath=" + nccCertificatePath + ", nccCertificateType=" + nccCertificateType
				+ ", nccUnitName=" + nccUnitName + ", anyProceedingsPendingBeforeCriminalCourtInIndia="
				+ anyProceedingsPendingBeforeCriminalCourtInIndia
				+ ", anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce="
				+ anyWarrantAsWitnessIssuedAndPendingCourtLawForTimeBeingForce
				+ ", anyWarrantIssuedAndPendingCourtLawForTimeBeingForce="
				+ anyWarrantIssuedAndPendingCourtLawForTimeBeingForce
				+ ", warrantForArrestIssuedByCourtForTimeBeingInForce="
				+ warrantForArrestIssuedByCourtForTimeBeingInForce
				+ ", convictedByCourtInIndiaForOffenceSentencedToImprsnment="
				+ convictedByCourtInIndiaForOffenceSentencedToImprsnment
				+ ", appliedInHigherCourtForAcquittalAgainstConvictionOfLaw="
				+ appliedInHigherCourtForAcquittalAgainstConvictionOfLaw + ", debarredByAnyGovtAgencyForRecruitment="
				+ debarredByAnyGovtAgencyForRecruitment + ", yearDebarredByAnyGovtAgencyForRecruitment="
				+ yearDebarredByAnyGovtAgencyForRecruitment + ", reasonDebarredByAnyGovtAgencyForRecruitment="
				+ reasonDebarredByAnyGovtAgencyForRecruitment + ", recordTracking=" + recordTracking
				+ ", applicantCredential=" + applicantCredential + "]";
	}
	
}