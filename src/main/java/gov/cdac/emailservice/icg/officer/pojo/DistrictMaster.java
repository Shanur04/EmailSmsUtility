package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the district_master database table.
 * 
 */
@Entity
@Table(name="district_master")
@NamedQuery(name="DistrictMaster.findAll", query="SELECT d FROM DistrictMaster d")
@JsonIgnoreProperties({"subDistrictMasters"})
public class DistrictMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lgd_district_code")
	private Integer lgdDistrictCode;

	@Column(name="district_name")
	private String districtName;

	@Column(name="district_status")
	private Boolean districtStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	public DistrictMaster(Integer lgdDistrictCode, String districtName, Boolean districtStatus) {
		super();
		this.lgdDistrictCode = lgdDistrictCode;
		this.districtName = districtName;
		this.districtStatus = districtStatus;
	}
	
	
	//bi-directional many-to-one association to StateMaster
	@ManyToOne
	@JoinColumn(name="lgd_state_code")
	private StateMaster stateMaster;

	//bi-directional many-to-one association to SubDistrictMaster
	@OneToMany(mappedBy="districtMaster")
	private List<SubDistrictMaster> subDistrictMasters;

	public DistrictMaster() {
	}

	public Integer getLgdDistrictCode() {
		return this.lgdDistrictCode;
	}

	public void setLgdDistrictCode(Integer lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Boolean getDistrictStatus() {
		return this.districtStatus;
	}

	public void setDistrictStatus(Boolean districtStatus) {
		this.districtStatus = districtStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public StateMaster getStateMaster() {
		return this.stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public List<SubDistrictMaster> getSubDistrictMasters() {
		return this.subDistrictMasters;
	}

	public void setSubDistrictMasters(List<SubDistrictMaster> subDistrictMasters) {
		this.subDistrictMasters = subDistrictMasters;
	}

	public SubDistrictMaster addSubDistrictMaster(SubDistrictMaster subDistrictMaster) {
		getSubDistrictMasters().add(subDistrictMaster);
		subDistrictMaster.setDistrictMaster(this);

		return subDistrictMaster;
	}

	public SubDistrictMaster removeSubDistrictMaster(SubDistrictMaster subDistrictMaster) {
		getSubDistrictMasters().remove(subDistrictMaster);
		subDistrictMaster.setDistrictMaster(null);

		return subDistrictMaster;
	}

}