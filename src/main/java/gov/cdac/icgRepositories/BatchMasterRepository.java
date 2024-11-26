package gov.cdac.icgRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.BatchMaster;
import gov.cdac.projection.BatchSummary;

@Repository("icgBatchMasterRepository")
public interface BatchMasterRepository extends JpaRepository<BatchMaster, Integer> {
	
	@Query(" from BatchMaster bm where bm.batchStatus = true ")
	List<BatchMaster> getAllActiveBatches();
    
    @Query("SELECT bm.batchId AS batchId, bm.batchCode AS batchCode, bm.batchName AS batchName FROM BatchMaster bm "
    	    + "WHERE bm.batchStatus = true ORDER BY bm.endDateTime DESC")
    BatchSummary findActiveBatch();
}

