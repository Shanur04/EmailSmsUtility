package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.ExamSlot;
import gov.cdac.projection.ExamSlotSummary;

@Repository("icgOfficerExamSlotRepository")
public interface IcgOfficerExamSlotRepository extends JpaRepository<ExamSlot, Integer> {

	 List<ExamSlotSummary> findByExamMasterExamIdOrderByExamSlotCode(Integer examId);

}
