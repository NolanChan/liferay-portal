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

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSPatchEntry;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.base.LCSPatchingAdvisorServiceBaseImpl;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the remote service for accessing and filtering LCS patch entries.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 0.1
 */
@ProviderType
public class LCSPatchingAdvisorServiceImpl
	extends LCSPatchingAdvisorServiceBaseImpl {

	/**
	 * Returns a list of patch IDs for patches that can be installed in the LCS
	 * cluster node.
	 *
	 * <p>
	 * For a particular portal build number and patching tool version, this
	 * method compares available patches to those already installed in the LCS
	 * cluster node. First, the patches already installed are removed from the
	 * list of available patches. Dependency requirements for the remaining
	 * available patches are then checked. If these requirements are met and
	 * multiple versions of the same patch component are available, a third
	 * check is performed that selects the highest patch component version.
	 * </p>
	 *
	 * @param  lcsClusterNode the LCS cluster node instance
	 * @param  patchingToolVersion the patching tool version of the LCS cluster
	 *         node
	 * @param  installedPatchIds the IDs of the patches already installed in the
	 *         LCS cluster node
	 * @return the IDs of the patches that are safe to install in the LCS
	 *         cluster node
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<String> getInstallablePatchIds(
		LCSClusterNode lcsClusterNode, int patchingToolVersion,
		String[] installedPatchIds) {

		Map<String, String> installablePatchIds = new HashMap<>();

		List<LCSPatchEntry> lcsPatchEntries =
			lcsPatchEntryLocalService.getLCSPatchEntries(
				patchingToolVersion,
				String.valueOf(lcsClusterNode.getBuildNumber()));

		for (LCSPatchEntry lcsPatchEntry : lcsPatchEntries) {
			if (lcsPatchEntry.isSingular()) {
				lcsPatchEntries = filterLCSPatchEntries(lcsPatchEntries);

				break;
			}
		}

		for (LCSPatchEntry lcsPatchEntry : lcsPatchEntries) {
			if (hasPatchEntry(installedPatchIds, lcsPatchEntry)) {
				continue;
			}

			if (!hasRequirements(
					installedPatchIds, lcsPatchEntry.getRequirements())) {

				continue;
			}

			if (!hasAllFixedIssues(lcsClusterNode, lcsPatchEntry)) {
				continue;
			}

			String component = lcsPatchEntry.getComponent();

			if (!installablePatchIds.containsKey(component)) {
				installablePatchIds.put(component, lcsPatchEntry.getPatchId());
			}
			else {
				if (lcsPatchEntry.getVersion() >
						getVersion(
							installablePatchIds.get(component), component)) {

					installablePatchIds.put(
						component, lcsPatchEntry.getPatchId());
				}
			}
		}

		return new ArrayList<>(installablePatchIds.values());
	}

	/**
	 * Returns a list of patch IDs for patches that can be installed in the LCS
	 * cluster node matching the specified portal key.
	 *
	 * <p>
	 * For a particular portal build number and patching tool version, this
	 * method compares available patches to those already installed in the LCS
	 * cluster node. First, the patches already installed are removed from the
	 * list of available patches. Dependency requirements for the remaining
	 * available patches are then checked. If these requirements are met and
	 * multiple versions of the same patch component are available, a third
	 * check is performed that selects the highest patch component version.
	 * </p>
	 *
	 * @param  key the portal key of the LCS cluster node
	 * @param  patchingToolVersion the patching tool version of the LCS cluster
	 *         node
	 * @param  installedPatchIds the IDs of the patches already installed in the
	 *         LCS cluster node
	 * @return the IDs of the patches that are safe to install in the LCS
	 *         cluster node
	 * @since  LCS 0.1
	 */
	@Override
	public List<String> getInstallablePatchIds(
		String key, int patchingToolVersion, String[] installedPatchIds) {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodeLocalService.getLCSClusterNode(key);

		return getInstallablePatchIds(
			lcsClusterNode, patchingToolVersion, installedPatchIds);
	}

	/**
	 * Returns the singular LCS patch entries from the list of LCS patch
	 * entries.
	 *
	 * <p>
	 * This method checks the LCS patch entries specified and returns only the
	 * singular patches.
	 * </p>
	 *
	 * @param  lcsPatchEntries the LCS patch entries to check
	 * @return the singular LCS patch entries
	 * @since  LCS 0.1
	 */
	protected List<LCSPatchEntry> filterLCSPatchEntries(
		List<LCSPatchEntry> lcsPatchEntries) {

		List<LCSPatchEntry> filteredLCSPatchEntries = new ArrayList<>();

		for (LCSPatchEntry lcsPatchEntry : lcsPatchEntries) {
			if (lcsPatchEntry.isSingular()) {
				filteredLCSPatchEntries.add(lcsPatchEntry);
			}
		}

		return filteredLCSPatchEntries;
	}

	/**
	 * Returns the patch version ID encoded in the patch ID.
	 *
	 * @param  patchId the patch ID
	 * @param  component the portal component
	 * @return the patch version ID
	 * @since  LCS 0.1
	 */
	protected int getVersion(String patchId, String component) {
		return GetterUtil.getInteger(
			patchId.substring(
				component.length() + 1,
				patchId.indexOf(StringPool.DASH, component.length() + 1)));
	}

	protected boolean hasAllFixedIssues(
		LCSClusterNode lcsClusterNode, LCSPatchEntry lcsPatchEntry) {

		LCSClusterNodePatches lcsClusterNodePatches =
			_lcsClusterNodePatchesService.fetchLCSClusterNodePatches(
				lcsClusterNode.getKey());

		if (lcsClusterNodePatches == null) {
			return false;
		}

		List<String> lcsClusterNodeFixedIssues = new ArrayList<>(
			lcsClusterNodePatches.getFixedIssues());

		String lcsPatchEntryFixedIssuesString = lcsPatchEntry.getFixedIssues();

		String[] lcsPatchEntryFixedIssues =
			lcsPatchEntryFixedIssuesString.split(",");

		for (String fixedIssue : lcsPatchEntryFixedIssues) {
			lcsClusterNodeFixedIssues.remove(fixedIssue);
		}

		if (lcsClusterNodeFixedIssues.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the patch entry is accounted for in the
	 * installed patch IDs.
	 *
	 * <p>
	 * This method returns <code>true</code> if the installed patch IDs
	 * reference the patch entry's component and if a component version of the
	 * installed patches is greater than or equal to the component version of
	 * the specified patch entry
	 * </p>
	 *
	 * @param  installedPatchIds the IDs of the installed patches
	 * @param  lcsPatchEntry the LCS patch entry
	 * @return <code>true</code> if the installed patch IDs have a patch defined
	 *         within the LCS patch entry; <code>false</code> otherwise
	 * @since  LCS 0.1
	 */
	protected boolean hasPatchEntry(
		String[] installedPatchIds, LCSPatchEntry lcsPatchEntry) {

		return hasPatchEntry(
			installedPatchIds, lcsPatchEntry.getComponent(),
			lcsPatchEntry.getVersion());
	}

	/**
	 * Returns <code>true</code> if a patch entry for the specified component
	 * and patch version is accounted for in the installed patch IDs.
	 *
	 * <p>
	 * This method returns <code>true</code> if the installed patch IDs contain
	 * the ID of the patch that has the specified component in its name and if
	 * that component's version is greater than or equal to the version
	 * specified.
	 * </p>
	 *
	 * @param  installedPatchIds the patch IDs
	 * @param  component the name of the portal component
	 * @param  version the version of the portal component
	 * @return <code>true</code> if the installed patch IDs have the patch ID
	 *         for the specified component and version; <code>false</code>
	 *         otherwise
	 * @since  LCS 0.1
	 */
	protected boolean hasPatchEntry(
		String[] installedPatchIds, String component, int version) {

		for (String patchId : installedPatchIds) {
			if (!patchId.startsWith(component)) {
				continue;
			}

			int installedPatchVersion = getVersion(patchId, component);

			if (installedPatchVersion < version) {
				return false;
			}
			else {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the patching requirements are satisfied by
	 * the patches specified by the installed patch IDs.
	 *
	 * <p>
	 * This method matches each requirement against each patch referenced by the
	 * specified patch IDs. If the patches satisfy all the requirements, this
	 * method returns <code>true</code>.
	 * </p>
	 *
	 * <p>
	 * Example: If the requirement is <code>portal-admin>3</code>, this method
	 * traverses all patch IDs and checks if the corresponding patches have the
	 * portal-admin component version <code>3</code> or greater.
	 * </p>
	 *
	 * @param  installedPatchIds the list of installed patch IDs
	 * @param  requirements the comma-delimited requirements expression
	 * @return <code>true</code> if requirements are satisfied;
	 *         <code>false</code> otherwise
	 * @since  LCS 0.1
	 */
	protected boolean hasRequirements(
		String[] installedPatchIds, String requirements) {

		if (Validator.isNull(requirements)) {
			return true;
		}

		String[] requirementsArray = StringUtil.split(requirements);

		for (String requirement : requirementsArray) {
			if (!requirement.contains(StringPool.GREATER_THAN_OR_EQUAL)) {
				return false;
			}

			String requiredComponent = requirement.substring(
				0, requirement.indexOf(StringPool.GREATER_THAN_OR_EQUAL));

			int requiredLeastVersion = GetterUtil.getInteger(
				requirement.substring(
					requiredComponent.length() +
						StringPool.GREATER_THAN_OR_EQUAL.length()));

			if (!hasPatchEntry(
					installedPatchIds, requiredComponent,
					requiredLeastVersion)) {

				return false;
			}
		}

		return true;
	}

	@ServiceReference(type = LCSClusterNodePatchesService.class)
	private LCSClusterNodePatchesService _lcsClusterNodePatchesService;

}