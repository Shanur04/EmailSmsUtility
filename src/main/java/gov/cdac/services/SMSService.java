package gov.cdac.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 
 * @author shanurj
 *
 */
public interface SMSService {

    /**
     * Return map object containing required page data
     * 
     * @return
     */
    Map<String, Object> getPageData();

    /**
     * Store SMSReasonMaster
     * 
     * @param smsReasonMaster
     */
    void addSMSReasonMaster(SMSReasonMaster smsReasonMaster);

    /**
     * Store SMSTemplateMaster
     * 
     * @param smsTemplateMaster
     */
    void addSMSTemplate(SMSTemplateMaster smsTemplateMaster);

    /**
     * Read applicant credential from csv
     * 
     * @param file
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    static List<Long> readCSV1(MultipartFile file) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String line = "";
		List<Long> appCredIds = new ArrayList<>();
		while ((line = bufferedReader.readLine()) != null) {
		    appCredIds.add(Long.parseLong(line));
		}
		return appCredIds;
    }

    
    static List<String> readCSV2(MultipartFile file) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String line = "";
		List<String> mobileNos = new ArrayList<>();
		while ((line = bufferedReader.readLine()) != null) {
			mobileNos.add(line);
		}
		return mobileNos;
    }
    /**
     * Get applicant credential id from centre and slot
     * 
     * @param centreId
     * @param slotIds
     * @return
     */
    List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds);

    /**
     * 
     * @param mobileList
     * @param candidateCount
     * @param templateId
     */
    void sendQuickSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, Long smsSentId, Long smsScheduleId);

    /**
     * 
     * @param appCredIds
     * @param smsModel
     * @param candidateCount
     */
    void sendBulkSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, Long smsSentId, Long smsScheduleId);

    /**
     * Store SMSSent
     * 
     * @param smsModel
     * @param candidateCount
     */
    Long addSMSSentEntry(SMSModel smsModel, int candidateCount, List<Long> appCredIds, List<String> mobileNos, String reqType, String smsType);

    /**
     * Get centre and slot wise candidate count
     * 
     * @param centreId
     * @param slotIds
     * @return
     */
    String centreSlotCountDetails(Integer centreId, List<Integer> slotIds);
    
    void sendSMSAsync(List<String> mobileList, List<Long> appCredIds, Long smsScheduleDetailId);
     
	void extractExcelReport(HttpServletResponse response, Long smsScheduleId) throws IOException;

}
