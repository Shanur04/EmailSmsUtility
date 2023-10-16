package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the exam_type_master database table.
 * 
 */
@Entity
@Table(name="exam_type_master")
@NamedQuery(name="ExamTypeMaster.findAll", query="SELECT e FROM ExamTypeMaster e")
@JsonIgnoreProperties({"examMasters"})
public class ExamTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMTYPEMASTERID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMTYPEMASTERID_GENERATOR")
	@Column(name="exam_type_master_id")
	private Integer examTypeMasterId;

	@Column(name="exam_type")
	private String examType;

	@Column(name = "status")
	private Boolean status;

	//bi-directional many-to-one association to ExamMaster
	@OneToMany(mappedBy="examTypeMaster")
	private List<ExamMaster> examMasters;

	public ExamTypeMaster() {
	}

	public Integer getExamTypeMasterId() {
		return this.examTypeMasterId;
	}

	public void setExamTypeMasterId(Integer examTypeMasterId) {
		this.examTypeMasterId = examTypeMasterId;
	}

	public String getExamType() {
		return this.examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<ExamMaster> getExamMasters() {
		return this.examMasters;
	}

	public void setExamMasters(List<ExamMaster> examMasters) {
		this.examMasters = examMasters;
	}

	public ExamMaster addExamMaster(ExamMaster examMaster) {
		getExamMasters().add(examMaster);
		examMaster.setExamTypeMaster(this);

		return examMaster;
	}

	public ExamMaster removeExamMaster(ExamMaster examMaster) {
		getExamMasters().remove(examMaster);
		examMaster.setExamTypeMaster(null);

		return examMaster;
	}

}