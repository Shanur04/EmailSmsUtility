package gov.cdac.icgOfficerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.IcgOfficerSMSStatus;


@Repository("icgOfficerSMSStatusRepository")
public interface IcgOfficerSMSStatusRepository extends JpaRepository<IcgOfficerSMSStatus, Long>{
	
}
