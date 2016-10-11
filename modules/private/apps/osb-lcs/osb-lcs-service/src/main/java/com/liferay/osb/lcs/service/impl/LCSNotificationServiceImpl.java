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

import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.base.LCSNotificationServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.util.List;

/**
 * Provides the remote service for accessing, adding, deleting, and updating LCS
 * notifications.
 *
 * @author Igor Beslic
 */
@JSONWebService(mode = JSONWebServiceMode.IGNORE)
@ProviderType
public class LCSNotificationServiceImpl extends LCSNotificationServiceBaseImpl {

	/**
	 * Adds an LCS notification for the LCS Cluster Entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the parent LCS cluster entry
	 * @param  enabled whether the notification is enabled
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification addLCSClusterEntryLCSNotification(
			long lcsClusterEntryId, boolean enabled, int type)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntry, OSBLCSActionKeys.VIEW);

		return lcsNotificationLocalService.addLCSNotification(
			getUserId(), lcsClusterEntry, enabled, type);
	}

	/**
	 * Adds an LCS notification for the LCS cluster node.
	 *
	 * @param  lcsClusterNodeId the primary key of the parent LCS cluster node
	 * @param  enabled whether the notification is enabled
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification addLCSClusterNodeLCSNotification(
			long lcsClusterNodeId, boolean enabled, int type)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsNotificationLocalService.addLCSNotification(
			getUserId(), lcsClusterNode, enabled, type);
	}

	/**
	 * Adds an LCS notification for the LCS project.
	 *
	 * @param  lcsProjectId the primary key of the parent LCS project
	 * @param  enabled whether the notification is enabled
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification addLCSProjectLCSNotification(
			long lcsProjectId, boolean enabled, int type)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsNotificationLocalService.addLCSProjectLCSNotification(
			getUserId(), lcsProjectId, enabled, type);
	}

	/**
	 * Deletes the LCS notification.
	 *
	 * @param  lcsNotificationId primary key of the LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public void deleteLCSNotification(long lcsNotificationId)
		throws PortalException {

		LCSNotification lcsNotification =
			lcsNotificationPersistence.findByPrimaryKey(lcsNotificationId);

		if (lcsNotification.getUserId() == getUserId()) {
			lcsNotificationPersistence.remove(lcsNotification);
		}
	}

	/**
	 * Deletes all LCS notifications.
	 *
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public void deleteLCSNotifications() throws PortalException {
		for (LCSNotification lcsNotification : getLCSNotifications()) {
			deleteLCSNotification(lcsNotification.getLcsNotificationId());
		}
	}

	/**
	 * Returns the LCS notification for the LCS cluster entry and type.
	 *
	 * @param  lcsClusterEntryId lcsClusterEntryId the primary key of the parent
	 *         LCS cluster entry
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification fetchLCSClusterEntryLCSNotification(
			long lcsClusterEntryId, int type)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntry, OSBLCSActionKeys.VIEW);

		return lcsNotificationPersistence.fetchByU_C_C_T(
			getUserId(),
			classNameLocalService.getClassNameId(LCSClusterEntry.class),
			lcsClusterEntryId, type);
	}

	/**
	 * Returns the LCS notification for the LCS cluster node and type.
	 *
	 * @param  lcsClusterNodeId the primary key of the parent LCS cluster node
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification fetchLCSClusterNodeLCSNotification(
			long lcsClusterNodeId, int type)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsNotificationPersistence.fetchByU_C_C_T(
			getUserId(),
			classNameLocalService.getClassNameId(LCSClusterNode.class),
			lcsClusterNodeId, type);
	}

	/**
	 * Returns the LCS notification for the LCS project and type.
	 *
	 * @param  lcsProjectId the primary key of the parent LCS project
	 * @param  type the type of notification as defined in {@link
	 *         com.liferay.osb.lcs.notifications.util.LCSEventType}
	 * @return the matching LCS notification
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public LCSNotification fetchLCSProjectLCSNotification(
			long lcsProjectId, int type)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsNotificationPersistence.fetchByU_C_C_T(
			getUserId(), classNameLocalService.getClassNameId(LCSProject.class),
			lcsProjectId, type);
	}

	/**
	 * Returns all LCS notifications for the LCS cluster entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the parent LCS cluster entry
	 * @return the matching LCS notifications
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public List<LCSNotification> getLCSClusterEntryLCSNotifications(
			long lcsClusterEntryId)
		throws PortalException {

		return lcsNotificationPersistence.findByU_C_C(
			getUserId(),
			classNameLocalService.getClassNameId(LCSClusterEntry.class),
			lcsClusterEntryId);
	}

	/**
	 * Returns all LCS notifications for the LCS cluster node.
	 *
	 * @param  lcsClusterNodeId the primary key of the parent LCS cluster node
	 * @return the matching LCS notifications
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public List<LCSNotification> getLCSClusterNodeLCSNotifications(
			long lcsClusterNodeId)
		throws PortalException {

		return lcsNotificationPersistence.findByU_C_C(
			getUserId(),
			classNameLocalService.getClassNameId(LCSClusterNode.class),
			lcsClusterNodeId);
	}

	/**
	 * Returns all LCS notifications.
	 *
	 * @return all LCS notifications
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public List<LCSNotification> getLCSNotifications() throws PortalException {
		return lcsNotificationPersistence.findByUserId(getUserId());
	}

	/**
	 * Returns all LCS notifications for the LCS project.
	 *
	 * @param  lcsProjectId the primary key of the parent LCS project
	 * @return the matching LCS notifications
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public List<LCSNotification> getLCSProjectLCSNotifications(
			long lcsProjectId)
		throws PortalException {

		return lcsNotificationPersistence.findByU_C_C(
			getUserId(), classNameLocalService.getClassNameId(LCSProject.class),
			lcsProjectId);
	}

	/**
	 * Updates the LCS notification if notification user matches the current
	 * user
	 *
	 * @param  lcsNotification LCS notification with new values
	 * @throws PortalException if any of the arguments were invalid
	 */
	@Override
	public void updateLCSNotification(LCSNotification lcsNotification)
		throws PortalException {

		if (lcsNotification.getUserId() == getUserId()) {
			lcsNotificationPersistence.update(lcsNotification);
		}
	}

}