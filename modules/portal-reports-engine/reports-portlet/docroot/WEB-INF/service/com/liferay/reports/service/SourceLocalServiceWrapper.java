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

package com.liferay.reports.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SourceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SourceLocalService
 * @generated
 */
@ProviderType
public class SourceLocalServiceWrapper implements SourceLocalService,
	ServiceWrapper<SourceLocalService> {
	public SourceLocalServiceWrapper(SourceLocalService sourceLocalService) {
		_sourceLocalService = sourceLocalService;
	}

	/**
	* Adds the source to the database. Also notifies the appropriate model listeners.
	*
	* @param source the source
	* @return the source that was added
	*/
	@Override
	public com.liferay.reports.model.Source addSource(
		com.liferay.reports.model.Source source) {
		return _sourceLocalService.addSource(source);
	}

	@Override
	public com.liferay.reports.model.Source addSource(long userId,
		long groupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.addSource(userId, groupId, nameMap,
			driverClassName, driverUrl, driverUserName, driverPassword,
			serviceContext);
	}

	/**
	* Creates a new source with the primary key. Does not add the source to the database.
	*
	* @param sourceId the primary key for the new source
	* @return the new source
	*/
	@Override
	public com.liferay.reports.model.Source createSource(long sourceId) {
		return _sourceLocalService.createSource(sourceId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the source from the database. Also notifies the appropriate model listeners.
	*
	* @param source the source
	* @return the source that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.reports.model.Source deleteSource(
		com.liferay.reports.model.Source source)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.deleteSource(source);
	}

	/**
	* Deletes the source with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sourceId the primary key of the source
	* @return the source that was removed
	* @throws PortalException if a source with the primary key could not be found
	*/
	@Override
	public com.liferay.reports.model.Source deleteSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.deleteSource(sourceId);
	}

	@Override
	public void deleteSources(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_sourceLocalService.deleteSources(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sourceLocalService.dynamicQuery();
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
		return _sourceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.SourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sourceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.SourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sourceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _sourceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _sourceLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.reports.model.Source fetchSource(long sourceId) {
		return _sourceLocalService.fetchSource(sourceId);
	}

	/**
	* Returns the source matching the UUID and group.
	*
	* @param uuid the source's UUID
	* @param groupId the primary key of the group
	* @return the matching source, or <code>null</code> if a matching source could not be found
	*/
	@Override
	public com.liferay.reports.model.Source fetchSourceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _sourceLocalService.fetchSourceByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _sourceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _sourceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _sourceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _sourceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the source with the primary key.
	*
	* @param sourceId the primary key of the source
	* @return the source
	* @throws PortalException if a source with the primary key could not be found
	*/
	@Override
	public com.liferay.reports.model.Source getSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.getSource(sourceId);
	}

	/**
	* Returns the source matching the UUID and group.
	*
	* @param uuid the source's UUID
	* @param groupId the primary key of the group
	* @return the matching source
	* @throws PortalException if a matching source could not be found
	*/
	@Override
	public com.liferay.reports.model.Source getSourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.getSourceByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.liferay.reports.model.Source> getSources(
		long groupId, java.lang.String name, java.lang.String driverUrl,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _sourceLocalService.getSources(groupId, name, driverUrl,
			andSearch, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the sources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.SourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sources
	* @param end the upper bound of the range of sources (not inclusive)
	* @return the range of sources
	*/
	@Override
	public java.util.List<com.liferay.reports.model.Source> getSources(
		int start, int end) {
		return _sourceLocalService.getSources(start, end);
	}

	/**
	* Returns all the sources matching the UUID and company.
	*
	* @param uuid the UUID of the sources
	* @param companyId the primary key of the company
	* @return the matching sources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.reports.model.Source> getSourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _sourceLocalService.getSourcesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of sources matching the UUID and company.
	*
	* @param uuid the UUID of the sources
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sources
	* @param end the upper bound of the range of sources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sources, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.reports.model.Source> getSourcesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.reports.model.Source> orderByComparator) {
		return _sourceLocalService.getSourcesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of sources.
	*
	* @return the number of sources
	*/
	@Override
	public int getSourcesCount() {
		return _sourceLocalService.getSourcesCount();
	}

	@Override
	public int getSourcesCount(long groupId, java.lang.String name,
		java.lang.String driverUrl, boolean andSearch) {
		return _sourceLocalService.getSourcesCount(groupId, name, driverUrl,
			andSearch);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sourceLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Updates the source in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param source the source
	* @return the source that was updated
	*/
	@Override
	public com.liferay.reports.model.Source updateSource(
		com.liferay.reports.model.Source source) {
		return _sourceLocalService.updateSource(source);
	}

	@Override
	public com.liferay.reports.model.Source updateSource(long sourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sourceLocalService.updateSource(sourceId, nameMap,
			driverClassName, driverUrl, driverUserName, driverPassword,
			serviceContext);
	}

	@Override
	public SourceLocalService getWrappedService() {
		return _sourceLocalService;
	}

	@Override
	public void setWrappedService(SourceLocalService sourceLocalService) {
		_sourceLocalService = sourceLocalService;
	}

	private SourceLocalService _sourceLocalService;
}