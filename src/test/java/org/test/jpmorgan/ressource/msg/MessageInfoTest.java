package org.test.jpmorgan.ressource.msg;

import static org.junit.Assert.*;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.GatewayImpl;
import org.junit.runner.RunWith;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:/MessageInfoTest-context.xml")
public class MessageInfoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayImpl.class);	
	
	@Autowired
    private MessageInfo message = null;

    /**
     * Tests message.
     */
    @Test
    public void testMessage() {   
        assertNotNull("Constructor message instance is null.", message);
        
        String msg = message.getDetails();
        String messageId=message.getMessageId();
        String groupId=message.getGroupId();
        String status=message.getStatus();
        
        assertNotNull("Message is null.", msg);
        assertNotNull("Message is null.", messageId);
        assertNotNull("Message is null.", groupId);
        assertNotNull("Message is null.", status);
        
        String expectedMessage = "First Message";
        
        assertEquals("Message should be '" + expectedMessage + "'.", expectedMessage, msg);
        assertEquals("10001", messageId);
        assertEquals("group1", groupId);
        assertEquals("active", status);

        logger.info("message='{}'", msg);
    }

}
