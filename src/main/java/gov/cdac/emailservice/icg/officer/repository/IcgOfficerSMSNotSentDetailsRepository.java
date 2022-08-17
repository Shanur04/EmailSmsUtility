package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.SmsNotSentDetail;

@Repository("icgOfficerSMSNotSentDetailsRepository")
public interface IcgOfficerSMSNotSentDetailsRepository extends JpaRepository<SmsNotSentDetail, Integer> {

    @Async
    public <S extends SmsNotSentDetail> S save(S entity);
}
