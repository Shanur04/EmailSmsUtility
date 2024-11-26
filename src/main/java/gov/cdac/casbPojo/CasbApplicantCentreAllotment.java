package gov.cdac.casbPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applicant_center_allotment")
public class CasbApplicantCentreAllotment {

    @Id
    @Column(name = "applicant_center_allotment_id")
    private Long applicantCentreAllotmentId;

    // bi-directional many-to-one association to ApplicantCredential
    @ManyToOne
    @JoinColumn(name = "applicant_cred_id")
    private CasbApplicantCredential casbApplicantCredential;

    public Long getApplicantCentreAllotmentId() {
	return applicantCentreAllotmentId;
    }

    public void setApplicantCentreAllotmentId(Long applicantCentreAllotmentId) {
	this.applicantCentreAllotmentId = applicantCentreAllotmentId;
    }

    public CasbApplicantCredential getCasbApplicantCredential() {
	return casbApplicantCredential;
    }

    public void setCasbApplicantCredential(CasbApplicantCredential casbApplicantCredential) {
	this.casbApplicantCredential = casbApplicantCredential;
    }

}
