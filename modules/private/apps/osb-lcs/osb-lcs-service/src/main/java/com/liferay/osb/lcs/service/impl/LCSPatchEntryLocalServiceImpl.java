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

import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.model.LCSPatchEntry;
import com.liferay.osb.lcs.service.base.LCSPatchEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service for accessing, adding, and updating LCS patch
 * entries.
 *
 * <p>
 * LCS holds information about every patch issued by the Liferay Support Team
 * since Liferay 6.2. An LCS patch entry holds patch information such as its
 * name, the compatible patching tool's build number, and the target portal
 * instance's build number.
 * </p>
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 0.1
 */
@ProviderType
public class LCSPatchEntryLocalServiceImpl
	extends LCSPatchEntryLocalServiceBaseImpl {

	/**
	 * Adds a new LCS patch entry.
	 *
	 * @param  patchId the patch ID specified by Liferay support
	 * @param  name the patch name
	 * @param  description the patch description
	 * @param  patchingToolVersion the minimum version of the patching tool
	 *         compatible with this patch
	 * @param  incremental whether the patch is incremental
	 * @param  singular whether the patch is singular
	 * @param  version the patch version
	 * @param  size
	 * @param  rank the patch rank. This is necessary during the install phase
	 *         to determine the chronology of the fixes.
	 * @param  requirements the patch requirements. This is only applicable when
	 *         the patch isn't singular.
	 * @param  component the patch component. Examples include hotfix, portal,
	 *         and collaboration.
	 * @param  compatibleBuild the build number of the portal instance
	 *         compatible with this patch. This is used for patches to Liferay
	 *         6.1.20 and older.
	 * @param  product the build number of the portal instance compatible with
	 *         this patch. This is used for patches to Liferay versions newer
	 *         than 6.1.20.
	 * @param  fixedIssues the comma delimited list of JIRA ticket IDs addressed
	 *         in the patch
	 * @param  moduleName the portal module name fixed by the patch
	 * @param  moduleId the portal module ID
	 * @param  tunnelWeb whether the patch requires the Tunnel Web plugin. This
	 *         isn't used in patches newer than 6.1.20.
	 * @param  buildDate the patch's build date
	 * @param  builtFor the name of the customer the patch was built for
	 * @return the new LCS patch entry
	 * @since  LCS 0.1
	 */
	@Override
	public LCSPatchEntry addLCSPatchEntry(
		String patchId, String name, String description,
		int patchingToolVersion, boolean incremental, boolean singular,
		int version, long size, long rank, String requirements,
		String component, String compatibleBuild, String product,
		String fixedIssues, String moduleName, String moduleId,
		boolean tunnelWeb, Date buildDate, String builtFor) {

		long lcsPatchEntryId = counterLocalService.increment();

		LCSPatchEntry lcsPatchEntry = createLCSPatchEntry(lcsPatchEntryId);

		lcsPatchEntry.setPatchId(patchId);
		lcsPatchEntry.setName(name);
		lcsPatchEntry.setDescription(description);
		lcsPatchEntry.setPatchingToolVersion(patchingToolVersion);
		lcsPatchEntry.setIncremental(incremental);
		lcsPatchEntry.setSingular(singular);
		lcsPatchEntry.setVersion(version);
		lcsPatchEntry.setSize(size);
		lcsPatchEntry.setRank(rank);
		lcsPatchEntry.setRequirements(requirements);
		lcsPatchEntry.setComponent(StringUtil.toLowerCase(component));
		lcsPatchEntry.setCompatibleBuild(compatibleBuild);
		lcsPatchEntry.setProduct(product);
		lcsPatchEntry.setFixedIssues(fixedIssues);
		lcsPatchEntry.setModuleName(moduleName);
		lcsPatchEntry.setModuleId(moduleId);
		lcsPatchEntry.setTunnelWeb(tunnelWeb);
		lcsPatchEntry.setBuildDate(buildDate);
		lcsPatchEntry.setBuiltFor(builtFor);

		lcsPatchEntry = lcsPatchEntryPersistence.update(lcsPatchEntry);

		updateSupportedPatchingTool(patchingToolVersion, product);

		return lcsPatchEntry;
	}

	/**
	 * Returns all LCS patch entries matching the patching tool version and
	 * product.
	 *
	 * @param  patchingToolVersion the patching tool version
	 * @param  product the portal instance build number
	 * @return all LCS patch entries matching the patching tool version and
	 *         product
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSPatchEntry> getLCSPatchEntries(
		int patchingToolVersion, String product) {

		return lcsPatchEntryPersistence.findByPTV_Product(
			patchingToolVersion, product);
	}

	/**
	 * Returns the LCS patch entry matching the patch ID.
	 *
	 * @param  patchId the patch ID defined by Liferay support
	 * @return the LCS patch entry matching the patch ID
	 * @since  LCS 0.1
	 */
	@Override
	public LCSPatchEntry getLCSPatchEntry(String patchId)
		throws PortalException {

		return lcsPatchEntryPersistence.findByPatchId(patchId);
	}

	/**
	 * Updates all LCS metadata compatible with the patching tool version and
	 * product.
	 *
	 * <p>
	 * This method updates only LCS metadata in which the current supported
	 * patching tool version is less than the patching tool version.
	 * </p>
	 *
	 * @param patchingToolVersion the patching tool version
	 * @param product the portal instance build number
	 * @since LCS 1.1
	 */
	protected void updateSupportedPatchingTool(
		int patchingToolVersion, String product) {

		List<LCSMetadata> lcsMetadatas = lcsMetadataPersistence.findByBN_PE(
			GetterUtil.getInteger(product), "EE");

		for (LCSMetadata lcsMetadata : lcsMetadatas) {
			if (patchingToolVersion <= lcsMetadata.getSupportedPatchingTool()) {
				continue;
			}

			lcsMetadataLocalService.updateSupportedPatchingTool(
				lcsMetadata.getLcsMetadataId(), patchingToolVersion);
		}
	}

}