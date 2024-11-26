package gov.cdac.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class SMSReasonMaster implements Serializable {

    @Id
    @Column(name = "sms_reason_master_id")
    private Integer SMSReasonMasterId;

    @Column(name = "sms_reason")
    private String SMSReason;

    public SMSReasonMaster() {
    }

    public SMSReasonMaster(Integer smsReasonMasterId) {
	this.SMSReasonMasterId = smsReasonMasterId;
    }

	public Integer getSMSReasonMasterId() {
		return SMSReasonMasterId;
	}

	public void setSMSReasonMasterId(Integer sMSReasonMasterId) {
		SMSReasonMasterId = sMSReasonMasterId;
	}

	public String getSMSReason() {
		return SMSReason;
	}

	public void setSMSReason(String sMSReason) {
		SMSReason = sMSReason;
	}



}
