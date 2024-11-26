package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rps_marked_for_deletion database table.
 * 
 */
@Entity
@Table(name="rps_marked_for_deletion")
@NamedQuery(name="RpsMarkedForDeletion.findAll", query="SELECT r FROM RpsMarkedForDeletion r")
public class RpsMarkedForDeletion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="coe_status", length=255)
	private String coeStatus;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="fk_exam_slot_id")
	private Long fkExamSlotId;

	@Column(name="hall_ticket_number", length=255)
	private String hallTicketNumber;

	@Column(name="session_id", length=255)
	private String sessionId;

	public RpsMarkedForDeletion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCoeStatus() {
		return this.coeStatus;
	}

	public void setCoeStatus(String coeStatus) {
		this.coeStatus = coeStatus;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Long getFkExamSlotId() {
		return this.fkExamSlotId;
	}

	public void setFkExamSlotId(Long fkExamSlotId) {
		this.fkExamSlotId = fkExamSlotId;
	}

	public String getHallTicketNumber() {
		return this.hallTicketNumber;
	}

	public void setHallTicketNumber(String hallTicketNumber) {
		this.hallTicketNumber = hallTicketNumber;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}