package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the dob_certificate_type database table.
 * 
 */
@Entity
@Table(name="dob_certificate_type")
@NamedQuery(name="DobCertificateType.findAll", query="SELECT d FROM DobCertificateType d")
@JsonIgnoreProperties({"personalDetails"})
public class DobCertificateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOBCERTIFICATETYPEID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOBCERTIFICATETYPEID_GENERATOR")
	@Column(name="dob_certificate_type_id")
	private Integer dobCertificateTypeId;

	@Column(name="dob_certificate_type")
	private String dobCertificateType;

	private Boolean status;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="dobCertificateType")
	private List<PersonalDetail> personalDetails;

	public DobCertificateType() {
	}

	public Integer getDobCertificateTypeId() {
		return this.dobCertificateTypeId;
	}

	public void setDobCertificateTypeId(Integer dobCertificateTypeId) {
		this.dobCertificateTypeId = dobCertificateTypeId;
	}

	public String getDobCertificateType() {
		return this.dobCertificateType;
	}

	public void setDobCertificateType(String dobCertificateType) {
		this.dobCertificateType = dobCertificateType;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<PersonalDetail> getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public PersonalDetail addPersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().add(personalDetail);
		personalDetail.setDobCertificateType(this);

		return personalDetail;
	}

	public PersonalDetail removePersonalDetail(PersonalDetail personalDetail) {
		getPersonalDetails().remove(personalDetail);
		personalDetail.setDobCertificateType(null);

		return personalDetail;
	}

}