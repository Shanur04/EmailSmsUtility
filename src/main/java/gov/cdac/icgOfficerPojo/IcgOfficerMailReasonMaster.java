package gov.cdac.icgOfficerPojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import gov.cdac.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class IcgOfficerMailReasonMaster extends EmailReasonMaster {

    public IcgOfficerMailReasonMaster() {
    }

    public IcgOfficerMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}

