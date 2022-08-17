package gov.cdac.emailservice.icg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.icg.pojo.ExamCityMaster;

@Repository("icgExamCityMasterRepository")
public interface ExamCityMasterRepository extends JpaRepository<ExamCityMaster, Integer> {

   
}
