package gov.cdac.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	public String getFileUploadPath(MultipartFile fileToSave,Long idToAppend, String whichFile, String reqType);
	
	public String findFile(String fileName,String applicantIdInString, String reqType);
	
	public boolean deleteFile(String filePath);
	
	public boolean fileExists(String filePath);
	
	public boolean createDirectory(String reqType);

	boolean validateFileContent(File file);


	String convertPngToJpg(String filePath) throws IOException;

	boolean createDirectoryNew(String path);


	boolean saveFiles(File fileToSave,String path, String reqType);
	
	ArrayList<File> findFiles(String reqType);
	
	ArrayList<File> findFiles(Long emailSentId, String reqType);

	String findFilePhotoCheck(String fileName, String applicantIdInString, String reqType);

	

	
}
