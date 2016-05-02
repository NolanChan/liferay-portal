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

import com.liferay.osb.lcs.nosql.model.LCSStatsLayoutMetricsEvents;

/**
 * @author Riccardo Ferrari
 */
public class LCSStatsLayoutMetricsEventsImpl
	extends LCSStatsDataBaseImpl implements LCSStatsLayoutMetricsEvents {

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getRequestStatus() {
		return _requestStatus;
	}

	@Override
	public String getRequestURL() {
		return _requestURL;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setRequestStatus(String requestStatus) {
		_requestStatus = requestStatus;
	}

	@Override
	public void setRequestURL(String requestURL) {
		_requestURL = requestURL;
	}

	private String _name;
	private String _requestStatus;
	private String _requestURL;

}