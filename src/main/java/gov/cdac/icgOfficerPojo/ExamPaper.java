package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the exam_paper database table.
 * 
 */
@Entity
@Table(name="exam_paper")
@NamedQuery(name="ExamPaper.findAll", query="SELECT e FROM ExamPaper e")
public class ExamPaper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMPAPERID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMPAPERID_GENERATOR")
	@Column(name="exam_paper_id")
	private Integer examPaperId;

	@Column(name="calculated_hash")
	private String calculatedHash;

	@Column(name="ci_list_to_view")
	private String ciListToView;

	@Column(name="exam_paper_path")
	private String examPaperPath;

	@Column(name="file_name")
	private String fileName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="uploded_hash")
	private String uplodedHash;

	//bi-directional many-to-one association to ExamSlot
	@ManyToOne
	@JoinColumn(name="exam_slot_id")
	private ExamSlot examSlot;
	
	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public ExamPaper() {
	}

	public Integer getExamPaperId() {
		return this.examPaperId;
	}

	public void setExamPaperId(Integer examPaperId) {
		this.examPaperId = examPaperId;
	}

	public String getCalculatedHash() {
		return this.calculatedHash;
	}

	public void setCalculatedHash(String calculatedHash) {
		this.calculatedHash = calculatedHash;
	}

	public String getCiListToView() {
		return this.ciListToView;
	}

	public void setCiListToView(String ciListToView) {
		this.ciListToView = ciListToView;
	}

	public String getExamPaperPath() {
		return this.examPaperPath;
	}

	public void setExamPaperPath(String examPaperPath) {
		this.examPaperPath = examPaperPath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getUplodedHash() {
		return this.uplodedHash;
	}

	public void setUplodedHash(String uplodedHash) {
		this.uplodedHash = uplodedHash;
	}

	public ExamSlot getExamSlot() {
		return this.examSlot;
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