package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.*;

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
@JsonIgnoreProperties({"applicantCityPreferencesMappings","centreMasters","examCityMasters"})
public class ExamStateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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
	private List<ApplicantCityPreferencesMapping> applicantCityPreferencesMappings;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="examStateMaster")
	private List<CentreMaster> centreMasters;

	//bi-directional many-to-one association to ExamCityMaster
	@OneToMany(mappedBy="examStateMaster")
	private List<ExamCityMaster> examCityMasters;

	public ExamStateMaster() {
	}
	
	public ExamStateMaster(int examStateId) {
		this.examStateId = examStateId;
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

	public List<ApplicantCityPreferencesMapping> getApplicantCityPreferencesMappings() {
		return this.applicantCityPreferencesMappings;
	}

	public void setApplicantCityPreferencesMappings(List<ApplicantCityPreferencesMapping> applicantCityPreferencesMappings) {
		this.applicantCityPreferencesMappings = applicantCityPreferencesMappings;
	}

	public ApplicantCityPreferencesMapping addApplicantCityPreferencesMapping(ApplicantCityPreferencesMapping applicantCityPreferencesMapping) {
		getApplicantCityPreferencesMappings().add(applicantCityPreferencesMapping);
		applicantCityPreferencesMapping.setExamStateMaster(this);

		return applicantCityPreferencesMapping;
	}

	public ApplicantCityPreferencesMapping removeApplicantCityPreferencesMapping(ApplicantCityPreferencesMapping applicantCityPreferencesMapping) {
		getApplicantCityPreferencesMappings().remove(applicantCityPreferencesMapping);
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

	@Override
	public String toString() {
		return "ExamStateMaster [examStateId=" + examStateId + ", examStateName=" + examStateName + ", examStateStatus="
				+ examStateStatus + ", recordTracking=" + recordTracking
				+ ", applicantCityPreferencesMappings=" + applicantCityPreferencesMappings + ", centreMasters="
				+ centreMasters + ", examCityMasters=" + examCityMasters + "]";
	}
	
	

}