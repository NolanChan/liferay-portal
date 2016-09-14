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

package com.liferay.osb.lcs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.service.LCSNotificationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSNotificationServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSNotificationSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSNotification}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSNotificationSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationServiceHttp
 * @see com.liferay.osb.lcs.model.LCSNotificationSoap
 * @see LCSNotificationServiceUtil
 * @generated
 */
@ProviderType
public class LCSNotificationServiceSoap {
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap addLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, boolean enabled, int type)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.addLCSClusterEntryLCSNotification(lcsClusterEntryId,
					enabled, type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap addLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, boolean enabled, int type)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.addLCSClusterNodeLCSNotification(lcsClusterNodeId,
					enabled, type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap addLCSProjectLCSNotification(
		long lcsProjectId, boolean enabled, int type) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.addLCSProjectLCSNotification(lcsProjectId,
					enabled, type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Deletes the LCS notification.
	*
	* @param lcsNotificationId primary key of the LCS notification
	* @throws PortalException if any of the arguments were invalid
	*/
	public static void deleteLCSNotification(long lcsNotificationId)
		throws RemoteException {
		try {
			LCSNotificationServiceUtil.deleteLCSNotification(lcsNotificationId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Deletes all LCS notifications.
	*
	* @throws PortalException if a portal exception occurred
	*/
	public static void deleteLCSNotifications() throws RemoteException {
		try {
			LCSNotificationServiceUtil.deleteLCSNotifications();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap fetchLCSClusterEntryLCSNotification(
		long lcsClusterEntryId, int type) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.fetchLCSClusterEntryLCSNotification(lcsClusterEntryId,
					type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap fetchLCSClusterNodeLCSNotification(
		long lcsClusterNodeId, int type) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.fetchLCSClusterNodeLCSNotification(lcsClusterNodeId,
					type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSNotificationSoap fetchLCSProjectLCSNotification(
		long lcsProjectId, int type) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSNotification returnValue = LCSNotificationServiceUtil.fetchLCSProjectLCSNotification(lcsProjectId,
					type);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS notifications for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the parent LCS cluster entry
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationSoap[] getLCSClusterEntryLCSNotifications(
		long lcsClusterEntryId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSNotification> returnValue =
				LCSNotificationServiceUtil.getLCSClusterEntryLCSNotifications(lcsClusterEntryId);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS notifications for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the parent LCS cluster node
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationSoap[] getLCSClusterNodeLCSNotifications(
		long lcsClusterNodeId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSNotification> returnValue =
				LCSNotificationServiceUtil.getLCSClusterNodeLCSNotifications(lcsClusterNodeId);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS notifications.
	*
	* @return all LCS notifications
	* @throws PortalException if a portal exception occurred
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationSoap[] getLCSNotifications()
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSNotification> returnValue =
				LCSNotificationServiceUtil.getLCSNotifications();

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns all LCS notifications for the LCS project.
	*
	* @param lcsProjectId the primary key of the parent LCS project
	* @return the matching LCS notifications
	* @throws PortalException if any of the arguments were invalid
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationSoap[] getLCSProjectLCSNotifications(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSNotification> returnValue =
				LCSNotificationServiceUtil.getLCSProjectLCSNotifications(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSNotificationSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Updates the LCS notification if notification user matches the current
	* user
	*
	* @param lcsNotification LCS notification with new values
	* @throws PortalException if any of the arguments were invalid
	*/
	public static void updateLCSNotification(
		com.liferay.osb.lcs.model.LCSNotificationSoap lcsNotification)
		throws RemoteException {
		try {
			LCSNotificationServiceUtil.updateLCSNotification(com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl.toModel(
					lcsNotification));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSNotificationServiceSoap.class);
}