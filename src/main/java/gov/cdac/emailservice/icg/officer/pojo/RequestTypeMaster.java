package gov.cdac.emailservice.icg.officer.pojo;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the request_type_master database table.
 * 
 */
@Entity
@Table(name="request_type_master")
@NamedQuery(name="RequestTypeMaster.findAll", query="SELECT r FROM RequestTypeMaster r")
@JsonIgnoreProperties({"requestDetails"})
public class RequestTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REQUEST_TYPE_MASTER_REQUESTTYPEMASTERID_GENERATOR" ,allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUEST_TYPE_MASTER_REQUESTTYPEMASTERID_GENERATOR")
	@Column(name="request_type_master_id")
	private Integer requestTypeMasterId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	@Column(name="request_status")
	private Boolean requestStatus;

	@Column(name="request_type")
	private String requestType;

	//bi-directional many-to-one association to RequestDetail
	@OneToMany(mappedBy="requestTypeMaster")
	private List<RequestDetail> requestDetails;

	public RequestTypeMaster() {
	}

	public Integer getRequestTypeMasterId() {
		return this.requestTypeMasterId;
	}

	public void setRequestTypeMasterId(Integer requestTypeMasterId) {
		this.requestTypeMasterId = requestTypeMasterId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public Boolean getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(Boolean requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestType() {
		return this.requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public List<RequestDetail> getRequestDetails() {
		return this.requestDetails;
	}

	public void setRequestDetails(List<RequestDetail> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public RequestDetail addRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().add(requestDetail);
		requestDetail.setRequestTypeMaster(this);

		return requestDetail;
	}

	public RequestDetail removeRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().remove(requestDetail);
		requestDetail.setRequestTypeMaster(null);

		return requestDetail;
	}

}