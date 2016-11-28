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

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.service.base.LCSSubscriptionEntryServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSSubscriptionEntryServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSSubscriptionEntryServiceUtil
 */
@ProviderType
public class LCSSubscriptionEntryServiceImpl
	extends LCSSubscriptionEntryServiceBaseImpl {

	@Override
	public void addCorpProjectLCSSubscriptionEntries(
			long corpProjectId, String lcsSubscriptionEntriesJSON)
		throws PortalException {

		LCSProject lcsProject = lcsProjectLocalService.fetchByCorpProject(
			corpProjectId);

		if (lcsProject == null) {
			lcsProject = lcsProjectLocalService.addLCSProject(corpProjectId);
		}

		addLCSSubscriptionEntries(
			lcsProject.getLcsProjectId(), lcsSubscriptionEntriesJSON);
	}

	@Override
	public void addLCSSubscriptionEntries(
			long lcsProjectId, String lcsSubscriptionEntriesJSON)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		lcsSubscriptionEntryLocalService.addLCSSubscriptionEntries(
			lcsProjectId, lcsSubscriptionEntriesJSON);
	}

	@Override
	public LCSSubscriptionEntry fetchLCSClusterNodeActiveLCSSubscriptionEntry(
			String key)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.fetchByKey(
			key);

		if (lcsClusterNode == null) {
			return null;
		}

		LCSClusterEntryPermission.check(
			permissionChecker, lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE);

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(
				lcsClusterNode.getLcsClusterEntryId());

		SubscriptionType lcsClusterEntrySubscriptionType =
			SubscriptionType.valueOf(lcsClusterEntry.getSubscriptionType());

		LCSSubscriptionEntry lcsClusterEntryLCSSubscriptionEntry =
			lcsSubscriptionEntryLocalService.
				fetchLCSProjectLCSSubscriptionEntry(
					lcsClusterEntry.getLcsProjectId(),
					lcsClusterNode.getProcessorCoresTotal(),
					lcsClusterEntrySubscriptionType);

		if (lcsClusterEntryLCSSubscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No LCS subscription entries");
			}
		}

		return lcsClusterEntryLCSSubscriptionEntry;
	}

	@Override
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
			long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsSubscriptionEntryLocalService.
			getLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	@Override
	public List<LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
			long lcsProjectId, boolean status)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsSubscriptionEntryLocalService.
			getLCSProjectLCSSubscriptionEntries(lcsProjectId, status);
	}

	@Override
	public boolean hasLCSProjectElasticLCSSubscriptionEntry(long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsSubscriptionEntryLocalService.
			hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries()
		throws PortalException {

		if (!getPermissionChecker().isCompanyAdmin()) {
			throw new PrincipalException();
		}

		lcsSubscriptionEntryLocalService.
			refreshLCSProjectLCSSubscriptionEntries();
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		lcsSubscriptionEntryLocalService.
			refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSSubscriptionEntryServiceImpl.class);

}