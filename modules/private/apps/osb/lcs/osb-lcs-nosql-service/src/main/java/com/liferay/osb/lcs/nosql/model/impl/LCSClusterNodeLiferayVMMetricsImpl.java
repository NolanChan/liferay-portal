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

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeLiferayVMMetricsImpl
	implements LCSClusterNodeLiferayVMMetrics {

	@JsonIgnore
	@Override
	public double getCacheHitRatio() {
		double cacheHitRatio = 0.0;

		int cacheLookUpCount = _cacheHits + _cacheMisses;

		if (cacheLookUpCount > 0) {
			cacheHitRatio = ((double)_cacheHits / cacheLookUpCount) * 100;
		}

		return cacheHitRatio;
	}

	@Override
	public int getCacheHits() {
		return _cacheHits;
	}

	@Override
	public int getCacheMisses() {
		return _cacheMisses;
	}

	@JsonIgnore
	@Override
	public double getCacheMissRatio() {
		double cacheMissRatio = 0.0;

		int cacheLookUpCount = _cacheHits + _cacheMisses;

		if (cacheLookUpCount > 0) {
			cacheMissRatio = ((double)_cacheMisses / cacheLookUpCount) * 100;
		}

		return cacheMissRatio;
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
	public int getObjectCount() {
		return _objectCount;
	}

	@Override
	public String getType() {
		return _type;
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
	public void setCacheHits(int cacheHits) {
		_cacheHits = cacheHits;
	}

	@Override
	public void setCacheMisses(int cacheMisses) {
		_cacheMisses = cacheMisses;
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
	public void setObjectCount(int objectCount) {
		_objectCount = objectCount;
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private int _cacheHits;
	private int _cacheMisses;
	private String _key;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private int _objectCount;
	private String _type;
	private String _uuid;

}