package gov.cdac.afcatPojo;

import gov.cdac.models.SMSTemplateMaster;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
