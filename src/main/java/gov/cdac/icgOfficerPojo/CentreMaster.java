package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

 
/**
 * The persistent class for the centre_master database table.
 * 
 */
@Entity
@Table(name="centre_master")
@NamedQuery(name="CentreMaster.findAll", query="SELECT c FROM CentreMaster c")
@JsonIgnoreProperties({"centreExamslotMappings","centreUserMappings","regionRegionheadCentreMappings","feedbackResponses"})
public class CentreMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CENTREID_GENERATOR",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CENTREID_GENERATOR")
	@Column(name="centre_id")
	private Integer centreId;

	@Column(name="centre_address")
	private String centreAddress;

	@Column(name="centre_code")
	private String centreCode;

	@Column(name="centre_contact_emailid")
	private String centreContactEmailid;
	
	@Column(name="centre_google_mapurl")
	private String centreGoogleMapurl;

	@Column(name="centre_landmark")
	private String centreLandmark;

	@Column(name="centre_name")
	private String centreName;

	@Column(name="centre_pincode")
	private String centrePincode;

	@Column(name="centre_status")
	private Boolean centreStatus;

	@Column(name="centre_totalcapacity")
	private Integer centreTotalcapacity;

	@Column(name="contact_number")
	private String contactNumber;

	@Column(name="contact_person_name")
	private String contactPersonName;

	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	//bi-directional many-to-one association to RegionRegionheadCentreMapping
	@OneToMany(mappedBy="centreMaster")
	private List<RegionRegionheadCentreMapping> regionRegionheadCentreMappings;

	//bi-directional many-to-one association to CentreExamslotMapping
	@OneToMany(mappedBy="centreMaster")
	private List<CentreExamslotMapping> centreExamslotMappings;
	
	//bi-directional many-to-one association to FeedbackRespons
	@OneToMany(mappedBy="centreMaster")
	private List<FeedbackRespons> feedbackResponses;

	//bi-directional many-to-one association to CentreUserMapping
	@OneToMany(mappedBy="centreMaster")
	private List<CentreUserMapping> centreUserMappings;
	
	//bi-directional many-to-one association to SystemUserCredential
	@ManyToOne
	@JoinColumn(name="centre_created_by")
	private SystemUserCredential systemUserCredential;
	
	//bi-directional many-to-one association to AtcMaster
	@ManyToOne
	@JoinColumn(name="responsible_atc_centre")
	private AtcMaster atcMaster;

	//bi-directional many-to-one association to CentreExamStatusMaster
	@ManyToOne
	@JoinColumn(name="centre_exam_status_master_id")
	private CentreExamStatusMaster centreExamStatusMaster;

	//bi-directional many-to-one association to ExamCityMaster
	@ManyToOne
	@JoinColumn(name="centre_city_id")
	private ExamCityMaster examCityMaster;

	//bi-directional many-to-one association to ExamMaster
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;

	//bi-directional many-to-one association to ExamStateMaster
	@ManyToOne
	@JoinColumn(name="centre_state_id")
	private ExamStateMaster examStateMaster;

	

	public CentreMaster() {
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

	public ExamMaster getExamMaster() {
		return this.examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
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

	public List<CentreUserMapping> getCentreUserMappings() {
		return this.centreUserMappings;
	}

	public void setCentreUserMappings(List<CentreUserMapping> centreUserMappings) {
		this.centreUserMappings = centreUserMappings;
	}

	public CentreUserMapping addCentreUserMapping(CentreUserMapping centreUserMapping) {
		getCentreUserMappings().add(centreUserMapping);
		centreUserMapping.setCentreMaster(this);

		return centreUserMapping;
	}

	public CentreUserMapping removeCentreUserMapping(CentreUserMapping centreUserMapping) {
		getCentreUserMappings().remove(centreUserMapping);
		centreUserMapping.setCentreMaster(null);

		return centreUserMapping;
	}
	
	public List<RegionRegionheadCentreMapping> getRegionRegionheadCentreMappings() {
		return this.regionRegionheadCentreMappings;
	}

	public void setRegionRegionheadCentreMappings(List<RegionRegionheadCentreMapping> regionRegionheadCentreMappings) {
		this.regionRegionheadCentreMappings = regionRegionheadCentreMappings;
	}

	public RegionRegionheadCentreMapping addRegionRegionheadCentreMapping(RegionRegionheadCentreMapping regionRegionheadCentreMapping) {
		getRegionRegionheadCentreMappings().add(regionRegionheadCentreMapping);
		regionRegionheadCentreMapping.setCentreMaster(this);

		return regionRegionheadCentreMapping;
	}

	public RegionRegionheadCentreMapping removeRegionRegionheadCentreMapping(RegionRegionheadCentreMapping regionRegionheadCentreMapping) {
		getRegionRegionheadCentreMappings().remove(regionRegionheadCentreMapping);
		regionRegionheadCentreMapping.setCentreMaster(null);

		return regionRegionheadCentreMapping;
	}

	public AtcMaster getAtcMaster() {
		return atcMaster;
	}

	public void setAtcMaster(AtcMaster atcMaster) {
		this.atcMaster = atcMaster;
	}

	public List<FeedbackRespons> getFeedbackResponses() {
		return feedbackResponses;
	}

	public void setFeedbackResponses(List<FeedbackRespons> feedbackResponses) {
		this.feedbackResponses = feedbackResponses;
	}
	

}