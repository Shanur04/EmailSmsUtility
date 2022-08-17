package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the exam_master database table.
 * 
 */
@Entity
@Table(name="exam_master")
@NamedQuery(name="ExamMaster.findAll", query="SELECT e FROM ExamMaster e")
@JsonIgnoreProperties({ "applicantCentreAllotments", "centreMasters","centreUserMappings", "examSlots", "examSlotSectionMappings",
	"regionMasters","emailSents" ,"examDetailsDisplays","icgSettings","examStateMasters","smsSents"})
public class ExamMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMID_GENERATOR",  allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMID_GENERATOR")
	@Column(name="exam_id")
	private Integer examId;

	@NotNull(message = "Exam Duration cannot be blank")
	@Column(name="exam_duration")
	private Integer examDuration;

	@NotBlank(message = "Exam Month cannot be blank")
	@Column(name="exam_month")
	private String examMonth;

	@NotBlank(message = "Exam Name cannot be blank")
	@Column(name="exam_name")
	private String examName;

	@NotNull(message = "Exam Status cannot be blank" )
	@Column(name="exam_status")
	private Boolean examStatus;

	@NotBlank(message = "Exam Year cannot be blank")
	@Column(name="exam_year")
	private String examYear;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCentreAllotment
	@OneToMany(mappedBy="examMaster")
	private List<ApplicantCentreAllotment> applicantCentreAllotments;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="examMaster")
	private List<CentreMaster> centreMasters;
	
	//bi-directional many-to-one association to CentreUserMapping
	@OneToMany(mappedBy="examMaster")
	private List<CentreUserMapping> centreUserMappings;

	//bi-directional many-to-one association to EmailSent
	@OneToMany(mappedBy="exam")
	private List<IcgOfficerEmailSent> emailSents;
		
	@OneToMany(mappedBy="examMaster")
	private List<ExamDetailsDisplay> examDetailsDisplays;

	//bi-directional many-to-one association to ExamSlot
	@OneToMany(mappedBy="examMaster")
	private List<ExamSlot> examSlots;

	//bi-directional many-to-one association to ExamSlotSectionMapping
	@OneToMany(mappedBy="examMaster")
	private List<ExamSlotSectionMapping> examSlotSectionMappings;
		
	//bi-directional many-to-one association to ExamStateMaster
	@OneToMany(mappedBy="examMaster")
	private List<ExamStateMaster> examStateMasters;

	//bi-directional many-to-one association to IcgSetting
	@OneToMany(mappedBy="examMaster")
	private List<IcgSetting> icgSettings;

