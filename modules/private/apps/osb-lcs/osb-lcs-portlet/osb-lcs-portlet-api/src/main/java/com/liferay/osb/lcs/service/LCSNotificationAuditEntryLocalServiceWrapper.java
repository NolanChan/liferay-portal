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
 * Provides a wrapper for {@link LCSNotificationAuditEntryLocalService}.
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntryLocalService
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryLocalServiceWrapper
	implements LCSNotificationAuditEntryLocalService,
		ServiceWrapper<LCSNotificationAuditEntryLocalService> {
	public LCSNotificationAuditEntryLocalServiceWrapper(
		LCSNotificationAuditEntryLocalService lcsNotificationAuditEntryLocalService) {
		_lcsNotificationAuditEntryLocalService = lcsNotificationAuditEntryLocalService;
	}

	/**
	* Adds the l c s notification audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry addLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return _lcsNotificationAuditEntryLocalService.addLCSNotificationAuditEntry(lcsNotificationAuditEntry);
	}

	/**
	* Creates a new l c s notification audit entry with the primary key. Does not add the l c s notification audit entry to the database.
	*
	* @param lcsNotificationAuditEntryId the primary key for the new l c s notification audit entry
	* @return the new l c s notification audit entry
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry createLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId) {
		return _lcsNotificationAuditEntryLocalService.createLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Deletes the l c s notification audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry deleteLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return _lcsNotificationAuditEntryLocalService.deleteLCSNotificationAuditEntry(lcsNotificationAuditEntry);
	}

	/**
	* Deletes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	* @throws PortalException if a l c s notification audit entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry deleteLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationAuditEntryLocalService.deleteLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry fetchLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId) {
		return _lcsNotificationAuditEntryLocalService.fetchLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Returns the l c s notification audit entry with the primary key.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry
	* @throws PortalException if a l c s notification audit entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry getLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationAuditEntryLocalService.getLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Updates the l c s notification audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSNotificationAuditEntry updateLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return _lcsNotificationAuditEntryLocalService.updateLCSNotificationAuditEntry(lcsNotificationAuditEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsNotificationAuditEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsNotificationAuditEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsNotificationAuditEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationAuditEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsNotificationAuditEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s notification audit entries.
	*
	* @return the number of l c s notification audit entries
	*/
	@Override
	public int getLCSNotificationAuditEntriesCount() {
		return _lcsNotificationAuditEntryLocalService.getLCSNotificationAuditEntriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsNotificationAuditEntryLocalService.getOSGiServiceIdentifier();
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
		return _lcsNotificationAuditEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsNotificationAuditEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsNotificationAuditEntryLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s notification audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of l c s notification audit entries
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
		int start, int end) {
		return _lcsNotificationAuditEntryLocalService.getLCSNotificationAuditEntries(start,
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
		return _lcsNotificationAuditEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsNotificationAuditEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSNotificationAuditEntryLocalService getWrappedService() {
		return _lcsNotificationAuditEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LCSNotificationAuditEntryLocalService lcsNotificationAuditEntryLocalService) {
		_lcsNotificationAuditEntryLocalService = lcsNotificationAuditEntryLocalService;
	}

	private LCSNotificationAuditEntryLocalService _lcsNotificationAuditEntryLocalService;
}