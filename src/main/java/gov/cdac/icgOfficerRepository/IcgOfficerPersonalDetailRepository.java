package gov.cdac.icgOfficerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.PersonalDetail;
import gov.cdac.projection.ApplicantMobileSummary;

@Repository("icgOfficerPersonalDetailRepository")
public interface IcgOfficerPersonalDetailRepository extends JpaRepository<PersonalDetail, Long> {

    @Query(value = "SELECT pd.applicant_cred_id AS appCredId, pd.mobile AS mobile FROM personal_details pd WHERE pd.applicant_cred_id IN (?1)", nativeQuery = true)
    List<ApplicantMobileSummary> findMobileByAppCredId(List<Long> appCredIds);
    
    @Query(value = "SELECT pgp_sym_decrypt(CAST(pd.mobile AS bytea), 'icgregkey','compress-algo=0, cipher-algo=aes256') AS mobile FROM personal_details pd WHERE pd.applicant_cred_id IN (?1)", nativeQuery = true)
    List<String> findMobileByAppCredId2(List<Long> appCredIds);

}
