/**
 * 
 */
package org.jpmorgan.resource.msg;

import java.util.ArrayList;
import java.util.Map;

import org.jpmorgan.resource.msg.exception.TerminationException;


/**
 * @author bhupsing
 *
 */
public interface MessageProcessor {
	
	public void addMessage(MessageInfo msg) throws TerminationException;
	public void completed();	
	public ArrayList<MessageInfo> getMessageList(int resources,
			Map<String, ArrayList<MessageInfo>> mesgs, String previousGroup);

}
