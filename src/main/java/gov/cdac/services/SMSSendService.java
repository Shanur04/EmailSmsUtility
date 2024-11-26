package gov.cdac.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gov.cdac.afcatPojo.AfcatApplicantCredential;
import gov.cdac.afcatPojo.AfcatSMSNotSentDetails;
import gov.cdac.afcatPojo.AfcatSMSSent;
import gov.cdac.afcatRepository.SMSNotSentDetailsRepository;
import gov.cdac.casbPojo.CasbApplicantCredential;
import gov.cdac.casbPojo.CasbSMSNotSentDetails;
import gov.cdac.casbPojo.CasbSMSSent;
import gov.cdac.icgOfficerPojo.IcgOfficerSMSSent;
import gov.cdac.icgOfficerPojo.SmsNotSentDetail;
import gov.cdac.icgOfficerRepository.IcgOfficerSMSNotSentDetailsRepository;
import gov.cdac.icgPojo.ApplicantCredential;
import gov.cdac.icgPojo.SMSNotSentDetails;
import gov.cdac.icgPojo.SMSSent;
import jakarta.annotation.Resource;

/**
 * @author Mobile Seva < msdp@cdac.in >
 *         <p>
 *         Kindly add require Jar files to run Java client
 *         </p>
 *         <p>
 *         Apache commons-codec-1.9 <br>
 *         Apache commons-httpclient-3.1 <br>
 *         Apache commons-logging-1.2
 * @see <a href="https://mgov.gov.in/doc/RequiredJars.zip">Download required Jar
 *      files</a>
 */
@Service
public class SMSSendService {
    private final Logger log = LogManager.getLogger(SMSSendService.class);

    @Value("${sms.username:VMCDAC}")
    private String SMS_USERNAME;

    @Value("${sms.password:abcd1234!@#}")
    private String SMS_PASSWORD;

    @Value("${sms.senderid:FPCDAC}")
    private String SMS_SENDERID;

    @Value("${sms.secureKey:56f801cc-8ed0-4bd2-b2b5-f652dbc4a8e9}")
    private String SMS_SECURE_KEY;

    @Resource(name = "icgSMSNotSentDetailsRepository")
    private gov.cdac.icgRepositories.SMSNotSentDetailsRepository icgSMSNotSentDetailsRepository;

    @Resource(name = "afcatSMSNotSentDetailsRepository")
    private SMSNotSentDetailsRepository afcatSMSNotSentDetailsRepository;

    @Resource(name = "casbSMSNotSentDetailsRepository")
    private gov.cdac.casbRepository.SMSNotSentDetailsRepository casbSMSNotSentDetailsRepository;
    
    @Resource(name = "icgOfficerSMSNotSentDetailsRepository")
    private IcgOfficerSMSNotSentDetailsRepository icgOfficerSMSNotSentDetailsRepository;


    public SMSSendService() {
    }

    /**
     * Send Single text SMS for ICG
     * 
     * @param message      : Message e.g. 'Welcome to mobile Seva'
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */
    public String sendSingleSMS(ApplicantCredential appCred, SMSSent smsSent, String mobileNumber, String templateid) {

	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, smsSent.getMessage(), SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", smsSent.getMessage()));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line + "\t" + mobileNumber;
	    }

	    // error, should save asynchronously
	    if (!responseString.substring(0, 3).equals("402")) {
		ICGSMSService.STATUS = "SMS not sent to " + mobileNumber;
		icgSMSNotSentDetailsRepository
			.save(new SMSNotSentDetails(appCred, mobileNumber, null, smsSent, false, null));
	    } else {
		ICGSMSService.STATUS = "SMS sent to " + (++ICGSMSService.COUNT) + " candidates";
	    }

