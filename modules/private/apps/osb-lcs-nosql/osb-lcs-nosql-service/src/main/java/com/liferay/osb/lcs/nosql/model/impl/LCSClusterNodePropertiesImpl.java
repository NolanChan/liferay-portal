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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeProperties;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodePropertiesImpl implements LCSClusterNodeProperties {

	@Override
	public String getHashCode() {
		return _hashCode;
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
	public Map<String, String> getProperties() {
		return _properties;
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
	public void setHashCode(String hashCode) {
		_hashCode = hashCode;
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
	public void setProperties(Map<String, String> properties) {
		if (properties == null) {
			return;
		}

		_properties = properties;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private String _hashCode;
	private String _key;
	private Date _modifiedDate;
	private boolean _new;
	private Map<String, String> _properties = Collections.emptyMap();
	private String _uuid;

}