package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.ExamCityMaster;

@Repository("icgExamCityMasterRepository")
public interface ExamCityMasterRepository extends JpaRepository<ExamCityMaster, Integer> {

   
}
