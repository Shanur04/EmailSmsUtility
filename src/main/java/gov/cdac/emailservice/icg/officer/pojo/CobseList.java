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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the cobse_list database table.
 * 
 */
@Entity
@Table(name="cobse_list")
@NamedQuery(name="CobseList.findAll", query="SELECT c FROM CobseList c")
public class CobseList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COBSELISTBOARDID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COBSELISTBOARDID_GENERATOR")
	@Column(name="board_id")
	private Integer boardId;

	@NotBlank(message = "Board Name cannot be blank")
	@Column(name="board_name")
	private String boardName;

	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@NotNull(message = "Status cannot be blank" )
	@Column(name="status")
	private Boolean status;

	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="system_user_credentials_id")
	private SystemUserCredential systemUserCredential;

	public CobseList() {
	}

	public Integer getBoardId() {
		return this.boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public SystemUserCredential getSystemUserCredential() {
		return this.systemUserCredential;
	}

	public void setSystemUserCredential(SystemUserCredential systemUserCredential) {
		this.systemUserCredential = systemUserCredential;
	}

}