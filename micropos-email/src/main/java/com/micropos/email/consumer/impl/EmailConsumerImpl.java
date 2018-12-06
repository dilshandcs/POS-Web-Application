package com.micropos.email.consumer.impl;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micropos.email.dto.EmailMetaData;
import com.micropos.email.sender.EmailSender;

@Component
public class EmailConsumerImpl implements MessageListener {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(EmailConsumerImpl.class);

	@Autowired
	private EmailSender emailSender;


	public void onMessage(final Message message) {

		logger.info("onMessage -> Get Called");

		try {
			if (message instanceof ObjectMessage) {

				EmailMetaData emailMetaData = (EmailMetaData) ((ObjectMessage) message).getObject();

				logger.info(
						String.format("onMessage -> Casted the message Successfully... [%s]", emailMetaData));
				if(emailMetaData.isWithAttachment()){
					emailSender.sendEmailWithAttachment(emailMetaData, emailMetaData.getFileName(),emailMetaData.getOutput());
				}else{
					emailSender.sendEmail(emailMetaData);
				}


			} else {
				logger.error("onMessage -> Received message of unknown type");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

}
