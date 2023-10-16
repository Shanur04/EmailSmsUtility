package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.SMSReportDetail;

@Repository("icgSMSReportDetailRepository")
public interface SMSReportDetailRepository extends JpaRepository<SMSReportDetail,Long>{

	@Query(value="SELECT * FROM sms_report_details a WHERE a.sms_sent_id = (?1) and a.sms_schedule_id = (?2)", nativeQuery = true)
	SMSReportDetail findBySmsSentIdAndSmsScheduleId(Long smsSentId, Long scheduledetailId);
}
