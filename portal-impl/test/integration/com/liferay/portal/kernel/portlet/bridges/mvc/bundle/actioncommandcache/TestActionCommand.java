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

package com.liferay.portal.kernel.portlet.bridges.mvc.bundle.actioncommandcache;

import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Manuel de la Peña
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TestActionCommandPortlet.PORTLET_NAME,
		"action.command.name=" + TestActionCommand.TEST_ACTION_COMMAND_NAME,
		"service.ranking:Integer=" + Integer.MAX_VALUE
	},
	service = ActionCommand.class
)
public class TestActionCommand implements ActionCommand {

	public static final String TEST_ACTION_COMMAND_NAME =
		"TEST_ACTION_COMMAND_NAME";

	@Override
	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		portletRequest.setAttribute(
			"TEST_ACTION_COMMAND", "TEST_ACTION_COMMAND");

		return true;
	}

}