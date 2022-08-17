package gov.cdac.emailservice.casb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.casb.pojo.CasbExamSlot;
import gov.cdac.emailservice.projection.ExamSlotSummary;

@Repository("casbExamSlotRepository")
public interface ExamSlotRepository extends JpaRepository<CasbExamSlot, Integer> {

    @Query(value = "select es.exam_slot_id AS examSlotId, es.exam_date AS examDate, es.exam_slot_code AS examSlotCode, es.exam_slot_name AS examSlotName "
	    + "from exam_slot es where es.exam_slot_status = true order by es.exam_slot_code", nativeQuery = true)
    List<ExamSlotSummary> findByOrderByCode();

}
