package org.jpmorgan.resource.msg.mngt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceSchedulerApp {
	
	public static void main(String[] args) {
		  ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
		  
	}

}
