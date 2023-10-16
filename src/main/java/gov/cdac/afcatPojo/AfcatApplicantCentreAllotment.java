package gov.cdac.afcatPojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
