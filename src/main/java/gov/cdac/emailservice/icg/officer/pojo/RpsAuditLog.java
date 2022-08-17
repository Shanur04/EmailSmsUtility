package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rps_audit_logs database table.
 * 
 */
@Entity
@Table(name="rps_audit_logs")
@NamedQuery(name="RpsAuditLog.findAll", query="SELECT r FROM RpsAuditLog r")
public class RpsAuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="class_name", length=255)
	private String className;

	private Timestamp datetime;

	@Column(name="function_call", length=2000)
	private String functionCall;

	@Column(name="remote_addr", length=255)
	private String remoteAddr;

	@Column(name="remote_host", length=255)
	private String remoteHost;

	@Column(name="remote_user", length=255)
	private String remoteUser;

	@Column(length=255)
	private String username;

	public RpsAuditLog() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Timestamp getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getFunctionCall() {
		return this.functionCall;
	}

	public void setFunctionCall(String functionCall) {
		this.functionCall = functionCall;
	}

	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return this.remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getRemoteUser() {
		return this.remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}