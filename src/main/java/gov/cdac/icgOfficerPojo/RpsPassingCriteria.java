package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_passing_criteria database table.
 * 
 */
@Entity
@Table(name="rps_passing_criteria")
@NamedQuery(name="RpsPassingCriteria.findAll", query="SELECT r FROM RpsPassingCriteria r")
public class RpsPassingCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="max_marks")
	private float maxMarks;

	@Column(name="passed_count_ews")
	private Long passedCountEws;

	@Column(name="passed_count_gen")
	private Long passedCountGen;

	@Column(name="passed_count_genewsobc")
	private Long passedCountGenewsobc;

	@Column(name="passed_count_obc")
	private Long passedCountObc;

	@Column(name="passed_count_sc")
	private Long passedCountSc;

	@Column(name="passed_count_scst")
	private Long passedCountScst;

	@Column(name="passed_count_st")
	private Long passedCountSt;

	@Column(name="passing_marks_genewsobc")
	private float passingMarksGenewsobc;

	@Column(name="passing_marks_scst")
	private float passingMarksScst;

	@Column(name="total_passed_count")
	private Long totalPassedCount;

	//bi-directional many-to-one association to RpsExamSection
	@ManyToOne
	@JoinColumn(name="fk_exam_section_id")
	private RpsExamSection rpsExamSection;

	//bi-directional many-to-one association to RpsExamination
	@ManyToOne
	@JoinColumn(name="fk_exam_code")
	private RpsExamination rpsExamination;

	public RpsPassingCriteria() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMaxMarks() {
		return this.maxMarks;
	}

	public void setMaxMarks(float maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Long getPassedCountEws() {
		return this.passedCountEws;
	}

	public void setPassedCountEws(Long passedCountEws) {
		this.passedCountEws = passedCountEws;
	}

	public Long getPassedCountGen() {
		return this.passedCountGen;
	}

	public void setPassedCountGen(Long passedCountGen) {
		this.passedCountGen = passedCountGen;
	}

	public Long getPassedCountGenewsobc() {
		return this.passedCountGenewsobc;
	}

	public void setPassedCountGenewsobc(Long passedCountGenewsobc) {
		this.passedCountGenewsobc = passedCountGenewsobc;
	}

	public Long getPassedCountObc() {
		return this.passedCountObc;
	}

	public void setPassedCountObc(Long passedCountObc) {
		this.passedCountObc = passedCountObc;
	}

	public Long getPassedCountSc() {
		return this.passedCountSc;
	}

	public void setPassedCountSc(Long passedCountSc) {
		this.passedCountSc = passedCountSc;
	}

	public Long getPassedCountScst() {
		return this.passedCountScst;
	}

	public void setPassedCountScst(Long passedCountScst) {
		this.passedCountScst = passedCountScst;
	}

	public Long getPassedCountSt() {
		return this.passedCountSt;
	}

	public void setPassedCountSt(Long passedCountSt) {
		this.passedCountSt = passedCountSt;
	}

	public float getPassingMarksGenewsobc() {
		return this.passingMarksGenewsobc;
	}

	public void setPassingMarksGenewsobc(float passingMarksGenewsobc) {
		this.passingMarksGenewsobc = passingMarksGenewsobc;
	}

	public float getPassingMarksScst() {
		return this.passingMarksScst;
	}

	public void setPassingMarksScst(float passingMarksScst) {
		this.passingMarksScst = passingMarksScst;
	}

	public Long getTotalPassedCount() {
		return this.totalPassedCount;
	}

	public void setTotalPassedCount(Long totalPassedCount) {
		this.totalPassedCount = totalPassedCount;
	}

	public RpsExamSection getRpsExamSection() {
		return this.rpsExamSection;
	}

	public void setRpsExamSection(RpsExamSection rpsExamSection) {
		this.rpsExamSection = rpsExamSection;
	}

	public RpsExamination getRpsExamination() {
		return this.rpsExamination;
	}

	public void setRpsExamination(RpsExamination rpsExamination) {
		this.rpsExamination = rpsExamination;
	}

}