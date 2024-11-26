package gov.cdac.icgOfficerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.IcgOfficerEmailStatus;

@Repository("icgOfficerEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<IcgOfficerEmailStatus, Long>{

}
