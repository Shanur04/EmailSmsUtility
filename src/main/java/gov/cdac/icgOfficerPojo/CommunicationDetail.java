package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.ColumnTransformer;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the communication_details database table.
 * 
 */
@Entity
@Table(name="communication_details")
@NamedQuery(name="CommunicationDetail.findAll", query="SELECT c FROM CommunicationDetail c")
public class CommunicationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMMUNICATIONDETAILSID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMUNICATIONDETAILSID_GENERATOR")
	@Column(name="communication_details_id")
	private Long communicationDetailsId;

	@ColumnTransformer(forColumn = "correspondance_address", read = "pgp_sym_decrypt(correspondance_address::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",
			write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="correspondance_address")
	private String correspondanceAddress;

	@ColumnTransformer(forColumn = "correspondance_alternate_mobile", read = "pgp_sym_decrypt(correspondance_alternate_mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",
			write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="correspondance_alternate_mobile")
	private String correspondanceAlternateMobile;

	//bi-directional many-to-one association to DistrictMaster
	@ManyToOne
	@JoinColumn(name="correspondance_lgd_district_code")
	private DistrictMaster correspondanceLgdDistrictMaster;

	@ColumnTransformer(forColumn = "correspondance_landmark", read = "pgp_sym_decrypt(correspondance_landmark::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="correspondance_landmark")
	private String correspondanceLandmark;

	@ColumnTransformer(forColumn = "correspondance_pincode", read = "pgp_sym_decrypt(correspondance_pincode::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="correspondance_pincode")
	private String correspondancePincode;

	@ColumnTransformer(forColumn = "correspondance_post", read = "pgp_sym_decrypt(correspondance_post::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="correspondance_post")
	private String correspondancePost;

	//bi-directional many-to-one association to StateMaster
	@ManyToOne
	@JoinColumn(name="correspondance_lgd_state_code")
	private StateMaster correspondanceLgdStateMaster;

	//bi-directional many-to-one association to SubDistrictMaster
	@ManyToOne
	@JoinColumn(name="correspondance_lgd_sub_district_code")
	private SubDistrictMaster correspondanceLgdSubDistrictMaster;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_of_issue_domicile_certificate")
	private Date dateOfIssueDomicileCertificate;
	
	
	/*
	 * @Column(name="date_of_issue_domicile_certificate") private Timestamp
	 * dateOfIssueDomicileCertificate;
	 */

	@Column(name="is_height_relaxation_availed")
	private Boolean isHeightRelaxationAvailed;
	
	@Column(name="domicile_certificate_path")
	private String domicileCertificatePath;

	@Column(name="is_correspondence_same_as_permanent")
	private Boolean isCorrespondenceSameAsPermanent;

	@ColumnTransformer(forColumn = "permanent_address", read = "pgp_sym_decrypt(permanent_address::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",
			write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="permanent_address")
	private String permanentAddress;

	@ColumnTransformer(forColumn = "permanent_alternate_mobile", read = "pgp_sym_decrypt(permanent_alternate_mobile::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",
			write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="permanent_alternate_mobile")
	private String permanentAlternateMobile;

	//bi-directional many-to-one association to DistrictMaster
	@ManyToOne
	@JoinColumn(name="permanent_lgd_district_code")
	private DistrictMaster permanantLgdDistrictMaster;

	@ColumnTransformer(forColumn = "permanent_landmark", read = "pgp_sym_decrypt(permanent_landmark::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="permanent_landmark")
	private String permanentLandmark;

	@ColumnTransformer(forColumn = "permanent_pincode", read = "pgp_sym_decrypt(permanent_pincode::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="permanent_pincode")
	private String permanentPincode;

	@ColumnTransformer(forColumn = "permanent_post", read = "pgp_sym_decrypt(permanent_post::bytea, 'icgregkey','compress-algo=0, cipher-algo=aes256')",write = "pgp_sym_encrypt(?, 'icgregkey','compress-algo=0, cipher-algo=aes256')")
	@Column(name="permanent_post")
	private String permanentPost;

	//bi-directional many-to-one association to StateMaster
	@ManyToOne
	@JoinColumn(name="permanent_lgd_state_code")
	private StateMaster permanentLgdStateMaster;

	//bi-directional many-to-one association to SubDistrictMaster
	@ManyToOne
	@JoinColumn(name="permanent_lgd_sub_district_code")
	private SubDistrictMaster permanentLgdSubDistrictMaster;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="domicile_state")
	private RecruitmentZoneState recruitmentZoneState;
	
	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	public CommunicationDetail() {
	}
	
	public Boolean getIsHeightRelaxationAvailed() {
		return isHeightRelaxationAvailed;
	}

	public void setIsHeightRelaxationAvailed(Boolean isHeightRelaxationAvailed) {
		this.isHeightRelaxationAvailed = isHeightRelaxationAvailed;
	}

	public Long getCommunicationDetailsId() {
		return this.communicationDetailsId;
	}

	public void setCommunicationDetailsId(Long communicationDetailsId) {
		this.communicationDetailsId = communicationDetailsId;
	}

	public String getCorrespondanceAddress() {
		return this.correspondanceAddress;
	}

	public void setCorrespondanceAddress(String correspondanceAddress) {
		this.correspondanceAddress = correspondanceAddress;
	}

	public String getCorrespondanceAlternateMobile() {
		return this.correspondanceAlternateMobile;
	}

	public void setCorrespondanceAlternateMobile(String correspondanceAlternateMobile) {
		this.correspondanceAlternateMobile = correspondanceAlternateMobile;
	}

	public String getCorrespondanceLandmark() {
		return this.correspondanceLandmark;
	}

	public void setCorrespondanceLandmark(String correspondanceLandmark) {
		this.correspondanceLandmark = correspondanceLandmark;
	}

	public String getCorrespondancePincode() {
		return this.correspondancePincode;
	}

	public void setCorrespondancePincode(String correspondancePincode) {
		this.correspondancePincode = correspondancePincode;
	}

	public String getCorrespondancePost() {
		return this.correspondancePost;
	}

	public void setCorrespondancePost(String correspondancePost) {
		this.correspondancePost = correspondancePost;
	}


	public String getDomicileCertificatePath() {
		return this.domicileCertificatePath;
	}

	public Date getDateOfIssueDomicileCertificate() {
		return dateOfIssueDomicileCertificate;
	}

	public void setDateOfIssueDomicileCertificate(Date dateOfIssueDomicileCertificate) {
		this.dateOfIssueDomicileCertificate = dateOfIssueDomicileCertificate;
	}

	public void setDomicileCertificatePath(String domicileCertificatePath) {
		this.domicileCertificatePath = domicileCertificatePath;
	}

	
	public Boolean getIsCorrespondenceSameAsPermanent() {
		return this.isCorrespondenceSameAsPermanent;
	}

	public void setIsCorrespondenceSameAsPermanent(Boolean isPermanentCorrespAddressSame) {
		this.isCorrespondenceSameAsPermanent = isPermanentCorrespAddressSame;
	}

	public String getPermanentAddress() {
		return this.permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentAlternateMobile() {
		return this.permanentAlternateMobile;
	}

	public void setPermanentAlternateMobile(String permanentAlternateMobile) {
		this.permanentAlternateMobile = permanentAlternateMobile;
	}

	

	public String getPermanentLandmark() {
		return this.permanentLandmark;
	}

	public void setPermanentLandmark(String permanentLandmark) {
		this.permanentLandmark = permanentLandmark;
	}

	public String getPermanentPincode() {
		return this.permanentPincode;
	}

	public void setPermanentPincode(String permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPermanentPost() {
		return this.permanentPost;
	}

	public void setPermanentPost(String permanentPost) {
		this.permanentPost = permanentPost;
	}

	
	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public RecruitmentZoneState getRecruitmentZoneState() {
		return recruitmentZoneState;
	}

	public void setRecruitmentZoneState(RecruitmentZoneState recruitmentZoneState) {
		this.recruitmentZoneState = recruitmentZoneState;
	}

	public DistrictMaster getCorrespondanceLgdDistrictMaster() {
		return correspondanceLgdDistrictMaster;
	}

	public void setCorrespondanceLgdDistrictMaster(DistrictMaster correspondanceLgdDistrictMaster) {
		this.correspondanceLgdDistrictMaster = correspondanceLgdDistrictMaster;
	}

	public StateMaster getCorrespondanceLgdStateMaster() {
		return correspondanceLgdStateMaster;
	}

	public void setCorrespondanceLgdStateMaster(StateMaster correspondanceLgdStateMaster) {
		this.correspondanceLgdStateMaster = correspondanceLgdStateMaster;
	}

	public SubDistrictMaster getCorrespondanceLgdSubDistrictMaster() {
		return correspondanceLgdSubDistrictMaster;
	}

	public void setCorrespondanceLgdSubDistrictMaster(SubDistrictMaster correspondanceLgdSubDistrictMaster) {
		this.correspondanceLgdSubDistrictMaster = correspondanceLgdSubDistrictMaster;
	}

	public DistrictMaster getPermanantLgdDistrictMaster() {
		return permanantLgdDistrictMaster;
	}

	public void setPermanantLgdDistrictMaster(DistrictMaster permanantLgdDistrictMaster) {
		this.permanantLgdDistrictMaster = permanantLgdDistrictMaster;
	}

	public StateMaster getPermanentLgdStateMaster() {
		return permanentLgdStateMaster;
	}

	public void setPermanentLgdStateMaster(StateMaster permanentLgdStateMaster) {
		this.permanentLgdStateMaster = permanentLgdStateMaster;
	}

	public SubDistrictMaster getPermanentLgdSubDistrictMaster() {
		return permanentLgdSubDistrictMaster;
	}

	public void setPermanentLgdSubDistrictMaster(SubDistrictMaster permanentLgdSubDistrictMaster) {
		this.permanentLgdSubDistrictMaster = permanentLgdSubDistrictMaster;
	}

}