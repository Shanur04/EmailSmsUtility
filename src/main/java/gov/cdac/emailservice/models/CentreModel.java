package gov.cdac.emailservice.models;

import java.util.HashMap;

public class CentreModel {

	private String candidateName;
	private Integer centreId;
	private String centreName;
	private String centreCode;
	private String cityName;

	private String slotName;
	private String centreAddress;
	private String pincode;

	private Long totalCapacity;
	private HashMap<String, Long> slotDetails;

	

	

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCentreAddress() {
		return centreAddress;
	}

	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}

	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Long totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getCentreCode() {
		return centreCode;
	}

	public void setCentreCode(String centreCode) {
		this.centreCode = centreCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public CentreModel(Integer centreId, String centreName, String centreCode, String cityName, String slotName,
			Long totalCapacity) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.centreCode = centreCode;
		this.cityName = cityName;
		this.slotName = slotName;
		this.totalCapacity = totalCapacity;
	}

	public CentreModel(Integer centreId, String centreName, String cityName, String centreCode) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.cityName = cityName;
		this.centreCode = centreCode;
		slotDetails = new HashMap<>();

	}
	
	public CentreModel(Integer centreId, String centreName, String centreCode) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.centreCode = centreCode;

	}

	public CentreModel(Integer centreId, String centreName, String centreCode,String centreAddress,String pincode) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.centreCode = centreCode;
		this.centreAddress=centreAddress;
		this.pincode=pincode;
	}

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	

	public HashMap<String, Long> getSlotDetails() {
		return slotDetails;
	}

	public void setSlotDetails(HashMap<String, Long> slotDetails) {
		this.slotDetails = slotDetails;
	}

	public CentreModel() {
	}

	public CentreModel(String slotName,String centreName ) {
		super();
		
		this.centreName = centreName;
		this.slotName = slotName;
	}
}
