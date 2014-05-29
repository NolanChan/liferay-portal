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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserTrackerPathLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerPathLocalService
 * @generated
 */
@ProviderType
public class UserTrackerPathLocalServiceWrapper
	implements UserTrackerPathLocalService,
		ServiceWrapper<UserTrackerPathLocalService> {
	public UserTrackerPathLocalServiceWrapper(
		UserTrackerPathLocalService userTrackerPathLocalService) {
		_userTrackerPathLocalService = userTrackerPathLocalService;
	}

	/**
	* Adds the user tracker path to the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackerPath the user tracker path
	* @return the user tracker path that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath addUserTrackerPath(
		com.liferay.portal.model.UserTrackerPath userTrackerPath)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.addUserTrackerPath(userTrackerPath);
	}

	/**
	* Creates a new user tracker path with the primary key. Does not add the user tracker path to the database.
	*
	* @param userTrackerPathId the primary key for the new user tracker path
	* @return the new user tracker path
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath createUserTrackerPath(
		long userTrackerPathId) {
		return _userTrackerPathLocalService.createUserTrackerPath(userTrackerPathId);
	}

	/**
	* Deletes the user tracker path with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackerPathId the primary key of the user tracker path
	* @return the user tracker path that was removed
	* @throws PortalException if a user tracker path with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath deleteUserTrackerPath(
		long userTrackerPathId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.deleteUserTrackerPath(userTrackerPathId);
	}

	/**
	* Deletes the user tracker path from the database. Also notifies the appropriate model listeners.
	*
	* @param userTrackerPath the user tracker path
	* @return the user tracker path that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath deleteUserTrackerPath(
		com.liferay.portal.model.UserTrackerPath userTrackerPath)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.deleteUserTrackerPath(userTrackerPath);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userTrackerPathLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.model.UserTrackerPath fetchUserTrackerPath(
		long userTrackerPathId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.fetchUserTrackerPath(userTrackerPathId);
	}

	/**
	* Returns the user tracker path with the primary key.
	*
	* @param userTrackerPathId the primary key of the user tracker path
	* @return the user tracker path
	* @throws PortalException if a user tracker path with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath getUserTrackerPath(
		long userTrackerPathId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getUserTrackerPath(userTrackerPathId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user tracker paths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserTrackerPathModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user tracker paths
	* @param end the upper bound of the range of user tracker paths (not inclusive)
	* @return the range of user tracker paths
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.model.UserTrackerPath> getUserTrackerPaths(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getUserTrackerPaths(start, end);
	}

	/**
	* Returns the number of user tracker paths.
	*
	* @return the number of user tracker paths
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getUserTrackerPathsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getUserTrackerPathsCount();
	}

	/**
	* Updates the user tracker path in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userTrackerPath the user tracker path
	* @return the user tracker path that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.model.UserTrackerPath updateUserTrackerPath(
		com.liferay.portal.model.UserTrackerPath userTrackerPath)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.updateUserTrackerPath(userTrackerPath);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _userTrackerPathLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userTrackerPathLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.util.List<com.liferay.portal.model.UserTrackerPath> getUserTrackerPaths(
		long userTrackerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userTrackerPathLocalService.getUserTrackerPaths(userTrackerId,
			start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public UserTrackerPathLocalService getWrappedUserTrackerPathLocalService() {
		return _userTrackerPathLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedUserTrackerPathLocalService(
		UserTrackerPathLocalService userTrackerPathLocalService) {
		_userTrackerPathLocalService = userTrackerPathLocalService;
	}

	@Override
	public UserTrackerPathLocalService getWrappedService() {
		return _userTrackerPathLocalService;
	}

	@Override
	public void setWrappedService(
		UserTrackerPathLocalService userTrackerPathLocalService) {
		_userTrackerPathLocalService = userTrackerPathLocalService;
	}

	private UserTrackerPathLocalService _userTrackerPathLocalService;
}