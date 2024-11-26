package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rps_section_result database table.
 * 
 */
@Entity
@Table(name="rps_section_result")
@NamedQuery(name="RpsSectionResult.findAll", query="SELECT r FROM RpsSectionResult r")
public class RpsSectionResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="candidate_category", length=255)
	private String candidateCategory;

	@Column(name="correctly_answered_questions", length=1000)
	private String correctlyAnsweredQuestions;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="marks_obtained", length=255)
	private String marksObtained;

	@Column(name="paper_code", length=255)
	private String paperCode;

	@Column(name="set_name", length=255)
	private String setName;

	@Column(name="subject_name", length=255)
	private String subjectName;

	@Column(name="unanswered_questions", length=1000)
	private String unansweredQuestions;

	@Column(name="wrong_questions", length=1000)
	private String wrongQuestions;

	//bi-directional many-to-one association to RpsExamResult
	@ManyToOne
	@JoinColumn(name="fk_exam_result_id")
	private RpsExamResult rpsExamResult;

	public RpsSectionResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateCategory() {
		return this.candidateCategory;
	}

	public void setCandidateCategory(String candidateCategory) {
		this.candidateCategory = candidateCategory;
	}

	public String getCorrectlyAnsweredQuestions() {
		return this.correctlyAnsweredQuestions;
	}

	public void setCorrectlyAnsweredQuestions(String correctlyAnsweredQuestions) {
		this.correctlyAnsweredQuestions = correctlyAnsweredQuestions;
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

	public String getMarksObtained() {
		return this.marksObtained;
	}

	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getPaperCode() {
		return this.paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getSetName() {
		return this.setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getUnansweredQuestions() {
		return this.unansweredQuestions;
	}

	public void setUnansweredQuestions(String unansweredQuestions) {
		this.unansweredQuestions = unansweredQuestions;
	}

	public String getWrongQuestions() {
		return this.wrongQuestions;
	}

	public void setWrongQuestions(String wrongQuestions) {
		this.wrongQuestions = wrongQuestions;
	}

	public RpsExamResult getRpsExamResult() {
		return this.rpsExamResult;
	}

	public void setRpsExamResult(RpsExamResult rpsExamResult) {
		this.rpsExamResult = rpsExamResult;
	}

}