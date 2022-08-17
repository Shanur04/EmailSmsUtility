package gov.cdac.emailservice.icg.pojo;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@SequenceGenerator(name="EXAMDSLOTWISEREPORTINGTIMEID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMDSLOTWISEREPORTINGTIMEID_GENERATOR")
	@Column(name="exam_slotwise_reportingtime_id")
	private Integer examSlotwiseReportingtimeId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="reporting_batch")
	private String reportingBatch;

	@Column(name="reporting_batch_sequence")
	private Integer reportingBatchSequence;

	@Column(name="reporting_batch_status")
	private Boolean reportingBatchStatus;

	@Column(name="reporting_time")
	private String reportingTime;
	
	@Column(name="reporting_closing_time")
	private String reportingClosingTime;
	
	@Column(name="exam_slot_start_time")
	private String examStartTime;
	
	@Column(name="exam_slot_end_time")
	private String examCloseTime;

	//bi-directional many-to-one association to ApplicantHallticket
	@OneToMany(mappedBy="examSlotwiseReportingtime")
	private List<ApplicantHallticket> applicantHalltickets;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;
    
    @ManyToOne
    @JoinColumn(name = "icg_post_combination_id")
    private IcgPostCombination icgPostCombination;
	    
	public ExamSlotwiseReportingtime() {
	}

    public String getExamCloseTime() {
		return examCloseTime;
	}

	public void setExamCloseTime(String examCloseTime) {
		this.examCloseTime = examCloseTime;
	}



	public IcgPostCombination getIcgPostCombination() {
		return icgPostCombination;
	}



	public void setIcgPostCombination(IcgPostCombination icgPostCombination) {
		this.icgPostCombination = icgPostCombination;
	}

    public ExamSlotwiseReportingtime(Integer examSlotwiseReportingtimeId) {
	this.examSlotwiseReportingtimeId = examSlotwiseReportingtimeId;
    }

    public String getKey() {
	return this.reportingBatchSequence + "" + this.examSlot.getExamSlotCode() + this.icgPostCombination.getPostCombinationCode();
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

	public String getReportingTime() {
		return this.reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	public List<ApplicantHallticket> getApplicantHalltickets() {
		return this.applicantHalltickets;
	}

	public void setApplicantHalltickets(List<ApplicantHallticket> applicantHalltickets) {
		this.applicantHalltickets = applicantHalltickets;
	}

	public ApplicantHallticket addApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().add(applicantHallticket);
		applicantHallticket.setExamSlotwiseReportingtime(this);

		return applicantHallticket;
	}

	public String getReportingClosingTime() {
		return reportingClosingTime;
	}

	public void setReportingClosingTime(String reportingClosingTime) {
		this.reportingClosingTime = reportingClosingTime;
	}

	public String getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}

	public String getExamCloseime() {
		return examCloseTime;
	}

	public void setExamCloseime(String examCloseime) {
		this.examCloseTime = examCloseime;
	}

	public ApplicantHallticket removeApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().remove(applicantHallticket);
		applicantHallticket.setExamSlotwiseReportingtime(null);

		return applicantHallticket;
	}

	public ExamSlot getExamSlot() {
		return this.examSlot;
	}

	public void setExamSlot(ExamSlot examSlot) {
		this.examSlot = examSlot;
	}

}