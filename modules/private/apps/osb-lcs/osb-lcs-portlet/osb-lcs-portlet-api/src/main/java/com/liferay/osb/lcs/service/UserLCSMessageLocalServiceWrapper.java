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
 * Provides a wrapper for {@link UserLCSMessageLocalService}.
 *
 * @author Igor Beslic
 * @see UserLCSMessageLocalService
 * @generated
 */
@ProviderType
public class UserLCSMessageLocalServiceWrapper
	implements UserLCSMessageLocalService,
		ServiceWrapper<UserLCSMessageLocalService> {
	public UserLCSMessageLocalServiceWrapper(
		UserLCSMessageLocalService userLCSMessageLocalService) {
		_userLCSMessageLocalService = userLCSMessageLocalService;
	}

	/**
	* Adds the user l c s message to the database. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage addUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return _userLCSMessageLocalService.addUserLCSMessage(userLCSMessage);
	}

	/**
	* Creates a new user l c s message with the primary key. Does not add the user l c s message to the database.
	*
	* @param userLcsMessageId the primary key for the new user l c s message
	* @return the new user l c s message
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage createUserLCSMessage(
		long userLcsMessageId) {
		return _userLCSMessageLocalService.createUserLCSMessage(userLcsMessageId);
	}

	/**
	* Deletes the user l c s message from the database. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return _userLCSMessageLocalService.deleteUserLCSMessage(userLCSMessage);
	}

	/**
	* Deletes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message that was removed
	* @throws PortalException if a user l c s message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		long userLcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageLocalService.deleteUserLCSMessage(userLcsMessageId);
	}

	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage fetchUserLCSMessage(
		long userLcsMessageId) {
		return _userLCSMessageLocalService.fetchUserLCSMessage(userLcsMessageId);
	}

	/**
	* Returns the user l c s message with the primary key.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message
	* @throws PortalException if a user l c s message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage getUserLCSMessage(
		long userLcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageLocalService.getUserLCSMessage(userLcsMessageId);
	}

	/**
	* Updates the user l c s message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLCSMessage the user l c s message
	* @return the user l c s message that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage updateUserLCSMessage(
		com.liferay.osb.lcs.model.UserLCSMessage userLCSMessage) {
		return _userLCSMessageLocalService.updateUserLCSMessage(userLCSMessage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userLCSMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userLCSMessageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userLCSMessageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of user l c s messages.
	*
	* @return the number of user l c s messages
	*/
	@Override
	public int getUserLCSMessagesCount() {
		return _userLCSMessageLocalService.getUserLCSMessagesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userLCSMessageLocalService.getOSGiServiceIdentifier();
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
		return _userLCSMessageLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _userLCSMessageLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _userLCSMessageLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		int start, int end) {
		return _userLCSMessageLocalService.getUserLCSMessages(start, end);
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
		return _userLCSMessageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userLCSMessageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public UserLCSMessageLocalService getWrappedService() {
		return _userLCSMessageLocalService;
	}

	@Override
	public void setWrappedService(
		UserLCSMessageLocalService userLCSMessageLocalService) {
		_userLCSMessageLocalService = userLCSMessageLocalService;
	}

	private UserLCSMessageLocalService _userLCSMessageLocalService;
}