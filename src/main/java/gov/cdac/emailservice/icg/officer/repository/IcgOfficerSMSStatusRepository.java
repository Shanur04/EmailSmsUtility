package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerSMSStatus;


@Repository("icgOfficerSMSStatusRepository")
public interface IcgOfficerSMSStatusRepository extends JpaRepository<IcgOfficerSMSStatus, Long>{
	
}
