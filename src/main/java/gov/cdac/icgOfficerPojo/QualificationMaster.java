package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the qualification_master database table.
 * 
 */
@Entity
@Table(name="qualification_master")
@NamedQuery(name="QualificationMaster.findAll", query="SELECT q FROM QualificationMaster q")
@JsonIgnoreProperties({"qualificationDetails"})
public class QualificationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUALIFICATIONID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUALIFICATIONID_GENERATOR")
	@Column(name="qualification_id")
	private Long qualificationId;

	@Column(name="aggregate_grade_or_cgpa")
	private String aggregateGradeOrCgpa;

	@Column(name="aggregate_percentage")
	private String aggregatePercentage;
	
	@Column(name="appearing_with_no_backlogs")
	private Boolean appearingWithNoBacklogs;
	
	@Column(name="average_calculated_percentage")
	private Boolean averageCalculatedPercentage;

	@Column(name="certificate_number")
	private String certificateNumber;

	@Column(name="certificate_path")
	private String certificatePath;

	@Column(name="conversion_formula_docpath_from_cgpa_or_grade_to_percentage")
	private String conversionFormulaDocpathFromCgpaOrGradeToPercentage;
	
	@Column(name="education_board_or_institute_or_university")
	private String educationBoardOrInstituteOrUniversity;
	
	@Column(name="is_pass_declared")
	private Boolean passDeclared;
	
	@Column(name="is_percentagegradecgpa")
	private String isPercentagegradecgpa;
	
	@Column(name="is_qualifying_degree")
	private Boolean isQualifyingDegree;
	
	@Column(name="marksheet_path")
	private String marksheetPath;
	
	@Column(name="provisional_certificate_path")
	private String provisionalCertificatePath;
	
	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@Column(name="total_no_of_subject_semester")
	private Integer totalNoOfSubjectSemester;
	
	@Column(name="duration")
	private Integer courseDuration;
	
	@Column(name="year_of_passing")
	private String yearOfPassing;

	@Column(name="other_degree_name")
	private String otherDegreeName;
	
	@Column(name="other_stream_name")
	private String otherStreamName;
	
	@Column(name="is_diploma_with_maths_phy")
	private Boolean isDiplomaWithMathsPhy;
	
	@Column(name="is_lateral_entry_for_graduation")
	private Boolean isLateralEntryForGraduation;
	
	//bi-directional many-to-one association to QualificationDetail
	@OneToMany(mappedBy="qualificationMaster")
	private List<QualificationDetail> qualificationDetails;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to BatchPostDegreeStreamMapping
	@ManyToOne
	@JoinColumn(name="batch_post_degree_stream_mapping_id")
	private BatchPostDegreeStreamMapping batchPostDegreeStreamMapping;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;
	
	public QualificationMaster() {
	}

	public QualificationMaster(Long qualificationId) {
		super();
		this.qualificationId = qualificationId;
	}


	
	public Boolean getIsLateralEntryForGraduation() {
		return isLateralEntryForGraduation;
	}

	public void setIsLateralEntryForGraduation(Boolean isLateralEntryForGraduation) {
		this.isLateralEntryForGraduation = isLateralEntryForGraduation;
	}

	public Boolean getIsDiplomaWithMathsPhy() {
		return isDiplomaWithMathsPhy;
	}

	public void setIsDiplomaWithMathsPhy(Boolean isDiplomaWithMathsPhy) {
		this.isDiplomaWithMathsPhy = isDiplomaWithMathsPhy;
	}

	public Integer getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}

	public Long getQualificationId() {
		return this.qualificationId;
	}

	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getAggregateGradeOrCgpa() {
		return this.aggregateGradeOrCgpa;
	}

	public void setAggregateGradeOrCgpa(String aggregateGradeOrCgpa) {
		this.aggregateGradeOrCgpa = aggregateGradeOrCgpa;
	}

	public String getAggregatePercentage() {
		return this.aggregatePercentage;
	}

	public void setAggregatePercentage(String aggregatePercentage) {
		this.aggregatePercentage = aggregatePercentage;
	}

	public String getCertificateNumber() {
		return this.certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getCertificatePath() {
		return this.certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getConversionFormulaDocpathFromCgpaOrGradeToPercentage() {
		return this.conversionFormulaDocpathFromCgpaOrGradeToPercentage;
	}

	public void setConversionFormulaDocpathFromCgpaOrGradeToPercentage(String conversionFormulaDocpathFromCgpaOrGradeToPercentage) {
		this.conversionFormulaDocpathFromCgpaOrGradeToPercentage = conversionFormulaDocpathFromCgpaOrGradeToPercentage;
	}

	public String getIsPercentagegradecgpa() {
		return this.isPercentagegradecgpa;
	}

	public void setIsPercentagegradecgpa(String isPercentagegradecgpa) {
		this.isPercentagegradecgpa = isPercentagegradecgpa;
	}

	public String getMarksheetPath() {
		return this.marksheetPath;
	}

	public void setMarksheetPath(String marksheetPath) {
		this.marksheetPath = marksheetPath;
	}

	public Boolean getPassDeclared() {
		return this.passDeclared;
	}

	public void setPassDeclared(Boolean passDeclared) {
		this.passDeclared = passDeclared;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getTotalNoOfSubjectSemester() {
		return this.totalNoOfSubjectSemester;
	}

	public void setTotalNoOfSubjectSemester(Integer totalNoOfSubjectSemester) {
		this.totalNoOfSubjectSemester = totalNoOfSubjectSemester;
	}

	public List<QualificationDetail> getQualificationDetails() {
		return this.qualificationDetails;
	}

	public void setQualificationDetails(List<QualificationDetail> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}

	public QualificationDetail addQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().add(qualificationDetail);
		qualificationDetail.setQualificationMaster(this);

		return qualificationDetail;
	}

	public QualificationDetail removeQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().remove(qualificationDetail);
		qualificationDetail.setQualificationMaster(null);

		return qualificationDetail;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}
	
	
	public Boolean getAverageCalculatedPercentage() {
		return averageCalculatedPercentage;
	}

	public void setAverageCalculatedPercentage(Boolean averageCalculatedPercentage) {
		this.averageCalculatedPercentage = averageCalculatedPercentage;
	}

	
	public Boolean getAppearingWithNoBacklogs() {
		return appearingWithNoBacklogs;
	}

	public void setAppearingWithNoBacklogs(Boolean appearingWithNoBacklogs) {
		this.appearingWithNoBacklogs = appearingWithNoBacklogs;
	}

	public String getEducationBoardOrInstituteOrUniversity() {
		return educationBoardOrInstituteOrUniversity;
	}

	public void setEducationBoardOrInstituteOrUniversity(String educationBoardOrInstituteOrUniversity) {
		this.educationBoardOrInstituteOrUniversity = educationBoardOrInstituteOrUniversity;
	}

	public Boolean getIsQualifyingDegree() {
		return isQualifyingDegree;
	}

	public void setIsQualifyingDegree(Boolean isQualifyingDegree) {
		this.isQualifyingDegree = isQualifyingDegree;
	}

	public String getProvisionalCertificatePath() {
		return provisionalCertificatePath;
	}

	public void setProvisionalCertificatePath(String provisionalCertificatePath) {
		this.provisionalCertificatePath = provisionalCertificatePath;
	}

	public String getYearOfPassing() {
		return yearOfPassing;
	}

	public void setYearOfPassing(String yearOfPassing) {
		this.yearOfPassing = yearOfPassing;
	}

	public BatchPostDegreeStreamMapping getBatchPostDegreeStreamMapping() {
		return batchPostDegreeStreamMapping;
	}

	public void setBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		this.batchPostDegreeStreamMapping = batchPostDegreeStreamMapping;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOtherDegreeName() {
		return otherDegreeName;
	}

	public void setOtherDegreeName(String otherDegreeName) {
		this.otherDegreeName = otherDegreeName;
	}

	public String getOtherStreamName() {
		return otherStreamName;
	}

	public void setOtherStreamName(String otherStreamName) {
		this.otherStreamName = otherStreamName;
	}

	

}