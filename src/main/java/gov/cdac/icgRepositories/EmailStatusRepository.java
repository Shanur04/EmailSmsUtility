package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.IcgEmailStatus;

@Repository("icgEmailStatusRepository")
public interface EmailStatusRepository extends JpaRepository<IcgEmailStatus, Long>{

}
