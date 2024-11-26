package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_exam_section database table.
 * 
 */
@Entity
@Table(name="rps_exam_section")
@NamedQuery(name="RpsExamSection.findAll", query="SELECT r FROM RpsExamSection r")
public class RpsExamSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String code;

	@Column(nullable=false, length=255)
	private String name;

	//bi-directional many-to-one association to RpsExamCategorySectionMapping
	@OneToMany(mappedBy="rpsExamSection")
	private List<RpsExamCategorySectionMapping> rpsExamCategorySectionMappings;

	//bi-directional many-to-one association to RpsPassingCriteria
	@OneToMany(mappedBy="rpsExamSection")
	private List<RpsPassingCriteria> rpsPassingCriterias;

	//bi-directional many-to-one association to RpsSectionQuestionNumber
	@OneToMany(mappedBy="rpsExamSection")
	private List<RpsSectionQuestionNumber> rpsSectionQuestionNumbers;

	public RpsExamSection() {
	}
	
	

	public RpsExamSection(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RpsExamCategorySectionMapping> getRpsExamCategorySectionMappings() {
		return this.rpsExamCategorySectionMappings;
	}

	public void setRpsExamCategorySectionMappings(List<RpsExamCategorySectionMapping> rpsExamCategorySectionMappings) {
		this.rpsExamCategorySectionMappings = rpsExamCategorySectionMappings;
	}

	public RpsExamCategorySectionMapping addRpsExamCategorySectionMapping(RpsExamCategorySectionMapping rpsExamCategorySectionMapping) {
		getRpsExamCategorySectionMappings().add(rpsExamCategorySectionMapping);
		rpsExamCategorySectionMapping.setRpsExamSection(this);

		return rpsExamCategorySectionMapping;
	}

	public RpsExamCategorySectionMapping removeRpsExamCategorySectionMapping(RpsExamCategorySectionMapping rpsExamCategorySectionMapping) {
		getRpsExamCategorySectionMappings().remove(rpsExamCategorySectionMapping);
		rpsExamCategorySectionMapping.setRpsExamSection(null);

		return rpsExamCategorySectionMapping;
	}

	public List<RpsPassingCriteria> getRpsPassingCriterias() {
		return this.rpsPassingCriterias;
	}

	public void setRpsPassingCriterias(List<RpsPassingCriteria> rpsPassingCriterias) {
		this.rpsPassingCriterias = rpsPassingCriterias;
	}

	public RpsPassingCriteria addRpsPassingCriteria(RpsPassingCriteria rpsPassingCriteria) {
		getRpsPassingCriterias().add(rpsPassingCriteria);
		rpsPassingCriteria.setRpsExamSection(this);

		return rpsPassingCriteria;
	}

	public RpsPassingCriteria removeRpsPassingCriteria(RpsPassingCriteria rpsPassingCriteria) {
		getRpsPassingCriterias().remove(rpsPassingCriteria);
		rpsPassingCriteria.setRpsExamSection(null);

		return rpsPassingCriteria;
	}

	public List<RpsSectionQuestionNumber> getRpsSectionQuestionNumbers() {
		return this.rpsSectionQuestionNumbers;
	}

	public void setRpsSectionQuestionNumbers(List<RpsSectionQuestionNumber> rpsSectionQuestionNumbers) {
		this.rpsSectionQuestionNumbers = rpsSectionQuestionNumbers;
	}

	public RpsSectionQuestionNumber addRpsSectionQuestionNumber(RpsSectionQuestionNumber rpsSectionQuestionNumber) {
		getRpsSectionQuestionNumbers().add(rpsSectionQuestionNumber);
		rpsSectionQuestionNumber.setRpsExamSection(this);

		return rpsSectionQuestionNumber;
	}

	public RpsSectionQuestionNumber removeRpsSectionQuestionNumber(RpsSectionQuestionNumber rpsSectionQuestionNumber) {
		getRpsSectionQuestionNumbers().remove(rpsSectionQuestionNumber);
		rpsSectionQuestionNumber.setRpsExamSection(null);

		return rpsSectionQuestionNumber;
	}

}