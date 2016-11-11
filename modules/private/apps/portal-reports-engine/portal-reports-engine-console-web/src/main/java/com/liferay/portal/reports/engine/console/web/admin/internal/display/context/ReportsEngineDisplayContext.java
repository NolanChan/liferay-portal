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
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.web.admin.configuration.ReportsEngineAdminWebConfiguration;
import com.liferay.portal.reports.engine.console.web.admin.internal.display.context.util.ReportsEngineRequestHelper;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rafael Praxedes
 */
public class ReportsEngineDisplayContext {

	public ReportsEngineDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		_request = PortalUtil.getHttpServletRequest(liferayPortletRequest);

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			_request);

		_reportsEngineRequestHelper = new ReportsEngineRequestHelper(_request);
	}

	public String getCurrentURL() {
		PortletURL portletURL = PortletURLUtil.getCurrent(
			_liferayPortletRequest, _liferayPortletResponse);

		return portletURL.toString();
	}

	public String getDisplayStyle() {
		if (_displayStyle == null) {
			PortalPreferences portalPreferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					_liferayPortletRequest);

			_displayStyle = ParamUtil.getString(
				_liferayPortletRequest, "displayStyle");

			if (Validator.isNull(_displayStyle)) {
				ReportsEngineAdminWebConfiguration
					reportsEngineAdminWebConfiguration =
						(ReportsEngineAdminWebConfiguration)
						_liferayPortletRequest.getAttribute(
							ReportsEngineAdminWebConfiguration.class.getName());

				_displayStyle = portalPreferences.getValue(
					ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
					"display-style",
					reportsEngineAdminWebConfiguration.defaultDisplayView());
			}
			else if (ArrayUtil.contains(_DISPLAY_VIEWS, _displayStyle)) {
				portalPreferences.setValue(
					ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
					"display-style", _displayStyle);
			}

			if (!ArrayUtil.contains(_DISPLAY_VIEWS, _displayStyle)) {
				_displayStyle = _DISPLAY_VIEWS[0];
			}
		}

		return _displayStyle;
	}

	public String[] getDisplayViews() {
		return _DISPLAY_VIEWS;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_liferayPortletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_request, "orderByCol");

		if (Validator.isNull(_orderByCol)) {
			_orderByCol = _portalPreferences.getValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-col",
				"last-activity-date");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
					"order-by-col", _orderByCol);
			}
		}

		return _orderByCol;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType");

		if (Validator.isNull(_orderByType)) {
			_orderByType = _portalPreferences.getValue(
				ReportsEngineConsolePortletKeys.REPORTS_ADMIN, "order-by-type",
				"asc");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portalPreferences.setValue(
					ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
					"order-by-type", _orderByType);
			}
		}

		return _orderByType;
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

	public boolean isDefinitionsTabSelected() {
		String tabs1 = getTabs1();

		if (tabs1.equals("definitions")) {
			return true;
		}

		return false;
	}

	public boolean isDisplayPortlet() {
		return !isAdminPortlet();
	}

	public boolean isReportsTabSelected() {
		String tabs1 = getTabs1();

		if (tabs1.equals("reports")) {
			return true;
		}

		return false;
	}

	public boolean isSearch() {
		if (Validator.isNotNull(getKeywords())) {
			return true;
		}

		return false;
	}

	public boolean isSourcesTabSelected() {
		String tabs1 = getTabs1();

		if (tabs1.equals("sources")) {
			return true;
		}

		return false;
	}

	protected String getPortletName() {
		return _reportsEngineRequestHelper.getPortletName();
	}

	private static final String[] _DISPLAY_VIEWS = {"descriptive", "list"};

	private String _displayStyle;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
	private final PortalPreferences _portalPreferences;
	private final ReportsEngineRequestHelper _reportsEngineRequestHelper;
	private final HttpServletRequest _request;

}