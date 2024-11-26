package gov.cdac.icgOfficerPojo;

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
@NamedQuery(name="AllocationCriteriaMaster.findAll", query="SELECT a FROM AllocationCriteriaMaster a")
public class AllocationCriteriaMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ALLOCATION_CRITERIA_MASTER_ALLOCATIONCRITERIAMASTERID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALLOCATION_CRITERIA_MASTER_ALLOCATIONCRITERIAMASTERID_GENERATOR")
	@Column(name="allocation_criteria_master_id")
	private Integer allocationCriteraId;
	
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;
	
	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;
	
	@Column(name="allocation_criteria_status")
	private boolean allocationCriteriaStatus;
	
	public AllocationCriteriaMaster() {
		super();
	}
	

	public boolean isAllocationCriteriaStatus() {
		return allocationCriteriaStatus;
	}


	public void setAllocationCriteriaStatus(boolean allocationCriteriaStatus) {
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


	public IcgPost getIcgPost() {
		return icgPost;
	}


	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	
}
