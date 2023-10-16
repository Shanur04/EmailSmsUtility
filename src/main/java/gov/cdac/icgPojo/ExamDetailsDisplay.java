package gov.cdac.icgPojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the exam_details_display database table.
 * 
 */
@Entity
@Table(name="exam_details_display")
@NamedQuery(name="ExamDetailsDisplay.findAll", query="SELECT e FROM ExamDetailsDisplay e")
public class ExamDetailsDisplay {
	
	@Id
	@SequenceGenerator(name="EXAMDETAILDISPLAYID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXAMDETAILDISPLAYID_GENERATOR")
	@Column(name="exam_details_display_id")
	private Integer examDetailsDisplayId;
	
	@Column(name="admit_card_download_datetime")
	private Timestamp admitCardDownloadDate;

	@Column(name="exam_city_display_datetime")
	private Timestamp examCityDisplayDate;
	
	 @UpdateTimestamp
	 @Column(name="record_tracking")
	 private Timestamp recordTracking;
	 
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	public Integer getExamDetailsDisplayId() {
		return examDetailsDisplayId;
	}

	public void setExamDetailsDisplayId(Integer examDetailsDisplayId) {
		this.examDetailsDisplayId = examDetailsDisplayId;
	}

	public Timestamp getAdmitCardDownloadDate() {
		return admitCardDownloadDate;
	}

	public void setAdmitCardDownloadDate(Timestamp admitCardDownloadDate) {
		this.admitCardDownloadDate = admitCardDownloadDate;
	}

	public Timestamp getExamCityDisplayDate() {
		return examCityDisplayDate;
	}

	public void setExamCityDisplayDate(Timestamp examCityDisplayDate) {
		this.examCityDisplayDate = examCityDisplayDate;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ExamMaster getExamMaster() {
		return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}
}
