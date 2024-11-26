package gov.cdac.icgOfficerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.SmsNotSentDetail;

@Repository("icgOfficerSMSNotSentDetailsRepository")
public interface IcgOfficerSMSNotSentDetailsRepository extends JpaRepository<SmsNotSentDetail, Integer> {

    @Async
    public <S extends SmsNotSentDetail> S save(S entity);
}
