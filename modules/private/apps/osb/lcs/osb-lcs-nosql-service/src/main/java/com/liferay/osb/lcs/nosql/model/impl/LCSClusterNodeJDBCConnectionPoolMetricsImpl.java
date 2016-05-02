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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJDBCConnectionPoolMetrics;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeJDBCConnectionPoolMetricsImpl
	implements LCSClusterNodeJDBCConnectionPoolMetrics {

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
	public int getNumActive() {
		return _numActive;
	}

	@Override
	public int getNumIdle() {
		return _numIdle;
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
	public void setNumActive(int numActive) {
		_numActive = numActive;
	}

	@Override
	public void setNumIdle(int numIdle) {
		_numIdle = numIdle;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private String _key;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private int _numActive;
	private int _numIdle;
	private String _uuid;

}