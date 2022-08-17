package gov.cdac.emailservice.projection;

import java.sql.Timestamp;

public interface SMSSentSummary {

	Long getSmsScheduleId();
	
    String getBatchName();

    String getExamName();

    String getSmsMessage();

    Integer getCandidateCount();

    Integer getNumberOfSMSUnits();

    Timestamp getSmsStartDateTime();

    Timestamp getSmsEndDateTime();

    String getSmsReason();

    Integer getSmsNotSentCount();

}
