package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * The persistent class for the batch_master database table.
 * 
 */
@Entity
@Table(name="batch_master")
@NamedQuery(name="BatchMaster.findAll", query="SELECT b FROM BatchMaster b")
@JsonIgnoreProperties({"applicantCredentials","casteCategoryDetails","examMasters","signUps","batchApplicantTypeMappings","batchPostMappings","smsSents"})
public class BatchMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCHID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCHID_GENERATOR")
	@Column(name="batch_id")
	private Integer batchId;

	@NotBlank(message = "Batch Code cannot be blank")
	@Column(name="batch_code")
	private String batchCode;

	@NotBlank(message = "Batch Name cannot be blank")
	@Column(name="batch_name")
	private String batchName;

	@NotNull(message = "Status cannot be blank" )
	@Column(name="batch_status")
	private Boolean batchStatus;

	@Column(name="end_date_time")
	private Timestamp endDateTime;

	@Column(name="last_date_time_of_payment")
	private Timestamp lastDateTimeOfPayment;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="start_date_time")
	private Timestamp startDateTime;

	//bi-directional many-to-one association to ApplicantCredential
	@OneToMany(mappedBy="batchMaster")
	private List<ApplicantCredential> applicantCredentials;
	
	//bi-directional many-to-one association to BatchApplicantTypeMapping
	@OneToMany(mappedBy="batchMaster")
	private List<BatchApplicantTypeMapping> batchApplicantTypeMappings;

	//bi-directional many-to-one association to BatchPostMapping
	@OneToMany(mappedBy="batchMaster")
	private List<BatchPostMapping> batchPostMappings;

	//bi-directional many-to-one association to CasteCategoryDetail
	@OneToMany(mappedBy="batchMaster")
	private List<CasteCategoryDetail> casteCategoryDetails;

	//bi-directional many-to-one association to ExamMaster
	@OneToMany(mappedBy="batchMaster")
	private List<ExamMaster> examMasters;

	//bi-directional many-to-one association to SignUp
	@OneToMany(mappedBy="batchMaster")
	private List<SignUp> signUps;
	
	//bi-directional many-to-one association to SmsSent
	@OneToMany(mappedBy="batchMaster")
	private List<IcgOfficerSMSSent> smsSents;
	

	public BatchMaster() {
	}

	public BatchMaster(Integer batchId2) {
		this.batchId=batchId2;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Boolean getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(Boolean batchStatus) {
		this.batchStatus = batchStatus;
	}

	public Timestamp getEndDateTime() {
		return this.endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Timestamp getLastDateTimeOfPayment() {
		return this.lastDateTimeOfPayment;
	}

	public void setLastDateTimeOfPayment(Timestamp lastDateTimeOfPayment) {
		this.lastDateTimeOfPayment = lastDateTimeOfPayment;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Timestamp getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public List<ApplicantCredential> getApplicantCredentials() {
		return this.applicantCredentials;
	}

	public void setApplicantCredentials(List<ApplicantCredential> applicantCredentials) {
		this.applicantCredentials = applicantCredentials;
	}

	public ApplicantCredential addApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().add(applicantCredential);
		applicantCredential.setBatchMaster(this);

		return applicantCredential;
	}

	public ApplicantCredential removeApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().remove(applicantCredential);
		applicantCredential.setBatchMaster(null);

		return applicantCredential;
	}

	public List<CasteCategoryDetail> getCasteCategoryDetails() {
		return this.casteCategoryDetails;
	}

	public void setCasteCategoryDetails(List<CasteCategoryDetail> casteCategoryDetails) {
		this.casteCategoryDetails = casteCategoryDetails;
	}

	public CasteCategoryDetail addCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		getCasteCategoryDetails().add(casteCategoryDetail);
		casteCategoryDetail.setBatchMaster(this);

		return casteCategoryDetail;
	}

	public CasteCategoryDetail removeCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		getCasteCategoryDetails().remove(casteCategoryDetail);
		casteCategoryDetail.setBatchMaster(null);

		return casteCategoryDetail;
	}

	public List<ExamMaster> getExamMasters() {
		return this.examMasters;
	}

	public void setExamMasters(List<ExamMaster> examMasters) {
		this.examMasters = examMasters;
	}

	public ExamMaster addExamMaster(ExamMaster examMaster) {
		getExamMasters().add(examMaster);
		examMaster.setBatchMaster(this);

		return examMaster;
	}

	public ExamMaster removeExamMaster(ExamMaster examMaster) {
		getExamMasters().remove(examMaster);
		examMaster.setBatchMaster(null);

		return examMaster;
	}

	public List<SignUp> getSignUps() {
		return this.signUps;
	}

	public void setSignUps(List<SignUp> signUps) {
		this.signUps = signUps;
	}

	public SignUp addSignUp(SignUp signUp) {
		getSignUps().add(signUp);
		signUp.setBatchMaster(this);

		return signUp;
	}

	public SignUp removeSignUp(SignUp signUp) {
		getSignUps().remove(signUp);
		signUp.setBatchMaster(null);

		return signUp;
	}

	public List<BatchApplicantTypeMapping> getBatchApplicantTypeMappings() {
		return batchApplicantTypeMappings;
	}

	public void setBatchApplicantTypeMappings(List<BatchApplicantTypeMapping> batchApplicantTypeMappings) {
		this.batchApplicantTypeMappings = batchApplicantTypeMappings;
	}

	public List<BatchPostMapping> getBatchPostMappings() {
		return batchPostMappings;
	}

	public void setBatchPostMappings(List<BatchPostMapping> batchPostMappings) {
		this.batchPostMappings = batchPostMappings;
	}

	public List<IcgOfficerSMSSent> getSmsSents() {
		return smsSents;
	}

	public void setSmsSents(List<IcgOfficerSMSSent> smsSents) {
		this.smsSents = smsSents;
	}

	
	
}