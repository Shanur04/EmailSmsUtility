package gov.cdac.afcatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatSMSReportDetail;

@Repository
public interface AfcatSMSReportDetailRepository extends JpaRepository<AfcatSMSReportDetail,Long>{

	@Query(value="SELECT * FROM sms_report_details a WHERE a.sms_sent_id = (?1) and a.sms_schedule_id = (?2)", nativeQuery = true)
	AfcatSMSReportDetail findBySmsSentIdAndSmsScheduleId(Long smsSentId, Long scheduledetailId);
}
