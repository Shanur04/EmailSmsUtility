package gov.cdac.emailservice.excel;

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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:filePath.properties")
public class UserExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Long> listOfAppCredIds;
	private List<String> listOfEmailIds;
	private String subject;
	private String body;
	private String reqType;
	private int sentType;
	private String filePath;
	private static long number = 0L;
	private ServletOutputStream outputStream;
	boolean f = false;
	
	//@Value("${filePath.icgExcelReportDownloadDir}")
	private String excelDirFromPropertyFile;
	
	@Value("${filePath.downloadExcelReportDownloadDir}")
	private String downloadExcelReportDownloadDir;

	public UserExcelExporter(List<Long> appCredIds, List<String> emailIds, String subject, String body, int sentType,
			String reqType) {
		if (appCredIds == null || appCredIds.isEmpty()) {
			this.listOfAppCredIds = new ArrayList<>();
		} else {
			this.listOfAppCredIds = appCredIds;
		}
		this.listOfEmailIds = emailIds;
		this.subject = subject;
		this.body = body;
		this.sentType = sentType;
		this.reqType = reqType;
		workbook = new XSSFWorkbook();
	}

	public UserExcelExporter(String filePath) {
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
		sheet = workbook.createSheet("Email_Ids");
		CellStyle style = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

		Row row = sheet.createRow(0);
		createCell(row, 0, "Date", style);
		row.createCell(1).setCellValue(java.time.LocalDateTime.now().format(format));
		row.getCell(1).setCellStyle(style);

		row = sheet.createRow(1);
		createCell(row, 0, "Email Subject", style);
		row.createCell(1).setCellValue(subject);

		row = sheet.createRow(2);
		createCell(row, 0, "Email Body", style);
		row.createCell(1).setCellValue(body);

		row = sheet.createRow(3);
		createCell(row, 0, "Candidate_AppCredIds", style);
		createCell(row, 1, "Candidate_EmailIds", style);
	}

	private void writeDataLines1() {
		int rowCount = 4;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		//System.out.println("list of Email Ids : " + listOfEmailIds);
		System.out.println("list of app cred ids : " + listOfAppCredIds);

		for (String emailId : listOfEmailIds) {
			int columnCount = 0;

			Row row = sheet.createRow(rowCount);
			if (listOfAppCredIds.size() != 0) {
				createCell(row, columnCount, listOfAppCredIds.get(rowCount - 1).toString(), style);
			} else {
				createCell(row, columnCount, "CI", style);
			}
			createCell(row, columnCount + 1, emailId, style);
			rowCount++;
		}
	}

	public Path export1() throws IOException {

		writeHeaderLine1();
		writeDataLines1();

		number++;

		if(reqType.equals("icg")) {
			excelDirFromPropertyFile="D:\\EmailSMS\\email_sent_excel_icg\\";
		}else if(reqType.equals("afcat")) {
			excelDirFromPropertyFile="D:\\EmailSMS\\email_sent_excel_afcat\\";
		}else if(reqType.equals("icgOfficer")) {
			excelDirFromPropertyFile="D:\\EmailSMS\\email_sent_excel_icgOff\\";
		}else {
			excelDirFromPropertyFile="D:\\EmailSMS\\email_sent_excel_casb\\";
		}
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

		if (sentType == 1) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number
							+ ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number + ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		} else if (sentType == 2) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_"
							+ number + ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_" + number
					+ ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		} else if (sentType == 3) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number
							+ ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number
					+ ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
			workbook.close();
			return path1;

		}
		return null;
	}
	
	public Path export2(HttpServletResponse response) throws IOException {
		writeHeaderLine1();
		writeDataLines1();

		number++;

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		
		this.outputStream = response.getOutputStream();
		
		if (sentType == 1) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number
							+ ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Quick" + "_" + reqType + "_" + number + ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
	        workbook.write(outputStream);
	        workbook.close();
			return path1;

		} else if (sentType == 2) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_"
							+ number + ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "QuickScheduled" + "_" + reqType + "_" + number
					+ ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
	        workbook.write(outputStream);
			workbook.close();
			return path1;

		} else if (sentType == 3) {
			OutputStream fileOut = new FileOutputStream(
					excelDirFromPropertyFile+"Email_Utility_report_"
							+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number
							+ ".xlsx");
			Path path1 = Paths.get(excelDirFromPropertyFile+"Email_Utility_report_"
					+ java.time.LocalDateTime.now().format(format) + "Scheduled" + "_" + reqType + "_" + number
					+ ".xlsx");

			workbook.write(fileOut);
			fileOut.flush();
	        workbook.write(outputStream);
	        workbook.write(outputStream);
			workbook.close();
			return path1;

		}
		return null;
	}

	public void extractFile() throws IOException {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

		String TargetSheetPathAndName = this.filePath;
		String NewSheetPathAndName = "C:\\Users\\shanurj\\Downloads\\file_"
				+ java.time.LocalDateTime.now().format(format) + ".xlsx";
		if (TargetSheetPathAndName != null && !"".equals(TargetSheetPathAndName.trim())) {
			try {
				File targetFile = new File(TargetSheetPathAndName);
				FileInputStream inputStream = new FileInputStream(targetFile);
				XSSFWorkbook inputWorkbook = new XSSFWorkbook(inputStream);
				int targetSheetCount = inputWorkbook.getNumberOfSheets();

				File outputFile = new File(NewSheetPathAndName.trim());
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				XSSFWorkbook outputWorkbook = new XSSFWorkbook();

				for (int i = 0; i < targetSheetCount; i++) {
					XSSFSheet targetSheet = inputWorkbook.getSheetAt(i);
					String inputSheetName = inputWorkbook.getSheetName(i);
					XSSFSheet outputSheet = outputWorkbook.createSheet(inputSheetName);
					copyExcelWB(targetSheet, outputSheet);
				}

				outputWorkbook.write(outputStream);
				outputStream.close();
			} catch (Exception ex) {
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

	public void extractTextFile() throws IOException {

	}

	public void export(HttpServletResponse response) throws IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
	/*
	 * public void addAcknowledgmentColumn(List<Boolean> sentEmailId) { int rowCount
	 * = 4; CellStyle style = workbook.createCellStyle(); XSSFFont font =
	 * workbook.createFont(); font.setFontHeight(14); style.setFont(font);
	 * 
	 * for (Boolean isSent : sentEmailId) {
	 * createCell(workbook.getSheetAt(0).getRow(rowCount), 2, isSent, style);
	 * rowCount++; } }
	 */
}
