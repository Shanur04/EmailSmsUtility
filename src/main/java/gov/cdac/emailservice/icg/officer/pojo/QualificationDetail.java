package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


/**
 * The persistent class for the qualification_details database table.
 * 
 */
@Entity
@Table(name="qualification_details")
@NamedQuery(name="QualificationDetail.findAll", query="SELECT q FROM QualificationDetail q")
public class QualificationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUALIFICATIONDETAILSID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUALIFICATIONDETAILSID_GENERATOR")
	@Column(name="qualification_details_id")
	private Long qualificationDetailsId;
	
	@Column(name="annual_or_semester")
	private String annualOrSemester;
	
	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="semester_or_annual_marksheet_path")
	private String semesterOrAnnualMarksheetPath;

	@Column(name="semester_or_annual_percentage")
	private String semesterOrAnnualPercentage;

	@Column(name="semester_sequence")
	private Integer semesterSequence;

	@Column(name="subject_or_semester")
	private String subjectOrSemester;

	@Column(name="subject_or_semester_obtained")
	private String subjectOrSemesterObtained;

	@Column(name="subject_or_semester_total")
	private String subjectOrSemesterTotal;
	
	
	
	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;

	//bi-directional many-to-one association to QualificationMaster
	@ManyToOne
	@JoinColumn(name="qualification_id")
	private QualificationMaster qualificationMaster;

	public QualificationDetail() {
	}

	public Long getQualificationDetailsId() {
		return this.qualificationDetailsId;
	}

	public void setQualificationDetailsId(Long qualificationDetailsId) {
		this.qualificationDetailsId = qualificationDetailsId;
	}

	public String getSemesterOrAnnualMarksheetPath() {
		return semesterOrAnnualMarksheetPath;
	}

	public void setSemesterOrAnnualMarksheetPath(String semesterOrAnnualMarksheetPath) {
		this.semesterOrAnnualMarksheetPath = semesterOrAnnualMarksheetPath;
	}

	public String getSemesterOrAnnualPercentage() {
		return semesterOrAnnualPercentage;
	}

	public void setSemesterOrAnnualPercentage(String semesterOrAnnualPercentage) {
		this.semesterOrAnnualPercentage = semesterOrAnnualPercentage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getSubjectOrSemester() {
		return this.subjectOrSemester;
	}

	public void setSubjectOrSemester(String subjectOrSemester) {
		this.subjectOrSemester = subjectOrSemester;
	}

	public String getSubjectOrSemesterObtained() {
		return this.subjectOrSemesterObtained;
	}

	public void setSubjectOrSemesterObtained(String subjectOrSemesterObtained) {
		this.subjectOrSemesterObtained = subjectOrSemesterObtained;
	}

	public String getSubjectOrSemesterTotal() {
		return this.subjectOrSemesterTotal;
	}

	public void setSubjectOrSemesterTotal(String subjectOrSemesterTotal) {
		this.subjectOrSemesterTotal = subjectOrSemesterTotal;
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

	public QualificationMaster getQualificationMaster() {
		return this.qualificationMaster;
	}

	public void setQualificationMaster(QualificationMaster qualificationMaster) {
		this.qualificationMaster = qualificationMaster;
	}

	public String getAnnualOrSemester() {
		return this.annualOrSemester;
	}

	public void setAnnualOrSemester(String annualOrSemester) {
		this.annualOrSemester = annualOrSemester;
	}
	public Integer getSemesterSequence() {
		return semesterSequence;
	}

	public void setSemesterSequence(Integer semesterSequence) {
		this.semesterSequence = semesterSequence;
	}

	@Override
	public String toString() {
		return "QualificationDetail [qualificationDetailsId=" + qualificationDetailsId + ", annualOrSemester="
				+ annualOrSemester + ", recordTracking=" + recordTracking + ", semesterOrAnnualMarksheetPath="
				+ semesterOrAnnualMarksheetPath + ", semesterOrAnnualPercentage=" + semesterOrAnnualPercentage
				+ ", semesterSequence=" + semesterSequence + ", subjectOrSemester=" + subjectOrSemester
				+ ", subjectOrSemesterObtained=" + subjectOrSemesterObtained + ", subjectOrSemesterTotal="
				+ subjectOrSemesterTotal + "]";
	}
	
	
}