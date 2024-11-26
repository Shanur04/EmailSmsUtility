package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="allocation_criteria_master")
@NamedQuery(name="AllocationCriteria.findAll", query="SELECT a FROM AllocationCriteria a")
public class AllocationCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ALLOCATIONCRITERIAGENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALLOCATION_CRITERIA_GENERATOR")
	@Column(name="allocation_criteria_master_id")
	private Integer allocationCriteraId;
	
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;
	
	@ManyToOne
	@JoinColumn(name="icg_post_combination_id")
	private IcgPostCombination icgPostCombination;
	
	@Column(name="allocation_criteria_status")
	private Boolean allocationCriteriaStatus;
	
	public AllocationCriteria() {
		super();
	}
	

	public Boolean isAllocationCriteriaStatus() {
		return allocationCriteriaStatus;
	}


	public void setAllocationCriteriaStatus(Boolean allocationCriteriaStatus) {
		this.allocationCriteriaStatus = allocationCriteriaStatus;
	}


	public Integer getAllocationCriteraId() {
		return allocationCriteraId;
	}

	public void setAllocationCriteraId(Integer allocationCriteraId) {
		this.allocationCriteraId = allocationCriteraId;
	}

	public ExamSlot getExamSlot() {
		return examSlot;
	}

	public void setExamSlot(ExamSlot examSlot) {
		this.examSlot = examSlot;
	}

	public IcgPostCombination getIcgPostCombination() {
		return icgPostCombination;
	}

	public void setIcgPostCombination(IcgPostCombination icgPostCombination) {
		this.icgPostCombination = icgPostCombination;
	} 
}
