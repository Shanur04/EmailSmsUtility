package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the sub_district_master database table.
 * 
 */
@Entity
@Table(name="sub_district_master")
@NamedQuery(name="SubDistrictMaster.findAll", query="SELECT s FROM SubDistrictMaster s")
public class SubDistrictMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lgd_sub_district_code")
	private Integer lgdSubDistrictCode;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="sub_district_name")
	private String subDistrictName;

	@Column(name="sub_district_status")
	private Boolean subDistrictStatus;

	
	public SubDistrictMaster(Integer lgdSubDistrictCode, String subDistrictName, Boolean subDistrictStatus) {
		super();
		this.lgdSubDistrictCode = lgdSubDistrictCode;
		this.subDistrictName = subDistrictName;
		this.subDistrictStatus = subDistrictStatus;
	}
	
	
	//bi-directional many-to-one association to DistrictMaster
	@ManyToOne
	@JoinColumn(name="lgd_district_code")
	private DistrictMaster districtMaster;

	public SubDistrictMaster() {
	}

	public Integer getLgdSubDistrictCode() {
		return this.lgdSubDistrictCode;
	}

	public void setLgdSubDistrictCode(Integer lgdSubDistrictCode) {
		this.lgdSubDistrictCode = lgdSubDistrictCode;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getSubDistrictName() {
		return this.subDistrictName;
	}

	public void setSubDistrictName(String subDistrictName) {
		this.subDistrictName = subDistrictName;
	}

	public Boolean getSubDistrictStatus() {
		return this.subDistrictStatus;
	}

	public void setSubDistrictStatus(Boolean subDistrictStatus) {
		this.subDistrictStatus = subDistrictStatus;
	}

	public DistrictMaster getDistrictMaster() {
		return this.districtMaster;
	}

	public void setDistrictMaster(DistrictMaster districtMaster) {
		this.districtMaster = districtMaster;
	}

}