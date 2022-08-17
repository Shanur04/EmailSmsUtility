package gov.cdac.emailservice.afcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatEmailStatus;

@Repository("afcatEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<AfcatEmailStatus, Long>{

}
