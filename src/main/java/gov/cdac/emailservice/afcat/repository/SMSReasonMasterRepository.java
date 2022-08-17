package gov.cdac.emailservice.afcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatSMSReasonMaster;


@Repository("afcatSMSReasonMasterRepository")
public interface SMSReasonMasterRepository extends JpaRepository<AfcatSMSReasonMaster, Integer> {

    List<AfcatSMSReasonMaster> findAllByOrderBySMSReason();

}
