package gov.cdac.casbPojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class CasbMailReasonMaster extends EmailReasonMaster {

    public CasbMailReasonMaster() {
    }

    public CasbMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}