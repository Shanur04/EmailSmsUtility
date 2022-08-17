package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.RegionMaster;

@Repository("icgRegionMasterRepository")
public interface RegionMasterRepository extends JpaRepository<RegionMaster, Integer>{

	@Query(" select rm from RegionMaster rm where rm.regionStatus = true and rm.examMaster.examId = ?1")
	List<RegionMaster> getRegionListByExamId(int examId);

}
