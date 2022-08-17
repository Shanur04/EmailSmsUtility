package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.ExamMaster;
import gov.cdac.emailservice.icg.pojo.ExamSlot;
import gov.cdac.emailservice.projection.ExamSlotSummary2;

@Repository("icgExamSlotRepository")
public interface ExamSlotRepository extends JpaRepository<ExamSlot, Integer> {

	List<ExamSlot> findByExamMasterOrderByExamSlotCodeAsc(ExamMaster em);

	List<ExamSlot> findByExamMasterOrderByExamSlotCode(ExamMaster em);
	
	List<ExamSlotSummary2> findByExamMasterExamIdOrderByExamSlotCode(Integer examId);

}

