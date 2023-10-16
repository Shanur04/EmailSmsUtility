package gov.cdac.afcatRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatMailReasonMaster;


@Repository("afcatEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<AfcatMailReasonMaster, Integer> {

    List<AfcatMailReasonMaster> findAllByOrderByEmailReason();
}
