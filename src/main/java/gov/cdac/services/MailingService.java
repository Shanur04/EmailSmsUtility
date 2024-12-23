package gov.cdac.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

public class MailingService {
	
	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");
	private static final Logger centerWiseMailSentEmailLogger = Logger.getLogger("CenterWiseMailSentEmailIds");
	private static final Logger CenterWiseEmailMessageIdLogger = Logger.getLogger("CenterWiseEmailMessageIDs");
	

	
	
	public static boolean sendMailCenterWise(final String mailServerHost, final String mailServerPort, final Boolean starttls, final String socketFactoryPort, final String from, final String password, final String to, final String subject, String message, ArrayList<File> FileList, boolean mailShouldBeSend, String tempFileDirFromPropertyFile,String admitCardFilePath) {
		
		
		
		
//		System.out.println("in send service");
		
		centerWiseSendEmail.info("inside sendMailCenterWise of MailingService");
		boolean booleanToReturn = false;
		

		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		try {
			
			
			sender.setHost(mailServerHost);
			sender.setPort(Integer.valueOf(mailServerPort));
			
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "false");
			
		
			
			
			props.put("mail.smtp.starttls.enable", starttls);
			
			props.put("mail.debug", "false");
			
			
			props.put("mail.smtp.socketFactory.port", socketFactoryPort);
			
			
			
			sender.setJavaMailProperties(props);
			//comment when production
			sender.setUsername(from);
			//comment when production
			sender.setPassword(password);
		} catch (BeansException e) {
			centerWiseSendEmail.error("inside BeansException Catch block of sendMail of MailingService");
			e.printStackTrace();
			
		}
		
		
		
		Properties properties = System.getProperties();

		Session session = Session.getDefaultInstance(properties);
		  
		  
		MimeMessage mimeMessage = null;
		
        
		try {
			
			mimeMessage = new MimeMessage(session) {
	            protected void updateMessageID() throws MessagingException {
	                if (getHeader("Message-ID") == null)
	                    super.updateMessageID();
	            }
	        };
	        
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
			//comment when production
			mimeMessageHelper.setFrom(from);
			//comment when local
//			mimeMessageHelper.setFrom("icg@cdac.in");
			mimeMessageHelper.setTo(to);
			centerWiseSendEmail.info("Sending Mail to : " + to);
			mimeMessageHelper.setText(message, message);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.getMimeMessage().saveChanges();
			centerWiseSendEmail.info("mimeMessageHelper.getMimeMessage().getMessageID() : " + mimeMessageHelper.getMimeMessage().getMessageID());
			CenterWiseEmailMessageIdLogger.info(to + " - " + mimeMessageHelper.getMimeMessage().getMessageID());
			
			
			if(FileList.size()>0)
			{
				for (File currentFile : FileList) {
					centerWiseSendEmail.info("Attachment File path : " +tempFileDirFromPropertyFile+currentFile.getName());
					FileSystemResource file = new FileSystemResource(tempFileDirFromPropertyFile+currentFile.getName());
					mimeMessageHelper.addAttachment(file.getFilename(), file);
				}
			}
			
			if(admitCardFilePath!=null)
			{
				centerWiseSendEmail.info("Admit card Path : " + admitCardFilePath);
				FileSystemResource file = new FileSystemResource(admitCardFilePath);
				mimeMessageHelper.addAttachment(file.getFilename(), file);
			}
			
//			System.out.println("after file list");
			
			
		} catch (MessagingException e) {
			centerWiseSendEmail.error("inside MessagingException Catch block of sendMail of MailingService");
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			centerWiseSendEmail.error("inside IllegalArgumentException Catch block of sendMail of MailingService");
			e.printStackTrace();
		}
		catch (Exception e) {
			centerWiseSendEmail.error("inside Exception Catch block of sendMail of MailingService");
			e.printStackTrace();
		}
		
		try {
			if(mailShouldBeSend)
			{
//				System.out.println("in send");
				sender.send(mimeMessage);
				centerWiseSendEmail.info("Mail Sent to : " + to);
				centerWiseMailSentEmailLogger.info(to);
				booleanToReturn =  true;
			}
		}
		catch (MailException e) {
			centerWiseSendEmail.error("inside MailException Catch block of sendMail of MailingService");
			e.printStackTrace();
		}
		catch (Exception e) {
			centerWiseSendEmail.error("inside Exception Catch block of sendMail of MailingService");
			e.printStackTrace();
		}
		centerWiseSendEmail.info("booleanToReturn : " + booleanToReturn);
		return booleanToReturn;
	}
}

