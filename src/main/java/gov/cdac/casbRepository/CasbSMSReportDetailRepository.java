package gov.cdac.casbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.cdac.casbPojo.CasbSMSReportDetail;

@Repository
public interface CasbSMSReportDetailRepository extends JpaRepository<CasbSMSReportDetail,Long>{

	@Query(value="SELECT * FROM sms_report_details a WHERE a.sms_sent_id = (?1) and a.sms_schedule_id = (?2)", nativeQuery = true)
	CasbSMSReportDetail findBySmsSentIdAndSmsScheduleId(Long smsSentId, Long scheduledetailId);
}
