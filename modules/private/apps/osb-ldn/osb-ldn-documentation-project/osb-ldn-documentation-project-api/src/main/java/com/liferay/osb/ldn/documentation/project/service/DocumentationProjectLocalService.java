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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DocumentationProject. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ryan Park
 * @see DocumentationProjectLocalServiceUtil
 * @see com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectLocalServiceBaseImpl
 * @see com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DocumentationProjectLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentationProjectLocalServiceUtil} to access the documentation project local service. Add custom service methods to {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the documentation project to the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DocumentationProject addDocumentationProject(
		DocumentationProject documentationProject);

	public DocumentationProject addDocumentationProject(long userId,
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, File iconFile, int status,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	*
	* @param documentationProjectId the primary key for the new documentation project
	* @return the new documentation project
	*/
	public DocumentationProject createDocumentationProject(
		long documentationProjectId);

	/**
	* Deletes the documentation project from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public DocumentationProject deleteDocumentationProject(
		DocumentationProject documentationProject) throws PortalException;

	/**
	* Deletes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project that was removed
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DocumentationProject deleteDocumentationProject(
		long documentationProjectId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentationProject fetchDocumentationProject(
		long documentationProjectId);

	/**
	* Returns the documentation project matching the UUID and group.
	*
	* @param uuid the documentation project's UUID
	* @param groupId the primary key of the group
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentationProject fetchDocumentationProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the documentation project with the primary key.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentationProject getDocumentationProject(
		long documentationProjectId) throws PortalException;

	/**
	* Returns the documentation project matching the UUID and group.
	*
	* @param uuid the documentation project's UUID
	* @param groupId the primary key of the group
	* @return the matching documentation project
	* @throws PortalException if a matching documentation project could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DocumentationProject getDocumentationProjectByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the documentation project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DocumentationProject updateDocumentationProject(
		DocumentationProject documentationProject);

	public DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		File iconFile, int status, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of documentation projects.
	*
	* @return the number of documentation projects
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDocumentationProjectsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocumentationProject> getDocumentationProjects(int start,
		int end);

	/**
	* Returns all the documentation projects matching the UUID and company.
	*
	* @param uuid the UUID of the documentation projects
	* @param companyId the primary key of the company
	* @return the matching documentation projects, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocumentationProject> getDocumentationProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DocumentationProject> getDocumentationProjectsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void updateAsset(long userId, long documentationProjectId,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws PortalException;
}