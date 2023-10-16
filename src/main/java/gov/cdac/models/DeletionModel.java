package gov.cdac.models;

import java.util.HashMap;

public class DeletionModel {
	
	private Integer centreId;
	private String centreName;
	private Integer slotId;
	private String slotName;
	private HashMap<Integer, String> slotDetails;
	private Integer afcatGroupId;
	private Long slotStrength;
	private Long registrationNumber;
	private String emailId;
	private String centerCode;

	// This Ctor is used for applicant Cred Id wise deletion

	public DeletionModel(Long slotStrength, Integer slotId) {
		super();
		this.slotId = slotId;
		this.slotStrength = slotStrength;
	}

	public DeletionModel(Integer centreId, String centreName) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		slotDetails = new HashMap<>();
	}

	public Integer getAfcatGroupId() {
		return afcatGroupId;
	}

	public void setAfcatGroupId(Integer afcatGroupId) {
		this.afcatGroupId = afcatGroupId;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public DeletionModel(Integer centreId, String centreName, Integer slotId, String slotName) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.slotId = slotId;
		this.slotName = slotName;
	}
	
	public DeletionModel(Integer centreId, String centreName, Integer slotId, String slotName,String centerCode) {
		super();
		this.centreId = centreId;
		this.centreName = centreName;
		this.slotId = slotId;
		this.slotName = slotName;
		this.centerCode=centerCode;
	}

	public DeletionModel(Integer centreId, Integer slotId) {
		super();
		this.centreId = centreId;
		this.slotId = slotId;
	}

	public HashMap<Integer, String> getSlotDetails() {
		return slotDetails;
	}

	public void setSlotDetails(HashMap<Integer, String> slotDetails) {
		this.slotDetails = slotDetails;
	}

	public DeletionModel() {
		// TODO Auto-generated constructor stub
	}

	// If user wants to delete all slots belonging to particular centre
	public DeletionModel(Integer centreId) {
		super();
		this.centreId = centreId;
	}

	
	
	public DeletionModel(Integer centreId2, String centreName2, String centerCode2) {
		super();
		this.centreId = centreId2;
		this.centreName = centreName2;
		this.centerCode=centerCode2;
		slotDetails = new HashMap<>();
	}

	@Override
	public String toString() {
		return "DeletionModel [centreId=" + centreId + ", centreName=" + centreName + ", slotId=" + slotId
				+ ", slotName=" + slotName + ", slotDetails=" + slotDetails + ", afcatGroupId=" + afcatGroupId
				+ ", slotStrength=" + slotStrength + "]";
	}

	public Long getSlotStrength() {
		return slotStrength;
	}

	public void setSlotStrength(Long slotStrength) {
		this.slotStrength = slotStrength;
	}

}
