package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the batch_post_mapping database table.
 * 
 */
@Entity
@Table(name="batch_post_mapping")
@NamedQuery(name="BatchPostMapping.findAll", query="SELECT b FROM BatchPostMapping b")
@JsonIgnoreProperties({"batchPostDegreeStreamMappings","castewisePostwiseVacanciesMappings"})
public class BatchPostMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BATCH_POST_MAPPING_BATCHPOSTMAPPINGID_GENERATOR" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BATCH_POST_MAPPING_BATCHPOSTMAPPINGID_GENERATOR")
	@Column(name="batch_post_mapping_id")
	private Integer batchPostMappingId;

	@Column(name="is_female_allowed")
	private Boolean isFemaleAllowed;

	@Column(name="is_final_yr_appearing_candidates_allowed")
	private Boolean isFinalYrAppearingCandidatesAllowed;

	@Column(name="is_male_allowed")
	private Boolean isMaleAllowed;

	//bi-directional many-to-one association to BatchPostDegreeStreamMapping
	@OneToMany(mappedBy="batchPostMapping")
	private List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings;
	
	//bi-directional many-to-one association to BatchPostDegreeStreamMapping
	@OneToMany(mappedBy="batchPostMapping")
	private List<CastewisePostwiseVacanciesMapping> castewisePostwiseVacanciesMappings;

	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;
	
	@Column(name = "is_common_vacancy_for_subpost")
	private Boolean isCommonVacancyForSubpost;
	
	@Column(name = "is_exam_combined")
	private Boolean isExamCombined;
	
	//bi-directional many-to-one association to IcgPost
//	Column(name="super_post_id_for_combine_vacancy")
	@Column(name="super_post_id_for_combine_vacancy")
	private Integer superPostIdForCombineVacancy;

	public BatchPostMapping() {
	}

	public Integer getBatchPostMappingId() {
		return this.batchPostMappingId;
	}

	public void setBatchPostMappingId(Integer batchPostMappingId) {
		this.batchPostMappingId = batchPostMappingId;
	}

	public Boolean getIsFemaleAllowed() {
		return this.isFemaleAllowed;
	}

	public void setIsFemaleAllowed(Boolean isFemaleAllowed) {
		this.isFemaleAllowed = isFemaleAllowed;
	}

	public Boolean getIsFinalYrAppearingCandidatesAllowed() {
		return this.isFinalYrAppearingCandidatesAllowed;
	}

	public void setIsFinalYrAppearingCandidatesAllowed(Boolean isFinalYrAppearingCandidatesAllowed) {
		this.isFinalYrAppearingCandidatesAllowed = isFinalYrAppearingCandidatesAllowed;
	}

	public Boolean getIsMaleAllowed() {
		return this.isMaleAllowed;
	}

	public void setIsMaleAllowed(Boolean isMaleAllowed) {
		this.isMaleAllowed = isMaleAllowed;
	}

	public List<BatchPostDegreeStreamMapping> getBatchPostDegreeStreamMappings() {
		return this.batchPostDegreeStreamMappings;
	}

	public void setBatchPostDegreeStreamMappings(List<BatchPostDegreeStreamMapping> batchPostDegreeStreamMappings) {
		this.batchPostDegreeStreamMappings = batchPostDegreeStreamMappings;
	}

	public BatchPostDegreeStreamMapping addBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().add(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setBatchPostMapping(this);

		return batchPostDegreeStreamMapping;
	}

	public BatchPostDegreeStreamMapping removeBatchPostDegreeStreamMapping(BatchPostDegreeStreamMapping batchPostDegreeStreamMapping) {
		getBatchPostDegreeStreamMappings().remove(batchPostDegreeStreamMapping);
		batchPostDegreeStreamMapping.setBatchPostMapping(null);

		return batchPostDegreeStreamMapping;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

	public IcgPost getIcgPost() {
		return this.icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	public List<CastewisePostwiseVacanciesMapping> getCastewisePostwiseVacanciesMappings() {
		return castewisePostwiseVacanciesMappings;
	}

	public void setCastewisePostwiseVacanciesMappings(
			List<CastewisePostwiseVacanciesMapping> castewisePostwiseVacanciesMappings) {
		this.castewisePostwiseVacanciesMappings = castewisePostwiseVacanciesMappings;
	}

	public Boolean getIsCommonVacancyForSubpost() {
		return isCommonVacancyForSubpost;
	}

	public void setIsCommonVacancyForSubpost(Boolean isCommonVacancyForSubpost) {
		this.isCommonVacancyForSubpost = isCommonVacancyForSubpost;
	}

	public Boolean getIsExamCombined() {
		return isExamCombined;
	}

	public void setIsExamCombined(Boolean isExamCombined) {
		this.isExamCombined = isExamCombined;
	}

	public Integer getSuperPostIdForCombineVacancy() {
		return superPostIdForCombineVacancy;
	}

	public void setSuperPostIdForCombineVacancy(Integer superPostIdForCombineVacancy) {
		this.superPostIdForCombineVacancy = superPostIdForCombineVacancy;
	}

	
	
}