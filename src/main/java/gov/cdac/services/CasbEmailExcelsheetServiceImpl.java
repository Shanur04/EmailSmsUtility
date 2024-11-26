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
import org.springframework.stereotype.Service;

@Service("casbEmailExcelsheetServiceImpl")
public class CasbEmailExcelsheetServiceImpl implements EmailExcelsheetService{
	@Override
	public List<String> getExcel(Path path, Boolean isMobileList) throws IOException {
		System.out.println("Casb get Excel");// done

		if (path != null) {

			FileInputStream inputStream = new FileInputStream(path.toString());
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum() - 3;
			System.out.println(rows);// 3 done
			int cols = sheet.getRow(3).getLastCellNum();
			System.out.println(cols);// 1 done

			ArrayList<Cell> list1 = new ArrayList<Cell>();
			List<String> outputList = new ArrayList<String>();

			for (int j = 0; j < cols; j++) {
				Row row = sheet.getRow(3);

				Cell cell = row.getCell(j); 
				String s = cell.getStringCellValue().toString();
				
				System.out.println(s);
				
				if (s.equals("Candidate_EmailIds") && isMobileList) {
					int p = cell.getColumnIndex();
					for (int i = 4; i <= sheet.getLastRowNum(); i++) {
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
					for (int i = 4; i <= sheet.getLastRowNum(); i++) {
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

}
