package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the region_regionheadmapping database table.
 * 
 */
@Entity
@Table(name="region_regionheadmapping")
@NamedQuery(name="RegionRegionheadmapping.findAll", query="SELECT r FROM RegionRegionheadmapping r")
@JsonIgnoreProperties({"regionRegionheadCentreMappings"})

public class RegionRegionheadmapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGIONHEADMAPPINGID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGIONHEADMAPPINGID_GENERATOR")
	@Column(name="regionheadmapping_id")
	private Integer regionheadmappingId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	private Boolean status;

	//bi-directional many-to-one association to RegionRegionheadCentreMapping
	@OneToMany(mappedBy="regionRegionheadmapping")
	private List<RegionRegionheadCentreMapping> regionRegionheadCentreMappings;

	//bi-directional many-to-one association to RegionMaster
	@ManyToOne
	@JoinColumn(name="region_id")
	private RegionMaster regionMaster;

	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="system_user_cred_id")
	private SystemUserCredential systemUserCredential;

	public RegionRegionheadmapping() {
	}

	public Integer getRegionheadmappingId() {
		return this.regionheadmappingId;
	}

	public void setRegionheadmappingId(Integer regionheadmappingId) {
		this.regionheadmappingId = regionheadmappingId;
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

	public List<RegionRegionheadCentreMapping> getRegionRegionheadCentreMappings() {
		return this.regionRegionheadCentreMappings;
	}

	public void setRegionRegionheadCentreMappings(List<RegionRegionheadCentreMapping> regionRegionheadCentreMappings) {
		this.regionRegionheadCentreMappings = regionRegionheadCentreMappings;
	}

	public RegionRegionheadCentreMapping addRegionRegionheadCentreMapping(RegionRegionheadCentreMapping regionRegionheadCentreMapping) {
		getRegionRegionheadCentreMappings().add(regionRegionheadCentreMapping);
		regionRegionheadCentreMapping.setRegionRegionheadmapping(this);

		return regionRegionheadCentreMapping;
	}

	public RegionRegionheadCentreMapping removeRegionRegionheadCentreMapping(RegionRegionheadCentreMapping regionRegionheadCentreMapping) {
		getRegionRegionheadCentreMappings().remove(regionRegionheadCentreMapping);
		regionRegionheadCentreMapping.setRegionRegionheadmapping(null);

		return regionRegionheadCentreMapping;
	}

	public RegionMaster getRegionMaster() {
		return this.regionMaster;
	}

	public void setRegionMaster(RegionMaster regionMaster) {
		this.regionMaster = regionMaster;
	}

	public SystemUserCredential getSystemUserCredential() {
		return this.systemUserCredential;
	}

	public void setSystemUserCredential(SystemUserCredential systemUserCredential) {
		this.systemUserCredential = systemUserCredential;
	}

}