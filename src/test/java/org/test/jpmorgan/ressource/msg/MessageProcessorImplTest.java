/**
 * 
 */
package org.test.jpmorgan.ressource.msg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jpmorgan.resource.msg.MessageInfo;
import org.jpmorgan.resource.msg.mngt.MessagePriority;
import org.jpmorgan.resource.msg.mngt.MessageProcessorImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author bhupsing
 *
 */
public class MessageProcessorImplTest {
	private static Map<String, ArrayList<MessageInfo>> yourMap = new HashMap<String, ArrayList<MessageInfo>>();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		MessageInfo msg1 = new MessageInfo("Hi", "1", "group1","active");
		MessageInfo msg2 = new MessageInfo("Hello", "2", "group2","active");
		MessageInfo msg3 = new MessageInfo("Hi there", "3", "group1","active");

		MessageInfo msg4 = new MessageInfo("Hi there", "3", "group4","active");

		MessageInfo msg5 = new MessageInfo("Hi there", "3", "group3","active");

		ArrayList<MessageInfo> l1 = new ArrayList<MessageInfo>();
		l1.add(msg1);

		l1.add(msg3);
		ArrayList<MessageInfo> l2 = new ArrayList<MessageInfo>();

		l2.add(msg2);

		ArrayList<MessageInfo> l3 = new ArrayList<MessageInfo>();

		l3.add(msg4);

		ArrayList<MessageInfo> l4 = new ArrayList<MessageInfo>();

		l4.add(msg5);
		
		yourMap.put(msg1.getGroupId(), l1);
		yourMap.put(msg2.getGroupId(), l2);
		yourMap.put(msg4.getGroupId(), l3);
		yourMap.put(msg5.getGroupId(), l4);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link org.jpmorgan.resource.msg.mngt.MessageProcessorImpl#MessageProcessorImpl()}.
	 */
	

	/**
	 * Test method for {@link org.jpmorgan.resource.msg.mngt.MessageProcessorImpl#getMessageList(int, java.util.Map, java.lang.String)}.
	 */
	@Test
	public final void testGetMessageList() {
     List<MessageInfo> msges = new MessageProcessorImpl().getMessageList(2,yourMap, "group1");
		
		assertNotNull("List not null",msges);
	}

	/**
	 * Test method for {@link org.jpmorgan.resource.msg.mngt.MessageProcessorImpl#addMessage(org.jpmorgan.resource.msg.MessageInfo)}.
	 */
	@Test
	public final void testAddMessage() {
		MessageInfo msg1 = new MessageInfo("Hi", "1", "group1","active");
		ArrayList<MessageInfo> l1 = new ArrayList<MessageInfo>();
		l1.add(msg1);
		yourMap.put(msg1.getGroupId(), l1);
		
		assertNotNull("List not null",yourMap);
	}

	/**
	 * Test method for {@link org.jpmorgan.resource.msg.mngt.MessageProcessorImpl#completed()}.
	 */
	@Test
	public final void testCompleted() {
		//assertNotNull("List not null",new MessageProcessorImpl());
	}

	/**
	 * Test method for {@link org.jpmorgan.resource.msg.mngt.MessageProcessorImpl#startMessageProcessor()}.
	 */
	@Test
	public final void testStartMessageProcessor() {
		assertNotNull("Not null",new Thread(new MessageProcessorImpl()));
	}

}
