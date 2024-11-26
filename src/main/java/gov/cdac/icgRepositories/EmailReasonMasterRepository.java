package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.IcgMailReasonMaster;


@Repository("icgEmailReasonMasterRepository")
public interface EmailReasonMasterRepository extends JpaRepository<IcgMailReasonMaster, Integer> {

    List<IcgMailReasonMaster> findAllByOrderByEmailReason();
}
