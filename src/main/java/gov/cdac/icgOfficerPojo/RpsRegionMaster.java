package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_region_master database table.
 * 
 */
@Entity
@Table(name="rps_region_master")
@NamedQuery(name="RpsRegionMaster.findAll", query="SELECT r FROM RpsRegionMaster r")
public class RpsRegionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=255)
	private String name;

	@Column(name="region_code", nullable=false, length=255)
	private String regionCode;

	//bi-directional many-to-one association to RpsExamCentre
	@OneToMany(mappedBy="rpsRegionMaster")
	private List<RpsExamCentre> rpsExamCentres;

	//bi-directional many-to-one association to RpsRegionHeadMapping
	@OneToMany(mappedBy="rpsRegionMaster")
	private List<RpsRegionHeadMapping> rpsRegionHeadMappings;

	public RpsRegionMaster() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public List<RpsExamCentre> getRpsExamCentres() {
		return this.rpsExamCentres;
	}

	public void setRpsExamCentres(List<RpsExamCentre> rpsExamCentres) {
		this.rpsExamCentres = rpsExamCentres;
	}

	public RpsExamCentre addRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().add(rpsExamCentre);
		rpsExamCentre.setRpsRegionMaster(this);

		return rpsExamCentre;
	}

	public RpsExamCentre removeRpsExamCentre(RpsExamCentre rpsExamCentre) {
		getRpsExamCentres().remove(rpsExamCentre);
		rpsExamCentre.setRpsRegionMaster(null);

		return rpsExamCentre;
	}

	public List<RpsRegionHeadMapping> getRpsRegionHeadMappings() {
		return this.rpsRegionHeadMappings;
	}

	public void setRpsRegionHeadMappings(List<RpsRegionHeadMapping> rpsRegionHeadMappings) {
		this.rpsRegionHeadMappings = rpsRegionHeadMappings;
	}

	public RpsRegionHeadMapping addRpsRegionHeadMapping(RpsRegionHeadMapping rpsRegionHeadMapping) {
		getRpsRegionHeadMappings().add(rpsRegionHeadMapping);
		rpsRegionHeadMapping.setRpsRegionMaster(this);

		return rpsRegionHeadMapping;
	}

	public RpsRegionHeadMapping removeRpsRegionHeadMapping(RpsRegionHeadMapping rpsRegionHeadMapping) {
		getRpsRegionHeadMappings().remove(rpsRegionHeadMapping);
		rpsRegionHeadMapping.setRpsRegionMaster(null);

		return rpsRegionHeadMapping;
	}

}