package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the exam_slotwise_reportingtime database table.
 * 
 */
@Entity
@Table(name="exam_slotwise_reportingtime")
@NamedQuery(name="ExamSlotwiseReportingtime.findAll", query="SELECT e FROM ExamSlotwiseReportingtime e")
@JsonIgnoreProperties({"applicantHalltickets"})
public class ExamSlotwiseReportingtime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMSLOTWISEREPORTINGTIMEID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMSLOTWISEREPORTINGTIMEID_GENERATOR")
	@Column(name="exam_slotwise_reportingtime_id")
	private Integer examSlotwiseReportingtimeId;
	
	@Column(name="postwise_exam_slot_end_time")
	private Timestamp postwiseExamSlotEndTime;

	@Column(name="postwise_exam_slot_start_time")
	private Timestamp postwiseExamSlotStartTime;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="reporting_batch")
	private String reportingBatch;

	@Column(name="reporting_batch_sequence")
	private Integer reportingBatchSequence;

	@Column(name="reporting_batch_status")
	private Boolean reportingBatchStatus;
	
	@Column(name="reporting_closing_time")
	private String reportingClosingTime;

	@Column(name="reporting_start_time")
	private String reportingStartTime;

	/*
	 * @Column(name="reporting_time") private String reportingTime;
	 */

	//bi-directional many-to-one association to ApplicantHallticket
	@OneToMany(mappedBy="examSlotwiseReportingtime")
	private List<ApplicantHallticket> applicantHalltickets;

	//bi-directional many-to-one association to ExamMaster
//	@ManyToOne
//	@JoinColumn(name="exam_id")
//	private ExamMaster examMaster;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;

	//bi-directional many-to-one association to WrittenTestSection
//	@ManyToOne
//	@JoinColumn(name="written_test_section_id")
//	private WrittenTestSection writtenTestSection;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public ExamSlotwiseReportingtime() {
	}

	public Integer getExamSlotwiseReportingtimeId() {
		return this.examSlotwiseReportingtimeId;
	}

	public void setExamSlotwiseReportingtimeId(Integer examSlotwiseReportingtimeId) {
		this.examSlotwiseReportingtimeId = examSlotwiseReportingtimeId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getReportingBatch() {
		return this.reportingBatch;
	}

	public void setReportingBatch(String reportingBatch) {
		this.reportingBatch = reportingBatch;
	}

	public Integer getReportingBatchSequence() {
		return this.reportingBatchSequence;
	}

	public void setReportingBatchSequence(Integer reportingBatchSequence) {
		this.reportingBatchSequence = reportingBatchSequence;
	}

	public Boolean getReportingBatchStatus() {
		return this.reportingBatchStatus;
	}

	public void setReportingBatchStatus(Boolean reportingBatchStatus) {
		this.reportingBatchStatus = reportingBatchStatus;
	}
	

	public Timestamp getPostwiseExamSlotEndTime() {
		return postwiseExamSlotEndTime;
	}

	public void setPostwiseExamSlotEndTime(Timestamp postwiseExamSlotEndTime) {
		this.postwiseExamSlotEndTime = postwiseExamSlotEndTime;
	}

	public Timestamp getPostwiseExamSlotStartTime() {
		return postwiseExamSlotStartTime;
	}

	public void setPostwiseExamSlotStartTime(Timestamp postwiseExamSlotStartTime) {
		this.postwiseExamSlotStartTime = postwiseExamSlotStartTime;
	}

	public String getReportingClosingTime() {
		return reportingClosingTime;
	}

	public void setReportingClosingTime(String reportingClosingTime) {
		this.reportingClosingTime = reportingClosingTime;
	}

	public String getReportingStartTime() {
		return reportingStartTime;
	}

	public void setReportingStartTime(String reportingStartTime) {
		this.reportingStartTime = reportingStartTime;
	}

	public IcgPost getIcgPost() {
		return icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ApplicantHallticket> getApplicantHalltickets() {
		return this.applicantHalltickets;
	}

	public void setApplicantHalltickets(List<ApplicantHallticket> applicantHalltickets) {
		this.applicantHalltickets = applicantHalltickets;
	}

//	public ExamMaster getExamMaster() {
//		return this.examMaster;
//	}
//
//	public void setExamMaster(ExamMaster examMaster) {
//		this.examMaster = examMaster;
//	}

	public ExamSlot getExamSlot() {
		return this.examSlot;
	}

	public void setExamSlot(ExamSlot examSlot) {
		this.examSlot = examSlot;
	}

//	public WrittenTestSection getWrittenTestSection() {
//		return this.writtenTestSection;
//	}
//
//	public void setWrittenTestSection(WrittenTestSection writtenTestSection) {
//		this.writtenTestSection = writtenTestSection;
//	}

}