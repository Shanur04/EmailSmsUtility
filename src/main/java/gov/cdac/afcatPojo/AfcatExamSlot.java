package gov.cdac.afcatPojo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The persistent class for the exam_slot database table.
 * 
 */
@Entity
@Table(name = "exam_slot")
public class AfcatExamSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "exam_slot_id")
	private Integer examSlotId;

	@Column(name = "exam_slot_code")
	private String code;

	@Column(name = "exam_date")
	private Timestamp examDate;

	@Column(name = "exam_slot_name")
	private String description;

	@Column(name = "exam_slot_endtime")
	private Time endTime;

	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	@Column(name = "exam_slot_starttime")
	private Time startTime;
	
    // bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private AfcatExamMaster examMaster;

	public AfcatExamSlot() {
	}

	public Integer getExamSlotId() {
		return this.examSlotId;
	}

	public void setExamSlotId(Integer examSlotId) {
		this.examSlotId = examSlotId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getExamDate() {
		return examDate;
	}

	public void setExamDate(Timestamp examDate) {
		this.examDate = examDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public AfcatExamMaster getExamMaster() {
		return examMaster;
	}
	
	public void setExamMaster(AfcatExamMaster examMaster) {
		this.examMaster = examMaster;
	}

}