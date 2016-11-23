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

import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class LCSMetadataDetailsImpl implements LCSMetadataDetails {

	@Override
	public int getBuildNumber() {
		return _buildNumber;
	}

	@Override
	public String getGitTag() {
		return _gitTag;
	}

	@Override
	public String getPortalEdition() {
		return _portalEdition;
	}

	@Override
	public Map<String, String> getPortalProperties() {
		return _portalProperties;
	}

	@Override
	public Map<String, String> getProperties() {
		return _properties;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	@Override
	public void setGitTag(String gitTag) {
		_gitTag = gitTag;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	@Override
	public void setPortalProperties(Map<String, String> portalProperties) {
		_portalProperties = portalProperties;
	}

	@Override
	public void setProperties(Map<String, String> properties) {
		_properties = properties;
	}

	private int _buildNumber;
	private String _gitTag;
	private boolean _new;
	private String _portalEdition;
	private Map<String, String> _portalProperties;
	private Map<String, String> _properties;

}