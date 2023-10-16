package gov.cdac.casbPojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.models.SMSTemplateMaster;

@Entity
@Table(name = "sms_template_master")
public class CasbSMSTemplateMaster extends SMSTemplateMaster {

    public CasbSMSTemplateMaster() {
    }

    public CasbSMSTemplateMaster(SMSTemplateMaster smsTemplateMaster) {
	setSampleMessage(smsTemplateMaster.getSampleMessage());
	setTemplateMasterId(smsTemplateMaster.getTemplateMasterId());
    }

}
