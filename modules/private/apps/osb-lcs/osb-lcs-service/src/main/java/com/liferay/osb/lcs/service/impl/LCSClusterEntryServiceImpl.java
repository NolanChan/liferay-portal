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

import com.liferay.osb.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.service.base.LCSClusterEntryServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSClusterEntryServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil
 */
@ProviderType
public class LCSClusterEntryServiceImpl extends LCSClusterEntryServiceBaseImpl {

	@Override
	public LCSClusterEntry addLCSClusterEntry(
			long lcsProjectId, String name, String description, String location,
			String subscriptionType, int type)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.addLCSClusterEntry(
			lcsProjectId, name, description, location, subscriptionType, type);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntry deleteLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		LCSProjectPermission.check(
			getPermissionChecker(), lcsClusterEntry.getLcsProjectId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.deleteLCSClusterEntry(
			lcsClusterEntryId);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public void deleteLCSProjectClusters(long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		lcsClusterEntryLocalService.deleteLCSProjectClusters(lcsProjectId);
	}

	@Override
	public byte[] exportLCSClusterEntryToken(
			long lcsProjectId, String lcsClusterEntryName,
			String subscriptionType, int type)
		throws PortalException {

		OSBLCSConfiguration configuration = getConfiguration();

		if (configuration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException();
		}

		LCSClusterEntry lcsClusterEntry = addLCSClusterEntry(
			lcsProjectId, lcsClusterEntryName, lcsClusterEntryName,
			lcsClusterEntryName, subscriptionType, type);

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenService.addLCSClusterEntryToken(
				lcsClusterEntry.getLcsClusterEntryId(),
				new HashMap<String, String>());

		try {
			return _lcsClusterEntryTokenAdvisor.getLCSEntryTokenEncryptedBytes(
				lcsClusterEntryToken);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public List<LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
			long lcsProjectId, String subscriptionType)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getArchivedLCSProjectLCSClusterEntries(
				lcsProjectId, subscriptionType);

		return filterLCSClusterEntries(lcsClusterEntries);
	}

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntry.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsClusterEntry;
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
			long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		return filterLCSClusterEntries(lcsClusterEntries);
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
			long lcsProjectId, String subscriptionType)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getLCSProjectLCSClusterEntries(
				lcsProjectId, subscriptionType);

		return filterLCSClusterEntries(lcsClusterEntries);
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectManageableLCSClusterEntries(
			long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		return filterLCSClusterEntries(
			lcsClusterEntries, OSBLCSActionKeys.MANAGE_ENTRY);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries()
		throws PortalException {

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getUserLCSClusterEntries(getUserId());

		return filterLCSClusterEntries(lcsClusterEntries);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries(long lcsProjectId)
		throws PortalException {

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryLocalService.getUserLCSClusterEntries(
				getUserId(), lcsProjectId);

		return filterLCSClusterEntries(lcsClusterEntries);
	}

	@Override
	public LCSClusterEntry updateElastic(
			long lcsClusterEntryId, boolean elastic)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.updateElastic(
			lcsClusterEntryId, elastic);
	}

	@Override
	public LCSClusterEntry updateHighPageLoadTime(
			long lcsClusterEntryId, int highPageLoadTime)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.updateHighPageLoadTime(
			lcsClusterEntryId, highPageLoadTime);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterEntry updateLCSClusterEntry(
			long lcsClusterEntryId, String name, String description,
			String location)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.updateLCSClusterEntry(
			lcsClusterEntryId, name, description, null, location);
	}

	@Override
	public LCSClusterEntry updateMediumPageLoadTime(
			long lcsClusterEntryId, int mediumPageLoadTime)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.updateMediumPageLoadTime(
			lcsClusterEntryId, mediumPageLoadTime);
	}

	@Override
	public LCSClusterEntry updateSubscriptionType(
			long lcsClusterEntryId, String subscriptionType)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterEntryLocalService.updateSubscriptionType(
			lcsClusterEntryId, subscriptionType);
	}

	protected List<LCSClusterEntry> filterLCSClusterEntries(
			List<LCSClusterEntry> lcsClusterEntries)
		throws PrincipalException {

		return filterLCSClusterEntries(
			lcsClusterEntries, OSBLCSActionKeys.VIEW);
	}

	protected List<LCSClusterEntry> filterLCSClusterEntries(
			List<LCSClusterEntry> lcsClusterEntries, String actionId)
		throws PrincipalException {

		List<LCSClusterEntry> filteredLCSClusterEntries = new ArrayList<>();

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			if (LCSClusterEntryPermission.contains(
					getPermissionChecker(), lcsClusterEntry, actionId)) {

				filteredLCSClusterEntries.add(lcsClusterEntry);
			}
		}

		return filteredLCSClusterEntries;
	}

	protected OSBLCSConfiguration getConfiguration()
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			OSBLCSConfiguration.class, 0);
	}

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

	@ServiceReference(type = LCSClusterEntryTokenAdvisor.class)
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

}