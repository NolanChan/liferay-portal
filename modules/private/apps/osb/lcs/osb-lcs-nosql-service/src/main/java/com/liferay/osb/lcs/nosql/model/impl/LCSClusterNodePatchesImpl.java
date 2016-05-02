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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodePatchesImpl implements LCSClusterNodePatches {

	@Override
	public String getError() {
		return _error;
	}

	@Override
	public List<String> getFixedIssues() {
		return _fixedIssues;
	}

	@Override
	public String getHashCode() {
		return _hashCode;
	}

	@Override
	public List<String> getInstallablePatchIds() {
		return _installablePatchIds;
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
	public Map<String, Integer> getPatchIdsStatuses() {
		return _patchIdsStatuses;
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
	public void setError(String error) {
		_error = error;
	}

	@Override
	public void setFixedIssues(List<String> fixedIssues) {
		if (fixedIssues == null) {
			fixedIssues = Collections.<String>emptyList();
		}

		_fixedIssues = fixedIssues;
	}

	@Override
	public void setHashCode(String hashCode) {
		_hashCode = hashCode;
	}

	@Override
	public void setInstallablePatchIds(List<String> installablePatchIds) {
		if (installablePatchIds == null) {
			installablePatchIds = Collections.<String>emptyList();
		}

		_installablePatchIds = installablePatchIds;
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
	public void setPatchIdsStatuses(Map<String, Integer> patchIdsStatuses) {
		if (patchIdsStatuses == null) {
			patchIdsStatuses = Collections.<String, Integer>emptyMap();
		}

		_patchIdsStatuses = patchIdsStatuses;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private String _error;
	private List<String> _fixedIssues = Collections.<String>emptyList();
	private String _hashCode;
	private List<String> _installablePatchIds = Collections.<String>emptyList();
	private String _key;
	private Date _modifiedDate;
	private boolean _new;
	private Map<String, Integer> _patchIdsStatuses =
		Collections.<String, Integer>emptyMap();
	private String _uuid;

}