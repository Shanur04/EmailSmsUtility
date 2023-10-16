package gov.cdac.casbRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.casbPojo.SmsMobileNoExcelsheet;

@Repository("casbSmsMobileNoExcelsheetRepository")
public interface SmsMobileNoExcelsheetRepo extends JpaRepository<SmsMobileNoExcelsheet,Long> {


}
