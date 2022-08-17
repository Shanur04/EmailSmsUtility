package gov.cdac.emailservice.icg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.emailservice.icg.pojo.ExamCentreMapping;

@Repository("icgExamCentreMappingRepository")
@Transactional(readOnly = true)
public interface ExamCentreMappingRepository extends JpaRepository<ExamCentreMapping, Integer> {

}
