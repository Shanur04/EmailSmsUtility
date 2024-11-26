package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbSMSTemplateMaster;

@Repository("casbSMSTemplateMasterRepository")
public interface SMSTemplateMasterRepository extends JpaRepository<CasbSMSTemplateMaster, Integer> {

    @Query("SELECT MAX(stm.templateMasterId) FROM CasbSMSTemplateMaster stm")
    Integer findMaxTemplateMasterId();

    List<CasbSMSTemplateMaster> findAllByOrderBySMSTemplateMasterIdDesc();
}
