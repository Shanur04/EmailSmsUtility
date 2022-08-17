package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerEmailStatus;

@Repository("icgOfficerEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<IcgOfficerEmailStatus, Long>{

}
