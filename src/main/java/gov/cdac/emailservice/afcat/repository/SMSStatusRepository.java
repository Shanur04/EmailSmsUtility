package gov.cdac.emailservice.afcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatSMSStatus;


@Repository("afcatSMSStatusRepository")
public interface SMSStatusRepository extends JpaRepository<AfcatSMSStatus, Long>{
	
}
