package gov.cdac.afcatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatSMSScheduleDetail;



@Repository("afcatSMSScheduleRepository")
public interface SMSScheduleRepository extends JpaRepository<AfcatSMSScheduleDetail, Long>{
	
	@Query(value="select * from sms_schedule_detail ss where ss.sms_sent_id = ?1 order by ss.sms_schedule_id", nativeQuery = true)
	List<AfcatSMSScheduleDetail> findByAfcatSMSSent(Long sentId);
	
	 public <S extends AfcatSMSScheduleDetail>S save(S entity);
	 
	 @Query(value = "select ss.sms_sent_id, ssd.sms_schedule_id, bm.batch_name batchName, em.exam_name examName, "
	 		+ "ss.message smsMessage, ssd.sms_total_count candidateCount, ss.number_of_sms_units numberOfSMSUnits, "
	 		+ "count(sns.sms_not_sent_details_id) smsNotSentCount, ssd.sms_schedule_start_date smsStartDateTime, "
	 		+ "ssd.sms_schedule_start_date smsEndDateTime, srm.sms_reason smsReason from sms_schedule_detail ssd "
	 		+ "left outer join sms_sent ss on ssd.sms_sent_id = ss.sms_sent_id left outer join sms_not_sent_details sns on ss.sms_sent_id = sns.sms_sent_id"
	 		+ " join batch_details bm on ss.batch_id = bm.batch_id join exam_master em on ss.exam_id = em.exam_id join sms_reason_master srm on ss.sms_reason_master_id = srm.sms_reason_master_id "
	 		+ "group by ss.sms_sent_id, ssd.sms_schedule_id, ss.sms_sent_id, bm.batch_name, em.exam_name, ss.message, ssd.sms_total_count, "
	 		+ "ss.number_of_sms_units, srm.sms_reason, ssd.sms_schedule_start_date, ssd.sms_schedule_end_date "
	 		+ "order by ss.sms_sent_id desc", nativeQuery = true)
	 AfcatSMSScheduleDetail findSMSScheduleDetailSummary();
	 
	 @Query(value="select ssd.sms_schedule_id from sms_schedule_detail ssd where ssd.sms_sent_id = ?1 order by ssd.sms_schedule_id limit 1", nativeQuery = true)
	 Long getFirstScheduler(Long smsSentId);
}
