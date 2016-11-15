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

package com.liferay.osb.lcs.admin.internal.advisor;

import com.liferay.osb.lcs.admin.internal.util.LCSMetadataAvailabilityIndex;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsService;
import com.liferay.osb.lcs.service.LCSMetadataLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
@Component(immediate = true)
public class LCSMetadataDetailsAdvisor {

	public LCSMetadataDetails addLCSMetadataDetails(
			int buildNumber, Map<String, String> generalProperties,
			String gitTag, String portalEdition,
			Map<String, String> portalProperties)
		throws SystemException {

		LCSMetadataDetails lcsMetadataDetails =
			_lcsMetadataDetailsService.addLCSMetadataDetails(
				buildNumber, generalProperties, gitTag, portalEdition,
				portalProperties);

		LCSMetadata lcsMetadata =
			_lcsMetadataLocalService.fetchLCSMetadata(
				buildNumber, gitTag, portalEdition);

		long availabilityIndex =
			LCSMetadataAvailabilityIndex.PORTAL_PROPERTIES_AVAILABLE.merge(
				lcsMetadata.getAvailabilityIndex());

		_lcsMetadataLocalService.updateAvailabilityIndex(
			lcsMetadata.getLcsMetadataId(), availabilityIndex);

		return lcsMetadataDetails;
	}

	public void updateLCSMetadataDetailsPortletProperties(
			long lcsMetadataId, Map<String, String> portalProperties)
		throws PortalException, SystemException {

		LCSMetadata lcsMetadata = _lcsMetadataLocalService.getLCSMetadata(
			lcsMetadataId);

		LCSMetadataDetails lcsMetadataDetails = fetchLCSMetadataDetails(
			lcsMetadata.getBuildNumber(), lcsMetadata.getGitTag(),
			lcsMetadata.getPortalEdition());

		if (lcsMetadataDetails != null) {
			lcsMetadataDetails.setPortalProperties(portalProperties);

			updateLCSMetadataDetails(lcsMetadataDetails);
		}
		else {
			addLCSMetadataDetails(
				lcsMetadata.getBuildNumber(), new HashMap<String, String>(),
				lcsMetadata.getGitTag(), lcsMetadata.getPortalEdition(),
				portalProperties);
		}
	}

	public LCSMetadataDetails fetchLCSMetadataDetails(
		int buildNumber, String gitTag, String portalEdition) {

		return _lcsMetadataDetailsService.fetchLCSMetadataDetails(
			buildNumber, gitTag, portalEdition);
	}

	public void updateLCSMetadataDetails(
			LCSMetadataDetails lcsMetadataDetails)
		throws SystemException {

		_lcsMetadataDetailsService.updateLCSMetadataDetails(
			lcsMetadataDetails);
	}

	@Reference(unbind = "-")
	protected void setLCSMetadataDetailsService(
		LCSMetadataDetailsService lcsMetadataDetailsService) {

		_lcsMetadataDetailsService = lcsMetadataDetailsService;
	}

	@Reference(unbind = "-")
	protected void setLCSMetadataLocalService(
		LCSMetadataLocalService lcsMetadataLocalService) {

		_lcsMetadataLocalService = lcsMetadataLocalService;
	}

	private LCSMetadataDetailsService _lcsMetadataDetailsService;
	private LCSMetadataLocalService _lcsMetadataLocalService;

}
