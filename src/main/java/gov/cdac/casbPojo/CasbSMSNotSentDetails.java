package gov.cdac.casbPojo;

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
public class CasbSMSNotSentDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "sms_not_sent_details_sms_not_sent_details_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_not_sent_details_sms_not_sent_details_id_seq")
    @Column(name = "sms_not_sent_details_id")
    private Integer smsNotSentDetailsId;

    @ManyToOne
    @JoinColumn(name = "applicant_cred_id")
    private CasbApplicantCredential casbApplicantCredential;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "alternate_mobile")
    private String alternateMobile;

    @ManyToOne
    @JoinColumn(name = "sms_sent_id")
    private CasbSMSSent casbSMSSent;

    @Column(name = "is_sms_sent_on_primary")
    private Boolean isSMSSentOnPrimary;

    @Column(name = "is_sms_sent_on_alternate")
    private Boolean isSMSSentOnAlternate;

    @CreationTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    public CasbSMSNotSentDetails() {
    }

    public CasbSMSNotSentDetails(CasbApplicantCredential casbApplicantCredential, String mobile, String alternateMobile,
	    CasbSMSSent casbSMSSent, Boolean isSMSSentOnPrimary, Boolean isSMSSentOnAlternate) {
	this.casbApplicantCredential = casbApplicantCredential;
	this.mobile = mobile;
	this.alternateMobile = alternateMobile;
	this.casbSMSSent = casbSMSSent;
	this.isSMSSentOnPrimary = isSMSSentOnPrimary;
	this.isSMSSentOnAlternate = isSMSSentOnAlternate;
    }

    public Integer getSmsNotSentDetailsId() {
	return smsNotSentDetailsId;
    }

    public void setSmsNotSentDetailsId(Integer smsNotSentDetailsId) {
	this.smsNotSentDetailsId = smsNotSentDetailsId;
    }

    public CasbApplicantCredential getApplicantCredential() {
	return casbApplicantCredential;
    }

    public void setApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
	this.casbApplicantCredential = casbApplicantCredential;
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


    public CasbApplicantCredential getCasbApplicantCredential() {
        return casbApplicantCredential;
    }

    public void setCasbApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
        this.casbApplicantCredential = casbApplicantCredential;
    }

    public CasbSMSSent getCasbSMSSent() {
        return casbSMSSent;
    }

    public void setCasbSMSSent(CasbSMSSent casbSMSSent) {
        this.casbSMSSent = casbSMSSent;
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
