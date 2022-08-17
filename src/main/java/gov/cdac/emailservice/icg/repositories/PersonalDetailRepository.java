package gov.cdac.emailservice.icg.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.ApplicantCredential;
import gov.cdac.emailservice.icg.pojo.PersonalDetail;
import gov.cdac.emailservice.projection.ApplicantMobileSummary;
import gov.cdac.emailservice.projection.PersonalDetailSummary;

@Repository("icgPersonalDetailRepository")
public interface PersonalDetailRepository extends JpaRepository<PersonalDetail, Long> {

    /**
     * Query to find candidate details w.r.t. hallticket number generation ordered
     * by applicant_centre_allotment_id.
     * 
     * @return
     */
//    @Query("SELECT pd.applicantCredential.applicantCredId AS applicantCredId, "
//	    + "pd.emailidPrimary AS emailIdPrimary, pd.registrationNo AS registrationNumber, "
//	    + "pd.photoPath AS photoPath, aca.applicantCentreAllotmentId AS applicantCenterAllotmentId, aca.examCentreMapping.icgPostCombination.postCombinationCode AS postCode, "
//	    + "aca.examCentreMapping.centreExamslotMapping.centreMaster.examCityMaster.regionMaster.regionCode AS regionheadCode, aca.examCentreMapping.centreExamslotMapping.centreMaster.centreCode AS centreCode, "
//	    + "aca.examMaster.examYear AS examYear, aca.examMaster.examMonth AS examMonth, pd.casteCategoryDetail.casteCategoryMaster.categoryCode AS categoryCode, "
//	    + "aca.examCentreMapping.centreExamslotMapping.centreMaster.examCityMaster.examCityCode AS cityCode, aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotCode AS examSlotCode, "
//	    + "aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotId AS examSlotId, aca.examCentreMapping.icgPostCombination.writtenTestSection.writtenTestSectionAlias AS sectionCode "
//	    + "FROM ApplicantCentreAllotment aca LEFT OUTER JOIN ApplicantHallticket ah ON aca.applicantCredential.applicantCredId = ah.applicantCredential.applicantCredId "
//	    + "JOIN PersonalDetail pd ON pd.applicantCredential.applicantCredId = aca.applicantCredential.applicantCredId "
//	    + "WHERE aca.reexamStatus = false AND (ah.isNewHallticketNumber IS NULL OR ah.isNewHallticketNumber = true) ORDER BY aca.applicantCentreAllotmentId")
//    List<PersonalDetailSummary> findByRemainingApplicantCenter();
    
    @Query("SELECT pd.applicantCredential.applicantCredId AS applicantCredId, "
    	    + "pd.emailidPrimary AS emailIdPrimary, pd.registrationNo AS registrationNumber, "
    	    + "pd.photoPath AS photoPath, aca.applicantCentreAllotmentId AS applicantCenterAllotmentId, aca.examCentreMapping.icgPostCombination.postCombinationCode AS postCode, "
    	    + "aca.examCentreMapping.centreExamslotMapping.centreMaster.examCityMaster.regionMaster.regionCode AS regionheadCode, aca.examCentreMapping.centreExamslotMapping.centreMaster.centreCode AS centreCode, "
    	    + "aca.examMaster.examYear AS examYear, aca.examMaster.examMonth AS examMonth, pd.casteCategoryDetail.casteCategoryMaster.categoryId AS categoryCode, "
    	    + "aca.examCentreMapping.centreExamslotMapping.centreMaster.examCityMaster.examCityCode AS cityCode, aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotCode AS examSlotCode, "
    	    + "aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotId AS examSlotId, aca.examCentreMapping.icgPostCombination.writtenTestSection.writtenTestSectionAlias AS sectionCode "
    	    + "FROM ApplicantCentreAllotment aca LEFT OUTER JOIN ApplicantHallticket ah ON aca.applicantCredential.applicantCredId = ah.applicantCredential.applicantCredId "
    	    + "JOIN PersonalDetail pd ON pd.applicantCredential.applicantCredId = aca.applicantCredential.applicantCredId "
    	    + "WHERE aca.examMaster.examStatus = true AND ah.isNewHallticketNumber IS NULL ORDER BY aca.examCentreMapping.centreExamslotMapping.centreMaster.centreCode, aca.examCentreMapping.centreExamslotMapping.examSlot.examSlotCode, aca.examCentreMapping.icgPostCombination.postCombinationCode")
        List<PersonalDetailSummary> findByRemainingApplicantCenter();
    
