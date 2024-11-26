package gov.cdac.afcatPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="exam_type_master")
@NamedQuery(name="AfcatExamTypeMaster.findAll", query="SELECT e FROM AfcatExamTypeMaster e")
public class AfcatExamTypeMaster {

	 @Id
	 @SequenceGenerator(name = "EXAMTYPEID_GENERATOR", allocationSize = 1)
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAMTYPEID_GENERATOR")
	 @Column(name = "exam_type_master_id")
	private Integer examTypeMasterId;
	 
	 @Column(name = "exam_type")
	 private String examType;
	 
	 @Column(name = "status")
	 private Boolean status;

	public Integer getExamTypeMasterId() {
		return examTypeMasterId;
	}

	public void setExamTypeMasterId(Integer examTypeMasterId) {
		this.examTypeMasterId = examTypeMasterId;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	 
}
