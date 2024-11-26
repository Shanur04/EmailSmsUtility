package gov.cdac.afcatPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "applicant_center_allotment")
public class AfcatApplicantCentreAllotment {
    @Id
    @Column(name = "applicant_center_allotment_id")
    private Long applicantCentreAllotmentId;

    public Long getApplicantCentreAllotmentId() {
        return applicantCentreAllotmentId;
    }

    public void setApplicantCentreAllotmentId(Long applicantCentreAllotmentId) {
        this.applicantCentreAllotmentId = applicantCentreAllotmentId;
    }
    
}
