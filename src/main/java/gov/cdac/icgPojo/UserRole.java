package gov.cdac.icgPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
@JsonIgnoreProperties({"systemUserCredentials"})
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_ROLES_ROLEID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ROLES_ROLEID_GENERATOR")
	@Column(name="role_id")
	private Integer roleId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="role_description")
	private String roleDescription;

	@Column(name="role_name")
	private String roleName;

	@Column(name="role_status")
	private Boolean roleStatus;

	//bi-directional many-to-one association to SystemUserCredential
	@OneToMany(mappedBy="userRole")
	private List<SystemUserCredential> systemUserCredentials;

	public UserRole() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getRoleStatus() {
		return this.roleStatus;
	}

	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
	}

	public List<SystemUserCredential> getSystemUserCredentials() {
		return this.systemUserCredentials;
	}

	public void setSystemUserCredentials(List<SystemUserCredential> systemUserCredentials) {
		this.systemUserCredentials = systemUserCredentials;
	}

	public SystemUserCredential addSystemUserCredential(SystemUserCredential systemUserCredential) {
		getSystemUserCredentials().add(systemUserCredential);
		systemUserCredential.setUserRole(this);

		return systemUserCredential;
	}

	public SystemUserCredential removeSystemUserCredential(SystemUserCredential systemUserCredential) {
		getSystemUserCredentials().remove(systemUserCredential);
		systemUserCredential.setUserRole(null);

		return systemUserCredential;
	}

}