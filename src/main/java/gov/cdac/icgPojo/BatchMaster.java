package gov.cdac.icgPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
@Table(name = "batch_master")
@NamedQuery(name = "BatchMaster.findAll", query = "SELECT b FROM BatchMaster b")
@JsonIgnoreProperties({ "applicantCredentials", "casteCategoryDetails", "icgSettings", "examMasters", "signUps" })
public class BatchMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "BATCHID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCHID_GENERATOR")
    @Column(name = "batch_id")
    private Integer batchId;

    @NotBlank(message = "Batch Code cannot be blank")
    @Column(name = "batch_code")
    private String batchCode;

    @NotBlank(message = "Batch Name cannot be blank")
    @Column(name = "batch_name")
    private String batchName;

    @NotNull(message = "Status cannot be blank")
    @Column(name = "batch_status")
    private Boolean batchStatus;

    @Column(name = "end_date_time")
    private Timestamp endDateTime;

    @Column(name = "last_date_time_of_payment")
    private Timestamp lastDateTimeOfPayment;

    @UpdateTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    public BatchMaster() {
    }

    public BatchMaster(Integer batchId) {
	super();
	this.batchId = batchId;
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