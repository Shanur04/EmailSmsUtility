package gov.cdac.icgPojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_schedule_status")
public class SMSStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name="status_details")
    private String statusDetail;

    public SMSStatus() {
		// TODO Auto-generated constructor stub
	}
    
	public SMSStatus(Long statusId, String statusDetail) {
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
		return "AfcatSMSStatus [statusId=" + statusId + ", statusDetail=" + statusDetail + "]";
	}
    
}
