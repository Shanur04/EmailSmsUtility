package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.IcgMailReasonMaster;


@Repository("icgEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<IcgMailReasonMaster, Integer> {

    List<IcgMailReasonMaster> findAllByOrderByEmailReason();
}
