package gov.cdac.emailservice.casb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.cdac.emailservice.casb.pojo.CasbEmailTextReportDetails;
import gov.cdac.emailservice.projection.EmailReportSummary;

@Repository("casbEmailTextReportDetailsRepository")
public interface EmailTextReportDetailsRepository extends JpaRepository<CasbEmailTextReportDetails, Long>{

	@Query(value="SELECT e.report_path FROM email_text_report_details e WHERE e.email_id = ?1", nativeQuery = true)
	String findReportPathByEmailId(String emailId);

	@Query(value="SELECT et FROM email_text_report_details et WHERE et.email_id = ?1", nativeQuery = true)
	CasbEmailTextReportDetails getEmailTextReportDetailByEmailId(String emailId);
	
	@Query(value="SELECT count(*) FROM email_text_report_details", nativeQuery = true)
	Long getTotalCandidate();
	
	@Query(value="SELECT e.email_id emailId, e.report_path emailReportPath FROM email_text_report_details e", nativeQuery = true)
	List<EmailReportSummary> getAllReportPaths();
}
