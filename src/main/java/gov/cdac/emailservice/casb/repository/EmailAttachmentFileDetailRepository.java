package gov.cdac.emailservice.casb.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.casb.pojo.CasbEmailAttachmentFileDetail;


@Repository("casbEmailAttachmentFileDetailRepository")
public interface EmailAttachmentFileDetailRepository extends JpaRepository<CasbEmailAttachmentFileDetail, Long>{
	@Query(value="SELECT e.file_path FROM email_Attachment_File_detail e WHERE e.email_sent_id = ?1", nativeQuery = true)
	ArrayList<String> findByEmailSentId(Long emailSentId);

}
