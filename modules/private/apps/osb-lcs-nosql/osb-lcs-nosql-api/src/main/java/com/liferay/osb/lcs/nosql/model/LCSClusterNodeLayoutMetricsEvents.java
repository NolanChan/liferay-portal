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
public interface LCSClusterNodeLayoutMetricsEvents extends LCSClusterNode {

	public int getAverageLoadTime();

	public long getCompanyId();

	public int getFrequency();

	public long getGroupId();

	public String getName();

	public String getPartitionKey();

	public String getReferer();

	public String getRemoteAddr();

	public String getRequestStatus();

	public String getRequestURL();

	public int getStatusCode();

	public String getUserAgent();

	public long getUserId();

	public void setAverageLoadTime(int averageLoadTime);

	public void setCompanyId(long companyId);

	public void setFrequency(int frequency);

	public void setGroupId(long groupId);

	public void setName(String name);

	public void setPartitionKey(String partitionKey);

	public void setReferer(String referer);

	public void setRemoteAddr(String remoteAddr);

	public void setRequestStatus(String requestStatus);

	public void setRequestURL(String requestURL);

	public void setStatusCode(int statusCode);

	public void setUserAgent(String userAgent);

	public void setUserId(long userId);

}