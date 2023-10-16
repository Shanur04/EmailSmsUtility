package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the exam_centre_mapping database table.
 * 
 */
@Entity
@Table(name="exam_centre_mapping")
@NamedQuery(name="ExamCentreMapping.findAll", query="SELECT e FROM ExamCentreMapping e")
@JsonIgnoreProperties({"applicantCentreAllotments"})
public class ExamCentreMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAMCENTREMAPPINGID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMCENTREMAPPINGID_GENERATOR")
	@Column(name="exam_centre_mapping_id")
	private Integer examCentreMappingId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="total_seat_allocated")
	private Integer totalSeatAllocated;

	//bi-directional many-to-one association to ApplicantCentreAllotment
	@OneToMany(mappedBy="examCentreMapping")
	private List<ApplicantCentreAllotment> applicantCentreAllotments;

	//bi-directional many-to-one association to CentreExamslotMapping
	@ManyToOne
	@JoinColumn(name="centre_examslot_mapping_id")
	private CentreExamslotMapping centreExamslotMapping;

	//bi-directional many-to-one association to IcgPost
	@ManyToOne
	@JoinColumn(name="post_id")
	private IcgPost icgPost;

	public ExamCentreMapping() {
	}

	public Integer getExamCentreMappingId() {
		return this.examCentreMappingId;
	}

	public void setExamCentreMappingId(Integer examCentreMappingId) {
		this.examCentreMappingId = examCentreMappingId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Integer getTotalSeatAllocated() {
		return this.totalSeatAllocated;
	}

	public void setTotalSeatAllocated(Integer totalSeatAllocated) {
		this.totalSeatAllocated = totalSeatAllocated;
	}

	public List<ApplicantCentreAllotment> getApplicantCentreAllotments() {
		return this.applicantCentreAllotments;
	}

	public void setApplicantCentreAllotments(List<ApplicantCentreAllotment> applicantCentreAllotments) {
		this.applicantCentreAllotments = applicantCentreAllotments;
	}

	public ApplicantCentreAllotment addApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().add(applicantCentreAllotment);
		applicantCentreAllotment.setExamCentreMapping(this);

		return applicantCentreAllotment;
	}

	public ApplicantCentreAllotment removeApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		getApplicantCentreAllotments().remove(applicantCentreAllotment);
		applicantCentreAllotment.setExamCentreMapping(null);

		return applicantCentreAllotment;
	}

	public CentreExamslotMapping getCentreExamslotMapping() {
		return this.centreExamslotMapping;
	}

	public void setCentreExamslotMapping(CentreExamslotMapping centreExamslotMapping) {
		this.centreExamslotMapping = centreExamslotMapping;
	}

	public IcgPost getIcgPost() {
		return icgPost;
	}

	public void setIcgPost(IcgPost icgPost) {
		this.icgPost = icgPost;
	}

	
}