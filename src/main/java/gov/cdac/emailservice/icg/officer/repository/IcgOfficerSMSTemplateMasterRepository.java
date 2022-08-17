package gov.cdac.emailservice.icg.officer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.SmsTemplateMaster;

@Repository("icgOfficerSMSTemplateMasterRepository")
public interface IcgOfficerSMSTemplateMasterRepository extends JpaRepository<SmsTemplateMaster,Integer> {

    @Query("SELECT MAX(stm.templateMasterId) FROM SmsTemplateMaster stm")
    Integer findMaxTemplateMasterId();

    List<SmsTemplateMaster> findAllByOrderBySMSTemplateMasterIdDesc();
}
