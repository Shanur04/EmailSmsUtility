package gov.cdac.icgOfficerPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.SMSReasonMaster;


@Entity
@Table(name = "sms_reason_master")
public class SmsReasonMaster extends SMSReasonMaster {

    public SmsReasonMaster() {
    }

    public SmsReasonMaster(Integer smsReasonMasterId) {
	super(smsReasonMasterId);
    }

}
