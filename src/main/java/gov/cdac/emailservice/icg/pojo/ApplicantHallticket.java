package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gov.cdac.emailservice.models.ExamCenterMappingModel;
import gov.cdac.emailservice.projection.PersonalDetailSummary;

/**
 * The persistent class for the applicant_hallticket database table.
 * 
 */
@Entity
@Table(name = "applicant_hallticket")
@NamedQuery(name = "ApplicantHallticket.findAll", query = "SELECT a FROM ApplicantHallticket a")
public class ApplicantHallticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private final transient Logger log = LogManager.getLogger(ApplicantHallticket.class);

//    applicant_hallticket_applicanthallticketid_generator
    @Id  
    @SequenceGenerator(name = "APPLICANT_HALLTICKET_APPLICANTHALLTICKETID_GENERATOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANT_HALLTICKET_APPLICANTHALLTICKETID_GENERATOR")
    @Column(name = "applicant_hallticket_id")
    private Long applicantHallticketId;

    private String emailid;

    @Column(name = "admitcard_downloaded_timestamp")
    private Timestamp admitCardDownloadedTimestamp;

    @Column(name = "hallticket_number")
    private String hallticketNumber;

    @Column(name = "is_email_sent")
    private Boolean isEmailSent;

    @Column(name = "is_admitcard_downloaded")
    private Boolean isAdmitCardDownloaded;

    @Column(name = "is_admitcard_generated")
    private Boolean isAdmitCardGenerated;

    @Column(name = "is_new_hallticket_number")
    private Boolean isNewHallticketNumber;

    @Column(name = "is_reexam")
    private Boolean isReexam;

    @Column(name = "photo_path")
    private String photograph;

    @CreationTimestamp
    @Column(name = "record_tracking")
    private Timestamp recordTracking;

    @Column(name = "registration_no")
    private String registrationNo;

    @Column(name = "is_dummy_image")
    private Boolean isDummyImage;

    @Column(name = "is_smssent")
    private Boolean isSmsSent;

    // bi-directional many-to-one association to ApplicantCentreAllotment
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "applicant_centre_allotment_id")
    private ApplicantCentreAllotment applicantCentreAllotment;

    // bi-directional many-to-one association to ApplicantCredential

    @ManyToOne
    @JoinColumn(name = "applicant_cred_id")
    private ApplicantCredential applicantCredential;

    // bi-directional many-to-one association to ExamSlotwiseReportingtime
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exam_slotwise_reportingtime_id")
    private ExamSlotwiseReportingtime examSlotwiseReportingtime;

    public ApplicantHallticket() {
    }

    /**
     * 
     * @param personalDetailSummary
     * @param examCenterMappingModel
     * @param slotWiseReportingTime
     * @param totalBatches
     * @param dummyFile
     * @param reExamFlag
     */
