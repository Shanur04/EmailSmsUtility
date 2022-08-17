package gov.cdac.emailservice.models;

import java.util.List;

public class EmailId {
	
	private String email_id;
	private List<String> centerCode;
	private List<String> slotCode;

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public List<String> getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(List<String> centerCode) {
		this.centerCode = centerCode;
	}

	public List<String> getSlotCode() {
		return slotCode;
	}

	public void setSlotCode(List<String> slotCode) {
		this.slotCode = slotCode;
	}

	@Override
	public String toString() {
		return "EmailId [email_id=" + email_id + ", centerCode=" + centerCode + ", slotCode=" + slotCode + "]";
	}
	
	
}
