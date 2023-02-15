package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @author shanurj
 *
 */
/**
 * The persistent class for the centre_user_mapping database table.
 * 
 */
@Entity
@Table(name="centre_user_mapping")
@NamedQuery(name="CentreUserMapping.findAll", query="SELECT c FROM CentreUserMapping c")
public class CentreUserMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CENTREUSERMAPPINGID_GENERATOR",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CENTREUSERMAPPINGID_GENERATOR")
	@Column(name="centre_user_mapping_id")
	private Integer centreUserMappingId;

	@Column(name="centre_status")
	private Boolean centreStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to CentreMaster
	@ManyToOne
	@JoinColumn(name="centre_id")
	private CentreMaster centreMaster;
	
	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="system_user_cred_id")
	private SystemUserCredential systemUserCredential;
		
	public CentreUserMapping() {
	}

	public Integer getCentreUserMappingId() {
		return this.centreUserMappingId;
	}

	public void setCentreUserMappingId(Integer centreUserMappingId) {
		this.centreUserMappingId = centreUserMappingId;
	}

	public Boolean getCentreStatus() {
		return this.centreStatus;
	}

	public void setCentreStatus(Boolean centreStatus) {
		this.centreStatus = centreStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public CentreMaster getCentreMaster() {
		return this.centreMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	

}