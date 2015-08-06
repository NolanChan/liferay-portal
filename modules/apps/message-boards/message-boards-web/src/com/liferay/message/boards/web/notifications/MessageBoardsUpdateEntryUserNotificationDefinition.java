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

package com.liferay.message.boards.web.notifications;

import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;
import com.liferay.portal.model.UserNotificationDeliveryConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS},
	service = UserNotificationDefinition.class
)
public class MessageBoardsUpdateEntryUserNotificationDefinition
	extends UserNotificationDefinition {

	public MessageBoardsUpdateEntryUserNotificationDefinition() {
		super(
			MBPortletKeys.MESSAGE_BOARDS, 0,
			NOTIFICATION_TYPE_UPDATE_ENTRY,
			"receive-a-notification-when-someone-updates-a-post-you-are-" +
				"subscribed-to");

		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"email", UserNotificationDeliveryConstants.TYPE_EMAIL, true,
				true));
		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"website", UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				true));
	}

}