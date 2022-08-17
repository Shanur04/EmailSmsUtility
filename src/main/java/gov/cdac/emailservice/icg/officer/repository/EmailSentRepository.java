package gov.cdac.emailservice.icg.officer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerEmailSent;
import gov.cdac.emailservice.projection.EmailSentSummary;

@Repository("icgOfficerEmailSentRepository")
public interface EmailSentRepository extends JpaRepository<IcgOfficerEmailSent, Long>{
    
	@Query(value = "select ssd.email_schedule_id emailScheduleId, em.exam_name examName, ss.body emailBody, ss.subject emailSubject, ss.candidates_count candidateCount, "
    	    + "ssd.email_schedule_start_date emailStartDateTime, ssd.email_schedule_end_date emailEndDateTime, ss.reason_for_email emailReason "
    	    + "from email_sent ss right outer join email_schedule_detail ssd on ss.email_sent_id = ssd.email_sent_id join exam_master em on ss.exam_id = em.exam_id "
    	    + "group by ss.email_sent_id, ssd.email_schedule_id, em.exam_name, ss.body, ss.subject, ss.candidates_count, ss.reason_for_email "
    	    + "order by ssd.email_schedule_id desc", nativeQuery = true)
        List<EmailSentSummary> findEmailSentSummary();;
	
	  @Query("SELECT e FROM IcgOfficerEmailSent e WHERE e.emailSentStatus = false")
	    List<IcgOfficerEmailSent> getScheduledEmailSentList();
}
