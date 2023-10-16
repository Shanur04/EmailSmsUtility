package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.icgPojo.ExamCentreMapping;

@Repository("icgExamCentreMappingRepository")
@Transactional(readOnly = true)
public interface ExamCentreMappingRepository extends JpaRepository<ExamCentreMapping, Integer> {

}
