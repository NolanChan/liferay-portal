/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.messageboards.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.util.test.MBTestUtil;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jonathan McCann
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MBMessageLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testThreadLastPostDate() throws Exception {
		MBMessage parentMessage = MBTestUtil.addMessage(_group.getGroupId());

		Thread.sleep(2000);

		MBMessage firstReplyMessage = MBTestUtil.addMessage(
			_group.getGroupId(), parentMessage.getCategoryId(),
			parentMessage.getThreadId(), parentMessage.getMessageId());

		Thread.sleep(2000);

		MBMessage secondReplyMessage = MBTestUtil.addMessage(
			_group.getGroupId(), parentMessage.getCategoryId(),
			parentMessage.getThreadId(), parentMessage.getMessageId());

		MBMessageLocalServiceUtil.deleteMessage(
			secondReplyMessage.getMessageId());

		Date lastMessageModifiedDate = firstReplyMessage.getModifiedDate();

		MBThread mbThread = parentMessage.getThread();

		Date mbThreadLastPostDate = mbThread.getLastPostDate();

		Assert.assertTrue(
			mbThreadLastPostDate.getTime() ==
				(lastMessageModifiedDate.getTime() / 1000) * 1000);
	}

	@DeleteAfterTestRun
	private Group _group;

}