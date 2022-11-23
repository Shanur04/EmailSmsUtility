package gov.cdac.emailservice.icg.officer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.officer.pojo.BatchMaster;
import gov.cdac.emailservice.projection.BatchSummary;

@Repository("icgOfficerBatchMasterRepository")
public interface IcgOfficerBatchMasterRepository extends JpaRepository<BatchMaster, Integer> {

    @Query("SELECT bm.batchId AS batchId, bm.batchCode AS batchCode, bm.batchName AS batchName FROM BatchMaster bm "
	    + "ORDER BY bm.endDateTime DESC")
    BatchSummary findActiveBatch();

}