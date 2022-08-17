package gov.cdac.emailservice.casb.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the applicant_credential database table.
 * 
 */
@Entity
@Table(name = "applicant_credential")
public class CasbApplicantCredential implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicant_cred_seq")
    @SequenceGenerator(name = "applicant_cred_seq", sequenceName = "applicant_credential_registration_no_seq", allocationSize = 1)
    @Column(name = "applicant_cred_id")
    private Long applicantCredId;

    private String emailid;

    private String password;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "secret_answer")
    private String secretAnswer;

    @Column(name = "secret_question")
    private String secretQuestion;

    @Column(name = "is_rejected")
    private boolean isRejected;

    @Column(name = "rejected_reason")
    private String rejectedReason;

    @Column(name = "is_password_reset")
    private Boolean isPasswordReset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rejected_by_system_user_cred_id")
    private CasbSystemUserCredential casbSystemUserCredential;

    public CasbApplicantCredential(Long applicantCredId) {
	this.applicantCredId = applicantCredId;
    }

    public Boolean getIsPasswordReset() {
	return isPasswordReset;
    }

    public void setIsPasswordReset(Boolean isPasswordReset) {
	this.isPasswordReset = isPasswordReset;
    }

    public boolean isRejected() {
	return isRejected;
    }

    public void setRejected(boolean isRejected) {
	this.isRejected = isRejected;
    }

    public String getRejectedReason() {
	return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
	this.rejectedReason = rejectedReason;
    }

    public CasbSystemUserCredential getSystemUserCredential() {
	return casbSystemUserCredential;
    }

    public void setSystemUserCredential(CasbSystemUserCredential casbSystemUserCredential) {
	this.casbSystemUserCredential = casbSystemUserCredential;
    }

    // bi-directional many-to-one association to PersonalDetail
    @OneToMany(mappedBy = "casbApplicantCredential")
    private List<CasbPersonalDetail> casbPersonalDetails;

    public CasbApplicantCredential() {
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

    public String getSecretAnswer() {
	return this.secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
	this.secretAnswer = secretAnswer;
    }

    public String getSecretQuestion() {
	return this.secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
	this.secretQuestion = secretQuestion;
    }

    public List<CasbPersonalDetail> getPersonalDetails() {
	return this.casbPersonalDetails;
    }

    public void setPersonalDetails(List<CasbPersonalDetail> casbPersonalDetails) {
	this.casbPersonalDetails = casbPersonalDetails;
    }

    public CasbPersonalDetail addPersonalDetail(CasbPersonalDetail casbPersonalDetail) {
	getPersonalDetails().add(casbPersonalDetail);
	casbPersonalDetail.setApplicantCredential(this);

	return casbPersonalDetail;
    }

    public CasbPersonalDetail removePersonalDetail(CasbPersonalDetail casbPersonalDetail) {
	getPersonalDetails().remove(casbPersonalDetail);
	casbPersonalDetail.setApplicantCredential(null);

	return casbPersonalDetail;
    }

}