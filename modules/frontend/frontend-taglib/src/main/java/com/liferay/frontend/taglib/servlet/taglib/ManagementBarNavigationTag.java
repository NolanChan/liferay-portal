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

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Sergio González
 */
public class ManagementBarNavigationTag extends IncludeTag implements BodyTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public List<FilterNavigationItem> getFilterNavigationItems() {
		return _filterNavigationItems;
	}

	public void setNavigationKeys(String[] navigationKeys) {
		_navigationKeys = navigationKeys;
	}

	public void setNavigationParam(String navigationParam) {
		_navigationParam = navigationParam;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	@Override
	protected void cleanUp() {
		_filterNavigationItems = new ArrayList<>();
		_navigationKeys = null;
		_navigationParam = "navigation";
		_portletURL = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected int processStartTag() throws Exception {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		if (_filterNavigationItems == null) {
			_filterNavigationItems = new ArrayList<>();
		}

		String navigationKey = ParamUtil.getString(request, _navigationParam);

		if (ArrayUtil.isNotEmpty(_navigationKeys)) {
			for (String curNavigationKey : _navigationKeys) {
				_portletURL.setParameter(_navigationParam, curNavigationKey);

				FilterNavigationItem filterNavigationItem =
					new FilterNavigationItem(
						curNavigationKey.equals(navigationKey),
						curNavigationKey, _portletURL.toString());

				_filterNavigationItems.add(filterNavigationItem);
			}
		}

		request.setAttribute(
			"liferay-frontend:management-bar-navigation:filterNavigationItems",
			_filterNavigationItems);
		request.setAttribute(
			"liferay-frontend:management-bar-navigation:navigationKeys",
			_navigationKeys);
		request.setAttribute(
			"liferay-frontend:management-bar-navigation:navigationParam",
			_navigationParam);
		request.setAttribute(
			"liferay-frontend:management-bar-navigation:portletURL",
			_portletURL);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/management_bar_navigation/page.jsp";

	private List<FilterNavigationItem> _filterNavigationItems =
		new ArrayList<>();
	private String[] _navigationKeys;
	private String _navigationParam = "navigation";
	private PortletURL _portletURL;

}