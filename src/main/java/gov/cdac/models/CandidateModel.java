package gov.cdac.models;

public class CandidateModel {
	private String mobileNo;

	public CandidateModel() {
	}

	public CandidateModel(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "CandidateModel [mobileNo=" + mobileNo + "]";
	}
	
	
	
	

}
