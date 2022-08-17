package gov.cdac.emailservice.afcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.emailservice.afcat.pojo.SmsMobileNoExcelsheet;

@Repository("afcatSmsMobileNoExcelsheetRepo")
public interface SmsMobileNoExcelsheetRepo extends JpaRepository<SmsMobileNoExcelsheet,Long> {

	

}
