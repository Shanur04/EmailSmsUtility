package gov.cdac.exception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileFetchNullException extends Exception{
	
    private static final Logger filelogger= LogManager.getLogger(FileFetchNullException.class);

	private static final long serialVersionUID = 1L;

	public FileFetchNullException(String message)
	{
		filelogger.error("FileFetchNullException:"+message);
	}
}
