package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the education_level_level_category_mapping database table.
 * 
 */
@Entity
@Table(name="education_level_level_category_mapping")
@NamedQuery(name="EducationLevelLevelCategoryMapping.findAll", query="SELECT e FROM EducationLevelLevelCategoryMapping e")
public class EducationLevelLevelCategoryMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATIONLEVELLEVELCATEGORYMAPPINGID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATIONLEVELLEVELCATEGORYMAPPINGID_GENERATOR")
	@Column(name="education_level_level_category_mapping_id")
	private Integer educationLevelLevelCategoryMappingId;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;

	//bi-directional many-to-one association to EducationLevelCategory
	@ManyToOne
	@JoinColumn(name="education_level_category_id")
	private EducationLevelCategory educationLevelCategory;

	public EducationLevelLevelCategoryMapping() {
	}

	public Integer getEducationLevelLevelCategoryMappingId() {
		return this.educationLevelLevelCategoryMappingId;
	}

	public void setEducationLevelLevelCategoryMappingId(Integer educationLevelLevelCategoryMappingId) {
		this.educationLevelLevelCategoryMappingId = educationLevelLevelCategoryMappingId;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public EducationLevelCategory getEducationLevelCategory() {
		return this.educationLevelCategory;
	}

	public void setEducationLevelCategory(EducationLevelCategory educationLevelCategory) {
		this.educationLevelCategory = educationLevelCategory;
	}

	@Override
	public String toString() {
		return "EducationLevelLevelCategoryMapping [educationLevelLevelCategoryMappingId="
				+ educationLevelLevelCategoryMappingId + ", educationLevel=" + educationLevel
				+ ", educationLevelCategory=" + educationLevelCategory + "]";
	}

	
}