package gov.cdac.casbPojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_Attachment_File_detail")
@NamedQuery(name = "CasbEmailAttachmentFileDetail.findAll", query = "SELECT e FROM CasbEmailAttachmentFileDetail e")
public class CasbEmailAttachmentFileDetail {
	@Id
	@SequenceGenerator(name = "email_Attachment_File_detail_email_Attachment_File_detail_id_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_Attachment_File_detail_email_Attachment_File_detail_id_SEQ")
	@Column(name = "email_Attachment_File_detail_id")
	private Long emailAttachmnetFileDetailId;
	
	@Column(name="file_path")
	private String filePath;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="email_sent_id", referencedColumnName="email_sent_id")	
	private CasbEmailSent emailSent;
	

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

	public CasbEmailSent getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(CasbEmailSent emailSent) {
		this.emailSent = emailSent;
	}


}
