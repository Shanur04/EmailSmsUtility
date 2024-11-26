package gov.cdac.icgPojo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the payment_details database table.
 * 
 */
@Entity
@Table(name="payment_details")
@NamedQuery(name="PaymentDetail.findAll", query="SELECT p FROM PaymentDetail p")
public class PaymentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="payment_id")
	private Integer paymentId;

	@Column(name="txn_amount")
	private String txnAmount;

	@Column(name="txn_bank_ref_no")
	private String txnBankRefNo;

	@Column(name="txn_date")
	private Timestamp txnDate;

	@Column(name="txn_gtw_name")
	private String txnGtwName;

	@Column(name="txn_order_no")
	private String txnOrderNo;

	@Column(name="txn_payment_mode")
	private String txnPaymentMode;

	@Column(name="txn_record_tracking")
	private Timestamp txnRecordTracking;

	@Column(name="txn_ref_no")
	private String txnRefNo;

	@Column(name="txn_status")
	private String txnStatus;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	//bi-directional many-to-one association to PersonalDetail
	@ManyToOne
	@JoinColumn(name="registration_no", referencedColumnName="registration_no")
	private PersonalDetail personalDetail;

	public PaymentDetail() {
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getTxnAmount() {
		return this.txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnBankRefNo() {
		return this.txnBankRefNo;
	}

	public void setTxnBankRefNo(String txnBankRefNo) {
		this.txnBankRefNo = txnBankRefNo;
	}

	public Timestamp getTxnDate() {
		return this.txnDate;
	}

	public void setTxnDate(Timestamp txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnGtwName() {
		return this.txnGtwName;
	}

	public void setTxnGtwName(String txnGtwName) {
		this.txnGtwName = txnGtwName;
	}

	public String getTxnOrderNo() {
		return this.txnOrderNo;
	}

	public void setTxnOrderNo(String txnOrderNo) {
		this.txnOrderNo = txnOrderNo;
	}

	public String getTxnPaymentMode() {
		return this.txnPaymentMode;
	}

	public void setTxnPaymentMode(String txnPaymentMode) {
		this.txnPaymentMode = txnPaymentMode;
	}

	public Timestamp getTxnRecordTracking() {
		return this.txnRecordTracking;
	}

	public void setTxnRecordTracking(Timestamp txnRecordTracking) {
		this.txnRecordTracking = txnRecordTracking;
	}

	public String getTxnRefNo() {
		return this.txnRefNo;
	}

	public void setTxnRefNo(String txnRefNo) {
		this.txnRefNo = txnRefNo;
	}

	public String getTxnStatus() {
		return this.txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public PersonalDetail getPersonalDetail() {
		return this.personalDetail;
	}

	public void setPersonalDetail(PersonalDetail personalDetail) {
		this.personalDetail = personalDetail;
	}

}