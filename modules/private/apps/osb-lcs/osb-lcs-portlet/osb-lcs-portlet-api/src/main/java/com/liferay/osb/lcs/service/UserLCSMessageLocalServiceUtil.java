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
 * Provides the local service utility for UserLCSMessage. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.UserLCSMessageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see UserLCSMessageLocalService
 * @see com.liferay.osb.lcs.service.base.UserLCSMessageLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.UserLCSMessageLocalServiceImpl
 * @generated
 */
@ProviderType
public class UserLCSMessageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.UserLCSMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user l c s message to the database. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was added
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage addUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return getService().addUserLCSMessage(userLCSMessage);
	}

	/**
	* Creates a new user l c s message with the primary key. Does not add the user l c s message to the database.
	*
	* @param userLcsMessageId the primary key for the new user l c s message
	* @return the new user l c s message
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage createUserLCSMessage(
		long userLcsMessageId) {
		return getService().createUserLCSMessage(userLcsMessageId);
	}

	/**
	* Deletes the user l c s message from the database. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was removed
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return getService().deleteUserLCSMessage(userLCSMessage);
	}

	/**
	* Deletes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message that was removed
	* @throws PortalException if a user l c s message with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		long userLcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteUserLCSMessage(userLcsMessageId);
	}

	public static com.liferay.osb.lcs.model.UserLCSMessage fetchUserLCSMessage(
		long userLcsMessageId) {
		return getService().fetchUserLCSMessage(userLcsMessageId);
	}

	/**
	* Returns the user l c s message with the primary key.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message
	* @throws PortalException if a user l c s message with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage getUserLCSMessage(
		long userLcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSMessage(userLcsMessageId);
	}

	/**
	* Updates the user l c s message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was updated
	*/
	public static com.liferay.osb.lcs.model.UserLCSMessage updateUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return getService().updateUserLCSMessage(userLCSMessage);
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
	* Returns the number of user l c s messages.
	*
	* @return the number of user l c s messages
	*/
	public static int getUserLCSMessagesCount() {
		return getService().getUserLCSMessagesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the user l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of user l c s messages
	*/
	public static java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		int start, int end) {
		return getService().getUserLCSMessages(start, end);
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

	public static UserLCSMessageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserLCSMessageLocalService, UserLCSMessageLocalService> _serviceTracker =
		ServiceTrackerFactory.open(UserLCSMessageLocalService.class);
}