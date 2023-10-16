package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_exam_category_section_mapping database table.
 * 
 */
@Entity
@Table(name="rps_exam_category_section_mapping")
@NamedQuery(name="RpsExamCategorySectionMapping.findAll", query="SELECT r FROM RpsExamCategorySectionMapping r")
public class RpsExamCategorySectionMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	//bi-directional many-to-one association to RpsExamCategoryMaster
	@ManyToOne
	@JoinColumn(name="fk_exam_category_code")
	private RpsExamCategoryMaster rpsExamCategoryMaster;

	//bi-directional many-to-one association to RpsExamSection
	@ManyToOne
	@JoinColumn(name="fk_exam_section_id")
	private RpsExamSection rpsExamSection;

	public RpsExamCategorySectionMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RpsExamCategoryMaster getRpsExamCategoryMaster() {
		return this.rpsExamCategoryMaster;
	}

	public void setRpsExamCategoryMaster(RpsExamCategoryMaster rpsExamCategoryMaster) {
		this.rpsExamCategoryMaster = rpsExamCategoryMaster;
	}

	public RpsExamSection getRpsExamSection() {
		return this.rpsExamSection;
	}

	public void setRpsExamSection(RpsExamSection rpsExamSection) {
		this.rpsExamSection = rpsExamSection;
	}

}