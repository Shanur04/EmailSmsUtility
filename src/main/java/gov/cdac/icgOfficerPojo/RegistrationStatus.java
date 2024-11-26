package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the registration_status database table.
 * 
 */
@Entity
@Table(name="registration_status")
@NamedQuery(name="RegistrationStatus.findAll", query="SELECT r FROM RegistrationStatus r")
@JsonIgnoreProperties({"applicantCredentials","signUps"})
public class RegistrationStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGISTRATIONSTATUSID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRATIONSTATUSID_GENERATOR")
	@Column(name="registration_status_id")
	private Integer registrationStatusId;

	@Column(name="status")
	private String status;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "status_sequence")
	private Integer statusSequence;
	
	@Column(name = "remarks")
	private String remarks;

	//bi-directional many-to-one association to ApplicantCredential
	@OneToMany(mappedBy="registrationStatus")
	private List<ApplicantCredential> applicantCredentials;
	
	//bi-directional many-to-one association to ApplicantType
	@ManyToOne
	@JoinColumn(name="applicant_type_id")
	private ApplicantType applicantType;

	//bi-directional many-to-one association to SignUp
	@OneToMany(mappedBy="registrationStatus")
	private List<SignUp> signUps;

    public RegistrationStatus() {
		
	}

	public RegistrationStatus(Integer registrationStatusId) {
	this.registrationStatusId = registrationStatusId;
    }

	public Integer getRegistrationStatusId() {
		return this.registrationStatusId;
	}

	public void setRegistrationStatusId(Integer registrationStatusId) {
		this.registrationStatusId = registrationStatusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ApplicantCredential> getApplicantCredentials() {
		return this.applicantCredentials;
	}

	public void setApplicantCredentials(List<ApplicantCredential> applicantCredentials) {
		this.applicantCredentials = applicantCredentials;
	}

	public ApplicantCredential addApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().add(applicantCredential);
		applicantCredential.setRegistrationStatus(this);

		return applicantCredential;
	}

	public ApplicantCredential removeApplicantCredential(ApplicantCredential applicantCredential) {
		getApplicantCredentials().remove(applicantCredential);
		applicantCredential.setRegistrationStatus(null);

		return applicantCredential;
	}

	public List<SignUp> getSignUps() {
		return this.signUps;
	}

	public void setSignUps(List<SignUp> signUps) {
		this.signUps = signUps;
	}

	public SignUp addSignUp(SignUp signUp) {
		getSignUps().add(signUp);
		signUp.setRegistrationStatus(this);

		return signUp;
	}

	public SignUp removeSignUp(SignUp signUp) {
		getSignUps().remove(signUp);
		signUp.setRegistrationStatus(null);

		return signUp;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getStatusSequence() {
		return statusSequence;
	}

	public void setStatusSequence(Integer statusSequence) {
		this.statusSequence = statusSequence;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ApplicantType getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(ApplicantType applicantType) {
		this.applicantType = applicantType;
	}

	
}