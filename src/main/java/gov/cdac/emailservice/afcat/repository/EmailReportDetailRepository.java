package gov.cdac.emailservice.afcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatEmailReportDetail;

@Repository("afcatEmailReportDetailRepository")
public interface EmailReportDetailRepository extends JpaRepository<AfcatEmailReportDetail, Long>{

	@Query(value="SELECT * FROM email_report_details a WHERE a.email_sent_id = (?1) and a.email_schedule_id = (?2)", nativeQuery = true)
	AfcatEmailReportDetail findByEmailSentIdAndEmailScheduleId(Long emailSentId, Long scheduledetailId);
	
}
