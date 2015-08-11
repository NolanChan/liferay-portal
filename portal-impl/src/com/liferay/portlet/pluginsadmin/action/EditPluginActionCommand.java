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

package com.liferay.portlet.pluginsadmin.action;

import com.liferay.pluginsadmin.web.constants.PluginsAdminPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.PluginSettingServiceUtil;
import com.liferay.portal.service.PortletServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Arrays;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Peter Fellwock
 */

@OSGiBeanProperties(
	property = {
		"javax.portlet.name=" + PluginsAdminPortletKeys.PLUGINS_ADMIN,
		"mvc.command.name=/plugins_admin/edit_plugin"
	},
	service = MVCActionCommand.class
)
public class EditPluginActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updatePluginSetting(actionRequest);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass());

				sendRedirect(
					actionRequest, actionResponse,
					"/html/portlet/plugins_admin/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	protected void updatePluginSetting(ActionRequest actionRequest)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(actionRequest);
		String pluginId = ParamUtil.getString(actionRequest, "pluginId");
		String pluginType = ParamUtil.getString(actionRequest, "pluginType");

		String[] rolesArray = StringUtil.split(
			ParamUtil.getString(actionRequest, "roles"), '\n');

		Arrays.sort(rolesArray);

		String roles = StringUtil.merge(rolesArray);

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (pluginType.equals(Plugin.TYPE_PORTLET)) {
			String portletId = pluginId;

			PortletServiceUtil.updatePortlet(
				companyId, portletId, StringPool.BLANK, active);
		}
		else {
			PluginSettingServiceUtil.updatePluginSetting(
				companyId, pluginId, pluginType, roles, active);
		}
	}

}