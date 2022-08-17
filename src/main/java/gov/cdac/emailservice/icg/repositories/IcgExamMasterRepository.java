package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.ExamMaster;

@Repository("icgExamMasterRepository")
public interface IcgExamMasterRepository extends JpaRepository<ExamMaster, Integer> {
	
	@Query(" select em from ExamMaster em where em.examStatus = true and em.batchMaster.batchId = ?1")
	List<ExamMaster> getActiveExamListByBatchId(int batchId);

	@Query("select em from ExamMaster em where em.examStatus = true")
	ExamMaster getActiveExam();

	@Query("select em.examTypeMaster.examType from ExamMaster em where em.examStatus = true")
	String getActiveExamType();
	
    @Query("select em.examId from ExamMaster em where em.examStatus = true")
    Integer getActiveExamId();
}
