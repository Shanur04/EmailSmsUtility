package gov.cdac.emailservice.icg.officer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerMailReasonMaster;

@Repository("icgOfficerEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<IcgOfficerMailReasonMaster, Integer> {

    List<IcgOfficerMailReasonMaster> findAllByOrderByEmailReason();
}
