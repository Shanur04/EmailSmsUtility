package gov.cdac.emailservice.models;
/**
 * 
 * @author Kunal Mande
 *
 */

public class SearchViewAllocationModel {

    private String AppCredId;
    
    private String RegNo;
    
    private String EmailId;

	public SearchViewAllocationModel() {
		
	}

	public SearchViewAllocationModel(String appCredId, String regNo, String emailId) {
		AppCredId = appCredId;
		RegNo = regNo;
		EmailId = emailId;
	}

	public String getAppCredId() {
		return AppCredId;
	}

	public void setAppCredId(String appCredId) {
		AppCredId = appCredId;
	}

	public String getRegNo() {
		return RegNo;
	}

	public void setRegNo(String regNo) {
		RegNo = regNo;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
    
    

    
}
