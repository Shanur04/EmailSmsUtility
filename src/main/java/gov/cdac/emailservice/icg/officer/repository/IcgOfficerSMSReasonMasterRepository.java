package gov.cdac.emailservice.icg.officer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.SmsReasonMaster;
import gov.cdac.emailservice.models.SMSReasonMaster;

@Repository("icgOfficerSMSReasonMasterRepository")
public interface IcgOfficerSMSReasonMasterRepository extends JpaRepository<SmsReasonMaster, Integer> {

     List<SMSReasonMaster> findAllByOrderBySMSReason();
}
