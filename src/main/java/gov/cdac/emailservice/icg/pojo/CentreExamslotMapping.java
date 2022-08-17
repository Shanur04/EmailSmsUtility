package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the centre_examslot_mapping database table.
 * 
 */
@Entity
@Table(name="centre_examslot_mapping")
@NamedQuery(name="CentreExamslotMapping.findAll", query="SELECT c FROM CentreExamslotMapping c")
@JsonIgnoreProperties({"examCentreMappings"})
public class CentreExamslotMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CENTREEXAMSLOTMAPPINGID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CENTREEXAMSLOTMAPPINGID_GENERATOR")
	@Column(name="centre_examslot_mapping_id")
	private Integer centreExamslotMappingId;

	@Column(name="centre_examslot_status")
	private Boolean centreExamslotStatus;

	@Column(name="centre_examslot_total_capacity")
	private Integer centreExamslotTotalCapacity;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to CentreMaster
	@ManyToOne
	@JoinColumn(name="centre_id")
	private CentreMaster centreMaster;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;

	//bi-directional many-to-one association to ExamCentreMapping
	@OneToMany(mappedBy="centreExamslotMapping")
	private List<ExamCentreMapping> examCentreMappings;

	public CentreExamslotMapping() {
	}

	public Integer getCentreExamslotMappingId() {
		return this.centreExamslotMappingId;
	}

	public void setCentreExamslotMappingId(Integer centreExamslotMappingId) {
		this.centreExamslotMappingId = centreExamslotMappingId;
	}

	public Boolean getCentreExamslotStatus() {
		return this.centreExamslotStatus;
	}

	public void setCentreExamslotStatus(Boolean centreExamslotStatus) {
		this.centreExamslotStatus = centreExamslotStatus;
	}

	public Integer getCentreExamslotTotalCapacity() {
		return this.centreExamslotTotalCapacity;
	}

	public void setCentreExamslotTotalCapacity(Integer centreExamslotTotalCapacity) {
		this.centreExamslotTotalCapacity = centreExamslotTotalCapacity;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public CentreMaster getCentreMaster() {
		return this.centreMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	public ExamSlot getExamSlot() {
		return this.examSlot;
	}

	public void setExamSlot(ExamSlot examSlot) {
		this.examSlot = examSlot;
	}

	public List<ExamCentreMapping> getExamCentreMappings() {
		return this.examCentreMappings;
	}

	public void setExamCentreMappings(List<ExamCentreMapping> examCentreMappings) {
		this.examCentreMappings = examCentreMappings;
	}

	public ExamCentreMapping addExamCentreMapping(ExamCentreMapping examCentreMapping) {
		getExamCentreMappings().add(examCentreMapping);
		examCentreMapping.setCentreExamslotMapping(this);

		return examCentreMapping;
	}

	public ExamCentreMapping removeExamCentreMapping(ExamCentreMapping examCentreMapping) {
		getExamCentreMappings().remove(examCentreMapping);
		examCentreMapping.setCentreExamslotMapping(null);

		return examCentreMapping;
	}

}