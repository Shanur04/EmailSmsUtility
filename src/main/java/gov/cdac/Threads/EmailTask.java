package gov.cdac.Threads;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import gov.cdac.services.MailServiceImpl;

public class EmailTask implements Runnable {

	private static final Logger centerWiseSendEmail = Logger.getLogger("CenterWiseSendEmail");

	private String mailServerHost = null;
	private String mailServerPort = null;
	private Boolean starttls = false;
	private String socketFactoryPort = null;
	private String mailUserName = null;
	private String mailpassword = null;
	private String emailSubject = null;
	private String emailContent = null;
	private ArrayList<File> fileArray = new ArrayList<File>();
	private String emailAttachmentDirFromPropFile = null;
	private String emailReason = null;
	private Set<String> mailNotSentSet = new HashSet<>();
	private Set<String> mailSentSet = new HashSet<>();
	private List<String> chunkSubset = new ArrayList<String>();

	private final AtomicInteger emailCounter;
	private final CustomBarrier barrier;
	private final CountDownLatch latch;
	private final AtomicInteger activeThreads;
	
	private static final AtomicInteger emailCounterTotal = new AtomicInteger(0);

	public EmailTask(String mailServerHost, String mailServerPort, Boolean starttls, String socketFactoryPort,
			String mailUserName, String mailpassword, String emailSubject, String emailContent,
			List<String> chunkSubset, List<File> fileArray, String emailAttachmentDirFromPropFile, String emailReason,
			AtomicInteger emailCounter, CustomBarrier barrier, CountDownLatch latch, AtomicInteger activeThreads) {
		super();

		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.starttls = starttls;
		this.socketFactoryPort = socketFactoryPort;
		this.mailUserName = mailUserName;
		this.mailpassword = mailpassword;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
		this.chunkSubset.addAll(chunkSubset);
		this.fileArray.addAll(fileArray);
		this.emailAttachmentDirFromPropFile = emailAttachmentDirFromPropFile;
		this.emailReason = emailReason;
		this.emailCounter = emailCounter;
		this.barrier = barrier;
		this.latch = latch;
		this.activeThreads = activeThreads;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		centerWiseSendEmail.info("Inside run of " + threadName + " : Count : " + chunkSubset.size());
		int mailNotSentCounter = 0;

		long taskStartTime = System.currentTimeMillis();
		int emailCount = 0;
		try {
			for (String emailId : chunkSubset) {
//			if (!MailServiceImpl.sendMailCenterWise(mailServerHost, mailServerPort, starttls, socketFactoryPort,
//					mailUserName, mailpassword, emailId, emailSubject, emailContent, fileArray, true,
//					emailAttachmentDirFromPropFile, null)) {
//				mailNotSentSet.add(emailId);
//				mailNotSentCounter++;
//			} else {
//				mailSentSet.add(emailId);
//			}
				
				for (int j = 0; j < 1000000; j++) {
					System.out.print("");
				}

				int count = emailCounter.incrementAndGet();
				int totalCount = emailCounterTotal.incrementAndGet();
				System.out.print(threadName + " : emailCount : " + (++emailCount) + "\n");

				if (emailCount % 20 == 0) {
					System.out.print(threadName + " : Send : " + count + "\n");
					System.err.println("\nbarrier Wait : " + threadName);
					if (activeThreads.get() > 1) {
                        barrier.await();
                    }
				}
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			Thread.currentThread().interrupt();
			System.err.println("Thread interrupted: " + e.getMessage());
		} finally {
			activeThreads.decrementAndGet();
            barrier.adjustParties(activeThreads.get());
            latch.countDown(); // Decrement the latch count when the task is done
            
			System.err.println("\nTask executed by " + Thread.currentThread().getName() +" and active thread count decremented : "+activeThreads.get());

		}
		
		long taskEndTime = System.currentTimeMillis();
		System.err.println("\nTask executed by " + Thread.currentThread().getName() + " took "
				+ (taskEndTime - taskStartTime) + " milliseconds");

		centerWiseSendEmail.info("Email not send : ");
		mailNotSentSet.stream().forEach(System.out::println);

	}

}
