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
 * Provides a wrapper for {@link LCSPatchEntryLocalService}.
 *
 * @author Igor Beslic
 * @see LCSPatchEntryLocalService
 * @generated
 */
@ProviderType
public class LCSPatchEntryLocalServiceWrapper
	implements LCSPatchEntryLocalService,
		ServiceWrapper<LCSPatchEntryLocalService> {
	public LCSPatchEntryLocalServiceWrapper(
		LCSPatchEntryLocalService lcsPatchEntryLocalService) {
		_lcsPatchEntryLocalService = lcsPatchEntryLocalService;
	}

	/**
	* Adds the l c s patch entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry addLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return _lcsPatchEntryLocalService.addLCSPatchEntry(lcsPatchEntry);
	}

	/**
	* Creates a new l c s patch entry with the primary key. Does not add the l c s patch entry to the database.
	*
	* @param lcsPatchEntryId the primary key for the new l c s patch entry
	* @return the new l c s patch entry
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry createLCSPatchEntry(
		long lcsPatchEntryId) {
		return _lcsPatchEntryLocalService.createLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Deletes the l c s patch entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry deleteLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return _lcsPatchEntryLocalService.deleteLCSPatchEntry(lcsPatchEntry);
	}

	/**
	* Deletes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry that was removed
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry deleteLCSPatchEntry(
		long lcsPatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsPatchEntryLocalService.deleteLCSPatchEntry(lcsPatchEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry fetchLCSPatchEntry(
		long lcsPatchEntryId) {
		return _lcsPatchEntryLocalService.fetchLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Returns the l c s patch entry with the primary key.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry getLCSPatchEntry(
		long lcsPatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsPatchEntryLocalService.getLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Updates the l c s patch entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSPatchEntry updateLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return _lcsPatchEntryLocalService.updateLCSPatchEntry(lcsPatchEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsPatchEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsPatchEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsPatchEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsPatchEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsPatchEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s patch entries.
	*
	* @return the number of l c s patch entries
	*/
	@Override
	public int getLCSPatchEntriesCount() {
		return _lcsPatchEntryLocalService.getLCSPatchEntriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsPatchEntryLocalService.getOSGiServiceIdentifier();
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
		return _lcsPatchEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsPatchEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsPatchEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s patch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @return the range of l c s patch entries
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSPatchEntry> getLCSPatchEntries(
		int start, int end) {
		return _lcsPatchEntryLocalService.getLCSPatchEntries(start, end);
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
		return _lcsPatchEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsPatchEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSPatchEntryLocalService getWrappedService() {
		return _lcsPatchEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LCSPatchEntryLocalService lcsPatchEntryLocalService) {
		_lcsPatchEntryLocalService = lcsPatchEntryLocalService;
	}

	private LCSPatchEntryLocalService _lcsPatchEntryLocalService;
}