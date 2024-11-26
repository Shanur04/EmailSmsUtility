package gov.cdac.models;


public class CandidateDetails {	
	
	Long appCredId;
	Integer icgPost;
	Integer candidateCaste;
	boolean isSeatAllocatioDone;
	Integer cityId;
	Integer prefIndexNo;
	
	
	public CandidateDetails(Long appCredId,Integer candidateCaste, Integer icgPost, boolean isSeatAllocatioDone,Integer cityId) {
		super();
		this.appCredId = appCredId;
		this.icgPost = icgPost;
		this.candidateCaste = candidateCaste;
		this.isSeatAllocatioDone = isSeatAllocatioDone;
		this.cityId = cityId;
	}
		
	public CandidateDetails(Long appCredId, Integer prefIndexNo, Integer icgPost,Integer cityId,
			boolean isSeatAllocatioDone) {
		super();
		this.appCredId = appCredId;
		this.icgPost = icgPost;
		this.candidateCaste = candidateCaste;
		this.isSeatAllocatioDone = isSeatAllocatioDone;
		this.cityId = cityId;
		this.prefIndexNo = prefIndexNo;
	}

	public Integer getPrefIndexNo() {
		return prefIndexNo;
	}

	public void setPrefIndexNo(Integer prefIndexNo) {
		this.prefIndexNo = prefIndexNo;
	}

	public Long getAppCredId() {
		return appCredId;
	}
	public void setAppCredId(Long appCredId) {
		this.appCredId = appCredId;
	}
	public Integer getIcgPost() {
		return icgPost;
	}
	public void setIcgPost(Integer icgPost) {
		this.icgPost = icgPost;
	}

	public Integer getCandidateCaste() {
		return candidateCaste;
	}

	public void setCandidateCaste(Integer candidateCaste) {
		this.candidateCaste = candidateCaste;
	}

	public boolean isSeatAllocatioDone() {
		return isSeatAllocatioDone;
	}

	public void setSeatAllocatioDone(boolean isSeatAllocatioDone) {
		this.isSeatAllocatioDone = isSeatAllocatioDone;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}
