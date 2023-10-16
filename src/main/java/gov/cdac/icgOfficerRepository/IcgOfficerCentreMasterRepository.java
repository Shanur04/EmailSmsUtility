package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.CentreMaster;
import gov.cdac.projection.CentreSummary;

@Repository("icgOfficerCentreMasterRepository")
public interface IcgOfficerCentreMasterRepository extends JpaRepository<CentreMaster, Integer> {

    @Query(value = "select cm.centre_id centreId, cm.centre_code centreCode, cm.centre_name centreName "
	    + "from centre_examslot_mapping cexm join centre_master cm on cm.centre_id = cexm.centre_id "
	    + "where cm.centre_status = true group by cm.centre_id, cm.centre_code, cm.centre_name "
	    + "order by cm.centre_code", nativeQuery = true)
    public List<CentreSummary> findAllCentres(Integer examId);

}
