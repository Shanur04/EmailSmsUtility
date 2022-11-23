package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the applicant_centre_allotment database table.
 * 
 */
@Entity
@Table(name="applicant_centre_allotment")
@NamedQuery(name="ApplicantCentreAllotment.findAll", query="SELECT a FROM ApplicantCentreAllotment a")
@JsonIgnoreProperties({"applicantHalltickets"})
public class ApplicantCentreAllotment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_CENTRE_ALLOTMENT_APPLICANTCENTREALLOTMENTID_GENERATOR" , allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_CENTRE_ALLOTMENT_APPLICANTCENTREALLOTMENTID_GENERATOR")
	@Column(name="applicant_centre_allotment_id")
	private Long applicantCentreAllotmentId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to ExamCentreMapping
	@ManyToOne
	@JoinColumn(name="exam_centre_mapping_id")
	private ExamCentreMapping examCentreMapping;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to ApplicantHallticket
	@OneToMany(mappedBy="applicantCentreAllotment")
	private List<ApplicantHallticket> applicantHalltickets;

	public ApplicantCentreAllotment() {
	}

	public Long getApplicantCentreAllotmentId() {
		return this.applicantCentreAllotmentId;
	}

	public void setApplicantCentreAllotmentId(Long applicantCentreAllotmentId) {
		this.applicantCentreAllotmentId = applicantCentreAllotmentId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public ExamCentreMapping getExamCentreMapping() {
		return this.examCentreMapping;
	}

	public void setExamCentreMapping(ExamCentreMapping examCentreMapping) {
		this.examCentreMapping = examCentreMapping;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public List<ApplicantHallticket> getApplicantHalltickets() {
		return this.applicantHalltickets;
	}

	public void setApplicantHalltickets(List<ApplicantHallticket> applicantHalltickets) {
		this.applicantHalltickets = applicantHalltickets;
	}

	public ApplicantHallticket addApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().add(applicantHallticket);
		applicantHallticket.setApplicantCentreAllotment(this);

		return applicantHallticket;
	}

	public ApplicantHallticket removeApplicantHallticket(ApplicantHallticket applicantHallticket) {
		getApplicantHalltickets().remove(applicantHallticket);
		applicantHallticket.setApplicantCentreAllotment(null);

		return applicantHallticket;
	}

}