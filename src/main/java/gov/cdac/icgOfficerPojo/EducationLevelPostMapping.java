package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the education_level_post_mapping database table.
 * 
 */
@Entity
@Table(name="education_level_post_mapping")
@NamedQuery(name="EducationLevelPostMapping.findAll", query="SELECT e FROM EducationLevelPostMapping e")
public class EducationLevelPostMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATION_LEVEL_POST_MAPPING_EDUCATIONLEVELPOSTMAPPINGID_GENERATOR",  allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATION_LEVEL_POST_MAPPING_EDUCATIONLEVELPOSTMAPPINGID_GENERATOR")
	@Column(name="education_level_post_mapping_id")
	private Integer educationLevelPostMappingId;

	@Column(name="is_qualifying_degree")
	private Boolean isQualifyingDegree;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="education_level_id")
	private EducationLevel educationLevel;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public EducationLevelPostMapping() {
	}

	public Integer getEducationLevelPostMappingId() {
		return this.educationLevelPostMappingId;
	}

	public void setEducationLevelPostMappingId(Integer educationLevelPostMappingId) {
		this.educationLevelPostMappingId = educationLevelPostMappingId;
	}

	public Boolean getIsQualifyingDegree() {
		return this.isQualifyingDegree;
	}

	public void setIsQualifyingDegree(Boolean isQualifyingDegree) {
		this.isQualifyingDegree = isQualifyingDegree;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public IcgPost getIcgPost() {
		return this.icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

}