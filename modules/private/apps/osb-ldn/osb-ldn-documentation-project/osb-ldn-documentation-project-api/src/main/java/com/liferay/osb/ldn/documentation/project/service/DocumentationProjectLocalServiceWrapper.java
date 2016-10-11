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

package com.liferay.osb.ldn.documentation.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocumentationProjectLocalService}.
 *
 * @author Ryan Park
 * @see DocumentationProjectLocalService
 * @generated
 */
@ProviderType
public class DocumentationProjectLocalServiceWrapper
	implements DocumentationProjectLocalService,
		ServiceWrapper<DocumentationProjectLocalService> {
	public DocumentationProjectLocalServiceWrapper(
		DocumentationProjectLocalService documentationProjectLocalService) {
		_documentationProjectLocalService = documentationProjectLocalService;
	}

	/**
	* Adds the documentation project to the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was added
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject) {
		return _documentationProjectLocalService.addDocumentationProject(documentationProject);
	}

	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		long userId, java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.addDocumentationProject(userId,
			name, description, iconFileName, iconFile, status, serviceContext);
	}

	/**
	* Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	*
	* @param documentationProjectId the primary key for the new documentation project
	* @return the new documentation project
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject createDocumentationProject(
		long documentationProjectId) {
		return _documentationProjectLocalService.createDocumentationProject(documentationProjectId);
	}

	/**
	* Deletes the documentation project from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject deleteDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.deleteDocumentationProject(documentationProject);
	}

	/**
	* Deletes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project that was removed
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject deleteDocumentationProject(
		long documentationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.deleteDocumentationProject(documentationProjectId);
	}

	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject fetchDocumentationProject(
		long documentationProjectId) {
		return _documentationProjectLocalService.fetchDocumentationProject(documentationProjectId);
	}

	/**
	* Returns the documentation project matching the UUID and group.
	*
	* @param uuid the documentation project's UUID
	* @param groupId the primary key of the group
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject fetchDocumentationProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _documentationProjectLocalService.fetchDocumentationProjectByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the documentation project with the primary key.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject getDocumentationProject(
		long documentationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.getDocumentationProject(documentationProjectId);
	}

	/**
	* Returns the documentation project matching the UUID and group.
	*
	* @param uuid the documentation project's UUID
	* @param groupId the primary key of the group
	* @return the matching documentation project
	* @throws PortalException if a matching documentation project could not be found
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject getDocumentationProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.getDocumentationProjectByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the documentation project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was updated
	*/
	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject) {
		return _documentationProjectLocalService.updateDocumentationProject(documentationProject);
	}

	@Override
	public com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.updateDocumentationProject(documentationProjectId,
			name, description, iconFileName, iconFile, status, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _documentationProjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _documentationProjectLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _documentationProjectLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _documentationProjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of documentation projects.
	*
	* @return the number of documentation projects
	*/
	@Override
	public int getDocumentationProjectsCount() {
		return _documentationProjectLocalService.getDocumentationProjectsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _documentationProjectLocalService.getOSGiServiceIdentifier();
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
		return _documentationProjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _documentationProjectLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _documentationProjectLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the documentation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @return the range of documentation projects
	*/
	@Override
	public java.util.List<com.liferay.osb.ldn.documentation.project.model.DocumentationProject> getDocumentationProjects(
		int start, int end) {
		return _documentationProjectLocalService.getDocumentationProjects(start,
			end);
	}

	/**
	* Returns all the documentation projects matching the UUID and company.
	*
	* @param uuid the UUID of the documentation projects
	* @param companyId the primary key of the company
	* @return the matching documentation projects, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.osb.ldn.documentation.project.model.DocumentationProject> getDocumentationProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _documentationProjectLocalService.getDocumentationProjectsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of documentation projects matching the UUID and company.
	*
	* @param uuid the UUID of the documentation projects
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching documentation projects, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.osb.ldn.documentation.project.model.DocumentationProject> getDocumentationProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.ldn.documentation.project.model.DocumentationProject> orderByComparator) {
		return _documentationProjectLocalService.getDocumentationProjectsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _documentationProjectLocalService.dynamicQueryCount(dynamicQuery);
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
		return _documentationProjectLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateAsset(long userId, long documentationProjectId,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		_documentationProjectLocalService.updateAsset(userId,
			documentationProjectId, assetCategoryIds, assetTagNames);
	}

	@Override
	public DocumentationProjectLocalService getWrappedService() {
		return _documentationProjectLocalService;
	}

	@Override
	public void setWrappedService(
		DocumentationProjectLocalService documentationProjectLocalService) {
		_documentationProjectLocalService = documentationProjectLocalService;
	}

	private DocumentationProjectLocalService _documentationProjectLocalService;
}