package gov.cdac.afcatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatSMSTemplateMaster;


@Repository("afcatSMSTemplateMasterRepository")
public interface SMSTemplateMasterRepository extends JpaRepository<AfcatSMSTemplateMaster, Integer> {

    @Query("SELECT MAX(stm.templateMasterId) FROM AfcatSMSTemplateMaster stm")
    Integer findMaxTemplateMasterId();

    List<AfcatSMSTemplateMaster> findAllByOrderBySMSTemplateMasterIdDesc();
}
