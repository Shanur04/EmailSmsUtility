package gov.cdac.emailservice.casb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.casb.pojo.BatchDetail;
import gov.cdac.emailservice.projection.BatchSummary;

@Repository("casbBatchMasterRepository")
public interface BatchMasterRepository extends JpaRepository<BatchDetail, Integer> {

    @Query("SELECT bm.batchId AS batchId, bm.batchCode AS batchCode, bm.batchName AS batchName FROM BatchDetail bm "
	    + "ORDER BY bm.endDateTime DESC")
    BatchSummary findActiveBatch();

}
