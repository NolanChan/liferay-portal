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

package com.liferay.message.boards.web.messaging;

import com.liferay.message.boards.web.configuration.MessageBoardsWebConfigurationValues;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerType;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael Young
 * @author Tina Tian
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + PortletKeys.MESSAGE_BOARDS},
	service = SchedulerEntry.class
)
public class ExpireBanMessageListener
	extends BaseSchedulerEntryMessageListener {

	@Activate
	protected void activate() {
		schedulerEntry.setTimeUnit(TimeUnit.MINUTE);
		schedulerEntry.setTriggerType(TriggerType.SIMPLE);
		schedulerEntry.setTriggerValue(
			MessageBoardsWebConfigurationValues.EXPIRE_BAN_JOB_INTERVAL);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		MBBanLocalServiceUtil.expireBans();
	}

	@Reference(
		target = "(javax.portlet.name=" + PortletKeys.MESSAGE_BOARDS + ")",
		unbind = "-"
	)
	protected void setPortlet(Portlet portlet) {
	}

	@Reference(target = "(original.bean=*)", unbind = "*")
	protected void setServletContext(ServletContext servletContext) {
	}

}