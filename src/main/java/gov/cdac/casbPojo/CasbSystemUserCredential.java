package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the system_user_credentials database table.
 * 
 */
@Entity
@Table(name = "system_user_credentials")
public class CasbSystemUserCredential implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "system_user_cred_id")
    private Long systemUserCredId;

    private String email;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    private String name;

    private String password;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    private String role;

    private Boolean status;

    @OneToMany(mappedBy = "casbSystemUserCredential")
    private List<CasbApplicantCredential> casbApplicantCredentials;

    public CasbSystemUserCredential() {
    }

    public Long getSystemUserCredId() {
	return this.systemUserCredId;
    }

    public void setSystemUserCredId(Long systemUserCredId) {
	this.systemUserCredId = systemUserCredId;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Long getMobileNumber() {
	return this.mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public String getRole() {
	return this.role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Boolean getStatus() {
	return this.status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public List<CasbApplicantCredential> getApplicantCredentials() {
	return casbApplicantCredentials;
    }

    public void setApplicantCredentials(List<CasbApplicantCredential> casbApplicantCredentials) {
	this.casbApplicantCredentials = casbApplicantCredentials;
    }

    public CasbApplicantCredential addApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
	getApplicantCredentials().add(casbApplicantCredential);
	casbApplicantCredential.setSystemUserCredential(this);

	return casbApplicantCredential;
    }

    public CasbApplicantCredential removeApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
	getApplicantCredentials().remove(casbApplicantCredential);
	casbApplicantCredential.setSystemUserCredential(null);

	return casbApplicantCredential;
    }

}