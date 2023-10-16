package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ExamMaster;
import gov.cdac.icgPojo.ExamSlot;
import gov.cdac.projection.ExamSlotSummary2;

@Repository("icgExamSlotRepository")
public interface ExamSlotRepository extends JpaRepository<ExamSlot, Integer> {

	List<ExamSlot> findByExamMasterOrderByExamSlotCodeAsc(ExamMaster em);

	List<ExamSlot> findByExamMasterOrderByExamSlotCode(ExamMaster em);
	
	List<ExamSlotSummary2> findByExamMasterExamIdOrderByExamSlotCode(Integer examId);

}

