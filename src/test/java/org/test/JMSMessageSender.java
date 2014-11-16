package org.test;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jpmorgan.resource.msg.MessageInfo;


public class JMSMessageSender {
	
	    private ConnectionFactory factory = null;
	    private Connection connection = null;
	    private Session session = null;
	    private Destination destination = null;
	    private MessageProducer producer = null;
	    private static String queue="Test3";
	
	    public JMSMessageSender() {
	
	    }
	
	    public void sendMessage() {
	
	        try {
	            factory = new ActiveMQConnectionFactory(
	                    ActiveMQConnection.DEFAULT_BROKER_URL);
	            connection = factory.createConnection();
	            connection.start();
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            destination = session.createQueue(queue);
	            producer = session.createProducer(destination);
	            
	            for(int i=1;i<=10;i++){
	            	
	            	  Random random = new Random();
	            MessageInfo msg=new MessageInfo("hi","1","group"+random.nextInt(5),"active");
	            //MessageInfo msg=new MessageInfo("hi","1","group3","active");
	            ObjectMessage message = session.createObjectMessage( msg );
	           
	            this.producer.send(message);
	            
	            System.out.println("Message sent : "+i);
	            }

	            
	            System.exit(0);
	
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }
	
	    public static void main(String[] args) {
		JMSMessageSender sender = new JMSMessageSender();
	        sender.sendMessage();
	    }
	
	}


