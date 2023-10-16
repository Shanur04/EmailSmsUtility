package gov.cdac.models;

public class newSlotAddedModel {
	
	private int examId;
	
	private int centreId;
	
	private int slotId;
	
	private int maximumCapacity;
	
	private boolean statusForCentreSlot;
	
	private String listOfIcgPostCombinations;

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public String getListOfIcgPostCombinations() {
		return listOfIcgPostCombinations;
	}

	public void setListOfIcgPostCombinations(String listOfIcgPostCombinations) {
		this.listOfIcgPostCombinations = listOfIcgPostCombinations;
	}

	public int getCentreId() {
		return centreId;
	}

	public void setCentreId(int centreId) {
		this.centreId = centreId;
	}

	public boolean getStatusForCentreSlot() {
		return statusForCentreSlot;
	}

	public void setStatusForCentreSlot(boolean statusForCentreSlot) {
		this.statusForCentreSlot = statusForCentreSlot;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}
	
}
