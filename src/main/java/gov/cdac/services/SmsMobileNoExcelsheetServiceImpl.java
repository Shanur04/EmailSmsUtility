package gov.cdac.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cdac.afcatPojo.SmsMobileNoExcelsheet;
import gov.cdac.afcatRepository.SmsMobileNoExcelsheetRepo;

@Service("afcatSmsMobileNoExcelsheetServiceImpl")
public class SmsMobileNoExcelsheetServiceImpl implements SmsMobileNoExcelsheetService{
	@Autowired
	SmsMobileNoExcelsheetRepo excelRepo;

	@Override
	public void saveData(String path, String smsType, int smsSentType) {
		SmsMobileNoExcelsheet s = new SmsMobileNoExcelsheet();
		s.setPath(path);
		s.setSmsType(smsType);
		s.setSmsSentType(smsSentType);
		excelRepo.save(s);
		
	}

	@Override
	public List<String> getExcel(Path path, Boolean isMobileList) throws IOException {
		System.out.println("Afcat get Excel");// done

		if (path != null) {

			FileInputStream inputStream = new FileInputStream(path.toString());
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			System.out.println(rows);// 3 done
			int cols = sheet.getRow(0).getLastCellNum();
			System.out.println(cols);// 1 done

			ArrayList<Cell> list1 = new ArrayList<Cell>();
			List<String> outputList = new ArrayList<String>();

			for (int j = 0; j < cols; j++) {
				Row row = sheet.getRow(0);

				Cell cell = row.getCell(j); 
				String s = cell.getStringCellValue().toString();
				
				System.out.println(s);
				
				if (s.equals("Candidate_Mobile_No.") && isMobileList) {
					int p = cell.getColumnIndex();
					for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
						row = sheet.getRow(i);
						cell = row.getCell(p);
						if (cell != null) {
							list1.add(cell);
							outputList.add(cell.toString());
						} else
							break;
					}
					return outputList;
				} else if (s.equals("Candidate_AppCredIds") && (!isMobileList)) {
					int p = cell.getColumnIndex();
					for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
						row = sheet.getRow(i);
						cell = row.getCell(p);
						System.out.println(cell);
						if (cell != null) {
							try {
								Long.parseLong(cell.toString());
							}catch (Exception e) {
								return new ArrayList<String>();
							}
							list1.add(cell);
							outputList.add(cell.toString());
						} else
							break;
					}
					return outputList;
				}
			}
		}
		return null;

	}

	@Override
	public List<Long> getFileNo() {
		List<SmsMobileNoExcelsheet> list = excelRepo.findAll();
		List<Long> fn = new ArrayList<Long>();
		for(int i=0;i<list.size();i++) {
			long fileNo=list.get(i).getFileNo();
			fn.add(fileNo);
		}
		return fn;
	}

	@Override
	public void saveData(long fileNo, String path, String smsType, int smsSentType) {
		// TODO Auto-generated method stub
		
	}
	

}
