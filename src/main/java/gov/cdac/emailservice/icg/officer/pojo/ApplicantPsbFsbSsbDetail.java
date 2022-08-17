package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the applicant_psb_fsb_ssb_details database table.
 * 
 */
@Entity
@Table(name="applicant_psb_fsb_ssb_details")
@NamedQuery(name="ApplicantPsbFsbSsbDetail.findAll", query="SELECT a FROM ApplicantPsbFsbSsbDetail a")
public class ApplicantPsbFsbSsbDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_PSB_FSB_SSB_DETAILS_APPLICANTPSBFSBSSBDETAILSID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_PSB_FSB_SSB_DETAILS_APPLICANTPSBFSBSSBDETAILSID_GENERATOR")
	@Column(name="applicant_psb_fsb_ssb_details_id")
	private Long applicantPsbFsbSsbDetailsId;

	@Column(name="is_recommanded")
	private Boolean isRecommanded;

	@Column(name="psb_fsb_ssb_batch_details")
	private String psbFsbSsbBatchDetails;

	@Column(name="psb_fsb_ssb_chest_number")
	private String psbFsbSsbChestNumber;

	@Column(name="psb_fsb_ssb_end_date")
	private Timestamp psbFsbSsbEndDate;

	@Column(name="psb_fsb_ssb_number")
	private String psbFsbSsbNumber;

	@Column(name="psb_fsb_ssb_place")
	private String psbFsbSsbPlace;

	@Column(name="psb_fsb_ssb_start_date")
	private Timestamp psbFsbSsbStartDate;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to SelectionExamTypeMaster
	@ManyToOne
	@JoinColumn(name="selection_exam_type_id")
	private SelectionExamTypeMaster selectionExamTypeMaster;

	//bi-directional many-to-one association to SelectionOrganisationTypeMaster
	@ManyToOne
	@JoinColumn(name="selection_organisation_type_id")
	private SelectionOrganisationTypeMaster selectionOrganisationTypeMaster;

	public ApplicantPsbFsbSsbDetail() {
	}

	public Long getApplicantPsbFsbSsbDetailsId() {
		return this.applicantPsbFsbSsbDetailsId;
	}

	public void setApplicantPsbFsbSsbDetailsId(Long applicantPsbFsbSsbDetailsId) {
		this.applicantPsbFsbSsbDetailsId = applicantPsbFsbSsbDetailsId;
	}

	public Boolean getIsRecommanded() {
		return this.isRecommanded;
	}

	public void setIsRecommanded(Boolean isRecommanded) {
		this.isRecommanded = isRecommanded;
	}

	public String getPsbFsbSsbBatchDetails() {
		return this.psbFsbSsbBatchDetails;
	}

	public void setPsbFsbSsbBatchDetails(String psbFsbSsbBatchDetails) {
		this.psbFsbSsbBatchDetails = psbFsbSsbBatchDetails;
	}

	public String getPsbFsbSsbChestNumber() {
		return this.psbFsbSsbChestNumber;
	}

	public void setPsbFsbSsbChestNumber(String psbFsbSsbChestNumber) {
		this.psbFsbSsbChestNumber = psbFsbSsbChestNumber;
	}

	public Timestamp getPsbFsbSsbEndDate() {
		return this.psbFsbSsbEndDate;
	}

	public void setPsbFsbSsbEndDate(Timestamp psbFsbSsbEndDate) {
		this.psbFsbSsbEndDate = psbFsbSsbEndDate;
	}

	public String getPsbFsbSsbNumber() {
		return this.psbFsbSsbNumber;
	}

	public void setPsbFsbSsbNumber(String psbFsbSsbNumber) {
		this.psbFsbSsbNumber = psbFsbSsbNumber;
	}

	public String getPsbFsbSsbPlace() {
		return this.psbFsbSsbPlace;
	}

	public void setPsbFsbSsbPlace(String psbFsbSsbPlace) {
		this.psbFsbSsbPlace = psbFsbSsbPlace;
	}

	public Timestamp getPsbFsbSsbStartDate() {
		return this.psbFsbSsbStartDate;
	}

	public void setPsbFsbSsbStartDate(Timestamp psbFsbSsbStartDate) {
		this.psbFsbSsbStartDate = psbFsbSsbStartDate;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public SelectionExamTypeMaster getSelectionExamTypeMaster() {
		return this.selectionExamTypeMaster;
	}

	public void setSelectionExamTypeMaster(SelectionExamTypeMaster selectionExamTypeMaster) {
		this.selectionExamTypeMaster = selectionExamTypeMaster;
	}

	public SelectionOrganisationTypeMaster getSelectionOrganisationTypeMaster() {
		return this.selectionOrganisationTypeMaster;
	}

	public void setSelectionOrganisationTypeMaster(SelectionOrganisationTypeMaster selectionOrganisationTypeMaster) {
		this.selectionOrganisationTypeMaster = selectionOrganisationTypeMaster;
	}
	

}