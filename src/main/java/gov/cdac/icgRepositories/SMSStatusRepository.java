package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.SMSStatus;

@Repository("icgSMSStatusRepository")
public interface SMSStatusRepository extends JpaRepository<SMSStatus, Long>{
	
}
