package gov.cdac.emailservice.icg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_schedule_status")
public class IcgEmailStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name="status_details")
    private String statusDetail;

    public IcgEmailStatus() {
		// TODO Auto-generated constructor stub
	}
    
	public IcgEmailStatus(Long statusId, String statusDetail) {
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
		return "SMSStatus [statusId=" + statusId + ", statusDetail=" + statusDetail + "]";
	}
    
}
