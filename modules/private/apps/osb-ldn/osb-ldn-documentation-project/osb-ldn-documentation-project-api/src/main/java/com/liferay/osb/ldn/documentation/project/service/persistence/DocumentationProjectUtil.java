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

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the documentation project service. This utility wraps {@link com.liferay.osb.ldn.documentation.project.service.persistence.impl.DocumentationProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see DocumentationProjectPersistence
 * @see com.liferay.osb.ldn.documentation.project.service.persistence.impl.DocumentationProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class DocumentationProjectUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(DocumentationProject documentationProject) {
		getPersistence().clearCache(documentationProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DocumentationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DocumentationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DocumentationProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DocumentationProject update(
		DocumentationProject documentationProject) {
		return getPersistence().update(documentationProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DocumentationProject update(
		DocumentationProject documentationProject, ServiceContext serviceContext) {
		return getPersistence().update(documentationProject, serviceContext);
	}

	/**
	* Returns all the documentation projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching documentation projects
	*/
	public static List<DocumentationProject> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<DocumentationProject> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<DocumentationProject> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<DocumentationProject> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByUuid_First(java.lang.String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByUuid_Last(java.lang.String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the documentation projects before and after the current documentation project in the ordered set where uuid = &#63;.
	*
	* @param documentationProjectId the primary key of the current documentation project
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next documentation project
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public static DocumentationProject[] findByUuid_PrevAndNext(
		long documentationProjectId, java.lang.String uuid,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence()
				   .findByUuid_PrevAndNext(documentationProjectId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the documentation projects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of documentation projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching documentation projects
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the documentation project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the documentation project where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the documentation project that was removed
	*/
	public static DocumentationProject removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of documentation projects where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching documentation projects
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching documentation projects
	*/
	public static List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<DocumentationProject> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last documentation project in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static DocumentationProject[] findByUuid_C_PrevAndNext(
		long documentationProjectId, java.lang.String uuid, long companyId,
		OrderByComparator<DocumentationProject> orderByComparator)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(documentationProjectId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the documentation projects where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of documentation projects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching documentation projects
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the documentation project where name = &#63; or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param name the name
	* @return the matching documentation project
	* @throws NoSuchDocumentationProjectException if a matching documentation project could not be found
	*/
	public static DocumentationProject findByName(java.lang.String name)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByName(java.lang.String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the documentation project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching documentation project, or <code>null</code> if a matching documentation project could not be found
	*/
	public static DocumentationProject fetchByName(java.lang.String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Removes the documentation project where name = &#63; from the database.
	*
	* @param name the name
	* @return the documentation project that was removed
	*/
	public static DocumentationProject removeByName(java.lang.String name)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().removeByName(name);
	}

	/**
	* Returns the number of documentation projects where name = &#63;.
	*
	* @param name the name
	* @return the number of matching documentation projects
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Caches the documentation project in the entity cache if it is enabled.
	*
	* @param documentationProject the documentation project
	*/
	public static void cacheResult(DocumentationProject documentationProject) {
		getPersistence().cacheResult(documentationProject);
	}

	/**
	* Caches the documentation projects in the entity cache if it is enabled.
	*
	* @param documentationProjects the documentation projects
	*/
	public static void cacheResult(
		List<DocumentationProject> documentationProjects) {
		getPersistence().cacheResult(documentationProjects);
	}

	/**
	* Creates a new documentation project with the primary key. Does not add the documentation project to the database.
	*
	* @param documentationProjectId the primary key for the new documentation project
	* @return the new documentation project
	*/
	public static DocumentationProject create(long documentationProjectId) {
		return getPersistence().create(documentationProjectId);
	}

	/**
	* Removes the documentation project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project that was removed
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public static DocumentationProject remove(long documentationProjectId)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().remove(documentationProjectId);
	}

	public static DocumentationProject updateImpl(
		DocumentationProject documentationProject) {
		return getPersistence().updateImpl(documentationProject);
	}

	/**
	* Returns the documentation project with the primary key or throws a {@link NoSuchDocumentationProjectException} if it could not be found.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project
	* @throws NoSuchDocumentationProjectException if a documentation project with the primary key could not be found
	*/
	public static DocumentationProject findByPrimaryKey(
		long documentationProjectId)
		throws com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException {
		return getPersistence().findByPrimaryKey(documentationProjectId);
	}

	/**
	* Returns the documentation project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param documentationProjectId the primary key of the documentation project
	* @return the documentation project, or <code>null</code> if a documentation project with the primary key could not be found
	*/
	public static DocumentationProject fetchByPrimaryKey(
		long documentationProjectId) {
		return getPersistence().fetchByPrimaryKey(documentationProjectId);
	}

	public static java.util.Map<java.io.Serializable, DocumentationProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the documentation projects.
	*
	* @return the documentation projects
	*/
	public static List<DocumentationProject> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DocumentationProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DocumentationProject> findAll(int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DocumentationProject> findAll(int start, int end,
		OrderByComparator<DocumentationProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the documentation projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of documentation projects.
	*
	* @return the number of documentation projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DocumentationProjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocumentationProjectPersistence, DocumentationProjectPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DocumentationProjectPersistence.class);
}