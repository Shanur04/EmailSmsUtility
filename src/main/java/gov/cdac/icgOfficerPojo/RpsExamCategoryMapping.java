package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_exam_category_mapping database table.
 * 
 */
@Entity
@Table(name="rps_exam_category_mapping")
@NamedQuery(name="RpsExamCategoryMapping.findAll", query="SELECT r FROM RpsExamCategoryMapping r")
public class RpsExamCategoryMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="shortlisting_done", length=1)
	private String shortlistingDone;

	//bi-directional many-to-one association to RpsExamCategoryMaster
	@ManyToOne
	@JoinColumn(name="fk_exam_category_master_code")
	private RpsExamCategoryMaster rpsExamCategoryMaster;

	//bi-directional many-to-one association to RpsExamination
	@ManyToOne
	@JoinColumn(name="fk_examination_code")
	private RpsExamination rpsExamination;

	//bi-directional many-to-one association to RpsExamSlot
	@OneToMany(mappedBy="rpsExamCategoryMapping")
	private List<RpsExamSlot> rpsExamSlots;

	//bi-directional many-to-one association to RpsVacancy
	@OneToMany(mappedBy="rpsExamCategoryMapping")
	private List<RpsVacancy> rpsVacancies;

	public RpsExamCategoryMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortlistingDone() {
		return this.shortlistingDone;
	}

	public void setShortlistingDone(String shortlistingDone) {
		this.shortlistingDone = shortlistingDone;
	}

	public RpsExamCategoryMaster getRpsExamCategoryMaster() {
		return this.rpsExamCategoryMaster;
	}

	public void setRpsExamCategoryMaster(RpsExamCategoryMaster rpsExamCategoryMaster) {
		this.rpsExamCategoryMaster = rpsExamCategoryMaster;
	}

	public RpsExamination getRpsExamination() {
		return this.rpsExamination;
	}

	public void setRpsExamination(RpsExamination rpsExamination) {
		this.rpsExamination = rpsExamination;
	}

	public List<RpsExamSlot> getRpsExamSlots() {
		return this.rpsExamSlots;
	}

	public void setRpsExamSlots(List<RpsExamSlot> rpsExamSlots) {
		this.rpsExamSlots = rpsExamSlots;
	}

	public RpsExamSlot addRpsExamSlot(RpsExamSlot rpsExamSlot) {
		getRpsExamSlots().add(rpsExamSlot);
		rpsExamSlot.setRpsExamCategoryMapping(this);

		return rpsExamSlot;
	}

	public RpsExamSlot removeRpsExamSlot(RpsExamSlot rpsExamSlot) {
		getRpsExamSlots().remove(rpsExamSlot);
		rpsExamSlot.setRpsExamCategoryMapping(null);

		return rpsExamSlot;
	}

	public List<RpsVacancy> getRpsVacancies() {
		return this.rpsVacancies;
	}

	public void setRpsVacancies(List<RpsVacancy> rpsVacancies) {
		this.rpsVacancies = rpsVacancies;
	}

	public RpsVacancy addRpsVacancy(RpsVacancy rpsVacancy) {
		getRpsVacancies().add(rpsVacancy);
		rpsVacancy.setRpsExamCategoryMapping(this);

		return rpsVacancy;
	}

	public RpsVacancy removeRpsVacancy(RpsVacancy rpsVacancy) {
		getRpsVacancies().remove(rpsVacancy);
		rpsVacancy.setRpsExamCategoryMapping(null);

		return rpsVacancy;
	}

}