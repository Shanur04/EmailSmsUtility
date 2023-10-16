package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the exam_slot_section_mapping database table.
 * 
 */
@Entity
@Table(name="exam_slot_section_mapping")
@NamedQuery(name="ExamSlotSectionMapping.findAll", query="SELECT e FROM ExamSlotSectionMapping e")
public class ExamSlotSectionMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="EXAM_SLOTSECTION_MAPPING_GENERATOR",  allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAM_SLOTSECTION_MAPPING_GENERATOR")
	@Column(name="exam_slot_section_mapping_id")
	private Integer examSlotSectionMappingId;

	@Column(name="exam_section_end_time")
	private String examSectionEndTime;

	@Column(name="exam_section_start_time")
	private String examSectionStartTime;

	@Column(name="exam_slot_section_mapping_status")
	private Boolean examSlotSectionMappingStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="section_sequence")
	private Integer sectionSequence;

	@Column(name="written_test_section_master_id")
	private Integer writtenSectionId;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public ExamSlotSectionMapping() {
	}

	public String getExamSectionEndTime() {
		return this.examSectionEndTime;
	}

	public void setExamSectionEndTime(String examSectionEndTime) {
		this.examSectionEndTime = examSectionEndTime;
	}

	public String getExamSectionStartTime() {
		return this.examSectionStartTime;
	}

	public void setExamSectionStartTime(String examSectionStartTime) {
		this.examSectionStartTime = examSectionStartTime;
	}

	public Integer getExamSlotSectionMappingId() {
		return this.examSlotSectionMappingId;
	}

	public void setExamSlotSectionMappingId(Integer examSlotSectionMappingId) {
		this.examSlotSectionMappingId = examSlotSectionMappingId;
	}

	public Boolean getExamSlotSectionMappingStatus() {
		return this.examSlotSectionMappingStatus;
	}

	public void setExamSlotSectionMappingStatus(Boolean examSlotSectionMappingStatus) {
		this.examSlotSectionMappingStatus = examSlotSectionMappingStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getSectionSequence() {
		return this.sectionSequence;
	}

	public void setSectionSequence(Integer sectionSequence) {
		this.sectionSequence = sectionSequence;
	}

	public Integer getWrittenSectionId() {
		return this.writtenSectionId;
	}

	public void setWrittenSectionId(Integer writtenSectionId) {
		this.writtenSectionId = writtenSectionId;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public ExamSlot getExamSlot() {
		return this.examSlot;
	}

	public void setExamSlot(ExamSlot examSlot) {
		this.examSlot = examSlot;
	}

	public IcgPost getIcgPost() {
		return this.icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

}