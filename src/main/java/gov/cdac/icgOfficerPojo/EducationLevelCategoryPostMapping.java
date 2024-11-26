package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the education_level_category_post_mapping database table.
 * 
 */
@Entity
@Table(name="education_level_category_post_mapping")
@NamedQuery(name="EducationLevelCategoryPostMapping.findAll", query="SELECT e FROM EducationLevelCategoryPostMapping e")
public class EducationLevelCategoryPostMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATIONLEVELCATEGORYPOSTMAPPINGID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATIONLEVELCATEGORYPOSTMAPPINGID_GENERATOR")
	@Column(name="education_level_category_post_mapping_id")
	private Integer educationLevelCategoryPostMappingId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to EducationLevelCategory
	@ManyToOne
	@JoinColumn(name="education_level_category_id")
	private EducationLevelCategory educationLevelCategory;
	
	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public EducationLevelCategoryPostMapping() {
	}

	public Integer getEducationLevelCategoryPostMappingId() {
		return this.educationLevelCategoryPostMappingId;
	}

	public void setEducationLevelCategoryPostMappingId(Integer educationLevelCategoryPostMappingId) {
		this.educationLevelCategoryPostMappingId = educationLevelCategoryPostMappingId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public EducationLevelCategory getEducationLevelCategory() {
		return this.educationLevelCategory;
	}

	public void setEducationLevelCategory(EducationLevelCategory educationLevelCategory) {
		this.educationLevelCategory = educationLevelCategory;
	}

	public IcgPost getIcgPost() {
		return icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	
}