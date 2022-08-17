package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.ApplicantHallticket;

@Repository("icgHallTicketRepository")
public interface HallTicketRepository extends JpaRepository<ApplicantHallticket, Long> {
	
	/**
	 * This method returns list of hallTicketMappings related to one particular
	 * centre and examSlot
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN \r\n"
			+ "(select ap.applicantCredential.applicantCredId from ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId in (select exm.examCentreMappingId from ExamCentreMapping exm join exm.centreExamslotMapping cex where cex.centreMaster.centreId = :centreId and cex.examSlot.examSlotId = :slotId ))")
	public List<ApplicantHallticket> getHallTicketMappingsCentreSlotWise(@Param("centreId") Integer centreId,
			@Param("slotId") Integer slotId);
	
	/**
	 * This fetchs out hallrticketmappings based on city based criteria
	 * 
	 * @param cityId
	 * @return
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential in (select ap.applicantCredential.applicantCredId from ApplicantCentreAllotment ap join ap.examCentreMapping ex where ex.centreExamslotMapping.centreExamslotMappingId in (select reg.centreExamslotMappingId from CentreExamslotMapping reg where reg.centreMaster.examCityMaster.examCityId= :cityId))")
	public List<ApplicantHallticket> getHallTicketMapping(@Param("cityId") Integer cityId);
	
	/**
	 * This fetchs out hallticketmappings based on centre based criteria (All Slots)
	 * 
	 * @param centreId
	 * @return
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId in (select ap.applicantCredential.applicantCredId from ApplicantCentreAllotment ap join ap.examCentreMapping ex where ex.centreExamslotMapping.centreExamslotMappingId = (select rm.centreExamslotMappingId from CentreExamslotMapping rm where rm.centreMaster.centreId= :centreId))")
	public List<ApplicantHallticket> getHallTicketMappingCentreWise(@Param("centreId") Integer centreId);
	
	/**
	 * this method returns all the hallticket mapped to one particular slot
	 * 
	 * @param slotId
	 * @return
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN (select ap.applicantCredential.applicantCredId from ApplicantCentreAllotment ap where ap.examCentreMapping.examCentreMappingId in (select exm.examCentreMappingId from ExamCentreMapping exm join exm.centreExamslotMapping cex where  cex.examSlot.examSlotId = :slotId ))")
	public List<ApplicantHallticket> getHallTicketSlotWise(@Param("slotId") Integer slotId);
	
	/**
	 * This method will get hallticketmapping object based on email id provided
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.emailidPrimary = :emailId )")
	public ApplicantHallticket getHallTickMapOnEmail(@Param("emailId") String emailId);

	/**
	 * This method will yield hallticketmapping object based on Reg Num
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.registrationNo IN :regNo )")
	public ApplicantHallticket getHallTickMapByRegNum(@Param("regNo") String regNo);

	/**
	 * This method will fetch out HallTicketMapping based on appCredId provided
	 */
	@Query("select hmp from ApplicantHallticket hmp JOIN hmp.applicantCredential apcred where apcred.applicantCredId IN :appCredId")
	public ApplicantHallticket getHallTicketsByAppCredId(@Param("appCredId") Long appCredId);
	
	/**
	 * This method will get us the list of hallticket mappings by appCredIds
	 * 
	 * @param applicantCredential
	 * @return
	 */
	@Query("select hmp from ApplicantHallticket hmp JOIN hmp.applicantCredential apcred where apcred.applicantCredId IN :appCredIds")
	public List<ApplicantHallticket> getHallTicketsByAppCredIds(@Param("appCredIds") List<Long> appCredIds);
	
	
	/**
	 * This method will get Hallticketmappings based on Registration Numbers
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.registrationNo IN :regNums)")
	public List<ApplicantHallticket> getHallTicketMappingsOnRegNums(@Param("regNums") List<String> regNums);

	/**
	 * This method pulls out hallTicketMappings based on the primary Email Ids
	 * Provided
	 */
	@Query("select hmp from ApplicantHallticket hmp where hmp.applicantCredential.applicantCredId IN (select afp.applicantCredential.applicantCredId from PersonalDetail afp where afp.emailidPrimary IN :emailIds)")
	public List<ApplicantHallticket> getHallTicketsMappingOnEmailIds(@Param("emailIds") List<String> emailIDs);

	


}
