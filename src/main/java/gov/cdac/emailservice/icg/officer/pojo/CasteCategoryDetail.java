package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the caste_category_details database table.
 * 
 */
@Entity
@Table(name="caste_category_details")
@NamedQuery(name="CasteCategoryDetail.findAll", query="SELECT c FROM CasteCategoryDetail c")
@JsonIgnoreProperties({"personalDetails","castewisePostwiseVacanciesMappings","castewisePostwiseDobrangeMappings"})
public class CasteCategoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORYID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORYID_GENERATOR")
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="candidate_category_status")
	private Boolean candidateCategoryStatus;

	//@Column(name="dob_reg_end_date")
	//private Timestamp dobRegEndDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob_reg_end_date")
	private Date dobRegEndDate;

	//@Column(name="dob_reg_start_date")
	//private Timestamp dobRegStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob_reg_start_date")
	private Date dobRegStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name="caste_certificate_validity_enddate")
	private Date casteCertificateValidityEndDate;

	@Column(name="fees")
	private Integer fees;

	@Column(name="is_age_relaxation")
	private Boolean isAgeRelaxation;


	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	//bi-directional many-to-one association to CasteCategoryMaster
	@ManyToOne
	@JoinColumn(name="caste_category_master_id")
	private CasteCategoryMaster casteCategoryMaster;


	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="casteCategoryDetail")
	private List<PersonalDetail> personalDetails;

	//bi-directional many-to-one association to CastewisePostwiseVacanciesMapping
	@OneToMany(mappedBy="casteCategoryDetail")
	private List<CastewisePostwiseVacanciesMapping> castewisePostwiseVacanciesMappings;
	
	//bi-directional many-to-one association to CastewisePostwiseDobrangeMapping
	@OneToMany(mappedBy="casteCategoryDetail")
	private List<CastewisePostwiseDobrangeMapping> castewisePostwiseDobrangeMappings;

	public CasteCategoryDetail() {
	}


	public Date getCasteCertificateValidityEndDate() {
		return casteCertificateValidityEndDate;
	}

	public void setCasteCertificateValidityEndDate(Date casteCertificateValidityEndDate) {
		this.casteCertificateValidityEndDate = casteCertificateValidityEndDate;
	}
	
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getCandidateCategoryStatus() {
		return this.candidateCategoryStatus;
	}

	public void setCandidateCategoryStatus(Boolean candidateCategoryStatus) {
		this.candidateCategoryStatus = candidateCategoryStatus;
	}


	public Date getDobRegEndDate() {
		return this.dobRegEndDate;
	}

	public void setDobRegEndDate(Date dobRegEndDate) {
		this.dobRegEndDate = dobRegEndDate;
	}

	public Date getDobRegStartDate() {
		return this.dobRegStartDate;
	}

	public void setDobRegStartDate(Date dobRegStartDate) {
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

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
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
	

	public List<CastewisePostwiseVacanciesMapping> getCastewisePostwiseVacanciesMappings() {
		return castewisePostwiseVacanciesMappings;
	}


	public void setCastewisePostwiseVacanciesMappings(
			List<CastewisePostwiseVacanciesMapping> castewisePostwiseVacanciesMappings) {
		this.castewisePostwiseVacanciesMappings = castewisePostwiseVacanciesMappings;
	}


	public List<CastewisePostwiseDobrangeMapping> getCastewisePostwiseDobrangeMappings() {
		return castewisePostwiseDobrangeMappings;
	}


	public void setCastewisePostwiseDobrangeMappings(
			List<CastewisePostwiseDobrangeMapping> castewisePostwiseDobrangeMappings) {
		this.castewisePostwiseDobrangeMappings = castewisePostwiseDobrangeMappings;
	}


	public CasteCategoryDetail(Integer categoryId, Boolean candidateCategoryStatus, Date dobRegEndDate,
			Date dobRegStartDate, Integer fees, Boolean isAgeRelaxation,
			Timestamp recordTracking, BatchMaster batchMaster, CasteCategoryMaster casteCategoryMaster,
			List<PersonalDetail> personalDetails) {
		this.categoryId = categoryId;
		this.candidateCategoryStatus = candidateCategoryStatus;
		this.dobRegEndDate = dobRegEndDate;
		this.dobRegStartDate = dobRegStartDate;
		this.fees = fees;
		this.isAgeRelaxation = isAgeRelaxation;
		this.recordTracking = recordTracking;
		this.batchMaster = batchMaster;
		this.casteCategoryMaster = casteCategoryMaster;
		this.personalDetails = personalDetails;
	}




}