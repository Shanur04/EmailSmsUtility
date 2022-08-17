package gov.cdac.emailservice.models;

import gov.cdac.emailservice.projection.ExamCenterMappingSummary;

public class ExamCenterMappingModel {
    int totalSeatAllocated;

    int generatedCount;

    String centerCode;

    String examSlotCode;

    public ExamCenterMappingModel(ExamCenterMappingSummary examCenterMappingSummary) {
	this.centerCode = examCenterMappingSummary.getCentreCode();
	this.examSlotCode = examCenterMappingSummary.getExamSlotCode();
	this.totalSeatAllocated = examCenterMappingSummary.getTotalSeatAllocated();
	this.generatedCount = 0;
    }

    public int getTotalSeatAllocated() {
	return this.totalSeatAllocated;
    }

    public void setTotalSeatAllocated(int totalSeatAllocated) {
	this.totalSeatAllocated = totalSeatAllocated;
    }

    public int getGeneratedCount() {
	return this.generatedCount;
    }

    public void setGeneratedCount(int generatedCount) {
	this.generatedCount = generatedCount;
    }

    public String getCenterCode() {
	return this.centerCode;
    }

    public void setCenterCode(String centerCode) {
	this.centerCode = centerCode;
    }

    public String getExamSlotCode() {
	return this.examSlotCode;
    }

    public void setExamSlotCode(String examSlotCode) {
	this.examSlotCode = examSlotCode;
    }
}
