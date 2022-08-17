package gov.cdac.emailservice.casb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
