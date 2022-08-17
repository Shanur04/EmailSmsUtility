package gov.cdac.emailservice.casb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.cdac.emailservice.casb.pojo.CasbEmailReportDetail;

@Repository("casbEmailReportDetailRepository")
public interface EmailReportDetailRepository extends JpaRepository<CasbEmailReportDetail, Long>{
	@Query(value="SELECT * FROM email_report_details a WHERE a.email_sent_id = (?1) and a.email_schedule_id = (?2)", nativeQuery = true)
	CasbEmailReportDetail findByEmailSentIdAndEmailScheduleId(Long emailSentId, Long scheduledetailId);

}
