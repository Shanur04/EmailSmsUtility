package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.ApplicantCentreAllotment;
import gov.cdac.projection.CentreSlotCandidateCount;

@Repository("icgOfficerApplicantCentreAllotmentRepository")
public interface IcgOfficerApplicantCentreAllotmentRepository extends JpaRepository<ApplicantCentreAllotment, Long> {

    @Query(value = "select applicant_cred_id from applicant_center_allotment aca "
	    + "join exam_center_mapping ecm on aca.exam_center_mapping_id = ecm.exam_center_mapping_id "
	    + "join centre_examslot_mapping cexm on ecm.centre_examslot_mappingid = cexm.centre_examslot_mappingid "
	    + "where cexm.centre_id = ?1 and cexm.exam_slot_id in (?2) ", nativeQuery = true)
    public List<Long> findAppCredIdByCentreIdAndExamSlotId(Integer centreId, List<Integer> examSlotIds);

    @Query(value = "select es.exam_slot_name slot, count(aca.applicant_cred_id) count from applicant_center_allotment aca "
	    + "join exam_center_mapping ecm on aca.exam_center_mapping_id = ecm.exam_center_mapping_id "
	    + "join centre_examslot_mapping cexm on ecm.centre_examslot_mappingid = cexm.centre_examslot_mappingid "
	    + "join exam_slot es on es.exam_slot_id = cexm.exam_slot_id where cexm.centre_id = ?1 and es.exam_slot_id in (?2) "
	    + "group by es.exam_slot_name order by es.exam_slot_name", nativeQuery = true)
    public List<CentreSlotCandidateCount> findCandidateCountByCentreIdAndExamSlotId(Integer centreId,
	    List<Integer> examSlotIds);
}
