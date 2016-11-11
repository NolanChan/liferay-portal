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

package com.liferay.portal.reports.engine.console.web.admin.internal.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.web.admin.internal.display.context.util.ReportsEngineRequestHelper;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rafael Praxedes
 */
public class ReportsEngineDisplayContext {

	public ReportsEngineDisplayContext(
		HttpServletRequest request,
		LiferayPortletResponse liferayPortletResponse) {

		_request = request;
		_liferayPortletResponse = liferayPortletResponse;
		_reportsEngineRequestHelper = new ReportsEngineRequestHelper(request);
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter("tabs1", getTabs1());

		return portletURL;
	}

	public String getTabs1() {
		return ParamUtil.getString(_request, "tabs1", "reports");
	}

	public boolean isAdminPortlet() {
		String portletName = getPortletName();

		return portletName.equals(
			ReportsEngineConsolePortletKeys.REPORTS_ADMIN);
	}

	public boolean isDisplayPortlet() {
		return !isAdminPortlet();
	}

	protected String getPortletName() {
		return _reportsEngineRequestHelper.getPortletName();
	}

	private final LiferayPortletResponse _liferayPortletResponse;
	private final ReportsEngineRequestHelper _reportsEngineRequestHelper;
	private final HttpServletRequest _request;

}