package gov.cdac.emailservice.icg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.IcgEmailStatus;

@Repository("icgEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<IcgEmailStatus, Long>{

}
