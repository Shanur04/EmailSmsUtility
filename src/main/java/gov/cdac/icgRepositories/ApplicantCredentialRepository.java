package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ApplicantCredential;

@Repository("icgApplicantCredentialRepository")
public interface ApplicantCredentialRepository extends JpaRepository<ApplicantCredential, Long>{
	
	
	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 AND ac.rejectedReason like '%you have applied for Same Post%' AND ac.isEmailSentToDuplicateCandidates=false")
	public Integer getSamePostCount();
	
	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 AND ac.rejectedReason like '%you have applied for Same Post%'")
	public Integer getSamePostCountDashBoard();
	
	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 AND ac.rejectedReason like '%you have applied for multiple posts%' AND ac.isEmailSentToDuplicateCandidates=false")
	public Integer getMultiplePostCount();

	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 AND ac.rejectedReason like '%you have applied for multiple posts%'")
	public Integer getMultiplePostCountDashBoard();

	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 AND ac.isEmailSentToDuplicateCandidates=false")
	public Integer getAllPostCount();

	@Query("SELECT COUNT(ac) FROM ApplicantCredential ac WHERE ac.isRejected = true AND ac.registrationStatusId >=14 ")
	public Integer getAllPostCountDashBoard();

}
