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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the centre_master database table.
 * 
 */
@Entity
@Table(name = "centre_master")
@NamedQuery(name = "CentreMaster.findAll", query = "SELECT c FROM CentreMaster c")
@JsonIgnoreProperties({ "centreExamslotMappings","centreExamStatusMaster","systemUserCredential" })
public class CentreMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CENTREID_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CENTREID_GENERATOR")
	@Column(name = "centre_id")
	private Integer centreId;

	@Column(name = "centre_address")
	private String centreAddress;

	@Column(name = "centre_code")
	private String centreCode;

	@Column(name = "centre_contact_emailid")
	private String centreContactEmailid;

	@Column(name = "centre_google_mapurl")
	private String centreGoogleMapurl;

	@Column(name = "centre_landmark")
	private String centreLandmark;

	@Column(name = "centre_name")
	private String centreName;

	@Column(name = "centre_pincode")
	private String centrePincode;

	@Column(name = "centre_status")
	private Boolean centreStatus;

	@Column(name = "centre_totalcapacity")
	private Integer centreTotalcapacity;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "contact_person_name")
	private String contactPersonName;

	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	// bi-directional many-to-one association to CentreExamslotMapping
	@OneToMany(mappedBy = "centreMaster")
	private List<CentreExamslotMapping> centreExamslotMappings;

	// bi-directional many-to-one association to CentreExamStatusMaster

	@ManyToOne
	@JoinColumn(name = "centre_exam_status_master_id")
	private CentreExamStatusMaster centreExamStatusMaster;

	// bi-directional many-to-one association to ExamCityMaster
	@ManyToOne
	@JoinColumn(name = "centre_city_id")
	private ExamCityMaster examCityMaster;

	// bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name = "centre_state_id")
	private ExamStateMaster examStateMaster;

	// bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name = "centre_created_by")
	private SystemUserCredential systemUserCredential;

	// bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private ExamMaster examMaster;

	@Transient
	private int examstateMasterId;

	@Transient
	private int examcityMasterId;

	public CentreMaster() {
	}

	public CentreMaster(int centreId) {
		this.centreId = centreId;
	}

	public ExamMaster getExamMaster() {
		return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public Integer getCentreId() {
		return this.centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public String getCentreAddress() {
		return this.centreAddress;
	}

	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}

	public String getCentreCode() {
		return this.centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCentreContactEmailid() {
		return this.centreContactEmailid;
	}

	public void setCentreContactEmailid(String centreContactEmailid) {
		this.centreContactEmailid = centreContactEmailid;
	}

	public String getCentreGoogleMapurl() {
		return this.centreGoogleMapurl;
	}

	public void setCentreGoogleMapurl(String centreGoogleMapurl) {
		this.centreGoogleMapurl = centreGoogleMapurl;
	}

	public String getCentreLandmark() {
		return this.centreLandmark;
	}

	public void setCentreLandmark(String centreLandmark) {
		this.centreLandmark = centreLandmark;
	}

	public String getCentreName() {
		return this.centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getCentrePincode() {
		return this.centrePincode;
	}

	public void setCentrePincode(String centrePincode) {
		this.centrePincode = centrePincode;
	}

	public Boolean getCentreStatus() {
		return this.centreStatus;
	}

	public void setCentreStatus(Boolean centreStatus) {
		this.centreStatus = centreStatus;
	}

	public Integer getCentreTotalcapacity() {
		return this.centreTotalcapacity;
	}

	public void setCentreTotalcapacity(Integer centreTotalcapacity) {
		this.centreTotalcapacity = centreTotalcapacity;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<CentreExamslotMapping> getCentreExamslotMappings() {
		return this.centreExamslotMappings;
	}

	public void setCentreExamslotMappings(List<CentreExamslotMapping> centreExamslotMappings) {
		this.centreExamslotMappings = centreExamslotMappings;
	}

	public CentreExamslotMapping addCentreExamslotMapping(CentreExamslotMapping centreExamslotMapping) {
		getCentreExamslotMappings().add(centreExamslotMapping);
		centreExamslotMapping.setCentreMaster(this);

		return centreExamslotMapping;
	}

	public CentreExamslotMapping removeCentreExamslotMapping(CentreExamslotMapping centreExamslotMapping) {
		getCentreExamslotMappings().remove(centreExamslotMapping);
		centreExamslotMapping.setCentreMaster(null);

		return centreExamslotMapping;
	}

	public CentreExamStatusMaster getCentreExamStatusMaster() {
		return this.centreExamStatusMaster;
	}

	public void setCentreExamStatusMaster(CentreExamStatusMaster centreExamStatusMaster) {
		this.centreExamStatusMaster = centreExamStatusMaster;
	}

	public ExamCityMaster getExamCityMaster() {
		return this.examCityMaster;
	}

	public void setExamCityMaster(ExamCityMaster examCityMaster) {
		this.examCityMaster = examCityMaster;
	}

	public ExamStateMaster getExamStateMaster() {
		return this.examStateMaster;
	}

	public void setExamStateMaster(ExamStateMaster examStateMaster) {
		this.examStateMaster = examStateMaster;
	}

	public SystemUserCredential getSystemUserCredential() {
		return this.systemUserCredential;
	}

	public void setSystemUserCredential(SystemUserCredential systemUserCredential) {
		this.systemUserCredential = systemUserCredential;
	}

	public int getExamstateMasterId() {
		return examstateMasterId;
	}

	public void setExamstateMasterId(int examstateMasterId) {
		this.examstateMasterId = examstateMasterId;
	}

	public int getExamcityMasterId() {
		return examcityMasterId;
	}

	public void setExamcityMasterId(int examcityMasterId) {
		this.examcityMasterId = examcityMasterId;
	}

	@Override
	public String toString() {
		return "CentreMaster [centreId=" + centreId + ", centreAddress=" + centreAddress + ", centreCode=" + centreCode
				+ ", centreContactEmailid=" + centreContactEmailid + ", centreGoogleMapurl=" + centreGoogleMapurl
				+ ", centreLandmark=" + centreLandmark + ", centreName=" + centreName + ", centrePincode="
				+ centrePincode + ", centreStatus=" + centreStatus + ", centreTotalcapacity=" + centreTotalcapacity
				+ ", contactNumber=" + contactNumber + ", contactPersonName=" + contactPersonName + ", recordTracking="
				+ recordTracking + ", centreExamslotMappings=" + centreExamslotMappings + ", examCityMaster="
				+ examCityMaster + ", examStateMaster=" + examStateMaster + ", systemUserCredential="
				+ systemUserCredential + ", examMaster=" + examMaster + ", examstateMasterId=" + examstateMasterId
				+ ", examcityMasterId=" + examcityMasterId + "]";
	}

}