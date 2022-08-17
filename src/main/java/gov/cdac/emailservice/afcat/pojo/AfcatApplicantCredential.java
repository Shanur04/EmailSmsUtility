package gov.cdac.emailservice.afcat.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the applicant_credential database table.
 * 
 */
@Entity
@Table(name = "applicant_credential")
public class AfcatApplicantCredential implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "applicant_cred_id")
    private Long applicantCredId;

    private String emailid;

    private String password;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    public AfcatApplicantCredential(Long applicantCredId) {
	this.applicantCredId = applicantCredId;
    }

    public AfcatApplicantCredential() {
    }

    public Long getApplicantCredId() {
	return this.applicantCredId;
    }

    public void setApplicantCredId(Long applicantCredId) {
	this.applicantCredId = applicantCredId;
    }

    public String getEmailid() {
	return this.emailid;
    }

    public void setEmailid(String emailid) {
	this.emailid = emailid;
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

}