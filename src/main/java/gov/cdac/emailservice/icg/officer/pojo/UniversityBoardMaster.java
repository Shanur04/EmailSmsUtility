package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the university_board_master database table.
 * 
 */
@Entity
@Table(name="university_board_master")
@NamedQuery(name="UniversityBoardMaster.findAll", query="SELECT u FROM UniversityBoardMaster u")
public class UniversityBoardMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNIVERSITY_BOARD_MASTER_UNIVERSITYBOARDMASTERID_GENERATOR",allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIVERSITY_BOARD_MASTER_UNIVERSITYBOARDMASTERID_GENERATOR")
	@Column(name="university_board_master_id")
	private Integer universityBoardMasterId;
	
	@NotNull(message = "University Master Status cannot be blank!" )
	@Column(name="status")
	private Boolean status;
	
	@NotBlank(message = "University Or Board Name cannot be blank!")
	@Column(name="university_or_board_name")
	private String universityOrBoardName;
	
	@UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public UniversityBoardMaster() {
	}

	public Integer getUniversityBoardMasterId() {
		return this.universityBoardMasterId;
	}

	public void setUniversityBoardMasterId(Integer universityBoardMasterId) {
		this.universityBoardMasterId = universityBoardMasterId;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUniversityOrBoardName() {
		return this.universityOrBoardName;
	}

	public void setUniversityOrBoardName(String universityOrBoardName) {
		this.universityOrBoardName = universityOrBoardName;
	}

}