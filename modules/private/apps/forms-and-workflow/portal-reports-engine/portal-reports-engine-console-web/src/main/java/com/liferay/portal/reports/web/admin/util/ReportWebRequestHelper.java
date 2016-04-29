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

package com.liferay.portal.reports.web.admin.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.settings.SettingsLocator;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.reports.configuration.ReportsGroupServiceEmailConfiguration;
import com.liferay.portal.reports.constants.ReportsPortletKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Prathima Shreenath
 */
public class ReportWebRequestHelper {

	public static ReportsGroupServiceEmailConfiguration
		getReportsGroupServiceEmailConfiguration(
			HttpServletRequest httpServletRequest) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long siteGroupId = themeDisplay.getSiteGroupId();

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			SettingsLocator settingsLocator = new GroupServiceSettingsLocator(
				siteGroupId, ReportsPortletKeys.SERVICE_NAME);

			if (Validator.isNotNull(portletDisplay.getPortletResource())) {
				settingsLocator = new ParameterMapSettingsLocator(
					httpServletRequest.getParameterMap(), settingsLocator);
			}

			return ConfigurationProviderUtil.getConfiguration(
				ReportsGroupServiceEmailConfiguration.class, settingsLocator);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

}