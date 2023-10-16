package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the centre_exam_status_master database table.
 * 
 */
@Entity
@Table(name="centre_exam_status_master")
@NamedQuery(name="CentreExamStatusMaster.findAll", query="SELECT c FROM CentreExamStatusMaster c")
@JsonIgnoreProperties({"centreMasters"})
public class CentreExamStatusMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CENTREEXAMSTATUSMASTERID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CENTREEXAMSTATUSMASTERID_GENERATOR")
	@Column(name="centre_exam_status_master_id")
	private Integer centreExamStatusMasterId;

	@Column(name="centre_exam_status")
	private String centreExamStatus;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="centreExamStatusMaster")
	private List<CentreMaster> centreMasters;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	public CentreExamStatusMaster() {
	}

	public Integer getCentreExamStatusMasterId() {
		return this.centreExamStatusMasterId;
	}

	public void setCentreExamStatusMasterId(Integer centreExamStatusMasterId) {
		this.centreExamStatusMasterId = centreExamStatusMasterId;
	}

	public String getCentreExamStatus() {
		return this.centreExamStatus;
	}

	public void setCentreExamStatus(String centreExamStatus) {
		this.centreExamStatus = centreExamStatus;
	}

	public List<CentreMaster> getCentreMasters() {
		return this.centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public CentreMaster addCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().add(centreMaster);
		centreMaster.setCentreExamStatusMaster(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setCentreExamStatusMaster(null);

		return centreMaster;
	}

}