/*    public ApplicantHallticket(PersonalDetailSummary personalDetailSummary,
	    ExamCenterMappingModel examCenterMappingModel, Map<String, Integer> slotWiseReportingTime, int totalBatches,
	    File dummyFile, Boolean reExamFlag) {
	this.applicantCentreAllotment = new ApplicantCentreAllotment(
		personalDetailSummary.getApplicantCenterAllotmentId());
	this.applicantCredential = new ApplicantCredential(personalDetailSummary.getApplicantCredId());

	this.emailid = personalDetailSummary.getEmailIdPrimary();
	this.registrationNo = personalDetailSummary.getRegistrationNumber();

	String hallTicketNumberWithBatchId = getHallTicketNumberFromExamCenterMappingId(personalDetailSummary,
		examCenterMappingModel, totalBatches);
	this.hallticketNumber = hallTicketNumberWithBatchId.substring(0, 20);
	this.examSlotwiseReportingtime = new ExamSlotwiseReportingtime(
		slotWiseReportingTime.get(hallTicketNumberWithBatchId.substring(21, 22)
			+ examCenterMappingModel.getExamSlotCode() + personalDetailSummary.getPostCode()));

	this.isDummyImage = false;

	File photoFile = new File(Paths.get(personalDetailSummary.getPhotoPath()).toString());
	if (!photoFile.exists()) {
	    //HallTicketController.dummyPlaced.add(this.hallticketNumber);
	    photoFile = dummyFile;
	    this.isDummyImage = true;
	    log.info("Dummy photo generated for: " + this.hallticketNumber);
	}

	HallTicketService.hallTicketPhotosMap.put(this.hallticketNumber, photoFile);

	this.photograph = this.hallticketNumber + "." + FilenameUtils.getExtension(photoFile.getAbsolutePath());

	this.isNewHallticketNumber = false;
	this.isEmailSent = false;
	this.isAdmitCardDownloaded = false;
	this.isAdmitCardGenerated = false;
	this.isReexam = reExamFlag;
	this.isSmsSent = false;

	//log.info(++HallTicketController.count + " " + hallTicketNumberWithBatchId);
    }
*/
    /**
     * 
     * @param personalDetailSummary
     * @param model
     * @param totalBatches
     * @return
     */
    private String getHallTicketNumberFromExamCenterMappingId(PersonalDetailSummary personalDetailSummary,
	    ExamCenterMappingModel model, int totalBatches) {
	int currentCount = model.getGeneratedCount() + 1;
	model.setGeneratedCount(currentCount);
	String hallTicketNumberToReturn = personalDetailSummary.getRegionheadCode()
		+ personalDetailSummary.getExamYear().substring(2)
		+ getMonthNumberFromMonthName(personalDetailSummary.getExamMonth())
		+ personalDetailSummary.getCityCode() + personalDetailSummary.getCentreCode()
		+ personalDetailSummary.getPostCode() + personalDetailSummary.getSectionCode()
		+ personalDetailSummary.getCategoryCode() + personalDetailSummary.getExamSlotCode();
	Long totalNoOfCandidatesInThatCenterInThatSlot = (long) model.getTotalSeatAllocated();
	hallTicketNumberToReturn += String.format("%03d", currentCount);

	int batchSize = (int) (Math.ceil((double) totalNoOfCandidatesInThatCenterInThatSlot / totalBatches));

	if (currentCount <= batchSize) {
	    hallTicketNumberToReturn += "B1";
	} else {
	    hallTicketNumberToReturn += "B2";
	}

	return hallTicketNumberToReturn;
    }

    /**
     * 
     * @param monthName
     * @return
     */
    private String getMonthNumberFromMonthName(String monthName) {
	String monthNumberToReturn = null;
	SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
	Calendar cal = Calendar.getInstance();
	try {
	    cal.setTime(inputFormat.parse(monthName));
	    SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
	    monthNumberToReturn = outputFormat.format(cal.getTime());
	} catch (ParseException e) {
	    log.info(e.getMessage());
	}
	return monthNumberToReturn;
    }

    public Long getApplicantHallticketId() {
	return this.applicantHallticketId;
    }

    public void setApplicantHallticketId(Long applicantHallticketId) {
	this.applicantHallticketId = applicantHallticketId;
    }

    public Boolean getIsSmsSent() {
	return isSmsSent;
    }

    public void setIsSmsSent(Boolean isSmsSent) {
	this.isSmsSent = isSmsSent;
    }

    public String getEmailid() {
	return this.emailid;
    }

    public void setEmailid(String emailid) {
	this.emailid = emailid;
    }

    public Timestamp getAdmitCardDownloadedTimestamp() {
	return admitCardDownloadedTimestamp;
    }

    public void setAdmitCardDownloadedTimestamp(Timestamp admitCardDownloadedTimestamp) {
	this.admitCardDownloadedTimestamp = admitCardDownloadedTimestamp;
    }

    public String getHallticketNumber() {

	return this.hallticketNumber;
    }

    public void setHallticketNumber(String hallticketNumber) {
	this.hallticketNumber = hallticketNumber;
    }

    public Boolean getIsEmailSent() {
	return this.isEmailSent;
    }

    public void setIsEmailSent(Boolean isEmailSent) {
	this.isEmailSent = isEmailSent;
    }

    public Boolean getIsAdmitCardDownloaded() {
	return isAdmitCardDownloaded;
    }

    public void setIsAdmitCardDownloaded(Boolean isAdmitCardDownloaded) {
	this.isAdmitCardDownloaded = isAdmitCardDownloaded;
    }

    public Boolean getIsAdmitCardGenerated() {
	return isAdmitCardGenerated;
    }

    public void setIsAdmitCardGenerated(Boolean isAdmitCardGenerated) {
	this.isAdmitCardGenerated = isAdmitCardGenerated;
    }

    public Boolean getIsNewHallticketNumber() {
	return this.isNewHallticketNumber;
    }

    public void setIsNewHallticketNumber(Boolean isNewHallticketNumber) {
	this.isNewHallticketNumber = isNewHallticketNumber;
    }

    public Boolean getIsDummyImage() {
	return isDummyImage;
    }

    public void setIsDummyImage(Boolean isDummyImage) {
	this.isDummyImage = isDummyImage;
    }

    public Boolean getIsReexam() {
	return this.isReexam;
    }

    public void setIsReexam(Boolean isReexam) {
	this.isReexam = isReexam;
    }

    public String getPhotograph() {
	return this.photograph;
    }

    public void setPhotograph(String photograph) {
	this.photograph = photograph;
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

}