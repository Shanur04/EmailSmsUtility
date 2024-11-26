package gov.cdac.afcatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.afcatPojo.SmsMobileNoExcelsheet;

@Repository("afcatSmsMobileNoExcelsheetRepo")
public interface SmsMobileNoExcelsheetRepo extends JpaRepository<SmsMobileNoExcelsheet,Long> {

	

}
