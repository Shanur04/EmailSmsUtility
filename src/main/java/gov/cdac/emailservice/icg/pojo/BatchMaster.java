package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author kunalm AND ankitas 
 *
 */
/**
 * The persistent class for the batch_master database table.
 * 
 */
@Entity
@Table(name="batch_master")
@NamedQuery(name="BatchMaster.findAll", query="SELECT b FROM BatchMaster b")
@JsonIgnoreProperties({"applicantCredentials","casteCategoryDetails","icgSettings","examMasters","signUps"})
public class BatchMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCHID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCHID_GENERATOR")
	@Column(name="batch_id")
	private Integer batchId;

	@NotBlank(message = "Batch Code cannot be blank")
	@Column(name="batch_code")
	private String batchCode;

	@NotBlank(message = "Batch Name cannot be blank")
	@Column(name="batch_name")
	private String batchName;

	@NotNull(message = "Status cannot be blank" )
	@Column(name="batch_status")
	private Boolean batchStatus;

	@Column(name="end_date_time")
	private Timestamp endDateTime;

	@Column(name="last_date_time_of_payment")
	private Timestamp lastDateTimeOfPayment;
	
	

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="start_date_time")
	private Timestamp startDateTime;

	
	public BatchMaster() {
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Boolean getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(Boolean batchStatus) {
		this.batchStatus = batchStatus;
	}

	public Timestamp getEndDateTime() {
		return this.endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Timestamp getLastDateTimeOfPayment() {
		return this.lastDateTimeOfPayment;
	}

	public void setLastDateTimeOfPayment(Timestamp lastDateTimeOfPayment) {
		this.lastDateTimeOfPayment = lastDateTimeOfPayment;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}
	

	public Timestamp getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

}