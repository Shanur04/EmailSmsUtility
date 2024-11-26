package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.CentreMaster;
import gov.cdac.models.CentreModel;


@Repository("icgCentreMasterRepository")
public interface CentreMasterRepository extends JpaRepository<CentreMaster, Integer>{


	@Query("select count(*) from CentreMaster cm where cm.examMaster.examId=?1")
	public int getAllCentresCount(Integer examId);
	
	@Query("select cem  from CentreMaster cem order by cem.centreCode")
	public List<CentreMaster> getAllCentresToGenerateAdmitCard();

	@Query("select new gov.cdac.models.CentreModel(cem.centreId,cem.centreName,cem.centreCode) from CentreMaster cem "
			+ "where cem.examMaster.examId=?1 order by cem.centreCode")
	public List<CentreModel> findAllCentres(Integer examId);
	
	@Query("select cem from CentreMaster cem where cem.centreId= :centreId")
	public CentreMaster findBycentreId(@Param("centreId")Integer centreId);

	@Query("select cm.centreCode from CentreMaster cm where cm.examMaster.examId=?1")
	public List<String> getAllCentresCodes(Integer examId);
	
	@Query("select cm from CentreMaster cm where cm.examCityMaster.examCityId=:cityid")
	public List<CentreMaster> getAllCentresCodesbyCityId(@Param("cityid") Integer cityid);

	CentreMaster findBycentreCode(String centreCode);

	@Query(" select cm from CentreMaster cm where cm.centreStatus = true and cm.examCityMaster.examCityId = ?1")
	List<CentreMaster> getCentreListByCityId(int cityId);
	
	@Query("select count(*) from CentreMaster cm where cm.centreId in"
			+ "(select cem.centreMaster.centreId from CentreExamslotMapping cem where cem.centreExamslotMappingId in"
			+ "(select ecm.centreExamslotMapping.centreExamslotMappingId from ExamCentreMapping ecm where ecm.examCentreMappingId in"
			+ "(select aca.examCentreMapping.examCentreMappingId from ApplicantCentreAllotment aca where aca.applicantCredential.applicantCredId in"
			+ "(select ah.applicantCredential.applicantCredId from ApplicantHallticket ah where ah.isAdmitCardGenerated=false))) and cm.examMaster.examId=?1 order by  cm.centreCode)")   
	public int getAllCentresCountInApplicantHallTicket(Integer examId);

	@Query("select cm from CentreMaster cm  where cm.centreId in (select ecm.centreMaster.centreId from CentreExamslotMapping ecm where ecm.examSlot.examSlotId = :slotid)")
	public List<CentreMaster> getAllCentresCodesbySlotId(@Param("slotid") Integer slotId);

	@Query("select cm from CentreMaster cm where cm.centreId in (select c.centreMaster.centreId from CentreExamslotMapping c where  c.centreExamslotMappingId in (select ecm.centreExamslotMapping.centreExamslotMappingId  from ExamCentreMapping ecm where ecm.examCentreMappingId=:examcentreid))")
	public CentreMaster findCentreByexamcentreMappingId(@Param("examcentreid")Integer exam_centre_id);
	
}
