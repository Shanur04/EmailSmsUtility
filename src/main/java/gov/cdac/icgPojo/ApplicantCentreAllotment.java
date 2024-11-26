package gov.cdac.icgPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@SequenceGenerator(name="APPLICANTCENTREALLOTMENTID_GENERATOR",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANTCENTREALLOTMENTID_GENERATOR")
	@Column(name="applicant_centre_allotment_id")
	private Long applicantCentreAllotmentId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;
	
    // bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamMaster examMaster;

	//bi-directional many-to-one association to ExamCentreMapping
	@ManyToOne
	@JoinColumn(name="exam_centre_mapping_id")
	private ExamCentreMapping examCentreMapping;

	//bi-directional many-to-one association to ApplicantHallticket
	@OneToMany(mappedBy="applicantCentreAllotment")
	private List<ApplicantHallticket> applicantHalltickets;
	
	public ApplicantCentreAllotment() {
	}
	
    public ApplicantCentreAllotment(ApplicantCredential applicantCredential,
			ExamMaster examMaster, ExamCentreMapping examCentreMapping) {
		super();
		this.recordTracking = new Timestamp(System.currentTimeMillis());
		this.applicantCredential = applicantCredential;
		this.examMaster = examMaster;
		this.examCentreMapping = examCentreMapping;
	}

	public ApplicantCentreAllotment(Long applicantCentreAllotmentId) {
	this.applicantCentreAllotmentId = applicantCentreAllotmentId;
    }
    
	public ExamMaster getExamMaster() {
        return examMaster;
    }

    public void setExamMaster(ExamMaster examMaster) {
        this.examMaster = examMaster;
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