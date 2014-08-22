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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.portlet.PortletURL;

/**
 * @author Sergio González
 */
public class AppViewDisplayStyleTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setDisplayStyles(String[] displayStyles) {
		_displayStyles = displayStyles;
	}

	public void setEventName(String eventName) {
		_eventName = eventName;
	}

	public void setRequestParams(Map<String, String> requestParams) {
		_requestParams = requestParams;
	}

	public void setDisplayStyleUrl(PortletURL displayStyleUrl) {
		_displayStyleUrl = displayStyleUrl;
	}

	@Override
	protected void cleanUp() {
		_displayStyle = null;
		_displayStyles = null;
		_eventName = null;
		_requestParams = null;
		_displayStyleUrl = null;
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
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:app-view-display-style:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-ui:app-view-display-style:displayStyles", _displayStyles);
		request.setAttribute(
			"liferay-ui:app-view-display-style:eventName", _eventName);
		request.setAttribute(
			"liferay-ui:app-view-display-style:requestParams", _requestParams);
		request.setAttribute(
			"liferay-ui:app-view-display-style:displayStyleUrl", _displayStyleUrl);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE =
		"/html/taglib/ui/app_view_display_style/page.jsp";

	private String _displayStyle;
	private String[] _displayStyles;
	private String _eventName;
	private Map<String, String> _requestParams;
	private PortletURL _displayStyleUrl;

}