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

package com.liferay.portal.reports.engine.console.web.admin.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.BaseJSPSettingsConfigurationAction;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.reports.engine.console.configuration.ReportsGroupServiceEmailConfiguration;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.web.admin.internal.display.context.util.ReportsEngineRequestHelper;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Prathima Shreenath
 */
@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {"javax.portlet.name=" + ReportsEngineConsolePortletKeys.REPORTS_ADMIN},
	service = ConfigurationAction.class
)
public class ReportsConfigurationAction
	extends BaseJSPSettingsConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/admin/configuration.jsp";
	}

	@Override
	public void postProcess(
			long companyId, PortletRequest portletRequest, Settings settings)
		throws PortalException {

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		ReportsEngineRequestHelper reportsEngineRequestHelper =
			new ReportsEngineRequestHelper(request);

		ReportsGroupServiceEmailConfiguration
			reportsGroupServiceEmailConfiguration =
				reportsEngineRequestHelper.
					getReportsGroupServiceEmailConfiguration();

		removeDefaultValue(
			portletRequest, modifiableSettings, "emailDeliveryBody",
			reportsGroupServiceEmailConfiguration.emailDeliveryBody());
		removeDefaultValue(
			portletRequest, modifiableSettings, "emailDeliverySubject",
			reportsGroupServiceEmailConfiguration.emailDeliverySubject());
		removeDefaultValue(
			portletRequest, modifiableSettings, "emailNotificationsBody",
			reportsGroupServiceEmailConfiguration.emailNotificationsBody());
		removeDefaultValue(
			portletRequest, modifiableSettings, "emailNotificationsSubject",
			reportsGroupServiceEmailConfiguration.emailNotificationsSubject());
	}

}