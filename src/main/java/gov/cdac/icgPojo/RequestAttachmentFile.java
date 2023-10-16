package gov.cdac.icgPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the request_attachment_files database table.
 * 
 */
@Entity
@Table(name="request_attachment_files")
@NamedQuery(name="RequestAttachmentFile.findAll", query="SELECT r FROM RequestAttachmentFile r")
public class RequestAttachmentFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "REQUEST_ATTACHMENT_FILES_REQUEST_ATTACHMENT_FILES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_ATTACHMENT_FILES_REQUEST_ATTACHMENT_FILES_ID_SEQ")
	@Column(name="request_attachment_files_id")
	private Integer requestAttachmentFilesId;

	@Column(name="atttachment_path")
	private String atttachmentPath;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to RequestDetail
	@ManyToOne
	@JoinColumn(name="request_details_id")
	private RequestDetail requestDetail;

	public RequestAttachmentFile() {
	}

	public Integer getRequestAttachmentFilesId() {
		return this.requestAttachmentFilesId;
	}

	public void setRequestAttachmentFilesId(Integer requestAttachmentFilesId) {
		this.requestAttachmentFilesId = requestAttachmentFilesId;
	}

	public String getAtttachmentPath() {
		return this.atttachmentPath;
	}

	public void setAtttachmentPath(String atttachmentPath) {
		this.atttachmentPath = atttachmentPath;
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

	public RequestDetail getRequestDetail() {
		return this.requestDetail;
	}

	public void setRequestDetail(RequestDetail requestDetail) {
		this.requestDetail = requestDetail;
	}

}