    @Query("select pd from PersonalDetail pd where pd.applicantCredential.applicantCredId = ?1")
	List<PersonalDetail> getPersonalDetailsObjByAppCredId(Long appCredId);
    
    @Query("select pd from PersonalDetail pd where pd.applicantCredential.applicantCredId in :appcred")
   	List<PersonalDetail> getPersonalDetailsObjByAppCredIdInList(@Param("appcred")ArrayList<Long> appCredId);

    @Query("select pd from PersonalDetail pd where pd.registrationNo = ?1")
	List<PersonalDetail> getPersonalDetailsObjByRegNo(String regNo);
	
    @Query("select pd from PersonalDetail pd where pd.emailidPrimary = ?1")
	List<PersonalDetail> getPersonalDetailsObjByEmailId(String emailId);
    
    @Modifying
	@Query(value = "update PersonalDetail set isSeatallocationDone = false where"
			+ " applicantCredential in :appCredIds")
	public void updateSeatAllocationFlag(@Param("appCredIds")List<ApplicantCredential> appCredIds);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = true  where"
   			+ " emailidPrimary in :emailid")
	public void updateSeatAllocationFlagOnEmailId(@Param("emailid")String id);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = true  where"
   			+ " registrationNo in :regno")
	void updateSeatAllocationFlagOnRegNo(@Param("regno") String id);
    
    @Modifying
	@Query(value = "update PersonalDetail set isSeatallocationDone = true where"
			+ " applicantCredential.applicantCredId in :appCredIds")
	void updateSeatAllocationFlagOnApplicantId(@Param("appCredIds") Long id);

    @Query("select pd from PersonalDetail pd where pd.applicantCredential.applicantCredId = ?1")
	PersonalDetail getPersonalDetailsSingleObjByAppCredId(Long valueOf);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = false  where"
   			+ " emailidPrimary in :emailid")
	void unBlockAndUpdateSeatAllocationFlagOnEmailId(@Param("emailid") String id);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = false  where"
   			+ " registrationNo in :regno")
	void unBlockAndUpdateSeatAllocationFlagOnRegNo(@Param("regno") String id);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = false where"
   			+ " applicantCredential.applicantCredId in :appCredIds")
	void unBlockAndUpdateSeatAllocationFlagOnApplicantId(@Param("appCredIds")Long id);

    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = true where"
   			+ " applicantCredential.applicantCredId in :appCredIds")
	void blockandupdateSeatAllocationFlag(@Param("appCredIds")List<Long> appCreds);
    
    @Modifying
   	@Query(value = "update PersonalDetail set isSeatallocationDone = false where"
   			+ " applicantCredential.applicantCredId in :appCredIds")
	void unblockandupdateSeatAllocationFlag(@Param("appCredIds")List<Long> appCreds);

    
   	@Query(value = "select p from  PersonalDetail p where"
   			+ " p.emailidPrimary in :email")
	List<PersonalDetail> getPersonalDetailsObjByEmailIdList(@Param("email") ArrayList<String> emailIds);

 	@Query(value = "select p from  PersonalDetail p where"
   			+ " p.registrationNo in :regno")
	List<PersonalDetail> getPersonalDetailsObjByRegNoInList(@Param("regno") ArrayList<String> registrationNumbers);
    
    
    @Query(value = "SELECT pd.applicant_cred_id AS appCredId, pgp_sym_decrypt(CAST(pd.mobile AS bytea), 'icgregkey','compress-algo=0, cipher-algo=aes256') AS mobile FROM personal_details pd WHERE pd.applicant_cred_id IN (?1)", nativeQuery = true)
    List<ApplicantMobileSummary> findMobileByAppCredId(List<Long> appCredIds);

    @Query(value="select p from PersonalDetail p where p.registrationNo=:regno")
	PersonalDetail findByRegistrationNo(String regno);
    
    PersonalDetail findByEmailidPrimary(String emailidPrimary);

}
