package gov.cdac.models;

import java.util.List;

import gov.cdac.icgPojo.CentreExamslotMapping;
import gov.cdac.icgPojo.ExamCentreMapping;
import gov.cdac.icgPojo.ExamSlot;

public class slotwiseDetailsModels {

	private List<ExamSlot> examSlotList;
	
	private List<ExamCentreMapping> examCentreMappingList;
	
	private List<CentreExamslotMapping> centreExamSlotMappingList;

	public List<ExamSlot> getExamSlotList() {
		return examSlotList;
	}

	public void setExamSlotList(List<ExamSlot> examSlotList) {
		this.examSlotList = examSlotList;
	}

	public List<ExamCentreMapping> getExamCentreMappingList() {
		return examCentreMappingList;
	}

	public void setExamCentreMappingList(List<ExamCentreMapping> examCentreMappingList) {
		this.examCentreMappingList = examCentreMappingList;
	}

	public List<CentreExamslotMapping> getCentreExamSlotMappingList() {
		return centreExamSlotMappingList;
	}

	public void setCentreExamSlotMappingList(List<CentreExamslotMapping> centreExamSlotMappingList) {
		this.centreExamSlotMappingList = centreExamSlotMappingList;
	}
	
	
	
}
