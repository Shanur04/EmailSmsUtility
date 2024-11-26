package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbExamMaster;
import gov.cdac.icgPojo.ExamMaster;

@Repository("casbExamMasterRepository")
public interface ExamMasterRepository extends JpaRepository<CasbExamMaster, Integer> {

    @Query("select em.examId from CasbExamMaster em where em.examStatus = true")
    Integer getActiveExamId();

	@Query(" select em from CasbExamMaster em where em.examStatus = true and em.batchMaster.batchId = ?1")
	List<CasbExamMaster> getActiveExamListByBatchId(int batchId);

	@Query("select em from CasbExamMaster em where em.examStatus = true")
	ExamMaster getActiveExam();

}
