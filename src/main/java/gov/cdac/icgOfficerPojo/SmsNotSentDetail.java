package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


/**
 * The persistent class for the sms_not_sent_details database table.
 * 
 */
@Entity
@Table(name = "sms_not_sent_details")
public class SmsNotSentDetail implements Serializable {
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
    private IcgOfficerSMSSent SMSSent;

    @Column(name = "is_sms_sent_on_primary")
    private Boolean isSMSSentOnPrimary;

    @Column(name = "is_sms_sent_on_alternate")
    private Boolean isSMSSentOnAlternate;

    @CreationTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    public SmsNotSentDetail() {
    }

    public SmsNotSentDetail(ApplicantCredential appCred, String mobile, String alternateMobile,
    		IcgOfficerSMSSent sMSSent, Boolean isSMSSentOnPrimary, Boolean isSMSSentOnAlternate) {
	this.applicantCredential = appCred;
	this.mobile = mobile;
	this.alternateMobile = alternateMobile;
	this.SMSSent = sMSSent;
	this.isSMSSentOnPrimary = isSMSSentOnPrimary;
	this.isSMSSentOnAlternate = isSMSSentOnAlternate;
    }

    public SmsNotSentDetail(gov.cdac.icgOfficerPojo.ApplicantCredential appCred, String mobileNumber, Object object, IcgOfficerSMSSent smsSent2,
			boolean b, Object object2) {
    	this.applicantCredential = appCred;
    	this.mobile = mobileNumber;
    	this.alternateMobile = (String) object;
    	this.SMSSent = smsSent2;
    	this.isSMSSentOnPrimary = b;
    	this.isSMSSentOnAlternate = (Boolean) object2;
        }

	public Integer getSmsNotSentDetailsId() {
	return smsNotSentDetailsId;
    }

    public void setSmsNotSentDetailsId(Integer smsNotSentDetailsId) {
	this.smsNotSentDetailsId = smsNotSentDetailsId;
    }

    public  gov.cdac.icgOfficerPojo.ApplicantCredential getApplicantCredential() {
	return applicantCredential;
    }

    public void setApplicantCredential( gov.cdac.icgOfficerPojo.ApplicantCredential applicantCredential) {
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

    public IcgOfficerSMSSent getSMSSent() {
	return SMSSent;
    }

    public void setSMSSent(IcgOfficerSMSSent sMSSent) {
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
