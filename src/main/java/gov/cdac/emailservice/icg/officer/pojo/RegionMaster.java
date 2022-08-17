package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the region_master database table.
 * 
 */
@Entity
@Table(name="region_master")
@NamedQuery(name="RegionMaster.findAll", query="SELECT r FROM RegionMaster r")
@JsonIgnoreProperties({"regionRegionheadmappings","examCityMasters"})
public class RegionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGIONID_GENERATOR",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGIONID_GENERATOR")
	@Column(name="region_id")
	private Integer regionId;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@NotBlank(message = "Region Code cannot be blank")
	@Length( max = 1, message = "Region Code can be only 1 Character")
	@Column(name="region_code")
	private String regionCode;

	@NotBlank(message = "Region Name cannot be blank")
	@Column(name="region_name")
	private String regionName;

	@NotNull(message = "Region Status cannot be blank" )
	@Column(name="region_status")
	private Boolean regionStatus;

	//bi-directional many-to-one association to ExamCityMaster
	@OneToMany(mappedBy="regionMaster")
	private List<ExamCityMaster> examCityMasters;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;
	
	//bi-directional many-to-one association to RegionRegionheadmapping
	@OneToMany(mappedBy="regionMaster")
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

	public List<ExamCityMaster> getExamCityMasters() {
		return examCityMasters;
	}

	public void setExamCityMasters(List<ExamCityMaster> examCityMasters) {
		this.examCityMasters = examCityMasters;
	}

}