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
 * Provides a wrapper for {@link LCSNotificationLocalService}.
 *
 * @author Igor Beslic
 * @see LCSNotificationLocalService
 * @generated
 */
@ProviderType
public class LCSNotificationLocalServiceWrapper
	implements LCSNotificationLocalService,
		ServiceWrapper<LCSNotificationLocalService> {
	public LCSNotificationLocalServiceWrapper(
		LCSNotificationLocalService lcsNotificationLocalService) {
		_lcsNotificationLocalService = lcsNotificationLocalService;
	}

	/**
	* Adds the l c s notification to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return _lcsNotificationLocalService.addLCSNotification(lcsNotification);
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		long userId, com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry,
		boolean enabled, int type) {
		return _lcsNotificationLocalService.addLCSNotification(userId,
			lcsClusterEntry, enabled, type);
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSNotification(
		long userId, com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode,
		boolean enabled, int type) {
		return _lcsNotificationLocalService.addLCSNotification(userId,
			lcsClusterNode, enabled, type);
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification addLCSProjectLCSNotification(
		long userId, long lcsProjectId, boolean enabled, int type) {
		return _lcsNotificationLocalService.addLCSProjectLCSNotification(userId,
			lcsProjectId, enabled, type);
	}

	/**
	* Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	*
	* @param lcsNotificationId the primary key for the new l c s notification
	* @return the new l c s notification
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification createLCSNotification(
		long lcsNotificationId) {
		return _lcsNotificationLocalService.createLCSNotification(lcsNotificationId);
	}

	/**
	* Deletes the l c s notification from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification deleteLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return _lcsNotificationLocalService.deleteLCSNotification(lcsNotification);
	}

	/**
	* Deletes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification that was removed
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification deleteLCSNotification(
		long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationLocalService.deleteLCSNotification(lcsNotificationId);
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterEntryLCSNotification(
		long userId, long lcsClusterEntryId, int type) {
		return _lcsNotificationLocalService.fetchLCSClusterEntryLCSNotification(userId,
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSClusterNodeLCSNotification(
		long userId, long lcsClusterNodeId, int type) {
		return _lcsNotificationLocalService.fetchLCSClusterNodeLCSNotification(userId,
			lcsClusterNodeId, type);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSNotification(
		long lcsNotificationId) {
		return _lcsNotificationLocalService.fetchLCSNotification(lcsNotificationId);
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
	@Override
	public com.liferay.osb.lcs.model.LCSNotification fetchLCSProjectLCSNotification(
		long userId, long lcsProjectId, int type) {
		return _lcsNotificationLocalService.fetchLCSProjectLCSNotification(userId,
			lcsProjectId, type);
	}

	/**
	* Returns the l c s notification with the primary key.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification
	* @throws PortalException if a l c s notification with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification getLCSNotification(
		long lcsNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationLocalService.getLCSNotification(lcsNotificationId);
	}

	/**
	* Updates the l c s notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsNotification the l c s notification
	* @return the l c s notification that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotification updateLCSNotification(
		com.liferay.osb.lcs.model.LCSNotification lcsNotification) {
		return _lcsNotificationLocalService.updateLCSNotification(lcsNotification);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsNotificationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsNotificationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsNotificationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s notifications.
	*
	* @return the number of l c s notifications
	*/
	@Override
	public int getLCSNotificationsCount() {
		return _lcsNotificationLocalService.getLCSNotificationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsNotificationLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsNotificationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _lcsNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _lcsNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotification> getLCSNotifications(
		int start, int end) {
		return _lcsNotificationLocalService.getLCSNotifications(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsNotificationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _lcsNotificationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Deletes the LCS notification for the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	*/
	@Override
	public void deleteLCSClusterEntryLCSNotification(long lcsClusterEntryId) {
		_lcsNotificationLocalService.deleteLCSClusterEntryLCSNotification(lcsClusterEntryId);
	}

	/**
	* Deletes the LCS notification for the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	*/
	@Override
	public void deleteLCSClusterNodeLCSNotification(long lcsClusterNodeId) {
		_lcsNotificationLocalService.deleteLCSClusterNodeLCSNotification(lcsClusterNodeId);
	}

	/**
	* Deletes the LCS notification for the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	*/
	@Override
	public void deleteLCSProjectLCSNotification(long lcsProjectId) {
		_lcsNotificationLocalService.deleteLCSProjectLCSNotification(lcsProjectId);
	}

	/**
	* Deletes the LCS notifications of the user.
	*
	* @param userId the primary key of the user
	*/
	@Override
	public void deleteUserLCSNotifications(long userId) {
		_lcsNotificationLocalService.deleteUserLCSNotifications(userId);
	}

	/**
	* Deletes the LCS notifications of the user, for the name and primary key
	* of the class.
	*
	* @param userId the primary key of the user
	* @param classNameValue the class name
	* @param classPK the primary key for the object of class
	*/
	@Override
	public void deleteUserLCSNotifications(long userId,
		java.lang.String classNameValue, long classPK) {
		_lcsNotificationLocalService.deleteUserLCSNotifications(userId,
			classNameValue, classPK);
	}

	@Override
	public LCSNotificationLocalService getWrappedService() {
		return _lcsNotificationLocalService;
	}

	@Override
	public void setWrappedService(
		LCSNotificationLocalService lcsNotificationLocalService) {
		_lcsNotificationLocalService = lcsNotificationLocalService;
	}

	private LCSNotificationLocalService _lcsNotificationLocalService;
}