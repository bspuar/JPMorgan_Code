/**
 * 
 */
package org.jpmorgan.resource.msg.mngt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jpmorgan.resource.msg.Gateway;
import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.target.TargetConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * @author bhupsing
 *
 */
public class GatewayImpl implements Gateway {
	private static final Logger logger = LoggerFactory.getLogger(GatewayImpl.class);	
	
	private ExecutorService executor = Executors.newFixedThreadPool(MessageUtil.RESOURCES);
	
	private MessageProcessorImpl mesgImpl=null;
	
	 public GatewayImpl() {
		
	}
	/*
	 * This method invokes target consumer each time if there is a message. Maximum resources available depends upon Executor Service's
	 *  configuration.
	 * @see org.jpmorgan.resource.msg.Gateway#send(org.jpmorgan.resource.msg.MessageInfo)
	 */
	@Override
	public void send(MessageInfo msg) {
		
		mesgImpl=new MessageProcessorImpl();
		Runnable worker = new TargetConsumer(msg,mesgImpl);
        executor.execute(worker);
	}

}
