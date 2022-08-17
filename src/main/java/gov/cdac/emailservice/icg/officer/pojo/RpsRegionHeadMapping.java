package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rps_region_head_mapping database table.
 * 
 */
@Entity
@Table(name="rps_region_head_mapping")
@NamedQuery(name="RpsRegionHeadMapping.findAll", query="SELECT r FROM RpsRegionHeadMapping r")
public class RpsRegionHeadMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=1)
	private String activated;

	@Column(name="exam_code", length=255)
	private String examCode;

	//bi-directional many-to-many association to RpsExamCentreMaster
	@ManyToMany
	@JoinTable(
		name="rps_region_exam_centre_mapping"
		, joinColumns={
			@JoinColumn(name="fk_region_head_mapping_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_centre_master_code", nullable=false)
			}
		)
	private List<RpsExamCentreMaster> rpsExamCentreMasters;

	//bi-directional many-to-one association to RpsRegionMaster
	@ManyToOne
	@JoinColumn(name="fk_region_id")
	private RpsRegionMaster rpsRegionMaster;

	//bi-directional many-to-one association to RpsUser
	@ManyToOne
	@JoinColumn(name="fk_region_head_username")
	private RpsUser rpsUser;

	public RpsRegionHeadMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivated() {
		return this.activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public List<RpsExamCentreMaster> getRpsExamCentreMasters() {
		return this.rpsExamCentreMasters;
	}

	public void setRpsExamCentreMasters(List<RpsExamCentreMaster> rpsExamCentreMasters) {
		this.rpsExamCentreMasters = rpsExamCentreMasters;
	}

	public RpsRegionMaster getRpsRegionMaster() {
		return this.rpsRegionMaster;
	}

	public void setRpsRegionMaster(RpsRegionMaster rpsRegionMaster) {
		this.rpsRegionMaster = rpsRegionMaster;
	}

	public RpsUser getRpsUser() {
		return this.rpsUser;
	}

	public void setRpsUser(RpsUser rpsUser) {
		this.rpsUser = rpsUser;
	}

}