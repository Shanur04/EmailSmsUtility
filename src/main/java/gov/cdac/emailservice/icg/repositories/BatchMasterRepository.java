package gov.cdac.emailservice.icg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.BatchMaster;

@Repository("icgBatchMasterRepository")
public interface BatchMasterRepository extends JpaRepository<BatchMaster, Integer> {
	
	@Query(" from BatchMaster bm where bm.batchStatus = true ")
	List<BatchMaster> getAllActiveBatches();

    @Query("SELECT bm FROM BatchMaster bm WHERE bm.batchStatus = true ORDER BY bm.endDateTime DESC")
    BatchMaster findActiveBatch();
}

