package gov.cdac.icgPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class IcgMailReasonMaster extends EmailReasonMaster {

    public IcgMailReasonMaster() {
    }

    public IcgMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}
