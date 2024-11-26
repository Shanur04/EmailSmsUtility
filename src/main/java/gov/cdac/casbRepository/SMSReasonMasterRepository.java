package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbSMSReasonMaster;

@Repository("casbSMSReasonMasterRepository")
public interface SMSReasonMasterRepository extends JpaRepository<CasbSMSReasonMaster, Integer> {

    List<CasbSMSReasonMaster> findAllByOrderBySMSReason();

}
