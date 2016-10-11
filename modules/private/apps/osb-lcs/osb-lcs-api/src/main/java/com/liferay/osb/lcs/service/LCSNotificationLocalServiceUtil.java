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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LCSNotification. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSNotificationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSNotificationLocalService
 * @see com.liferay.osb.lcs.service.base.LCSNotificationLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSNotificationLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSNotificationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSNotificationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s notification to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was added
	*/
	public static com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return getService().addLCSNotification(lcsNotification);
	}

	/**
	* Adds an LCS notification for the LCS cluster entry.
	*
	* @param userId the primary key of the user
	* @param lcsClusterEntry the notification's LCS cluster entry
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		long userId, com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry,
		boolean enabled, int type) {
		return getService()
				   .addLCSNotification(userId, lcsClusterEntry, enabled, type);
	}

	/**
	* Adds an LCS notification for the LCS cluster node.
	*
	* @param userId the primary key of the user
	* @param lcsClusterNode the notification's LCS cluster node
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		long userId, com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode,
		boolean enabled, int type) {
		return getService()
				   .addLCSNotification(userId, lcsClusterNode, enabled, type);
	}

	/**
	* Adds an LCS notification for the LCS project.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the notification's LCS project
	* @param enabled whether to enable the notification
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification addLCSProjectLCSNotification(
		long userId, long lcsProjectId, boolean enabled, int type) {
		return getService()
				   .addLCSProjectLCSNotification(userId, lcsProjectId, enabled,
			type);
	}

	/**
	* Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	*
	* @param lcsNotificationId the primary key for the new l c s notification
	* @return the new l c s notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification createLCSNotification(
		long lcsNotificationId) {
		return getService().createLCSNotification(lcsNotificationId);
	}

	/**
	* Deletes the l c s notification from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSNotification deleteLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return getService().deleteLCSNotification(lcsNotification);
	}

	/**
	* Deletes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification that was removed
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSNotification deleteLCSNotification(
		long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSNotification(lcsNotificationId);
	}

	/**
	* Returns the LCS notification of the user, LCS cluster entry, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsClusterEntryId the primary key of the notification's LCS
	cluster entry
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterEntryLCSNotification(
		long userId, long lcsClusterEntryId, int type) {
		return getService()
				   .fetchLCSClusterEntryLCSNotification(userId,
			lcsClusterEntryId, type);
	}

	/**
	* Returns the LCS notification of the user, LCS cluster node, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsClusterNodeId the primary key of the notification's LCS
	cluster node
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterNodeLCSNotification(
		long userId, long lcsClusterNodeId, int type) {
		return getService()
				   .fetchLCSClusterNodeLCSNotification(userId,
			lcsClusterNodeId, type);
	}

	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSNotification(
		long lcsNotificationId) {
		return getService().fetchLCSNotification(lcsNotificationId);
	}

	/**
	* Returns the LCS notification for the user, LCS project, and LCS
	* notification type.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the notification's LCS project
	* @param type the notification type as defined in {@link
	com.liferay.osb.lcs.notifications.util.LCSEventType}
	* @return the matching LCS notification
	*/
	public static com.liferay.osb.lcs.model.LCSNotification fetchLCSProjectLCSNotification(
		long userId, long lcsProjectId, int type) {
		return getService()
				   .fetchLCSProjectLCSNotification(userId, lcsProjectId, type);
	}

	/**
	* Returns the l c s notification with the primary key.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSNotification getLCSNotification(
		long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSNotification(lcsNotificationId);
	}

	/**
	* Updates the l c s notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSNotification updateLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return getService().updateLCSNotification(lcsNotification);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s notifications.
	*
	* @return the number of l c s notifications
	*/
	public static int getLCSNotificationsCount() {
		return getService().getLCSNotificationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of l c s notifications
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSNotifications(
		int start, int end) {
		return getService().getLCSNotifications(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Deletes the LCS notification for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	*/
	public static void deleteLCSClusterEntryLCSNotification(
		long lcsClusterEntryId) {
		getService().deleteLCSClusterEntryLCSNotification(lcsClusterEntryId);
	}

	/**
	* Deletes the LCS notification for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	*/
	public static void deleteLCSClusterNodeLCSNotification(
		long lcsClusterNodeId) {
		getService().deleteLCSClusterNodeLCSNotification(lcsClusterNodeId);
	}

	/**
	* Deletes the LCS notification for the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	*/
	public static void deleteLCSProjectLCSNotification(long lcsProjectId) {
		getService().deleteLCSProjectLCSNotification(lcsProjectId);
	}

	/**
	* Deletes the LCS notifications of the user.
	*
	* @param userId the primary key of the user
	*/
	public static void deleteUserLCSNotifications(long userId) {
		getService().deleteUserLCSNotifications(userId);
	}

	/**
	* Deletes the LCS notifications of the user, for the name and primary key
	* of the class.
	*
	* @param userId the primary key of the user
	* @param classNameValue the class name
	* @param classPK the primary key for the object of class
	*/
	public static void deleteUserLCSNotifications(long userId,
		java.lang.String classNameValue, long classPK) {
		getService().deleteUserLCSNotifications(userId, classNameValue, classPK);
	}

	public static LCSNotificationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSNotificationLocalService, LCSNotificationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSNotificationLocalService.class);
}