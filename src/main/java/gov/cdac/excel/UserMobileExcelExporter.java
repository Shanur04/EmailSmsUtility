package gov.cdac.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;

public class UserMobileExcelExporter {
	//excel code
	//testing
	//new code
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Long> listOfAppCredIds;
	private List<String> listOfMobileNo;
	private String smsContent;
	private String smsType;
	private String reqType;
	private int smsSendType;
	private String filePath;
	private static long number = 0L;
	private static long number2 = 0L;

	boolean f = false;

	public UserMobileExcelExporter(List<Long> appCredIds, List<String> mobileNos, String smsContent, String smsType,
			int smsSentType, String reqType) {
		if (appCredIds == null || appCredIds.isEmpty()) {
			this.listOfAppCredIds = new ArrayList<>();
		} else {
			this.listOfAppCredIds = appCredIds;
		}
		this.listOfMobileNo = mobileNos;
		this.smsContent = smsContent;
		this.smsSendType = smsSentType;
		this.reqType = reqType;
		workbook = new XSSFWorkbook();
	}

	public UserMobileExcelExporter(String filePath) {
		this.filePath = filePath;
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeHeaderLine1() {
		sheet = workbook.createSheet("Mobile_No");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		createCell(row, 0, "Candidate_AppCredIds", style);
		createCell(row, 1, "Candidate_Mobile_No.", style);

	}

	private void writeDataLines1() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		createCell(sheet.createRow(rowCount), 1, this.smsContent, style);

		System.out.println("list of Mobile nos : " + listOfMobileNo);
		System.out.println("list of app cred ids : " + listOfAppCredIds);
		for (String mobileNo : listOfMobileNo) {
			int columnCount = 0;

			Row row = sheet.createRow(rowCount);
			if (listOfAppCredIds.size() != 0) {
				createCell(row, columnCount, listOfAppCredIds.get(rowCount - 1).toString(), style);
			} else {
				createCell(row, columnCount, "CI", style);
			}
			createCell(row, columnCount + 1, mobileNo, style);
			rowCount++;
		}
	}

	public Path export1() throws IOException {

		writeHeaderLine1();
		writeDataLines1();

		number++;
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

		if (smsSendType == 1) {
			OutputStream fileOut = new FileOutputStream(
					"D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number + ".xlsx");
			Path path1 = Paths.get("D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number + ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		} else if (smsSendType == 2) {
			OutputStream fileOut = new FileOutputStream(
					"D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_" + number
							+ ".xlsx");
			Path path1 = Paths.get("D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_" + number + ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		} else if (smsSendType == 3) {
			OutputStream fileOut = new FileOutputStream(
					"D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number + ".xlsx");
			Path path1 = Paths.get("D:\\EmailSMS\\sms_sent\\SMS_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number + ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		}
		return null;
	}

	public void extractFile(HttpServletResponse response) throws IOException {
			
		response.setContentType("application/vnd.ms-excel");
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		String fileName = "file_"+java.time.LocalDateTime.now().format(format)+".xlsx";
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	    
	    String TargetSheetPathAndName = this.filePath;
		if (TargetSheetPathAndName != null && !"".equals(TargetSheetPathAndName.trim())) {
			try {	 
		        
				File targetFile = new File(TargetSheetPathAndName);
				FileInputStream inputStream = new FileInputStream(targetFile);
				XSSFWorkbook inputWorkbook = new XSSFWorkbook(inputStream);
				
				OutputStream outputStream = response.getOutputStream();	
				inputWorkbook.write(outputStream);
				outputStream.close();
				
//				XSSFWorkbook outputWorkbook = new XSSFWorkbook();
//	
//				int targetSheetCount = inputWorkbook.getNumberOfSheets();
//				for (int i = 0; i < targetSheetCount; i++) {
//					XSSFSheet targetSheet = inputWorkbook.getSheetAt(i);
//					String inputSheetName = inputWorkbook.getSheetName(i);
//					XSSFSheet outputSheet = outputWorkbook.createSheet(inputSheetName);
//					copyExcelWB(targetSheet, outputSheet);
//				}
//	
				
			}catch(Exception ex){
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	public static void copyExcelWB(XSSFSheet targetSheet, XSSFSheet outputSheet) {
		int rowCount = targetSheet.getLastRowNum();
		int currentRowIndex = 0;
		if (rowCount > 0) {
			Iterator<Row> rowIterator = targetSheet.iterator();
			while (rowIterator.hasNext()) {
				int currentCellIndex = 0;
				Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator();
				while (cellIterator.hasNext()) {
					String cellData = cellIterator.next().toString();
					if (currentCellIndex == 0)
						outputSheet.createRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
					else
						outputSheet.getRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
					currentCellIndex++;
				}
				currentRowIndex++;
			}
		}
	}
}


