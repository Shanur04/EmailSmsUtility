package gov.cdac.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface SmsMobileNoExcelsheetService {
	
	public void saveData(long fileNo,String path,String smsType,int smsSentType);
	public List<String> getExcel(Path path, Boolean isMobileList) throws FileNotFoundException, IOException;
	public List<Long> getFileNo();
	public void saveData(String string, String smsType, int smsSentType);

}
