package gov.cdac.afcatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatSMSReasonMaster;


@Repository("afcatSMSReasonMasterRepository")
public interface SMSReasonMasterRepository extends JpaRepository<AfcatSMSReasonMaster, Integer> {

    List<AfcatSMSReasonMaster> findAllByOrderBySMSReason();

}
