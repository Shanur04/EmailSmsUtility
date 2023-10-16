package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ExamMaster;

@Repository("icgExamMasterRepository")
public interface ExamMasterRepository extends JpaRepository<ExamMaster, Integer> {

    @Query("select em.examId from ExamMaster em where em.examStatus = true")
    Integer getActiveExamId();

}
