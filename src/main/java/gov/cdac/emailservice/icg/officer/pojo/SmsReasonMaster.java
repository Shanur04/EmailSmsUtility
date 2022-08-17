package gov.cdac.emailservice.icg.officer.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.SMSReasonMaster;


@Entity
@Table(name = "sms_reason_master")
public class SmsReasonMaster extends SMSReasonMaster {

    public SmsReasonMaster() {
    }

    public SmsReasonMaster(Integer smsReasonMasterId) {
	super(smsReasonMasterId);
    }

}
