package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the exam_slot database table.
 * 
 */
@Entity
@Table(name="exam_slot")
@NamedQuery(name="ExamSlot.findAll", query="SELECT e FROM ExamSlot e")
@JsonIgnoreProperties({"allocationCriteriaMasters","centreExamslotMappings","examPapers","examPaperKeys","examSlotSubjectMappings"
	,"examSlotwiseReportingtimes","examSlotSectionMappings"})
public class ExamSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAM_SLOT_EXAMSLOTID_GENERATOR",  allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAM_SLOT_EXAMSLOTID_GENERATOR")
	@Column(name="exam_slot_id")
	private Integer examSlotId;

	@Temporal(TemporalType.DATE)
	@Column(name="exam_date")
	private Date examDate;

	@Column(name="exam_slot_code")
	private String examSlotCode;

	@Column(name="exam_slot_endtime")
	private Time examSlotEndtime;

	@Column(name="exam_slot_name")
	private String examSlotName;

	@Column(name="exam_slot_shift")
	private String examSlotShift;

	@Column(name="exam_slot_starttime")
	private Time examSlotStarttime;

	@Column(name="exam_slot_status")
	private Boolean examSlotStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to AllocationCriteriaMaster
	@OneToMany(mappedBy="examSlot")
	private List<AllocationCriteriaMaster> allocationCriteriaMasters;
	
	//bi-directional many-to-one association to CentreExamslotMapping
	@OneToMany(mappedBy="examSlot")
	private List<CentreExamslotMapping> centreExamslotMappings;

	//bi-directional many-to-one association to ExamPaper
	@OneToMany(mappedBy="examSlot")
	private List<ExamPaper> examPapers;

	//bi-directional many-to-one association to ExamPaperKey
	@OneToMany(mappedBy="examSlot")
	private List<ExamPaperKey> examPaperKeys;	

	//bi-directional many-to-one association to ExamSlotSectionMapping
	@OneToMany(mappedBy="examSlot")
	private List<ExamSlotSectionMapping> examSlotSectionMappings;

	//bi-directional many-to-one association to ExamSlotwiseReportingtime
	@OneToMany(mappedBy="examSlot")
	private List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes;
	
	//bi-directional many-to-one association to ExamDetailsDisplay
	@ManyToOne
	@JoinColumn(name="exam_details_display_id")
	private ExamDetailsDisplay examDetailsDisplay;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	public ExamSlot() {
	}

	public Integer getExamSlotId() {
		return this.examSlotId;
	}

	public void setExamSlotId(Integer examSlotId) {
		this.examSlotId = examSlotId;
	}

	public String getExamSlotCode() {
		return this.examSlotCode;
	}

	public void setExamSlotCode(String examSlotCode) {
		this.examSlotCode = examSlotCode;
	}

	public Time getExamSlotEndtime() {
		return this.examSlotEndtime;
	}

	public void setExamSlotEndtime(Time examSlotEndtime) {
		this.examSlotEndtime = examSlotEndtime;
	}

	public String getExamSlotName() {
		return this.examSlotName;
	}

	public void setExamSlotName(String examSlotName) {
		this.examSlotName = examSlotName;
	}

	public String getExamSlotShift() {
		return this.examSlotShift;
	}

	public void setExamSlotShift(String examSlotShift) {
		this.examSlotShift = examSlotShift;
	}

	public Time getExamSlotStarttime() {
		return this.examSlotStarttime;
	}

	public void setExamSlotStarttime(Time examSlotStarttime) {
		this.examSlotStarttime = examSlotStarttime;
	}

	public Boolean getExamSlotStatus() {
		return this.examSlotStatus;
	}

	public void setExamSlotStatus(Boolean examSlotStatus) {
		this.examSlotStatus = examSlotStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<CentreExamslotMapping> getCentreExamslotMappings() {
		return this.centreExamslotMappings;
	}

	public void setCentreExamslotMappings(List<CentreExamslotMapping> centreExamslotMappings) {
		this.centreExamslotMappings = centreExamslotMappings;
	}

	public CentreExamslotMapping addCentreExamslotMapping(CentreExamslotMapping centreExamslotMapping) {
		getCentreExamslotMappings().add(centreExamslotMapping);
		centreExamslotMapping.setExamSlot(this);

		return centreExamslotMapping;
	}

	public CentreExamslotMapping removeCentreExamslotMapping(CentreExamslotMapping centreExamslotMapping) {
		getCentreExamslotMappings().remove(centreExamslotMapping);
		centreExamslotMapping.setExamSlot(null);

		return centreExamslotMapping;
	}

	public List<ExamPaper> getExamPapers() {
		return this.examPapers;
	}

	public void setExamPapers(List<ExamPaper> examPapers) {
		this.examPapers = examPapers;
	}

	public ExamPaper addExamPaper(ExamPaper examPaper) {
		getExamPapers().add(examPaper);
		examPaper.setExamSlot(this);

		return examPaper;
	}

	public ExamPaper removeExamPaper(ExamPaper examPaper) {
		getExamPapers().remove(examPaper);
		examPaper.setExamSlot(null);

		return examPaper;
	}

	public List<ExamPaperKey> getExamPaperKeys() {
		return this.examPaperKeys;
	}

	public void setExamPaperKeys(List<ExamPaperKey> examPaperKeys) {
		this.examPaperKeys = examPaperKeys;
	}

	public ExamPaperKey addExamPaperKey(ExamPaperKey examPaperKey) {
		getExamPaperKeys().add(examPaperKey);
		examPaperKey.setExamSlot(this);

		return examPaperKey;
	}

	public ExamPaperKey removeExamPaperKey(ExamPaperKey examPaperKey) {
		getExamPaperKeys().remove(examPaperKey);
		examPaperKey.setExamSlot(null);

		return examPaperKey;
	}

	public ExamDetailsDisplay getExamDetailsDisplay() {
		return this.examDetailsDisplay;
	}

	public void setExamDetailsDisplay(ExamDetailsDisplay examDetailsDisplay) {
		this.examDetailsDisplay = examDetailsDisplay;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	

	public List<AllocationCriteriaMaster> getAllocationCriteriaMasters() {
		return allocationCriteriaMasters;
	}

	public void setAllocationCriteriaMasters(List<AllocationCriteriaMaster> allocationCriteriaMasters) {
		this.allocationCriteriaMasters = allocationCriteriaMasters;
	}

	public List<ExamSlotSectionMapping> getExamSlotSectionMappings() {
		return examSlotSectionMappings;
	}

	public void setExamSlotSectionMappings(List<ExamSlotSectionMapping> examSlotSectionMappings) {
		this.examSlotSectionMappings = examSlotSectionMappings;
	}

	public ExamSlotSectionMapping addExamSlotSectionMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().add(examSlotSectionMapping);
		examSlotSectionMapping.setExamSlot(this);

		return examSlotSectionMapping;
	}

	public ExamSlotSectionMapping removeExamSlotSubjectMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().remove(examSlotSectionMapping);
		examSlotSectionMapping.setExamSlot(null);
		return examSlotSectionMapping;
	}


	public List<ExamSlotwiseReportingtime> getExamSlotwiseReportingtimes() {
		return this.examSlotwiseReportingtimes;
	}

	public void setExamSlotwiseReportingtimes(List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes) {
		this.examSlotwiseReportingtimes = examSlotwiseReportingtimes;
	}

	public ExamSlotwiseReportingtime addExamSlotwiseReportingtime(ExamSlotwiseReportingtime examSlotwiseReportingtime) {
		getExamSlotwiseReportingtimes().add(examSlotwiseReportingtime);
		examSlotwiseReportingtime.setExamSlot(this);

		return examSlotwiseReportingtime;
	}

	public ExamSlotwiseReportingtime removeExamSlotwiseReportingtime(ExamSlotwiseReportingtime examSlotwiseReportingtime) {
		getExamSlotwiseReportingtimes().remove(examSlotwiseReportingtime);
		examSlotwiseReportingtime.setExamSlot(null);

		return examSlotwiseReportingtime;
	}
public Date getExamDate() {
	return examDate;
}

public void setExamDate(Date examDate) {
	this.examDate = examDate;
}

}













