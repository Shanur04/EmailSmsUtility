package gov.cdac.emailservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gov.cdac.emailservice.icg.pojo.ApplicantHallticket;
import gov.cdac.emailservice.projection.EmailProjection;


public interface ApplicantHallTicketService {
	
	public List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(ArrayList<String> slotsArrayList,ArrayList<String> centersArrayList);

	public Integer getTotalEmailSentCount();

	public List<ApplicantHallticket> getTotalEmailNotSentData();

	public List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(ArrayList<String> slotsArrayList, ArrayList<String> centersArrayList);
	
	public void updateEmailSentFlag(Set<String> mailSentSet, boolean flagToSet);
	
	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidate();

	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateMultiPost();

	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateAllPost();

	public void updateEmailSentFlagInApplicantCredential(Set<String> mailSentSet, boolean b);
	
}

