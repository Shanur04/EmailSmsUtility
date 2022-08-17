package gov.cdac.emailservice.afcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.AfcatSMSNotSentDetails;


@Repository("afcatSMSNotSentDetailsRepository")
public interface SMSNotSentDetailsRepository extends JpaRepository<AfcatSMSNotSentDetails, Integer> {

    @Async
    public <S extends AfcatSMSNotSentDetails> S save(S entity);
}
