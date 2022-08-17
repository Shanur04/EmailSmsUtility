package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the state_master database table.
 * 
 */
@Entity
@Table(name="state_master")
@NamedQuery(name="StateMaster.findAll", query="SELECT s FROM StateMaster s")
@JsonIgnoreProperties({"districtMasters"})
public class StateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lgd_state_code")
	private Integer lgdStateCode;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="state_name")
	private String stateName;

	@Column(name="state_status")
	private Boolean stateStatus;
	
	

	public StateMaster(Integer lgdStateCode, String stateName, Boolean stateStatus) {
		super();
		this.lgdStateCode = lgdStateCode;
		this.stateName = stateName;
		this.stateStatus = stateStatus;
	}

	//bi-directional many-to-one association to DistrictMaster
	@OneToMany(mappedBy="stateMaster")
	private List<DistrictMaster> districtMasters;

	public StateMaster() {
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Boolean getStateStatus() {
		return this.stateStatus;
	}

	public void setStateStatus(Boolean stateStatus) {
		this.stateStatus = stateStatus;
	}

	public Integer getLgdStateCode() {
		return lgdStateCode;
	}

	public void setLgdStateCode(Integer lgdStateCode) {
		this.lgdStateCode = lgdStateCode;
	}


	public List<DistrictMaster> getDistrictMasters() {
		return districtMasters;
	}

	public void setDistrictMasters(List<DistrictMaster> districtMasters) {
		this.districtMasters = districtMasters;
	}
	
	public DistrictMaster addCityMaster(DistrictMaster districtMaster) {
		getDistrictMasters().add(districtMaster);
		districtMaster.setStateMaster(this);

		return districtMaster;
	}

	public DistrictMaster removeCityMaster(DistrictMaster districtMaster) {
		getDistrictMasters().remove(districtMaster);
		districtMaster.setStateMaster(null);

		return districtMaster;
	}

}