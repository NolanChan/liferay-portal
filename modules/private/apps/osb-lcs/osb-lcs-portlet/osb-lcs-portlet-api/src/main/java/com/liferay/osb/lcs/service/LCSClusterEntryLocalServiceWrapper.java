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
 * Provides a wrapper for {@link LCSClusterEntryLocalService}.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryLocalService
 * @generated
 */
@ProviderType
public class LCSClusterEntryLocalServiceWrapper
	implements LCSClusterEntryLocalService,
		ServiceWrapper<LCSClusterEntryLocalService> {
	public LCSClusterEntryLocalServiceWrapper(
		LCSClusterEntryLocalService lcsClusterEntryLocalService) {
		_lcsClusterEntryLocalService = lcsClusterEntryLocalService;
	}

	/**
	* Adds the l c s cluster entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry) {
		return _lcsClusterEntryLocalService.addLCSClusterEntry(lcsClusterEntry);
	}

	/**
	* Creates a new l c s cluster entry with the primary key. Does not add the l c s cluster entry to the database.
	*
	* @param lcsClusterEntryId the primary key for the new l c s cluster entry
	* @return the new l c s cluster entry
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry createLCSClusterEntry(
		long lcsClusterEntryId) {
		return _lcsClusterEntryLocalService.createLCSClusterEntry(lcsClusterEntryId);
	}

	/**
	* Deletes the l c s cluster entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry) {
		return _lcsClusterEntryLocalService.deleteLCSClusterEntry(lcsClusterEntry);
	}

	/**
	* Deletes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry that was removed
	* @throws PortalException if a l c s cluster entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryLocalService.deleteLCSClusterEntry(lcsClusterEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry fetchLCSClusterEntry(
		long lcsClusterEntryId) {
		return _lcsClusterEntryLocalService.fetchLCSClusterEntry(lcsClusterEntryId);
	}

	/**
	* Returns the l c s cluster entry with the primary key.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry
	* @throws PortalException if a l c s cluster entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry getLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryLocalService.getLCSClusterEntry(lcsClusterEntryId);
	}

	/**
	* Updates the l c s cluster entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry) {
		return _lcsClusterEntryLocalService.updateLCSClusterEntry(lcsClusterEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsClusterEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsClusterEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsClusterEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s cluster entries.
	*
	* @return the number of l c s cluster entries
	*/
	@Override
	public int getLCSClusterEntriesCount() {
		return _lcsClusterEntryLocalService.getLCSClusterEntriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterEntryLocalService.getOSGiServiceIdentifier();
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
		return _lcsClusterEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s cluster entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of l c s cluster entries
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSClusterEntries(
		int start, int end) {
		return _lcsClusterEntryLocalService.getLCSClusterEntries(start, end);
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
		return _lcsClusterEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsClusterEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSClusterEntryLocalService getWrappedService() {
		return _lcsClusterEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LCSClusterEntryLocalService lcsClusterEntryLocalService) {
		_lcsClusterEntryLocalService = lcsClusterEntryLocalService;
	}

	private LCSClusterEntryLocalService _lcsClusterEntryLocalService;
}