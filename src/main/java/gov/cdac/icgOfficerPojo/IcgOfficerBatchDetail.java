package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the batch_details database table.
 * 
 */
@Entity
@Table(name = "batch_details")
public class IcgOfficerBatchDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "batch_id")
    private Integer batchId;

    @Column(name = "batch_code")
    private String batchCode;

    @Column(name = "batch_name")
    private String batchName;

    @Column(name = "end_date_time")
    private Timestamp endDateTime;

    @Column(name = "last_date_time_of_payment")
    private Timestamp lastDateTimeOfPayment;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    public IcgOfficerBatchDetail() {
    }

    public IcgOfficerBatchDetail(Integer batchId) {
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