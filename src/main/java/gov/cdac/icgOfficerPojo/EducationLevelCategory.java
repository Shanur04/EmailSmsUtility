package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the education_level_category database table.
 * 
 */
@Entity
@Table(name="education_level_category")
@NamedQuery(name="EducationLevelCategory.findAll", query="SELECT e FROM EducationLevelCategory e")
@JsonIgnoreProperties({"educationLevelCategoryPostMappings","personalDetails","educationLevelLevelCategoryMappings"})
public class EducationLevelCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATIONLEVELCATEGORYID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATIONLEVELCATEGORYID_GENERATOR")
	@Column(name="education_level_category_id")
	private Integer educationLevelCategoryId;

	@Column(name="education_level_category_alias")
	private String educationLevelCategoryAlias;

	@Column(name="education_level_category_name")
	private String educationLevelCategoryName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to EducationLevelCategoryPostMapping
	@OneToMany(mappedBy="educationLevelCategory")
	private List<EducationLevelCategoryPostMapping> educationLevelCategoryPostMappings;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="educationLevelCategory")
	private List<PersonalDetail> personalDetails;
	
	@Column(name="is_lateral_entry_applicable_in_graduation")
	private Boolean isLateralEntryApplicableInGraduation;

	
	//bi-directional many-to-one association to EducationLevelLevelCategoryMapping
	@OneToMany(mappedBy="educationLevelCategory")
	private List<EducationLevelLevelCategoryMapping> educationLevelLevelCategoryMappings;

	public EducationLevelCategory() {
	}

	
	
	public Boolean getIsLateralEntryApplicableInGraduation() {
		return isLateralEntryApplicableInGraduation;
	}



	public void setIsLateralEntryApplicableInGraduation(Boolean isLateralEntryApplicableInGraduation) {
		this.isLateralEntryApplicableInGraduation = isLateralEntryApplicableInGraduation;
	}



	public Integer getEducationLevelCategoryId() {
		return this.educationLevelCategoryId;
	}

	public void setEducationLevelCategoryId(Integer educationLevelCategoryId) {
		this.educationLevelCategoryId = educationLevelCategoryId;
	}

	public String getEducationLevelCategoryAlias() {
		return this.educationLevelCategoryAlias;
	}

	public void setEducationLevelCategoryAlias(String educationLevelCategoryAlias) {
		this.educationLevelCategoryAlias = educationLevelCategoryAlias;
	}

	public String getEducationLevelCategoryName() {
		return this.educationLevelCategoryName;
	}

	public void setEducationLevelCategoryName(String educationLevelCategoryName) {
		this.educationLevelCategoryName = educationLevelCategoryName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<EducationLevelCategoryPostMapping> getEducationLevelCategoryPostMappings() {
		return this.educationLevelCategoryPostMappings;
	}

	public void setEducationLevelCategoryPostMappings(List<EducationLevelCategoryPostMapping> educationLevelCategoryPostMappings) {
		this.educationLevelCategoryPostMappings = educationLevelCategoryPostMappings;
	}

	public EducationLevelCategoryPostMapping addEducationLevelCategoryPostMapping(EducationLevelCategoryPostMapping educationLevelCategoryPostMapping) {
		getEducationLevelCategoryPostMappings().add(educationLevelCategoryPostMapping);
		educationLevelCategoryPostMapping.setEducationLevelCategory(this);

		return educationLevelCategoryPostMapping;
	}

	public EducationLevelCategoryPostMapping removeEducationLevelCategoryPostMapping(EducationLevelCategoryPostMapping educationLevelCategoryPostMapping) {
		getEducationLevelCategoryPostMappings().remove(educationLevelCategoryPostMapping);
		educationLevelCategoryPostMapping.setEducationLevelCategory(null);

		return educationLevelCategoryPostMapping;
	}

	public List<PersonalDetail> getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setEducationLevelCategory(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setEducationLevelCategory(null);

		return personalDetail;
	}

	public List<EducationLevelLevelCategoryMapping> getEducationLevelLevelCategoryMappings() {
		return educationLevelLevelCategoryMappings;
	}

	public void setEducationLevelLevelCategoryMappings(
			List<EducationLevelLevelCategoryMapping> educationLevelLevelCategoryMappings) {
		this.educationLevelLevelCategoryMappings = educationLevelLevelCategoryMappings;
	}

	@Override
	public String toString() {
		return "EducationLevelCategory [educationLevelCategoryId=" + educationLevelCategoryId
				+ ", educationLevelCategoryAlias=" + educationLevelCategoryAlias + ", educationLevelCategoryName="
				+ educationLevelCategoryName + ", recordTracking=" + recordTracking
				+ ", educationLevelCategoryPostMappings=" + educationLevelCategoryPostMappings + ", personalDetails="
				+ personalDetails + ", educationLevelLevelCategoryMappings=" + educationLevelLevelCategoryMappings
				+ "]";
	}

	

}