package gov.cdac.emailservice.icg.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the allocation_change_level_master database table.
 * 
 */
@Entity
@Table(name="allocation_change_level_master")
@NamedQuery(name="AllocationChangeLevelMaster.findAll", query="SELECT a FROM AllocationChangeLevelMaster a")
public class AllocationChangeLevelMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ALLOCATION_CHANGE_LEVEL_MASTER_ALLOCATIONCHANGELEVELMASTERID_GENERATOR", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALLOCATION_CHANGE_LEVEL_MASTER_ALLOCATIONCHANGELEVELMASTERID_GENERATOR")
	@Column(name="allocation_change_level_master_id")
	private Integer allocationChangeLevelMasterId;

	@Column(name="allocation_change_level")
	private String allocationChangeLevel;

	@Column(name="allocation_change_level_status")
	private Boolean allocationChangeLevelStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to RequestDetail
	@OneToMany(mappedBy="allocationChangeLevelMaster")
	private List<RequestDetail> requestDetails;

	public AllocationChangeLevelMaster() {
	}

	public Integer getAllocationChangeLevelMasterId() {
		return this.allocationChangeLevelMasterId;
	}

	public void setAllocationChangeLevelMasterId(Integer allocationChangeLevelMasterId) {
		this.allocationChangeLevelMasterId = allocationChangeLevelMasterId;
	}

	public String getAllocationChangeLevel() {
		return this.allocationChangeLevel;
	}

	public void setAllocationChangeLevel(String allocationChangeLevel) {
		this.allocationChangeLevel = allocationChangeLevel;
	}

	public Boolean getAllocationChangeLevelStatus() {
		return this.allocationChangeLevelStatus;
	}

	public void setAllocationChangeLevelStatus(Boolean allocationChangeLevelStatus) {
		this.allocationChangeLevelStatus = allocationChangeLevelStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<RequestDetail> getRequestDetails() {
		return this.requestDetails;
	}

	public void setRequestDetails(List<RequestDetail> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public RequestDetail addRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().add(requestDetail);
		requestDetail.setAllocationChangeLevelMaster(this);

		return requestDetail;
	}

	public RequestDetail removeRequestDetail(RequestDetail requestDetail) {
		getRequestDetails().remove(requestDetail);
		requestDetail.setAllocationChangeLevelMaster(null);

		return requestDetail;
	}

}