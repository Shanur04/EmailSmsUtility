package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the education_level database table.
 * 
 */
@Entity
@Table(name="education_level")
@NamedQuery(name="EducationLevel.findAll", query="SELECT e FROM EducationLevel e")
@JsonIgnoreProperties({"qualificationDetails","qualificationMasters","educationLevelLevelCategoryMappings","degreeMasters","icgPosts","educationLevelPostMappings"})
public class EducationLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EDUCATIONLEVELID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EDUCATIONLEVELID_GENERATOR")
	@Column(name="education_level_id")
	private Integer educationLevelId;

	@Column(name="education_level_alias")
	private String educationLevelAlias;

	@Column(name="education_level_name")
	private String educationLevelName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to QualificationDetail
	@OneToMany(mappedBy="educationLevel")
	private List<QualificationDetail> qualificationDetails;

	//bi-directional many-to-one association to QualificationMaster
	@OneToMany(mappedBy="educationLevel")
	private List<QualificationMaster> qualificationMasters;

	//bi-directional many-to-one association to DegreeMaster
	@OneToMany(mappedBy="educationLevel")
	private List<DegreeMaster> degreeMasters;

	//bi-directional many-to-one association to IcgPost
	@OneToMany(mappedBy="educationLevel")
	private List<IcgPost> icgPosts;
	
	//bi-directional many-to-one association to EducationLevelLevelCategoryMapping
	@OneToMany(mappedBy="educationLevel")
	private List<EducationLevelLevelCategoryMapping> educationLevelLevelCategoryMappings;
		
	//bi-directional many-to-one association to EducationLevelPostMapping
	@OneToMany(mappedBy="educationLevel")
	private List<EducationLevelPostMapping> educationLevelPostMappings;

		
	public EducationLevel() {
	}
	

	public EducationLevel(Integer educationLevelId) {
		super();
		this.educationLevelId = educationLevelId;
	}



	public Integer getEducationLevelId() {
		return this.educationLevelId;
	}

	public void setEducationLevelId(Integer educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public String getEducationLevelAlias() {
		return this.educationLevelAlias;
	}

	public void setEducationLevelAlias(String educationLevelAlias) {
		this.educationLevelAlias = educationLevelAlias;
	}

	public String getEducationLevelName() {
		return this.educationLevelName;
	}

	public void setEducationLevelName(String educationLevelName) {
		this.educationLevelName = educationLevelName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<QualificationDetail> getQualificationDetails() {
		return this.qualificationDetails;
	}

	public void setQualificationDetails(List<QualificationDetail> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}

	public QualificationDetail addQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().add(qualificationDetail);
		qualificationDetail.setEducationLevel(this);

		return qualificationDetail;
	}

	public QualificationDetail removeQualificationDetail(QualificationDetail qualificationDetail) {
		getQualificationDetails().remove(qualificationDetail);
		qualificationDetail.setEducationLevel(null);

		return qualificationDetail;
	}

	public List<QualificationMaster> getQualificationMasters() {
		return this.qualificationMasters;
	}

	public void setQualificationMasters(List<QualificationMaster> qualificationMasters) {
		this.qualificationMasters = qualificationMasters;
	}

	public QualificationMaster addQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().add(qualificationMaster);
		qualificationMaster.setEducationLevel(this);

		return qualificationMaster;
	}

	public QualificationMaster removeQualificationMaster(QualificationMaster qualificationMaster) {
		getQualificationMasters().remove(qualificationMaster);
		qualificationMaster.setEducationLevel(null);

		return qualificationMaster;
	}


	public List<EducationLevelLevelCategoryMapping> getEducationLevelLevelCategoryMappings() {
		return educationLevelLevelCategoryMappings;
	}



	public void setEducationLevelLevelCategoryMappings(
			List<EducationLevelLevelCategoryMapping> educationLevelLevelCategoryMappings) {
		this.educationLevelLevelCategoryMappings = educationLevelLevelCategoryMappings;
	}


	public List<DegreeMaster> getDegreeMasters() {
		return degreeMasters;
	}


	public void setDegreeMasters(List<DegreeMaster> degreeMasters) {
		this.degreeMasters = degreeMasters;
	}


	public List<IcgPost> getIcgPosts() {
		return icgPosts;
	}


	public void setIcgPosts(List<IcgPost> icgPosts) {
		this.icgPosts = icgPosts;
	}


	public List<EducationLevelPostMapping> getEducationLevelPostMappings() {
		return educationLevelPostMappings;
	}


	public void setEducationLevelPostMappings(List<EducationLevelPostMapping> educationLevelPostMappings) {
		this.educationLevelPostMappings = educationLevelPostMappings;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "EducationLevel [educationLevelId=" + educationLevelId + ", educationLevelAlias=" + educationLevelAlias
				+ ", educationLevelName=" + educationLevelName + ", recordTracking=" + recordTracking
				+ ", qualificationDetails=" + qualificationDetails + ", qualificationMasters=" + qualificationMasters
				+ ", degreeMasters=" + degreeMasters + ", icgPosts=" + icgPosts
				+ ", educationLevelLevelCategoryMappings=" + educationLevelLevelCategoryMappings
				+ ", educationLevelPostMappings=" + educationLevelPostMappings + "]";
	}




}