package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rps_uploaded_biometric_data database table.
 * 
 */
@Entity
@Table(name="rps_uploaded_biometric_data")
@NamedQuery(name="RpsUploadedBiometricData.findAll", query="SELECT r FROM RpsUploadedBiometricData r")
public class RpsUploadedBiometricData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="attendance_done", length=1)
	private String attendanceDone;

	@Column(name="biometric_attendance_file_name", length=255)
	private String biometricAttendanceFileName;

	@Column(name="biometric_data_file_name", length=255)
	private String biometricDataFileName;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="ci_remarks_entered", length=1)
	private String ciRemarksEntered;

	@Column(name="ci_username", length=255)
	private String ciUsername;

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="exam_slot_code", length=255)
	private String examSlotCode;

	@Column(length=1)
	private String reconciled;

	@Column(name="upload_date")
	private Timestamp uploadDate;

	public RpsUploadedBiometricData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceDone() {
		return this.attendanceDone;
	}

	public void setAttendanceDone(String attendanceDone) {
		this.attendanceDone = attendanceDone;
	}

	public String getBiometricAttendanceFileName() {
		return this.biometricAttendanceFileName;
	}

	public void setBiometricAttendanceFileName(String biometricAttendanceFileName) {
		this.biometricAttendanceFileName = biometricAttendanceFileName;
	}

	public String getBiometricDataFileName() {
		return this.biometricDataFileName;
	}

	public void setBiometricDataFileName(String biometricDataFileName) {
		this.biometricDataFileName = biometricDataFileName;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCiRemarksEntered() {
		return this.ciRemarksEntered;
	}

	public void setCiRemarksEntered(String ciRemarksEntered) {
		this.ciRemarksEntered = ciRemarksEntered;
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

	public String getExamSlotCode() {
		return this.examSlotCode;
	}

	public void setExamSlotCode(String examSlotCode) {
		this.examSlotCode = examSlotCode;
	}

	public String getReconciled() {
		return this.reconciled;
	}

	public void setReconciled(String reconciled) {
		this.reconciled = reconciled;
	}

	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

}