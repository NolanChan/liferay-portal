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

import com.liferay.osb.lcs.model.LCSNotification;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for LCSNotification. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSNotificationServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSNotificationServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSNotificationServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSNotification"}, service = LCSNotificationService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSNotificationService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSNotificationServiceUtil} to access the l c s notification remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSNotificationServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

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
	public LCSNotification addLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, boolean enabled, int type)
		throws PortalException;

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
	public LCSNotification addLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, boolean enabled, int type)
		throws PortalException;

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
	public LCSNotification addLCSProjectLCSNotification(long lcsProjectId,
		boolean enabled, int type) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, int type) throws PortalException;

	/**
	* Returns the LCS notification for the LCS cluster node and type.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, int type) throws PortalException;

	/**
	* Returns the LCS notification for the LCS project and type.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @param type the type of notification as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSNotification fetchLCSProjectLCSNotification(long lcsProjectId,
		int type) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns all LCS notifications for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the parent LCS cluster entry
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSNotification> getLCSClusterEntryLCSNotifications(
		long lcsClusterEntryId) throws PortalException;

	/**
	* Returns all LCS notifications for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSNotification> getLCSClusterNodeLCSNotifications(
		long lcsClusterNodeId) throws PortalException;

	/**
	* Returns all LCS notifications.
	*
	* @return all LCS notifications
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSNotification> getLCSNotifications()
		throws PortalException;

	/**
	* Returns all LCS notifications for the LCS project.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSNotification> getLCSProjectLCSNotifications(
		long lcsProjectId) throws PortalException;

	/**
	* Deletes the LCS notification.
	*
	* @param lcsNotificationId primary key of the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	public void deleteLCSNotification(long lcsNotificationId)
		throws PortalException;

	/**
	* Deletes all LCS notifications.
	*
	* @throws PortalException if a portal exception occurred
	*/
	public void deleteLCSNotifications() throws PortalException;

	/**
	* Updates the LCS notification if notification user matches the current
	* user
	*
	* @param lcsNotification LCS notification with new values
	* @throws PortalException if any of the arguments were invalid
	*/
	public void updateLCSNotification(LCSNotification lcsNotification)
		throws PortalException;
}