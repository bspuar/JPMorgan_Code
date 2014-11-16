package org.jpmorgan.resource.msg.mngt;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.exception.TerminationException;
//import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageConsumer implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(GatewayImpl.class);
	private MessageProcessorImpl msgImp = null;
	
	public MessageConsumer() {
	}

	public MessageConsumer(MessageProcessorImpl msgImp) {
		this.msgImp = msgImp;
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			try {

				Object object = ((ObjectMessage) message).getObject();

				MessageInfo msg = (MessageInfo) object;

				msgImp.addMessage(msg);

				
			}catch(TerminationException ex){
				
				System.out.println("Exception");
				logger.error(ex.getErrorMessage());
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception");
				e.printStackTrace();
			}

		} else {

			System.out.println("String Message Received");
		}
	}
}