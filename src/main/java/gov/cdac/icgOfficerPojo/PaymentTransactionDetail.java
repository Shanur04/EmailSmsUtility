package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the payment_transaction_details database table.
 * 
 */
@Entity
@Table(name="payment_transaction_details")
@NamedQuery(name="PaymentTransactionDetail.findAll", query="SELECT p FROM PaymentTransactionDetail p")
@JsonIgnoreProperties({"paymentDetails"})
public class PaymentTransactionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENTTRANSACTIONDETAILSID_GENERATOR" ,  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENTTRANSACTIONDETAILSID_GENERATOR")
	@Column(name="payment_transaction_details_id")
	private Integer paymentTransactionDetailsId;

	@Column(name="txn_amount")
	private String txnAmount;

	@Column(name="txn_bank_ref_no")
	private String txnBankRefNo;

	@Column(name="txn_callbackservice_status")
	private Boolean txnCallbackserviceStatus;

	@Column(name="txn_cardtype")
	private String txnCardtype;

	@Column(name="txn_gtw_name")
	private String txnGtwName;

	@Column(name="txn_ipaddress")
	private String txnIpaddress;

	@Column(name="txn_merchant_ref_no")
	private String txnMerchantRefNo;

	@Column(name="txn_order_bank_response")
	private String txnOrderBankResponse;

	@Column(name="txn_order_info")
	private String txnOrderInfo;
	
	@Column(name="txn_order_no")
	private String txnOrderNo;

	@Column(name="txn_payment_mode")
	private String txnPaymentMode;

	@Column(name="txn_pg")
	private String txnPg;

	@Column(name="txn_record_tracking")
	private Timestamp txnRecordTracking;

	@Column(name="txn_ref_no")
	private String txnRefNo;

	@Column(name="txn_remark")
	private String txnRemark;

	@Column(name="txn_response_code")
	private String txnResponseCode;

	@Column(name="txn_return_record_tracking")
	private Timestamp txnReturnRecordTracking;

	@Column(name="txn_status")
	private String txnStatus;

	@Column(name="txn_vpa")
	private String txnVpa;
	
	@Column(name="txn_cron_status")
	private Boolean txnCronStatus;
	

	//bi-directional many-to-one association to PaymentDetail
	@OneToMany(mappedBy="paymentTransactionDetail")
	private List<PaymentDetail> paymentDetails;

	//bi-directional many-to-one association to ApplicantCredential
	@ManyToOne
	@JoinColumn(name="applicant_cred_id")
	private ApplicantCredential applicantCredential;

	public PaymentTransactionDetail() {
	}

	public Integer getPaymentTransactionDetailsId() {
		return this.paymentTransactionDetailsId;
	}

	public void setPaymentTransactionDetailsId(Integer paymentTransactionDetailsId) {
		this.paymentTransactionDetailsId = paymentTransactionDetailsId;
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

	public Boolean getTxnCallbackserviceStatus() {
		return this.txnCallbackserviceStatus;
	}

	public void setTxnCallbackserviceStatus(Boolean txnCallbackserviceStatus) {
		this.txnCallbackserviceStatus = txnCallbackserviceStatus;
	}

	public String getTxnCardtype() {
		return this.txnCardtype;
	}

	public void setTxnCardtype(String txnCardtype) {
		this.txnCardtype = txnCardtype;
	}

	public String getTxnGtwName() {
		return this.txnGtwName;
	}

	public void setTxnGtwName(String txnGtwName) {
		this.txnGtwName = txnGtwName;
	}

	public String getTxnIpaddress() {
		return this.txnIpaddress;
	}

	public void setTxnIpaddress(String txnIpaddress) {
		this.txnIpaddress = txnIpaddress;
	}

	public String getTxnMerchantRefNo() {
		return this.txnMerchantRefNo;
	}

	public void setTxnMerchantRefNo(String txnMerchantRefNo) {
		this.txnMerchantRefNo = txnMerchantRefNo;
	}

	public String getTxnOrderBankResponse() {
		return this.txnOrderBankResponse;
	}

	public void setTxnOrderBankResponse(String txnOrderBankResponse) {
		this.txnOrderBankResponse = txnOrderBankResponse;
	}

	public String getTxnOrderInfo() {
		return this.txnOrderInfo;
	}

	public void setTxnOrderInfo(String txnOrderInfo) {
		this.txnOrderInfo = txnOrderInfo;
	}

	public String getTxnPaymentMode() {
		return this.txnPaymentMode;
	}

	public void setTxnPaymentMode(String txnPaymentMode) {
		this.txnPaymentMode = txnPaymentMode;
	}

	public String getTxnPg() {
		return this.txnPg;
	}

	public void setTxnPg(String txnPg) {
		this.txnPg = txnPg;
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

	public String getTxnRemark() {
		return this.txnRemark;
	}

	public void setTxnRemark(String txnRemark) {
		this.txnRemark = txnRemark;
	}

	public String getTxnResponseCode() {
		return this.txnResponseCode;
	}

	public void setTxnResponseCode(String txnResponseCode) {
		this.txnResponseCode = txnResponseCode;
	}

	public Timestamp getTxnReturnRecordTracking() {
		return this.txnReturnRecordTracking;
	}

	public void setTxnReturnRecordTracking(Timestamp txnReturnRecordTracking) {
		this.txnReturnRecordTracking = txnReturnRecordTracking;
	}

	public String getTxnStatus() {
		return this.txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnVpa() {
		return this.txnVpa;
	}

	public void setTxnVpa(String txnVpa) {
		this.txnVpa = txnVpa;
	}

	
	public Boolean getTxnCronStatus() {
		return txnCronStatus;
	}

	public void setTxnCronStatus(Boolean txnCronStatus) {
		this.txnCronStatus = txnCronStatus;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return this.paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public PaymentDetail addPaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().add(paymentDetail);
		paymentDetail.setPaymentTransactionDetail(this);

		return paymentDetail;
	}

	public PaymentDetail removePaymentDetail(PaymentDetail paymentDetail) {
		getPaymentDetails().remove(paymentDetail);
		paymentDetail.setPaymentTransactionDetail(null);

		return paymentDetail;
	}

	public ApplicantCredential getApplicantCredential() {
		return this.applicantCredential;
	}

	public void setApplicantCredential(ApplicantCredential applicantCredential) {
		this.applicantCredential = applicantCredential;
	}

	public String getTxnOrderNo() {
		return txnOrderNo;
	}

	public void setTxnOrderNo(String txnOrderNo) {
		this.txnOrderNo = txnOrderNo;
	}

	/*
	 * @Override public String toString() { return
	 * "PaymentTransactionDetail [paymentTransactionDetailsId=" +
	 * paymentTransactionDetailsId + ", txnAmount=" + txnAmount + ", txnBankRefNo="
	 * + txnBankRefNo + ", txnCallbackserviceStatus=" + txnCallbackserviceStatus +
	 * ", txnCardtype=" + txnCardtype + ", txnGtwName=" + txnGtwName +
	 * ", txnIpaddress=" + txnIpaddress + ", txnMerchantRefNo=" + txnMerchantRefNo +
	 * ", txnOrderBankResponse=" + txnOrderBankResponse + ", txnOrderInfo=" +
	 * txnOrderInfo + ", txnOrderNo=" + txnOrderNo + ", txnPaymentMode=" +
	 * txnPaymentMode + ", txnPg=" + txnPg + ", txnRecordTracking=" +
	 * txnRecordTracking + ", txnRefNo=" + txnRefNo + ", txnRemark=" + txnRemark +
	 * ", txnResponseCode=" + txnResponseCode + ", txnReturnRecordTracking=" +
	 * txnReturnRecordTracking + ", txnStatus=" + txnStatus + ", txnVpa=" + txnVpa +
	 * ", paymentDetails=" + paymentDetails + ", applicantCredential=" +
	 * applicantCredential + "]"; }
	 */

}