package gov.cdac.icgOfficerPojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sms_schedule_status")
public class IcgOfficerSMSStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name="status_details")
    private String statusDetail;

    public IcgOfficerSMSStatus() {
		// TODO Auto-generated constructor stub
	}
    
	public IcgOfficerSMSStatus(Long statusId, String statusDetail) {
		super();
		this.statusId = statusId;
		this.statusDetail = statusDetail;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	@Override
	public String toString() {
		return "IcgOfficerSMSStatus [statusId=" + statusId + ", statusDetail=" + statusDetail + "]";
	}

	
    
}
