package org.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.MessagePriority;



public class TestMessagePriority {

	public static void main(String[] args) {	
		
		  MessageInfo msg1=new MessageInfo("hi","1","group1","active");
		  MessageInfo msg2=new MessageInfo("hi","1","group1","active");
		  MessageInfo msg3=new MessageInfo("hi","1","group4","active");
		  MessageInfo msg4=new MessageInfo("hi","1","group5","active");
		  
		  ArrayList<MessageInfo> l1 = new ArrayList<MessageInfo>();
			l1.add(msg1);

			l1.add(msg2);
			ArrayList<MessageInfo> l2 = new ArrayList<MessageInfo>();

			l2.add(msg3);

			ArrayList<MessageInfo> l3 = new ArrayList<MessageInfo>();

			l3.add(msg4);

			
		  HashMap<String, ArrayList<MessageInfo>> messageList = new HashMap<String, ArrayList<MessageInfo>>();
		  
		  messageList.put(msg1.getGroupId(), l1);
		  messageList.put(msg3.getGroupId(), l2);
		  messageList.put(msg4.getGroupId(), l3);
		 
		  List<MessageInfo> msges = new MessagePriority().prioritizeMessageList(3, messageList, "group1");
			System.out.println(messageList.size());
			for (int i = 0; i < msges.size(); i++) {

				System.out.println("List of messages need to send :"+msges.get(i).getGroupId());
			}
		  

	}

}
