package gov.cdac.emailservice.casb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.casb.pojo.CentreDetail;
import gov.cdac.emailservice.projection.CentreSummary;

@Repository("casbCentreMasterRepository")
public interface CentreMasterRepository extends JpaRepository<CentreDetail, Integer> {

    @Query(value = "select cm.center_id centreId, cm.center_code centreCode, cm.center_name centreName "
	    + "from centre_examslot_mapping cexm " + "join center_master cm on cm.center_id = cexm.centre_id "
	    + "where cm.center_status = true group by cm.center_id, cm.center_code, cm.center_name "
	    + "order by cm.center_code", nativeQuery = true)
    public List<CentreSummary> findAllCentres(Integer examId);

}
