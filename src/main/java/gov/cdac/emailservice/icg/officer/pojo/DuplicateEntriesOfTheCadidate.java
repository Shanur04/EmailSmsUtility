package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the duplicate_entries_of_the_cadidate database table.
 * 
 */
@Entity
@Table(name="duplicate_entries_of_the_cadidate")
public class DuplicateEntriesOfTheCadidate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="aadhaar_card_number")
	private String aadhaarCardNumber;

	@Column(name="candidate_name")
	private String candidateName;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Column(name="email_id_primary")
	private String emailIdPrimary;
	
	@Column(name="mobile")
	private String mobile;

	@Column(name="parent_name")
	private String parentName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="registration_no")
	private Long registrationNo;

	@Column(name="short_name")
	private String shortName;
	
	@Column(name="is_rejected")
	private Boolean isRejected;
	
	@Column(name="rejected_reason")
	private String rejectedReason;
	
	@Transient
	private String formattedDate;
	
	@Transient
	private String formattedDOB;
	
	@Transient
	String statusLink;
	
	@Transient
	String editLink;
	
	@Transient
	String viewDetailsLink;
	
	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="rejected_by_system_user_cred_id")
	private SystemUserCredential systemUserCredential;

	public DuplicateEntriesOfTheCadidate() {
	}

	public String getAadhaarCardNumber() {
		return this.aadhaarCardNumber;
	}

	public void setAadhaarCardNumber(String aadhaarCardNumber) {
		this.aadhaarCardNumber = aadhaarCardNumber;
	}

	public String getCandidateName() {
		return this.candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailIdPrimary() {
		return this.emailIdPrimary;
	}

	public void setEmailIdPrimary(String emailIdPrimary) {
		this.emailIdPrimary = emailIdPrimary;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Long getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(Long registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public String getFormattedDOB() {
		return formattedDOB;
	}

	public void setFormattedDOB(String formattedDOB) {
		this.formattedDOB = formattedDOB;
	}

	public String getStatusLink() {
		return statusLink;
	}

	public void setStatusLink(String statusLink) {
		this.statusLink = statusLink;
	}

	public String getEditLink() {
		return editLink;
	}

	public void setEditLink(String editLink) {
		this.editLink = editLink;
	}

	public String getViewDetailsLink() {
		return viewDetailsLink;
	}

	public void setViewDetailsLink(String viewDetailsLink) {
		this.viewDetailsLink = viewDetailsLink;
	}

	public Boolean getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(Boolean isRejected) {
		this.isRejected = isRejected;
	}
	
	
	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public SystemUserCredential getSystemUserCredential() {
		return this.systemUserCredential;
	}

	 @ManyToOne(fetch = FetchType.LAZY)
	public void setSystemUserCredential(SystemUserCredential systemUserCredential) {
		this.systemUserCredential = systemUserCredential;
	}

	@Override
	public String toString() {
		return "DuplicateEntriesOfTheCadidate [id=" + id + ", aadhaarCardNumber=" + aadhaarCardNumber
				+ ", candidateName=" + candidateName + ", dateOfBirth=" + dateOfBirth + ", emailIdPrimary="
				+ emailIdPrimary + ", mobile=" + mobile + ", parentName=" + parentName + ", recordTracking="
				+ recordTracking + ", registrationNo=" + registrationNo + ", shortName=" + shortName + ", isRejected="
				+ isRejected + ", rejectedReason=" + rejectedReason + ", formattedDate=" + formattedDate
				+ ", formattedDOB=" + formattedDOB + ", statusLink=" + statusLink + ", editLink=" + editLink
				+ ", viewDetailsLink=" + viewDetailsLink + ", systemUserCredential=" + systemUserCredential + "]";
	}	

}