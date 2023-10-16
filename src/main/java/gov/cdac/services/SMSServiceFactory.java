package gov.cdac.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import gov.cdac.models.SMSModel;
import gov.cdac.models.SMSReasonMaster;
import gov.cdac.models.SMSTemplateMaster;

/**
 * 
 * @author shanurj
 *
 */
@Service
public class SMSServiceFactory {
    private SMSService icgSMSService;
    private SMSService casbSMSService;
    private SMSService afcatSMSService;
    private SMSService icgOfficerSMSService;
    
    /**
     * Constructor based autowiring with qualifier
     * 
     * @param icgSMSService
     * @param casbSMSService
     * @param afcatSMSService
     * @param icgOfficerSMSService
     */
    public SMSServiceFactory(
    	@Qualifier("icgSMSService") SMSService icgSMSService,
	    @Qualifier("casbSMSService") SMSService casbSMSService,
	    @Qualifier("afcatSMSService") SMSService afcatSMSService,
        @Qualifier("icgOfficerSMSService") SMSService icgOfficerSMSService)
    {
	this.icgSMSService = icgSMSService;
	this.casbSMSService = casbSMSService;
	this.afcatSMSService = afcatSMSService;
	this.icgOfficerSMSService = icgOfficerSMSService;
    }

    /**
     * Calls getPageData according to reqType
     * 
     * @param reqType
     * @return
     */
    public Map<String, Object> getPageData(String reqType) {
	if (reqType.equalsIgnoreCase("icg")) {
	    return icgSMSService.getPageData();
	} else if (reqType.equalsIgnoreCase("casb")) {
	    return casbSMSService.getPageData();
	} else if (reqType.equalsIgnoreCase("afcat")) {
	    return afcatSMSService.getPageData();
	} else if(reqType.equalsIgnoreCase("icgOfficer")) {
		return icgOfficerSMSService.getPageData();
	}
	return null;
    }

    /**
     * Stores SMSReasonMaster according to reqType
     * 
     * @param smsReasonMaster
     * @param reqType
     */
    public void addSMSReasonMaster(SMSReasonMaster smsReasonMaster, String reqType) {
	if (reqType.equalsIgnoreCase("icg")) {
	    icgSMSService.addSMSReasonMaster(smsReasonMaster);
	} else if (reqType.equalsIgnoreCase("casb")) {
	    casbSMSService.addSMSReasonMaster(smsReasonMaster);
	} else if (reqType.equalsIgnoreCase("afcat")) {
	    afcatSMSService.addSMSReasonMaster(smsReasonMaster);
	} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		icgOfficerSMSService.addSMSReasonMaster(smsReasonMaster);
	}
    }

    /**
     * Stores SMSTemplateMaster according to reqType
     * 
     * @param smsTemplateMaster
     * @param reqType
     */
    public void addSMSTemplate(SMSTemplateMaster smsTemplateMaster, String reqType) {
	if (reqType.equalsIgnoreCase("icg")) {
	    icgSMSService.addSMSTemplate(smsTemplateMaster);
	} else if (reqType.equalsIgnoreCase("casb")) {
	    casbSMSService.addSMSTemplate(smsTemplateMaster);
	} else if (reqType.equalsIgnoreCase("afcat")) {
	    afcatSMSService.addSMSTemplate(smsTemplateMaster);
	} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		icgOfficerSMSService.addSMSTemplate(smsTemplateMaster);
	}
    }

    /**
     * Fetch appcred ids according to reqType
     * 
     * @param centreId
     * @param slotIds
     * @param reqType
     * @return
     */
    public List<Long> getAppCredFromCentreAndSlot(Integer centreId, List<Integer> slotIds, String reqType) {
	if (reqType.equalsIgnoreCase("icg")) {
	    return icgSMSService.getAppCredFromCentreAndSlot(centreId, slotIds);
	} else if (reqType.equalsIgnoreCase("casb")) {
	    return casbSMSService.getAppCredFromCentreAndSlot(centreId, slotIds);
	} else if (reqType.equalsIgnoreCase("afcat")) {
	    return afcatSMSService.getAppCredFromCentreAndSlot(centreId, slotIds);
	} else if (reqType.equalsIgnoreCase("icgOfficer")) {
		return icgOfficerSMSService.getAppCredFromCentreAndSlot(centreId, slotIds);
	}
	return null;
    }

    /**
     * Send quick SMS according to reqType
     * 
     * @param mobileList
     * @param candidateCount
     * @param templateId
     * @param reqType
     */
    public void sendQuickBulkSMS(List<String> mobileList, List<Long> appCredIds, int candidateCount, String reqType, Long smsScheduleId) {
		if (reqType.equalsIgnoreCase("icg")) {
		    icgSMSService.sendSMSAsync(mobileList, appCredIds, smsScheduleId);
		} else if (reqType.equalsIgnoreCase("casb")) {
			casbSMSService.sendSMSAsync(mobileList, appCredIds, smsScheduleId);
		} else if (reqType.equalsIgnoreCase("afcat")) {
		    afcatSMSService.sendSMSAsync(mobileList, appCredIds, smsScheduleId);
		} else if (reqType.equalsIgnoreCase("icgOfficer")) {
			icgOfficerSMSService.sendSMSAsync(mobileList, appCredIds, smsScheduleId);
		}
		
		
		
    }

    /**
     * Send bulk SMS according to reqType, asynchronously
     * 
     * @param appCredIds
     * @param smsModel
     * @param candidateCount
     * @param reqType
     */
