package gov.cdac.emailservice;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Executor;
//gitadmin.pune.cdac.in/indian-coast-guard/code/seatallocation.git

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import gov.cdac.emailservice.afcat.pojo.AfcatEmailScheduleDetail;
import gov.cdac.emailservice.afcat.pojo.AfcatEmailSent;
import gov.cdac.emailservice.casb.pojo.CasbEmailScheduleDetail;
import gov.cdac.emailservice.casb.pojo.CasbEmailSent;
import gov.cdac.emailservice.casb.repository.EmailScheduleDetailRepository;
import gov.cdac.emailservice.casb.repository.EmailSentRepository;
import gov.cdac.emailservice.casb.repository.EmailStatusRepository;
//import gov.cdac.emailservice.casb.pojo.CasbEmailScheduleDetail;
//import gov.cdac.emailservice.casb.pojo.CasbEmailSent;
import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerEmailScheduleDetail;
import gov.cdac.emailservice.icg.officer.pojo.IcgOfficerEmailSent;
import gov.cdac.emailservice.icg.pojo.IcgEmailScheduleDetail;
import gov.cdac.emailservice.icg.pojo.IcgEmailSent;
import gov.cdac.emailservice.services.MailServiceFactory;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class email_service_Afcat_ICGSailor_ICGOfficer_application extends SpringBootServletInitializer {
	
	@Autowired
	@Qualifier("afcatEmailSentRepository")
	private gov.cdac.emailservice.afcat.repository.EmailSentRepository afcatEmailSentRepository;
	
	@Autowired
	@Qualifier("afcatEmailScheduledDetailRepository")
	private gov.cdac.emailservice.afcat.repository.EmailScheduleDetailRepository afcatEmailScheduleRepository;
	
	@Autowired
	@Qualifier("afcatEmailStatusRepository")
	private gov.cdac.emailservice.afcat.repository.EmailStatusRepository afcatEmailStatusRepository;	

	@Autowired
	@Qualifier("icgEmailSentRepository")
	private gov.cdac.emailservice.icg.repositories.EmailSentRepository icgEmailSentRepository;
	
	@Autowired
	@Qualifier("icgEmailScheduledDetailRepository")
	private gov.cdac.emailservice.icg.repositories.EmailScheduleDetailRepository icgEmailScheduleRepository;
	
	@Autowired
	@Qualifier("icgEmailStatusRepository")
	private gov.cdac.emailservice.icg.repositories.EmailStatusRepository icgEmailStatusRepository;	@Autowired
	
	@Qualifier("icgOfficerEmailSentRepository")
	private gov.cdac.emailservice.icg.officer.repository.EmailSentRepository icgOfficerEmailSentRepository;
	
	@Autowired
	@Qualifier("icgOfficerEmailScheduledDetailRepository")
	private gov.cdac.emailservice.icg.officer.repository.EmailScheduleDetailRepository icgOfficerEmailScheduleRepository;
	
	@Autowired
	@Qualifier("icgOfficerEmailStatusRepository")
	private gov.cdac.emailservice.icg.officer.repository.EmailStatusRepository icgOfficerEmailStatusRepository;
	
	@Autowired
	@Qualifier("casbEmailSentRepository")
	private EmailSentRepository casbEmailSentRepository;
	
	@Autowired
	@Qualifier("casbEmailScheduledDetailRepository")
	private EmailScheduleDetailRepository casbEmailScheduleRepository;
	
	@Autowired
	@Qualifier("casbEmailStatusRepository")
	private EmailStatusRepository casbEmailStatusRepository;
	
	@Autowired
	private MailServiceFactory emailServiceFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(email_service_Afcat_ICGSailor_ICGOfficer_application.class, args);
	}

	@Bean
	public Executor threadPoolTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("MyThread - ");
        executor.initialize();
        return executor;
	}
	
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructAfcat() {
       	List<AfcatEmailSent> list = afcatEmailSentRepository.getScheduledEmailSentList();
    	for(AfcatEmailSent afcatEmailSent : list) {
    		Boolean flag = false;
    		List<AfcatEmailScheduleDetail> afcatEmailScheduleDetailList = afcatEmailScheduleRepository.findByEmailSentId(afcatEmailSent.getEmailSentId());
    		Boolean completedFlag = true;
    		for(AfcatEmailScheduleDetail iesd  : afcatEmailScheduleDetailList) {
    			if(flag == false  && (afcatEmailSent.getEmailSentType() == 1 || afcatEmailSent.getEmailSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(iesd.getEmailScheduleStatus().getStatusId() == 3L  && 
	    					((iesd.getEmailScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				iesd.setEmailScheduleStatus(afcatEmailStatusRepository.findById(4L).get());
	    				afcatEmailScheduleRepository.save(iesd);
	    				emailServiceFactory.sendAsyncFactoryMethod(null, null, iesd.getEmailScheduleDetailId(), afcatEmailSent.getReqType());
	    			}
    			}
    			
    			if(iesd.getEmailScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			afcatEmailSent.setEmailEndDatetime(new Timestamp(System.currentTimeMillis()));
    			afcatEmailSent.setEmailSentStatus(true);
    			afcatEmailSentRepository.save(afcatEmailSent);
    		}
    	}
    }
    
    
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructCasb() {
       	List<CasbEmailSent> list = casbEmailSentRepository.getScheduledEmailSentList();
       	//System.out.println(list);
    	for(CasbEmailSent casbEmailSent : list) {
    		Boolean flag = false;
    		List<CasbEmailScheduleDetail> casbEmailScheduleDetailList = casbEmailScheduleRepository.findByEmailSentId(casbEmailSent.getEmailSentId());
    		Boolean completedFlag = true;
    		for(CasbEmailScheduleDetail iesd  : casbEmailScheduleDetailList) {
    			if(flag == false  && (casbEmailSent.getEmailSentType() == 1 || casbEmailSent.getEmailSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(iesd.getEmailScheduleStatus().getStatusId() == 3L  && 
	    					((iesd.getEmailScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				iesd.setEmailScheduleStatus(casbEmailStatusRepository.findById(4L).get());
	    				casbEmailScheduleRepository.save(iesd);
	    				emailServiceFactory.sendAsyncFactoryMethod(null, null, iesd.getEmailScheduleDetailId(), casbEmailSent.getReqType());
	    			}
    			}
    			
    			if(iesd.getEmailScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			casbEmailSent.setEmailEndDatetime(new Timestamp(System.currentTimeMillis()));
    			casbEmailSent.setEmailSentStatus(true);
    			casbEmailSentRepository.save(casbEmailSent);
    		}
    	}
    }
    
    
    
    
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructIcg() {
       	List<IcgEmailSent> list = icgEmailSentRepository.getScheduledEmailSentList();
    	for(IcgEmailSent icgEmailSent : list) {
    		Boolean flag = false;
    		List<IcgEmailScheduleDetail> icgEmailScheduleDetailList = icgEmailScheduleRepository.findByEmailSentId(icgEmailSent.getEmailSentId());
    		Boolean completedFlag = true;
    		for(IcgEmailScheduleDetail iesd  : icgEmailScheduleDetailList) {
    			if(flag == false  && (icgEmailSent.getEmailSentType() == 1 || icgEmailSent.getEmailSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(iesd.getEmailScheduleStatus().getStatusId() == 3L  && 
	    					((iesd.getEmailScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				iesd.setEmailScheduleStatus(icgEmailStatusRepository.findById(4L).get());
	    				icgEmailScheduleRepository.save(iesd);
	    				emailServiceFactory.sendAsyncFactoryMethod(null, null, iesd.getEmailScheduleDetailId(), icgEmailSent.getReqType());
	    			}
    			}
    			
    			if(iesd.getEmailScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			icgEmailSent.setEmailEndDatetime(new Timestamp(System.currentTimeMillis()));
    			icgEmailSent.setEmailSentStatus(true);
    			icgEmailSentRepository.save(icgEmailSent);
    		}
    	}
    }
    
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructIcgOfficer() {
       	List<IcgOfficerEmailSent> list = icgOfficerEmailSentRepository.getScheduledEmailSentList();
    	for(IcgOfficerEmailSent icgOfficerEmailSent : list) {
    		Boolean flag = false;
    		List<IcgOfficerEmailScheduleDetail> icgOfficerEmailScheduleDetailList = icgOfficerEmailScheduleRepository.findByEmailSentId(icgOfficerEmailSent.getEmailSentId());
    		Boolean completedFlag = true;
    		for(IcgOfficerEmailScheduleDetail iesd  : icgOfficerEmailScheduleDetailList) {
    			if(flag == false  && (icgOfficerEmailSent.getEmailSentType() == 1 || icgOfficerEmailSent.getEmailSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(iesd.getEmailScheduleStatus().getStatusId() == 3L  && 
	    					((iesd.getEmailScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				iesd.setEmailScheduleStatus(icgOfficerEmailStatusRepository.findById(4L).get());
	    				icgOfficerEmailScheduleRepository.save(iesd);
	    				emailServiceFactory.sendAsyncFactoryMethod(null, null, iesd.getEmailScheduleDetailId(), icgOfficerEmailSent.getReqType());
	    			}
    			}
    			
    			if(iesd.getEmailScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			icgOfficerEmailSent.setEmailEndDatetime(new Timestamp(System.currentTimeMillis()));
    			icgOfficerEmailSent.setEmailSentStatus(true);
    			icgOfficerEmailSentRepository.save(icgOfficerEmailSent);
    		}
    	}
    }
    
    
    
}
