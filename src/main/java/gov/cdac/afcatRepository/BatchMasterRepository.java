package gov.cdac.afcatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.AfcatBatchDetail;
import gov.cdac.projection.BatchSummary;

@Repository("afcatBatchMasterRepository")
public interface BatchMasterRepository extends JpaRepository<AfcatBatchDetail, Integer> {

    @Query("SELECT bm.batchId AS batchId, bm.batchCode AS batchCode, bm.batchName AS batchName FROM AfcatBatchDetail bm "
	    + "ORDER BY bm.endDateTime DESC")
    BatchSummary findActiveBatch();

}
