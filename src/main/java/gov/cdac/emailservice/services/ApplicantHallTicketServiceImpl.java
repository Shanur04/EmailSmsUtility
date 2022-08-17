package gov.cdac.emailservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.emailservice.icg.pojo.ApplicantHallticket;
import gov.cdac.emailservice.icg.repositories.ApplicantHallTicketRepository;
import gov.cdac.emailservice.projection.EmailProjection;

/**
 * this class is implementation of ApplicantHallTicketService
 * @author mohit
 *
 */
@Service
@Transactional
public class ApplicantHallTicketServiceImpl implements ApplicantHallTicketService {
	
	@Autowired
	private ApplicantHallTicketRepository applicantHallTicketRepo;
	

	@Override
	public List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(ArrayList<String> slotsArrayList,ArrayList<String> centersArrayList) {
		return applicantHallTicketRepo.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlot(slotsArrayList,centersArrayList);
	}

	@Override
	public Integer getTotalEmailSentCount() {
		return applicantHallTicketRepo.getTotalEmailSentCount();
	}

	@Override
	public List<ApplicantHallticket> getTotalEmailNotSentData() {
		return applicantHallTicketRepo.getTotalEmailNotSentData();
	}

	@Override
	public List<Object[]> getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(ArrayList<String> slotsArrayList, ArrayList<String> centersArrayList) {
		return applicantHallTicketRepo.getAllEmailIdsAndHallTicketNumbersOrderByCenterCodeAndSlotForReExam(slotsArrayList,centersArrayList);
	}

	@Override
	public void updateEmailSentFlag(Set<String> mailSentSet, boolean flagToSet) {
		applicantHallTicketRepo.updateEmailSentFlag(mailSentSet,flagToSet);

	}

	@Override
	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidate() {
		// TODO Auto-generated method stub
		return applicantHallTicketRepo.getAllRejectedEmailIdWithReasonForSamePost();
	}

	@Override
	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateMultiPost() {
		// TODO Auto-generated method stub
		return applicantHallTicketRepo.getAllRejectedEmailIdWithReasonForMultiplePost();
	}

	@Override
	public List<EmailProjection> getAllEmailIdsAndHallTicketNumbersOfRejectedCandidateAllPost() {
		// TODO Auto-generated method stub
		return applicantHallTicketRepo.getAllRejectedEmailIdWithReasonForAllPost();
	}

	@Override
	public void updateEmailSentFlagInApplicantCredential(Set<String> mailSentSet, boolean b) {
		applicantHallTicketRepo.updateEmailSentFlagInApplicantCredential(mailSentSet,b);
	}


}
