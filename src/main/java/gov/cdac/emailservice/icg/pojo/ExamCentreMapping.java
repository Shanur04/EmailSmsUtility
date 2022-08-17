package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


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
	
    // bi-directional many-to-one association to IcgPostCombination
    @ManyToOne
    @JoinColumn(name = "icg_post_combination_id")
    private IcgPostCombination icgPostCombination;

    @UpdateTimestamp
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
	
	@Transient
	private Boolean isMappingUpdated;

	public ExamCentreMapping() {
		isMappingUpdated = false;
	}
		
	public ExamCentreMapping(Integer examCentreMappingId, Integer totalSeatAllocated) {
		super();
		this.examCentreMappingId = examCentreMappingId;
		this.totalSeatAllocated = totalSeatAllocated;
		isMappingUpdated = false;
	}
	
	public Boolean getIsMappingUpdated() {
		return isMappingUpdated;
	}

	public void setIsMappingUpdated(Boolean isMappingUpdated) {
		this.isMappingUpdated = isMappingUpdated;
	}

	public ExamCentreMapping(Integer examCentreMappingId) {
		super();
		this.examCentreMappingId = examCentreMappingId;
	}

	public Integer getExamCentreMappingId() {
		return this.examCentreMappingId;
	}

	public IcgPostCombination getIcgPostCombination() {
	    return icgPostCombination;
	}

	public void setIcgPostCombination(IcgPostCombination icgPostCombination) {
	    this.icgPostCombination = icgPostCombination;
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

}