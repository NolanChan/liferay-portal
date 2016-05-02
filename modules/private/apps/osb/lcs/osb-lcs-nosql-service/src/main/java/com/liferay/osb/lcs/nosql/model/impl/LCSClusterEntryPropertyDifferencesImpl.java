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

import com.liferay.osb.lcs.nosql.model.LCSClusterEntryPropertyDifferences;

import java.util.Map;

/**
 * @author Matija Petanjek
 */
public class LCSClusterEntryPropertyDifferencesImpl
	implements LCSClusterEntryPropertyDifferences {

	@Override
	public long getLCSClusterEntryId() {
		return _lcsClusterEntryId;
	}

	@Override
	public String getPropertyName() {
		return _propertyName;
	}

	@Override
	public Map<String, String> getPropertyValues() {
		return _propertyValues;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setLCSClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;
	}

	@Override
	public void setPropertyValues(Map<String, String> propertyValues) {
		_propertyValues = propertyValues;
	}

	private long _lcsClusterEntryId;
	private boolean _new;
	private String _propertyName;
	private Map<String, String> _propertyValues;

}