package gov.cdac.afcatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatEmailStatus;

@Repository("afcatEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<AfcatEmailStatus, Long>{

}
