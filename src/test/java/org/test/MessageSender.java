package org.test;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.MessageConsumer;
import org.jpmorgan.resource.msg.mngt.MessageProcessorImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
public class MessageSender {
	public static void main(String[] args) {
		  ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		  
		 // MessageConsumer consumer=(MessageConsumer)context.getBean("messageConsumer");
		  
//	  JmsTemplate jmsTemplate=(JmsTemplate) context.getBean("jmsTemplate");
//		  jmsTemplate.send(
//        		new MessageCreator() {
//        	          public ObjectMessage  createMessage(Session session) throws JMSException {
//        	        	  ObjectMessage message = session.createObjectMessage();
//        	        	  MessageInfo msg1=new MessageInfo("1","1","group2","active");
//       	        	     message.setObject(msg1);       	              
//        	               return message;
//        	          }
//        }  );
//      
		 //System.out.println("MESSAGE SENT TO myMessageQueue");
	}
}