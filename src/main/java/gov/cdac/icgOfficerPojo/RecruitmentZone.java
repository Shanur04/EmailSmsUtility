package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the recruitment_zone database table.
 * 
 */
@Entity
@Table(name="recruitment_zone")
@NamedQuery(name="RecruitmentZone.findAll", query="SELECT r FROM RecruitmentZone r")
@JsonIgnoreProperties({"recruitmentZoneStates","applicantPhase2PreferencesMappings","phase2CityMasters"})
public class RecruitmentZone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECRUITMENTZONEID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECRUITMENTZONEID_GENERATOR")
	@Column(name="recruitment_zone_id")
	private Integer recruitmentZoneId;

	@Column(name="zone_name")
	private String zoneName;
	
	@NotNull(message = "Recruitment Zone Status cannot be blank" )
	@Column(name="status")
	private Boolean recruitmentZoneStatus;

	//bi-directional many-to-one association to RecruitmentZoneState
	@OneToMany(mappedBy="recruitmentZone")
	private List<RecruitmentZoneState> recruitmentZoneStates;

	//bi-directional many-to-one association to ApplicantPhase2PreferencesMapping
	@OneToMany(mappedBy="recruitmentZone")
	private List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings;

	//bi-directional many-to-one association to Phase2CityMaster
	@OneToMany(mappedBy="recruitmentZone")
	private List<Phase2CityMaster> phase2CityMasters;
	
	public RecruitmentZone() {
	}

	
	public Boolean getRecruitmentZoneStatus() {
		return recruitmentZoneStatus;
	}


	public void setRecruitmentZoneStatus(Boolean recruitmentZoneStatus) {
		this.recruitmentZoneStatus = recruitmentZoneStatus;
	}


	public Integer getRecruitmentZoneId() {
		return this.recruitmentZoneId;
	}

	public void setRecruitmentZoneId(Integer recruitmentZoneId) {
		this.recruitmentZoneId = recruitmentZoneId;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public List<ApplicantPhase2PreferencesMapping> getApplicantPhase2PreferencesMappings() {
	    return applicantPhase2PreferencesMappings;
	}

	public void setApplicantPhase2PreferencesMappings(
		List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings) {
	    this.applicantPhase2PreferencesMappings = applicantPhase2PreferencesMappings;
	}

	public List<RecruitmentZoneState> getRecruitmentZoneStates() {
		return this.recruitmentZoneStates;
	}

	public void setRecruitmentZoneStates(List<RecruitmentZoneState> recruitmentZoneStates) {
		this.recruitmentZoneStates = recruitmentZoneStates;
	}

	public List<Phase2CityMaster> getPhase2CityMasters() {
	    return phase2CityMasters;
	}

	public void setPhase2CityMasters(List<Phase2CityMaster> phase2CityMasters) {
	    this.phase2CityMasters = phase2CityMasters;
	}

	public RecruitmentZoneState addRecruitmentZoneState(RecruitmentZoneState recruitmentZoneState) {
		getRecruitmentZoneStates().add(recruitmentZoneState);
		recruitmentZoneState.setRecruitmentZone(this);

		return recruitmentZoneState;
	}

	public RecruitmentZoneState removeRecruitmentZoneState(RecruitmentZoneState recruitmentZoneState) {
		getRecruitmentZoneStates().remove(recruitmentZoneState);
		recruitmentZoneState.setRecruitmentZone(null);

		return recruitmentZoneState;
	}

}