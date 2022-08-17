package gov.cdac.emailservice.icg.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.SystemUserCredential;

@Repository("icgSystemUserCredentialsRepository")
public interface SystemUserCredentialsRepository extends JpaRepository<SystemUserCredential, Integer> {

    Optional<SystemUserCredential> findByEmail(String email);

}
