package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the selection_exam_type_master database table.
 * 
 */
@Entity
@Table(name="selection_exam_type_master")
@NamedQuery(name="SelectionExamTypeMaster.findAll", query="SELECT s FROM SelectionExamTypeMaster s")
@JsonIgnoreProperties({"applicantPsbFsbSsbDetails"})
public class SelectionExamTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SELECTION_EXAM_TYPE_MASTER_SELECTIONEXAMTYPEMASTERID_GENERATOR",allocationSize = 1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SELECTION_EXAM_TYPE_MASTER_SELECTIONEXAMTYPEMASTERID_GENERATOR")
	@Column(name="selection_exam_type_master_id")
	private Integer selectionExamTypeMasterId;

	@Column(name="selection_exam_type")
	private String selectionExamType;

	@Column(name="selection_exam_type_description")
	private String selectionExamTypeDescription;

	//bi-directional many-to-one association to ApplicantPsbFsbSsbDetail
	@OneToMany(mappedBy="selectionExamTypeMaster")
	private List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails;

	public SelectionExamTypeMaster() {
	}

	public Integer getSelectionExamTypeMasterId() {
		return this.selectionExamTypeMasterId;
	}

	public void setSelectionExamTypeMasterId(Integer selectionExamTypeMasterId) {
		this.selectionExamTypeMasterId = selectionExamTypeMasterId;
	}

	public String getSelectionExamType() {
		return this.selectionExamType;
	}

	public void setSelectionExamType(String selectionExamType) {
		this.selectionExamType = selectionExamType;
	}

	public String getSelectionExamTypeDescription() {
		return this.selectionExamTypeDescription;
	}

	public void setSelectionExamTypeDescription(String selectionExamTypeDescription) {
		this.selectionExamTypeDescription = selectionExamTypeDescription;
	}

	public List<ApplicantPsbFsbSsbDetail> getApplicantPsbFsbSsbDetails() {
		return this.applicantPsbFsbSsbDetails;
	}

	public void setApplicantPsbFsbSsbDetails(List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails) {
		this.applicantPsbFsbSsbDetails = applicantPsbFsbSsbDetails;
	}

	public ApplicantPsbFsbSsbDetail addApplicantPsbFsbSsbDetail(ApplicantPsbFsbSsbDetail applicantPsbFsbSsbDetail) {
		getApplicantPsbFsbSsbDetails().add(applicantPsbFsbSsbDetail);
		applicantPsbFsbSsbDetail.setSelectionExamTypeMaster(this);

		return applicantPsbFsbSsbDetail;
	}

	public ApplicantPsbFsbSsbDetail removeApplicantPsbFsbSsbDetail(ApplicantPsbFsbSsbDetail applicantPsbFsbSsbDetail) {
		getApplicantPsbFsbSsbDetails().remove(applicantPsbFsbSsbDetail);
		applicantPsbFsbSsbDetail.setSelectionExamTypeMaster(null);

		return applicantPsbFsbSsbDetail;
	}

}