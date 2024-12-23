package gov.cdac.casbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.CasbPersonalDetail;
import gov.cdac.projection.ApplicantMobileSummary;

@Repository("casbPersonalDetailRepository")
public interface PersonalDetailRepository extends JpaRepository<CasbPersonalDetail, Long> {

    @Query(value = "SELECT pd.applicant_cred_id AS appCredId, pd.mobile AS mobile FROM personal_details pd WHERE pd.applicant_cred_id IN (?1)", nativeQuery = true)
    List<ApplicantMobileSummary> findMobileByAppCredId(List<Long> appCredIds);
    
    @Query(value = "SELECT pd.mobile AS mobile FROM personal_details pd WHERE pd.applicant_cred_id IN (?1)", nativeQuery = true)
    List<String> findMobileByAppCredId2(List<Long> appCredIds);

}
