package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the applicant_hallticket database table.
 * 
 */
@Entity
@Table(name="applicant_hallticket")
@NamedQuery(name="ApplicantHallticket.findAll", query="SELECT a FROM ApplicantHallticket a")
public class ApplicantHallticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICANT_HALLTICKET_APPLICANTHALLTICKETID_GENERATOR", allocationSize = 1  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICANT_HALLTICKET_APPLICANTHALLTICKETID_GENERATOR")
	@Column(name="applicant_hallticket_id")
	private Long applicantHallticketId;

	@Column(name="admitcard_downloaded_timestamp")
	private Timestamp admitcardDownloadedTimestamp;

	private String emailid;

	@Column(name="hallticket_number")
	private String hallticketNumber;

	@Column(name="is_admitcard_downloaded")
	private Boolean isAdmitcardDownloaded;

	@Column(name="is_admitcard_generated")
	private Boolean isAdmitcardGenerated;
	
	@Column(name="is_dummy_image")
	private Boolean isDummyImage;

	@Column(name="is_email_sent")
	private Boolean isEmailSent;

	@Column(name="is_new_hallticket_number")
	private Boolean isNewHallticketNumber;

	@Column(name="is_reexam")
	private Boolean isReexam;

	@Column(name="is_smssent")
	private Boolean isSmssent;

	@Column(name="photo_path")
	private String photoPath;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="registration_no")
	private String registrationNo;

	//bi-directional many-to-one association to ApplicantCentreAllotment
	@ManyToOne
	@JoinColumn(name="applicant_centre_allotment_id")
	private ApplicantCentreAllotment applicantCentreAllotment;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to ExamSlotwiseReportingtime
	@ManyToOne
	@JoinColumn(name="exam_slotwise_reportingtime_id")
	private ExamSlotwiseReportingtime examSlotwiseReportingtime;

	public ApplicantHallticket() {
	}

	public Long getApplicantHallticketId() {
		return this.applicantHallticketId;
	}

	public void setApplicantHallticketId(Long applicantHallticketId) {
		this.applicantHallticketId = applicantHallticketId;
	}

	public Timestamp getAdmitcardDownloadedTimestamp() {
		return this.admitcardDownloadedTimestamp;
	}

	public void setAdmitcardDownloadedTimestamp(Timestamp admitcardDownloadedTimestamp) {
		this.admitcardDownloadedTimestamp = admitcardDownloadedTimestamp;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getHallticketNumber() {
		return this.hallticketNumber;
	}

	public void setHallticketNumber(String hallticketNumber) {
		this.hallticketNumber = hallticketNumber;
	}

	public Boolean getIsAdmitcardDownloaded() {
		return this.isAdmitcardDownloaded;
	}

	public void setIsAdmitcardDownloaded(Boolean isAdmitcardDownloaded) {
		this.isAdmitcardDownloaded = isAdmitcardDownloaded;
	}

	public Boolean getIsAdmitcardGenerated() {
		return this.isAdmitcardGenerated;
	}

	public void setIsAdmitcardGenerated(Boolean isAdmitcardGenerated) {
		this.isAdmitcardGenerated = isAdmitcardGenerated;
	}

	public Boolean getIsEmailSent() {
		return this.isEmailSent;
	}

	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	public Boolean getIsNewHallticketNumber() {
		return this.isNewHallticketNumber;
	}

	public void setIsNewHallticketNumber(Boolean isNewHallticketNumber) {
		this.isNewHallticketNumber = isNewHallticketNumber;
	}

	public Boolean getIsReexam() {
		return this.isReexam;
	}

	public void setIsReexam(Boolean isReexam) {
		this.isReexam = isReexam;
	}

	public Boolean getIsSmssent() {
		return this.isSmssent;
	}

	public void setIsSmssent(Boolean isSmssent) {
		this.isSmssent = isSmssent;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public ApplicantCentreAllotment getApplicantCentreAllotment() {
		return this.applicantCentreAllotment;
	}

	public void setApplicantCentreAllotment(ApplicantCentreAllotment applicantCentreAllotment) {
		this.applicantCentreAllotment = applicantCentreAllotment;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public ExamSlotwiseReportingtime getExamSlotwiseReportingtime() {
		return this.examSlotwiseReportingtime;
	}

	public void setExamSlotwiseReportingtime(ExamSlotwiseReportingtime examSlotwiseReportingtime) {
		this.examSlotwiseReportingtime = examSlotwiseReportingtime;
	}

	public Boolean getIsDummyImage() {
		return isDummyImage;
	}

	public void setIsDummyImage(Boolean isDummyImage) {
		this.isDummyImage = isDummyImage;
	}
	

}