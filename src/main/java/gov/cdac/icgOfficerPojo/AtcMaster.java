package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the atc_master database table.
 * 
 */
@Entity
@Table(name="atc_master")
@NamedQuery(name="AtcMaster.findAll", query="SELECT a FROM AtcMaster a")
@JsonIgnoreProperties({"centreMasters"})
public class AtcMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATC_MASTER_ATCMASTERID_GENERATOR" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATC_MASTER_ATCMASTERID_GENERATOR")
	@Column(name="atc_master_id")
	private Integer atcMasterId;

	@Column(name="atc_code")
	private String atcCode;

	@Column(name="atc_name")
	private String atcName;

	@Column(name="record_traking")
	private Timestamp recordTraking;

	private Boolean status;

	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="system_user_cred_id")
	private SystemUserCredential systemUserCredential;

	//bi-directional many-to-one association to CentreMaster
	@OneToMany(mappedBy="atcMaster")
	private List<CentreMaster> centreMasters;

	public AtcMaster() {
	}

	public Integer getAtcMasterId() {
		return this.atcMasterId;
	}

	public void setAtcMasterId(Integer atcMasterId) {
		this.atcMasterId = atcMasterId;
	}

	public String getAtcCode() {
		return this.atcCode;
	}

	public void setAtcCode(String atcCode) {
		this.atcCode = atcCode;
	}

	public String getAtcName() {
		return this.atcName;
	}

	public void setAtcName(String atcName) {
		this.atcName = atcName;
	}

	public Timestamp getRecordTraking() {
		return this.recordTraking;
	}

	public void setRecordTraking(Timestamp recordTraking) {
		this.recordTraking = recordTraking;
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

	public List<CentreMaster> getCentreMasters() {
		return this.centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public CentreMaster addCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().add(centreMaster);
		centreMaster.setAtcMaster(this);

		return centreMaster;
	}

	public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
		getCentreMasters().remove(centreMaster);
		centreMaster.setAtcMaster(null);

		return centreMaster;
	}

}