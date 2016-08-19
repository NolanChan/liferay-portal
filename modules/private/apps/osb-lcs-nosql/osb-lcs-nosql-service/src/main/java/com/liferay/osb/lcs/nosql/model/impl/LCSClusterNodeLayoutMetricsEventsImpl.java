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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetricsEvents;

import java.util.Date;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeLayoutMetricsEventsImpl
	implements LCSClusterNodeLayoutMetricsEvents {

	@Override
	public int getAverageLoadTime() {
		return _averageLoadTime;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public int getFrequency() {
		return _frequency;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPartitionKey() {
		return _partitionKey;
	}

	@Override
	public String getReferer() {
		return _referer;
	}

	@Override
	public String getRemoteAddr() {
		return _remoteAddr;
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
	public int getStatusCode() {
		return _statusCode;
	}

	@Override
	public String getUserAgent() {
		return _userAgent;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public String getUUID() {
		return _uuid;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setAverageLoadTime(int averageLoadTime) {
		_averageLoadTime = averageLoadTime;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setPartitionKey(String partitionKey) {
		_partitionKey = partitionKey;
	}

	@Override
	public void setReferer(String referer) {
		_referer = referer;
	}

	@Override
	public void setRemoteAddr(String remoteAddr) {
		_remoteAddr = remoteAddr;
	}

	@Override
	public void setRequestStatus(String requestStatus) {
		_requestStatus = requestStatus;
	}

	@Override
	public void setRequestURL(String requestURL) {
		_requestURL = requestURL;
	}

	@Override
	public void setStatusCode(int statusCode) {
		_statusCode = statusCode;
	}

	@Override
	public void setUserAgent(String userAgent) {
		_userAgent = userAgent;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private int _averageLoadTime;
	private long _companyId;
	private int _frequency;
	private long _groupId;
	private String _key;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private String _partitionKey;
	private String _referer;
	private String _remoteAddr;
	private String _requestStatus;
	private String _requestURL;
	private int _statusCode;
	private String _userAgent;
	private long _userId;
	private String _uuid;

}