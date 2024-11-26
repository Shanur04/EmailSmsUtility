package gov.cdac.projection;

import java.util.Date;

public interface EmailSentSummary {
	
    String getExamName();
    
    String getEmailReason();
       
    String getEmailSubject();
    
    String getEmailBody();
    
    Integer getCandidateCount();
    
    Date getEmailStartDateTime();
    
    Date getEmailEndDateTime();
    
    Long getEmailScheduleId();
    
	Integer getAttachmentCount();
	
	String getAttachmentPath();
		
    Boolean getIsAttachment();
    

}
