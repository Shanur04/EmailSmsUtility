package gov.cdac.emailservice.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gov.cdac.emailservice.models.CentreModel;
import gov.cdac.emailservice.models.EmailModel;
import gov.cdac.emailservice.models.ReportInfo;
import gov.cdac.emailservice.models.TestEmailBulkModel;
import gov.cdac.emailservice.projection.EmailReportSummary;

/**
 * 
 * @author shiri
 *
 */
@Service
public class MailServiceFactory {
    private MailService icgMailService;
    private MailService afcatMailService;
    private MailService icgOfficerMailService;
    private MailService casbMailService;
    
    /**
     * Constructor based autowiring with qualifier
     * 
     * @param icgSMSService
     * @param casbSMSService
     * @param afcatSMSService
     * @param icgOfficerSMSService
     */
    public MailServiceFactory(
    	@Qualifier("afcatMailService") MailService afcatMailService,
    	@Qualifier("icgMailService") MailService icgMailService,
    	@Qualifier("icgOfficerMailService") MailService icgOfficerMailService,
    	@Qualifier("casbMailService") MailService casbMailService)
    {
    	this.afcatMailService = afcatMailService;
    	this.icgMailService  = icgMailService;
    	this.icgOfficerMailService = icgOfficerMailService;
    	this.casbMailService = casbMailService;
    }

    /**
     * Calls getPageData according to reqType
     * 
     * @param reqType
     * @return
     */
    public Map<String, Object> getPageData(String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.getPageData();
		} else if (reqType.equalsIgnoreCase("casb")) {
		    return casbMailService.getPageData();
		} else if (reqType.equalsIgnoreCase("afcat")) {
		    return afcatMailService.getPageData();
		} else if(reqType.equalsIgnoreCase("icgOfficer")) {
			return icgOfficerMailService.getPageData();
		}
		return null;
    }

	public void extractReport(Long smsScheduleId, String reqType, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		if(reqType.equalsIgnoreCase("icg")) {
			icgMailService.extractExcelReport(smsScheduleId, httpRequest, httpResponse);
		}else if(reqType.equalsIgnoreCase("afcat")) {
			afcatMailService.extractExcelReport(smsScheduleId, httpRequest, httpResponse);
		}else if(reqType.equals("icgOfficer")) {
			icgOfficerMailService.extractExcelReport(smsScheduleId, httpRequest, httpResponse);
		}else if(reqType.equals("casb")) {
			casbMailService.extractExcelReport(smsScheduleId, httpRequest, httpResponse);
		}		
	}
	public void extractTextReport(String path, String reqType, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		if(reqType.equalsIgnoreCase("icg")) {
			icgMailService.extractTextReport(path, httpRequest, httpResponse);
		}else if(reqType.equalsIgnoreCase("afcat")) {
			afcatMailService.extractTextReport(path, httpRequest, httpResponse);
		}else if(reqType.equals("icgOfficer")) {
			icgOfficerMailService.extractTextReport(path, httpRequest, httpResponse);
		}
		else if(reqType.equals("casb")) {
			casbMailService.extractTextReport(path, httpRequest, httpResponse);
		}		
	}
	public String addEmailDataEntry(EmailModel emailModel, String emailType, String reqType, HttpServletRequest request) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.addEmailSentEntry(emailModel, emailType, reqType, request);
		} else if (reqType.equalsIgnoreCase("casb")) {
			return casbMailService.addEmailSentEntry(emailModel, emailType, reqType, request);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		    return afcatMailService.addEmailSentEntry(emailModel, emailType, reqType, request);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.addEmailSentEntry(emailModel, emailType, reqType, request);
		}	
		return "";
	}

    
