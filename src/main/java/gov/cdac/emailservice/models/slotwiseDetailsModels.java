package gov.cdac.emailservice.models;

import java.util.List;

import gov.cdac.emailservice.icg.pojo.CentreExamslotMapping;
import gov.cdac.emailservice.icg.pojo.ExamCentreMapping;
import gov.cdac.emailservice.icg.pojo.ExamSlot;

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
