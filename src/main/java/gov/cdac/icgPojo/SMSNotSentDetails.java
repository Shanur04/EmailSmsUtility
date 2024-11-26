package gov.cdac.icgPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sms_not_sent_details")
public class SMSNotSentDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sms_not_sent_details_sms_not_sent_details_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_not_sent_details_sms_not_sent_details_id_seq")
    @Column(name = "sms_not_sent_details_id")
    private Integer smsNotSentDetailsId;

    @ManyToOne
    @JoinColumn(name = "applicant_cred_id")
    private ApplicantCredential applicantCredential;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "alternate_mobile")
    private String alternateMobile;

    @ManyToOne
    @JoinColumn(name = "sms_sent_id")
    private SMSSent SMSSent;

    @Column(name = "is_sms_sent_on_primary")
    private Boolean isSMSSentOnPrimary;

    @Column(name = "is_sms_sent_on_alternate")
    private Boolean isSMSSentOnAlternate;

    @CreationTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    public SMSNotSentDetails() {
    }

    public SMSNotSentDetails(ApplicantCredential applicantCredential, String mobile, String alternateMobile,
	    SMSSent sMSSent, Boolean isSMSSentOnPrimary, Boolean isSMSSentOnAlternate) {
	this.applicantCredential = applicantCredential;
	this.mobile = mobile;
	this.alternateMobile = alternateMobile;
	this.SMSSent = sMSSent;
	this.isSMSSentOnPrimary = isSMSSentOnPrimary;
	this.isSMSSentOnAlternate = isSMSSentOnAlternate;
    }

    public Integer getSmsNotSentDetailsId() {
	return smsNotSentDetailsId;
    }

    public void setSmsNotSentDetailsId(Integer smsNotSentDetailsId) {
	this.smsNotSentDetailsId = smsNotSentDetailsId;
    }

    public ApplicantCredential getApplicantCredential() {
	return applicantCredential;
    }

    public void setApplicantCredential(ApplicantCredential applicantCredential) {
	this.applicantCredential = applicantCredential;
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

    public SMSSent getSMSSent() {
	return SMSSent;
    }

    public void setSMSSent(SMSSent sMSSent) {
	SMSSent = sMSSent;
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
