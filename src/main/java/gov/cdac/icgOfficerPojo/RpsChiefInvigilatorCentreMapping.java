package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rps_chief_invigilator_centre_mapping database table.
 * 
 */
@Entity
@Table(name="rps_chief_invigilator_centre_mapping")
@NamedQuery(name="RpsChiefInvigilatorCentreMapping.findAll", query="SELECT r FROM RpsChiefInvigilatorCentreMapping r")
public class RpsChiefInvigilatorCentreMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="ci_phonenumber", length=255)
	private String ciPhonenumber;

	@Column(name="ci_username", length=255)
	private String ciUsername;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="regional_head_name", length=255)
	private String regionalHeadName;

	@Column(name="regional_head_phonenumber", length=255)
	private String regionalHeadPhonenumber;

	public RpsChiefInvigilatorCentreMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCiPhonenumber() {
		return this.ciPhonenumber;
	}

	public void setCiPhonenumber(String ciPhonenumber) {
		this.ciPhonenumber = ciPhonenumber;
	}

	public String getCiUsername() {
		return this.ciUsername;
	}

	public void setCiUsername(String ciUsername) {
		this.ciUsername = ciUsername;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getRegionalHeadName() {
		return this.regionalHeadName;
	}

	public void setRegionalHeadName(String regionalHeadName) {
		this.regionalHeadName = regionalHeadName;
	}

	public String getRegionalHeadPhonenumber() {
		return this.regionalHeadPhonenumber;
	}

	public void setRegionalHeadPhonenumber(String regionalHeadPhonenumber) {
		this.regionalHeadPhonenumber = regionalHeadPhonenumber;
	}

}