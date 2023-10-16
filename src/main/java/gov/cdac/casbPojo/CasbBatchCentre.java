package gov.cdac.casbPojo;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the batch_centre database table.
 * 
 */
@Entity
@Table(name = "batch_centre")
public class CasbBatchCentre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "batch_centre_id")
    private Integer batchCentreId;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    private Boolean status;

    // bi-directional many-to-one association to BatchDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private BatchDetail batchDetail;

    // bi-directional many-to-one association to CentreDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id")
    private CentreDetail centreDetail;

    // bi-directional one-to-one association to BatchCentreSlot
    @OneToOne(mappedBy = "casbBatchCentre", fetch = FetchType.LAZY)
    private CasbBatchCentreSlot casbBatchCentreSlot;

    public CasbBatchCentre() {
    }

    public Integer getBatchCentreId() {
	return this.batchCentreId;
    }

    public void setBatchCentreId(Integer batchCentreId) {
	this.batchCentreId = batchCentreId;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public Boolean getStatus() {
	return this.status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public BatchDetail getBatchDetail() {
	return this.batchDetail;
    }

    public void setBatchDetail(BatchDetail batchDetail) {
	this.batchDetail = batchDetail;
    }

    public CentreDetail getCentreDetail() {
	return this.centreDetail;
    }

    public void setCentreDetail(CentreDetail centreDetail) {
	this.centreDetail = centreDetail;
    }

    public CasbBatchCentreSlot getBatchCentreSlot() {
	return this.casbBatchCentreSlot;
    }

    public void setBatchCentreSlot(CasbBatchCentreSlot casbBatchCentreSlot) {
	this.casbBatchCentreSlot = casbBatchCentreSlot;
    }

}