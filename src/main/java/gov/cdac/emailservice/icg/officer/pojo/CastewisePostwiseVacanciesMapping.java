package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the castewise_postwise_vacancies_mapping database table.
 * 
 */
@Entity
@Table(name="castewise_postwise_vacancies_mapping")
@NamedQuery(name="CastewisePostwiseVacanciesMapping.findAll", query="SELECT c FROM CastewisePostwiseVacanciesMapping c")
public class CastewisePostwiseVacanciesMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASTEWISEPOSTWISEVACANCIESMAPPINGID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASTEWISEPOSTWISEVACANCIESMAPPINGID_GENERATOR")
	@Column(name="castewise_postwise_vacancies_mapping_id")
	private Long castewisePostwiseVacanciesMappingId;

	@Column(name="total_vacancies")
	private Integer totalVacancies;
	
	//bi-directional many-to-one association to CasteCategoryDetail
	@ManyToOne
	@JoinColumn(name="caste_category_details_id")
	private CasteCategoryDetail casteCategoryDetail;

	//bi-directional many-to-one association to BatchPostMapping
	@ManyToOne
	@JoinColumn(name="batch_post_mapping_id")
	private BatchPostMapping batchPostMapping;
	
	public CastewisePostwiseVacanciesMapping() {
	}

	public Long getCastewisePostwiseVacanciesMappingId() {
		return this.castewisePostwiseVacanciesMappingId;
	}

	public void setCastewisePostwiseVacanciesMappingId(Long castewisePostwiseVacanciesMappingId) {
		this.castewisePostwiseVacanciesMappingId = castewisePostwiseVacanciesMappingId;
	}

	public Integer getTotalVacancies() {
		return this.totalVacancies;
	}

	public void setTotalVacancies(Integer totalVacancies) {
		this.totalVacancies = totalVacancies;
	}

	public CasteCategoryDetail getCasteCategoryDetail() {
		return casteCategoryDetail;
	}

	public void setCasteCategoryDetail(CasteCategoryDetail casteCategoryDetail) {
		this.casteCategoryDetail = casteCategoryDetail;
	}

	public BatchPostMapping getBatchPostMapping() {
		return batchPostMapping;
	}

	public void setBatchPostMapping(BatchPostMapping batchPostMapping) {
		this.batchPostMapping = batchPostMapping;
	}

	
}