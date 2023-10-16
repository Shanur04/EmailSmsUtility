package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.SMSNotSentDetails;

@Repository("icgSMSNotSentDetailsRepository")
public interface SMSNotSentDetailsRepository extends JpaRepository<SMSNotSentDetails, Integer> {

    @Async
    public <S extends SMSNotSentDetails> S save(S entity);

}