//    @Async
//    public void sendBulkSMS(List<Long> appCredIds, int candidateCount, String reqType, Long smsSentId) {
//	if (reqType.equalsIgnoreCase("icg")) {
//	    icgSMSService.sendBulkSMS(appCredIds, candidateCount, smsSentId);
//	} else if (reqType.equalsIgnoreCase("casb")) {
//		casbSMSService.sendBulkSMS(appCredIds, candidateCount, smsSentId);
//	} else if (reqType.equalsIgnoreCase("afcat")) {
//		List<AfcatSMSScheduleDetail> afcatSMSScheduleDetails = afcatSMSService.getSMSSentObject(smsSentId).getAfcatSMSScheduleDetails();
//	    afcatSMSService.sendSMSAsync(afcatSMSScheduleDetails.get(0).getSmsScheduleDetailId());	}
//    }

    /**
     * Store SMSSent object according to reqType
     * 
     * @param smsModel
     * @param candidateCount
     * @param reqType
     */
    public Long addSMSSentEntry(SMSModel smsModel, int candidateCount, String smsType, List<Long> appCredIds, List<String> mobileNos, String reqType) {	  	
    	Long smsScheduleId = null;
    	if (reqType.equalsIgnoreCase("icg")) {
    		smsScheduleId =  icgSMSService.addSMSSentEntry(smsModel, candidateCount, appCredIds, mobileNos, reqType, smsType);
		} else if (reqType.equalsIgnoreCase("casb")) {
			smsScheduleId = casbSMSService.addSMSSentEntry(smsModel, candidateCount, appCredIds, mobileNos, reqType, smsType);
		} else if (reqType.equalsIgnoreCase("afcat")) {
			smsScheduleId = afcatSMSService.addSMSSentEntry(smsModel, candidateCount, appCredIds, mobileNos, reqType, smsType);
		} else if(reqType.equalsIgnoreCase("icgOfficer")) {
			smsScheduleId = icgOfficerSMSService.addSMSSentEntry(smsModel, candidateCount, appCredIds, mobileNos, reqType, smsType);
		}
		return smsScheduleId;
    }

    /**
     * Fetch candidate detail according to centre, slot and reqType
     * 
     * @param centreId
     * @param slotIds
     * @param reqType
     * @return
     */
    public String centreSlotCountDetails(Integer centreId, List<Integer> slotIds, String reqType) {
	if (reqType.equalsIgnoreCase("icg")) {
	    return icgSMSService.centreSlotCountDetails(centreId, slotIds);
	} else if (reqType.equalsIgnoreCase("casb")) {
	    return casbSMSService.centreSlotCountDetails(centreId, slotIds);
	} else if (reqType.equalsIgnoreCase("afcat")) {
	    return afcatSMSService.centreSlotCountDetails(centreId, slotIds);
	} else if(reqType.equalsIgnoreCase("icgOfficer")) {
		return icgOfficerSMSService.centreSlotCountDetails(centreId, slotIds);
	}
	return null;
    }
    
	
	public void sendSMSFactory(Long smsScheduleDetailId, String reqType) {
		if(reqType.equalsIgnoreCase("icg")) {
			icgSMSService.sendSMSAsync(null, null, smsScheduleDetailId);
		}else if(reqType.equalsIgnoreCase("afcat")) {
			afcatSMSService.sendSMSAsync(null, null, smsScheduleDetailId);
		}else if(reqType.equals("icgOfficer")) {
			icgOfficerSMSService.sendSMSAsync(null, null, smsScheduleDetailId);
		}
		else if(reqType.equals("casb")) {
			casbSMSService.sendSMSAsync(null,null,smsScheduleDetailId);
		}
	}
	
	public void extractReport(HttpServletResponse response, Long smsScheduleId, String reqType) throws IOException {
		if(reqType.equalsIgnoreCase("icg")) {
			icgSMSService.extractExcelReport(response, smsScheduleId);
		}else if(reqType.equalsIgnoreCase("afcat")) {
			afcatSMSService.extractExcelReport(response, smsScheduleId);
		}else if(reqType.equals("icgOfficer")) {
			icgOfficerSMSService.extractExcelReport(response, smsScheduleId);
		}
		else if(reqType.equals("casb"))  {
			casbSMSService.extractExcelReport(response, smsScheduleId);
		}		
	}
}
