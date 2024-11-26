package gov.cdac.icgOfficerPojo;


import java.io.Serializable;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the icg_post database table.
 * 
 */
@Entity
@Table(name="icg_post")
@NamedQuery(name="IcgPost.findAll", query="SELECT i FROM IcgPost i")
@JsonIgnoreProperties({"allocationCriteriaMasters","batchPostMappings","castewisePostwiseDobrangeMappings","educationLevelCategoryPostMappings",
	"educationLevelPostMappings","examCentreMappings","examPapers","examPaperKeys","examSlotSectionMappings","examSlotwiseReportingtimes","personalDetails"})
public class IcgPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ICG_POST_POSTID_GENERATOR" ,allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ICG_POST_POSTID_GENERATOR")
	@Column(name="post_id")
	private Integer postId;

	@Column(name="appointment_type")
	private String appointmentType;

	@Column(name="is_cgcat_applicable")
	private Boolean isCgcatApplicable;

	@Column(name="post_code")
	private String postCode;

	@Column(name="post_name")
	private String postName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="written_test_section_id")
	private Integer writtenTestSectionId;
	
	@Column(name="is_having_subpost")
	private Boolean isHavingSubpost;
	
	@Column(name="subpost_of_post")
	private Integer subpostOfPost;
	
	@Column(name="eligibility_criteria")
	private String eligibilityCriteria;

	//bi-directional many-to-one association to AllocationCriteriaMaster
	@OneToMany(mappedBy="icgPost")
	private List<AllocationCriteriaMaster> allocationCriteriaMasters;

	//bi-directional many-to-one association to BatchPostMapping
	@OneToMany(mappedBy="icgPost")
	private List<BatchPostMapping> batchPostMappings;

	//bi-directional many-to-one association to CastewisePostwiseDobrangeMapping
	@OneToMany(mappedBy="icgPost")
	private List<CastewisePostwiseDobrangeMapping> castewisePostwiseDobrangeMappings;


	//bi-directional many-to-one association to EducationLevelCategoryPostMapping
	@OneToMany(mappedBy="icgPost")
	private List<EducationLevelCategoryPostMapping> educationLevelCategoryPostMappings;

	//bi-directional many-to-one association to EducationLevelPostMapping
	@OneToMany(mappedBy="icgPost")
	private List<EducationLevelPostMapping> educationLevelPostMappings;

	//bi-directional many-to-one association to ExamCentreMapping
	@OneToMany(mappedBy="icgPost")
	private List<ExamCentreMapping> examCentreMappings;

	//bi-directional many-to-one association to ExamPaper
	@OneToMany(mappedBy="icgPost")
	private List<ExamPaper> examPapers;

	//bi-directional many-to-one association to ExamPaperKey
	@OneToMany(mappedBy="icgPost")
	private List<ExamPaperKey> examPaperKeys;

	//bi-directional many-to-one association to ExamSlotSectionMapping
	@OneToMany(mappedBy="icgPost")
	private List<ExamSlotSectionMapping> examSlotSectionMappings;

	//bi-directional many-to-one association to ExamSlotwiseReportingtime
	@OneToMany(mappedBy="icgPost")
	private List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes;

	//bi-directional many-to-one association to EducationLevel
	@ManyToOne
	@JoinColumn(name="qualifying_education_level_id")
	private EducationLevel educationLevel;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="icgPost")
	private List<PersonalDetail> personalDetails;

	public IcgPost() {
	}
	
	public Boolean getIsHavingSubpost() {
		return isHavingSubpost;
	}

	public void setIsHavingSubpost(Boolean isHavingSubpost) {
		this.isHavingSubpost = isHavingSubpost;
	}

	public Integer getSubpostOfPost() {
		return subpostOfPost;
	}

	public void setSubpostOfPost(Integer subpostOfPost) {
		this.subpostOfPost = subpostOfPost;
	}

	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}

	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}

	public Integer getPostId() {
		return this.postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getAppointmentType() {
		return this.appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Boolean getIsCgcatApplicable() {
		return this.isCgcatApplicable;
	}

	public void setIsCgcatApplicable(Boolean isCgcatApplicable) {
		this.isCgcatApplicable = isCgcatApplicable;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getWrittenTestSectionId() {
		return this.writtenTestSectionId;
	}

	public void setWrittenTestSectionId(Integer writtenTestSectionId) {
		this.writtenTestSectionId = writtenTestSectionId;
	}

	public List<AllocationCriteriaMaster> getAllocationCriteriaMasters() {
		return this.allocationCriteriaMasters;
	}

	public void setAllocationCriteriaMasters(List<AllocationCriteriaMaster> allocationCriteriaMasters) {
		this.allocationCriteriaMasters = allocationCriteriaMasters;
	}

	public AllocationCriteriaMaster addAllocationCriteriaMaster(AllocationCriteriaMaster allocationCriteriaMaster) {
		getAllocationCriteriaMasters().add(allocationCriteriaMaster);
		allocationCriteriaMaster.setIcgPost(this);

		return allocationCriteriaMaster;
	}

	public AllocationCriteriaMaster removeAllocationCriteriaMaster(AllocationCriteriaMaster allocationCriteriaMaster) {
		getAllocationCriteriaMasters().remove(allocationCriteriaMaster);
		allocationCriteriaMaster.setIcgPost(null);

		return allocationCriteriaMaster;
	}

	public List<BatchPostMapping> getBatchPostMappings() {
		return this.batchPostMappings;
	}

	public void setBatchPostMappings(List<BatchPostMapping> batchPostMappings) {
		this.batchPostMappings = batchPostMappings;
	}

	public BatchPostMapping addBatchPostMapping(BatchPostMapping batchPostMapping) {
		getBatchPostMappings().add(batchPostMapping);
		batchPostMapping.setIcgPost(this);

		return batchPostMapping;
	}

	public BatchPostMapping removeBatchPostMapping(BatchPostMapping batchPostMapping) {
		getBatchPostMappings().remove(batchPostMapping);
		batchPostMapping.setIcgPost(null);

		return batchPostMapping;
	}

	public List<CastewisePostwiseDobrangeMapping> getCastewisePostwiseDobrangeMappings() {
		return this.castewisePostwiseDobrangeMappings;
	}

	public void setCastewisePostwiseDobrangeMappings(List<CastewisePostwiseDobrangeMapping> castewisePostwiseDobrangeMappings) {
		this.castewisePostwiseDobrangeMappings = castewisePostwiseDobrangeMappings;
	}

	public CastewisePostwiseDobrangeMapping addCastewisePostwiseDobrangeMapping(CastewisePostwiseDobrangeMapping castewisePostwiseDobrangeMapping) {
		getCastewisePostwiseDobrangeMappings().add(castewisePostwiseDobrangeMapping);
		castewisePostwiseDobrangeMapping.setIcgPost(this);

		return castewisePostwiseDobrangeMapping;
	}

	public CastewisePostwiseDobrangeMapping removeCastewisePostwiseDobrangeMapping(CastewisePostwiseDobrangeMapping castewisePostwiseDobrangeMapping) {
		getCastewisePostwiseDobrangeMappings().remove(castewisePostwiseDobrangeMapping);
		castewisePostwiseDobrangeMapping.setIcgPost(null);

		return castewisePostwiseDobrangeMapping;
	}


	public List<EducationLevelCategoryPostMapping> getEducationLevelCategoryPostMappings() {
		return this.educationLevelCategoryPostMappings;
	}

	public void setEducationLevelCategoryPostMappings(List<EducationLevelCategoryPostMapping> educationLevelCategoryPostMappings) {
		this.educationLevelCategoryPostMappings = educationLevelCategoryPostMappings;
	}

	public EducationLevelCategoryPostMapping addEducationLevelCategoryPostMapping(EducationLevelCategoryPostMapping educationLevelCategoryPostMapping) {
		getEducationLevelCategoryPostMappings().add(educationLevelCategoryPostMapping);
		educationLevelCategoryPostMapping.setIcgPost(this);

		return educationLevelCategoryPostMapping;
	}

	public EducationLevelCategoryPostMapping removeEducationLevelCategoryPostMapping(EducationLevelCategoryPostMapping educationLevelCategoryPostMapping) {
		getEducationLevelCategoryPostMappings().remove(educationLevelCategoryPostMapping);
		educationLevelCategoryPostMapping.setIcgPost(null);

		return educationLevelCategoryPostMapping;
	}

	public List<EducationLevelPostMapping> getEducationLevelPostMappings() {
		return this.educationLevelPostMappings;
	}

	public void setEducationLevelPostMappings(List<EducationLevelPostMapping> educationLevelPostMappings) {
		this.educationLevelPostMappings = educationLevelPostMappings;
	}

	public EducationLevelPostMapping addEducationLevelPostMapping(EducationLevelPostMapping educationLevelPostMapping) {
		getEducationLevelPostMappings().add(educationLevelPostMapping);
		educationLevelPostMapping.setIcgPost(this);

		return educationLevelPostMapping;
	}

	public EducationLevelPostMapping removeEducationLevelPostMapping(EducationLevelPostMapping educationLevelPostMapping) {
		getEducationLevelPostMappings().remove(educationLevelPostMapping);
		educationLevelPostMapping.setIcgPost(null);

		return educationLevelPostMapping;
	}

	public List<ExamCentreMapping> getExamCentreMappings() {
		return this.examCentreMappings;
	}

	public void setExamCentreMappings(List<ExamCentreMapping> examCentreMappings) {
		this.examCentreMappings = examCentreMappings;
	}

	public ExamCentreMapping addExamCentreMapping(ExamCentreMapping examCentreMapping) {
		getExamCentreMappings().add(examCentreMapping);
		examCentreMapping.setIcgPost(this);

		return examCentreMapping;
	}

	public ExamCentreMapping removeExamCentreMapping(ExamCentreMapping examCentreMapping) {
		getExamCentreMappings().remove(examCentreMapping);
		examCentreMapping.setIcgPost(null);

		return examCentreMapping;
	}

	public List<ExamPaper> getExamPapers() {
		return this.examPapers;
	}

	public void setExamPapers(List<ExamPaper> examPapers) {
		this.examPapers = examPapers;
	}

	public ExamPaper addExamPaper(ExamPaper examPaper) {
		getExamPapers().add(examPaper);
		examPaper.setIcgPost(this);

		return examPaper;
	}

	public ExamPaper removeExamPaper(ExamPaper examPaper) {
		getExamPapers().remove(examPaper);
		examPaper.setIcgPost(null);

		return examPaper;
	}

	public List<ExamPaperKey> getExamPaperKeys() {
		return this.examPaperKeys;
	}

	public void setExamPaperKeys(List<ExamPaperKey> examPaperKeys) {
		this.examPaperKeys = examPaperKeys;
	}

	public ExamPaperKey addExamPaperKey(ExamPaperKey examPaperKey) {
		getExamPaperKeys().add(examPaperKey);
		examPaperKey.setIcgPost(this);

		return examPaperKey;
	}

	public ExamPaperKey removeExamPaperKey(ExamPaperKey examPaperKey) {
		getExamPaperKeys().remove(examPaperKey);
		examPaperKey.setIcgPost(null);

		return examPaperKey;
	}

	public List<ExamSlotSectionMapping> getExamSlotSectionMappings() {
		return this.examSlotSectionMappings;
	}

	public void setExamSlotSectionMappings(List<ExamSlotSectionMapping> examSlotSectionMappings) {
		this.examSlotSectionMappings = examSlotSectionMappings;
	}

	public ExamSlotSectionMapping addExamSlotSectionMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().add(examSlotSectionMapping);
		examSlotSectionMapping.setIcgPost(this);

		return examSlotSectionMapping;
	}

	public ExamSlotSectionMapping removeExamSlotSectionMapping(ExamSlotSectionMapping examSlotSectionMapping) {
		getExamSlotSectionMappings().remove(examSlotSectionMapping);
		examSlotSectionMapping.setIcgPost(null);

		return examSlotSectionMapping;
	}

	public List<ExamSlotwiseReportingtime> getExamSlotwiseReportingtimes() {
		return this.examSlotwiseReportingtimes;
	}

	public void setExamSlotwiseReportingtimes(List<ExamSlotwiseReportingtime> examSlotwiseReportingtimes) {
		this.examSlotwiseReportingtimes = examSlotwiseReportingtimes;
	}

	public ExamSlotwiseReportingtime addExamSlotwiseReportingtime(ExamSlotwiseReportingtime examSlotwiseReportingtime) {
		getExamSlotwiseReportingtimes().add(examSlotwiseReportingtime);
		examSlotwiseReportingtime.setIcgPost(this);

		return examSlotwiseReportingtime;
	}

	public ExamSlotwiseReportingtime removeExamSlotwiseReportingtime(ExamSlotwiseReportingtime examSlotwiseReportingtime) {
		getExamSlotwiseReportingtimes().remove(examSlotwiseReportingtime);
		examSlotwiseReportingtime.setIcgPost(null);

		return examSlotwiseReportingtime;
	}

	public EducationLevel getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public List<PersonalDetail> getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setIcgPost(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setIcgPost(null);

		return personalDetail;
	}

}