package gov.cdac.emailservice.icg.officer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.ExamSlot;
import gov.cdac.emailservice.projection.ExamSlotSummary;

@Repository("icgOfficerExamSlotRepository")
public interface IcgOfficerExamSlotRepository extends JpaRepository<ExamSlot, Integer> {

	 List<ExamSlotSummary> findByExamMasterExamIdOrderByExamSlotCode(Integer examId);

}
