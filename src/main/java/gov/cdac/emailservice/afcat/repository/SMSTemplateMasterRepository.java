package gov.cdac.emailservice.afcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatSMSTemplateMaster;


@Repository("afcatSMSTemplateMasterRepository")
public interface SMSTemplateMasterRepository extends JpaRepository<AfcatSMSTemplateMaster, Integer> {

    @Query("SELECT MAX(stm.templateMasterId) FROM AfcatSMSTemplateMaster stm")
    Integer findMaxTemplateMasterId();

    List<AfcatSMSTemplateMaster> findAllByOrderBySMSTemplateMasterIdDesc();
}