//    public void sendEmails(String emailContent, String emailSubject, String sendAdmitCardsRadio, String slots,
//    		String centers, String mailServerHost, String mailServerPort, Boolean starttls,	String exam, String mailReason,
//			String emailBreathingTime, String emailsPerBatch, String socketFactoryPort, String reqType, HttpServletRequest request) {
//		if (reqType.equalsIgnoreCase("icg")) {
//		    icgMailService.sendEmails(emailContent, emailSubject, sendAdmitCardsRadio, slots,
//		    		centers, mailServerHost, mailServerPort, starttls,	exam, mailReason,
//					emailBreathingTime, emailsPerBatch, socketFactoryPort, request);
//		} else if (reqType.equalsIgnoreCase("casb")) {
//		   // return casbMailService.sendEmails(emailContent, emailSubject, sendAdmitCardsRadio, slots,
//		    //		centers, mailServerHost, mailServerPort, starttls,	exam, mailReason,
//			//		emailBreathingTime, emailsPerBatch, socketFactoryPort, request);			
//		} else if (reqType.equalsIgnoreCase("afcat")) {
//		    return afcatMailService.sendEmails(emailContent, emailSubject, sendAdmitCardsRadio, slots,
//		    		centers, mailServerHost, mailServerPort, starttls,	exam, mailReason,
//					emailBreathingTime, emailsPerBatch, socketFactoryPort, request);		
//		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
//		    return icgOfficerMailService.sendEmails(emailContent, emailSubject, sendAdmitCardsRadio, slots,
//		    		centers, mailServerHost, mailServerPort, starttls,	exam, mailReason,
//					emailBreathingTime, emailsPerBatch, socketFactoryPort, request);
//		}	
//		return "success";
//    }
    
    public ResponseEntity<?> sendMultipleEmailOfRejected(TestEmailBulkModel testEmailBulkModel, String reqType, HttpServletRequest request) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.sendMultipleEmailOfRejected(testEmailBulkModel, request);
		} else if (reqType.equalsIgnoreCase("casb")) {
		  return casbMailService.sendMultipleEmailOfRejected(testEmailBulkModel, request);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		    return afcatMailService.sendMultipleEmailOfRejected(testEmailBulkModel, request);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.sendMultipleEmailOfRejected(testEmailBulkModel, request);
		}	
		return new ResponseEntity<>(true, HttpStatus.OK);   	
    }

	public void sendAsyncFactoryMethod(ArrayList<String> emailIdList, ArrayList<Long> appCredIdList, Long scheduleId, String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    icgMailService.sendEmailAsync(emailIdList, appCredIdList, scheduleId);
		} else if (reqType.equalsIgnoreCase("casb")) {
		  casbMailService.sendEmailAsync(emailIdList, appCredIdList, scheduleId);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		    afcatMailService.sendEmailAsync(emailIdList, appCredIdList, scheduleId);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    icgOfficerMailService.sendEmailAsync(emailIdList, appCredIdList, scheduleId);
		}	
	}

	public List<ReportInfo> searchByEmailId(String emailId, String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.searchListOfPathByEmailId(emailId);
		} else if (reqType.equalsIgnoreCase("casb")) {
			return casbMailService.searchListOfPathByEmailId(emailId);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		   return afcatMailService.searchListOfPathByEmailId(emailId);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.searchListOfPathByEmailId(emailId);
		}
		return null;
	}
	
	public List<ReportInfo> getUploadDocPaths(String folderPath, String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.searchListOfPathInFolder(folderPath);
		} else if (reqType.equalsIgnoreCase("casb")) {
			return casbMailService.searchListOfPathInFolder(folderPath);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		   return afcatMailService.searchListOfPathInFolder(folderPath);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.searchListOfPathInFolder(folderPath);
		}
		return null;
	}
	
	public String getPath(String emailId, String filePath, String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.getPath(emailId, filePath);
		} else if (reqType.equalsIgnoreCase("casb")) {
			return casbMailService.getPath(emailId, filePath);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		   return afcatMailService.getPath(emailId, filePath);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.getPath(emailId, filePath);
		}
		return null;
	}


	public List<CentreModel> populateListOfCentres(String reqType) {
		if (reqType.equalsIgnoreCase("icg")) {
		    return icgMailService.populateListOfCentres();
		} else if (reqType.equalsIgnoreCase("casb")) {
			return casbMailService.populateListOfCentres();
		} else if (reqType.equalsIgnoreCase("afcat")) {
		   return afcatMailService.populateListOfCentres();
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		    return icgOfficerMailService.populateListOfCentres();
		}
		return null;
	}
}
