package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the exam_paper_key database table.
 * 
 */
@Entity
@Table(name="exam_paper_key")
@NamedQuery(name="ExamPaperKey.findAll", query="SELECT e FROM ExamPaperKey e")
public class ExamPaperKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMPAPERKEYID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMPAPERKEYID_GENERATOR")
	@Column(name="exam_paper_key_id")
	private Integer examPaperKeyId;

	@Column(name="ci_key")
	private String ciKey;
	
	@Column(name="icg_key")
	private String icgKey;
	
	@Column(name="ci_list_to_view")
	private String ciListToView_;
	
	@Column(name="icg_list_to_view")
	private String icgListToView_;
	

	@Column(name="is_ci_key_visible")
	private Boolean isCiKeyVisible;
	
	@Column(name="is_icg_key_visible")
	private Boolean isIcgKeyVisible;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="set_id")
	private Integer setId;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public ExamPaperKey() {
	}

	public Integer getExamPaperKeyId() {
		return this.examPaperKeyId;
	}

	public void setExamPaperKeyId(Integer examPaperKeyId) {
		this.examPaperKeyId = examPaperKeyId;
	}

	public String getCiKey() {
		return this.ciKey;
	}

	public void setCiKey(String ciKey) {
		this.ciKey = ciKey;
	}

	public Boolean getIsCiKeyVisible() {
		return this.isCiKeyVisible;
	}

	public void setIsCiKeyVisible(Boolean isCiKeyVisible) {
		this.isCiKeyVisible = isCiKeyVisible;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getSetId() {
		return this.setId;
	}

	public void setSetId(Integer setId) {
		this.setId = setId;
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