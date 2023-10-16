package gov.cdac.icgPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the exam_city_subcity_mapping database table.
 * 
 */
@Entity
@Table(name="exam_city_subcity_mapping")
@NamedQuery(name="ExamCitySubcityMapping.findAll", query="SELECT e FROM ExamCitySubcityMapping e")
public class ExamCitySubcityMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exam_city_subcity_mappingid")
	private Integer examCitySubcityMappingid;

	@Column(name="exam_city_subcity_mapping_status")
	private Boolean examCitySubcityMappingStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="subcity_index")
	private Integer subcityIndex;

	//bi-directional many-to-one association to ExamCityMaster
	@ManyToOne
	@JoinColumn(name="exam_subcity_id")
	private ExamCityMaster examCityMaster1;

	//bi-directional many-to-one association to ExamCityMaster
	@ManyToOne
	@JoinColumn(name="exam_city_id")
	private ExamCityMaster examCityMaster2;

	public ExamCitySubcityMapping() {
	}

	public Integer getExamCitySubcityMappingid() {
		return this.examCitySubcityMappingid;
	}

	public void setExamCitySubcityMappingid(Integer examCitySubcityMappingid) {
		this.examCitySubcityMappingid = examCitySubcityMappingid;
	}

	public Boolean getExamCitySubcityMappingStatus() {
		return this.examCitySubcityMappingStatus;
	}

	public void setExamCitySubcityMappingStatus(Boolean examCitySubcityMappingStatus) {
		this.examCitySubcityMappingStatus = examCitySubcityMappingStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getSubcityIndex() {
		return this.subcityIndex;
	}

	public void setSubcityIndex(Integer subcityIndex) {
		this.subcityIndex = subcityIndex;
	}

	public ExamCityMaster getExamCityMaster1() {
		return this.examCityMaster1;
	}

	public void setExamCityMaster1(ExamCityMaster examCityMaster1) {
		this.examCityMaster1 = examCityMaster1;
	}

	public ExamCityMaster getExamCityMaster2() {
		return this.examCityMaster2;
	}

	public void setExamCityMaster2(ExamCityMaster examCityMaster2) {
		this.examCityMaster2 = examCityMaster2;
	}

}