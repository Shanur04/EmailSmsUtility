package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import gov.cdac.models.SMSTemplateMaster;

/**
 * The persistent class for the sms_template_master database table.
 * 
 */
@Entity
@Table(name = "sms_template_master")
public class SmsTemplateMaster extends SMSTemplateMaster {
	public SmsTemplateMaster() {
    }

    public SmsTemplateMaster(SMSTemplateMaster smsTemplateMaster) {
	setSampleMessage(smsTemplateMaster.getSampleMessage());
	setTemplateMasterId(smsTemplateMaster.getTemplateMasterId());
    }


}