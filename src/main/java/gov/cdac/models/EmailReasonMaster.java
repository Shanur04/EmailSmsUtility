package gov.cdac.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class EmailReasonMaster implements Serializable {

    @Id
    @Column(name = "email_reason_master_id")
    private Integer EmailReasonMasterId;

    @Column(name = "email_reason")
    private String emailReason;

    public EmailReasonMaster() {
    }

    public EmailReasonMaster(Integer emailReasonMasterId) {
	this.EmailReasonMasterId = emailReasonMasterId;
    }

	public Integer getEmailReasonMasterId() {
		return EmailReasonMasterId;
	}

	public void setEmailReasonMasterId(Integer emailReasonMasterId) {
		EmailReasonMasterId = emailReasonMasterId;
	}

	public String getEmailReason() {
		return emailReason;
	}

	public void setEmailReason(String emailReason) {
		this.emailReason = emailReason;
	}
}
