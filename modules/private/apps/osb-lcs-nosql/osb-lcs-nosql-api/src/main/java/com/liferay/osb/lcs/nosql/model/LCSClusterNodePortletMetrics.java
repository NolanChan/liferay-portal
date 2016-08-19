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
 * @author Ivica Cardic
 */
public interface LCSClusterNodePortletMetrics extends LCSClusterNode {

	public int getAverageLoadTime();

	public long getCompanyId();

	public long getCount();

	public String getDisplayName();

	public int getFrequency();

	public long getGroupId();

	public String getLayoutName();

	public int getMaxLoadTime();

	public int getMinLoadTime();

	public String getName();

	public String getPortletId();

	public String getRequestType();

	public void setAverageLoadTime(int averageLoadTime);

	public void setCompanyId(long companyId);

	public void setCount(long count);

	public void setDisplayName(String displayName);

	public void setFrequency(int frequency);

	public void setGroupId(long groupId);

	public void setLayoutName(String layoutName);

	public void setMaxLoadTime(int maxLoadTime);

	public void setMinLoadTime(int minLoadTime);

	public void setName(String name);

	public void setPortletId(String portletId);

	public void setRequestType(String requestType);

}