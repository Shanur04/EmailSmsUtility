package gov.cdac.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import gov.cdac.exception.EmailNotValidException;
import gov.cdac.exception.RegistrationNumberInvalidException;



public class FileContentReader {

	/**
	 * 
	 *         <p>
	 *         This will read the contents of Excel file uploaded
	 *         </p>
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<?> excelFileReader(File file, char st) throws Exception {
		// st: whether file contains reg number or emails or appcred ids
		try {
			// List will contain appCredIds and registration numbers read out from Excel
			// File
			ArrayList<Long> appList = new ArrayList<>();
			// list will contain emails of applicants
			ArrayList<String> appListString = new ArrayList<>();
			// WorkBook will contain all the sheets containing data(could be reg no.
			// appcredid or emails)
			Workbook workbook = WorkbookFactory.create(new File(file.getPath()));
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			Sheet sheet = workbook.getSheetAt(0);
			try {
				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();
				sheet.forEach(row -> {
					if (row.getRowNum() != 0) {
						row.forEach(cell -> {
							String cellValue = dataFormatter.formatCellValue(cell).trim();
							switch (st) {
							case 'a': {

								appList.add(Long.parseLong(cellValue));
							}
								break;
							case 'r': {
								try {
//									if (StringValidator.isRegistrationNumberValid(cellValue))
										appListString.add(cellValue);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									throw new RuntimeException(e.getMessage(), e);
								}
							}
								break;
							case 'e': {

								try {
									if (StringValidator.isEmailValid(cellValue))
										appListString.add(cellValue);
								} catch (gov.cdac.exception.EmailNotValidException e) {
									// If email is not valid in file this exception is thrown

									throw new RuntimeException(e.getMessage(), e);
								}

							}
								break;

							}
						});
					}
					System.out.println();
				});
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}

			// Closing the workbook
			workbook.close();
			file.delete();
			if (st == 'a')
				return appList;
			else
				return appListString;
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			throw new Exception();

		}

	}

	/**
	 *  This will read the content of CSV file
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<Long> csvFileReader(File file, Character st) throws Exception {
		try {
			// List will contain appCredIds mentioned in CSV file
			ArrayList<Long> appCredIds = new ArrayList<>();
			// List will contain Emails Or Registration Numbers that are valid
			ArrayList<String> listString = new ArrayList<>();
			// opening reader to read from CSV File
			FileReader filereader = new FileReader(file);

			// create csvReader object passing
			// file reader as a parameter
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;
			// we are going to read data line by line
			try {
				while ((nextRecord = csvReader.readNext()) != null) {
					for (String cell : nextRecord) {
						switch (st) {
						case 'a': {

							appCredIds.add(Long.parseLong(cell));
						}
							break;
						case 'r': {
							try {
								if (StringValidator.isRegistrationNumberValid(cell))
									listString.add(cell);
							} catch (RegistrationNumberInvalidException e) {
								// Runtime exception if registration numbers are not valid
								throw new RuntimeException(e.getMessage(), e);
							}
						}
							break;
						case 'e': {

							try {
								if (StringValidator.isEmailValid(cell))
									listString.add(cell);
							} catch (EmailNotValidException e) {
								// If email is not valid in file this exception is thrown

								throw new RuntimeException(e.getMessage(), e);
							}

						}
							break;

						}

					}

				}
				System.out.println();
			} catch (NumberFormatException e) {
				throw new NumberFormatException();

			}
			file.delete();
			return appCredIds;
		} catch (Exception e) {
			throw new Exception();

		}

	}

	/**
	 * 
	 *         <p>
	 *         This method will convert multipart file fomat to File(java.io)
	 *         </p>
	 * @param mFile
	 * @return
	 * @throws Exception
	 */
	public static File multipartToFile(MultipartFile mFile) throws Exception {

		File convertedFile = new File(mFile.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(mFile.getBytes());
		fos.close();
		return convertedFile;
	}

}
