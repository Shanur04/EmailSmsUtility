package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerEmailReportDetail;

@Repository("icgOfficerEmailReportDetailRepository")
public interface EmailReportDetailRepository extends JpaRepository<IcgOfficerEmailReportDetail, Long>{

	@Query(value="SELECT * FROM email_report_details a WHERE a.email_sent_id = (?1) and a.email_schedule_id = (?2)", nativeQuery = true)
	IcgOfficerEmailReportDetail findByEmailSentIdAndEmailScheduleId(Long emailSentId, Long scheduledetailId);
	
}
