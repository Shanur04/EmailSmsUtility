package gov.cdac.icgOfficerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.cdac.icgOfficerPojo.SmsMobileNoExcelsheet;

@Repository("icgOfficerSmsMobileNoExcelsheetRepository")
public interface IcgOfficerSmsMobileNoExcelsheetRepo extends JpaRepository<SmsMobileNoExcelsheet,Long> {
}
