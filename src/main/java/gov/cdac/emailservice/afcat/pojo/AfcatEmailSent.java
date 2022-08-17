package gov.cdac.emailservice.afcat.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * The persistent class for the email_sent database table.
 * 
 */
@Entity
@Table(name="email_sent")
@NamedQuery(name="AfcatEmailSent.findAll", query="SELECT e FROM AfcatEmailSent e")
public class AfcatEmailSent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMAIL_SENT_EMAIL_SENT_ID_SEQ", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMAIL_SENT_EMAIL_SENT_ID_SEQ")
	@Column(name="email_sent_id")
	private Long emailSentId;

	@ManyToOne
    @JoinColumn(name = "exam_id")
	private AfcatExamMaster exam;
	
	@Column(name="candidates_count")
	private Integer candidatesCount;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="body")
	private String body;
	
	@Column(name="reason_for_email")
	private String reasonForEmail;
	
	@Column(name="is_attachment")
	private Boolean isAttachment;

	@Column(name="attachment_count")
	private Integer attachmentCount;

	@Column(name="attachment_path")
	private String attachmentPath;

    @UpdateTimestamp
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name = "email_start_datetime")
	private Timestamp emailStartDatetime;

	@Column(name = "email_end_datetime")
	private Timestamp emailEndDatetime;
	
	@Column(name = "email_sent_type")
	private int emailSentType;

	@Column(name = "email_sent_status")
	private Boolean emailSentStatus;

	@Column(name = "req_type")
	private String reqType;

	@Column(name = "mail_server_host")
	private String mailServerHost;
	
	@Column(name = "mail_server_port")
	private String mailServerPort;

	@Column(name = "starttls")
	private Boolean starttls;
	
	@Column(name = "socket_factory_port")
	private String socketFactoryPort;
	
	@Column(name = "send_admit_cards_radio")
	private String sendAdmitCardsRadio;
	
	@Column(name = "mail_slots")
	private String slots;
	
	@Column(name = "mail_centers")
	private String centers;
	
	@Column(name = "email_breathing_time")
	private String emailBreathingTime;
	
	@Column(name="email_per_batch")
	private String emailsPerBatch;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "emailSent", cascade = CascadeType.ALL)
	private List<AfcatEmailScheduleDetail> emailScheduleDetails = new ArrayList<>();

	@OneToOne(mappedBy = "emailSent")
	private AfcatEmailReportDetail emailReportDetail;

	public AfcatEmailSent() {
	}

	public AfcatEmailSent(AfcatExamMaster exam, Integer candidatesCount, String subject, String body,
			Timestamp emailStartDatetime, int emailSentType, Boolean emailSentStatus, String reqType, String reasonForEmail, 
			String mailServerHost, String mailServerPort, Boolean starttls,
			String socketFactoryPort) {
		super();
		this.exam = exam;
		this.candidatesCount = candidatesCount;
		this.subject = subject;
		this.body = body;
		this.emailStartDatetime = emailStartDatetime;
		this.emailSentType = emailSentType;
		this.emailSentStatus = emailSentStatus;
		this.reqType = reqType;
		this.reasonForEmail = reasonForEmail;
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
	}
	
	public AfcatEmailSent(AfcatExamMaster exam, Integer candidatesCount, String subject, String body,
			String reasonForEmail, Timestamp emailStartDatetime, int emailSentType, Boolean emailSentStatus,
			String reqType, String mailServerHost, String mailServerPort, Boolean starttls, String socketFactoryPort,
			String sendAdmitCardsRadio, String slots, String centers, String emailBreathingTime,
			String emailsPerBatch) {
		super();
		this.exam = exam;
		this.candidatesCount = candidatesCount;
		this.subject = subject;
		this.body = body;
		this.reasonForEmail = reasonForEmail;
		this.emailStartDatetime = emailStartDatetime;
		this.emailSentType = emailSentType;
		this.emailSentStatus = emailSentStatus;
		this.reqType = reqType;
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
		this.slots = slots;
		this.centers = centers;
		this.emailBreathingTime = emailBreathingTime;
		this.emailsPerBatch = emailsPerBatch;
	}

	public Long getEmailSentId() {
		return this.emailSentId;
	}

	public void setEmailSentId(Long emailSentId) {
		this.emailSentId = emailSentId;
	}

	public Integer getAttachmentCount() {
		return this.attachmentCount;
	}

	public void setAttachmentCount(Integer attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getCandidatesCount() {
		return this.candidatesCount;
	}

	public void setCandidatesCount(Integer candidatesCount) {
		this.candidatesCount = candidatesCount;
	}

	public AfcatExamMaster getExam() {
		return exam;
	}

	public void setExam(AfcatExamMaster exam) {
		this.exam = exam;
	}

	public Boolean getIsAttachment() {
		return this.isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	public String getReasonForEmail() {
		return this.reasonForEmail;
	}

	public void setReasonForEmail(String reasonForEmail) {
		this.reasonForEmail = reasonForEmail;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getEmailStartDatetime() {
		return emailStartDatetime;
	}

	public void setEmailStartDatetime(Timestamp emailStartDatetime) {
		this.emailStartDatetime = emailStartDatetime;
	}

	public Timestamp getEmailEndDatetime() {
		return emailEndDatetime;
	}

	public void setEmailEndDatetime(Timestamp emailEndDatetime) {
		this.emailEndDatetime = emailEndDatetime;
	}

	public int getEmailSentType() {
		return emailSentType;
	}

	public void setEmailSentType(int emailSentType) {
		this.emailSentType = emailSentType;
	}

	public Boolean getEmailSentStatus() {
		return emailSentStatus;
	}

	public void setEmailSentStatus(Boolean emailSentStatus) {
		this.emailSentStatus = emailSentStatus;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public Boolean getStarttls() {
		return starttls;
	}

	public void setStarttls(Boolean starttls) {
		this.starttls = starttls;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public void setSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}

	public List<AfcatEmailScheduleDetail> getEmailScheduleDetails() {
		return emailScheduleDetails;
	}

	public void setEmailScheduleDetails(List<AfcatEmailScheduleDetail> emailScheduleDetails) {
		this.emailScheduleDetails = emailScheduleDetails;
		for(AfcatEmailScheduleDetail esd : emailScheduleDetails) {
			esd.setEmailSent(this);
		}
	}

	public AfcatEmailReportDetail getEmailReportDetail() {
		return emailReportDetail;
	}

	public void setEmailReportDetail(AfcatEmailReportDetail emailReportDetail) {
		this.emailReportDetail = emailReportDetail;
	}
	
	public void getHelperScheduler(AfcatEmailScheduleDetail emailScheduleDetail) {
		this.emailScheduleDetails.add(emailScheduleDetail);
		emailScheduleDetail.setEmailSent(this);
		System.out.println("in helper : "+this.emailScheduleDetails.get(0).getEmailSent());
	}
	
	public String getSendAdmitCardsRadio() {
		return sendAdmitCardsRadio;
	}

	public void setSendAdmitCardsRadio(String sendAdmitCardsRadio) {
		this.sendAdmitCardsRadio = sendAdmitCardsRadio;
	}

	public String getSlots() {
		return slots;
	}

	public void setSlots(String slots) {
		this.slots = slots;
	}

	public String getCenters() {
		return centers;
	}

	public void setCenters(String centers) {
		this.centers = centers;
	}

	public String getEmailBreathingTime() {
		return emailBreathingTime;
	}

	public void setEmailBreathingTime(String emailBreathingTime) {
		this.emailBreathingTime = emailBreathingTime;
	}

	public String getEmailsPerBatch() {
		return emailsPerBatch;
	}

	public void setEmailsPerBatch(String emailsPerBatch) {
		this.emailsPerBatch = emailsPerBatch;
	}

}