package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_exam_centre_master database table.
 * 
 */
@Entity
@Table(name="rps_exam_centre_master")
@NamedQuery(name="RpsExamCentreMaster.findAll", query="SELECT r FROM RpsExamCentreMaster r")
public class RpsExamCentreMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=255)
	private String code;

	@Column(length=255)
	private String city;

	@Column(nullable=false, length=255)
	private String name;

	@Column(length=255)
	private String state;

	//bi-directional many-to-one association to RpsExamCentre
	@OneToMany(mappedBy="rpsExamCentreMaster")
	private List<RpsExamCentre> rpsExamCentres;

	//bi-directional many-to-many association to RpsRegionHeadMapping
	@ManyToMany(mappedBy="rpsExamCentreMasters")
	private List<RpsRegionHeadMapping> rpsRegionHeadMappings;

	public RpsExamCentreMaster() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<RpsExamCentre> getRpsExamCentres() {
		return this.rpsExamCentres;
	}

	public void setRpsExamCentres(List<RpsExamCentre> rpsExamCentres) {
		this.rpsExamCentres = rpsExamCentres;
	}

	public RpsExamCentre addRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().add(rpsExamCentre);
		rpsExamCentre.setRpsExamCentreMaster(this);

		return rpsExamCentre;
	}

	public RpsExamCentre removeRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().remove(rpsExamCentre);
		rpsExamCentre.setRpsExamCentreMaster(null);

		return rpsExamCentre;
	}

	public List<RpsRegionHeadMapping> getRpsRegionHeadMappings() {
		return this.rpsRegionHeadMappings;
	}

	public void setRpsRegionHeadMappings(List<RpsRegionHeadMapping> rpsRegionHeadMappings) {
		this.rpsRegionHeadMappings = rpsRegionHeadMappings;
	}

}