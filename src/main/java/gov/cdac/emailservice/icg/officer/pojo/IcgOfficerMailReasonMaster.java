package gov.cdac.emailservice.icg.officer.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import gov.cdac.emailservice.models.EmailReasonMaster;

@Entity
@Table(name = "email_reason_master")
public class IcgOfficerMailReasonMaster extends EmailReasonMaster {

    public IcgOfficerMailReasonMaster() {
    }

    public IcgOfficerMailReasonMaster(Integer emailReasonMasterId) {
	super(emailReasonMasterId);
    }

}

