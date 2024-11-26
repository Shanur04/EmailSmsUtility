package gov.cdac.icgRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ApplicantHallticket;
import gov.cdac.projection.EmailProjection;

@Repository("icgApplicantHallTicketRepository")
public interface ApplicantHallTicketRepository extends JpaRepository<ApplicantHallticket, Long> {

	
	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examMaster.examId=?1 and ah.isAdmitCardGenerated=false")
	public Long getTotalCountOfAppCredIds(int examid);
	
	
	@Modifying
	@Query("update ApplicantHallticket ah set ah.isAdmitCardGenerated=true where ah.applicantCredential.applicantCredId = ?1")
	public void updateIsAdmitCardGeneratedFlagToTrue(Long applicantCredId);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examMaster.examId=?1")
	int getTotalHallTicketNosCount(int examid);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.isAdmitCardGenerated=true and ah.applicantCentreAllotment.examMaster.examId=?1")
	public int getTotalAdmitCardGeneratedCount(int examid);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.isAdmitCardGenerated=false and ah.applicantCentreAllotment.examMaster.examId=?1")
	public int getRemainingAdmitCardCount(int examid);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode=?1 and ah.isAdmitCardGenerated=true")
	public int getTotalAdmitCardGeneratedForSelectedCentre(String centerCode);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode=?1")
	public int getTotalHallTicketNosCountForSelectedCentre(String centerCode);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode=?1 and ah.isAdmitCardGenerated=false")
	public int getAdmitCardGeneratedYetToGenerateSingle(String centerCode);
		
	@Query("select ac.emailid as emailId,ac.rejectedReason as rejectedReason,ac.rejectedReasonHindi as hindiReason from ApplicantCredential ac Where ac.isRejected=true AND ac.registrationStatusId>=14 AND ac.rejectedReason LIKE '%you have applied for Same Post%' AND ac.isEmailSentToDuplicateCandidates=false")
	public List<EmailProjection> getAllRejectedEmailIdWithReasonForSamePost();
	

	@Query("select ac.emailid as emailId,ac.rejectedReason as rejectedReason,ac.rejectedReasonHindi as hindiReason from ApplicantCredential ac Where ac.isRejected=true AND ac.registrationStatusId>=14 AND ac.rejectedReason LIKE '%you have applied for multiple posts%' AND ac.isEmailSentToDuplicateCandidates=false")
	public List<EmailProjection> getAllRejectedEmailIdWithReasonForMultiplePost();
	
	@Query("select ac.emailid as emailId,ac.rejectedReason as rejectedReason,ac.rejectedReasonHindi as hindiReason from ApplicantCredential ac Where ac.isRejected=true AND ac.registrationStatusId>=14 AND ac.isEmailSentToDuplicateCandidates=false")
	public List<EmailProjection> getAllRejectedEmailIdWithReasonForAllPost();

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode in (?1)")
	public int getTotalHallTicketNosCountForSelectedCentres(String[] centerCode);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode in (?1) and ah.isAdmitCardGenerated=true")
	public int getTotalAdmitCardGeneratedForSelectedCentres(String[] centerCode);

	@Query("select count(ah.applicantCredential.applicantCredId) from ApplicantHallticket ah where ah.applicantCentreAllotment.examCentreMapping.centreExamslotMapping.centreMaster.centreCode in (?1) and ah.isAdmitCardGenerated=false")
	public int getAdmitCardGeneratedYetToGenerateMulti(String[] centerCode);

	@Query("select ah.emailid,ah.hallticketNumber from ApplicantHallticket ah where ah.isEmailSent=false "
			+ "and (substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 3,1) in (:slotsArrayList)) "
			+ "and (substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 11,3) in (:centersArrayList))"
			+ "order by substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 11,3)")
	List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(@Param(value = "slotsArrayList") ArrayList<String> slotsArrayList,
			@Param(value = "centersArrayList") ArrayList<String> centersArrayList);

	@Modifying
	@Query("update ApplicantHallticket ah set ah.isEmailSent=:flagToSet where ah.emailid in (:emailIds)")
	public void updateEmailSentFlag(@Param(value = "emailIds") Set<String> mailSentSet,@Param(value = "flagToSet") boolean flagToSet);

	
	
	
	@Query("select ah.emailid,ah.hallticketNumber from ApplicantHallticket ah where ah.isNewHallticketNumber=true "
			+ "and (substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 3,1) in (:slot)) "
			+ "and (substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 11,3) in (:center)) "
			+ "order by substring(ah.hallticketNumber,LENGTH(ah.hallticketNumber) - 11,3)")
	public List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(@Param(value = "slot") ArrayList<String> slot,
			@Param(value = "center") ArrayList<String> center);
	
	
	@Query("select count(*) from ApplicantHallticket ah where ah.isEmailSent=true")
	Integer getTotalEmailSentCount();

	@Query("select ah from ApplicantHallticket ah where ah.isEmailSent=false")
	List<ApplicantHallticket> getTotalEmailNotSentData();
	
	
	@Query("select ah from ApplicantHallticket ah where ah.emailid =?1 and ah.applicantCentreAllotment.examMaster.examStatus=true")
	public ApplicantHallticket getAdmitCardDetailOfEmail(@Param("emailId")String emailId);

	@Query("select ah from ApplicantHallticket ah where ah.registrationNo = ?1 and ah.applicantCentreAllotment.examMaster.examStatus=true")
	public ApplicantHallticket getAdmitCardDetailsOfRegNo(String regNo);

	@Query("select ah from ApplicantHallticket ah where ah.hallticketNumber = ?1 and ah.applicantCentreAllotment.examMaster.examStatus=true")
	public ApplicantHallticket getAdmitCardDetailHallTicketNo(String halltktno);

	
	@Query("SELECT COUNT(*) FROM ApplicantHallticket ah WHERE ah.isDummyImage = true")
	public long getDummyCount();

//	@Query("select new gov.cdac.emailservice.icg.models.AdmitCardData(pd.name,ah.hallticketNumber,ah.registrationNo,ah.applicantCredential.applicantCredId,ah.photograph) from ApplicantHallticket ah,PersonalDetail pd where ah.hallticketNumber like %:halltktno% and "
//			+ "ah.applicantCredential.applicantCredId=pd.applicantCredential.applicantCredId")
//	public List<AdmitCardData> getCandidateDataByCentreSlot(String halltktno);


	@Query("select ah from ApplicantHallticket ah where ah.photograph=?1")
	public ApplicantHallticket findByHallTicketPath(String halltktpath);

	@Modifying
	@Query("update ApplicantCredential ah set ah.isEmailSentToDuplicateCandidates=:b where ah.emailid in (:mailSentSet)")
	public void updateEmailSentFlagInApplicantCredential(Set<String> mailSentSet, boolean b);

}
