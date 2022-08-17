package gov.cdac.emailservice.casb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.cdac.emailservice.casb.pojo.CasbEmailStatus;

@Repository("casbEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<CasbEmailStatus, Long> {

}
