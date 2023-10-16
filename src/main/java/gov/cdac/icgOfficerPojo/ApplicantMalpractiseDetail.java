package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the applicant_malpractise_details database table.
 * 
 */
@Entity
@Table(name="applicant_malpractise_details")
@NamedQuery(name="ApplicantMalpractiseDetail.findAll", query="SELECT a FROM ApplicantMalpractiseDetail a")
public class ApplicantMalpractiseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_MALPRACTISE_DETAILS_ID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_MALPRACTISE_DETAILS_ID_GENERATOR")
	private Integer id;

	@Column(name="applicant_cred_id")
	private Long applicantCredId;

	@Column(name="batch_id")
	private String batchId;
	
	@Column(name="education_level_id")
	private Integer educationLevelId;

	@Column(name="date_of_birth")
	private Timestamp dateOfBirth;

	@Column(name="emailid")
	private String emailid;

	@Column(name="father_name")
	private String fatherName;

	@Column(name="hallticketno")
	private String hallticketno;

	@Column(name="mother_name")
	private String motherName;

	@Column(name="name")
	private String name;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="registration_no")
	private String registrationNo;

	@Column(name="remarks")
	private String remarks;

	@Column(name="hsc_or_diploma_certificate_number")
	private String hscOrDiplomaCertificateNumber;

	@Column(name="status_description")
	private String statusDescription;

	//bi-directional many-to-one association to StatusMaster
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusMaster statusMaster;

	public ApplicantMalpractiseDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getApplicantCredId() {
		return this.applicantCredId;
	}

	public void setApplicantCredId(Long applicantCredId) {
		this.applicantCredId = applicantCredId;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getHallticketno() {
		return this.hallticketno;
	}

	public void setHallticketno(String hallticketno) {
		this.hallticketno = hallticketno;
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

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHscOrDiplomaCertificateNumber() {
		return hscOrDiplomaCertificateNumber;
	}

	public void setHscOrDiplomaCertificateNumber(String hscOrDiplomaCertificateNumber) {
		this.hscOrDiplomaCertificateNumber = hscOrDiplomaCertificateNumber;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public StatusMaster getStatusMaster() {
		return this.statusMaster;
	}

	public void setStatusMaster(StatusMaster statusMaster) {
		this.statusMaster = statusMaster;
	}

	public Integer getEducationLevelId() {
		return educationLevelId;
	}

	public void setEducationLevelId(Integer educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	
}