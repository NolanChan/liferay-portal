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

package com.liferay.osb.ldn.documentation.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the documentation project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see com.liferay.osb.ldn.documentation.project.service.persistence.impl.DocumentationProjectPersistenceImpl
 * @see DocumentationProjectUtil
 * @generated
 */
@ProviderType
public interface DocumentationProjectPersistence extends BasePersistence<DocumentationProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocumentationProjectUtil} to access the documentation project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the documentation projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the documentation projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @return the range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the documentation projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns an ordered range of all the documentation projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns the documentation projects before and after the current documentation project in the ordered set where uuid = &#63;.
	*
	* @param documentationProjectId the primary key of the current documentation project
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next documentation project
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public DocumentationProject[] findByUuid_PrevAndNext(
		long documentationProjectId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Removes all the documentation projects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of documentation projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching documentation projects
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the documentation project where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the documentation project that was removed
	*/
	public DocumentationProject removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchDocumentationProjectException;

	/**
	* Returns the number of documentation projects where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching documentation projects
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @return the range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns an ordered range of all the documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching documentation projects
	*/
	public java.util.List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns the documentation projects before and after the current documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param documentationProjectId the primary key of the current documentation project
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next documentation project
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public DocumentationProject[] findByUuid_C_PrevAndNext(
		long documentationProjectId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator)
		throws NoSuchDocumentationProjectException;

	/**
	* Removes all the documentation projects where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching documentation projects
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the documentation project where name = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param name the name
	* @return the matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public DocumentationProject findByName(java.lang.String name)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByName(java.lang.String name);

	/**
	* Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public DocumentationProject fetchByName(java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the documentation project where name = &#63; from the database.
	*
	* @param name the name
	* @return the documentation project that was removed
	*/
	public DocumentationProject removeByName(java.lang.String name)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the number of documentation projects where name = &#63;.
	*
	* @param name the name
	* @return the number of matching documentation projects
	*/
	public int countByName(java.lang.String name);

	/**
	* Caches the documentation project in the entity cache if it is enabled.
	*
	* @param documentationProject the documentation project
	*/
	public void cacheResult(DocumentationProject documentationProject);

	/**
	* Caches the documentation projects in the entity cache if it is enabled.
	*
	* @param documentationProjects the documentation projects
	*/
	public void cacheResult(
		java.util.List<DocumentationProject> documentationProjects);

	/**
	* Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	*
	* @param documentationProjectId the primary key for the new documentation project
	* @return the new documentation project
	*/
	public DocumentationProject create(long documentationProjectId);

	/**
	* Removes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project that was removed
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public DocumentationProject remove(long documentationProjectId)
		throws NoSuchDocumentationProjectException;

	public DocumentationProject updateImpl(
		DocumentationProject documentationProject);

	/**
	* Returns the documentation project with the primary key or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public DocumentationProject findByPrimaryKey(long documentationProjectId)
		throws NoSuchDocumentationProjectException;

	/**
	* Returns the documentation project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project, or <code>null</code> if a documentation project with the primary key could not be found
	*/
	public DocumentationProject fetchByPrimaryKey(long documentationProjectId);

	@Override
	public java.util.Map<java.io.Serializable, DocumentationProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the documentation projects.
	*
	* @return the documentation projects
	*/
	public java.util.List<DocumentationProject> findAll();

	/**
	* Returns a range of all the documentation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @return the range of documentation projects
	*/
	public java.util.List<DocumentationProject> findAll(int start, int end);

	/**
	* Returns an ordered range of all the documentation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of documentation projects
	*/
	public java.util.List<DocumentationProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator);

	/**
	* Returns an ordered range of all the documentation projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentationProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documentation projects
	* @param end the upper bound of the range of documentation projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of documentation projects
	*/
	public java.util.List<DocumentationProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the documentation projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of documentation projects.
	*
	* @return the number of documentation projects
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}