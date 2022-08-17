package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="applicant_phase1_preferences_mapping")
@NamedQuery(name="ApplicantPhase1PreferencesMapping.findAll", query="SELECT a FROM ApplicantPhase1PreferencesMapping a")
public class ApplicantPhase1PreferencesMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_PHASE1_PREFERENCES_MAPPING_APPLICANTPHASE1PREFERENCESMAPPINGID_GENERATOR" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_PHASE1_PREFERENCES_MAPPING_APPLICANTPHASE1PREFERENCESMAPPINGID_GENERATOR")
	@Column(name="applicant_phase1_preferences_mapping_id")
	private Long applicantPhase1PreferencesMappingId;

	@Column(name="pref_index_number")
	private Integer prefIndexNumber;

	@UpdateTimestamp
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

	public ApplicantPhase1PreferencesMapping() {
	}

	public ApplicantPhase1PreferencesMapping(Integer prefIndexNumber) {
		super();
		this.prefIndexNumber = prefIndexNumber;
		this.examCityMaster = new ExamCityMaster();
		this.examStateMaster = new ExamStateMaster();
	}
	
	public Long getApplicantPhase1PreferencesMappingId() {
		return this.applicantPhase1PreferencesMappingId;
	}

	public void setApplicantPhase1PreferencesMappingId(Long applicantPhase1PreferencesMappingId) {
		this.applicantPhase1PreferencesMappingId = applicantPhase1PreferencesMappingId;
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