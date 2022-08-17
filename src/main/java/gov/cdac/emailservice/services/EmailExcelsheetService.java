package gov.cdac.emailservice.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface EmailExcelsheetService {	
	public List<String> getExcel(Path path, Boolean isMobileList) throws FileNotFoundException, IOException;

}
