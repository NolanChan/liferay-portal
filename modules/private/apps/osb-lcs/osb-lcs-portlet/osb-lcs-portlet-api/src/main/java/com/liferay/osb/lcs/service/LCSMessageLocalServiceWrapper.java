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
 * Provides a wrapper for {@link LCSMessageLocalService}.
 *
 * @author Igor Beslic
 * @see LCSMessageLocalService
 * @generated
 */
@ProviderType
public class LCSMessageLocalServiceWrapper implements LCSMessageLocalService,
	ServiceWrapper<LCSMessageLocalService> {
	public LCSMessageLocalServiceWrapper(
		LCSMessageLocalService lcsMessageLocalService) {
		_lcsMessageLocalService = lcsMessageLocalService;
	}

	/**
	* Adds the l c s message to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage addLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return _lcsMessageLocalService.addLCSMessage(lcsMessage);
	}

	/**
	* Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	*
	* @param lcsMessageId the primary key for the new l c s message
	* @return the new l c s message
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage createLCSMessage(
		long lcsMessageId) {
		return _lcsMessageLocalService.createLCSMessage(lcsMessageId);
	}

	/**
	* Deletes the l c s message from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage deleteLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return _lcsMessageLocalService.deleteLCSMessage(lcsMessage);
	}

	/**
	* Deletes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message that was removed
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage deleteLCSMessage(
		long lcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageLocalService.deleteLCSMessage(lcsMessageId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSMessage fetchLCSMessage(
		long lcsMessageId) {
		return _lcsMessageLocalService.fetchLCSMessage(lcsMessageId);
	}

	/**
	* Returns the l c s message with the primary key.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage getLCSMessage(long lcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageLocalService.getLCSMessage(lcsMessageId);
	}

	/**
	* Updates the l c s message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSMessage updateLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return _lcsMessageLocalService.updateLCSMessage(lcsMessage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsMessageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsMessageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s messages.
	*
	* @return the number of l c s messages
	*/
	@Override
	public int getLCSMessagesCount() {
		return _lcsMessageLocalService.getLCSMessagesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsMessageLocalService.getOSGiServiceIdentifier();
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
		return _lcsMessageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsMessageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsMessageLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of l c s messages
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		int start, int end) {
		return _lcsMessageLocalService.getLCSMessages(start, end);
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
		return _lcsMessageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsMessageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSMessageLocalService getWrappedService() {
		return _lcsMessageLocalService;
	}

	@Override
	public void setWrappedService(LCSMessageLocalService lcsMessageLocalService) {
		_lcsMessageLocalService = lcsMessageLocalService;
	}

	private LCSMessageLocalService _lcsMessageLocalService;
}