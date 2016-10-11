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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.model.impl.LCSMetadataImpl;
import com.liferay.osb.lcs.service.base.LCSMetadataLocalServiceBaseImpl;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import java.util.List;

/**
 * Provides the local service for accessing, adding, and updating LCS metadata.
 *
 * <p>
 * LCS metadata holds information about a particular portal release. This
 * includes build number, portal edition, the branch the portal release was
 * built from, and the availability index. The availability index is a bit mask
 * based indicator for additional information in the NoSQL database.
 * </p>
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.1
 */
@ProviderType
public class LCSMetadataLocalServiceImpl
	extends LCSMetadataLocalServiceBaseImpl {

	/**
	 * Adds new LCS metadata.
	 *
	 * @param  buildNumber the portal instance's build number
	 * @param  gitTag the Git tag of the portal instance's source code
	 * @param  portalEdition the portal instance's edition
	 * @param  supportedLCSPortlet the latest supported LCS portlet's build
	 *         number
	 * @param  supportedPatchingTool the latest supported patching tool's build
	 *         number
	 * @return the LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata addLCSMetadata(
		int buildNumber, String gitTag, String portalEdition,
		int supportedLCSPortlet, int supportedPatchingTool) {

		LCSMetadata lcsMetadata = new LCSMetadataImpl();

		lcsMetadata.setLcsMetadataId(
			counterLocalService.increment(LCSMetadata.class.getName()));

		lcsMetadata.setBuildNumber(buildNumber);
		lcsMetadata.setGitTag(gitTag);
		lcsMetadata.setPortalEdition(portalEdition);
		lcsMetadata.setSupportedLCSPortlet(supportedLCSPortlet);
		lcsMetadata.setSupportedPatchingTool(supportedPatchingTool);

		return lcsMetadataPersistence.update(lcsMetadata);
	}

	/**
	 * Returns the LCS metadata matching the build number, Git tag, and portal
	 * edition.
	 *
	 * @param  buildNumber the portal instance's build number
	 * @param  gitTag the Git tag of the portal instance's source code
	 * @param  portalEdition the portal instance's edition
	 * @return the matching LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata fetchLCSMetadata(
		int buildNumber, String gitTag, String portalEdition) {

		return lcsMetadataPersistence.fetchByBN_GT_PE(
			buildNumber, gitTag, portalEdition);
	}

	/**
	 * Returns all LCS metadata that match the build number and portal edition.
	 *
	 * @param  buildNumber the portal instance's build number
	 * @param  portalEdition the portal instance's edition
	 * @return the matching LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public List<LCSMetadata> getLCSMetadatas(
		int buildNumber, String portalEdition) {

		return lcsMetadataPersistence.findByBN_PE(buildNumber, portalEdition);
	}

	/**
	 * Updates the availability index bit mask for the LCS metadata that matches
	 * the LCS metadata ID.
	 *
	 * <p>
	 * If the availability index {@link
	 * com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex} bit is turned on,
	 * a particular service is available either within the LCS metadata or
	 * {@link com.liferay.osb.lcs.nosql.model.LCSMetadataDetails}. Use {@link
	 * com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex#merge(long)} to
	 * encode the bit mask, and {@link
	 * com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex#isAvailable(long)}
	 * to decode it.
	 * </p>
	 *
	 * @param  lcsMetadataId the primary key of the LCS metadata
	 * @param  availabilityIndex the bit mask
	 * @return the updated LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata updateAvailabilityIndex(
		long lcsMetadataId, long availabilityIndex) {

		LCSMetadata lcsMetadata = lcsMetadataPersistence.fetchByPrimaryKey(
			lcsMetadataId);

		lcsMetadata.setAvailabilityIndex(availabilityIndex);

		return lcsMetadataPersistence.update(lcsMetadata);
	}

	/**
	 * Throws a new unsupported operation exception, to forbid updating an
	 * entire LCS metadata object.
	 *
	 * <p>
	 * Allowed update methods are {@link #updateAvailabilityIndex(long, long)},
	 * {@link #updateSupportedLCSPortlet(long, int)}, and {@link
	 * #updateSupportedLCSPortlet(long, int)}.
	 * </p>
	 *
	 * @param  lcsMetadata the LCS metadata
	 * @return the LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata updateLCSMetadata(LCSMetadata lcsMetadata) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Updates the supported LCS portlet build number in the LCS metadata
	 * matching the primary key.
	 *
	 * @param  lcsMetadataId the primary key of the LCS metadata
	 * @param  supportedLCSPortlet the supported LCS portlet's build number
	 * @return the updated LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata updateSupportedLCSPortlet(
		long lcsMetadataId, int supportedLCSPortlet) {

		LCSMetadata lcsMetadata = lcsMetadataPersistence.fetchByPrimaryKey(
			lcsMetadataId);

		if (supportedLCSPortlet > lcsMetadata.getSupportedLCSPortlet()) {
			Message message = new Message();

			message.put("portalBuildNumber", lcsMetadata.getBuildNumber());
			message.put("portalEdition", lcsMetadata.getPortalEdition());

			message.setPayload(LCSEventType.NEW_LCS_PORTLET_AVAILABLE);

			MessageBusUtil.sendMessage("liferay/osb_lcs_events", message);
		}

		lcsMetadata.setSupportedLCSPortlet(supportedLCSPortlet);

		return lcsMetadataPersistence.update(lcsMetadata);
	}

	/**
	 * Updates the latest supported portal patching tool's build number in the
	 * LCS metadata matching the primary key.
	 *
	 * @param  lcsMetadataId the primary key of the LCS metadata
	 * @param  supportedPatchingToolVersion the latest supported patching tool's
	 *         build number
	 * @return the updated LCS metadata
	 * @since  LCS 1.1
	 */
	@Override
	public LCSMetadata updateSupportedPatchingTool(
		long lcsMetadataId, int supportedPatchingToolVersion) {

		LCSMetadata lcsMetadata = lcsMetadataPersistence.fetchByPrimaryKey(
			lcsMetadataId);

		if (supportedPatchingToolVersion >
				lcsMetadata.getSupportedPatchingTool()) {

			Message message = new Message();

			message.put("portalBuildNumber", lcsMetadata.getBuildNumber());
			message.put("portalEdition", lcsMetadata.getPortalEdition());

			message.setPayload(LCSEventType.NEW_PATCHING_TOOL_AVAILABLE);

			MessageBusUtil.sendMessage("liferay/osb_lcs_events", message);
		}

		lcsMetadata.setSupportedPatchingTool(supportedPatchingToolVersion);

		return lcsMetadataPersistence.update(lcsMetadata);
	}

}