//	//bi-directional many-to-one association to ExamSlotwiseReportingtime
//	@OneToMany(mappedBy="examMaster")
//	private List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes;

	//bi-directional many-to-one association to RegionMaster
	@OneToMany(mappedBy="examMaster")
	private List<RegionMaster> regionMasters;
	
	//bi-directional many-to-one association to SmsSent
	@OneToMany
	private List<IcgOfficerSMSSent> smsSents;
		
	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	//bi-directional many-to-one association to ExamTypeMaster
	@ManyToOne
	@JoinColumn(name="exam_type_master_id")
	private ExamTypeMaster examTypeMaster;

	public ExamMaster() {
	}

	public ExamMaster(Integer icgOfficerExamMasterId) {
	this.examId=icgOfficerExamMasterId;
	}

	public Integer getExamId() {
		return this.examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getExamDuration() {
		return this.examDuration;
	}

	public void setExamDuration(Integer examDuration) {
		this.examDuration = examDuration;
	}

	public String getExamMonth() {
		return this.examMonth;
	}

	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}

	public String getExamName() {
		return this.examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Boolean getExamStatus() {
		return this.examStatus;
	}

	public void setExamStatus(Boolean examStatus) {
		this.examStatus = examStatus;
	}

	public String getExamYear() {
		return this.examYear;
	}

	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<ApplicantCentreAllotment> getApplicantCentreAllotments() {
		return this.applicantCentreAllotments;
	}

	public void setApplicantCentreAllotments(List<ApplicantCentreAllotment> applicantCentreAllotments) {
		this.applicantCentreAllotments = applicantCentreAllotments;
	}

	public ApplicantCentreAllotment addApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().add(applicantCentreAllotment);
		applicantCentreAllotment.setExamMaster(this);

		return applicantCentreAllotment;
	}

	public ApplicantCentreAllotment removeApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().remove(applicantCentreAllotment);
		applicantCentreAllotment.setExamMaster(null);

		return applicantCentreAllotment;
	}

	public List<CentreMaster> getCentreMasters() {
		return this.centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public CentreMaster addCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().add(centreMaster);
		centreMaster.setExamMaster(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setExamMaster(null);

		return centreMaster;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

	public ExamTypeMaster getExamTypeMaster() {
		return this.examTypeMaster;
	}

	public void setExamTypeMaster(ExamTypeMaster examTypeMaster) {
		this.examTypeMaster = examTypeMaster;
	}

	public List<ExamSlot> getExamSlots() {
		return this.examSlots;
	}

	public void setExamSlots(List<ExamSlot> examSlots) {
		this.examSlots = examSlots;
	}

	public ExamSlot addExamSlot(ExamSlot examSlot) {
		getExamSlots().add(examSlot);
		examSlot.setExamMaster(this);

		return examSlot;
	}

	public ExamSlot removeExamSlot(ExamSlot examSlot) {
		getExamSlots().remove(examSlot);
		examSlot.setExamMaster(null);

		return examSlot;
	}


	public List<ExamSlotSectionMapping> getExamSlotSectionMappings() {
		return examSlotSectionMappings;
	}

	public void setExamSlotSectionMappings(List<ExamSlotSectionMapping> examSlotSectionMappings) {
		this.examSlotSectionMappings = examSlotSectionMappings;
	}

	public ExamSlotSectionMapping addExamSlotSectionMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().add(examSlotSectionMapping);
		examSlotSectionMapping.setExamMaster(this);

		return examSlotSectionMapping;
	}

	public ExamSlotSectionMapping removeExamSlotSectionMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().remove(examSlotSectionMapping);
		examSlotSectionMapping.setExamMaster(null);
		return examSlotSectionMapping;
	}

//	public List<ExamSlotwiseReportingtime> getExamSlotwiseReportingtimes() {
//		return this.examSlotwiseReportingtimes;
//	}
//
//	public void setExamSlotwiseReportingtimes(List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes) {
//		this.examSlotwiseReportingtimes = examSlotwiseReportingtimes;
//	}

	public List<RegionMaster> getRegionMasters() {
		return this.regionMasters;
	}

	public void setRegionMasters(List<RegionMaster> regionMasters) {
		this.regionMasters = regionMasters;
	}

	public RegionMaster addRegionMaster(RegionMaster regionMaster) {
		getRegionMasters().add(regionMaster);
		regionMaster.setExamMaster(this);

		return regionMaster;
	}

	public RegionMaster removeRegionMaster(RegionMaster regionMaster) {
		getRegionMasters().remove(regionMaster);
		regionMaster.setExamMaster(null);

		return regionMaster;
	}

	public List<CentreUserMapping> getCentreUserMappings() {
		return centreUserMappings;
	}

	public void setCentreUserMappings(List<CentreUserMapping> centreUserMappings) {
		this.centreUserMappings = centreUserMappings;
	}

	public List<IcgOfficerEmailSent> getEmailSents() {
		return emailSents;
	}

	public void setEmailSents(List<IcgOfficerEmailSent> emailSents) {
		this.emailSents = emailSents;
	}

	public List<ExamDetailsDisplay> getExamDetailsDisplays() {
		return examDetailsDisplays;
	}

	public void setExamDetailsDisplays(List<ExamDetailsDisplay> examDetailsDisplays) {
		this.examDetailsDisplays = examDetailsDisplays;
	}

	public List<ExamStateMaster> getExamStateMasters() {
		return examStateMasters;
	}

	public void setExamStateMasters(List<ExamStateMaster> examStateMasters) {
		this.examStateMasters = examStateMasters;
	}

	public List<IcgSetting> getIcgSettings() {
		return icgSettings;
	}

	public void setIcgSettings(List<IcgSetting> icgSettings) {
		this.icgSettings = icgSettings;
	}

	public List<IcgOfficerSMSSent> getSmsSents() {
		return smsSents;
	}

	public void setSmsSents(List<IcgOfficerSMSSent> smsSents) {
		this.smsSents = smsSents;
	}

	
}