package gov.cdac.emailservice.afcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatSMSSent;
import gov.cdac.emailservice.projection.SMSSentSummary;


@Repository("afcatSMSSentRepository")
public interface SMSSentRepository extends JpaRepository<AfcatSMSSent, Long> {

    List<AfcatSMSSent> findAllByOrderBySMSSentIdDesc();

    @Query(value = "select ssd.sms_schedule_id smsScheduleId, bm.batch_name batchName, em.exam_name examName, ss.message smsMessage, ss.candidates_count candidateCount, ss.number_of_sms_units numberOfSMSUnits, "
	    + "count(sns.sms_not_sent_details_id) smsNotSentCount, ssd.sms_schedule_start_date smsStartDateTime, ssd.sms_schedule_start_date smsEndDateTime, srm.sms_reason smsReason "
	    + "from sms_sent ss right outer join sms_schedule_detail ssd on ss.sms_sent_id = ssd.sms_sent_id left outer join sms_not_sent_details sns on ss.sms_sent_id = sns.sms_sent_id "
	    + "join batch_details bm on ss.batch_id = bm.batch_id join exam_master em on ss.exam_id = em.exam_id "
	    + "join sms_reason_master srm on ss.sms_reason_master_id = srm.sms_reason_master_id "
	    + "group by ss.sms_sent_id, ssd.sms_schedule_id, bm.batch_name, em.exam_name, ss.message, ss.candidates_count, ss.number_of_sms_units, srm.sms_reason, "
	    + "ss.sms_start_datetime, ss.sms_end_datetime order by ss.sms_sent_id desc", nativeQuery = true)
    List<SMSSentSummary> findSMSSentSummary();

    AfcatSMSSent findTop1ByOrderBySMSSentIdDesc();
    
    @Query("SELECT s FROM AfcatSMSSent s WHERE s.smsSentStatus = false")
    List<AfcatSMSSent> getScheduleSMSSentList();
       
}
