package gov.cdac.casbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbEmailStatus;

@Repository("casbEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<CasbEmailStatus, Long> {

}
