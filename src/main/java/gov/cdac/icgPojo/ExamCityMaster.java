package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the exam_city_master database table.
 * 
 */
@Entity
@Table(name="exam_city_master")
@NamedQuery(name="ExamCityMaster.findAll", query="SELECT e FROM ExamCityMaster e")
@JsonIgnoreProperties({"applicantCityPreferencesMappings","centreMasters","examCitySubcityMappings1","examCitySubcityMappings2"})
public class ExamCityMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exam_city_id")
	private Integer examCityId;

	@Column(name="exam_city_code")
	private String examCityCode;

	@Column(name="exam_city_name")
	private String examCityName;

	@Column(name="exam_city_status")
	private Boolean examCityStatus;

	@Column(name="is_registration_exam_city")
	private Boolean isRegistrationExamCity;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCityPreferencesMapping
	@OneToMany(mappedBy="examCityMaster")
	private List<ApplicantCityPreferencesMapping> applicantCityPreferencesMappings;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="examCityMaster")
	private List<CentreMaster> centreMasters;

	//bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name="exam_state_id")
	private ExamStateMaster examStateMaster;
	
	//bi-directional many-to-one association to RegionMaster
	@ManyToOne
	@JoinColumn(name="region_id")
	private RegionMaster regionMaster;

	//bi-directional many-to-one association to ExamCitySubcityMapping
	@OneToMany(mappedBy="examCityMaster1")
	private List<ExamCitySubcityMapping> examCitySubcityMappings1;

	//bi-directional many-to-one association to ExamCitySubcityMapping
	@OneToMany(mappedBy="examCityMaster2")
	private List<ExamCitySubcityMapping> examCitySubcityMappings2;

	public ExamCityMaster() {
	}
	
	public ExamCityMaster(int examCityId) {
		this.examCityId =examCityId;
	}
	
	public ExamCityMaster(String examCityName,Integer examCityId) {
		super();
		this.examCityId = examCityId;
		this.examCityName = examCityName;
	}

	public RegionMaster getRegionMaster() {
		return regionMaster;
	}

	public void setRegionMaster(RegionMaster regionMaster) {
		this.regionMaster = regionMaster;
	}

	public Integer getExamCityId() {
		return this.examCityId;
	}

	public void setExamCityId(Integer examCityId) {
		this.examCityId = examCityId;
	}

	public String getExamCityCode() {
		return this.examCityCode;
	}

	public void setExamCityCode(String examCityCode) {
		this.examCityCode = examCityCode;
	}

	public String getExamCityName() {
		return this.examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
	}

	public Boolean getExamCityStatus() {
		return this.examCityStatus;
	}

	public void setExamCityStatus(Boolean examCityStatus) {
		this.examCityStatus = examCityStatus;
	}

	public Boolean getIsRegistrationExamCity() {
		return this.isRegistrationExamCity;
	}

	public void setIsRegistrationExamCity(Boolean isRegistrationExamCity) {
		this.isRegistrationExamCity = isRegistrationExamCity;
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
		applicantCityPreferencesMapping.setExamCityMaster(this);

		return applicantCityPreferencesMapping;
	}

	public ApplicantCityPreferencesMapping removeApplicantCityPreferencesMapping(ApplicantCityPreferencesMapping applicantCityPreferencesMapping) {
		getApplicantCityPreferencesMappings().remove(applicantCityPreferencesMapping);
		applicantCityPreferencesMapping.setExamCityMaster(null);

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
		centreMaster.setExamCityMaster(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setExamCityMaster(null);

		return centreMaster;
	}

	public ExamStateMaster getExamStateMaster() {
		return this.examStateMaster;
	}

	public void setExamStateMaster(ExamStateMaster examStateMaster) {
		this.examStateMaster = examStateMaster;
	}

	public List<ExamCitySubcityMapping> getExamCitySubcityMappings1() {
		return this.examCitySubcityMappings1;
	}

	public void setExamCitySubcityMappings1(List<ExamCitySubcityMapping> examCitySubcityMappings1) {
		this.examCitySubcityMappings1 = examCitySubcityMappings1;
	}

	public ExamCitySubcityMapping addExamCitySubcityMappings1(ExamCitySubcityMapping examCitySubcityMappings1) {
		getExamCitySubcityMappings1().add(examCitySubcityMappings1);
		examCitySubcityMappings1.setExamCityMaster1(this);

		return examCitySubcityMappings1;
	}

	public ExamCitySubcityMapping removeExamCitySubcityMappings1(ExamCitySubcityMapping examCitySubcityMappings1) {
		getExamCitySubcityMappings1().remove(examCitySubcityMappings1);
		examCitySubcityMappings1.setExamCityMaster1(null);

		return examCitySubcityMappings1;
	}

	public List<ExamCitySubcityMapping> getExamCitySubcityMappings2() {
		return this.examCitySubcityMappings2;
	}

	public void setExamCitySubcityMappings2(List<ExamCitySubcityMapping> examCitySubcityMappings2) {
		this.examCitySubcityMappings2 = examCitySubcityMappings2;
	}

	public ExamCitySubcityMapping addExamCitySubcityMappings2(ExamCitySubcityMapping examCitySubcityMappings2) {
		getExamCitySubcityMappings2().add(examCitySubcityMappings2);
		examCitySubcityMappings2.setExamCityMaster2(this);

		return examCitySubcityMappings2;
	}

	public ExamCitySubcityMapping removeExamCitySubcityMappings2(ExamCitySubcityMapping examCitySubcityMappings2) {
		getExamCitySubcityMappings2().remove(examCitySubcityMappings2);
		examCitySubcityMappings2.setExamCityMaster2(null);

		return examCitySubcityMappings2;
	}

	@Override
	public String toString() {
		return "ExamCityMaster [examCityId=" + examCityId + ", examCityCode=" + examCityCode + ", examCityName="
				+ examCityName + ", examCityStatus=" + examCityStatus + ", isRegistrationExamCity="
				+ isRegistrationExamCity + ", recordTracking=" + recordTracking + ", applicantCityPreferencesMappings="
				+ applicantCityPreferencesMappings + ", centreMasters=" + centreMasters + ", examStateMaster="
				+ examStateMaster + ", examCitySubcityMappings1=" + examCitySubcityMappings1
				+ ", examCitySubcityMappings2=" + examCitySubcityMappings2 + "]";
	}

	
}