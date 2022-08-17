package gov.cdac.emailservice.projection;

public interface ExamCenterMappingSummary {
    Integer getTotalSeatAllocated();

    String getCentreCode();

    String getExamSlotCode();

    default String getKey() {
	return getCentreCode() + getExamSlotCode();
    }
}
