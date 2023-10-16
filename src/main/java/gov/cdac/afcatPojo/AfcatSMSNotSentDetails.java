package gov.cdac.afcatPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sms_not_sent_details")
public class AfcatSMSNotSentDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sms_not_sent_details_sms_not_sent_details_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_not_sent_details_sms_not_sent_details_id_seq")
    @Column(name = "sms_not_sent_details_id")
    private Integer smsNotSentDetailsId;

    @ManyToOne
    @JoinColumn(name = "applicant_cred_id")
    private AfcatApplicantCredential afcatApplicantCredential;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "alternate_mobile")
    private String alternateMobile;

    @ManyToOne
    @JoinColumn(name = "sms_sent_id")
    private AfcatSMSSent afcatSMSSent;

    @Column(name = "is_sms_sent_on_primary")
    private Boolean isSMSSentOnPrimary;

    @Column(name = "is_sms_sent_on_alternate")
    private Boolean isSMSSentOnAlternate;

    @CreationTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    public AfcatSMSNotSentDetails() {
    }

    public AfcatSMSNotSentDetails(AfcatApplicantCredential afcatApplicantCredential, String mobile,
	    String alternateMobile, AfcatSMSSent afcatSMSSent, Boolean isSMSSentOnPrimary,
	    Boolean isSMSSentOnAlternate) {
	this.afcatApplicantCredential = afcatApplicantCredential;
	this.mobile = mobile;
	this.alternateMobile = alternateMobile;
	this.afcatSMSSent = afcatSMSSent;
	this.isSMSSentOnPrimary = isSMSSentOnPrimary;
	this.isSMSSentOnAlternate = isSMSSentOnAlternate;
    }

    public Integer getSmsNotSentDetailsId() {
	return smsNotSentDetailsId;
    }

    public void setSmsNotSentDetailsId(Integer smsNotSentDetailsId) {
	this.smsNotSentDetailsId = smsNotSentDetailsId;
    }

    public AfcatApplicantCredential getApplicantCredential() {
	return afcatApplicantCredential;
    }

    public void setApplicantCredential(AfcatApplicantCredential casbApplicantCredential) {
	this.afcatApplicantCredential = casbApplicantCredential;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getAlternateMobile() {
	return alternateMobile;
    }

    public void setAlternateMobile(String aleternateMobile) {
	this.alternateMobile = aleternateMobile;
    }

    public AfcatApplicantCredential getAfcatApplicantCredential() {
	return afcatApplicantCredential;
    }

    public void setAfcatApplicantCredential(AfcatApplicantCredential afcatApplicantCredential) {
	this.afcatApplicantCredential = afcatApplicantCredential;
    }

    public AfcatSMSSent getAfcatSMSSent() {
	return afcatSMSSent;
    }

    public void setAfcatSMSSent(AfcatSMSSent afcatSMSSent) {
	this.afcatSMSSent = afcatSMSSent;
    }

    public Boolean getIsSMSSentOnPrimary() {
	return isSMSSentOnPrimary;
    }

    public void setIsSMSSentOnPrimary(Boolean isSMSSentOnPrimary) {
	this.isSMSSentOnPrimary = isSMSSentOnPrimary;
    }

    public Boolean getIsSMSSentOnAlternate() {
	return isSMSSentOnAlternate;
    }

    public void setIsSMSSentOnAlternate(Boolean isSMSSentOnAlternate) {
	this.isSMSSentOnAlternate = isSMSSentOnAlternate;
    }

    public Timestamp getRecordTracking() {
	return recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

}
