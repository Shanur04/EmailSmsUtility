package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_section_question_number database table.
 * 
 */
@Entity
@Table(name="rps_section_question_number")
@NamedQuery(name="RpsSectionQuestionNumber.findAll", query="SELECT r FROM RpsSectionQuestionNumber r")
public class RpsSectionQuestionNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="max_question_number")
	private Integer maxQuestionNumber;

	@Column(name="min_question_number")
	private Integer minQuestionNumber;

	@Column(name="section_order")
	private Integer sectionOrder;

	//bi-directional many-to-one association to RpsExamSection
	@ManyToOne
	@JoinColumn(name="fk_exam_section_id")
	private RpsExamSection rpsExamSection;

	//bi-directional many-to-one association to RpsQuestionPaperSection
	@ManyToOne
	@JoinColumn(name="fk_question_paper_section_id")
	private RpsQuestionPaperSection rpsQuestionPaperSection;

	public RpsSectionQuestionNumber() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxQuestionNumber() {
		return this.maxQuestionNumber;
	}

	public void setMaxQuestionNumber(Integer maxQuestionNumber) {
		this.maxQuestionNumber = maxQuestionNumber;
	}

	public Integer getMinQuestionNumber() {
		return this.minQuestionNumber;
	}

	public void setMinQuestionNumber(Integer minQuestionNumber) {
		this.minQuestionNumber = minQuestionNumber;
	}

	public Integer getSectionOrder() {
		return this.sectionOrder;
	}

	public void setSectionOrder(Integer sectionOrder) {
		this.sectionOrder = sectionOrder;
	}

	public RpsExamSection getRpsExamSection() {
		return this.rpsExamSection;
	}

	public void setRpsExamSection(RpsExamSection rpsExamSection) {
		this.rpsExamSection = rpsExamSection;
	}

	public RpsQuestionPaperSection getRpsQuestionPaperSection() {
		return this.rpsQuestionPaperSection;
	}

	public void setRpsQuestionPaperSection(RpsQuestionPaperSection rpsQuestionPaperSection) {
		this.rpsQuestionPaperSection = rpsQuestionPaperSection;
	}

}