package gov.cdac.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import gov.cdac.models.CentreModel;
import gov.cdac.models.EmailModel;
import gov.cdac.models.ReportInfo;
import gov.cdac.models.TestEmailBulkModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MailService {

    /**
     * Return map object containing required page data
     * 
     * @return
     */
    Map<String, Object> getPageData();

    /**
     * Read applicant credential from csv
     * 
     * @param file
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    static List<Long> readCSV(MultipartFile file) throws NumberFormatException, IOException {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	String line = "";
	List<Long> appCredIds = new ArrayList<>();
	while ((line = bufferedReader.readLine()) != null) {
	    appCredIds.add(Long.parseLong(line));
	}
	return appCredIds;
    }

    void extractExcelReport(Long smsScheduleId, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException;
    
	void extractTextReport(String path, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException;

    void sendEmailAsync(List<String> emailIdList, List<Long> appCredIds, Long emailScheduleDeailId);
     

	void sendTestEmailsService(List<String> emailIdList, List<Long> appCredIdsFromFile, int candidatesCount, Long emailSentId, Long emailScheduleDetailId);

	void cIMultiple(List<String> emailIdList, List<Long> appCredIdsFromFile, int candidatesCount,
			Long emailSentId, Long emailScheduleDeailId);

	void sendEmails(String emailContent, String emailSubject, String sendAdmitCardsRadio, String slots,
			String centers, String mailServerHost, String mailServerPort, Boolean starttls, String exam,
			String mailReason, String emailBreathingTime, String emailsPerBatch, String socketFactoryPort,
			Long emailSentId, Long emailScheduleDeailId);
	
	ResponseEntity<?> sendMultipleEmailOfRejected(TestEmailBulkModel testEmailBulkModel, HttpServletRequest request);

	String addEmailSentEntry(EmailModel emailModel, String emailType, String reqType, HttpServletRequest request);

	List<ReportInfo> searchListOfPathByEmailId(String emailId);

	List<ReportInfo> searchListOfPathInFolder(String folderPath);
	
	String getPath(String emialId, String filePath);

	List<CentreModel> populateListOfCentres();

}
