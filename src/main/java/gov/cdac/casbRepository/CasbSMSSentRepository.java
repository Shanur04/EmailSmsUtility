package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbSMSSent;
import gov.cdac.projection.SMSSentSummary;

@Repository("casbSMSSentRepository")
public interface CasbSMSSentRepository extends JpaRepository<CasbSMSSent, Long> {

    List<CasbSMSSent> findAllByOrderBySMSSentIdDesc();

    @Query(value = "select bm.batch_name batchName, em.exam_name examName, ss.message smsMessage, ss.candidates_count candidateCount, ss.number_of_sms_units numberOfSMSUnits, "
	    + "count(sns.sms_not_sent_details_id) smsNotSentCount, ss.sms_start_datetime smsStartDateTime, ss.sms_end_datetime smsEndDateTime, srm.sms_reason smsReason "
	    + "from sms_sent ss left outer join sms_not_sent_details sns on ss.sms_sent_id = sns.sms_sent_id "
	    + "join batch_details bm on ss.batch_id = bm.batch_id join exam_master em on ss.exam_id = em.exam_id "
	    + "join sms_reason_master srm on ss.sms_reason_master_id = srm.sms_reason_master_id "
	    + "group by ss.sms_sent_id, bm.batch_name, em.exam_name, ss.message, ss.candidates_count, ss.number_of_sms_units, srm.sms_reason, "
	    + "ss.sms_start_datetime, ss.sms_end_datetime order by ss.sms_sent_id desc", nativeQuery = true)
    List<SMSSentSummary> findSMSSentSummary();

    CasbSMSSent findTop1ByOrderBySMSSentIdDesc();
    
    @Query("SELECT s FROM CasbSMSSent s WHERE s.smsSentStatus = false")
    List<CasbSMSSent> getScheduleSMSSentList();
       

}
