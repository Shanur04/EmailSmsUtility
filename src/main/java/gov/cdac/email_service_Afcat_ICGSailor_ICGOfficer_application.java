package gov.cdac;

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

import gov.cdac.afcatPojo.AfcatEmailScheduleDetail;
import gov.cdac.afcatPojo.AfcatEmailSent;
import gov.cdac.casbPojo.CasbEmailScheduleDetail;
import gov.cdac.casbPojo.CasbEmailSent;
import gov.cdac.casbRepository.EmailScheduleDetailRepository;
import gov.cdac.casbRepository.EmailSentRepository;
import gov.cdac.casbRepository.EmailStatusRepository;
import gov.cdac.icgOfficerPojo.IcgOfficerEmailScheduleDetail;
import gov.cdac.icgOfficerPojo.IcgOfficerEmailSent;
import gov.cdac.icgPojo.IcgEmailScheduleDetail;
import gov.cdac.icgPojo.IcgEmailSent;
import gov.cdac.services.MailServiceFactory;
import gov.cdac.services.SMSServiceFactory;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class email_service_Afcat_ICGSailor_ICGOfficer_application extends SpringBootServletInitializer {
	
	@Autowired
	@Qualifier("afcatEmailSentRepository")
	private gov.cdac.afcatRepository.EmailSentRepository afcatEmailSentRepository;
	
	@Autowired
	@Qualifier("afcatEmailScheduledDetailRepository")
	private gov.cdac.afcatRepository.EmailScheduleDetailRepository afcatEmailScheduleRepository;
	
	@Autowired
	@Qualifier("afcatEmailStatusRepository")
	private gov.cdac.afcatRepository.EmailStatusRepository afcatEmailStatusRepository;	

	@Autowired
	@Qualifier("icgEmailSentRepository")
	private gov.cdac.icgRepositories.EmailSentRepository icgEmailSentRepository;
	
	@Autowired
	@Qualifier("icgEmailScheduledDetailRepository")
	private gov.cdac.icgRepositories.EmailScheduleDetailRepository icgEmailScheduleRepository;
	
	@Autowired
	@Qualifier("icgEmailStatusRepository")
	private gov.cdac.icgRepositories.EmailStatusRepository icgEmailStatusRepository;	@Autowired
	
	@Qualifier("icgOfficerEmailSentRepository")
	private gov.cdac.icgOfficerRepository.EmailSentRepository icgOfficerEmailSentRepository;
	
	@Autowired
	@Qualifier("icgOfficerEmailScheduledDetailRepository")
	private gov.cdac.icgOfficerRepository.EmailScheduleDetailRepository icgOfficerEmailScheduleRepository;
	
	@Autowired
	@Qualifier("icgOfficerEmailStatusRepository")
	private gov.cdac.icgOfficerRepository.EmailStatusRepository icgOfficerEmailStatusRepository;
	
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
	
	@Autowired
	@Qualifier("afcatSMSSentRepository")
	private gov.cdac.afcatRepository.SMSSentRepository afcatSMSSentRepository;
	
	@Autowired
	@Qualifier("afcatSMSScheduleRepository")
	private gov.cdac.afcatRepository.SMSScheduleRepository afcatSMSScheduleRepository;
	
	@Autowired
	@Qualifier("afcatSMSStatusRepository")
	private gov.cdac.afcatRepository.SMSStatusRepository afcatSMSStatusRepository;	

	@Autowired
	@Qualifier("icgSMSSentRepository")
	private gov.cdac.icgRepositories.SMSSentRepository icgSMSSentRepository;
	
	@Autowired
	@Qualifier("icgSMSScheduleRepository")
	private gov.cdac.icgRepositories.SMSScheduleRepository icgSMSScheduleRepository;
	
	@Autowired
	@Qualifier("icgSMSStatusRepository")
	private gov.cdac.icgRepositories.SMSStatusRepository icgSMSStatusRepository;	
	
	@Autowired
	@Qualifier("icgOfficerSMSSentRepository")
	private gov.cdac.icgOfficerRepository.IcgOfficerSMSSentRepository icgOfficerSMSSentRepository;
	
	@Autowired
	@Qualifier("icgOfficerSMSScheduleRepository")
	private gov.cdac.icgOfficerRepository.IcgOfficerSMSScheduleRepository icgOfficerSMSScheduleRepository;
	
	@Autowired
	@Qualifier("icgOfficerSMSStatusRepository")
	private gov.cdac.icgOfficerRepository.IcgOfficerSMSStatusRepository icgOfficerSMSStatusRepository;
	
	@Autowired
	@Qualifier("casbSMSSentRepository")
	private gov.cdac.casbRepository.CasbSMSSentRepository casbSMSSentRepository;
	
	@Autowired
	@Qualifier("casbSMSScheduleRepository")
	private gov.cdac.casbRepository.CasbSMSScheduleRepository casbSMSScheduleRepository;
	
	@Autowired
	@Qualifier("casbSMSStatusRepository")
	private gov.cdac.casbRepository.SMSStatusRepository casbSMSStatusRepository;
	
	@Autowired
	private SMSServiceFactory smsServiceFactory;
	
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
    
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructSMSAfcat() {
       	List<gov.cdac.afcatPojo.AfcatSMSSent> list = afcatSMSSentRepository.getScheduleSMSSentList();
    	for(gov.cdac.afcatPojo.AfcatSMSSent afcatSMSSent : list) {
    		Boolean flag = false;
    		List<gov.cdac.afcatPojo.AfcatSMSScheduleDetail> afcatSMSScheduleDetailList = afcatSMSScheduleRepository.findByAfcatSMSSent(afcatSMSSent.getSMSSentId());
    		Boolean completedFlag = true;
    		for(gov.cdac.afcatPojo.AfcatSMSScheduleDetail assd  : afcatSMSScheduleDetailList) {
    			if(flag == false  && (afcatSMSSent.getSmsSentType() == 1 || afcatSMSSent.getSmsSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(assd.getAfcatSmsScheduleStatus().getStatusId() == 3L  && 
	    					((assd.getSmsScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				assd.setAfcatSmsScheduleStatus(afcatSMSStatusRepository.findById(4L).get());
	    				afcatSMSScheduleRepository.save(assd);
	    				smsServiceFactory.sendSMSFactory(assd.getSmsScheduleDetailId(), afcatSMSSent.getReqType());
	    			}
    			}
    			
    			if(assd.getAfcatSmsScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			afcatSMSSent.setSmsEndDatetime(new Timestamp(System.currentTimeMillis()));
    			afcatSMSSent.setSmsSentStatus(true);
    			afcatSMSSentRepository.save(afcatSMSSent);
    		}
    	}
    }
    
    @Scheduled(fixedDelay = 3000)
    @Async
    protected void postConstructSMSIcg() {
       	List<gov.cdac.icgPojo.SMSSent> list = icgSMSSentRepository.getScheduleSMSSentList();
    	for(gov.cdac.icgPojo.SMSSent icgSMSSent : list) {
    		Boolean flag = false;
    		List<gov.cdac.icgPojo.SMSScheduleDetail> icgSMSScheduleDetailList = icgSMSScheduleRepository.findByIcgSMSSent(icgSMSSent.getSMSSentId());
    		Boolean completedFlag = true;
    		for(gov.cdac.icgPojo.SMSScheduleDetail assd  : icgSMSScheduleDetailList) {
    			if(flag == false  && (icgSMSSent.getSmsSentType() == 1 || icgSMSSent.getSmsSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(assd.getSmsScheduleStatus().getStatusId() == 3L  && 
	    					((assd.getSmsScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				assd.setSmsScheduleStatus(icgSMSStatusRepository.findById(4L).get());
	    				icgSMSScheduleRepository.save(assd);
	    				smsServiceFactory.sendSMSFactory(assd.getSmsScheduleDetailId(), icgSMSSent.getReqType());
	    			}
    			}
    			
    			if(assd.getSmsScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			icgSMSSent.setSmsEndDatetime(new Timestamp(System.currentTimeMillis()));
    			icgSMSSent.setSmsSentStatus(true);
    			icgSMSSentRepository.save(icgSMSSent);
    		}
    	}
    }
    
    
    @Scheduled(fixedDelay = 5000)
    @Async
    protected void postConstructSMSIcgOfficer() {
       	List<gov.cdac.icgOfficerPojo.IcgOfficerSMSSent> list = icgOfficerSMSSentRepository.getScheduleSMSSentList();
    	for(gov.cdac.icgOfficerPojo.IcgOfficerSMSSent icgSMSSent : list) {
    		Boolean flag = false;
    		List<gov.cdac.icgOfficerPojo.IcgOfficerSMSScheduleDetail> icgSMSScheduleDetailList = icgOfficerSMSScheduleRepository.findByIcgOfficerSMSSent(icgSMSSent.getSMSSentId());
    		Boolean completedFlag = true;
    		for(gov.cdac.icgOfficerPojo.IcgOfficerSMSScheduleDetail assd  : icgSMSScheduleDetailList) {
    			if(flag == false  && (icgSMSSent.getSmsSentType() == 1 || icgSMSSent.getSmsSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(assd.getSmsScheduleStatus().getStatusId() == 3L  && 
	    					((assd.getSmsScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				assd.setSmsScheduleStatus(icgOfficerSMSStatusRepository.findById(4L).get());
	    				icgOfficerSMSScheduleRepository.save(assd);
	    				smsServiceFactory.sendSMSFactory(assd.getSmsScheduleDetailId(), icgSMSSent.getReqType());
	    			}
    			}
    			
    			if(assd.getSmsScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			icgSMSSent.setSmsEndDatetime(new Timestamp(System.currentTimeMillis()));
    			icgSMSSent.setSmsSentStatus(true);
    			icgOfficerSMSSentRepository.save(icgSMSSent);
    		}
    	}
    }
    
    
    @Scheduled(fixedDelay = 5000)
    @Async
    protected void postConstructSMSCasb() {
       	List<gov.cdac.casbPojo.CasbSMSSent> list = casbSMSSentRepository.getScheduleSMSSentList();
    	for(gov.cdac.casbPojo.CasbSMSSent casbSMSSent : list) {
    		Boolean flag = false;
    		List<gov.cdac.casbPojo.CasbSMSScheduleDetail> casbSMSScheduleDetailList = casbSMSScheduleRepository.findByCasbSMSSent(casbSMSSent.getSMSSentId());
    		Boolean completedFlag = true;
    		for(gov.cdac.casbPojo.CasbSMSScheduleDetail assd  : casbSMSScheduleDetailList) {
    			if(flag == false  && (casbSMSSent.getSmsSentType() == 1 || casbSMSSent.getSmsSentType() == 2)) {
    				flag = true;
    			}else {
	    			if(assd.getCasbSmsScheduleStatus().getStatusId() == 3L  && 
	    					((assd.getSmsScheduleStartDate()).compareTo(new Timestamp(System.currentTimeMillis()))) <= 0) {
	    				assd.setCasbSmsScheduleStatus(casbSMSStatusRepository.findById(4L).get());
	    				casbSMSScheduleRepository.save(assd);
	    				smsServiceFactory.sendSMSFactory(assd.getSmsScheduleDetailId(),casbSMSSent.getReqType());
	    			}
    			}
    			
    			if(assd.getCasbSmsScheduleStatus().getStatusId() != 1L) {
    				completedFlag = false;
    			}
    		}
    		
    		if(completedFlag) {
    			casbSMSSent.setSmsEndDatetime(new Timestamp(System.currentTimeMillis()));
    			casbSMSSent.setSmsSentStatus(true);
    			casbSMSSentRepository.save(casbSMSSent);
    		}
    	}
    } 
    
}
