package gov.cdac.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service("fileUploadService")
@PropertySource("classpath:filePath.properties")
public class FileUploadServiceImpl implements FileUploadService{
	
	@Value("${filePath.icgEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileICG;
	
	@Value("${filePath.icgOfficerEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileICGOfficer;
	
	@Value("${filePath.afcatEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileAfcat;
	
	@Value("${filePath.casbEmailAttachmentsDir}")
	private String emailAttachmentDirFromPropertyFileCASB;
	
	
	@Value("${filePath.registration}")
	private String registrationFilePathFromPropertyFile;
	

	
	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");
		
	
	@Override
	public boolean saveFiles(File fileToSave,String path, String reqType)
	{
		boolean booleanToReturn = false;						
		Path filepath = Paths.get(path+fileToSave.getName());
	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(Files.readAllBytes(filepath));
	    } catch (IOException e) {
			e.printStackTrace();
		}				
		File source = null;
		if(reqType.equalsIgnoreCase("icg"))
			source = new File(emailAttachmentDirFromPropertyFileICG+fileToSave.getName());
		else if(reqType.equalsIgnoreCase("icgOfficer"))
			source = new File(emailAttachmentDirFromPropertyFileICGOfficer+fileToSave.getName());
		else if(reqType.equalsIgnoreCase("afcat"))
			source = new File(emailAttachmentDirFromPropertyFileAfcat+fileToSave.getName());
		else if(reqType.equalsIgnoreCase("casb"))
			source = new File(emailAttachmentDirFromPropertyFileCASB+fileToSave.getName());

		System.out.println(source);
		File dest = new File(path+fileToSave.getName());
		try {
		    FileUtils.copyFile(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
		
		return booleanToReturn;
	}
	
	
	@Override
	public String getFileUploadPath(MultipartFile fileToSave,Long idToAppend, String whichFile, String reqType)
	{
				
		File dir = null;
		if(reqType.equalsIgnoreCase("icg"))
			dir = new File(emailAttachmentDirFromPropertyFileICG  + idToAppend.toString());
		else if(reqType.equalsIgnoreCase("icgOfficer"))
			dir = new File(emailAttachmentDirFromPropertyFileICGOfficer  + idToAppend.toString());
		else if(reqType.equalsIgnoreCase("afcat"))
			dir = new File(emailAttachmentDirFromPropertyFileAfcat  + idToAppend.toString());
		else if(reqType.equalsIgnoreCase("casb"))
			dir = new File(emailAttachmentDirFromPropertyFileCASB  + idToAppend.toString());
		
		String fileExtension = FilenameUtils.getExtension(fileToSave.getOriginalFilename()).toLowerCase();
		String fileName = idToAppend.toString() + whichFile + "." + fileExtension; 
		String fileUploadPath = dir.getAbsolutePath() + File.separator + fileName;		
		return fileUploadPath;
	}
	
	@Override
	public String findFile(String fileName,String applicantIdInString, String reqType)
    {
		String filePathToReturn = null;
		String fileNameWithOutExt = null;
		
		File dir = null;
		if(reqType.equalsIgnoreCase("icg"))
			dir = new File(emailAttachmentDirFromPropertyFileICG  + applicantIdInString);
		else if(reqType.equalsIgnoreCase("icgOfficer"))
			dir = new File(emailAttachmentDirFromPropertyFileICGOfficer  + applicantIdInString);
		else if(reqType.equalsIgnoreCase("afcat"))
			dir = new File(emailAttachmentDirFromPropertyFileAfcat  + applicantIdInString);
		else if(reqType.equalsIgnoreCase("casb"))
			dir = new File(emailAttachmentDirFromPropertyFileCASB  + applicantIdInString);
		
	   File[] list = dir.listFiles();
        if(list!=null)
        for (File file : list)
        {
        	fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
            if (file.isDirectory()) {
                findFile(fileName,applicantIdInString, reqType);
            }
            else if (fileName.equalsIgnoreCase(fileNameWithOutExt)) {
            	filePathToReturn = dir.getAbsolutePath() + File.separator + file.getName();
                break;
            }
        }
        return filePathToReturn;
    }
	
	@Override
	public String findFilePhotoCheck(String fileName,String applicantIdInString, String reqType)
    {
		String filePathToReturn = null;
		String fileNameWithOutExt = null;
		File dir = new File(registrationFilePathFromPropertyFile  + applicantIdInString);
	   File[] list = dir.listFiles();
        if(list!=null)
        for (File file : list)
        {
        	fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
            if (file.isDirectory()) {
                findFile(fileName,applicantIdInString, reqType);
            }
            else if (fileName.equalsIgnoreCase(fileNameWithOutExt)) {
            	filePathToReturn = dir.getAbsolutePath() + File.separator + file.getName();
                break;
            }
        }
        return filePathToReturn;
    }
	
	
	
	@Override
	public boolean deleteFile(String filePath) {	
		 try {
			  Files.delete(Paths.get(filePath));       //file.delete();
			  return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	@Override
	public boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
	
	
	@Override
	public boolean createDirectory(String reqType){
		boolean directoryCreated = true;
		try{
				
				File dir = null;
				if(reqType.equalsIgnoreCase("icg"))
					dir = new File(emailAttachmentDirFromPropertyFileICG);
				else if(reqType.equalsIgnoreCase("icgOfficer"))
					dir = new File(emailAttachmentDirFromPropertyFileICGOfficer);
				else if(reqType.equalsIgnoreCase("afcat"))
					dir = new File(emailAttachmentDirFromPropertyFileAfcat);
				else if(reqType.equalsIgnoreCase("casb"))
					dir = new File(emailAttachmentDirFromPropertyFileCASB);
				
				if (dir.exists()) {
					directoryCreated=false;
					centerWiseSendEmail.info("directory already exists");
				}
				else{
					directoryCreated = dir.mkdir();					
					centerWiseSendEmail.info("directory created: "+directoryCreated);
				}
		}
		catch(Exception e ){
			e.printStackTrace();
			directoryCreated=false;
		}
		return directoryCreated;
	}
	
	

	
	@Override
	public boolean validateFileContent(File file) {
		boolean result = false;
		
		return result;
	}
	
	public boolean renameFileName(String oldFilePath,boolean revertFlag) {
		try {
				Path fileToMovePath, targetPath;
				fileToMovePath = Paths.get(oldFilePath);
				String name = fileToMovePath.getFileName().toString();
				String copiedName = name.replaceFirst("(\\.[^\\.]*)?$", "-copy$0");
				if(!revertFlag) {
					targetPath = fileToMovePath.resolveSibling(copiedName);
				}else {
					fileToMovePath = fileToMovePath.resolveSibling(copiedName);
					targetPath = Paths.get(oldFilePath);
				}				
				Files.move(fileToMovePath, targetPath);
				return true;
		}catch (Exception e) {
				return false;
		}		
	}
	
	
	
	@Override
	public String convertPngToJpg(String filePath) throws IOException {
		String[] substrings = filePath.split("\\.");
	    String ext = substrings[substrings.length - 1];
		if(ext.equalsIgnoreCase("png"))
		{
			 Path source = Paths.get(filePath);
			 String filename = filePath.substring(0, filePath.lastIndexOf('.'));
			 filename+=".jpg";
		     Path target = Paths.get(filename);
		     BufferedImage originalImage = ImageIO.read(source.toFile());
		     BufferedImage newBufferedImage = new BufferedImage(
		                originalImage.getWidth(),
		                originalImage.getHeight(),
		                BufferedImage.TYPE_INT_RGB);
		     newBufferedImage.createGraphics()
	            .drawImage(originalImage,
	                    0,
	                    0,
	                    Color.WHITE,
	                    null);
		     ImageIO.write(newBufferedImage, "jpg", target.toFile());
		     return filename;
		}
		else
			return filePath;
	}


	@Override
	public boolean createDirectoryNew(String path) {
		boolean directoryCreated = true;
		try{
				File dir = new File(path);
				if (dir.exists()) {
					directoryCreated=false;
				}
				else{
					directoryCreated = dir.mkdir();
				}
		}
		catch(Exception e ){
			e.printStackTrace();
			directoryCreated=false;
		}
		return directoryCreated;
	}


	@Override
	public ArrayList<File> findFiles(String reqType) {
		//Creating a File object for directory
	      System.out.println("reqType : "+reqType);
	      File directoryPath = null;
			if(reqType.equalsIgnoreCase("icg"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileICG);
			else if(reqType.equalsIgnoreCase("icgOfficer"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileICGOfficer);
			else if(reqType.equalsIgnoreCase("afcat"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileAfcat);
			else if(reqType.equalsIgnoreCase("casb"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileCASB);
			
		System.out.println(directoryPath);
	      //List of all files and directories
	      File[] listFiles = directoryPath.listFiles();
	      ArrayList<File> finalFiles = new ArrayList<File>();
	      for (File file : listFiles) {		
	    	  if(file.isFile())
	    	  {
	    		  finalFiles.add(file);
	    	  }    	  
		}
	    return finalFiles;
	}
	
	@Override
	public ArrayList<File> findFiles(Long emailSentId, String reqType) {	    
		 File directoryPath = null;
			if(reqType.equalsIgnoreCase("icg"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileICG + emailSentId);
			else if(reqType.equalsIgnoreCase("icgOfficer"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileICGOfficer + emailSentId);
			else if(reqType.equalsIgnoreCase("afcat"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileAfcat + emailSentId);
			else if(reqType.equalsIgnoreCase("casb"))
				directoryPath = new File(emailAttachmentDirFromPropertyFileCASB + emailSentId);
		System.out.println("Path : "+directoryPath.getAbsolutePath());
		File[] listFiles = directoryPath.listFiles();
	    ArrayList<File> finalFiles = new ArrayList<File>();
	    for (File file : listFiles) {
	    	if(file.isFile())
	    		finalFiles.add(file);	    	  
		}
	    System.out.println("finalFiles.size() : "+finalFiles.size());
	    return finalFiles;
	}
	
}

