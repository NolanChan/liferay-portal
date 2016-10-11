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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LCSNotificationService}.
 *
 * @author Igor Beslic
 * @see LCSNotificationService
 * @generated
 */
@ProviderType
public class LCSNotificationServiceWrapper implements LCSNotificationService,
	ServiceWrapper<LCSNotificationService> {
	public LCSNotificationServiceWrapper(
		LCSNotificationService lcsNotificationService) {
		_lcsNotificationService = lcsNotificationService;
	}

	/**
	* Adds an LCS notification for the LCS Cluster Entry.
	*
	* @param lcsClusterEntryId the primary key of the parent LCS cluster entry
	* @param enabled whether the notification is enabled
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, boolean enabled, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.addLCSClusterEntryLCSNotification(lcsClusterEntryId,
			enabled, type);
	}

	/**
	* Adds an LCS notification for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @param enabled whether the notification is enabled
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, boolean enabled, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.addLCSClusterNodeLCSNotification(lcsClusterNodeId,
			enabled, type);
	}

	/**
	* Adds an LCS notification for the LCS project.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @param enabled whether the notification is enabled
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSProjectLCSNotification(
		long lcsProjectId, boolean enabled, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.addLCSProjectLCSNotification(lcsProjectId,
			enabled, type);
	}

	/**
	* Returns the LCS notification for the LCS cluster entry and type.
	*
	* @param lcsClusterEntryId lcsClusterEntryId the primary key of the parent
	LCS cluster entry
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.fetchLCSClusterEntryLCSNotification(lcsClusterEntryId,
			type);
	}

	/**
	* Returns the LCS notification for the LCS cluster node and type.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.fetchLCSClusterNodeLCSNotification(lcsClusterNodeId,
			type);
	}

	/**
	* Returns the LCS notification for the LCS project and type.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSProjectLCSNotification(
		long lcsProjectId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.fetchLCSProjectLCSNotification(lcsProjectId,
			type);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsNotificationService.getOSGiServiceIdentifier();
	}

	/**
	* Returns all LCS notifications for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the parent LCS cluster entry
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSClusterEntryLCSNotifications(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.getLCSClusterEntryLCSNotifications(lcsClusterEntryId);
	}

	/**
	* Returns all LCS notifications for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSClusterNodeLCSNotifications(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.getLCSClusterNodeLCSNotifications(lcsClusterNodeId);
	}

	/**
	* Returns all LCS notifications.
	*
	* @return all LCS notifications
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSNotifications()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.getLCSNotifications();
	}

	/**
	* Returns all LCS notifications for the LCS project.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSProjectLCSNotifications(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationService.getLCSProjectLCSNotifications(lcsProjectId);
	}

	/**
	* Deletes the LCS notification.
	*
	* @param lcsNotificationId primary key of the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public void deleteLCSNotification(long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsNotificationService.deleteLCSNotification(lcsNotificationId);
	}

	/**
	* Deletes all LCS notifications.
	*
	* @throws PortalException if a portal exception occurred
	*/
	@Override
	public void deleteLCSNotifications()
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsNotificationService.deleteLCSNotifications();
	}

	/**
	* Updates the LCS notification if notification user matches the current
	* user
	*
	* @param lcsNotification LCS notification with new values
	* @throws PortalException if any of the arguments were invalid
	*/
	@Override
	public void updateLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsNotificationService.updateLCSNotification(lcsNotification);
	}

	@Override
	public LCSNotificationService getWrappedService() {
		return _lcsNotificationService;
	}

	@Override
	public void setWrappedService(LCSNotificationService lcsNotificationService) {
		_lcsNotificationService = lcsNotificationService;
	}

	private LCSNotificationService _lcsNotificationService;
}