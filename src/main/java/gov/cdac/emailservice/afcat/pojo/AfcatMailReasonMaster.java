package gov.cdac.emailservice.afcat.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class AfcatMailReasonMaster extends EmailReasonMaster {

    public AfcatMailReasonMaster() {
    }

    public AfcatMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}
