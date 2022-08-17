package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_shortlisted_exam_result database table.
 * 
 */
@Entity
@Table(name="rps_shortlisted_exam_result")
@NamedQuery(name="RpsShortlistedExamResult.findAll", query="SELECT r FROM RpsShortlistedExamResult r")
public class RpsShortlistedExamResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="candidate_category", length=255)
	private String candidateCategory;

	@Column(name="candidature_cancelled", length=1)
	private String candidatureCancelled;

	@Column(name="candidature_cancelled_reason", length=255)
	private String candidatureCancelledReason;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="marks_relaxation_availed", length=1)
	private String marksRelaxationAvailed;

	@Column(length=1)
	private String meritorious;

	@Column(name="navik_db_passed", length=1)
	private String navikDbPassed;

	@Column(name="navik_db_shortlisted", length=1)
	private String navikDbShortlisted;

	@Column(name="navik_gd_passed", length=1)
	private String navikGdPassed;

	@Column(name="navik_gd_shortlisted", length=1)
	private String navikGdShortlisted;

	@Column(name="reason_for_order", length=255)
	private String reasonForOrder;

	@Column(name="sectioni_marks", length=255)
	private String sectioniMarks;

	@Column(name="sectioni_passed", length=1)
	private String sectioniPassed;

	@Column(name="sectionii_marks", length=255)
	private String sectioniiMarks;

	@Column(name="sectionii_passed", length=1)
	private String sectioniiPassed;

	@Column(name="sectioniii_marks", length=255)
	private String sectioniiiMarks;

	@Column(name="sectioniii_passed", length=1)
	private String sectioniiiPassed;

	@Column(name="sectioniv_marks", length=255)
	private String sectionivMarks;

	@Column(name="sectioniv_passed", length=1)
	private String sectionivPassed;

	@Column(name="sectionv_marks", length=255)
	private String sectionvMarks;

	@Column(name="sectionv_passed", length=1)
	private String sectionvPassed;

	@Column(name="vacancy_shortlisted", length=255)
	private String shortlistedCategory;

	@Column(name="vacancy_shortlisted_order", length=255)
	private String shortlistedOrder;

	@Column(name="yantrik_ec_passed", length=1)
	private String yantrikEcPassed;

	@Column(name="yantrik_ec_shortlisted", length=1)
	private String yantrikEcShortlisted;

	@Column(name="yantrik_el_passed", length=1)
	private String yantrikElPassed;

	@Column(name="yantrik_el_shortlisted", length=1)
	private String yantrikElShortlisted;

	@Column(name="yantrik_ml_passed", length=1)
	private String yantrikMlPassed;

	@Column(name="yantrik_ml_shortlisted", length=1)
	private String yantrikMlShortlisted;

	//bi-directional many-to-one association to RpsCandidate
	@ManyToOne
	@JoinColumn(name="fk_hall_ticket_number", nullable=false,columnDefinition="VARCHAR(255)")
	private RpsCandidate rpsCandidate;

	//bi-directional many-to-one association to RpsShortlistedSectionResult
	@OneToMany(mappedBy="rpsShortlistedExamResult")
	private List<RpsShortlistedSectionResult> rpsShortlistedSectionResults;

	public RpsShortlistedExamResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateCategory() {
		return this.candidateCategory;
	}

	public void setCandidateCategory(String candidateCategory) {
		this.candidateCategory = candidateCategory;
	}

	public String getCandidatureCancelled() {
		return this.candidatureCancelled;
	}

	public void setCandidatureCancelled(String candidatureCancelled) {
		this.candidatureCancelled = candidatureCancelled;
	}

	public String getCandidatureCancelledReason() {
		return this.candidatureCancelledReason;
	}

	public void setCandidatureCancelledReason(String candidatureCancelledReason) {
		this.candidatureCancelledReason = candidatureCancelledReason;
	}

	public String getExamCategory() {
		return this.examCategory;
	}

	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getMarksRelaxationAvailed() {
		return this.marksRelaxationAvailed;
	}

	public void setMarksRelaxationAvailed(String marksRelaxationAvailed) {
		this.marksRelaxationAvailed = marksRelaxationAvailed;
	}

	public String getMeritorious() {
		return this.meritorious;
	}

	public void setMeritorious(String meritorious) {
		this.meritorious = meritorious;
	}

	public String getNavikDbPassed() {
		return this.navikDbPassed;
	}

	public void setNavikDbPassed(String navikDbPassed) {
		this.navikDbPassed = navikDbPassed;
	}

	public String getNavikDbShortlisted() {
		return this.navikDbShortlisted;
	}

	public void setNavikDbShortlisted(String navikDbShortlisted) {
		this.navikDbShortlisted = navikDbShortlisted;
	}

	public String getNavikGdPassed() {
		return this.navikGdPassed;
	}

	public void setNavikGdPassed(String navikGdPassed) {
		this.navikGdPassed = navikGdPassed;
	}

	public String getNavikGdShortlisted() {
		return this.navikGdShortlisted;
	}

	public void setNavikGdShortlisted(String navikGdShortlisted) {
		this.navikGdShortlisted = navikGdShortlisted;
	}

	public String getReasonForOrder() {
		return this.reasonForOrder;
	}

	public void setReasonForOrder(String reasonForOrder) {
		this.reasonForOrder = reasonForOrder;
	}

	public String getSectioniMarks() {
		return this.sectioniMarks;
	}

	public void setSectioniMarks(String sectioniMarks) {
		this.sectioniMarks = sectioniMarks;
	}

	public String getSectioniPassed() {
		return this.sectioniPassed;
	}

	public void setSectioniPassed(String sectioniPassed) {
		this.sectioniPassed = sectioniPassed;
	}

	public String getSectioniiMarks() {
		return this.sectioniiMarks;
	}

	public void setSectioniiMarks(String sectioniiMarks) {
		this.sectioniiMarks = sectioniiMarks;
	}

	public String getSectioniiPassed() {
		return this.sectioniiPassed;
	}

	public void setSectioniiPassed(String sectioniiPassed) {
		this.sectioniiPassed = sectioniiPassed;
	}

	public String getSectioniiiMarks() {
		return this.sectioniiiMarks;
	}

	public void setSectioniiiMarks(String sectioniiiMarks) {
		this.sectioniiiMarks = sectioniiiMarks;
	}

	public String getSectioniiiPassed() {
		return this.sectioniiiPassed;
	}

	public void setSectioniiiPassed(String sectioniiiPassed) {
		this.sectioniiiPassed = sectioniiiPassed;
	}

	public String getSectionivMarks() {
		return this.sectionivMarks;
	}

	public void setSectionivMarks(String sectionivMarks) {
		this.sectionivMarks = sectionivMarks;
	}

	public String getSectionivPassed() {
		return this.sectionivPassed;
	}

	public void setSectionivPassed(String sectionivPassed) {
		this.sectionivPassed = sectionivPassed;
	}

	public String getSectionvMarks() {
		return this.sectionvMarks;
	}

	public void setSectionvMarks(String sectionvMarks) {
		this.sectionvMarks = sectionvMarks;
	}

	public String getSectionvPassed() {
		return this.sectionvPassed;
	}

	public void setSectionvPassed(String sectionvPassed) {
		this.sectionvPassed = sectionvPassed;
	}

	public String getShortlistedCategory() {
		return this.shortlistedCategory;
	}

	public void setShortlistedCategory(String shortlistedCategory) {
		this.shortlistedCategory = shortlistedCategory;
	}

	public String getShortlistedOrder() {
		return this.shortlistedOrder;
	}

	public void setShortlistedOrder(String shortlistedOrder) {
		this.shortlistedOrder = shortlistedOrder;
	}

	public String getYantrikEcPassed() {
		return this.yantrikEcPassed;
	}

	public void setYantrikEcPassed(String yantrikEcPassed) {
		this.yantrikEcPassed = yantrikEcPassed;
	}

	public String getYantrikEcShortlisted() {
		return this.yantrikEcShortlisted;
	}

	public void setYantrikEcShortlisted(String yantrikEcShortlisted) {
		this.yantrikEcShortlisted = yantrikEcShortlisted;
	}

	public String getYantrikElPassed() {
		return this.yantrikElPassed;
	}

	public void setYantrikElPassed(String yantrikElPassed) {
		this.yantrikElPassed = yantrikElPassed;
	}

	public String getYantrikElShortlisted() {
		return this.yantrikElShortlisted;
	}

	public void setYantrikElShortlisted(String yantrikElShortlisted) {
		this.yantrikElShortlisted = yantrikElShortlisted;
	}

	public String getYantrikMlPassed() {
		return this.yantrikMlPassed;
	}

	public void setYantrikMlPassed(String yantrikMlPassed) {
		this.yantrikMlPassed = yantrikMlPassed;
	}

	public String getYantrikMlShortlisted() {
		return this.yantrikMlShortlisted;
	}

	public void setYantrikMlShortlisted(String yantrikMlShortlisted) {
		this.yantrikMlShortlisted = yantrikMlShortlisted;
	}

	public RpsCandidate getRpsCandidate() {
		return this.rpsCandidate;
	}

	public void setRpsCandidate(RpsCandidate rpsCandidate) {
		this.rpsCandidate = rpsCandidate;
	}

	public List<RpsShortlistedSectionResult> getRpsShortlistedSectionResults() {
		return this.rpsShortlistedSectionResults;
	}

	public void setRpsShortlistedSectionResults(List<RpsShortlistedSectionResult> rpsShortlistedSectionResults) {
		this.rpsShortlistedSectionResults = rpsShortlistedSectionResults;
	}

	public RpsShortlistedSectionResult addRpsShortlistedSectionResult(RpsShortlistedSectionResult rpsShortlistedSectionResult) {
		getRpsShortlistedSectionResults().add(rpsShortlistedSectionResult);
		rpsShortlistedSectionResult.setRpsShortlistedExamResult(this);

		return rpsShortlistedSectionResult;
	}

	public RpsShortlistedSectionResult removeRpsShortlistedSectionResult(RpsShortlistedSectionResult rpsShortlistedSectionResult) {
		getRpsShortlistedSectionResults().remove(rpsShortlistedSectionResult);
		rpsShortlistedSectionResult.setRpsShortlistedExamResult(null);

		return rpsShortlistedSectionResult;
	}

}