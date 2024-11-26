package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rps_question_paper_analysis database table.
 * 
 */
@Entity
@Table(name="rps_question_paper_analysis")
@NamedQuery(name="RpsQuestionPaperAnalysi.findAll", query="SELECT r FROM RpsQuestionPaperAnalysi r")
public class RpsQuestionPaperAnalysi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="candidate_passed_count")
	private Long candidatePassedCount;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(length=255)
	private String city;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="exam_date")
	private Timestamp examDate;

	@Column(name="paper_code", length=255)
	private String paperCode;

	@Column(name="passed_percentage")
	private float passedPercentage;

	@Column(length=255)
	private String region;

	@Column(name="slot_code", length=255)
	private String slotCode;

	@Column(length=255)
	private String state;

	@Column(length=255)
	private String subject;

	@Column(name="total_candidates_appeared")
	private Long totalCandidatesAppeared;

	@Column(name="total_candidates_called")
	private Long totalCandidatesCalled;

	//bi-directional many-to-one association to RpsExamSlot
	@ManyToOne
	@JoinColumn(name="fk_exam_slot_id")
	private RpsExamSlot rpsExamSlot;

	public RpsQuestionPaperAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidatePassedCount() {
		return this.candidatePassedCount;
	}

	public void setCandidatePassedCount(Long candidatePassedCount) {
		this.candidatePassedCount = candidatePassedCount;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExamCategory() {
		return this.examCategory;
	}

	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Timestamp getExamDate() {
		return this.examDate;
	}

	public void setExamDate(Timestamp examDate) {
		this.examDate = examDate;
	}

	public String getPaperCode() {
		return this.paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public float getPassedPercentage() {
		return this.passedPercentage;
	}

	public void setPassedPercentage(float passedPercentage) {
		this.passedPercentage = passedPercentage;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSlotCode() {
		return this.slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getTotalCandidatesAppeared() {
		return this.totalCandidatesAppeared;
	}

	public void setTotalCandidatesAppeared(Long totalCandidatesAppeared) {
		this.totalCandidatesAppeared = totalCandidatesAppeared;
	}

	public Long getTotalCandidatesCalled() {
		return this.totalCandidatesCalled;
	}

	public void setTotalCandidatesCalled(Long totalCandidatesCalled) {
		this.totalCandidatesCalled = totalCandidatesCalled;
	}

	public RpsExamSlot getRpsExamSlot() {
		return this.rpsExamSlot;
	}

	public void setRpsExamSlot(RpsExamSlot rpsExamSlot) {
		this.rpsExamSlot = rpsExamSlot;
	}

}