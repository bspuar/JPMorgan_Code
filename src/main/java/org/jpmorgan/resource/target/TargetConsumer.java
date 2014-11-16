/**
 * 
 */
package org.jpmorgan.resource.target;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.GatewayImpl;
import org.jpmorgan.resource.msg.mngt.MessageProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @author bhupsing
 *
 *This is target service class which consume messages all the messages send by gateway interface.
 */
public class TargetConsumer implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(TargetConsumer.class);
	
	MessageProcessorImpl messageImpl=null;
	MessageInfo msg=null;
	

	public TargetConsumer(MessageInfo msg,	MessageProcessorImpl messageImpl){
		
		this.msg=msg;
		this.messageImpl=messageImpl;
		
	}


	/*
	 * This is method consume all the messages and  update the resource list when work is done.
	 * @see java.lang.Runnable#run()
	 */
	
	@Override
	public void run() {
		
		logger.info(Thread.currentThread().getName()+"::"+"Target Resource:"+msg.getGroupId()+":: Start");
          
           //Thread wait for 10 seconds then continue
            try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            logger.info(Thread.currentThread().getName() + " ::End.");
            
            //Update available resource list 
            messageImpl.completed();
	}

}
