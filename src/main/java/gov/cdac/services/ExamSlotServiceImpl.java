package gov.cdac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.icgPojo.ExamSlot;
import gov.cdac.icgRepositories.ExamSlotRepository;

@Service
@Transactional
public class ExamSlotServiceImpl implements ExamSlotService {
	
	@Autowired
	ExamSlotRepository examSlotRepo;

	@Override
	public List<ExamSlot> getExamSlots() {
		// TODO Auto-generated method stub
		return examSlotRepo.findAll();
	}

}
