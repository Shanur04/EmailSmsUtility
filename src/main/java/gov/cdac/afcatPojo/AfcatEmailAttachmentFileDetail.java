package gov.cdac.afcatPojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "email_Attachment_File_detail")
@NamedQuery(name = "AfcatEmailAttachmentFileDetail.findAll", query = "SELECT e FROM AfcatEmailAttachmentFileDetail e")
public class AfcatEmailAttachmentFileDetail {

	@Id
	@SequenceGenerator(name = "email_Attachment_File_detail_email_Attachment_File_detail_id_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_Attachment_File_detail_email_Attachment_File_detail_id_SEQ")
	@Column(name = "email_Attachment_File_detail_id")
	private Long emailAttachmnetFileDetailId;
	
	@Column(name="file_path")
	private String filePath;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="email_sent_id", referencedColumnName="email_sent_id")	
	private AfcatEmailSent emailSent;
	

	public Long getEmailAttachmnetFileDetailId() {
		return emailAttachmnetFileDetailId;
	}

	public void setEmailAttachmnetFileDetailId(Long emailAttachmnetFileDetailId) {
		this.emailAttachmnetFileDetailId = emailAttachmnetFileDetailId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public AfcatEmailSent getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(AfcatEmailSent emailSent) {
		this.emailSent = emailSent;
	}

}