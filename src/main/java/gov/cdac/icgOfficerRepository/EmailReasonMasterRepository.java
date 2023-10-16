package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.IcgOfficerMailReasonMaster;

@Repository("icgOfficerEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<IcgOfficerMailReasonMaster, Integer> {

    List<IcgOfficerMailReasonMaster> findAllByOrderByEmailReason();
}
