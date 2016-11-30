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
 * Provides a wrapper for {@link LCSClusterEntryTokenLocalService}.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenLocalService
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenLocalServiceWrapper
	implements LCSClusterEntryTokenLocalService,
		ServiceWrapper<LCSClusterEntryTokenLocalService> {
	public LCSClusterEntryTokenLocalServiceWrapper(
		LCSClusterEntryTokenLocalService lcsClusterEntryTokenLocalService) {
		_lcsClusterEntryTokenLocalService = lcsClusterEntryTokenLocalService;
	}

	/**
	* Adds the l c s cluster entry token to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return _lcsClusterEntryTokenLocalService.addLCSClusterEntryToken(lcsClusterEntryToken);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		long userId, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.addLCSClusterEntryToken(userId,
			lcsClusterEntryId, lcsServicesConfiguration);
	}

	/**
	* Creates a new l c s cluster entry token with the primary key. Does not add the l c s cluster entry token to the database.
	*
	* @param lcsClusterEntryTokenId the primary key for the new l c s cluster entry token
	* @return the new l c s cluster entry token
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken createLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {
		return _lcsClusterEntryTokenLocalService.createLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	/**
	* Deletes the l c s cluster entry token from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return _lcsClusterEntryTokenLocalService.deleteLCSClusterEntryToken(lcsClusterEntryToken);
	}

	/**
	* Deletes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.deleteLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId) {
		return _lcsClusterEntryTokenLocalService.fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {
		return _lcsClusterEntryTokenLocalService.fetchLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	/**
	* Returns the l c s cluster entry token with the primary key.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken getLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.getLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken regenerateLCSClusterEntryToken(
		long userId, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.regenerateLCSClusterEntryToken(userId,
			lcsClusterEntryId, lcsServicesConfiguration);
	}

	/**
	* Updates the l c s cluster entry token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken updateLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return _lcsClusterEntryTokenLocalService.updateLCSClusterEntryToken(lcsClusterEntryToken);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsClusterEntryTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsClusterEntryTokenLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsClusterEntryTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s cluster entry tokens.
	*
	* @return the number of l c s cluster entry tokens
	*/
	@Override
	public int getLCSClusterEntryTokensCount() {
		return _lcsClusterEntryTokenLocalService.getLCSClusterEntryTokensCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterEntryTokenLocalService.getOSGiServiceIdentifier();
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
		return _lcsClusterEntryTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterEntryTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterEntryTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s cluster entry tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entry tokens
	* @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	* @return the range of l c s cluster entry tokens
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntryToken> getLCSClusterEntryTokens(
		int start, int end) {
		return _lcsClusterEntryTokenLocalService.getLCSClusterEntryTokens(start,
			end);
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
		return _lcsClusterEntryTokenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsClusterEntryTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSClusterEntryTokenLocalService getWrappedService() {
		return _lcsClusterEntryTokenLocalService;
	}

	@Override
	public void setWrappedService(
		LCSClusterEntryTokenLocalService lcsClusterEntryTokenLocalService) {
		_lcsClusterEntryTokenLocalService = lcsClusterEntryTokenLocalService;
	}

	private LCSClusterEntryTokenLocalService _lcsClusterEntryTokenLocalService;
}