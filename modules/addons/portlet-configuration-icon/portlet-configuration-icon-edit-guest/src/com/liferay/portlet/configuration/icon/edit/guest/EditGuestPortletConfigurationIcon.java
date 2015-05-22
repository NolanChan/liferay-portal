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

package com.liferay.portlet.configuration.icon.edit.guest;

import com.liferay.portal.kernel.portlet.configuration.BasePortletConfigurationIcon;
import com.liferay.portal.theme.PortletDisplay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditGuestPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public EditGuestPortletConfigurationIcon(HttpServletRequest request) {
		init(request);
	}

	@Override
	public String getImage() {
		return "../aui/edit-sign";
	}

	@Override
	public String getMessage() {
		return "guest-preferences";
	}

	@Override
	public String getURL() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getURLEditGuest();
	}

	@Override
	public boolean isShow() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.isShowEditGuestIcon();
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

}