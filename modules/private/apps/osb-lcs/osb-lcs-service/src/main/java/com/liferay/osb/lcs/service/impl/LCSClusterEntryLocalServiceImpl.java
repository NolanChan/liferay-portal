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
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSClusterEntryConstants;
import com.liferay.osb.lcs.exception.DuplicateLCSClusterEntryNameException;
import com.liferay.osb.lcs.exception.RequiredLCSClusterEntryNameException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.base.LCSClusterEntryLocalServiceBaseImpl;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Beslic
 * @see    LCSClusterEntryLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil
 */
@ProviderType
public class LCSClusterEntryLocalServiceImpl
	extends LCSClusterEntryLocalServiceBaseImpl {

	@Override
	public LCSClusterEntry addLCSClusterEntry(
			long lcsProjectId, String name, String description,
			int highPageLoadTime, String location, int mediumPageLoadTime,
			String subscriptionType, int type)
		throws PortalException {

		validate(lcsProjectId, name);

		long lcsClusterEntryId = counterLocalService.increment();

		LCSClusterEntry lcsClusterEntry = createLCSClusterEntry(
			lcsClusterEntryId);

		lcsClusterEntry.setLcsProjectId(lcsProjectId);
		lcsClusterEntry.setName(name);
		lcsClusterEntry.setDescription(description);
		lcsClusterEntry.setHighPageLoadTime(highPageLoadTime);
		lcsClusterEntry.setLocation(location);
		lcsClusterEntry.setMediumPageLoadTime(mediumPageLoadTime);
		lcsClusterEntry.setSubscriptionType(subscriptionType);
		lcsClusterEntry.setType(type);

		checkDefaults(lcsClusterEntry);

		return lcsClusterEntryPersistence.update(lcsClusterEntry);
	}

	@Override
	public LCSClusterEntry addLCSClusterEntry(
			long lcsProjectId, String name, String description, String location,
			String subscriptionType, int type)
		throws PortalException {

		return addLCSClusterEntry(
			lcsProjectId, name, description,
			LCSClusterEntryConstants.HIGH_PAGE_LOAD_TIME_DEFAULT, location,
			LCSClusterEntryConstants.MEDIUM_PAGE_LOAD_TIME_DEFAULT,
			subscriptionType, type);
	}

	@Override
	public LCSClusterEntry deleteLCSClusterEntry(
			LCSClusterEntry lcsClusterEntry)
		throws PortalException {

		lcsClusterNodeLocalService.deleteLCSClusterEntryLCSClusterNodes(
			lcsClusterEntry.getLcsClusterEntryId());

		if (!lcsClusterEntry.isElastic()) {
			lcsClusterEntry = lcsClusterEntryPersistence.remove(
				lcsClusterEntry);
		}
		else {
			lcsClusterEntry.setArchived(true);

			lcsClusterEntry = lcsClusterEntryPersistence.update(
				lcsClusterEntry);
		}

		List<LCSInvitation> lcsInvitations =
			lcsInvitationPersistence.findByLCSProjectId(
				lcsClusterEntry.getLcsProjectId());

		for (LCSInvitation lcsInvitation : lcsInvitations) {
			if (lcsClusterEntry.getLcsClusterEntryId() !=
					lcsInvitation.getLcsClusterEntryId()) {

				continue;
			}

			lcsInvitationPersistence.remove(lcsInvitation);
		}

		lcsMessageLocalService.deleteLCSClusterEntryLCSMessages(
			lcsClusterEntry.getLcsClusterEntryId());

		lcsNotificationLocalService.deleteLCSClusterEntryLCSNotification(
			lcsClusterEntry.getLcsClusterEntryId());

		List<LCSRole> lcsRoles = lcsRolePersistence.findByLCSClusterEntryId(
			lcsClusterEntry.getLcsClusterEntryId());

		for (LCSRole lcsRole : lcsRoles) {
			lcsRolePersistence.remove(lcsRole);
		}

		LCSClusterEntryToken lcsClusterEntryToken =
			lcsClusterEntryTokenPersistence.fetchByLCSClusterEntryId(
				lcsClusterEntry.getLcsClusterEntryId());

		if (lcsClusterEntryToken != null) {
			lcsClusterEntryTokenPersistence.remove(lcsClusterEntryToken);
		}

		return lcsClusterEntry;
	}

	@Override
	public LCSClusterEntry deleteLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		return deleteLCSClusterEntry(lcsClusterEntry);
	}

	@Override
	public void deleteLCSProjectClusters(long lcsProjectId)
		throws PortalException {

		List<LCSClusterEntry> lcsClusterEntries =
			lcsClusterEntryPersistence.findByLCSProjectId(lcsProjectId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			deleteLCSClusterEntry(lcsClusterEntry);
		}
	}

	@Override
	public List<LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, String subscriptionType) {

		return lcsClusterEntryPersistence.findByLPI_ST_A(
			lcsProjectId, subscriptionType, true);
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId) {

		return getLCSProjectLCSClusterEntries(
			lcsProjectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, int start, int end,
		OrderByComparator orderByComparator) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSClusterEntry.class, getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("lcsProjectId", lcsProjectId));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", false));

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public List<LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, String subscriptionType) {

		return lcsClusterEntryPersistence.findByLPI_ST_A(
			lcsProjectId, subscriptionType, false);
	}

	@Override
	public long[] getLCSProjectLCSClusterEntryIds(long lcsProjectId) {
		List<LCSClusterEntry> lcsClusterEntries =
			getLCSProjectLCSClusterEntries(lcsProjectId);

		long[] lcsClusterEntryIds = new long[lcsClusterEntries.size()];

		for (int i = 0; i < lcsClusterEntries.size(); i++) {
			LCSClusterEntry lcsClusterEntry = lcsClusterEntries.get(i);

			lcsClusterEntryIds[i] = lcsClusterEntry.getLcsClusterEntryId();
		}

		return lcsClusterEntryIds;
	}

	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries(long userId) {
		return getUserLCSClusterEntries(userId, 0);
	}

	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries(
		long userId, long lcsProjectId) {

		long[] lcsClusterEntryIds = getUserLCSClusterEntryIds(
			userId, lcsProjectId);

		if (lcsClusterEntryIds.length == 0) {
			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSClusterEntry.class, getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"lcsClusterEntryId", ArrayUtil.toArray(lcsClusterEntryIds)));

		if (lcsProjectId > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("lcsProjectId", lcsProjectId));
		}

		dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", false));

		return dynamicQuery(dynamicQuery);
	}

	@Override
	public long[] getUserLCSClusterEntryIds(long userId, long lcsProjectId) {
		Set<Long> lcsClusterEntryIdsSet = new HashSet<>();

		List<LCSRole> lcsRoles = lcsRolePersistence.findByUserId(userId);

		for (LCSRole lcsRole : lcsRoles) {
			if ((lcsProjectId > 0) &&
				(lcsProjectId != lcsRole.getLcsProjectId())) {

				continue;
			}

			if (lcsRole.getLcsClusterEntryId() == 0) {
				long[] lcsClusterEntryIds = getLCSProjectLCSClusterEntryIds(
					lcsRole.getLcsProjectId());

				for (long lcsClusterEntryId : lcsClusterEntryIds) {
					lcsClusterEntryIdsSet.add(lcsClusterEntryId);
				}
			}
			else {
				lcsClusterEntryIdsSet.add(lcsRole.getLcsClusterEntryId());
			}
		}

		return ArrayUtil.toArray(
			lcsClusterEntryIdsSet.toArray(
				new Long[lcsClusterEntryIdsSet.size()]));
	}

	@Override
	public LCSClusterEntry updateElastic(
			long lcsClusterEntryId, boolean elastic)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		if (lcsClusterEntry.isElastic() ||
			(SubscriptionType.valueOf(
				lcsClusterEntry.getSubscriptionType()) ==
					SubscriptionType.UNDEFINED)) {

			throw new UnsupportedOperationException();
		}

		if (lcsClusterNodePersistence.countByLCSClusterEntryId(
				lcsClusterEntryId) != 0) {

			throw new UnsupportedOperationException();
		}

		lcsClusterEntry.setElastic(elastic);

		return lcsClusterEntryPersistence.update(lcsClusterEntry);
	}

	@Override
	public LCSClusterEntry updateHighPageLoadTime(
			long lcsClusterEntryId, int highPageLoadTime)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		lcsClusterEntry.setHighPageLoadTime(highPageLoadTime);

		return lcsClusterEntryPersistence.update(lcsClusterEntry);
	}

	@Override
	public LCSClusterEntry updateLCSClusterEntry(
		LCSClusterEntry lcsClusterEntry) {

		OSBLCSConfiguration configuration;

		try {
			configuration = getConfiguration();
		}
		catch (ConfigurationException ce) {
			throw new SystemException("Configuration is not available.", ce);
		}

		if (configuration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException();
		}

		return lcsClusterEntryPersistence.update(lcsClusterEntry);
	}

	@Override
	public LCSClusterEntry updateLCSClusterEntry(
			long lcsClusterEntryId, String name, String description,
			String subscriptionType, String location)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		if (!StringUtil.equalsIgnoreCase(lcsClusterEntry.getName(), name)) {
			validate(lcsClusterEntry.getLcsProjectId(), name);
		}

		lcsClusterEntry.setName(name);
		lcsClusterEntry.setDescription(description);
		lcsClusterEntry.setLocation(location);

		lcsClusterEntry = lcsClusterEntryPersistence.update(lcsClusterEntry);

		return updateSubscriptionType(lcsClusterEntry, subscriptionType);
	}

	@Override
	public LCSClusterEntry updateMediumPageLoadTime(
			long lcsClusterEntryId, int mediumPageLoadTime)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		lcsClusterEntry.setMediumPageLoadTime(mediumPageLoadTime);

		return lcsClusterEntryPersistence.update(lcsClusterEntry);
	}

	@Override
	public LCSClusterEntry updateSubscriptionType(
			long lcsClusterEntryId, String subscriptionType)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		return updateSubscriptionType(lcsClusterEntry, subscriptionType);
	}

	protected void checkDefaults(LCSClusterEntry lcsClusterEntry) {
		if (Validator.isNull(lcsClusterEntry.getSubscriptionType())) {
			lcsClusterEntry.setSubscriptionType(
				SubscriptionType.UNDEFINED.name());
		}
	}

	protected OSBLCSConfiguration getConfiguration()
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			OSBLCSConfiguration.class, 0);
	}

	protected LCSClusterEntry updateSubscriptionType(
			LCSClusterEntry lcsClusterEntry, String subscriptionType)
		throws PortalException {

		SubscriptionType newSubscriptionType = SubscriptionType.UNDEFINED;

		if (Validator.isNotNull(subscriptionType)) {
			newSubscriptionType = SubscriptionType.valueOf(subscriptionType);
		}

		if (newSubscriptionType == SubscriptionType.UNDEFINED) {
			return lcsClusterEntry;
		}

		SubscriptionType curSubscriptionType = SubscriptionType.valueOf(
			lcsClusterEntry.getSubscriptionType());

		if (curSubscriptionType != SubscriptionType.UNDEFINED) {
			throw new UnsupportedOperationException(
				"LCS cluster environment has defined subscription");
		}

		boolean hasInactiveLCSClusterEntryAllLCSClusterNodes =
			lcsClusterNodeLocalService.
				hasLCSClusterEntryAllInactiveLCSClusterNodes(
					lcsClusterEntry.getLcsClusterEntryId());

		if (!hasInactiveLCSClusterEntryAllLCSClusterNodes) {
			throw new UnsupportedOperationException(
				"LCS cluster environment has active nodes");
		}

		lcsClusterEntry.setSubscriptionType(subscriptionType);

		lcsClusterEntry = lcsClusterEntryPersistence.update(lcsClusterEntry);

		lcsProjectLocalService.updateSubscriptionActive(
			lcsClusterEntry.getLcsProjectId(), true);

		return lcsClusterEntry;
	}

	protected void validate(long lcsProjectId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new RequiredLCSClusterEntryNameException();
		}

		int lcsProjectLCSClusterEntriesCount =
			lcsClusterEntryPersistence.countByLPI_N_A(
				lcsProjectId, name, false);

		if (lcsProjectLCSClusterEntriesCount > 0) {
			throw new DuplicateLCSClusterEntryNameException();
		}
	}

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

}