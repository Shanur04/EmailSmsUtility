package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rps_examination database table.
 * 
 */
@Entity
@Table(name="rps_examination")
@NamedQuery(name="RpsExamination.findAll", query="SELECT r FROM RpsExamination r")
public class RpsExamination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=255)
	private String code;

	@Column(length=1)
	private String activated;

	@Column(name="batch_id")
	private Long batchId;

	@Column(name="creation_date")
	private Timestamp creationDate;

	@Column(length=255)
	private String description;

	@Column(nullable=false, length=255)
	private String name;

	@Column(name="passing_criteria_applied", length=1)
	private String passingCriteriaApplied;

	@Column(length=1)
	private String reconciled;

	@Column(name="result_generated", length=1)
	private String resultGenerated;

	//bi-directional many-to-one association to RpsExamCategoryMapping
	@OneToMany(mappedBy="rpsExamination")
	private List<RpsExamCategoryMapping> rpsExamCategoryMappings;

	//bi-directional many-to-one association to RpsPassingCriteria
	@OneToMany(mappedBy="rpsExamination")
	private List<RpsPassingCriteria> rpsPassingCriterias;

	public RpsExamination() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getActivated() {
		return this.activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public Long getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassingCriteriaApplied() {
		return this.passingCriteriaApplied;
	}

	public void setPassingCriteriaApplied(String passingCriteriaApplied) {
		this.passingCriteriaApplied = passingCriteriaApplied;
	}

	public String getReconciled() {
		return this.reconciled;
	}

	public void setReconciled(String reconciled) {
		this.reconciled = reconciled;
	}

	public String getResultGenerated() {
		return this.resultGenerated;
	}

	public void setResultGenerated(String resultGenerated) {
		this.resultGenerated = resultGenerated;
	}

	public List<RpsExamCategoryMapping> getRpsExamCategoryMappings() {
		return this.rpsExamCategoryMappings;
	}

	public void setRpsExamCategoryMappings(List<RpsExamCategoryMapping> rpsExamCategoryMappings) {
		this.rpsExamCategoryMappings = rpsExamCategoryMappings;
	}

	public RpsExamCategoryMapping addRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		getRpsExamCategoryMappings().add(rpsExamCategoryMapping);
		rpsExamCategoryMapping.setRpsExamination(this);

		return rpsExamCategoryMapping;
	}

	public RpsExamCategoryMapping removeRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		getRpsExamCategoryMappings().remove(rpsExamCategoryMapping);
		rpsExamCategoryMapping.setRpsExamination(null);

		return rpsExamCategoryMapping;
	}

	public List<RpsPassingCriteria> getRpsPassingCriterias() {
		return this.rpsPassingCriterias;
	}

	public void setRpsPassingCriterias(List<RpsPassingCriteria> rpsPassingCriterias) {
		this.rpsPassingCriterias = rpsPassingCriterias;
	}

	public RpsPassingCriteria addRpsPassingCriteria(RpsPassingCriteria rpsPassingCriteria) {
		getRpsPassingCriterias().add(rpsPassingCriteria);
		rpsPassingCriteria.setRpsExamination(this);

		return rpsPassingCriteria;
	}

	public RpsPassingCriteria removeRpsPassingCriteria(RpsPassingCriteria rpsPassingCriteria) {
		getRpsPassingCriterias().remove(rpsPassingCriteria);
		rpsPassingCriteria.setRpsExamination(null);

		return rpsPassingCriteria;
	}

}