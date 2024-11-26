package gov.cdac.icgPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the exam_slot database table.
 * 
 */
@Entity
@Table(name="exam_slot")
@NamedQuery(name="ExamSlot.findAll", query="SELECT e FROM ExamSlot e")
@JsonIgnoreProperties({"centreExamslotMappings","examSlotwiseReportingtimes"})
public class ExamSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMSLOTID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMSLOTID_GENERATOR")
	@Column(name="exam_slot_id")
	private Integer examSlotId;

	@Column(name="exam_date")
	private Timestamp examDate;

	@Column(name="exam_slot_code")
	private String examSlotCode;

	@Column(name="exam_slot_endtime")
	private Timestamp examSlotEndtime;

	@Column(name="exam_slot_name")
	private String examSlotName;

	@Column(name="exam_slot_shift")
	private String examSlotShift;

	@Column(name="exam_slot_starttime")
	private Timestamp examSlotStarttime;

	@Column(name="exam_slot_status")
	private Boolean examSlotStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@ManyToOne
	@JoinColumn(name="exam_details_display_id")
	private ExamDetailsDisplay examDetailDisplay;

	//bi-directional many-to-one association to CentreExamslotMapping
	@OneToMany(mappedBy="examSlot")
	private List<CentreExamslotMapping> centreExamslotMappings;

	//bi-directional many-to-one association to ExamSlotwiseReportingtime
	@OneToMany(mappedBy="examSlot")
	private List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes;
	
    // bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamMaster examMaster;

	public ExamSlot() {
	}

	public ExamDetailsDisplay getExamDetailDisplay() {
		return examDetailDisplay;
	}

	public void setExamDetailDisplay(ExamDetailsDisplay examDetailDisplay) {
		this.examDetailDisplay = examDetailDisplay;
	}

	public ExamMaster getExamMaster() {
	    return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
	    this.examMaster = examMaster;
	}

	public Integer getExamSlotId() {
		return this.examSlotId;
	}

	public void setExamSlotId(Integer examSlotId) {
		this.examSlotId = examSlotId;
	}

	public Timestamp getExamDate() {
		return this.examDate;
	}

	public void setExamDate(Timestamp examDate) {
		this.examDate = examDate;
	}

	public String getExamSlotCode() {
		return this.examSlotCode;
	}

	public void setExamSlotCode(String examSlotCode) {
		this.examSlotCode = examSlotCode;
	}

	public Timestamp getExamSlotEndtime() {
		return this.examSlotEndtime;
	}

	public void setExamSlotEndtime(Timestamp examSlotEndtime) {
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

	public Timestamp getExamSlotStarttime() {
		return this.examSlotStarttime;
	}

	public void setExamSlotStarttime(Timestamp examSlotStarttime) {
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

}