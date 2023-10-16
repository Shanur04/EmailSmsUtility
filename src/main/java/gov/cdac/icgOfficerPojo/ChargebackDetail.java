package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the chargeback_details database table.
 * 
 */
@Entity
@Table(name="chargeback_details")
@NamedQuery(name="ChargebackDetail.findAll", query="SELECT c FROM ChargebackDetail c")
public class ChargebackDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CHARGEBACK_DETAILS_CBID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHARGEBACK_DETAILS_CBID_GENERATOR")
	@Column(name="cb_id")
	private Integer cbId;

	@Column(name="cb_bank")
	private String cbBank;

	@Column(name="cb_case_no")
	private String cbCaseNo;

	@Column(name="cb_count")
	private Integer cbCount;

	@Column(name="cb_email_content")
	private String cbEmailContent;

	@Column(name="cb_received_date")
	private Timestamp cbReceivedDate;

	@Column(name="cb_remark")
	private String cbRemark;

	@Column(name="cb_reply_date")
	private Timestamp cbReplyDate;

	@Column(name="cb_status")
	private String cbStatus;

	@Column(name="cb_tat_date")
	private Timestamp cbTatDate;

	private String emailid;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="registration_no")
	private String registrationNo;

	@Column(name="resolved_by")
	private String resolvedBy;

	@Column(name="txn_bank_ref_no")
	private String txnBankRefNo;

	@Column(name="txn_order_no")
	private String txnOrderNo;

	@Column(name="txn_ref_no")
	private String txnRefNo;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	public ChargebackDetail() {
	}

	public Integer getCbId() {
		return this.cbId;
	}

	public void setCbId(Integer cbId) {
		this.cbId = cbId;
	}

	public String getCbBank() {
		return this.cbBank;
	}

	public void setCbBank(String cbBank) {
		this.cbBank = cbBank;
	}

	public String getCbCaseNo() {
		return this.cbCaseNo;
	}

	public void setCbCaseNo(String cbCaseNo) {
		this.cbCaseNo = cbCaseNo;
	}

	public Integer getCbCount() {
		return this.cbCount;
	}

	public void setCbCount(Integer cbCount) {
		this.cbCount = cbCount;
	}

	public String getCbEmailContent() {
		return this.cbEmailContent;
	}

	public void setCbEmailContent(String cbEmailContent) {
		this.cbEmailContent = cbEmailContent;
	}

	public Timestamp getCbReceivedDate() {
		return this.cbReceivedDate;
	}

	public void setCbReceivedDate(Timestamp cbReceivedDate) {
		this.cbReceivedDate = cbReceivedDate;
	}

	public String getCbRemark() {
		return this.cbRemark;
	}

	public void setCbRemark(String cbRemark) {
		this.cbRemark = cbRemark;
	}

	public Timestamp getCbReplyDate() {
		return this.cbReplyDate;
	}

	public void setCbReplyDate(Timestamp cbReplyDate) {
		this.cbReplyDate = cbReplyDate;
	}

	public String getCbStatus() {
		return this.cbStatus;
	}

	public void setCbStatus(String cbStatus) {
		this.cbStatus = cbStatus;
	}

	public Timestamp getCbTatDate() {
		return this.cbTatDate;
	}

	public void setCbTatDate(Timestamp cbTatDate) {
		this.cbTatDate = cbTatDate;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getResolvedBy() {
		return this.resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public String getTxnBankRefNo() {
		return this.txnBankRefNo;
	}

	public void setTxnBankRefNo(String txnBankRefNo) {
		this.txnBankRefNo = txnBankRefNo;
	}

	public String getTxnOrderNo() {
		return this.txnOrderNo;
	}

	public void setTxnOrderNo(String txnOrderNo) {
		this.txnOrderNo = txnOrderNo;
	}

	public String getTxnRefNo() {
		return this.txnRefNo;
	}

	public void setTxnRefNo(String txnRefNo) {
		this.txnRefNo = txnRefNo;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

}