package gov.cdac.emailservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.cdac.emailservice.exception.EmailNotValidException;
import gov.cdac.emailservice.exception.RegistrationNumberInvalidException;



public class StringValidator {
	
	/**
	 * This will check the given email id is correect or not
	 * 
	 * @param email
	 * @return
	 * @throws EmailNotValidException
	 */
	public static boolean isEmailValid(String email) throws EmailNotValidException {
		String emailPattern = "^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		if (!m.matches())
			throw new EmailNotValidException(
					"Email Id: " + email + " is not valid! Please rewrite the file and upload.");
		return m.matches();
	}

	/**
	 * This will check if given registration number is in proper format provided as
	 * per the database
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isRegistrationNumberValid(String num) throws RegistrationNumberInvalidException {

		String regNumberPattern = "^[0-9]{4}[A-Z]{2}[0-9]{7}$";
		Pattern p = Pattern.compile(regNumberPattern);
		Matcher m = p.matcher(num);
		if (!m.matches())
			throw new RegistrationNumberInvalidException(
					num + " is invalid Registration Number ,Please try again!");
		return m.matches();

	}

	/**
	 * This method will validate AppCredIds they should not be containing any spcial
	 * chatacters and Letters
	 */
	public static boolean isAppCredIdValid(Long app) {
		String appCredIdPattern = " /^[a-zA-Z!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$/";
		Pattern p = Pattern.compile(appCredIdPattern);
		Matcher m = p.matcher(app.toString());

		return m.matches();

	}

}
