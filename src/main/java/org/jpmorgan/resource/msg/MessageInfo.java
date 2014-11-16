package org.jpmorgan.resource.msg;

import java.io.Serializable;


public class MessageInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String details;
	private String messageId;
	private String groupId;
	private String status;
	
	public MessageInfo(){
		super();
	}
	
	public MessageInfo(String details, String messageId, String groupId,String status){
		this.details=details;
		this.messageId=messageId;
		this.groupId=groupId;
		this.status=status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}

