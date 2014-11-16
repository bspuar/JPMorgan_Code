package org.jpmorgan.resource.msg.mngt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jpmorgan.resource.msg.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePriority {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MessagePriority.class);
	
	/*
	 * This method prioritise messages if message group already exist in the map.It remove the used messages from the map. It returns the List of messages.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MessageInfo> prioritizeMessageList(int resources,
			Map<String, ArrayList<MessageInfo>> mesgs, String previousGroup) {
				
		//logger.info("Entered in prioritizeMessageList");
		ArrayList<MessageInfo> list = null;
		ArrayList<MessageInfo> response = new ArrayList<MessageInfo>();

		if (previousGroup != null && previousGroup != "") {
			
			//logger.info("previousGroup:"+previousGroup);

			if (mesgs.containsKey(previousGroup)) {

				//logger.info("Group Exist Already");

				list = (ArrayList<MessageInfo>) mesgs.get(previousGroup);

				
				if (list.size() == resources) {

					response = (ArrayList<MessageInfo>) list.clone();
					mesgs.remove(previousGroup);
					
				} else if (list.size() > resources) {

					for (int i = 0; i < resources; i++) {

						response.add(list.get(i));

						list.remove(i);
						i = i - 1;
						resources = resources - 1;

						
					}
				} else if (list.size() > 0 && list.size() < resources) {
					// Copy messages from first list
					response = (ArrayList<MessageInfo>) list.clone();
					list.clear();
					mesgs.remove(previousGroup);
                    //copy message from next list
					List<String> keys = new ArrayList<String>(mesgs.keySet());

					
					Collections.sort(keys);
					for (int j = 0; j < keys.size(); j++) {
						

						list = (ArrayList<MessageInfo>) mesgs.get(keys.get(j));

						if (list.size() == resources - response.size()) {
							response.addAll(list);
							mesgs.remove(keys.get(j));
						} else if (list.size() > resources - response.size()) {

							for (int i = 0; i < resources - response.size(); i++) {

								response.add(list.get(i));

								list.remove(i);
								i = i - 1;
								resources = resources - 1;
						
							}

							if (response.size() == resources) {
								break;
							}
						} else if (list.size() < resources - response.size()) {
							response.addAll(list);
							mesgs.remove(keys.get(j));
						}

						if (response.size() == resources) {
							break;
						}
						
//						System.out.println("List :" + list.size() + " response :"
//								+ response.size());
					}
					

				}

			} else {

			
				response=this.sortMessageList(resources, mesgs);
				
				

			}
		} else {
  
			response=this.sortMessageList(resources, mesgs);
			
		}
		 //System.out.println("Map size :" + mesgs.size());
		return response;

	}

	/*
	 * This method prioritise messages if message group does not exit in the map. It removes the used messages.
	 * 
	 */
	
	public ArrayList<MessageInfo> sortMessageList(int resources,
			Map<String, ArrayList<MessageInfo>> mesgs){
		
		ArrayList<MessageInfo> list = null;
		ArrayList<MessageInfo> response = new ArrayList<MessageInfo>();
		
		List<String> keys = new ArrayList<String>(mesgs.keySet());

		Collections.sort(keys);
		for (int j = 0; j < keys.size(); j++) {
			

			list = (ArrayList<MessageInfo>) mesgs.get(keys.get(j));

			if (list.size() == resources - response.size()) {
				response.addAll(list);
				mesgs.remove(keys.get(j));
			} else if (list.size() > resources - response.size()) {

				for (int i = 0; i < resources - response.size(); i++) {

					response.add(list.get(i));

					list.remove(i);
					i = i - 1;
					resources = resources - 1;

				}

				if (response.size() == resources) {
					break;
				}
			} else if (list.size() < resources - response.size()) {
				response.addAll(list);
				mesgs.remove(keys.get(j));
			}

			if (response.size() == resources) {
				break;
			}
			
//			System.out.println("List :" + list.size() + " response :"
//					+ response.size());
		}
		
		
		return response;
		
	}

}
