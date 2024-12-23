package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbMailReasonMaster;




@Repository("casbEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<CasbMailReasonMaster, Integer> {

	//@Query("SELECT r FROM IcgMailReasonMaster r ORDER BY r.emailReason")
    List<CasbMailReasonMaster> findAllByOrderByEmailReason();
}
