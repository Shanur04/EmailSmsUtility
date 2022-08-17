package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_candidate_exam_slot_mapping database table.
 * 
 */
@Entity
@Table(name="rps_candidate_exam_slot_mapping")
@NamedQuery(name="RpsCandidateExamSlotMapping.findAll", query="SELECT r FROM RpsCandidateExamSlotMapping r")
public class RpsCandidateExamSlotMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="attendance_status", length=1)
	private String attendanceStatus;

	@Column(name="biometric_attendance", length=1)
	private String biometricAttendance;

	@Column(name="biometric_attendance_remarks_ci", length=255)
	private String biometricAttendanceRemarksCi;

	@Column(name="biometric_attendance_remarks_coe", length=255)
	private String biometricAttendanceRemarksCoe;

	@Column(name="biometric_attendance_remarks_rh", length=255)
	private String biometricAttendanceRemarksRh;

	@Column(name="biometric_feature", length=50)
	private String biometricFeature;

	@Column(name="biometric_remarks_approved", length=1)
	private String biometricRemarksApproved;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="ci_attendance_status", length=1)
	private String ciAttendanceStatus;

	@Column(name="coe_attendance_status", length=1)
	private String coeAttendanceStatus;

	@Column(name="marked_for_deletion", length=1)
	private String markedForDeletion;

	@Column(name="reason_for_granting_extra_time", length=255)
	private String reasonForGrantingExtraTime;

	@Column(length=255)
	private String remarks;

	//bi-directional many-to-one association to RpsCandidate
	@ManyToOne
	@JoinColumn(name="fk_hall_ticket_number" , columnDefinition="VARCHAR(255)")
	private RpsCandidate rpsCandidate;

	//bi-directional many-to-one association to RpsExamSlot
	@ManyToOne
	@JoinColumn(name="fk_exam_slot_id")
	private RpsExamSlot rpsExamSlot;

	public RpsCandidateExamSlotMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceStatus() {
		return this.attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getBiometricAttendance() {
		return this.biometricAttendance;
	}

	public void setBiometricAttendance(String biometricAttendance) {
		this.biometricAttendance = biometricAttendance;
	}

	public String getBiometricAttendanceRemarksCi() {
		return this.biometricAttendanceRemarksCi;
	}

	public void setBiometricAttendanceRemarksCi(String biometricAttendanceRemarksCi) {
		this.biometricAttendanceRemarksCi = biometricAttendanceRemarksCi;
	}

	public String getBiometricAttendanceRemarksCoe() {
		return this.biometricAttendanceRemarksCoe;
	}

	public void setBiometricAttendanceRemarksCoe(String biometricAttendanceRemarksCoe) {
		this.biometricAttendanceRemarksCoe = biometricAttendanceRemarksCoe;
	}

	public String getBiometricAttendanceRemarksRh() {
		return this.biometricAttendanceRemarksRh;
	}

	public void setBiometricAttendanceRemarksRh(String biometricAttendanceRemarksRh) {
		this.biometricAttendanceRemarksRh = biometricAttendanceRemarksRh;
	}

	public String getBiometricFeature() {
		return this.biometricFeature;
	}

	public void setBiometricFeature(String biometricFeature) {
		this.biometricFeature = biometricFeature;
	}

	public String getBiometricRemarksApproved() {
		return this.biometricRemarksApproved;
	}

	public void setBiometricRemarksApproved(String biometricRemarksApproved) {
		this.biometricRemarksApproved = biometricRemarksApproved;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCiAttendanceStatus() {
		return this.ciAttendanceStatus;
	}

	public void setCiAttendanceStatus(String ciAttendanceStatus) {
		this.ciAttendanceStatus = ciAttendanceStatus;
	}

	public String getCoeAttendanceStatus() {
		return this.coeAttendanceStatus;
	}

	public void setCoeAttendanceStatus(String coeAttendanceStatus) {
		this.coeAttendanceStatus = coeAttendanceStatus;
	}

	public String getMarkedForDeletion() {
		return this.markedForDeletion;
	}

	public void setMarkedForDeletion(String markedForDeletion) {
		this.markedForDeletion = markedForDeletion;
	}

	public String getReasonForGrantingExtraTime() {
		return this.reasonForGrantingExtraTime;
	}

	public void setReasonForGrantingExtraTime(String reasonForGrantingExtraTime) {
		this.reasonForGrantingExtraTime = reasonForGrantingExtraTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public RpsCandidate getRpsCandidate() {
		return this.rpsCandidate;
	}

	public void setRpsCandidate(RpsCandidate rpsCandidate) {
		this.rpsCandidate = rpsCandidate;
	}

	public RpsExamSlot getRpsExamSlot() {
		return this.rpsExamSlot;
	}

	public void setRpsExamSlot(RpsExamSlot rpsExamSlot) {
		this.rpsExamSlot = rpsExamSlot;
	}

}