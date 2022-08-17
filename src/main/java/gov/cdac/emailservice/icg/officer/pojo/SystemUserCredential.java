package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the system_user_credentials database table.
 * 
 */
@Entity
@Table(name="system_user_credentials")
@NamedQuery(name="SystemUserCredential.findAll", query="SELECT s FROM SystemUserCredential s")
@JsonIgnoreProperties({"centreMasters","cobseLists","regionRegionheadmappings","atcMasters","centreUserMappings","feedbackResponses"})
public class SystemUserCredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYSTEMUSERCREDID_GENERATOR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYSTEMUSERCREDID_GENERATOR")
	@Column(name="system_user_cred_id")
	private Integer systemUserCredId;
	
	private String address;

	@Column(name="createdBy")
	private Integer createdBy;

	@Column(name="description")
	private String description;
	
	private String designation;

	@Column(name="email")
	private String email;
	
	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamMaster examMaster;
	

	@Column(name="mobile")
	private String mobile;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="password_reset_status")
	private Boolean passwordResetStatus;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="status")
	private Boolean status;

	//bi-directional many-to-one association to CentreMaster
	
	 @OneToMany(mappedBy="systemUserCredential") 
	 private List<CentreMaster> centreMasters;
	 

	//bi-directional many-to-one association to CobseList
	@OneToMany(mappedBy="systemUserCredential")
	private List<CobseList> cobseLists;
	

	//bi-directional many-to-one association to AtcMaster
	@OneToMany(mappedBy="systemUserCredential")
	private List<AtcMaster> atcMasters;

	//bi-directional many-to-one association to CentreUserMapping
	@OneToMany(mappedBy="systemUserCredential")
	private List<CentreUserMapping> centreUserMappings;
	
	//bi-directional many-to-one association to RegionRegionheadmapping
	@OneToMany(mappedBy="systemUserCredential")
	private List<RegionRegionheadmapping> regionRegionheadmappings;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="role_id")
	private UserRole userRole;
	
	//bi-directional many-to-one association to FeedbackRespons
	@OneToMany(mappedBy="systemUserCredential")
	private List<FeedbackRespons> feedbackResponses;

	public SystemUserCredential() {
	}

	public SystemUserCredential(Integer systemUserCredId) {
		this.systemUserCredId = systemUserCredId;
	}

	public Integer getSystemUserCredId() {
		return this.systemUserCredId;
	}

	public void setSystemUserCredId(Integer systemUserCredId) {
		this.systemUserCredId = systemUserCredId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPasswordResetStatus() {
		return this.passwordResetStatus;
	}

	public void setPasswordResetStatus(Boolean passwordResetStatus) {
		this.passwordResetStatus = passwordResetStatus;
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

	/*
	 * public List<CentreMaster> getCentreMasters() { return this.centreMasters; }
	 * 
	 * public void setCentreMasters(List<CentreMaster> centreMasters) {
	 * this.centreMasters = centreMasters; }
	 */

	/*
	 * public CentreMaster addCentreMaster(CentreMaster centreMaster) {
	 * getCentreMasters().add(centreMaster);
	 * centreMaster.setSystemUserCredential(this);
	 * 
	 * return centreMaster; }
	 * 
	 * public CentreMaster removeCentreMaster(CentreMaster centreMaster) {
	 * getCentreMasters().remove(centreMaster);
	 * centreMaster.setSystemUserCredential(null);
	 * 
	 * return centreMaster; }
	 */

	public List<CobseList> getCobseLists() {
		return this.cobseLists;
	}

	public void setCobseLists(List<CobseList> cobseLists) {
		this.cobseLists = cobseLists;
	}

	public CobseList addCobseList(CobseList cobseList) {
		getCobseLists().add(cobseList);
		cobseList.setSystemUserCredential(this);

		return cobseList;
	}

	public CobseList removeCobseList(CobseList cobseList) {
		getCobseLists().remove(cobseList);
		cobseList.setSystemUserCredential(null);

		return cobseList;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public List<RegionRegionheadmapping> getRegionRegionheadmappings() {
		return this.regionRegionheadmappings;
	}

	public void setRegionRegionheadmappings(List<RegionRegionheadmapping> regionRegionheadmappings) {
		this.regionRegionheadmappings = regionRegionheadmappings;
	}

	public RegionRegionheadmapping addRegionRegionheadmapping(RegionRegionheadmapping regionRegionheadmapping) {
		getRegionRegionheadmappings().add(regionRegionheadmapping);
		regionRegionheadmapping.setSystemUserCredential(this);

		return regionRegionheadmapping;
	}

	public RegionRegionheadmapping removeRegionRegionheadmapping(RegionRegionheadmapping regionRegionheadmapping) {
		getRegionRegionheadmappings().remove(regionRegionheadmapping);
		regionRegionheadmapping.setSystemUserCredential(null);

		return regionRegionheadmapping;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public ExamMaster getExamMaster() {
		return examMaster;
	}

	public void setExamMaster(ExamMaster examMaster) {
		this.examMaster = examMaster;
	}

	public List<CentreMaster> getCentreMasters() {
		return centreMasters;
	}

	public void setCentreMasters(List<CentreMaster> centreMasters) {
		this.centreMasters = centreMasters;
	}

	public List<AtcMaster> getAtcMasters() {
		return atcMasters;
	}

	public void setAtcMasters(List<AtcMaster> atcMasters) {
		this.atcMasters = atcMasters;
	}

	public List<CentreUserMapping> getCentreUserMappings() {
		return centreUserMappings;
	}

	public void setCentreUserMappings(List<CentreUserMapping> centreUserMappings) {
		this.centreUserMappings = centreUserMappings;
	}

	public List<FeedbackRespons> getFeedbackResponses() {
		return feedbackResponses;
	}

	public void setFeedbackResponses(List<FeedbackRespons> feedbackResponses) {
		this.feedbackResponses = feedbackResponses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}