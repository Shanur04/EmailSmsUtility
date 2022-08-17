package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rps_users database table.
 * 
 */
@Entity
@Table(name="rps_users")
@NamedQuery(name="RpsUser.findAll", query="SELECT r FROM RpsUser r")
public class RpsUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=255)
	private String username;

	@Column(length=1)
	private String activated;

	@Column(name="contact_number", length=255)
	private String contactNumber;

	@Column(name="email_id", length=255)
	private String emailId;

	@Column(name="invalid_login_attempts")
	private Integer invalidLoginAttempts;

	@Column(name="locked_out", length=1)
	private String lockedOut;

	@Column(name="login_attempt_date")
	private Timestamp loginAttemptDate;

	@Column(length=255)
	private String name;

	@Column(name="new_password", length=1)
	private String newPassword;

	@Column(length=255)
	private String password;

	@Column(name="password_link_creation_date")
	private Timestamp passwordLinkCreationDate;

	@Column(name="password_link_used", length=1)
	private String passwordLinkUsed;

	@Column(length=255)
	private String role;

	@Column(name="security_answer", length=255)
	private String securityAnswer;

	@Column(name="security_question", length=255)
	private String securityQuestion;

	//bi-directional many-to-one association to RpsRegionHeadMapping
	@OneToMany(mappedBy="rpsUser")
	private List<RpsRegionHeadMapping> rpsRegionHeadMappings;

	public RpsUser() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActivated() {
		return this.activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getInvalidLoginAttempts() {
		return this.invalidLoginAttempts;
	}

	public void setInvalidLoginAttempts(Integer invalidLoginAttempts) {
		this.invalidLoginAttempts = invalidLoginAttempts;
	}

	public String getLockedOut() {
		return this.lockedOut;
	}

	public void setLockedOut(String lockedOut) {
		this.lockedOut = lockedOut;
	}

	public Timestamp getLoginAttemptDate() {
		return this.loginAttemptDate;
	}

	public void setLoginAttemptDate(Timestamp loginAttemptDate) {
		this.loginAttemptDate = loginAttemptDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewPassword() {
		return this.newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getPasswordLinkCreationDate() {
		return this.passwordLinkCreationDate;
	}

	public void setPasswordLinkCreationDate(Timestamp passwordLinkCreationDate) {
		this.passwordLinkCreationDate = passwordLinkCreationDate;
	}

	public String getPasswordLinkUsed() {
		return this.passwordLinkUsed;
	}

	public void setPasswordLinkUsed(String passwordLinkUsed) {
		this.passwordLinkUsed = passwordLinkUsed;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public List<RpsRegionHeadMapping> getRpsRegionHeadMappings() {
		return this.rpsRegionHeadMappings;
	}

	public void setRpsRegionHeadMappings(List<RpsRegionHeadMapping> rpsRegionHeadMappings) {
		this.rpsRegionHeadMappings = rpsRegionHeadMappings;
	}

	public RpsRegionHeadMapping addRpsRegionHeadMapping(RpsRegionHeadMapping rpsRegionHeadMapping) {
		getRpsRegionHeadMappings().add(rpsRegionHeadMapping);
		rpsRegionHeadMapping.setRpsUser(this);

		return rpsRegionHeadMapping;
	}

	public RpsRegionHeadMapping removeRpsRegionHeadMapping(RpsRegionHeadMapping rpsRegionHeadMapping) {
		getRpsRegionHeadMappings().remove(rpsRegionHeadMapping);
		rpsRegionHeadMapping.setRpsUser(null);

		return rpsRegionHeadMapping;
	}

}