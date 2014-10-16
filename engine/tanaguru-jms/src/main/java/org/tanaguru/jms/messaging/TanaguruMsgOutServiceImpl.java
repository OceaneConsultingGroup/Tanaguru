package org.tanaguru.jms.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.opens.tanaguru.messagin.TanaguruMsgOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author OCEANE
 * 
 */
@Component
public class TanaguruMsgOutServiceImpl implements TanaguruMsgOutService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(TanaguruMsgOutServiceImpl.class);

	private JmsTemplate jmsTemplate;

	@Value("${jdbc.username}")
	private String userName;

	@Value("${jdbc.password}")
	private String pass;

	@Value("${jdbc.url}")
	private String databaseUrl;

	@Override
	public boolean send(final String urlSite) {

		try {
			jmsTemplate.send(new MessageCreator() {
				public Message createMessage(final Session session)
						throws JMSException {

					String messageToSend = getDataBaseName(databaseUrl) + ";"
							+ getDataBasePort(databaseUrl) + ";" + userName
							+ ";" + pass + ";" + getSchemaName(databaseUrl)
							+ ";" + urlSite;
					System.out.println(messageToSend);
					return session.createTextMessage(messageToSend);
				}
			});
		} catch (JmsException e) {
			LOGGER.error("Error during sending message to tanaguruQueueOut ", e);
			return false;
		}
		return true;
	}

	@Override
	public void sendAndSave(String urlPage, String fileName, String id) {
		// TODO Auto-generated method stub
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	private String getDataBaseName(String url) {
		String result = null;
		String[] split = url.split("//");
		result = split[1].substring(0, split[1].indexOf(":"));
		return result;
	}

	private String getDataBasePort(String url) {
		String result = null;
		String[] split = url.split(":");
		result = split[3].substring(0, split[3].indexOf("/"));
		return result;
	}

	private String getSchemaName(String url) {
		String result = null;
		String[] split = url.split("/");
		result = split[3];
		return result;
	}

}
