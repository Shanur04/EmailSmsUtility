package gov.cdac.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMailConfiguration {

	/*@Bean("alertnateMailServer")
	public JavaMailSenderImpl getAlertnateMailServer() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.cdac.in");
		sender.setPort(587);
		sender.setUsername("casb@cdac.in");
		sender.setPassword("casb123@@");

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		//props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		props.put("mail.smtp.socketFactory.port", "587");
		
		sender.setJavaMailProperties(props);

		return sender;
	}*/
	
	/**
	 * sets the settings required for sending mail
	 * @return JavaMailSenderImpl
	 */
	@Bean("cdac")
	public JavaMailSenderImpl getJavaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		//for local and staging
		sender.setHost("smtp.cdac.in");
		sender.setPort(587);
		
		//for production
		//sender.setHost("smtpb.pune.cdac.in");
		//sender.setPort(25);
		

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "false");
		
		//for production
		//props.put("mail.smtp.starttls.enable", "false");
		
		//for local and staging
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.debug", "false");
		
		//for local and staging
		props.put("mail.smtp.socketFactory.port", "587");
		
		//for production
		//props.put("mail.smtp.socketFactory.port", "25");
		
		sender.setJavaMailProperties(props);

		return sender;
	}
}

