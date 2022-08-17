package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rps_question_difficulty_level database table.
 * 
 */
@Entity
@Table(name="rps_question_difficulty_level")
@NamedQuery(name="RpsQuestionDifficultyLevel.findAll", query="SELECT r FROM RpsQuestionDifficultyLevel r")
public class RpsQuestionDifficultyLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="candidate_attempts_count")
	private Long candidateAttemptsCount;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(length=255)
	private String city;

	@Column(name="correctly_answered_count")
	private Long correctlyAnsweredCount;

	@Column(name="difficulty_level_percentage")
	private float difficultyLevelPercentage;

	@Column(name="difficulty_level_value")
	private Integer difficultyLevelValue;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="exam_date")
	private Timestamp examDate;

	@Column(name="paper_code", length=255)
	private String paperCode;

	@Column(name="question_number")
	private Integer questionNumber;

	@Column(length=255)
	private String region;

	@Column(name="set_name", length=255)
	private String setName;

	@Column(name="slot_code", length=255)
	private String slotCode;

	@Column(length=255)
	private String state;

	@Column(length=255)
	private String subject;

	@Column(name="total_candidates_appeared")
	private Long totalCandidatesAppeared;

	//bi-directional many-to-one association to RpsExamSlot
	@ManyToOne
	@JoinColumn(name="fk_exam_slot_id")
	private RpsExamSlot rpsExamSlot;

	public RpsQuestionDifficultyLevel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidateAttemptsCount() {
		return this.candidateAttemptsCount;
	}

	public void setCandidateAttemptsCount(Long candidateAttemptsCount) {
		this.candidateAttemptsCount = candidateAttemptsCount;
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

	public Long getCorrectlyAnsweredCount() {
		return this.correctlyAnsweredCount;
	}

	public void setCorrectlyAnsweredCount(Long correctlyAnsweredCount) {
		this.correctlyAnsweredCount = correctlyAnsweredCount;
	}

	public float getDifficultyLevelPercentage() {
		return this.difficultyLevelPercentage;
	}

	public void setDifficultyLevelPercentage(float difficultyLevelPercentage) {
		this.difficultyLevelPercentage = difficultyLevelPercentage;
	}

	public Integer getDifficultyLevelValue() {
		return this.difficultyLevelValue;
	}

	public void setDifficultyLevelValue(Integer difficultyLevelValue) {
		this.difficultyLevelValue = difficultyLevelValue;
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

	public Integer getQuestionNumber() {
		return this.questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSetName() {
		return this.setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
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

	public RpsExamSlot getRpsExamSlot() {
		return this.rpsExamSlot;
	}

	public void setRpsExamSlot(RpsExamSlot rpsExamSlot) {
		this.rpsExamSlot = rpsExamSlot;
	}

}