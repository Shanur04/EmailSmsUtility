package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rps_exam_centre database table.
 * 
 */
@Entity
@Table(name="rps_exam_centre")
@NamedQuery(name="RpsExamCentre.findAll", query="SELECT r FROM RpsExamCentre r")
public class RpsExamCentre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="attendance_marked", length=1)
	private String attendanceMarked;

	//bi-directional many-to-one association to RpsExamCentreMaster
	@ManyToOne
	@JoinColumn(name="fk_exam_centre_code")
	private RpsExamCentreMaster rpsExamCentreMaster;

	//bi-directional many-to-one association to RpsExamSlot
	@ManyToOne
	@JoinColumn(name="fk_exam_slot_id")
	private RpsExamSlot rpsExamSlot;

	//bi-directional many-to-one association to RpsRegionMaster
	@ManyToOne
	@JoinColumn(name="fk_region_master_id")
	private RpsRegionMaster rpsRegionMaster;

	public RpsExamCentre() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceMarked() {
		return this.attendanceMarked;
	}

	public void setAttendanceMarked(String attendanceMarked) {
		this.attendanceMarked = attendanceMarked;
	}

	public RpsExamCentreMaster getRpsExamCentreMaster() {
		return this.rpsExamCentreMaster;
	}

	public void setRpsExamCentreMaster(RpsExamCentreMaster rpsExamCentreMaster) {
		this.rpsExamCentreMaster = rpsExamCentreMaster;
	}

	public RpsExamSlot getRpsExamSlot() {
		return this.rpsExamSlot;
	}

	public void setRpsExamSlot(RpsExamSlot rpsExamSlot) {
		this.rpsExamSlot = rpsExamSlot;
	}

	public RpsRegionMaster getRpsRegionMaster() {
		return this.rpsRegionMaster;
	}

	public void setRpsRegionMaster(RpsRegionMaster rpsRegionMaster) {
		this.rpsRegionMaster = rpsRegionMaster;
	}

}