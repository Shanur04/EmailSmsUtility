package gov.cdac.emailservice.casb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.cdac.emailservice.casb.pojo.CasbEmailScheduleDetail;

@Repository("casbEmailScheduledDetailRepository")
public interface EmailScheduleDetailRepository extends JpaRepository<CasbEmailScheduleDetail, Long>{
	@Query(value = "SELECT * FROM email_schedule_detail es WHERE es.email_sent_id = ?1 order by es.email_schedule_id", nativeQuery = true)
	List<CasbEmailScheduleDetail> findByEmailSentId(Long emailSentId);
	
	 @Query(value="select es.email_schedule_id from email_schedule_detail es where es.email_sent_id = ?1 order by es.email_schedule_id limit 1", nativeQuery = true)
	Long findFirstSchedulerByEmailSentId(Long emailSentId);
	 
	public <S extends CasbEmailScheduleDetail>S save(S entity);

}
