package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the exam_casb_group_slot database table.
 * 
 */
@Entity
@Table(name = "exam_casb_group_slot")
public class ExamCasbGroupSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "exam_casb_group_slot_id")
    private Integer examCasbGroupSlotId;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    // bi-directional many-to-one association to BatchCentreSlot
    @OneToMany(mappedBy = "examCasbGroupSlot")
    private List<CasbBatchCentreSlot> casbBatchCentreSlots;

    // bi-directional many-to-one association to CasbGroup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casb_group_id")
    private CasbGroup casbGroup;

    // bi-directional many-to-one association to ExamSlot
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_slot_id")
    private CasbExamSlot casbExamSlot;

    public ExamCasbGroupSlot() {
    }

    public Integer getExamCasbGroupSlotId() {
	return this.examCasbGroupSlotId;
    }

    public void setExamCasbGroupSlotId(Integer examCasbGroupSlotId) {
	this.examCasbGroupSlotId = examCasbGroupSlotId;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public List<CasbBatchCentreSlot> getBatchCentreSlots() {
	return this.casbBatchCentreSlots;
    }

    public void setBatchCentreSlots(List<CasbBatchCentreSlot> casbBatchCentreSlots) {
	this.casbBatchCentreSlots = casbBatchCentreSlots;
    }

    public CasbBatchCentreSlot addBatchCentreSlot(CasbBatchCentreSlot casbBatchCentreSlot) {
	getBatchCentreSlots().add(casbBatchCentreSlot);
	casbBatchCentreSlot.setExamCasbGroupSlot(this);

	return casbBatchCentreSlot;
    }

    public CasbBatchCentreSlot removeBatchCentreSlot(CasbBatchCentreSlot casbBatchCentreSlot) {
	getBatchCentreSlots().remove(casbBatchCentreSlot);
	casbBatchCentreSlot.setExamCasbGroupSlot(null);

	return casbBatchCentreSlot;
    }

    public CasbGroup getCasbGroup() {
	return this.casbGroup;
    }

    public void setCasbGroup(CasbGroup casbGroup) {
	this.casbGroup = casbGroup;
    }

    public CasbExamSlot getExamSlot() {
	return this.casbExamSlot;
    }

    public void setExamSlot(CasbExamSlot casbExamSlot) {
	this.casbExamSlot = casbExamSlot;
    }

}