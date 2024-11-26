package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the batchwise_edit_registration_details database table.
 * 
 */
@Entity
@Table(name="batchwise_edit_registration_details")
@NamedQuery(name="BatchwiseEditRegistrationDetail.findAll", query="SELECT b FROM BatchwiseEditRegistrationDetail b")
public class BatchwiseEditRegistrationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCHWISE_EDIT_REGISTRATION_D_BATCHWISE_EDIT_REGISTRATION_D_SEQ" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCHWISE_EDIT_REGISTRATION_D_BATCHWISE_EDIT_REGISTRATION_D_SEQ")
	@Column(name="batchwise_edit_registration_details_id")
	private Integer batchwiseEditRegistrationDetailsId;

	@Column(name="batch_id")
	private Integer batchId;

	@Column(name="editable_registration_status")
	private String editableRegistrationStatus;

	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="start_date")
	private Timestamp startDate;

	public BatchwiseEditRegistrationDetail() {
	}

	public Integer getBatchwiseEditRegistrationDetailsId() {
		return this.batchwiseEditRegistrationDetailsId;
	}

	public void setBatchwiseEditRegistrationDetailsId(Integer batchwiseEditRegistrationDetailsId) {
		this.batchwiseEditRegistrationDetailsId = batchwiseEditRegistrationDetailsId;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getEditableRegistrationStatus() {
		return this.editableRegistrationStatus;
	}

	public void setEditableRegistrationStatus(String editableRegistrationStatus) {
		this.editableRegistrationStatus = editableRegistrationStatus;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

}