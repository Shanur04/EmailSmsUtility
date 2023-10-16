package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.ExamMaster;

@Repository("icgOfficerExamMasterRepository")
public interface IcgOfficerExamMasterRepository extends JpaRepository<ExamMaster, Integer> {

	@Query(" select em from ExamMaster em where em.examStatus = true and em.batchMaster.batchId = ?1")
	List<ExamMaster> getActiveExamListByBatchId(int batchId);

	@Query("select em from ExamMaster em where em.examStatus = true")
	ExamMaster getActiveExam();

    @Query("select em.examId from ExamMaster em where em.examStatus = true")
    Integer getActiveExamId();

}
