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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeHibernateMetrics;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeHibernateMetricsImpl
	implements LCSClusterNodeHibernateMetrics {

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public int getQueryCacheHitCount() {
		return _queryCacheHitCount;
	}

	@Override
	public int getQueryCacheMissCount() {
		return _queryCacheMissCount;
	}

	@Override
	public int getQueryExecutionCount() {
		return _queryExecutionCount;
	}

	@Override
	public int getQueryExecutionMaxTime() {
		return _queryExecutionMaxTime;
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
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setQueryCacheHitCount(int queryCacheHitCount) {
		_queryCacheHitCount = queryCacheHitCount;
	}

	@Override
	public void setQueryCacheMissCount(int queryCacheMissCount) {
		_queryCacheMissCount = queryCacheMissCount;
	}

	@Override
	public void setQueryExecutionCount(int queryExecutionCount) {
		_queryExecutionCount = queryExecutionCount;
	}

	@Override
	public void setQueryExecutionMaxTime(int queryExecutionMaxTime) {
		_queryExecutionMaxTime = queryExecutionMaxTime;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private String _key;
	private Date _modifiedDate;
	private boolean _new;
	private int _queryCacheHitCount;
	private int _queryCacheMissCount;
	private int _queryExecutionCount;
	private int _queryExecutionMaxTime;
	private String _uuid;

}