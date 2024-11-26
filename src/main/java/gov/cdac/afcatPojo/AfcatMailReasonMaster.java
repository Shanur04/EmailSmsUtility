package gov.cdac.afcatPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class AfcatMailReasonMaster extends EmailReasonMaster {

    public AfcatMailReasonMaster() {
    }

    public AfcatMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}
