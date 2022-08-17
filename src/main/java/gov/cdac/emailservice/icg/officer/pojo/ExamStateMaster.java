package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the exam_state_master database table.
 * 
 */
@Entity
@Table(name="exam_state_master")
@NamedQuery(name="ExamStateMaster.findAll", query="SELECT e FROM ExamStateMaster e")
@JsonIgnoreProperties({"applicantPhase1PreferencesMappings","centreMasters","examCityMasters"})
public class ExamStateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAM_STATE_MASTER_EXAMSTATEID_GENERATOR",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAM_STATE_MASTER_EXAMSTATEID_GENERATOR")
	@Column(name="exam_state_id")
	private Integer examStateId;

	@Column(name="exam_state_name")
	private String examStateName;

	@Column(name="exam_state_status")
	private Boolean examStateStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCityPreferencesMapping
	@OneToMany(mappedBy="examStateMaster")
	private List<ApplicantPhase1PreferencesMapping> applicantPhase1PreferencesMappings;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="examStateMaster")
	private List<CentreMaster> centreMasters;

	//bi-directional many-to-one association to ExamCityMaster
	@OneToMany(mappedBy="examStateMaster")
	private List<ExamCityMaster> examCityMasters;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	public ExamStateMaster() {
	}

	public Integer getExamStateId() {
		return this.examStateId;
	}

	public void setExamStateId(Integer examStateId) {
		this.examStateId = examStateId;
	}

	public String getExamStateName() {
		return this.examStateName;
	}

	public void setExamStateName(String examStateName) {
		this.examStateName = examStateName;
	}

	public Boolean getExamStateStatus() {
		return this.examStateStatus;
	}

	public void setExamStateStatus(Boolean examStateStatus) {
		this.examStateStatus = examStateStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	

	public List<ApplicantPhase1PreferencesMapping> getApplicantPhase1PreferencesMappings() {
		return applicantPhase1PreferencesMappings;
	}

	public void setApplicantPhase1PreferencesMappings(
			List<ApplicantPhase1PreferencesMapping> applicantPhase1PreferencesMappings) {
		this.applicantPhase1PreferencesMappings = applicantPhase1PreferencesMappings;
	}

	public ApplicantPhase1PreferencesMapping addApplicantPhase1PreferencesMapping(ApplicantPhase1PreferencesMapping applicantPhase1PreferencesMapping) {
		getApplicantPhase1PreferencesMappings().add(applicantPhase1PreferencesMapping);
		applicantPhase1PreferencesMapping.setExamStateMaster(this);

		return applicantPhase1PreferencesMapping;
	}

	public ApplicantPhase1PreferencesMapping removeApplicantPhase1PreferencesMapping(ApplicantPhase1PreferencesMapping applicantCityPreferencesMapping) {
		getApplicantPhase1PreferencesMappings().remove(applicantCityPreferencesMapping);
		applicantCityPreferencesMapping.setExamStateMaster(null);

		return applicantCityPreferencesMapping;
	}

	public List<CentreMaster> getCentreMasters() {
		return this.centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public CentreMaster addCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().add(centreMaster);
		centreMaster.setExamStateMaster(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setExamStateMaster(null);

		return centreMaster;
	}

	public List<ExamCityMaster> getExamCityMasters() {
		return this.examCityMasters;
	}

	public void setExamCityMasters(List<ExamCityMaster> examCityMasters) {
		this.examCityMasters = examCityMasters;
	}

	public ExamCityMaster addExamCityMaster(ExamCityMaster examCityMaster) {
		getExamCityMasters().add(examCityMaster);
		examCityMaster.setExamStateMaster(this);

		return examCityMaster;
	}

	public ExamCityMaster removeExamCityMaster(ExamCityMaster examCityMaster) {
		getExamCityMasters().remove(examCityMaster);
		examCityMaster.setExamStateMaster(null);

		return examCityMaster;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	@Override
	public String toString() {
		return "ExamStateMaster [examStateId=" + examStateId + ", examStateName=" + examStateName + ", examStateStatus="
				+ examStateStatus + ", recordTracking=" + recordTracking + ", applicantPhase1PreferencesMappings="
				+ applicantPhase1PreferencesMappings + ", centreMasters=" + centreMasters + ", examCityMasters="
				+ examCityMasters + ", examMaster=" + examMaster + "]";
	}
	
	

}