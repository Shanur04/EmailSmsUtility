package gov.cdac.emailservice.validator;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;


public class InputValidator {
	
	public static boolean isValidGender(String str)
	{
		if(str.equalsIgnoreCase("Male"))
		{
			return true;
		}
		return false;
	}
	
	
	
	public static boolean isValidField(String str)
	{
		
		if(str.contains("<")|| str.contains(">"))
		{
			return false;
		}
		return true;
		
	}
	
	
	public static boolean isValidEmail(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9._]+@[a-zA-Z.]+[a-zA-Z]{2,6}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public static boolean isValidEmail2(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	public static boolean isValidPassword(String str)
	{
		if(Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
		
	
	public static boolean isValidNationality(String str)
	{
		if(str.equalsIgnoreCase("Indian"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidMobileNo(String str)
	{
		if(Pattern.matches("^[1-9]{1}[0-9]{9}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidPhoneWithAreaCode(String str)
	{
		if(Pattern.matches("^(?=.*[0-9])[0-9 ()/-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidName(String str)
	{
		if(Pattern.matches("^[a-zA-Z]+( [a-zA-Z]+)*$", str))
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
	}
	
	public static boolean validNameSignup(String str)
	{
		if(Pattern.matches("^[a-z A-Z]+[ ]?[.]?[ ]?[a-z A-Z]*", str))
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
		
	}
	
	public static boolean isValidHallticketNo(String str)
	{
		if(Pattern.matches("^[A-Z0-9]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isAlphaNumericeData(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9 ]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isSubjectValidName(String str)
	{
		/*if(Pattern.matches("^[a-zA-Z0-9 ()-]+$", str))*/
		//alphanumeric but alphabets are compulsary and ( )- allowed 
		if(Pattern.matches("^[A-Za-z0-9() -]*[()-]*[a-zA-Z]+[()-]*[A-Za-z0-9() -]*", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isSpecialAlpha(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9 ,.-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isSpecialAlphaWithoutDigits(String str)
	{
		if(Pattern.matches("^[a-zA-Z ,.-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isValidIdentificationMark(String str)
	{
		if(Pattern.matches("^[a-zA-Z ,]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isValidCityName(String str)
	{
		if(Pattern.matches("^[a-zA-Z &-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	public static boolean isSpecialAlpha2(String str)
	{
		if(Pattern.matches("^[a-zA-Z ()-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean isValidNo(String str)
	{
		if(Pattern.matches("^[1-9][0-9]*$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidNoFromZero(String str)
	{
		if(Pattern.matches("^[0-9][0-9]*$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidDecimal(String str)
	{
		if(Pattern.matches("^[0-9]{1,3}[.]{1}[0-9]{1,2}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidHouseStreet(String str)
	{
		if(Pattern.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 ,.()/&-]+$", str)) //if(Pattern.matches("^[a-zA-Z0-9 ,.()/-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidTehsil(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9 ,()&-]+$", str)) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidPostOffice(String str)
	{
		if(Pattern.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 -&().]+$", str)) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean dropDownValidation(Integer cityId)
	{
		if(isValidField(cityId+""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*public static boolean isValidCaptcha(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9]{6}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/
	public static boolean isValidNameWithSpecialCharacters(String str)
	{
		if(Pattern.matches("^[a-zA-Z0-9 ,.-]+$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidMonth(String str)
	{
		if(Pattern.matches("^[a-zA-Z]+$", str))
			return true;
		else
			return false;
	}	
	
	public static String findFile(String fileName,String applicantIdInString, String fileStoragePath)
    {
		String filePathToReturn = null;
		String fileNameWithOutExt = null;
	
		 File dir = new File(fileStoragePath  + applicantIdInString);
        File[] list = dir.listFiles();
        fileName = FilenameUtils.removeExtension(fileName);
        if(list!=null)
        for (File file : list)
        {
        	fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
            if (file.isDirectory())
            {
                findFile(fileName,applicantIdInString, fileStoragePath);
            }
            else if (fileName.equalsIgnoreCase(fileNameWithOutExt))
            {
            	filePathToReturn = dir.getAbsolutePath() + File.separator + file.getName();
                break;
            }
        }
        return filePathToReturn;
    }
	public static boolean isValidHeight(String str)
	{
		if(Pattern.matches("^[1-9]{1}[0-9]{2}[.]{0,1}[0-9]{0,1}$", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static Boolean isValidDate(String dateOfBirth)
	{
		return Pattern.matches("^(19|20)\\d\\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$",dateOfBirth);
	}
	
	public static Boolean isEmptyString(String string)
	{
		return string.replaceAll(" ", "").isEmpty();
	}
	
	public static boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	
	public static boolean isValiddecimalCGPA(String str) {
		if(Pattern.matches("^[0-9](\\.[0-9]+)?$", str) || str.equals("10"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isValidGRADE(String str) {
		if(Pattern.matches("[A-F][+-]?", str))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	public static boolean dropDownValidationName(String cityName)
	{
		if(isValidField(cityName+""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}



	public static boolean isValidCertificateNumber(String CertificateNo) {
		if(Pattern.matches("^[a-zA-Z0-9 \\s\\-/()]*$", CertificateNo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static boolean isValidCity(String city) {
		//if(Pattern.matches("^[a-zA-Z]+(?:[\\s-&][a-zA-Z]+)*$",city))
		if(Pattern.matches("^[a-zA-Z]+[-&\\sa-zA-Z]*[a-zA-Z]+$", city))  //my regex
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isNotOnlyNumericValue(String str) {
		if(Pattern.matches("^(?=.*[a-zA-Z])[A-Za-z0-9\\-_/ ]+$",str))
			return true;
		else
			return false;
	}



	public static boolean isValidAggregatePercenatge(String aggregatePercentage) {
		if(Pattern.matches("(^100([.]0{1,3})?)$|(^\\d{1,2}([.]\\d{1,3})?)$", aggregatePercentage))  //my regex
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
