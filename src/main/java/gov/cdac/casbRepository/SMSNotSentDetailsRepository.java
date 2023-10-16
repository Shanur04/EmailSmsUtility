package gov.cdac.casbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbSMSNotSentDetails;

@Repository("casbSMSNotSentDetailsRepository")
public interface SMSNotSentDetailsRepository extends JpaRepository<CasbSMSNotSentDetails, Integer> {

    @Async
    public <S extends CasbSMSNotSentDetails> S save(CasbSMSNotSentDetails smsNotSentDetail);
}
