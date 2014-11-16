/**
 * 
 */
package org.test.jpmorgan.ressource.msg;

import static org.junit.Assert.*;

import org.jpmorgan.resource.msg.mngt.MessageUtil;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author bhupsing
 *
 */
public class MessageUtilTest {

	
	@Test
	public final void test() {
		
		assertNotNull("Not null",MessageUtil.CANCEL);
		assertNotNull("Not null",MessageUtil.TERMINATE);
		assertNotNull("Not null",MessageUtil.RESOURCES);
	}

}
