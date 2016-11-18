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

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public interface LCSMetadataDetails {

	public int getBuildNumber();

	public String getGitTag();

	public String getPortalEdition();

	public Map<String, String> getPortalProperties();

	public Map<String, String> getProperties();

	public boolean isNew();

	public void setBuildNumber(int buildNumber);

	public void setGitTag(String gitTag);

	public void setNew(boolean n);

	public void setPortalEdition(String portalEdition);

	public void setPortalProperties(Map<String, String> portalProperties);

	public void setProperties(Map<String, String> properties);

}