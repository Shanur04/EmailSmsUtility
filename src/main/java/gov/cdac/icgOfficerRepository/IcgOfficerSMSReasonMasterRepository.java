package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.SmsReasonMaster;
import gov.cdac.models.SMSReasonMaster;

@Repository("icgOfficerSMSReasonMasterRepository")
public interface IcgOfficerSMSReasonMasterRepository extends JpaRepository<SmsReasonMaster, Integer> {

     List<SMSReasonMaster> findAllByOrderBySMSReason();
}
