package gov.cdac.icgRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgPojo.SmsMobileNoExcelsheet;

@Repository("icgSmsMobileNoExcelsheetRepository")
public interface SmsMobileNoExcelsheetRepo extends JpaRepository<SmsMobileNoExcelsheet,Long> {


}
