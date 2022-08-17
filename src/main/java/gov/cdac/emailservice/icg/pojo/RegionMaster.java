package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author kunalm AND ankitas 
 *
 */
/**
 * The persistent class for the region_master database table.
 * 
 */
@Entity
@Table(name = "region_master")
@NamedQuery(name = "RegionMaster.findAll", query = "SELECT r FROM RegionMaster r")
@JsonIgnoreProperties({ "regionRegionheadmappings","examStateMasters" })
public class RegionMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "REGIONID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGIONID_GENERATOR")
    @Column(name = "region_id")
    private Integer regionId;

    @UpdateTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_status")
    private Boolean regionStatus;

    // bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamMaster examMaster;

    // bi-directional many-to-one association to RegionRegionheadmapping
    @OneToMany(mappedBy = "regionMaster")
    private List<RegionRegionheadmapping> regionRegionheadmappings;

    public RegionMaster() {
    }

    public Integer getRegionId() {
	return this.regionId;
    }

    public void setRegionId(Integer regionId) {
	this.regionId = regionId;
    }

    public Timestamp getRecordTracking() {
	return this.recordTracking;
    }

    public void setRecordTracking(Timestamp recordTracking) {
	this.recordTracking = recordTracking;
    }

    public String getRegionCode() {
	return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
	this.regionCode = regionCode;
    }

    public String getRegionName() {
	return this.regionName;
    }

    public void setRegionName(String regionName) {
	this.regionName = regionName;
    }

    public Boolean getRegionStatus() {
	return this.regionStatus;
    }

    public void setRegionStatus(Boolean regionStatus) {
	this.regionStatus = regionStatus;
    }

    public ExamMaster getExamMaster() {
	return this.examMaster;
    }

    public void setExamMaster(ExamMaster examMaster) {
	this.examMaster = examMaster;
    }

    public List<RegionRegionheadmapping> getRegionRegionheadmappings() {
	return this.regionRegionheadmappings;
    }

    public void setRegionRegionheadmappings(List<RegionRegionheadmapping> regionRegionheadmappings) {
	this.regionRegionheadmappings = regionRegionheadmappings;
    }

    public RegionRegionheadmapping addRegionRegionheadmapping(RegionRegionheadmapping regionRegionheadmapping) {
	getRegionRegionheadmappings().add(regionRegionheadmapping);
	regionRegionheadmapping.setRegionMaster(this);

	return regionRegionheadmapping;
    }

    public RegionRegionheadmapping removeRegionRegionheadmapping(RegionRegionheadmapping regionRegionheadmapping) {
	getRegionRegionheadmappings().remove(regionRegionheadmapping);
	regionRegionheadmapping.setRegionMaster(null);

	return regionRegionheadmapping;
    }

}