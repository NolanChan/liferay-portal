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

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryImpl;
import com.liferay.osb.lcs.service.base.LCSNotificationAuditEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSNotificationAuditEntryLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSNotificationAuditEntryLocalServiceUtil
 */
@ProviderType
public class LCSNotificationAuditEntryLocalServiceImpl
	extends LCSNotificationAuditEntryLocalServiceBaseImpl {

	@Override
	public LCSNotificationAuditEntry addLCSNotificationAuditEntry(
			long userId, long lcsClusterNodeId, int type)
		throws PortalException {

		LCSNotificationAuditEntry lcsNotificationAuditEntry =
			new LCSNotificationAuditEntryImpl();

		lcsNotificationAuditEntry.setLcsNotificationAuditEntryId(
			counterLocalService.increment(
				LCSNotificationAuditEntry.class.getName()));
		lcsNotificationAuditEntry.setUserId(userId);
		lcsNotificationAuditEntry.setCreateDate(new Date());
		lcsNotificationAuditEntry.setLcsClusterNodeId(lcsClusterNodeId);
		lcsNotificationAuditEntry.setType(type);

		return lcsNotificationAuditEntryPersistence.update(
			lcsNotificationAuditEntry);
	}

	@Override
	public List<LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
			int start, int end, boolean details)
		throws PortalException {

		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			lcsNotificationAuditEntryPersistence.findAll(start, end);

		if (details) {
			lcsNotificationAuditEntries = getLCSNotificationAuditEntries(
				lcsNotificationAuditEntries);
		}

		return lcsNotificationAuditEntries;
	}

	@Override
	public List<LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
			long lcsProjectId, long lcsClusterEntryId, long lcsClusterNodeId)
		throws PortalException {

		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries = null;

		if (lcsClusterNodeId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			lcsNotificationAuditEntries =
				lcsNotificationAuditEntryPersistence.findByLCSClusterNodeId(
					lcsClusterNodeId);
		}
		else if (lcsClusterEntryId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodePersistence.findByLCSClusterEntryId(
					lcsClusterEntryId);

			long[] lcsClusterNodeIds = new long[lcsClusterNodes.size()];

			for (int i = 0; i < lcsClusterNodes.size(); i++) {
				LCSClusterNode lcsClusterNode = lcsClusterNodes.get(i);

				lcsClusterNodeIds[i] = lcsClusterNode.getLcsClusterNodeId();
			}

			lcsNotificationAuditEntries =
				lcsNotificationAuditEntryPersistence.findByLCSClusterNodeId(
					lcsClusterNodeIds);
		}
		else {
			List<LCSClusterEntry> lcsClusterEntries =
				lcsClusterEntryPersistence.findByLCSProjectId(lcsProjectId);

			long[] lcsClusterEntryIds = new long[lcsClusterEntries.size()];

			for (int i = 0; i < lcsClusterEntries.size(); i++) {
				LCSClusterEntry lcsClusterEntry = lcsClusterEntries.get(i);

				lcsClusterEntryIds[i] = lcsClusterEntry.getLcsClusterEntryId();
			}

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodePersistence.findByLCSClusterEntryId(
					lcsClusterEntryIds);

			long[] lcsClusterNodeIds = new long[lcsClusterNodes.size()];

			for (int i = 0; i < lcsClusterNodes.size(); i++) {
				LCSClusterNode lcsClusterNode = lcsClusterNodes.get(i);

				lcsClusterNodeIds[i] = lcsClusterNode.getLcsClusterNodeId();
			}

			lcsNotificationAuditEntries =
				lcsNotificationAuditEntryPersistence.findByLCSClusterNodeId(
					lcsClusterNodeIds);
		}

		return lcsNotificationAuditEntries;
	}

	@Override
	public List<LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
			long corpProjectId, long lcsClusterEntryId, long lcsClusterNodeId,
			boolean details)
		throws PortalException {

		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			getLCSNotificationAuditEntries(
				corpProjectId, lcsClusterEntryId, lcsClusterNodeId);

		if (details) {
			lcsNotificationAuditEntries = getLCSNotificationAuditEntries(
				lcsNotificationAuditEntries);
		}

		return lcsNotificationAuditEntries;
	}

	@Override
	public List<LCSNotificationAuditEntry> getUserLCSNotificationAuditEntries(
			long userId, long lcsClusterNodeId, int type)
		throws PortalException {

		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			lcsNotificationAuditEntryPersistence.findByU_LCNI_T(
				userId, lcsClusterNodeId, type);

		return lcsNotificationAuditEntries;
	}

	protected List<LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
			List<LCSNotificationAuditEntry> lcsNotificationAuditEntries)
		throws PortalException {

		for (LCSNotificationAuditEntry lcsNotificationAuditEntry :
				lcsNotificationAuditEntries) {

			LCSClusterNode lcsClusterNode =
				lcsClusterNodePersistence.findByPrimaryKey(
					lcsNotificationAuditEntry.getLcsClusterNodeId());

			LCSClusterEntry lcsClusterEntry =
				lcsClusterEntryPersistence.findByPrimaryKey(
					lcsClusterNode.getLcsClusterEntryId());

			LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
				lcsClusterEntry.getLcsProjectId());

			lcsNotificationAuditEntry.setLcsProjectName(lcsProject.getName());

			lcsNotificationAuditEntry.setLcsClusterEntryName(
				lcsClusterEntry.getName());
			lcsNotificationAuditEntry.setLcsClusterNodeName(
				lcsClusterNode.getName());
			lcsNotificationAuditEntry.setLcsClusterNodeKey(
				lcsClusterNode.getKey());

			User user = userLocalService.getUser(
				lcsNotificationAuditEntry.getUserId());

			lcsNotificationAuditEntry.setUserEmailAddress(
				user.getEmailAddress());
		}

		return lcsNotificationAuditEntries;
	}

}