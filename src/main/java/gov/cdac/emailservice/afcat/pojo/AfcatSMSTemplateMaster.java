package gov.cdac.emailservice.afcat.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.SMSTemplateMaster;

@Entity
@Table(name = "sms_template_master")
public class AfcatSMSTemplateMaster extends SMSTemplateMaster {

    public AfcatSMSTemplateMaster() {
    }

    public AfcatSMSTemplateMaster(SMSTemplateMaster smsTemplateMaster) {
	setSampleMessage(smsTemplateMaster.getSampleMessage());
	setTemplateMasterId(smsTemplateMaster.getTemplateMasterId());
    }

}
