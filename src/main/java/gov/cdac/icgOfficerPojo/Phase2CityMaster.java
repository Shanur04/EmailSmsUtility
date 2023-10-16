package gov.cdac.icgOfficerPojo;

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
 * The persistent class for the phase2_city_master database table.
 * 
 */
@Entity
@Table(name="phase2_city_master")
@NamedQuery(name="Phase2CityMaster.findAll", query="SELECT p FROM Phase2CityMaster p")
@JsonIgnoreProperties({"applicantPhase2PreferencesMappings"})
public class Phase2CityMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PHASE2CITYID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHASE2CITYID_GENERATOR")
	@Column(name="phase2_city_id")
	private Integer phase2CityId;
	
	@Column(name="phase2_city_address")
	private String phase2CityAddress;

	@NotBlank(message = "Phase II City Code cannot be blank")
	@Length( max = 2, message = "Phase II City Code can be only 2 Character")
	@Column(name="phase2_city_code")
	private String phase2CityCode;

	@NotNull(message = "Phase II City Status cannot be blank" )
	@Column(name="phase2_city_isactive")
	private Boolean phase2CityIsactive;

	@NotBlank(message = "Phase II City Name cannot be blank")
	@Column(name="phase2_city_name")
	private String phase2CityName;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantPhase2PreferencesMapping
	@OneToMany(mappedBy="phase2CityMaster")
	private List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings;

	//bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name="recruitment_zone_id")
	private RecruitmentZone recruitmentZone;

	public Phase2CityMaster() {
	}

	public Integer getPhase2CityId() {
		return this.phase2CityId;
	}

	public void setPhase2CityId(Integer phase2CityId) {
		this.phase2CityId = phase2CityId;
	}

	public String getPhase2CityCode() {
		return this.phase2CityCode;
	}

	public void setPhase2CityCode(String phase2CityCode) {
		this.phase2CityCode = phase2CityCode;
	}

	public Boolean getPhase2CityIsactive() {
		return this.phase2CityIsactive;
	}

	public void setPhase2CityIsactive(Boolean phase2CityIsactive) {
		this.phase2CityIsactive = phase2CityIsactive;
	}

	public String getPhase2CityName() {
		return this.phase2CityName;
	}

	public void setPhase2CityName(String phase2CityName) {
		this.phase2CityName = phase2CityName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<ApplicantPhase2PreferencesMapping> getApplicantPhase2PreferencesMappings() {
		return this.applicantPhase2PreferencesMappings;
	}

	public void setApplicantPhase2PreferencesMappings(List<ApplicantPhase2PreferencesMapping> applicantPhase2PreferencesMappings) {
		this.applicantPhase2PreferencesMappings = applicantPhase2PreferencesMappings;
	}

	public ApplicantPhase2PreferencesMapping addApplicantPhase2PreferencesMapping(ApplicantPhase2PreferencesMapping applicantPhase2PreferencesMapping) {
		getApplicantPhase2PreferencesMappings().add(applicantPhase2PreferencesMapping);
		applicantPhase2PreferencesMapping.setPhase2CityMaster(this);

		return applicantPhase2PreferencesMapping;
	}

	public ApplicantPhase2PreferencesMapping removeApplicantPhase2PreferencesMapping(ApplicantPhase2PreferencesMapping applicantPhase2PreferencesMapping) {
		getApplicantPhase2PreferencesMappings().remove(applicantPhase2PreferencesMapping);
		applicantPhase2PreferencesMapping.setPhase2CityMaster(null);

		return applicantPhase2PreferencesMapping;
	}

	public RecruitmentZone getRecruitmentZone() {
	    return recruitmentZone;
	}

	public void setRecruitmentZone(RecruitmentZone recruitmentZone) {
	    this.recruitmentZone = recruitmentZone;
	}
	
}