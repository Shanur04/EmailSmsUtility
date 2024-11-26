package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the email_reason_type database table.
 * 
 */
@Entity
@Table(name="email_reason_type")
@NamedQuery(name="EmailReasonType.findAll", query="SELECT e FROM EmailReasonType e")
public class EmailReasonType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMAIL_REASON_TYPE_EMAILREASONTYPEID_GENERATOR",  allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMAIL_REASON_TYPE_EMAILREASONTYPEID_GENERATOR")
	@Column(name="email_reason_type_id")
	private Integer emailReasonTypeId;

	@Column(name="email_reason_status")
	private Boolean emailReasonStatus;

	@Column(name="email_reason_type")
	private String emailReasonType;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	public EmailReasonType() {
	}

	public Integer getEmailReasonTypeId() {
		return this.emailReasonTypeId;
	}

	public void setEmailReasonTypeId(Integer emailReasonTypeId) {
		this.emailReasonTypeId = emailReasonTypeId;
	}

	public Boolean getEmailReasonStatus() {
		return this.emailReasonStatus;
	}

	public void setEmailReasonStatus(Boolean emailReasonStatus) {
		this.emailReasonStatus = emailReasonStatus;
	}

	public String getEmailReasonType() {
		return this.emailReasonType;
	}

	public void setEmailReasonType(String emailReasonType) {
		this.emailReasonType = emailReasonType;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

}