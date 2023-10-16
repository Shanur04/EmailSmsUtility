package gov.cdac.icgOfficerPojo;
import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the status_master database table.
 * 
 */
@Entity
@Table(name="status_master")
@NamedQuery(name="StatusMaster.findAll", query="SELECT s FROM StatusMaster s")
@JsonIgnoreProperties({"applicantMalpractiseDetails"})
public class StatusMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUS_MASTER_STATUSID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATUS_MASTER_STATUSID_GENERATOR")
	@Column(name="status_id")
	private Integer statusId;

	@Column(name="status_description")
	private String statusDescription;

	//bi-directional many-to-one association to ApplicantMalpractiseDetail
	@OneToMany(mappedBy="statusMaster")
	private List<ApplicantMalpractiseDetail> applicantMalpractiseDetails;

	public StatusMaster() {
	}

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public List<ApplicantMalpractiseDetail> getApplicantMalpractiseDetails() {
		return this.applicantMalpractiseDetails;
	}

	public void setApplicantMalpractiseDetails(List<ApplicantMalpractiseDetail> applicantMalpractiseDetails) {
		this.applicantMalpractiseDetails = applicantMalpractiseDetails;
	}

	public ApplicantMalpractiseDetail addApplicantMalpractiseDetail(ApplicantMalpractiseDetail applicantMalpractiseDetail) {
		getApplicantMalpractiseDetails().add(applicantMalpractiseDetail);
		applicantMalpractiseDetail.setStatusMaster(this);

		return applicantMalpractiseDetail;
	}

	public ApplicantMalpractiseDetail removeApplicantMalpractiseDetail(ApplicantMalpractiseDetail applicantMalpractiseDetail) {
		getApplicantMalpractiseDetails().remove(applicantMalpractiseDetail);
		applicantMalpractiseDetail.setStatusMaster(null);

		return applicantMalpractiseDetail;
	}

}