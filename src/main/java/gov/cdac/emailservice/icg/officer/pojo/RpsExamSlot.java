package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rps_exam_slot database table.
 * 
 */
@Entity
@Table(name="rps_exam_slot")
@NamedQuery(name="RpsExamSlot.findAll", query="SELECT r FROM RpsExamSlot r")
public class RpsExamSlot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="biometric_data_reconciled", length=1)
	private String biometricDataReconciled;

	@Column(name="created_by", length=255)
	private String createdBy;

	@Column(name="end_time", length=255)
	private String endTime;

	@Column(name="exam_date")
	private Timestamp examDate;

	@Column(name="paper_code", length=255)
	private String paperCode;

	@Column(length=1)
	private String reconciled;

	@Column(name="result_generated", length=1)
	private String resultGenerated;

	@Column(name="slot_code", length=255)
	private String slotCode;

	@Column(name="start_time", length=255)
	private String startTime;

	@Column(name="subject_codes", length=255)
	private String subjectCodes;

	@Column(name="super_slot_id")
	private Long superSlotId;

	//bi-directional many-to-one association to RpsCandidateExamSlotMapping
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsCandidateExamSlotMapping> rpsCandidateExamSlotMappings;

	//bi-directional many-to-one association to RpsExamCentre
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsExamCentre> rpsExamCentres;

	//bi-directional many-to-one association to RpsExamCategoryMapping
	@ManyToOne
	@JoinColumn(name="fk_exam_category_mapping_id")
	private RpsExamCategoryMapping rpsExamCategoryMapping;

	//bi-directional many-to-one association to RpsQuestionDifficultyLevel
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsQuestionDifficultyLevel> rpsQuestionDifficultyLevels;

	//bi-directional many-to-one association to RpsQuestionPaperAnalysi
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsQuestionPaperAnalysi> rpsQuestionPaperAnalysis;

	//bi-directional many-to-one association to RpsUploadedAttendanceSheet
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsUploadedAttendanceSheet> rpsUploadedAttendanceSheets;

	//bi-directional many-to-one association to RpsUploadedResponseSheet
	@OneToMany(mappedBy="rpsExamSlot")
	private List<RpsUploadedResponseSheet> rpsUploadedResponseSheets;

	public RpsExamSlot() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBiometricDataReconciled() {
		return this.biometricDataReconciled;
	}

	public void setBiometricDataReconciled(String biometricDataReconciled) {
		this.biometricDataReconciled = biometricDataReconciled;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Timestamp getExamDate() {
		return this.examDate;
	}

	public void setExamDate(Timestamp examDate) {
		this.examDate = examDate;
	}

	public String getPaperCode() {
		return this.paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getReconciled() {
		return this.reconciled;
	}

	public void setReconciled(String reconciled) {
		this.reconciled = reconciled;
	}

	public String getResultGenerated() {
		return this.resultGenerated;
	}

	public void setResultGenerated(String resultGenerated) {
		this.resultGenerated = resultGenerated;
	}

	public String getSlotCode() {
		return this.slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSubjectCodes() {
		return this.subjectCodes;
	}

	public void setSubjectCodes(String subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	public Long getSuperSlotId() {
		return this.superSlotId;
	}

	public void setSuperSlotId(Long superSlotId) {
		this.superSlotId = superSlotId;
	}

	public List<RpsCandidateExamSlotMapping> getRpsCandidateExamSlotMappings() {
		return this.rpsCandidateExamSlotMappings;
	}

	public void setRpsCandidateExamSlotMappings(List<RpsCandidateExamSlotMapping> rpsCandidateExamSlotMappings) {
		this.rpsCandidateExamSlotMappings = rpsCandidateExamSlotMappings;
	}

	public RpsCandidateExamSlotMapping addRpsCandidateExamSlotMapping(RpsCandidateExamSlotMapping rpsCandidateExamSlotMapping) {
		getRpsCandidateExamSlotMappings().add(rpsCandidateExamSlotMapping);
		rpsCandidateExamSlotMapping.setRpsExamSlot(this);

		return rpsCandidateExamSlotMapping;
	}

	public RpsCandidateExamSlotMapping removeRpsCandidateExamSlotMapping(RpsCandidateExamSlotMapping rpsCandidateExamSlotMapping) {
		getRpsCandidateExamSlotMappings().remove(rpsCandidateExamSlotMapping);
		rpsCandidateExamSlotMapping.setRpsExamSlot(null);

		return rpsCandidateExamSlotMapping;
	}

	public List<RpsExamCentre> getRpsExamCentres() {
		return this.rpsExamCentres;
	}

	public void setRpsExamCentres(List<RpsExamCentre> rpsExamCentres) {
		this.rpsExamCentres = rpsExamCentres;
	}

	public RpsExamCentre addRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().add(rpsExamCentre);
		rpsExamCentre.setRpsExamSlot(this);

		return rpsExamCentre;
	}

	public RpsExamCentre removeRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().remove(rpsExamCentre);
		rpsExamCentre.setRpsExamSlot(null);

		return rpsExamCentre;
	}

	public RpsExamCategoryMapping getRpsExamCategoryMapping() {
		return this.rpsExamCategoryMapping;
	}

	public void setRpsExamCategoryMapping(RpsExamCategoryMapping rpsExamCategoryMapping) {
		this.rpsExamCategoryMapping = rpsExamCategoryMapping;
	}

	public List<RpsQuestionDifficultyLevel> getRpsQuestionDifficultyLevels() {
		return this.rpsQuestionDifficultyLevels;
	}

	public void setRpsQuestionDifficultyLevels(List<RpsQuestionDifficultyLevel> rpsQuestionDifficultyLevels) {
		this.rpsQuestionDifficultyLevels = rpsQuestionDifficultyLevels;
	}

	public RpsQuestionDifficultyLevel addRpsQuestionDifficultyLevel(RpsQuestionDifficultyLevel rpsQuestionDifficultyLevel) {
		getRpsQuestionDifficultyLevels().add(rpsQuestionDifficultyLevel);
		rpsQuestionDifficultyLevel.setRpsExamSlot(this);

		return rpsQuestionDifficultyLevel;
	}

	public RpsQuestionDifficultyLevel removeRpsQuestionDifficultyLevel(RpsQuestionDifficultyLevel rpsQuestionDifficultyLevel) {
		getRpsQuestionDifficultyLevels().remove(rpsQuestionDifficultyLevel);
		rpsQuestionDifficultyLevel.setRpsExamSlot(null);

		return rpsQuestionDifficultyLevel;
	}

	public List<RpsQuestionPaperAnalysi> getRpsQuestionPaperAnalysis() {
		return this.rpsQuestionPaperAnalysis;
	}

	public void setRpsQuestionPaperAnalysis(List<RpsQuestionPaperAnalysi> rpsQuestionPaperAnalysis) {
		this.rpsQuestionPaperAnalysis = rpsQuestionPaperAnalysis;
	}

	public RpsQuestionPaperAnalysi addRpsQuestionPaperAnalysi(RpsQuestionPaperAnalysi rpsQuestionPaperAnalysi) {
		getRpsQuestionPaperAnalysis().add(rpsQuestionPaperAnalysi);
		rpsQuestionPaperAnalysi.setRpsExamSlot(this);

		return rpsQuestionPaperAnalysi;
	}

	public RpsQuestionPaperAnalysi removeRpsQuestionPaperAnalysi(RpsQuestionPaperAnalysi rpsQuestionPaperAnalysi) {
		getRpsQuestionPaperAnalysis().remove(rpsQuestionPaperAnalysi);
		rpsQuestionPaperAnalysi.setRpsExamSlot(null);

		return rpsQuestionPaperAnalysi;
	}

	public List<RpsUploadedAttendanceSheet> getRpsUploadedAttendanceSheets() {
		return this.rpsUploadedAttendanceSheets;
	}

	public void setRpsUploadedAttendanceSheets(List<RpsUploadedAttendanceSheet> rpsUploadedAttendanceSheets) {
		this.rpsUploadedAttendanceSheets = rpsUploadedAttendanceSheets;
	}

	public RpsUploadedAttendanceSheet addRpsUploadedAttendanceSheet(RpsUploadedAttendanceSheet rpsUploadedAttendanceSheet) {
		getRpsUploadedAttendanceSheets().add(rpsUploadedAttendanceSheet);
		rpsUploadedAttendanceSheet.setRpsExamSlot(this);

		return rpsUploadedAttendanceSheet;
	}

	public RpsUploadedAttendanceSheet removeRpsUploadedAttendanceSheet(RpsUploadedAttendanceSheet rpsUploadedAttendanceSheet) {
		getRpsUploadedAttendanceSheets().remove(rpsUploadedAttendanceSheet);
		rpsUploadedAttendanceSheet.setRpsExamSlot(null);

		return rpsUploadedAttendanceSheet;
	}

	public List<RpsUploadedResponseSheet> getRpsUploadedResponseSheets() {
		return this.rpsUploadedResponseSheets;
	}

	public void setRpsUploadedResponseSheets(List<RpsUploadedResponseSheet> rpsUploadedResponseSheets) {
		this.rpsUploadedResponseSheets = rpsUploadedResponseSheets;
	}

	public RpsUploadedResponseSheet addRpsUploadedResponseSheet(RpsUploadedResponseSheet rpsUploadedResponseSheet) {
		getRpsUploadedResponseSheets().add(rpsUploadedResponseSheet);
		rpsUploadedResponseSheet.setRpsExamSlot(this);

		return rpsUploadedResponseSheet;
	}

	public RpsUploadedResponseSheet removeRpsUploadedResponseSheet(RpsUploadedResponseSheet rpsUploadedResponseSheet) {
		getRpsUploadedResponseSheets().remove(rpsUploadedResponseSheet);
		rpsUploadedResponseSheet.setRpsExamSlot(null);

		return rpsUploadedResponseSheet;
	}

}