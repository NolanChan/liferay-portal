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

package com.liferay.portlet.messageboards.subscriptions;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousMailTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.test.MBTestUtil;
import com.liferay.portlet.subscriptions.test.BaseSubscriptionAuthorTestCase;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author José Ángel Jiménez
 */
@Sync
public class MBSubscriptionAuthorTest extends BaseSubscriptionAuthorTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE,
			SynchronousMailTestRule.INSTANCE);

	@Override
	protected long addBaseModel(long userId, long containerModelId)
		throws Exception {

		MBMessage message = MBTestUtil.addMessage(
			userId, group.getGroupId(), containerModelId, true);

		return message.getMessageId();
	}

	@Override
	protected long addContainerModel(long userId, long containerModelId)
		throws Exception {

		MBCategory category = MBTestUtil.addCategory(
			userId, group.getGroupId(), containerModelId);

		return category.getCategoryId();
	}

	@Override
	protected void addSubscription(long userId, long containerModelId)
		throws Exception {

		MBCategoryLocalServiceUtil.subscribeCategory(
			userId, group.getGroupId(), containerModelId);
	}

	@Override
	protected boolean isSubscriptionForAuthorEnabled() {
		return true;
	}

	@Override
	protected void updateBaseModel(long userId, long baseModelId)
		throws Exception {

		MBMessage message = MBMessageLocalServiceUtil.getMessage(baseModelId);

		MBTestUtil.updateMessage(
				userId, message, RandomTestUtil.randomString(),
				RandomTestUtil.randomString(50), true);
	}

}