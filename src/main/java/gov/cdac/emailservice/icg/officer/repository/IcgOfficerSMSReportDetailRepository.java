package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerSMSReportDetail;
@Repository("icgOfficerSMSReportDetailRepository")
public interface IcgOfficerSMSReportDetailRepository extends JpaRepository<IcgOfficerSMSReportDetail,Long>{

	@Query(value="SELECT * FROM sms_report_details a WHERE a.sms_sent_id = (?1) and a.sms_schedule_id = (?2)", nativeQuery = true)
	IcgOfficerSMSReportDetail findBySmsSentIdAndSmsScheduleId(Long smsSentId, Long scheduledetailId);
	
}
