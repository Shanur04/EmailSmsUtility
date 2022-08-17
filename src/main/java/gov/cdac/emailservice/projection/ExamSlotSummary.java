package gov.cdac.emailservice.projection;

import java.sql.Date;
public interface ExamSlotSummary {
    Integer getExamSlotId();
    
    Date getExamDate();

    String getExamSlotCode();

    String getExamSlotName();
}
