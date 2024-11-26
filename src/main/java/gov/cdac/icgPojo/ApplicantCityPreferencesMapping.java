package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the applicant_city_preferences_mapping database table.
 * 
 */
@Entity
@Table(name="applicant_city_preferences_mapping")
@NamedQuery(name="ApplicantCityPreferencesMapping.findAll", query="SELECT a FROM ApplicantCityPreferencesMapping a")
public class ApplicantCityPreferencesMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "APPLICANTCITYPREFERENCEMAPPINGID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANTCITYPREFERENCEMAPPINGID_GENERATOR")
	@Column(name="applicant_city_preferences_mapping_id")
	private Long applicantCityPreferencesMappingId;

	@Column(name="pref_index_number")
	private Integer prefIndexNumber;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to ExamCityMaster
	@ManyToOne
	@JoinColumn(name="exam_city_id")
	private ExamCityMaster examCityMaster;

	//bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name="exam_state_id")
	private ExamStateMaster examStateMaster;

	public ApplicantCityPreferencesMapping() {
	}

	public Long getApplicantCityPreferencesMappingId() {
		return this.applicantCityPreferencesMappingId;
	}

	public void setApplicantCityPreferencesMappingId(Long applicantCityPreferencesMappingId) {
		this.applicantCityPreferencesMappingId = applicantCityPreferencesMappingId;
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

	public ExamCityMaster getExamCityMaster() {
		return this.examCityMaster;
	}

	public void setExamCityMaster(ExamCityMaster examCityMaster) {
		this.examCityMaster = examCityMaster;
	}

	public ExamStateMaster getExamStateMaster() {
		return this.examStateMaster;
	}

	public void setExamStateMaster(ExamStateMaster examStateMaster) {
		this.examStateMaster = examStateMaster;
	}

}