	    log.info(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    /**
     * Send Single text SMS for CASB
     * 
     * @param message      : Message e.g. 'Welcome to mobile Seva'
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */
    public String sendSingleSMS(CasbApplicantCredential appCred, CasbSMSSent smsSent, String mobileNumber,
	    String templateid) {

	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, smsSent.getMessage(), SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", smsSent.getMessage()));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line + "\t" + mobileNumber;
	    }

	    // error, should save asynchronously
	    if (!responseString.substring(0, 3).equals("402")) {
		CasbSMSService.STATUS = "SMS not sent to " + mobileNumber;
		casbSMSNotSentDetailsRepository
			.save(new CasbSMSNotSentDetails(appCred, mobileNumber, null, smsSent, false, null));
	    } else {
		CasbSMSService.STATUS = "SMS sent to " + (++CasbSMSService.COUNT) + " candidates";
	    }

	    log.info(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    /**
     * Send Single text SMS for AFCAT
     * 
     * @param message      : Message e.g. 'Welcome to mobile Seva'
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */
    public String sendSingleSMS(AfcatApplicantCredential appCred, AfcatSMSSent smsSent, String mobileNumber,
	    String templateid) {
	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, smsSent.getMessage(), SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", smsSent.getMessage()));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line + "\t" + mobileNumber;
	    }

	    // error, should save asynchronously
	    if (!responseString.substring(0, 3).equals("402")) {
	    	AfcatSMSService.STATUS = "SMS not sent to " + mobileNumber;
	    	afcatSMSNotSentDetailsRepository
				.save(new AfcatSMSNotSentDetails(appCred, mobileNumber, null, smsSent, false, null));
	    } else {
	    	AfcatSMSService.STATUS = "SMS sent to " + (++CasbSMSService.COUNT) + " candidates";
	    }

	    log.info(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    /**
     * Send Bulk text SMS
     * 
     * @param message      : Message e.g. 'Welcome to mobile Seva'
     * @param mobileNumber : Bulk Mobile Number with comma separated e.g.
     *                     '99XXXXXXX,99XXXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */
    public String sendBulkSMS(String message, String mobileNumber, String templateid) {
	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, message, SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("bulkmobno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", message));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "bulkmsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    
	    CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post);
	    
//	    HttpResponse response = client.execute(post);
	    
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line;
	    }
	    
	    bf.close();
	    log.info(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    /**
     * Send Unicode text SMS
     * 
     * @param message      : Unicode Message
     * @param mobileNumber : Bulk Mobile Number with comma separated e.g.
     *                     '99XXXXXXX,99XXXXXXXX'
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     * @param templateid : templateId unique for each template message content
     */
    public String sendUnicodeSMS(String message, String mobileNumber, String templateid) {

	String finalmessage = "";
	for (int i = 0; i < message.length(); i++) {

	    char ch = message.charAt(i);
	    int j = (int) ch;
	    String sss = "&#" + j + ";";
	    finalmessage = finalmessage + sss;
	}

	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, finalmessage, SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("bulkmobno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", finalmessage));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "unicodemsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line + " for " + mobileNumber;

	    }
	    log.info(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    /**
     * Send Single OTP text SMS
     * </namevaluepair></namevaluepair></namevaluepair></namevaluepair></namevaluepair></namevaluepair>
     * </p>
     * <p>
     * Use only in case of OTP related message
     * </p>
     * <p>
     * Messages other than OTP will not be delivered to the users
     * 
     * @param message      : Message e.g. 'Welcome to mobile Seva'
     * @param mobileNumber : Single Mobile Number e.g. '99XXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */

    public String sendOtpSMS(String message, String mobileNumber, String templateid) {
	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, message, SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", message));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "otpmsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line;

	    }
	    System.out.println(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;

    }

    /**
     * Send Single Unicode OTP text SMS
     * 
     * @param message      : Unicode Message
     * @param mobileNumber : Bulk Mobile Number with comma separated e.g.
     *                     '99XXXXXXX,99XXXXXXXX'
     * @param templateid   : templateId unique for each template message content
     * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID =
     *         150620161466003974245msdgsms'
     * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code
     *      details</a>
     * 
     */

    public String sendUnicodeOtpSMS(String message, String mobileNumber, String templateid) {
	String finalmessage = "";
	for (int i = 0; i < message.length(); i++) {

	    char ch = message.charAt(i);
	    int j = (int) ch;
	    String sss = "&#" + j + ";";
	    finalmessage = finalmessage + sss;
	}

	String responseString = "";
	SSLSocketFactory sf = null;
	SSLContext context = null;
	String encryptedPassword;
	try {
	    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
	    // 6
	    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	    context.init(null, null, null);
	    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
	    Scheme scheme = new Scheme("https", 443, sf);
	    HttpClient client = new DefaultHttpClient();
	    client.getConnectionManager().getSchemeRegistry().register(scheme);
	    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
	    encryptedPassword = MD5(SMS_PASSWORD);
	    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, finalmessage, SMS_SECURE_KEY);
	    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
	    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
	    nameValuePairs.add(new BasicNameValuePair("content", finalmessage));
	    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "unicodeotpmsg"));
	    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
	    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    HttpResponse response = client.execute(post);
	    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String line = "";
	    while ((line = bf.readLine()) != null) {
		responseString = responseString + line;

	    }
	    System.out.println(responseString);
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return responseString;
    }

    protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
	// TODO Auto-generated method stub
	StringBuffer finalString = new StringBuffer();
	finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
	// logger.info("Parameters for SHA-512 : "+finalString);
	String hashGen = finalString.toString();
	StringBuffer sb = null;
	MessageDigest md;
	try {
	    md = MessageDigest.getInstance("SHA-512");
	    md.update(hashGen.getBytes());
	    byte byteData[] = md.digest();
	    // convert the byte to hex format method 1
	    sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	    }

	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
	return sb.toString();
    }

    /**
     * Get units of the unicode message
     * 
     * @param message
     * @return int message unit
     **/
    public int getUnicodeTextMessageUnit(String message) {
	String charInUnicode = "";
	int msgUnit = 1;
	int msgLen = 0;
	String unicodeMessgae = "";
	String finalmessage = null;
	for (int i = 0; i < message.length(); i++) {

	    char ch = message.charAt(i);
	    int j = (int) ch;
	    String sss = "&#" + j + ";";
	    finalmessage = finalmessage + sss;
	}
	StringTokenizer st = new StringTokenizer(finalmessage, " ");
	while (st.hasMoreElements()) {
	    String str1 = (String) st.nextElement();
	    StringTokenizer dd = new StringTokenizer(str1, ";");

	    while (dd.hasMoreElements()) {
		charInUnicode = (String) dd.nextElement();
		if (charInUnicode.startsWith("&#")) {
		    StringTokenizer df = new StringTokenizer(charInUnicode, "&#");
		    while (df.hasMoreElements()) {
			String kk = (String) df.nextElement();
			unicodeMessgae = unicodeMessgae + "," + kk;
			msgLen = msgLen + 1;
		    }

		} else {
		    if (charInUnicode.contains("&#")) {
			StringTokenizer st1 = new StringTokenizer(charInUnicode, "&#");
			while (st1.hasMoreElements()) {
			    String kk = (String) st1.nextElement();
			    for (int i1 = 0; i1 < kk.length(); ++i1) {
				char c = kk.charAt(i1);
				int j = (int) c;
				unicodeMessgae = unicodeMessgae + "," + j;
				msgLen = msgLen + 1;
			    }
			    String uni = st1.nextToken();
			    unicodeMessgae = unicodeMessgae + "," + uni;
			    msgLen = msgLen + 1;
			}
		    }

		    else {
			for (int i1 = 0; i1 < charInUnicode.length(); ++i1) {
			    char c = charInUnicode.charAt(i1);
			    int j = (int) c;
			    unicodeMessgae = unicodeMessgae + "," + j;
			    msgLen = msgLen + 1;
			}
		    }
		}

	    }
	    unicodeMessgae = unicodeMessgae + " ";
	}

	if (msgLen > 70) {

	    msgUnit = 2;

	    if (msgLen > 134) {
		msgUnit = 3;

		if (msgLen > 201) {
		    msgUnit = 4;
		    if (msgLen > 268) {
			msgUnit = 5;
			if (msgLen > 335) {
			    msgUnit = 6;
			    if (msgLen > 402) {
				msgUnit = 7;
				if (msgLen > 469) {
				    msgUnit = 8;
				    if (msgLen > 536) {
					msgUnit = 9;
					if (msgLen > 603) {
					    msgUnit = 10;

					}
				    }
				}
			    }
			}
		    }
		}
	    }

	} else {
	    msgUnit = 1;
	}
	return msgUnit;
    }

    /**
     * Get units of the text message
     * 
     * @param message e.g. 'Welcome to Mobile Seva'
     * @return int message unit
     **/
    public int getNormalTextMessageUnit(String message) {

	int msgUnit = 1;
	if (message.length() > 160) {
	    msgUnit = 2;
	    if (message.length() > 306) {
		msgUnit = 3;
	    }
	    if (message.length() > 459) {
		msgUnit = 4;
	    }
	    if (message.length() > 612) {
		msgUnit = 5;
	    }
	    if (message.length() > 765) {
		msgUnit = 6;
	    }
	    if (message.length() > 918) {
		msgUnit = 7;
	    }
	    if (message.length() > 1071) {
		msgUnit = 8;
	    }
	    if (message.length() > 1224) {
		msgUnit = 9;
	    }
	    if (message.length() > 1377) {
		msgUnit = 10;
	    }

	} else {
	    msgUnit = 1;
	}
	return msgUnit;
    }

    /****
     * Method to convert Normal Plain Text Password to MD5 encrypted password
     ***/
    private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	MessageDigest md;
	md = MessageDigest.getInstance("SHA-1");
	byte[] md5 = new byte[64];
	md.update(text.getBytes("iso-8859-1"), 0, text.length());
	md5 = md.digest();
	return convertedToHex(md5);
    }

    private static String convertedToHex(byte[] data) {
	StringBuffer buf = new StringBuffer();

	for (int i = 0; i < data.length; i++) {
	    int halfOfByte = (data[i] >>> 4) & 0x0F;
	    int twoHalfBytes = 0;

	    do {
		if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
		    buf.append((char) ('0' + halfOfByte));
		}

		else {
		    buf.append((char) ('a' + (halfOfByte - 10)));
		}

		halfOfByte = data[i] & 0x0F;

	    } while (twoHalfBytes++ < 1);
	}
	return buf.toString();
    }

	public String sendSingleSMS(gov.cdac.icgOfficerPojo.ApplicantCredential appCred, IcgOfficerSMSSent smsSent, String mobileNumber, String templateid) {
		String responseString = "";
		SSLSocketFactory sf = null;
		SSLContext context = null;
		String encryptedPassword;
		try {
		    // context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version
		    // 6
		    context = SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
		    context.init(null, null, null);
		    sf = new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
		    Scheme scheme = new Scheme("https", 443, sf);
		    HttpClient client = new DefaultHttpClient();
		    client.getConnectionManager().getSchemeRegistry().register(scheme);
		    HttpPost post = new HttpPost("https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT");
		    encryptedPassword = MD5(SMS_PASSWORD);
		    String genratedhashKey = hashGenerator(SMS_USERNAME, SMS_SENDERID, smsSent.getMessage(), SMS_SECURE_KEY);
		    List<NameValuePair> nameValuePairs = new ArrayList<>(1);
		    nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
		    nameValuePairs.add(new BasicNameValuePair("senderid", SMS_SENDERID));
		    nameValuePairs.add(new BasicNameValuePair("content", smsSent.getMessage()));
		    nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
		    nameValuePairs.add(new BasicNameValuePair("username", SMS_USERNAME));
		    nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
		    nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
		    nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
		    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    HttpResponse response = client.execute(post);
		    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    String line = "";
		    while ((line = bf.readLine()) != null) {
			responseString = responseString + line + "\t" + mobileNumber;
		    }

		    // error, should save asynchronously
		    if (!responseString.substring(0, 3).equals("402")) {
			ICGOfficerSMSService.STATUS = "SMS not sent to " + mobileNumber;
			icgOfficerSMSNotSentDetailsRepository
				.save(new SmsNotSentDetail(appCred, mobileNumber, null, smsSent, false, null));
		    } else {
			ICGSMSService.STATUS = "SMS sent to " + (++ICGSMSService.COUNT) + " candidates";
		    }

		    log.info(responseString);
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} catch (KeyManagementException e) {
		    e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		} catch (ClientProtocolException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return responseString;
	   }
	}
   
 