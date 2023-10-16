package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_vacancy database table.
 * 
 */
@Entity
@Table(name="rps_vacancy")
@NamedQuery(name="RpsVacancy.findAll", query="SELECT r FROM RpsVacancy r")
public class RpsVacancy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="candidate_category", length=255)
	private String candidateCategory;

	@Column(name="vacancy_count")
	private Integer vacancyCount;

	//bi-directional many-to-one association to RpsShortlistingCriteria
	@OneToMany(mappedBy="rpsVacancy")
	private List<RpsShortlistingCriteria> rpsShortlistingCriterias;

	//bi-directional many-to-one association to RpsExamCategoryMapping
	@ManyToOne
	@JoinColumn(name="fk_exam_category_mapping_id")
	private RpsExamCategoryMapping rpsExamCategoryMapping;

	public RpsVacancy() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateCategory() {
		return this.candidateCategory;
	}

	public void setCandidateCategory(String candidateCategory) {
		this.candidateCategory = candidateCategory;
	}

	public Integer getVacancyCount() {
		return this.vacancyCount;
	}

	public void setVacancyCount(Integer vacancyCount) {
		this.vacancyCount = vacancyCount;
	}

	public List<RpsShortlistingCriteria> getRpsShortlistingCriterias() {
		return this.rpsShortlistingCriterias;
	}

	public void setRpsShortlistingCriterias(List<RpsShortlistingCriteria> rpsShortlistingCriterias) {
		this.rpsShortlistingCriterias = rpsShortlistingCriterias;
	}

	public RpsShortlistingCriteria addRpsShortlistingCriteria(RpsShortlistingCriteria rpsShortlistingCriteria) {
		getRpsShortlistingCriterias().add(rpsShortlistingCriteria);
		rpsShortlistingCriteria.setRpsVacancy(this);

		return rpsShortlistingCriteria;
	}

	public RpsShortlistingCriteria removeRpsShortlistingCriteria(RpsShortlistingCriteria rpsShortlistingCriteria) {
		getRpsShortlistingCriterias().remove(rpsShortlistingCriteria);
		rpsShortlistingCriteria.setRpsVacancy(null);

		return rpsShortlistingCriteria;
	}

	public RpsExamCategoryMapping getRpsExamCategoryMapping() {
		return this.rpsExamCategoryMapping;
	}

	public void setRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		this.rpsExamCategoryMapping = rpsExamCategoryMapping;
	}

}