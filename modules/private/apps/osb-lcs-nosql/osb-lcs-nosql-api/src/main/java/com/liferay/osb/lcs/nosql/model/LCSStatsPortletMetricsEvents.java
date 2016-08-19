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

package com.liferay.osb.lcs.nosql.model;

/**
 * @author Riccardo Ferrari
 */
public interface LCSStatsPortletMetricsEvents
	extends LCSStats, LCSStatsData, LCSStatsSparkMetadata {

	public String getDisplayName();

	public String getLayoutName();

	public String getName();

	public String getPortletId();

	public String getRequestType();

	public void setDisplayName(String displayName);

	public void setLayoutName(String layoutName);

	public void setName(String name);

	public void setPortletId(String portletId);

	public void setRequestType(String requestType);

}