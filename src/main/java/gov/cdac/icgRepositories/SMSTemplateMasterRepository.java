package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.IcgSMSTemplateMaster;

@Repository("icgSMSTemplateMasterRepository")
public interface SMSTemplateMasterRepository extends JpaRepository<IcgSMSTemplateMaster, Integer> {

    @Query("SELECT MAX(stm.templateMasterId) FROM IcgSMSTemplateMaster stm")
    Integer findMaxTemplateMasterId();

    List<IcgSMSTemplateMaster> findAllByOrderBySMSTemplateMasterIdDesc();
}
