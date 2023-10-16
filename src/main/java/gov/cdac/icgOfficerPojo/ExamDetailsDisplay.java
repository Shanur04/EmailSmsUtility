package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the exam_details_display database table.
 * 
 */
@Entity
@Table(name="exam_details_display")
@NamedQuery(name="ExamDetailsDisplay.findAll", query="SELECT e FROM ExamDetailsDisplay e")
@JsonIgnoreProperties({"examSlots"})
public class ExamDetailsDisplay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXAM_DETAILS_DISPLAY_EXAMDETAILSDISPLAYID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAM_DETAILS_DISPLAY_EXAMDETAILSDISPLAYID_GENERATOR")
	@Column(name="exam_details_display_id")
	private Integer examDetailsDisplayId;

	@Column(name="admit_card_download_datetime")
	private Timestamp admitCardDownloadDatetime;

	@Column(name="exam_city_display_datetime")
	private Timestamp examCityDisplayDatetime;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to ExamSlot
	@OneToMany(mappedBy="examDetailsDisplay")
	private List<ExamSlot> examSlots;

	public ExamDetailsDisplay() {
	}

	public Integer getExamDetailsDisplayId() {
		return this.examDetailsDisplayId;
	}

	public void setExamDetailsDisplayId(Integer examDetailsDisplayId) {
		this.examDetailsDisplayId = examDetailsDisplayId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public List<ExamSlot> getExamSlots() {
		return this.examSlots;
	}

	public void setExamSlots(List<ExamSlot> examSlots) {
		this.examSlots = examSlots;
	}

	public Timestamp getAdmitCardDownloadDatetime() {
		return admitCardDownloadDatetime;
	}

	public void setAdmitCardDownloadDatetime(Timestamp admitCardDownloadDatetime) {
		this.admitCardDownloadDatetime = admitCardDownloadDatetime;
	}

	public Timestamp getExamCityDisplayDatetime() {
		return examCityDisplayDatetime;
	}

	public void setExamCityDisplayDatetime(Timestamp examCityDisplayDatetime) {
		this.examCityDisplayDatetime = examCityDisplayDatetime;
	}

}