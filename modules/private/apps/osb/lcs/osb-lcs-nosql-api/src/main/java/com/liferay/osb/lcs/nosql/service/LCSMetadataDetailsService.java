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

package com.liferay.osb.lcs.nosql.service;

import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public interface LCSMetadataDetailsService {

	public LCSMetadataDetails addLCSMetadataDetails(
		int buildNumber, Map<String, String> generalProperties, String gitTag,
		String portalEdition, Map<String, String> portalProperties);

	public LCSMetadataDetails fetchLCSMetadataDetails(
		int buildNumber, String gitTag, String portalEdition);

	public LCSMetadataDetails updateLCSMetadataDetails(
		LCSMetadataDetails lcsMetadataDetails);

}