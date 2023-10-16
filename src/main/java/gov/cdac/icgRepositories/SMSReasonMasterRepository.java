package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.IcgSMSReasonMaster;

@Repository("icgSMSReasonMasterRepository")
public interface SMSReasonMasterRepository extends JpaRepository<IcgSMSReasonMaster, Integer> {

    List<IcgSMSReasonMaster> findAllByOrderBySMSReason();
}
