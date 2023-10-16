package gov.cdac.icgPojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.models.SMSReasonMaster;

@Entity
@Table(name = "sms_reason_master")
public class IcgSMSReasonMaster extends SMSReasonMaster {

    public IcgSMSReasonMaster() {
    }

    public IcgSMSReasonMaster(Integer smsReasonMasterId) {
	super(smsReasonMasterId);
    }

}