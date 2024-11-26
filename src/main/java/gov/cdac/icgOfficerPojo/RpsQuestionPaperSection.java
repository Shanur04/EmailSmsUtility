package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_question_paper_section database table.
 * 
 */
@Entity
@Table(name="rps_question_paper_section")
@NamedQuery(name="RpsQuestionPaperSection.findAll", query="SELECT r FROM RpsQuestionPaperSection r")
public class RpsQuestionPaperSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="paper_question_count")
	private Integer paperQuestionCount;

	//bi-directional many-to-one association to RpsSectionQuestionNumber
	@OneToMany(mappedBy="rpsQuestionPaperSection")
	private List<RpsSectionQuestionNumber> rpsSectionQuestionNumbers;

	public RpsQuestionPaperSection() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getPaperQuestionCount() {
		return this.paperQuestionCount;
	}

	public void setPaperQuestionCount(Integer paperQuestionCount) {
		this.paperQuestionCount = paperQuestionCount;
	}

	public List<RpsSectionQuestionNumber> getRpsSectionQuestionNumbers() {
		return this.rpsSectionQuestionNumbers;
	}

	public void setRpsSectionQuestionNumbers(List<RpsSectionQuestionNumber> rpsSectionQuestionNumbers) {
		this.rpsSectionQuestionNumbers = rpsSectionQuestionNumbers;
	}

	public RpsSectionQuestionNumber addRpsSectionQuestionNumber(RpsSectionQuestionNumber rpsSectionQuestionNumber) {
		getRpsSectionQuestionNumbers().add(rpsSectionQuestionNumber);
		rpsSectionQuestionNumber.setRpsQuestionPaperSection(this);

		return rpsSectionQuestionNumber;
	}

	public RpsSectionQuestionNumber removeRpsSectionQuestionNumber(RpsSectionQuestionNumber rpsSectionQuestionNumber) {
		getRpsSectionQuestionNumbers().remove(rpsSectionQuestionNumber);
		rpsSectionQuestionNumber.setRpsQuestionPaperSection(null);

		return rpsSectionQuestionNumber;
	}

}