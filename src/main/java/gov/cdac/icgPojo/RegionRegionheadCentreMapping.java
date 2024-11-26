package gov.cdac.icgPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * The persistent class for the region_regionhead_centre_mapping database table.
 * 
 */
@Entity
@Table(name = "region_regionhead_centre_mapping")
@NamedQuery(name = "RegionRegionheadCentreMapping.findAll", query = "SELECT r FROM RegionRegionheadCentreMapping r")
public class RegionRegionheadCentreMapping implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "REGIONREGIONHEADCENTREMAPPINGID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGIONREGIONHEADCENTREMAPPINGID_GENERATOR")
    @Column(name = "region_regionhead_centre_mappingid")
    private Integer regionRegionheadCentreMappingid;

    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "region_regionhead_centre_mappingstatus")
    private Boolean regionRegionheadCentreMappingstatus;

    // bi-directional many-to-one association to CentreMaster
    @ManyToOne
    @JoinColumn(name = "centre_id")
    private CentreMaster centreMaster;

    // bi-directional many-to-one association to RegionRegionheadmapping
    @ManyToOne
    @JoinColumn(name = "regionheadmapping_id")
    private RegionRegionheadmapping regionRegionheadmapping;

    public RegionRegionheadCentreMapping() {
    }

    public Integer getRegionRegionheadCentreMappingid() {
	return this.regionRegionheadCentreMappingid;
    }

    public void setRegionRegionheadCentreMappingid(Integer regionRegionheadCentreMappingid) {
	this.regionRegionheadCentreMappingid = regionRegionheadCentreMappingid;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public Boolean getRegionRegionheadCentreMappingstatus() {
	return this.regionRegionheadCentreMappingstatus;
    }

    public void setRegionRegionheadCentreMappingstatus(Boolean regionRegionheadCentreMappingstatus) {
	this.regionRegionheadCentreMappingstatus = regionRegionheadCentreMappingstatus;
    }

    public CentreMaster getCentreMaster() {
	return this.centreMaster;
    }

    public void setCentreMaster(CentreMaster centreMaster) {
	this.centreMaster = centreMaster;
    }

    public RegionRegionheadmapping getRegionRegionheadmapping() {
	return this.regionRegionheadmapping;
    }

    public void setRegionRegionheadmapping(RegionRegionheadmapping regionRegionheadmapping) {
	this.regionRegionheadmapping = regionRegionheadmapping;
    }

}