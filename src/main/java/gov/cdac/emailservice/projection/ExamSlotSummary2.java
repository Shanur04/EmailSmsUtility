package gov.cdac.emailservice.projection;

import java.sql.Timestamp;
public interface ExamSlotSummary2 {
    Integer getExamSlotId();
    
    Timestamp getExamDate();
    
    String getExamSlotCode();

    String getExamSlotName();
}
