package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the applicant_type database table.
 * 
 */
@Entity
@Table(name="applicant_type")
@NamedQuery(name="ApplicantType.findAll", query="SELECT a FROM ApplicantType a")
@JsonIgnoreProperties({"applicantCredentials","batchApplicantTypeMappings","registrationStatuses"})
public class ApplicantType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_TYPE_APPLICANTTYPEID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_TYPE_APPLICANTTYPEID_GENERATOR")
	@Column(name="applicant_type_id")
	private Integer applicantTypeId;

	@Column(name="applicant_type_name")
	private String applicantTypeName;

	@Column(name="applicant_type_status")
	private Boolean applicantTypeStatus;

	@Column(name="is_cgcat_applicable")
	private Boolean isCgcatApplicable;

	@Column(name="is_psb_applicable")
	private Boolean isPsbApplicable;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@OneToMany(mappedBy="applicantType")
	private List<ApplicantCredential> applicantCredentials;

	//bi-directional many-to-one association to BatchApplicantTypeMapping
	@OneToMany(mappedBy="applicantType")
	private List<BatchApplicantTypeMapping> batchApplicantTypeMappings;

	
	//bi-directional many-to-one association to RegistrationStatus
	@OneToMany(mappedBy="applicantType")
	private List<RegistrationStatus> registrationStatuses;

	public ApplicantType() {
	}

	public Integer getApplicantTypeId() {
		return this.applicantTypeId;
	}

	public void setApplicantTypeId(Integer applicantTypeId) {
		this.applicantTypeId = applicantTypeId;
	}

	public String getApplicantTypeName() {
		return this.applicantTypeName;
	}

	public void setApplicantTypeName(String applicantTypeName) {
		this.applicantTypeName = applicantTypeName;
	}

	public Boolean getApplicantTypeStatus() {
		return this.applicantTypeStatus;
	}

	public void setApplicantTypeStatus(Boolean applicantTypeStatus) {
		this.applicantTypeStatus = applicantTypeStatus;
	}

	public Boolean getIsCgcatApplicable() {
		return this.isCgcatApplicable;
	}

	public void setIsCgcatApplicable(Boolean isCgcatApplicable) {
		this.isCgcatApplicable = isCgcatApplicable;
	}

	public Boolean getIsPsbApplicable() {
		return this.isPsbApplicable;
	}

	public void setIsPsbApplicable(Boolean isPsbApplicable) {
		this.isPsbApplicable = isPsbApplicable;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<ApplicantCredential> getApplicantCredentials() {
		return this.applicantCredentials;
	}

	public void setApplicantCredentials(List<ApplicantCredential> applicantCredentials) {
		this.applicantCredentials = applicantCredentials;
	}

	public ApplicantCredential addApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().add(applicantCredential);
		applicantCredential.setApplicantType(this);

		return applicantCredential;
	}

	public ApplicantCredential removeApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().remove(applicantCredential);
		applicantCredential.setApplicantType(null);

		return applicantCredential;
	}

	public List<BatchApplicantTypeMapping> getBatchApplicantTypeMappings() {
		return this.batchApplicantTypeMappings;
	}

	public void setBatchApplicantTypeMappings(List<BatchApplicantTypeMapping> batchApplicantTypeMappings) {
		this.batchApplicantTypeMappings = batchApplicantTypeMappings;
	}

	public BatchApplicantTypeMapping addBatchApplicantTypeMapping(BatchApplicantTypeMapping batchApplicantTypeMapping) {
		getBatchApplicantTypeMappings().add(batchApplicantTypeMapping);
		batchApplicantTypeMapping.setApplicantType(this);

		return batchApplicantTypeMapping;
	}

	public BatchApplicantTypeMapping removeBatchApplicantTypeMapping(BatchApplicantTypeMapping batchApplicantTypeMapping) {
		getBatchApplicantTypeMappings().remove(batchApplicantTypeMapping);
		batchApplicantTypeMapping.setApplicantType(null);

		return batchApplicantTypeMapping;
	}


	public List<RegistrationStatus> getRegistrationStatuses() {
		return this.registrationStatuses;
	}

	public void setRegistrationStatuses(List<RegistrationStatus> registrationStatuses) {
		this.registrationStatuses = registrationStatuses;
	}

	public RegistrationStatus addRegistrationStatus(RegistrationStatus registrationStatus) {
		getRegistrationStatuses().add(registrationStatus);
		registrationStatus.setApplicantType(this);

		return registrationStatus;
	}

	public RegistrationStatus removeRegistrationStatus(RegistrationStatus registrationStatus) {
		getRegistrationStatuses().remove(registrationStatus);
		registrationStatus.setApplicantType(null);

		return registrationStatus;
	}

}