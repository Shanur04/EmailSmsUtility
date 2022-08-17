package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rps_candidate database table.
 * 
 */
@Entity
@Table(name="rps_candidate")
@NamedQuery(name="RpsCandidate.findAll", query="SELECT r FROM RpsCandidate r")
public class RpsCandidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="hall_ticket_number", unique=true, nullable=false, columnDefinition="VARCHAR(255)")
	private String hallTicketNumber;

	@Column(name="age_relaxation_availed")
	private Boolean ageRelaxationAvailed;

	@Column(name="candidature_cancelled", length=1)
	private String candidatureCancelled;

	@Column(name="candidature_cancelled_reason", length=255)
	private String candidatureCancelledReason;

	@Column(length=255)
	private String category;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="date_of_birth")
	private Timestamp dateOfBirth;

	

	@Column(name="exam_category", length=255)
	private String examCategory;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(length=1)
	private String meritorious;

	@Column(length=255)
	private String name;

	@Column(name="photo_id_number", length=255)
	private String photoIdNumber;

	@Column(name="photograph_name", length=255)
	private String photographName;

	@Column(name="qualifying_exam")
	private String qualifyingExam;

	@Column(name="qualifying_percentage")
	private Float qualifyingPercentage;

	@Column(length=255)
	private String region;

	@Column(name="registration_number", length=255)
	private String registrationNumber;

	

	@Column(name="slot_code", length=255)
	private String slotCode;

	//bi-directional many-to-one association to RpsCandidateExamSlotMapping
	@OneToMany(mappedBy="rpsCandidate")
	private List<RpsCandidateExamSlotMapping> rpsCandidateExamSlotMappings;

	//bi-directional many-to-one association to RpsExamResult
	@OneToMany(mappedBy="rpsCandidate")
	private List<RpsExamResult> rpsExamResults;

	//bi-directional many-to-one association to RpsShortlistedExamResult
	@OneToMany(mappedBy="rpsCandidate")
	private List<RpsShortlistedExamResult> rpsShortlistedExamResults;

	public RpsCandidate() {
	}

	public String getHallTicketNumber() {
		return this.hallTicketNumber;
	}

	public void setHallTicketNumber(String hallTicketNumber) {
		this.hallTicketNumber = hallTicketNumber;
	}

	public Boolean getAgeRelaxationAvailed() {
		return this.ageRelaxationAvailed;
	}

	public void setAgeRelaxationAvailed(Boolean ageRelaxationAvailed) {
		this.ageRelaxationAvailed = ageRelaxationAvailed;
	}

	public String getCandidatureCancelled() {
		return this.candidatureCancelled;
	}

	public void setCandidatureCancelled(String candidatureCancelled) {
		this.candidatureCancelled = candidatureCancelled;
	}

	public String getCandidatureCancelledReason() {
		return this.candidatureCancelledReason;
	}

	public void setCandidatureCancelledReason(String candidatureCancelledReason) {
		this.candidatureCancelledReason = candidatureCancelledReason;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public Timestamp getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getMeritorious() {
		return this.meritorious;
	}

	public void setMeritorious(String meritorious) {
		this.meritorious = meritorious;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoIdNumber() {
		return this.photoIdNumber;
	}

	public void setPhotoIdNumber(String photoIdNumber) {
		this.photoIdNumber = photoIdNumber;
	}

	public String getPhotographName() {
		return this.photographName;
	}

	public void setPhotographName(String photographName) {
		this.photographName = photographName;
	}

	public String getQualifyingExam() {
		return this.qualifyingExam;
	}

	public void setQualifyingExam(String qualifyingExam) {
		this.qualifyingExam = qualifyingExam;
	}

	public Float getQualifyingPercentage() {
		return this.qualifyingPercentage;
	}

	public void setQualifyingPercentage(Float qualifyingPercentage) {
		this.qualifyingPercentage = qualifyingPercentage;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	
	public String getSlotCode() {
		return this.slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public List<RpsCandidateExamSlotMapping> getRpsCandidateExamSlotMappings() {
		return this.rpsCandidateExamSlotMappings;
	}

	public void setRpsCandidateExamSlotMappings(List<RpsCandidateExamSlotMapping> rpsCandidateExamSlotMappings) {
		this.rpsCandidateExamSlotMappings = rpsCandidateExamSlotMappings;
	}

	public RpsCandidateExamSlotMapping addRpsCandidateExamSlotMapping(RpsCandidateExamSlotMapping rpsCandidateExamSlotMapping) {
		getRpsCandidateExamSlotMappings().add(rpsCandidateExamSlotMapping);
		rpsCandidateExamSlotMapping.setRpsCandidate(this);

		return rpsCandidateExamSlotMapping;
	}

	public RpsCandidateExamSlotMapping removeRpsCandidateExamSlotMapping(RpsCandidateExamSlotMapping rpsCandidateExamSlotMapping) {
		getRpsCandidateExamSlotMappings().remove(rpsCandidateExamSlotMapping);
		rpsCandidateExamSlotMapping.setRpsCandidate(null);

		return rpsCandidateExamSlotMapping;
	}

	public List<RpsExamResult> getRpsExamResults() {
		return this.rpsExamResults;
	}

	public void setRpsExamResults(List<RpsExamResult> rpsExamResults) {
		this.rpsExamResults = rpsExamResults;
	}

	public RpsExamResult addRpsExamResult(RpsExamResult rpsExamResult) {
		getRpsExamResults().add(rpsExamResult);
		rpsExamResult.setRpsCandidate(this);

		return rpsExamResult;
	}

	public RpsExamResult removeRpsExamResult(RpsExamResult rpsExamResult) {
		getRpsExamResults().remove(rpsExamResult);
		rpsExamResult.setRpsCandidate(null);

		return rpsExamResult;
	}

	public List<RpsShortlistedExamResult> getRpsShortlistedExamResults() {
		return this.rpsShortlistedExamResults;
	}

	public void setRpsShortlistedExamResults(List<RpsShortlistedExamResult> rpsShortlistedExamResults) {
		this.rpsShortlistedExamResults = rpsShortlistedExamResults;
	}

	public RpsShortlistedExamResult addRpsShortlistedExamResult(RpsShortlistedExamResult rpsShortlistedExamResult) {
		getRpsShortlistedExamResults().add(rpsShortlistedExamResult);
		rpsShortlistedExamResult.setRpsCandidate(this);

		return rpsShortlistedExamResult;
	}

	public RpsShortlistedExamResult removeRpsShortlistedExamResult(RpsShortlistedExamResult rpsShortlistedExamResult) {
		getRpsShortlistedExamResults().remove(rpsShortlistedExamResult);
		rpsShortlistedExamResult.setRpsCandidate(null);

		return rpsShortlistedExamResult;
	}

}