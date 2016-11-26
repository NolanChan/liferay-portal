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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSMetadataDetailsPersistence;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = LCSMetadataDetailsService.class)
public class LCSMetadataDetailsServiceImpl
	implements LCSMetadataDetailsService {

	@Override
	public LCSMetadataDetails addLCSMetadataDetails(
		int buildNumber, Map<String, String> generalProperties, String gitTag,
		String portalEdition, Map<String, String> portalProperties) {

		LCSMetadataDetails lcsMetadataDetails =
			_lcsMetadataDetailsPersistence.create();

		lcsMetadataDetails.setBuildNumber(buildNumber);
		lcsMetadataDetails.setProperties(generalProperties);
		lcsMetadataDetails.setGitTag(gitTag);
		lcsMetadataDetails.setPortalEdition(portalEdition);
		lcsMetadataDetails.setPortalProperties(portalProperties);

		return _lcsMetadataDetailsPersistence.update(lcsMetadataDetails);
	}

	@Override
	public LCSMetadataDetails fetchLCSMetadataDetails(
		int buildNumber, String gitTag, String portalEdition) {

		return _lcsMetadataDetailsPersistence.fetchByBN_GT_PE(
			buildNumber, gitTag, portalEdition);
	}

	public void setLCSMetadataDetailsPersistence(
		LCSMetadataDetailsPersistence lcsMetadataDetailsPersistence) {

		_lcsMetadataDetailsPersistence = lcsMetadataDetailsPersistence;
	}

	@Override
	public LCSMetadataDetails updateLCSMetadataDetails(
		LCSMetadataDetails lcsMetadataDetails) {

		_lcsMetadataDetailsPersistence.update(lcsMetadataDetails);

		return lcsMetadataDetails;
	}

	private LCSMetadataDetailsPersistence _lcsMetadataDetailsPersistence;

}