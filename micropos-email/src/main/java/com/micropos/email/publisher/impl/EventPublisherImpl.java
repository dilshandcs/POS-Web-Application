package com.micropos.email.publisher.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.micropos.email.dto.EmailMetaData;
import com.micropos.email.publisher.EventPublisher;

@Component
public class EventPublisherImpl implements EventPublisher {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(EventPublisherImpl.class);

	@Autowired
	private final JmsTemplate jmsTemplate = null;

	public void proceedEmailEvent(final EmailMetaData emailMetaData) {
		logger.info(String.format(
				"proceedEmailEvent -> get called with emailMetaData : Subject[%s], ToEmails[%s], VmFile[%s]",
				emailMetaData.getSubject(), emailMetaData.getToEmailAddresses(), emailMetaData.getVmFile()));

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {

				ObjectMessage objMessage = session.createObjectMessage();
				objMessage.setObject(emailMetaData);

				return objMessage;
			}
		});
	}
}
