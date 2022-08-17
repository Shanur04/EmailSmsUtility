package gov.cdac.emailservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.cdac.emailservice.icg.pojo.ExamSlot;
import gov.cdac.emailservice.icg.repositories.ExamSlotRepository;

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
