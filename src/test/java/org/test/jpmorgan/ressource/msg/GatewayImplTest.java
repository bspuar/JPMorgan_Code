package org.test.jpmorgan.ressource.msg;

import static org.junit.Assert.*;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.MessageProcessorImpl;
import org.jpmorgan.resource.target.TargetConsumer;
import org.junit.Test;

public class GatewayImplTest {

	

	@Test
	public final void testSend() {

    MessageInfo msg=new MessageInfo("Hello", "1", "groupId", "active");
    MessageProcessorImpl msgImpl=new MessageProcessorImpl();
    
    assertNotNull("Not null",new Thread(new TargetConsumer(msg,msgImpl)));
	}

}
