package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the candidate_employment_type database table.
 * 
 */
@Entity
@Table(name="candidate_employment_type")
@NamedQuery(name="CandidateEmploymentType.findAll", query="SELECT c FROM CandidateEmploymentType c")
@JsonIgnoreProperties({"personalDetails"})
public class CandidateEmploymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CANDIDATEEMPLOYMENTTYPEID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANDIDATEEMPLOYMENTTYPEID_GENERATOR")
	@Column(name="candidate_employment_type_id")
	private Integer candidateEmploymentTypeId;

	@Column(name="candidate_employment_type")
	private String candidateEmploymentType;
	
	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="candidateEmploymentType")
	private List<PersonalDetail> personalDetails;


	public CandidateEmploymentType() {
	}

	public Integer getCandidateEmploymentTypeId() {
		return this.candidateEmploymentTypeId;
	}

	public void setCandidateEmploymentTypeId(Integer candidateEmploymentTypeId) {
		this.candidateEmploymentTypeId = candidateEmploymentTypeId;
	}

	public String getCandidateEmploymentType() {
		return this.candidateEmploymentType;
	}

	public void setCandidateEmploymentType(String candidateEmploymentType) {
		this.candidateEmploymentType = candidateEmploymentType;
	}

}