package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rps_centre_ignored_result database table.
 * 
 */
@Entity
@Table(name="rps_centre_ignored_result")
@NamedQuery(name="RpsCentreIgnoredResult.findAll", query="SELECT r FROM RpsCentreIgnoredResult r")
public class RpsCentreIgnoredResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="centre_code", length=255)
	private String centreCode;

	@Column(name="ci_username", length=255)
	private String ciUsername;

	@Column(name="exam_code", length=255)
	private String examCode;

	@Column(name="exam_slot_id")
	private Long examSlotId;

	@Column(length=255)
	private String remarks;

	public RpsCentreIgnoredResult() {
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

	public String getCiUsername() {
		return this.ciUsername;
	}

	public void setCiUsername(String ciUsername) {
		this.ciUsername = ciUsername;
	}

	public String getExamCode() {
		return this.examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Long getExamSlotId() {
		return this.examSlotId;
	}

	public void setExamSlotId(Long examSlotId) {
		this.examSlotId = examSlotId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}