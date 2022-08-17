package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the system_user_credentials database table.
 * 
 */
@Entity
@Table(name="system_user_credentials")
@NamedQuery(name="SystemUserCredential.findAll", query="SELECT s FROM SystemUserCredential s")
@JsonIgnoreProperties({"centreMasters"})
public class SystemUserCredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYSTEMUSERCREDID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYSTEMUSERCREDID_GENERATOR")
	@Column(name="system_user_cred_id")
	private Integer systemUserCredId;

	@Column(name="created_by")
	private Integer createdBy;

	private String description;

	@Column(name="email")
	private String email;

	private String mobile;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="password_reset_status")
	private Boolean passwordResetStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	private Boolean status;
	
	// bi-directional many-to-one association to ExamMaster
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamMaster examMaster;
    
	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="systemUserCredential")
	private List<CentreMaster> centreMasters;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="role_id")
	private UserRole userRole;

	public SystemUserCredential() {
	}

	public ExamMaster getExamMaster() {
		return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public Integer getSystemUserCredId() {
		return this.systemUserCredId;
	}

	public void setSystemUserCredId(Integer systemUserCredId) {
		this.systemUserCredId = systemUserCredId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPasswordResetStatus() {
		return this.passwordResetStatus;
	}

	public void setPasswordResetStatus(Boolean passwordResetStatus) {
		this.passwordResetStatus = passwordResetStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<CentreMaster> getCentreMasters() {
		return this.centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public CentreMaster addCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().add(centreMaster);
		centreMaster.setSystemUserCredential(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setSystemUserCredential(null);

		return centreMaster;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}