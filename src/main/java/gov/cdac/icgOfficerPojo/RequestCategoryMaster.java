package gov.cdac.icgOfficerPojo;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the request_category_master database table.
 * 
 */
@Entity
@Table(name="request_category_master")
@NamedQuery(name="RequestCategoryMaster.findAll", query="SELECT r FROM RequestCategoryMaster r")
@JsonIgnoreProperties({"requestDetails"})
public class RequestCategoryMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REQUEST_CATEGORY_MASTER_REQUESTCATEGORYMASTERID_GENERATOR" ,allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUEST_CATEGORY_MASTER_REQUESTCATEGORYMASTERID_GENERATOR")
	@Column(name="request_category_master_id")
	private Integer requestCategoryMasterId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="request_category_status")
	private Boolean requestCategoryStatus;

	@Column(name="request_category_type")
	private String requestCategoryType;

	//bi-directional many-to-one association to RequestDetail
	@OneToMany(mappedBy="requestCategoryMaster")
	private List<RequestDetail> requestDetails;

	public RequestCategoryMaster() {
	}

	public Integer getRequestCategoryMasterId() {
		return this.requestCategoryMasterId;
	}

	public void setRequestCategoryMasterId(Integer requestCategoryMasterId) {
		this.requestCategoryMasterId = requestCategoryMasterId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Boolean getRequestCategoryStatus() {
		return this.requestCategoryStatus;
	}

	public void setRequestCategoryStatus(Boolean requestCategoryStatus) {
		this.requestCategoryStatus = requestCategoryStatus;
	}

	public String getRequestCategoryType() {
		return this.requestCategoryType;
	}

	public void setRequestCategoryType(String requestCategoryType) {
		this.requestCategoryType = requestCategoryType;
	}

	public List<RequestDetail> getRequestDetails() {
		return this.requestDetails;
	}

	public void setRequestDetails(List<RequestDetail> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public RequestDetail addRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().add(requestDetail);
		requestDetail.setRequestCategoryMaster(this);

		return requestDetail;
	}

	public RequestDetail removeRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().remove(requestDetail);
		requestDetail.setRequestCategoryMaster(null);

		return requestDetail;
	}

}