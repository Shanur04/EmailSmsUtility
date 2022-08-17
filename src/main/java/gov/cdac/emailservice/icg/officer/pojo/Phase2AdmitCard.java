/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author boss
 */
@Entity
@Table(name = "phase2_admit_card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phase2AdmitCard.findAll", query = "SELECT p FROM Phase2AdmitCard p"),
    @NamedQuery(name = "Phase2AdmitCard.findByApplicantPhase2Regno", query = "SELECT p FROM Phase2AdmitCard p WHERE p.applicantPhase2Regno = :applicantPhase2Regno"),
    @NamedQuery(name = "Phase2AdmitCard.findByPhase2TestDate", query = "SELECT p FROM Phase2AdmitCard p WHERE p.phase2TestDate = :phase2TestDate"),
    @NamedQuery(name = "Phase2AdmitCard.findByPhase2Asc", query = "SELECT p FROM Phase2AdmitCard p WHERE p.phase2Asc = :phase2Asc"),
    @NamedQuery(name = "Phase2AdmitCard.findByChestNo", query = "SELECT p FROM Phase2AdmitCard p WHERE p.chestNo = :chestNo"),
    @NamedQuery(name = "Phase2AdmitCard.findByApplicantCredentialId", query = "SELECT p FROM Phase2AdmitCard p WHERE p.applicantCredentialId = :applicantCredentialId"),
    @NamedQuery(name = "Phase2AdmitCard.findByPersonalDetailsId", query = "SELECT p FROM Phase2AdmitCard p WHERE p.personalDetailsId = :personalDetailsId"),
    @NamedQuery(name = "Phase2AdmitCard.findByApplicantRegistrationNo", query = "SELECT p FROM Phase2AdmitCard p WHERE p.applicantRegistrationNo = :applicantRegistrationNo"),
    @NamedQuery(name = "Phase2AdmitCard.findByIsEmailed", query = "SELECT p FROM Phase2AdmitCard p WHERE p.isEmailed = :isEmailed"),
    @NamedQuery(name = "Phase2AdmitCard.findByCandidateViewed", query = "SELECT p FROM Phase2AdmitCard p WHERE p.candidateViewed = :candidateViewed"),
    @NamedQuery(name = "Phase2AdmitCard.findByPhase2AdmitCardId", query = "SELECT p FROM Phase2AdmitCard p WHERE p.phase2AdmitCardId = :phase2AdmitCardId")})
public class Phase2AdmitCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "applicant_phase2_regno")
    private String applicantPhase2Regno;
    @Basic(optional = false)
    @Column(name = "phase2_test_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phase2TestDate;
    @Basic(optional = false)
    @Column(name = "phase2_asc")
    private int phase2Asc;
    @Basic(optional = false)
    @Column(name = "chest_no")
    private int chestNo;
    @Column(name = "applicant_credential_id")
    private BigInteger applicantCredentialId;
    @Column(name = "personal_details_id")
    private BigInteger personalDetailsId;
    @Column(name = "applicant_registration_no")
    private String applicantRegistrationNo;
    @Basic(optional = false)
    @Column(name = "is_emailed")
    private boolean isEmailed;
    @Column(name = "candidate_viewed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date candidateViewed;
    @Id
    @Basic(optional = false)
    @Column(name = "phase2_admit_card_id")
    private Integer phase2AdmitCardId;

    public Phase2AdmitCard() {
    }

    public Phase2AdmitCard(Integer phase2AdmitCardId) {
        this.phase2AdmitCardId = phase2AdmitCardId;
    }

    public Phase2AdmitCard(Integer phase2AdmitCardId, String applicantPhase2Regno, Date phase2TestDate, int phase2Asc, int chestNo, boolean isEmailed) {
        this.phase2AdmitCardId = phase2AdmitCardId;
        this.applicantPhase2Regno = applicantPhase2Regno;
        this.phase2TestDate = phase2TestDate;
        this.phase2Asc = phase2Asc;
        this.chestNo = chestNo;
        this.isEmailed = isEmailed;
    }

    public String getApplicantPhase2Regno() {
        return applicantPhase2Regno;
    }

    public void setApplicantPhase2Regno(String applicantPhase2Regno) {
        this.applicantPhase2Regno = applicantPhase2Regno;
    }

    public Date getPhase2TestDate() {
        return phase2TestDate;
    }

    public void setPhase2TestDate(Date phase2TestDate) {
        this.phase2TestDate = phase2TestDate;
    }

    public int getPhase2Asc() {
        return phase2Asc;
    }

    public void setPhase2Asc(int phase2Asc) {
        this.phase2Asc = phase2Asc;
    }

    public int getChestNo() {
        return chestNo;
    }

    public void setChestNo(int chestNo) {
        this.chestNo = chestNo;
    }

    public BigInteger getApplicantCredentialId() {
        return applicantCredentialId;
    }

    public void setApplicantCredentialId(BigInteger applicantCredentialId) {
        this.applicantCredentialId = applicantCredentialId;
    }

    public BigInteger getPersonalDetailsId() {
        return personalDetailsId;
    }

    public void setPersonalDetailsId(BigInteger personalDetailsId) {
        this.personalDetailsId = personalDetailsId;
    }

    public String getApplicantRegistrationNo() {
        return applicantRegistrationNo;
    }

    public void setApplicantRegistrationNo(String applicantRegistrationNo) {
        this.applicantRegistrationNo = applicantRegistrationNo;
    }

    public boolean getIsEmailed() {
        return isEmailed;
    }

    public void setIsEmailed(boolean isEmailed) {
        this.isEmailed = isEmailed;
    }

    public Date getCandidateViewed() {
        return candidateViewed;
    }

    public void setCandidateViewed(Date candidateViewed) {
        this.candidateViewed = candidateViewed;
    }

    public Integer getPhase2AdmitCardId() {
        return phase2AdmitCardId;
    }

    public void setPhase2AdmitCardId(Integer phase2AdmitCardId) {
        this.phase2AdmitCardId = phase2AdmitCardId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phase2AdmitCardId != null ? phase2AdmitCardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phase2AdmitCard)) {
            return false;
        }
        Phase2AdmitCard other = (Phase2AdmitCard) object;
        if ((this.phase2AdmitCardId == null && other.phase2AdmitCardId != null) || (this.phase2AdmitCardId != null && !this.phase2AdmitCardId.equals(other.phase2AdmitCardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cipetjpa.Phase2AdmitCard[ phase2AdmitCardId=" + phase2AdmitCardId + " ]";
    }
    
}
