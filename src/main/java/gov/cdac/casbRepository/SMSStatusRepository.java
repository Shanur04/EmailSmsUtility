package gov.cdac.casbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbSMSStatus;


@Repository("casbSMSStatusRepository")
public interface SMSStatusRepository extends JpaRepository<CasbSMSStatus, Long>{
	
}
