package gov.cdac.emailservice.icg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="exam_type_master")
@NamedQuery(name="ExamTypeMaster.findAll", query="SELECT e FROM ExamTypeMaster e")
public class ExamTypeMaster {

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