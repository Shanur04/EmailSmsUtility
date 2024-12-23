package gov.cdac.afcatPojo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The persistent class for the personal_details database table.
 * 
 */
@Entity
@Table(name = "afcat_personal_details")
public class AfcatPersonalDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "personal_details_id")
    private Long personalDetailsId;

    public AfcatPersonalDetail() {
    }

    public Long getPersonalDetailsId() {
	return this.personalDetailsId;
    }

    public void setPersonalDetailsId(Long personalDetailsId) {
	this.personalDetailsId = personalDetailsId;
    }

}