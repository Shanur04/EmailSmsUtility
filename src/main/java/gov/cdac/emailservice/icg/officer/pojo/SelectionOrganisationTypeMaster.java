package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the selection_organisation_type_master database table.
 * 
 */
@Entity
@Table(name="selection_organisation_type_master")
@NamedQuery(name="SelectionOrganisationTypeMaster.findAll", query="SELECT s FROM SelectionOrganisationTypeMaster s")
@JsonIgnoreProperties({"applicantPsbFsbSsbDetails"})
public class SelectionOrganisationTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SELECTION_ORGANISATION_TYPE_MASTER_SELECTIONORGANISATIONTYPEMASTERID_GENERATOR",allocationSize = 1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SELECTION_ORGANISATION_TYPE_MASTER_SELECTIONORGANISATIONTYPEMASTERID_GENERATOR")
	@Column(name="selection_organisation_type_master_id")
	private Integer selectionOrganisationTypeMasterId;

	@Column(name="selection_organisation")
	private String selectionOrganisation;

	//bi-directional many-to-one association to ApplicantPsbFsbSsbDetail
	@OneToMany(mappedBy="selectionOrganisationTypeMaster")
	private List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails;

	public SelectionOrganisationTypeMaster() {
	}

	public Integer getSelectionOrganisationTypeMasterId() {
		return this.selectionOrganisationTypeMasterId;
	}

	public void setSelectionOrganisationTypeMasterId(Integer selectionOrganisationTypeMasterId) {
		this.selectionOrganisationTypeMasterId = selectionOrganisationTypeMasterId;
	}

	public String getSelectionOrganisation() {
		return this.selectionOrganisation;
	}

	public void setSelectionOrganisation(String selectionOrganisation) {
		this.selectionOrganisation = selectionOrganisation;
	}

	public List<ApplicantPsbFsbSsbDetail> getApplicantPsbFsbSsbDetails() {
		return this.applicantPsbFsbSsbDetails;
	}

	public void setApplicantPsbFsbSsbDetails(List<ApplicantPsbFsbSsbDetail> applicantPsbFsbSsbDetails) {
		this.applicantPsbFsbSsbDetails = applicantPsbFsbSsbDetails;
	}

	public ApplicantPsbFsbSsbDetail addApplicantPsbFsbSsbDetail(ApplicantPsbFsbSsbDetail applicantPsbFsbSsbDetail) {
		getApplicantPsbFsbSsbDetails().add(applicantPsbFsbSsbDetail);
		applicantPsbFsbSsbDetail.setSelectionOrganisationTypeMaster(this);

		return applicantPsbFsbSsbDetail;
	}

	public ApplicantPsbFsbSsbDetail removeApplicantPsbFsbSsbDetail(ApplicantPsbFsbSsbDetail applicantPsbFsbSsbDetail) {
		getApplicantPsbFsbSsbDetails().remove(applicantPsbFsbSsbDetail);
		applicantPsbFsbSsbDetail.setSelectionOrganisationTypeMaster(null);

		return applicantPsbFsbSsbDetail;
	}

}