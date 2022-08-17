package gov.cdac.emailservice.models;

public class AllocationCentreModals {
	
	private Integer centreId;
	private Integer examSlotId;
	private Integer icgPostId;
	private Integer cityId;
	private Integer totalAvailableSeats;
	private Integer totalSeatAllocated;
	private Integer examCenterMapId;
	private boolean isCenterOccipied;
	private long allocatedSeat;
	
	public Integer getCentreId() {
		return centreId;
	}
		
	public AllocationCentreModals(Integer centreId, Integer examSlotId, Integer icgPostId, Integer cityId,
			Integer totalAvailableSeats, Integer totalSeatAllocated, Integer examCenterMapId) {
		super();
		this.centreId = centreId;
		this.examSlotId = examSlotId;
		this.icgPostId = icgPostId;
		this.cityId = cityId;
		this.totalAvailableSeats = totalAvailableSeats;
		this.totalSeatAllocated = totalSeatAllocated;
		this.examCenterMapId = examCenterMapId;
	}

	public AllocationCentreModals(Integer centreId, Integer examSlotId, Integer totalAvailableSeats,
			long allocatedSeat,Integer cityId) {
		super();
		this.centreId = centreId;
		this.examSlotId = examSlotId;
		this.totalAvailableSeats = totalAvailableSeats;
		this.allocatedSeat = allocatedSeat;
		this.cityId = cityId;
	}

	public long getAllocatedSeat() {
		return allocatedSeat;
	}

	public void setAllocatedSeat(long allocatedSeat) {
		this.allocatedSeat = allocatedSeat;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}
	public Integer getExamSlotId() {
		return examSlotId;
	}
	public void setExamSlotId(Integer examSlotId) {
		this.examSlotId = examSlotId;
	}
	public Integer getIcgPostId() {
		return icgPostId;
	}
	public void setIcgPostId(Integer icgPostId) {
		this.icgPostId = icgPostId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getTotalAvailableSeats() {
		return totalAvailableSeats;
	}
	public void setTotalAvailableSeats(Integer totalAvailableSeats) {
		this.totalAvailableSeats = totalAvailableSeats;
	}
	public Integer getTotalSeatAllocated() {
		return totalSeatAllocated;
	}
	public void setTotalSeatAllocated(Integer totalSeatAllocated) {
		this.totalSeatAllocated = totalSeatAllocated;
	}
	public Integer getExamCenterMapId() {
		return examCenterMapId;
	}
	public void setExamCenterMapId(Integer examCenterMapId) {
		this.examCenterMapId = examCenterMapId;
	}
	public boolean isCenterOccipied() {
		return isCenterOccipied;
	}
	public void setCenterOccipied(boolean isCenterOccipied) {
		this.isCenterOccipied = isCenterOccipied;
	}
}
