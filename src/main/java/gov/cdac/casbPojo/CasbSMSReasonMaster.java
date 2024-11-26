package gov.cdac.casbPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.SMSReasonMaster;

@Entity
@Table(name = "sms_reason_master")
public class CasbSMSReasonMaster extends SMSReasonMaster {

    public CasbSMSReasonMaster() {
    }

    public CasbSMSReasonMaster(Integer smsReasonMasterId) {
	super(smsReasonMasterId);
    }

}
