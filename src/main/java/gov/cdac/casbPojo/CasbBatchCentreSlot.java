package gov.cdac.casbPojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the batch_centre_slot database table.
 * 
 */
@Entity
@Table(name = "batch_centre_slot")
public class CasbBatchCentreSlot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "batch_centre_slot_id")
    private Integer batchCentreSlotId;

    @Column(name = "batch_centre_id")
    private Integer batchCentreId;

    private Boolean status;

    // bi-directional one-to-one association to BatchCentre
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_centre_slot_id")
    private CasbBatchCentre casbBatchCentre;

    // bi-directional many-to-one association to ExamCasbGroupSlot
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_casb_group_slot_id")
    private ExamCasbGroupSlot examCasbGroupSlot;

    public CasbBatchCentreSlot() {
    }

    public Integer getBatchCentreSlotId() {
	return this.batchCentreSlotId;
    }

    public void setBatchCentreSlotId(Integer batchCentreSlotId) {
	this.batchCentreSlotId = batchCentreSlotId;
    }

    public Integer getBatchCentreId() {
	return this.batchCentreId;
    }

    public void setBatchCentreId(Integer batchCentreId) {
	this.batchCentreId = batchCentreId;
    }

    public Boolean getStatus() {
	return this.status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public CasbBatchCentre getBatchCentre() {
	return this.casbBatchCentre;
    }

    public void setBatchCentre(CasbBatchCentre casbBatchCentre) {
	this.casbBatchCentre = casbBatchCentre;
    }

    public ExamCasbGroupSlot getExamCasbGroupSlot() {
	return this.examCasbGroupSlot;
    }

    public void setExamCasbGroupSlot(ExamCasbGroupSlot examCasbGroupSlot) {
	this.examCasbGroupSlot = examCasbGroupSlot;
    }

}