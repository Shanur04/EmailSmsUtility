package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rps_uploaded_attendance_sheet database table.
 * 
 */
@Entity
@Table(name="rps_uploaded_attendance_sheet")
@NamedQuery(name="RpsUploadedAttendanceSheet.findAll", query="SELECT r FROM RpsUploadedAttendanceSheet r")
public class RpsUploadedAttendanceSheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="attendance_sheet_file_name", length=255)
	private String attendanceSheetFileName;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="ci_username", length=255)
	private String ciUsername;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="reupload_date")
	private Timestamp reuploadDate;

	@Column(name="upload_date")
	private Timestamp uploadDate;

	//bi-directional many-to-one association to RpsExamSlot
	@ManyToOne
	@JoinColumn(name="fk_exam_slot_id")
	private RpsExamSlot rpsExamSlot;

	public RpsUploadedAttendanceSheet() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceSheetFileName() {
		return this.attendanceSheetFileName;
	}

	public void setAttendanceSheetFileName(String attendanceSheetFileName) {
		this.attendanceSheetFileName = attendanceSheetFileName;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCiUsername() {
		return this.ciUsername;
	}

	public void setCiUsername(String ciUsername) {
		this.ciUsername = ciUsername;
	}

	public String getExamCategory() {
		return this.examCategory;
	}

	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Timestamp getReuploadDate() {
		return this.reuploadDate;
	}

	public void setReuploadDate(Timestamp reuploadDate) {
		this.reuploadDate = reuploadDate;
	}

	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public RpsExamSlot getRpsExamSlot() {
		return this.rpsExamSlot;
	}

	public void setRpsExamSlot(RpsExamSlot rpsExamSlot) {
		this.rpsExamSlot = rpsExamSlot;
	}

}