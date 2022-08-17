package gov.cdac.emailservice.icg.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class IcgMailReasonMaster extends EmailReasonMaster {

    public IcgMailReasonMaster() {
    }

    public IcgMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}
