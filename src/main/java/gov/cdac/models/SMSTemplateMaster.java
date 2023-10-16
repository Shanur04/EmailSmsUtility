package gov.cdac.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class SMSTemplateMaster implements Serializable {

    @Id
    @SequenceGenerator(name = "sms_template_master_sms_template_master_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_template_master_sms_template_master_id_seq")
    @Column(name = "sms_template_master_id")
    private Integer SMSTemplateMasterId;

    @Column(name = "sample_message")
    private String sampleMessage;

    @Column(name = "template_master_id")
    private String templateMasterId;

    public SMSTemplateMaster() {
    }

    public Integer getSMSTemplateMasterId() {
	return SMSTemplateMasterId;
    }

    public void setSMSTemplateMasterId(Integer sMSTemplateMasterId) {
	SMSTemplateMasterId = sMSTemplateMasterId;
    }

    public String getSampleMessage() {
	return sampleMessage;
    }

    public void setSampleMessage(String sampleMessage) {
	this.sampleMessage = sampleMessage;
    }

    public String getTemplateMasterId() {
	return templateMasterId;
    }

    public void setTemplateMasterId(String templateMasterId) {
	this.templateMasterId = templateMasterId;
    }

}
