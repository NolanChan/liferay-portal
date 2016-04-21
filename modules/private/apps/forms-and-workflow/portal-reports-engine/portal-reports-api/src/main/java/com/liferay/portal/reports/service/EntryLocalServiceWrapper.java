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

package com.liferay.portal.reports.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntryLocalService
 * @generated
 */
@ProviderType
public class EntryLocalServiceWrapper implements EntryLocalService,
	ServiceWrapper<EntryLocalService> {
	public EntryLocalServiceWrapper(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _entryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the entry to the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was added
	*/
	@Override
	public com.liferay.portal.reports.model.Entry addEntry(
		com.liferay.portal.reports.model.Entry entry) {
		return _entryLocalService.addEntry(entry);
	}

	@Override
	public com.liferay.portal.reports.model.Entry addEntry(long userId,
		long groupId, long definitionId, java.lang.String format,
		boolean schedulerRequest, java.util.Date startDate,
		java.util.Date endDate, boolean repeating, java.lang.String recurrence,
		java.lang.String emailNotifications, java.lang.String emailDelivery,
		java.lang.String portletId, java.lang.String pageURL,
		java.lang.String reportName, java.lang.String reportParameters,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.addEntry(userId, groupId, definitionId,
			format, schedulerRequest, startDate, endDate, repeating,
			recurrence, emailNotifications, emailDelivery, portletId, pageURL,
			reportName, reportParameters, serviceContext);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	@Override
	public com.liferay.portal.reports.model.Entry createEntry(long entryId) {
		return _entryLocalService.createEntry(entryId);
	}

	/**
	* Deletes the entry from the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.reports.model.Entry deleteEntry(
		com.liferay.portal.reports.model.Entry entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deleteEntry(entry);
	}

	/**
	* Deletes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws PortalException if a entry with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.reports.model.Entry deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deleteEntry(entryId);
	}

	@Override
	public com.liferay.portal.reports.model.Entry fetchEntry(long entryId) {
		return _entryLocalService.fetchEntry(entryId);
	}

	/**
	* Returns the entry with the primary key.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws PortalException if a entry with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.reports.model.Entry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.getEntry(entryId);
	}

	/**
	* Updates the entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was updated
	*/
	@Override
	public com.liferay.portal.reports.model.Entry updateEntry(
		com.liferay.portal.reports.model.Entry entry) {
		return _entryLocalService.updateEntry(entry);
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	@Override
	public int getEntriesCount() {
		return _entryLocalService.getEntriesCount();
	}

	@Override
	public int getEntriesCount(long groupId, java.lang.String definitionName,
		java.lang.String userName, java.util.Date createDateGT,
		java.util.Date createDateLT, boolean andSearch) {
		return _entryLocalService.getEntriesCount(groupId, definitionName,
			userName, createDateGT, createDateLT, andSearch);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entryLocalService.getOSGiServiceIdentifier();
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
		return _entryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.reports.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.reports.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.reports.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	@Override
	public java.util.List<com.liferay.portal.reports.model.Entry> getEntries(
		int start, int end) {
		return _entryLocalService.getEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.reports.model.Entry> getEntries(
		long groupId, java.lang.String definitionName,
		java.lang.String userName, java.util.Date createDateGT,
		java.util.Date createDateLT, boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _entryLocalService.getEntries(groupId, definitionName, userName,
			createDateGT, createDateLT, andSearch, start, end, orderByComparator);
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
		return _entryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _entryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public void addEntryResources(
		com.liferay.portal.reports.model.Entry entry,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.addEntryResources(entry, addCommunityPermissions,
			addGuestPermissions);
	}

	@Override
	public void addEntryResources(
		com.liferay.portal.reports.model.Entry entry,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.addEntryResources(entry, communityPermissions,
			guestPermissions);
	}

	@Override
	public void deleteAttachment(long companyId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.deleteAttachment(companyId, fileName);
	}

	@Override
	public void generateReport(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.generateReport(entryId);
	}

	@Override
	public void generateReport(long entryId, java.lang.String reportName)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.generateReport(entryId, reportName);
	}

	@Override
	public void sendEmails(long entryId, java.lang.String fileName,
		java.lang.String[] emailAddresses, boolean notification)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.sendEmails(entryId, fileName, emailAddresses,
			notification);
	}

	@Override
	public void unscheduleEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.unscheduleEntry(entryId);
	}

	@Override
	public void updateEntry(long entryId, java.lang.String reportName,
		byte[] reportResults)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.updateEntry(entryId, reportName, reportResults);
	}

	@Override
	public void updateEntryStatus(long entryId,
		com.liferay.portal.reports.ReportStatus status,
		java.lang.String errorMessage)
		throws com.liferay.portal.kernel.exception.PortalException {
		_entryLocalService.updateEntryStatus(entryId, status, errorMessage);
	}

	@Override
	public EntryLocalService getWrappedService() {
		return _entryLocalService;
	}

	@Override
	public void setWrappedService(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	private EntryLocalService _entryLocalService;
}