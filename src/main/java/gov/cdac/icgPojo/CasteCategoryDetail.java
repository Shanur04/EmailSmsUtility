package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the caste_category_details database table.
 * 
 */
@Entity
@Table(name="caste_category_details")
@NamedQuery(name="CasteCategoryDetail.findAll", query="SELECT c FROM CasteCategoryDetail c")
@JsonIgnoreProperties({"personalDetails"})
public class CasteCategoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="batch_id")
	private Integer batchId;

	@Column(name="candidate_category_status")
	private Boolean candidateCategoryStatus;

	@Column(name="dob_reg_end_date")
	private Timestamp dobRegEndDate;

	@Column(name="dob_reg_start_date")
	private Timestamp dobRegStartDate;

	private Integer fees;

	@Column(name="is_age_relaxation")
	private Boolean isAgeRelaxation;

	@Column(name="min_passing_percentage")
	private Integer minPassingPercentage;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to CasteCategoryMaster
	@ManyToOne
	@JoinColumn(name="caste_category_master_id")
	private CasteCategoryMaster casteCategoryMaster;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="casteCategoryDetail")
	private List<PersonalDetail> personalDetails;

	public CasteCategoryDetail() {
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Boolean getCandidateCategoryStatus() {
		return this.candidateCategoryStatus;
	}

	public void setCandidateCategoryStatus(Boolean candidateCategoryStatus) {
		this.candidateCategoryStatus = candidateCategoryStatus;
	}

	public Timestamp getDobRegEndDate() {
		return this.dobRegEndDate;
	}

	public void setDobRegEndDate(Timestamp dobRegEndDate) {
		this.dobRegEndDate = dobRegEndDate;
	}

	public Timestamp getDobRegStartDate() {
		return this.dobRegStartDate;
	}

	public void setDobRegStartDate(Timestamp dobRegStartDate) {
		this.dobRegStartDate = dobRegStartDate;
	}

	public Integer getFees() {
		return this.fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}

	public Boolean getIsAgeRelaxation() {
		return this.isAgeRelaxation;
	}

	public void setIsAgeRelaxation(Boolean isAgeRelaxation) {
		this.isAgeRelaxation = isAgeRelaxation;
	}

	public Integer getMinPassingPercentage() {
		return this.minPassingPercentage;
	}

	public void setMinPassingPercentage(Integer minPassingPercentage) {
		this.minPassingPercentage = minPassingPercentage;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public CasteCategoryMaster getCasteCategoryMaster() {
		return this.casteCategoryMaster;
	}

	public void setCasteCategoryMaster(CasteCategoryMaster casteCategoryMaster) {
		this.casteCategoryMaster = casteCategoryMaster;
	}

	public List<PersonalDetail> getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setCasteCategoryDetail(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setCasteCategoryDetail(null);

		return personalDetail;
	}

}