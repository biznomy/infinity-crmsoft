package com.ednomy.crm.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ednomy.crm.commons.commu.EmailMessage;
import com.ednomy.crm.commons.commu.SMSMessage;
import com.ednomy.crm.commons.constants.EdnomyConstants;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdMessageManager;
import com.ednomy.crm.service.EdMessageManagerService;
import com.ednomy.crm.service.TypeMessageService;
import com.ednomy.crm.util.ApplicationProperties;
import com.ednomy.crm.util.UtilityClass;

@Service
public class TypeMessageServiceImpl implements TypeMessageService {
	
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Autowired
	UtilityClass utilityClass;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	EdMessageManagerService edMessageManagerService; 

	@Async
	@Override
	public void sendMail(EmailMessage emailMessage) {
		
		MimeMessage message = mailSender.createMimeMessage();
		try {

			message.setSubject(emailMessage.getSubject());
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailMessage.getSender(), emailMessage.getSenderName());
			helper.setTo(emailMessage.getReceiver());
			helper.setText(emailMessage.getBody(), true);
			if (emailMessage.getMultipartFile() != null && emailMessage.getMultipartFile().getSize() > 10) {
				helper.addAttachment(emailMessage.getMultipartFile().getOriginalFilename(), 
						(InputStreamSource) new ByteArrayInputStream(emailMessage.getMultipartFile().getBytes()));
			}
			mailSender.send(message);
			LOGGER.info("Mail Success,\n From "+emailMessage.getSender()+",\n TO :"+emailMessage.getReceiver()+",\n From Name :"+emailMessage.getSenderName()+",\n Subject :"+emailMessage.getSubject()+",\n Body :"+emailMessage.getBody());
			if (emailMessage.getId() != null && emailMessage.getId() != 0) {
				EdMessageManager edMessageManager = new EdMessageManager(); 
				edMessageManager.setDeliveryDate(new Date());
				edMessageManager.setDeliverStatus((long)EdnomyConstants.TRUE_INT);
				edMessageManager.setId(emailMessage.getId());
				edMessageManagerService.patch(edMessageManager);
			}

		} catch (Exception e) {
			
			if (emailMessage.getId() != null && emailMessage.getId() != 0) {
				EdMessageManager edMessageManager = new EdMessageManager(); 
				edMessageManager.setId(emailMessage.getId());
				edMessageManagerService.retry(edMessageManager);
			}
			
			LOGGER.info("Mail Send Error");
			LOGGER.error(e.getMessage(), e);
		}

	}

	@Async
	@Override
	public void sendSms(SMSMessage smsMessage) {
		try {
			smsMessage.setSender(smsMessage.getSender().replaceAll("\\s+",""));
			smsMessage.setReceiver(smsMessage.getReceiver().replaceAll("\\s+",""));		
			smsMessage.setContent(URLEncoder.encode(smsMessage.getContent(), "UTF-8"));
			
			String paramString = URLEncodedUtils.format(smsMessage.getNameValuePairs(), "utf-8");
			//String url = "http://bhashsms.com/api/sendmsg.php?user=nikhil.bvm&pass=t$123&sender=BZNOMY&phone="+smsMessage.getReceiver()+"&text="+smsMessage.getContent()+"&priority=ndnd&stype=normal";
			//LOGGER.info("URL : "+ url);
            String smsURL =applicationProperties.getSmsUrl()+"user="+applicationProperties.getSmsUserName()+"&pass="+applicationProperties.getSmsPass()+"&sender="+applicationProperties.getSmsSender()+"&phone="+smsMessage.getReceiver()+"&text="+smsMessage.getContent()+"&priority="+applicationProperties.getSmsPriority()+"&stype"+applicationProperties.getSmsStype();
			HttpClient client = HttpClientBuilder.create().build();
			//String url = smsMessage.getUrl() + paramString;
			LOGGER.info(smsURL);
			HttpGet request = new HttpGet(smsURL);
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			LOGGER.info(response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new EdnomyException(response.getStatusLine().getStatusCode()+""); 
			}
			
			
			if (smsMessage.getId() != null && smsMessage.getId() != 0 && response.getStatusLine().getStatusCode() == 200) {
				EdMessageManager edMessageManager = new EdMessageManager(); 
				edMessageManager.setDeliveryDate(new Date());
				edMessageManager.setDeliverStatus((long)EdnomyConstants.TRUE_INT);
				edMessageManager.setResponse(String.valueOf(response.getStatusLine().getStatusCode()));
				edMessageManager.setId(smsMessage.getId());
				edMessageManagerService.patch(edMessageManager);
			}

		} catch (Exception e) {
			if (smsMessage.getId() != null && smsMessage.getId() != 0) {
				EdMessageManager edMessageManager = new EdMessageManager(); 
				edMessageManager.setResponse(e.getMessage());
				edMessageManager.setId(smsMessage.getId());				
				edMessageManagerService.retry(edMessageManager);
			}
			LOGGER.error("Sending SMS Fail");
			LOGGER.error(e.getMessage(), e);

		}
	}

	

}
