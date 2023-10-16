package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the applicant_phase2_preferences_mapping database table.
 * 
 */
@Entity
@Table(name="applicant_phase2_preferences_mapping")
@NamedQuery(name="ApplicantPhase2PreferencesMapping.findAll", query="SELECT a FROM ApplicantPhase2PreferencesMapping a")
public class ApplicantPhase2PreferencesMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANTPHASE2PREFERENCESMAPPINGID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANTPHASE2PREFERENCESMAPPINGID_GENERATOR")
	@Column(name="applicant_phase2_preferences_mapping_id")
	private Long applicantPhase2PreferencesMappingId;

	@Column(name="pref_index_number")
	private Integer prefIndexNumber;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name="recruitment_zone_id")
	private RecruitmentZone recruitmentZone;

	//bi-directional many-to-one association to Phase2CityMaster
	@ManyToOne
	@JoinColumn(name="phase2_city_id")
	private Phase2CityMaster phase2CityMaster;

	public ApplicantPhase2PreferencesMapping() {
	}

	public ApplicantPhase2PreferencesMapping(Integer prefIndexNumber) {
	    this.prefIndexNumber = prefIndexNumber;
	    this.recruitmentZone = new RecruitmentZone();
	    this.phase2CityMaster = new Phase2CityMaster();
	}
	
	public ApplicantPhase2PreferencesMapping(Integer prefIndexNumber, RecruitmentZone recruitmentZone) {
	    this.prefIndexNumber = prefIndexNumber;
	    this.recruitmentZone = recruitmentZone;
	    this.phase2CityMaster = new Phase2CityMaster();
	}

	public Long getApplicantPhase2PreferencesMappingId() {
		return this.applicantPhase2PreferencesMappingId;
	}

	public void setApplicantPhase2PreferencesMappingId(Long applicantPhase2PreferencesMappingId) {
		this.applicantPhase2PreferencesMappingId = applicantPhase2PreferencesMappingId;
	}

	public Integer getPrefIndexNumber() {
		return this.prefIndexNumber;
	}

	public void setPrefIndexNumber(Integer prefIndexNumber) {
		this.prefIndexNumber = prefIndexNumber;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public RecruitmentZone getRecruitmentZone() {
	    return recruitmentZone;
	}

	public void setRecruitmentZone(RecruitmentZone recruitmentZone) {
	    this.recruitmentZone = recruitmentZone;
	}

	public Phase2CityMaster getPhase2CityMaster() {
		return this.phase2CityMaster;
	}

	public void setPhase2CityMaster(Phase2CityMaster phase2CityMaster) {
		this.phase2CityMaster = phase2CityMaster;
	}

	@Override
	public String toString() {
	    return "ApplicantPhase2PreferencesMapping [prefIndexNumber=" + prefIndexNumber + ", recruitmentZone="
		    + recruitmentZone.getRecruitmentZoneId() + ", phase2CityMaster="
		    + phase2CityMaster.getPhase2CityId() + "]";
	}

}