/**
 * 
 */
package org.jpmorgan.resource.msg.mngt;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.MessageProcessor;
import org.jpmorgan.resource.msg.exception.TerminationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bhupsing
 *
 *This class implements MessageProcessor interface. It continuous  poll for free resources and new messages.
 */
public class MessageProcessorImpl implements MessageProcessor, Runnable {

	private static final Logger logger = LoggerFactory
			.getLogger(MessageProcessorImpl.class);

	public static transient int resource = MessageUtil.RESOURCES;
	private static HashMap<String, ArrayList<MessageInfo>> messageList = new HashMap<String, ArrayList<MessageInfo>>();
	private static HashMap<String, String> messageTerminationList = new HashMap<String, String>();
	private static String previousProcesseGroup = "";
	private GatewayImpl gateway = new GatewayImpl();
	private MessagePriority priority = new MessagePriority();

	public MessageProcessorImpl(){}
	

	/*
	 * 
	 * This method checks if there is any resource is free and message in the
	 * map then it will send message to to the gateway. it continuously check
	 * for free resource and new messages.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Thread Started");
		
		while (true) {
			logger.info("Before::Number of messages :" + messageList.size()
					+ " Number of resources available : " + resource);
			if (messageList.size() > 0 && resource > 0) {
              
				ArrayList<MessageInfo> messages = this.getMessageList(resource,
						messageList, previousProcesseGroup);
				
				logger.info("number of message need to send :"
						+ messages.size());
				
				
				for (int i = 0; i < messages.size(); i++) {
					
				     gateway.send(messages.get(i));
					 resource = resource - 1;
					 previousProcesseGroup = messages.get(i).getGroupId();
				}
				
				
				
				
				logger.info("After::Number of messages :" + messageList.size()
						+ " Number of resources available : " + resource);
			}else if(messageList.size()==0){
				
				 previousProcesseGroup="";
				
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}

	}

	/*
	 * This method prioritise the message list as per the message group.
	 * It returns the number of messages as per available resources.
	 * 
	 * @see org.jpmorgan.resource.msg.MessageProcessor#getMessageList(int,
	 * java.util.Map, java.lang.String)
	 */

	
	@Override
	public ArrayList<MessageInfo> getMessageList(int resources,
			Map<String, ArrayList<MessageInfo>> mesgs, String previousGroup) {
		  
		ArrayList<MessageInfo> response = priority.prioritizeMessageList(
				resources, mesgs, previousGroup);
		return response;
		
		
	

	}

	/*
	 * Add new message in HashMap. If group exist in the Map then update existing list otherwise create new entry in the Map. 
	 * If message status is cancel it will remove all the messages belongs to that group.
	 * If message status is terminate and it got message message for same group then it will throw TerminationException exception
	 * @see org.jpmorgan.resource.msg.MessageProcessor#addMessage()
	 */
	@Override
	public void addMessage(MessageInfo msg) throws TerminationException{
		
		synchronized (messageList) {
			logger.info("Adding to message queue : " + msg.getGroupId() +", status :"+msg.getStatus());
			
			if(msg.getStatus().equalsIgnoreCase(MessageUtil.TERMINATE)){
				
				messageTerminationList.put(msg.getGroupId(), MessageUtil.TERMINATE);
				
				
			}else if(msg.getStatus().equalsIgnoreCase(MessageUtil.CANCEL)){
				
				messageList.remove(msg.getGroupId());
			}else if(!messageTerminationList.containsKey(msg.getGroupId())){
				
				
			 if (messageList.containsKey(msg.getGroupId())) {

				ArrayList<MessageInfo> msgList = messageList.get(msg
						.getGroupId());
				msgList.add(msg);
				messageList.put(msg.getGroupId(), msgList);
			} else {

				ArrayList<MessageInfo> newMsg = new ArrayList<MessageInfo>();
				newMsg.add(msg);

				messageList.put(msg.getGroupId(), newMsg);
			}
			}else if(messageTerminationList.containsKey(msg.getGroupId())){
				
			 throw new TerminationException("MESSAGE_TERMINATION_EXCEPTION","Terminate message already received for this group id "+msg.getGroupId()+". This message can not be processed");
			}
		}

	}

	/*
	 * Increment resource list when resource  is free.
	 * 
	 * @see org.jpmorgan.resource.msg.MessageProcessor#updateResource()
	 */
	@Override
	public void completed() {

		resource = resource + 1;
	}
	
	/*
	 * This method start message processor thread
	 * 
	 */
	public void startMessageProcessor(){
		
		new Thread(new MessageProcessorImpl()).start();
	
	}
		
	}


