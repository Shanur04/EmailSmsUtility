package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the exam_slot database table.
 * 
 */
@Entity
@Table(name = "exam_slot")
public class CasbExamSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "exam_slot_id")
    private Integer examSlotId;

    @Column(name = "exam_slot_code")
    private String code;

    @Column(name = "exam_date")
    private Timestamp date;

    @Column(name = "exam_name")
    private String description;

    @Column(name = "exam_slot_endtime")
    private Time endTime;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "exam_slot_starttime")
    private Time startTime;

    // bi-directional many-to-one association to ExamCasbGroupSlot
    @OneToMany(mappedBy = "casbExamSlot")
    private List<ExamCasbGroupSlot> examCasbGroupSlots;

    // bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private CasbExamMaster examMaster;
  
    public CasbExamSlot() {
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

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
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

    public List<ExamCasbGroupSlot> getExamCasbGroupSlots() {
	return this.examCasbGroupSlots;
    }

    public void setExamCasbGroupSlots(List<ExamCasbGroupSlot> examCasbGroupSlots) {
	this.examCasbGroupSlots = examCasbGroupSlots;
    }

    public ExamCasbGroupSlot addExamCasbGroupSlot(ExamCasbGroupSlot examCasbGroupSlot) {
	getExamCasbGroupSlots().add(examCasbGroupSlot);
	examCasbGroupSlot.setExamSlot(this);

	return examCasbGroupSlot;
    }

    public ExamCasbGroupSlot removeExamCasbGroupSlot(ExamCasbGroupSlot examCasbGroupSlot) {
	getExamCasbGroupSlots().remove(examCasbGroupSlot);
	examCasbGroupSlot.setExamSlot(null);

	return examCasbGroupSlot;
    }
    
    public CasbExamMaster getExamMaster() {
		return examMaster;
	}
    public void setExamMaster(CasbExamMaster examMaster) {
		this.examMaster = examMaster;
	}

}