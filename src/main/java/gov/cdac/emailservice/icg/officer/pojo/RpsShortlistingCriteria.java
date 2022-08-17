package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_shortlisting_criteria database table.
 * 
 */
@Entity
@Table(name="rps_shortlisting_criteria")
@NamedQuery(name="RpsShortlistingCriteria.findAll", query="SELECT r FROM RpsShortlistingCriteria r")
public class RpsShortlistingCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	private Integer actualshortlistedcount;

	private Integer desiredshortlistedcount;

	@Column(name="multiplying_factor")
	private Integer multiplyingFactor;

	@Column(name="shortlisted_marks")
	private Float shortlistedMarks;

	//bi-directional many-to-one association to RpsVacancy
	@ManyToOne
	@JoinColumn(name="fk_vacancy_id")
	private RpsVacancy rpsVacancy;

	public RpsShortlistingCriteria() {
	}

	
	
	public RpsShortlistingCriteria(Long id, Integer actualshortlistedcount, Integer desiredshortlistedcount,
			Integer multiplyingFactor, float shortlistedMarks, RpsVacancy rpsVacancy) {
		super();
		this.id = id;
		this.actualshortlistedcount = actualshortlistedcount;
		this.desiredshortlistedcount = desiredshortlistedcount;
		this.multiplyingFactor = multiplyingFactor;
		this.shortlistedMarks = shortlistedMarks;
		this.rpsVacancy = rpsVacancy;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getActualshortlistedcount() {
		return this.actualshortlistedcount;
	}

	public void setActualshortlistedcount(Integer actualshortlistedcount) {
		this.actualshortlistedcount = actualshortlistedcount;
	}

	public Integer getDesiredshortlistedcount() {
		return this.desiredshortlistedcount;
	}

	public void setDesiredshortlistedcount(Integer desiredshortlistedcount) {
		this.desiredshortlistedcount = desiredshortlistedcount;
	}

	public Integer getMultiplyingFactor() {
		return this.multiplyingFactor;
	}

	public void setMultiplyingFactor(Integer multiplyingFactor) {
		this.multiplyingFactor = multiplyingFactor;
	}

	public Float getShortlistedMarks() {
		return this.shortlistedMarks;
	}

	public void setShortlistedMarks(Float shortlistedMarks) {
		this.shortlistedMarks = shortlistedMarks;
	}

	public RpsVacancy getRpsVacancy() {
		return this.rpsVacancy;
	}

	public void setRpsVacancy(RpsVacancy rpsVacancy) {
		this.rpsVacancy = rpsVacancy;
	}

}