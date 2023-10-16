package gov.cdac.afcatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatSMSStatus;


@Repository("afcatSMSStatusRepository")
public interface SMSStatusRepository extends JpaRepository<AfcatSMSStatus, Long>{
	
}
