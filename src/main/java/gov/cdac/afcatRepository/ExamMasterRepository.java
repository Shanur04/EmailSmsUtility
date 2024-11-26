package gov.cdac.afcatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatExamMaster;

@Repository("afcatExamMasterRepository")
public interface ExamMasterRepository extends JpaRepository<AfcatExamMaster, Integer> {

    @Query("select em.examId from AfcatExamMaster em where em.examStatus = true")
    Integer getActiveExamId();
    
	@Query(" select em from AfcatExamMaster em where em.examStatus = true and em.batchMaster.batchId = ?1")
	List<AfcatExamMaster> getActiveExamListByBatchId(int batchId);
	
	
	@Query("select em from AfcatExamMaster em where em.examStatus = true")
	AfcatExamMaster getActiveExam();
	
}
