package gov.cdac.icgRepositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ApplicantCentreAllotment;
import gov.cdac.models.CentreModel;
import gov.cdac.projection.CentreSlotCandidateCount;

/**
 * 
 * @author shanurj
 *
 */

@Repository("icgApplicantCentreAllotmentRepository")
public interface ApplicantCentreAllotmentRepository extends JpaRepository<ApplicantCentreAllotment, Long>{

	@Query("Select aca from ApplicantCentreAllotment aca where aca.applicantCredential.applicantCredId = ?1 and aca.examMaster.examStatus = true"
			+ " Order By aca.applicantCentreAllotmentId Desc")
	List<ApplicantCentreAllotment> findApplicantCenterAllotmentObjectByAppCredAndIfRexamStatusInDesc(
			Long applicantCredId);
	
	
	/**
	 * This fetchs out appCentreAllotment on city based criteria
	 * 
	 * @param cityId
	 * @return
	 */
	@Query("SELECT ap FROM ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId IN (select ex.examCentreMappingId from ExamCentreMapping ex JOIN ex.centreExamslotMapping re where ex.centreExamslotMapping.centreExamslotMappingId IN (select reg.centreExamslotMappingId from CentreExamslotMapping reg where reg.centreMaster.examCityMaster.examCityId=:cityId))")
	public ArrayList<ApplicantCentreAllotment> getApplicantCentreAllotment(@Param("cityId") Integer cityId);
	
	/**
	 * This method will fetch out List of applicantCentreAllotments based on
	 * centreId and slotId
	 */
	@Query("select ap from ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId in (select exm.examCentreMappingId from ExamCentreMapping exm join exm.centreExamslotMapping cex where cex.centreMaster.centreId = :centreId and cex.examSlot.examSlotId = :slotId )")
	public ArrayList<ApplicantCentreAllotment> getApplicantCentreAllotmentCentreSlotWise(
			@Param("centreId") Integer centreId, @Param("slotId") Integer slotId);
	
	/**
	 * This fetchs out appCentreAllotment based on centreId(All the Slots)
	 * 
	 * @param centreId
	 * @return
	 */
	@Query("SELECT ap FROM ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId IN (select ex.examCentreMappingId from ExamCentreMapping ex JOIN ex.centreExamslotMapping re where ex.centreExamslotMapping.centreExamslotMappingId IN (select reg.centreExamslotMappingId from CentreExamslotMapping reg where reg.centreMaster.centreId=:centreId))")
	public ArrayList<ApplicantCentreAllotment> getApplicantCentreAllotmentCentreWise(
			@Param("centreId") Integer centreId);
		
	/**
	 * This method returns List of ApplicantCentreAllotment belonging to one
	 * particular slot
	 * 
	 * @param slotId
	 * @return
	 */
	@Query("select ap from ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId in (select exm.examCentreMappingId from ExamCentreMapping exm join exm.centreExamslotMapping cex where cex.examSlot.examSlotId = :slotId )")
	public List<ApplicantCentreAllotment> getApplicantCentreAllotmentSlotWise(@Param("slotId") Integer slotId);
	
	
	/**
	 * This method will fetch the ApplicantCentreAllotment based on Email Id
	 * Provided
	 */
	@Query("select apc from ApplicantCentreAllotment apc where apc.applicantCredential.applicantCredId = (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.emailidPrimary = :emailId)")
	public ApplicantCentreAllotment getApplicantCentreAllotmentOnEmail(@Param("emailId") String email);

	/**
	 * This method will get applicantCentreAllotment based on Registration Number
	 * provided
	 */
	@Query("select apc from ApplicantCentreAllotment apc where apc.applicantCredential.applicantCredId = (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.registrationNo = :regNo)")
	public ApplicantCentreAllotment getApplicantCentreAllotmentOnRegNum(@Param("regNo") String regNo);

	/**
	 * This method will get ApplicantCentreAllotment based on AppcredId provided
	 */
	@Query("select ap from ApplicantCentreAllotment ap where ap.applicantCredential.applicantCredId = :appId")
	public ApplicantCentreAllotment getApplicantCentreAllotmentOnAppCredId(@Param("appId") Long appId);
	
	
	/**
	 * This method will bring out applicantCentreAllotment objects based on the
	 * AppcredIds provided
	 */
	@Query("select ap from ApplicantCentreAllotment ap where ap.applicantCredential.applicantCredId IN :appCredIds")
	public ArrayList<ApplicantCentreAllotment> getApplicantCentreAllotmentIdWise(
			@Param("appCredIds") List<Long> appCredIds);

	/**
	 * This method will get the list of ApplicantCentreAllotment on the basis of
	 * Registration Numbers provided
	 * 
	 */
	@Query("select apc from ApplicantCentreAllotment apc where apc.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.registrationNo IN :regNums)")
	public List<ApplicantCentreAllotment> getAppCentreAllotmentOnRegistrationNums(
			@Param("regNums") List<String> regNums);

	/**
	 * This method will fetch out ApplicantCentreAllotment based on primary email id
	 */
	@Query("select apc from ApplicantCentreAllotment apc where apc.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.emailidPrimary IN :emailIds)")
	public List<ApplicantCentreAllotment> getAppCentreAllotmentOnEmailIds(@Param("emailIds") List<String> emailIds);
	
	/**
	 * This method will fetch details of allocation of applicant(populated in
	 * CentreModel) based on regNumber or AppcredId or PrimaryEmail id
	 */
	
	@Query("select new gov.cdac.models.CentreModel(exm.centreExamslotMapping.examSlot.examSlotName,exm.centreExamslotMapping.centreMaster.centreName) from ApplicantCentreAllotment apc JOIN apc.examCentreMapping exm where apc.applicantCredential.applicantCredId = (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.emailidPrimary = :email )")
	public CentreModel getCandidateAllocationDetailsOnEmail(@Param("email") String email);
	
    @Query("SELECT aca.applicantCredential.applicantCredId FROM ApplicantCentreAllotment aca WHERE aca.examCentreMapping.centreExamslotMapping.centreMaster.centreId = ?1 "
    	    + "AND aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotId IN (?2)")
        public List<Long> findAppCredIdByCentreIdAndExamSlotId(Integer centreId, List<Integer> examSlotIds);

        @Query("SELECT aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotName AS slot, "
    	    + "COUNT(aca.applicantCredential.applicantCredId) AS count FROM ApplicantCentreAllotment aca WHERE aca.examCentreMapping.centreExamslotMapping.centreMaster.centreId = ?1 "
    	    + "AND aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotId IN (?2) GROUP BY aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotName "
    	    + "ORDER BY aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotName")
        public List<CentreSlotCandidateCount> findCandidateCountByCentreIdAndExamSlotId(Integer centreId,
    	    List<Integer> examSlotIds);

}
