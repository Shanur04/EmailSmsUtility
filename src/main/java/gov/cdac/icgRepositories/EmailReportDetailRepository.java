package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.IcgEmailReportDetail;

@Repository("icgEmailReportDetailRepository")
public interface EmailReportDetailRepository extends JpaRepository<IcgEmailReportDetail, Long>{

	@Query(value="SELECT * FROM email_report_details a WHERE a.email_sent_id = (?1) and a.email_schedule_id = (?2)", nativeQuery = true)
	IcgEmailReportDetail findByEmailSentIdAndEmailScheduleId(Long emailSentId, Long scheduledetailId);
	
}