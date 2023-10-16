package gov.cdac.icgOfficerPojo;


import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bank1_refund_details database table.
 * 
 */
@Entity
@Table(name="bank1_refund_details")
@NamedQuery(name="Bank1RefundDetail.findAll", query="SELECT b FROM Bank1RefundDetail b")
public class Bank1RefundDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BANK1_REFUND_DETAILS_REFUNDID_GENERATOR" ,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANK1_REFUND_DETAILS_REFUNDID_GENERATOR")
	@Column(name="refund_id")
	private Integer refundId;

	@Column(name="finance_employee_name")
	private String financeEmployeeName;

	@Column(name="refund_by_finance_date")
	private Timestamp refundByFinanceDate;

	@Column(name="registration_no")
	private String registrationNo;

	@Column(name="shared_with_finance_date")
	private Timestamp sharedWithFinanceDate;

	@Column(name="txn_amount")
	private String txnAmount;

	@Column(name="txn_authcode")
	private String txnAuthcode;

	@Column(name="txn_bank_ref_no")
	private String txnBankRefNo;

	@Column(name="txn_card_name")
	private String txnCardName;

	@Column(name="txn_date")
	private Timestamp txnDate;

	@Column(name="txn_gtw_name")
	private String txnGtwName;

	@Column(name="txn_invoice_ref_no")
	private Integer txnInvoiceRefNo;

	@Column(name="txn_issuing_bank")
	private String txnIssuingBank;

	@Column(name="txn_order_no")
	private String txnOrderNo;

	@Column(name="txn_payment_type")
	private String txnPaymentType;

	@Column(name="txn_payout_id")
	private Long txnPayoutId;

	@Column(name="txn_record_tracking")
	private Timestamp txnRecordTracking;

	@Column(name="txn_ref_no")
	private String txnRefNo;

	@Column(name="txn_settlement_date")
	private Timestamp txnSettlementDate;

	@Column(name="txn_td_type")
	private String txnTdType;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	public Bank1RefundDetail() {
	}

	public Integer getRefundId() {
		return this.refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public String getFinanceEmployeeName() {
		return this.financeEmployeeName;
	}

	public void setFinanceEmployeeName(String financeEmployeeName) {
		this.financeEmployeeName = financeEmployeeName;
	}

	public Timestamp getRefundByFinanceDate() {
		return this.refundByFinanceDate;
	}

	public void setRefundByFinanceDate(Timestamp refundByFinanceDate) {
		this.refundByFinanceDate = refundByFinanceDate;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public Timestamp getSharedWithFinanceDate() {
		return this.sharedWithFinanceDate;
	}

	public void setSharedWithFinanceDate(Timestamp sharedWithFinanceDate) {
		this.sharedWithFinanceDate = sharedWithFinanceDate;
	}

	public String getTxnAmount() {
		return this.txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnAuthcode() {
		return this.txnAuthcode;
	}

	public void setTxnAuthcode(String txnAuthcode) {
		this.txnAuthcode = txnAuthcode;
	}

	public String getTxnBankRefNo() {
		return this.txnBankRefNo;
	}

	public void setTxnBankRefNo(String txnBankRefNo) {
		this.txnBankRefNo = txnBankRefNo;
	}

	public String getTxnCardName() {
		return this.txnCardName;
	}

	public void setTxnCardName(String txnCardName) {
		this.txnCardName = txnCardName;
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

	public Integer getTxnInvoiceRefNo() {
		return this.txnInvoiceRefNo;
	}

	public void setTxnInvoiceRefNo(Integer txnInvoiceRefNo) {
		this.txnInvoiceRefNo = txnInvoiceRefNo;
	}

	public String getTxnIssuingBank() {
		return this.txnIssuingBank;
	}

	public void setTxnIssuingBank(String txnIssuingBank) {
		this.txnIssuingBank = txnIssuingBank;
	}

	public String getTxnOrderNo() {
		return this.txnOrderNo;
	}

	public void setTxnOrderNo(String txnOrderNo) {
		this.txnOrderNo = txnOrderNo;
	}

	public String getTxnPaymentType() {
		return this.txnPaymentType;
	}

	public void setTxnPaymentType(String txnPaymentType) {
		this.txnPaymentType = txnPaymentType;
	}

	public Long getTxnPayoutId() {
		return this.txnPayoutId;
	}

	public void setTxnPayoutId(Long txnPayoutId) {
		this.txnPayoutId = txnPayoutId;
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

	public Timestamp getTxnSettlementDate() {
		return this.txnSettlementDate;
	}

	public void setTxnSettlementDate(Timestamp txnSettlementDate) {
		this.txnSettlementDate = txnSettlementDate;
	}

	public String getTxnTdType() {
		return this.txnTdType;
	}

	public void setTxnTdType(String txnTdType) {
		this.txnTdType = txnTdType;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

}