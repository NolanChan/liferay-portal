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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DocumentationProject. This utility wraps
 * {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see DocumentationProjectLocalService
 * @see com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectLocalServiceBaseImpl
 * @see com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectLocalServiceImpl
 * @generated
 */
@ProviderType
public class DocumentationProjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.ldn.documentation.project.service.impl.DocumentationProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the documentation project to the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was added
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject) {
		return getService().addDocumentationProject(documentationProject);
	}

	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject addDocumentationProject(
		long userId, java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addDocumentationProject(userId, name, description,
			iconFileName, iconFile, status);
	}

	/**
	* Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	*
	* @param documentationProjectId the primary key for the new documentation project
	* @return the new documentation project
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject createDocumentationProject(
		long documentationProjectId) {
		return getService().createDocumentationProject(documentationProjectId);
	}

	/**
	* Deletes the documentation project from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject deleteDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDocumentationProject(documentationProject);
	}

	/**
	* Deletes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project that was removed
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject deleteDocumentationProject(
		long documentationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDocumentationProject(documentationProjectId);
	}

	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject fetchDocumentationProject(
		long documentationProjectId) {
		return getService().fetchDocumentationProject(documentationProjectId);
	}

	/**
	* Returns the documentation project with the matching UUID and company.
	*
	* @param uuid the documentation project's UUID
	* @param companyId the primary key of the company
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject fetchDocumentationProjectByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .fetchDocumentationProjectByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the documentation project with the primary key.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project
	* @throws PortalException if a documentation project with the primary key could not be found
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject getDocumentationProject(
		long documentationProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDocumentationProject(documentationProjectId);
	}

	/**
	* Returns the documentation project with the matching UUID and company.
	*
	* @param uuid the documentation project's UUID
	* @param companyId the primary key of the company
	* @return the matching documentation project
	* @throws PortalException if a matching documentation project could not be found
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject getDocumentationProjectByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getDocumentationProjectByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Updates the documentation project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param documentationProject the documentation project
	* @return the documentation project that was updated
	*/
	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		com.liferay.osb.ldn.documentation.project.model.DocumentationProject documentationProject) {
		return getService().updateDocumentationProject(documentationProject);
	}

	public static com.liferay.osb.ldn.documentation.project.model.DocumentationProject updateDocumentationProject(
		long documentationProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDocumentationProject(documentationProjectId, name,
			description, iconFileName, iconFile, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of documentation projects.
	*
	* @return the number of documentation projects
	*/
	public static int getDocumentationProjectsCount() {
		return getService().getDocumentationProjectsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.osb.ldn.documentation.project.model.DocumentationProject> getDocumentationProjects(
		int start, int end) {
		return getService().getDocumentationProjects(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static DocumentationProjectLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocumentationProjectLocalService, DocumentationProjectLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DocumentationProjectLocalService.class);
}