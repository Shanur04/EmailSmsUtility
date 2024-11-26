package gov.cdac.icgPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.SMSTemplateMaster;

@Entity
@Table(name = "sms_template_master")
public class IcgSMSTemplateMaster extends SMSTemplateMaster {

    public IcgSMSTemplateMaster() {
    }

    public IcgSMSTemplateMaster(SMSTemplateMaster smsTemplateMaster) {
	setSampleMessage(smsTemplateMaster.getSampleMessage());
	setTemplateMasterId(smsTemplateMaster.getTemplateMasterId());
    }

}
