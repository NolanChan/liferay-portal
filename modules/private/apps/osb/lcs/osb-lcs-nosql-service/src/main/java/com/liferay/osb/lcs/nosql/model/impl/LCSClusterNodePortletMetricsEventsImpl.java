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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetricsEvents;

import java.util.Date;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodePortletMetricsEventsImpl
	implements LCSClusterNodePortletMetricsEvents {

	@Override
	public int getAverageLoadTime() {
		return _averageLoadTime;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getDisplayName() {
		return _displayName;
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
	public String getLayoutName() {
		return _layoutName;
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
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getRequestType() {
		return _requestType;
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
	public void setDisplayName(String displayName) {
		_displayName = displayName;
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
	public void setLayoutName(String layoutName) {
		_layoutName = layoutName;
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
	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	@Override
	public void setRequestType(String requestType) {
		_requestType = requestType;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private int _averageLoadTime;
	private long _companyId;
	private String _displayName;
	private int _frequency;
	private long _groupId;
	private String _key;
	private String _layoutName;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private String _partitionKey;
	private String _portletId;
	private String _requestType;
	private String _uuid;

}