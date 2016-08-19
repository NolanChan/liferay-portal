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

package com.liferay.osb.lcs.nosql.model.impl;

import com.liferay.osb.lcs.nosql.model.LCSStatsPortletMetricsEvents;

/**
 * @author Riccardo Ferrari
 */
public class LCSStatsPortletMetricsEventsImpl
	extends LCSStatsDataBaseImpl implements LCSStatsPortletMetricsEvents {

	@Override
	public String getDisplayName() {
		return _displayName;
	}

	@Override
	public String getLayoutName() {
		return _layoutName;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getRequestType() {
		return _requestType;
	}

	@Override
	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	@Override
	public void setLayoutName(String layoutName) {
		_layoutName = layoutName;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	@Override
	public void setRequestType(String requestType) {
		_requestType = requestType;
	}

	private String _displayName;
	private String _layoutName;
	private String _name;
	private String _portletId;
	private String _requestType;

}