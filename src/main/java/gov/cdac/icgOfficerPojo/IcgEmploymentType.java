package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the icg_employment_type database table.
 * 
 */
@Entity
@Table(name="icg_employment_type")
@NamedQuery(name="IcgEmploymentType.findAll", query="SELECT i FROM IcgEmploymentType i")
@JsonIgnoreProperties({"personalDetails"})
public class IcgEmploymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ICGEMPLOYMENTTYPEID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ICGEMPLOYMENTTYPEID_GENERATOR")
	@Column(name="icg_employment_type_id")
	private Integer icgEmploymentTypeId;

	@Column(name="icg_employment_type")
	private String icgEmploymentType;
	
	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="IcgEmploymentType")
	private List<PersonalDetail> personalDetails;

	public IcgEmploymentType() {
	}

	public Integer getIcgEmploymentTypeId() {
		return this.icgEmploymentTypeId;
	}

	public void setIcgEmploymentTypeId(Integer icgEmploymentTypeId) {
		this.icgEmploymentTypeId = icgEmploymentTypeId;
	}

	public String getIcgEmploymentType() {
		return this.icgEmploymentType;
	}

	public void setIcgEmploymentType(String icgEmploymentType) {
		this.icgEmploymentType = icgEmploymentType;
	}

}