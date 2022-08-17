package gov.cdac.emailservice.afcat.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.SMSReasonMaster;


@Entity
@Table(name = "sms_reason_master")
public class AfcatSMSReasonMaster extends SMSReasonMaster {

    public AfcatSMSReasonMaster() {
    }

    public AfcatSMSReasonMaster(Integer smsReasonMasterId) {
	super(smsReasonMasterId);
    }

}
