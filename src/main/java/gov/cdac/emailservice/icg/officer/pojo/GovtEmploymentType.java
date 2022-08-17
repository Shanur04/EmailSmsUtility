package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "govt_employment_type_master")
@NamedQuery(name = "GovtEmploymentType.findAll", query = "SELECT i FROM GovtEmploymentType i")
@JsonIgnoreProperties({ "personalDetails" })
public class GovtEmploymentType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "GOVTEMPLOYMENTTYPEID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOVTEMPLOYMENTTYPEID_GENERATOR")
	@Column(name = "govt_employment_type_master_id")
	private Integer govtEmploymentTypeId;
	
	@Column(name = "govt_employment_type")
	private String govtEmploymentType;

	public GovtEmploymentType() {
	}

	// bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy = "GovtEmploymentType")
	private List<PersonalDetail> personalDetails;

	public Integer getGovtEmploymentTypeId() {
		return govtEmploymentTypeId;
	}

	public void setGovtEmploymentTypeId(Integer govtEmploymentTypeId) {
		this.govtEmploymentTypeId = govtEmploymentTypeId;
	}

	public String getGovtEmploymentType() {
		return govtEmploymentType;
	}

	public void setGovtEmploymentType(String govtEmploymentType) {
		this.govtEmploymentType = govtEmploymentType;
	}

}
