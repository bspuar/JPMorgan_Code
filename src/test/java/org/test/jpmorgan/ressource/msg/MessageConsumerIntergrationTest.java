package org.test.jpmorgan.ressource.msg;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.MessagePriority;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueue;

/**
 * @author bhupsing
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/JmsTest-appContext.xml" })
public class MessageConsumerIntergrationTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MessagePriority.class);
	 @Resource
	    private JmsTemplate jmsTemplate;

	    @Resource
	    private DestinationManager mockDestinationManager;

	    @Resource
	    private MessageInfo messageInfo;

	   
		@Before
	    public void init() {
	        assertNotNull(jmsTemplate);
	        assertNotNull(mockDestinationManager);
	        assertNotNull(messageInfo);
	    }

	    @Test
	    public void testMessageConsumer() throws Exception {
	        MockQueue mockQueue = mockDestinationManager.createQueue("test3");

			  jmsTemplate.send(
	        		new MessageCreator() {
	        	          public ObjectMessage  createMessage(Session session) throws JMSException {
	        	        	  ObjectMessage message = session.createObjectMessage();
	        	        	  MessageInfo msg1=new MessageInfo("1","1","group2","active");
	       	        	       message.setObject(msg1);       	              
	        	               return message;
	        	          }
	        }  );
	          
//			  ObjectMessage message= (ObjectMessage) jmsTemplate.receive(mockQueue);			
//
//				MessageInfo msg = (MessageInfo) message;
//				
//				assertNotNull("Not nul",msg);
//				
//				logger.debug(messageInfo.getGroupId());



	    }
}
