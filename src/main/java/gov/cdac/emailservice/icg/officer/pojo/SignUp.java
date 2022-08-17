package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.validator.constraints.Range;

/**
 * The persistent class for the sign_up database table.
 * 
 */
@Entity
@Table(name="sign_up")
@NamedQuery(name="SignUp.findAll", query="SELECT s FROM SignUp s")
public class SignUp implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="SIGNUPID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIGNUPID_GENERATOR")
	@Column(name="signup_id")
	private Long signupId;

	@NotNull(message="email Should not be empty")
	@NotEmpty(message="email Should not be empty")
	@Pattern(regexp="^[a-zA-Z0-9._]+@[a-zA-Z.]+[a-zA-Z]{2,6}$",message="Invalid Email")
	@Size(max=100,message="Length of email Id must be less than 100 characters")
	@Column(name="emailid")
	private String emailid;
	
	@NotNull(message="Father Name Should not be empty")
    @NotEmpty(message="Father Name Should not be empty")
    @Size(min=1,max=50)
	@Pattern(regexp="^[a-z A-Z]+[ ]?[.]?[ ]?[a-z A-Z]*",message="Only upper case ,lower case, dot and space is allowed")
	@Column(name="father_name")
	private String fatherName;

	@NotNull(message="gender should not be empty")
	@NotEmpty(message="gender should not be empty")
	@Column(name="gender")
	private String gender;
	
	@ColumnTransformer(forColumn = "mobile", read = "pgp_sym_decrypt(mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",
			write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@NotNull(message="mobile should not be empty")
	@NotEmpty(message="mobile should not be empty")
	@Column(name="mobile")
	@Range(min = 0000000001,message="Mobile Number must be greater than 0000000001 and less than or equal to 9999999999")
	private String mobile;
	
	@NotNull(message="Mother Name Should not be null")
    @NotEmpty(message="Mother Name Should not be empty")
	@NotBlank(message="Mother Name Should not be blank")
	@Size(min=1,max=50) 
	@Pattern(regexp="^[a-z A-Z]+[ ]?[.]?[ ]?[a-z A-Z]*",message="Only upper case ,lower case, dot and space is allowed")
	@Column(name="mother_name")
	private String motherName;
	
	@NotNull(message="Name Should not be null")
    @NotEmpty(message="Name Should not be empty")
	@NotBlank(message="Name should not be blank")
	@Size(min=2,max=50)
	@Pattern(regexp="^[a-z A-Z]+[ ]?[.]?[ ]?[a-z A-Z]*",message="Only upper case ,lower case, dot and space is allowed")
	@Column(name="name")
	private String name;

	@NotEmpty(message="Nationality should not be empty")
	@NotNull(message="Nationality should not be null")
	@Column(name="nationality")
	private String nationality;

	@Column(name="password")
	private String password;

	@Column(name="record_tracking")
	@CreationTimestamp
	private Timestamp recordTracking;
	
	/*
	 * @NotEmpty(message="Mobile Otp should not be empty")
	 * 
	 * @NotNull(message="Mobile Otp Should not be empty")
	 */
	@Transient
	private String mobileOtp;
	
	@NotEmpty(message="Email Otp should not be empty")
	@NotNull(message="Email Otp Should not be empty")
	@Transient
	private String emailOtp;
	
	@NotEmpty(message="Confirm Email should not be empty")
	@NotNull(message="Confirm Email  Should not be empty")
	@Transient
	private String confirmEmail;
	
	@Column(name="is_mobile_verified")
	private Boolean isMobileVerified;
	
	
	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}

	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	
	
	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	

	public String getMobileOtp() {
		return mobileOtp;
	}

	@Override
	public String toString() {
		return "SignUp [signupId=" + signupId + ", emailid=" + emailid + ", fatherName=" + fatherName + ", gender="
				+ gender + ", mobile=" + mobile + ", motherName=" + motherName + ", name=" + name + ", nationality="
				+ nationality + ", password=" + password + ", recordTracking=" + recordTracking + ", mobileOtp="
				+ mobileOtp + ", emailOtp=" + emailOtp + "]";
	}

	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
	}

	public String getEmailOtp() {
		return emailOtp;
	}

	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}

	//bi-directional many-to-one association to BatchMaster
	@ManyToOne
	@JoinColumn(name="batch_id")
	private BatchMaster batchMaster;

	//bi-directional many-to-one association to RegistrationStatus
	@ManyToOne
	@JoinColumn(name="registration_status_id")
	private RegistrationStatus registrationStatus;

	public SignUp() {
	}

	public Long getSignupId() {
		return this.signupId;
	}

	public void setSignupId(Long signupId) {
		this.signupId = signupId;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public BatchMaster getBatchMaster() {
		return this.batchMaster;
	}

	public void setBatchMaster(BatchMaster batchMaster) {
		this.batchMaster = batchMaster;
	}

	public RegistrationStatus getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

}