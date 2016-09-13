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

package com.liferay.osb.lcs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.model.LCSProject;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s project service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSProjectPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSProjectUtil {
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
	public static void clearCache(LCSProject lcsProject) {
		getPersistence().clearCache(lcsProject);
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
	public static List<LCSProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSProject update(LCSProject lcsProject) {
		return getPersistence().update(lcsProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSProject update(LCSProject lcsProject,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsProject, serviceContext);
	}

	/**
	* Returns all the l c s projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching l c s projects
	*/
	public static List<LCSProject> findByName(java.lang.String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the l c s projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @return the range of matching l c s projects
	*/
	public static List<LCSProject> findByName(java.lang.String name, int start,
		int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the l c s projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByName(java.lang.String name, int start,
		int end, OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByName(java.lang.String name, int start,
		int end, OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByName_First(java.lang.String name,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByName_First(java.lang.String name,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByName_Last(java.lang.String name,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByName_Last(java.lang.String name,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the l c s projects before and after the current l c s project in the ordered set where name LIKE &#63;.
	*
	* @param lcsProjectId the primary key of the current l c s project
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public static LCSProject[] findByName_PrevAndNext(long lcsProjectId,
		java.lang.String name, OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByName_PrevAndNext(lcsProjectId, name, orderByComparator);
	}

	/**
	* Removes all the l c s projects where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(java.lang.String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of l c s projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching l c s projects
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the l c s project where corpProjectId = &#63; or throws a {@link NoSuchLCSProjectException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @return the matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByCorpProjectId(long corpProjectId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().findByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCorpProjectId(long corpProjectId) {
		return getPersistence().fetchByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCorpProjectId(corpProjectId, retrieveFromCache);
	}

	/**
	* Removes the l c s project where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @return the l c s project that was removed
	*/
	public static LCSProject removeByCorpProjectId(long corpProjectId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().removeByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the number of l c s projects where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching l c s projects
	*/
	public static int countByCorpProjectId(long corpProjectId) {
		return getPersistence().countByCorpProjectId(corpProjectId);
	}

	/**
	* Returns all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @return the matching l c s projects
	*/
	public static List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived) {
		return getPersistence().findByCPI_A(corpProjectId, archived);
	}

	/**
	* Returns a range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @return the range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end) {
		return getPersistence().findByCPI_A(corpProjectId, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .findByCPI_A(corpProjectId, archived, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCPI_A(corpProjectId, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByCPI_A_First(long corpProjectId,
		boolean archived, OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_A_First(corpProjectId, archived, orderByComparator);
	}

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCPI_A_First(long corpProjectId,
		boolean archived, OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .fetchByCPI_A_First(corpProjectId, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByCPI_A_Last(long corpProjectId,
		boolean archived, OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_A_Last(corpProjectId, archived, orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCPI_A_Last(long corpProjectId,
		boolean archived, OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .fetchByCPI_A_Last(corpProjectId, archived, orderByComparator);
	}

	/**
	* Returns the l c s projects before and after the current l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the primary key of the current l c s project
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public static LCSProject[] findByCPI_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_A_PrevAndNext(lcsProjectId, corpProjectId,
			archived, orderByComparator);
	}

	/**
	* Removes all the l c s projects where corpProjectId = &#63; and archived = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	*/
	public static void removeByCPI_A(long corpProjectId, boolean archived) {
		getPersistence().removeByCPI_A(corpProjectId, archived);
	}

	/**
	* Returns the number of l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @return the number of matching l c s projects
	*/
	public static int countByCPI_A(long corpProjectId, boolean archived) {
		return getPersistence().countByCPI_A(corpProjectId, archived);
	}

	/**
	* Returns all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s projects
	*/
	public static List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived) {
		return getPersistence().findByCPI_N_A(corpProjectId, name, archived);
	}

	/**
	* Returns a range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @return the range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end) {
		return getPersistence()
				   .findByCPI_N_A(corpProjectId, name, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .findByCPI_N_A(corpProjectId, name, archived, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s projects
	*/
	public static List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCPI_N_A(corpProjectId, name, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByCPI_N_A_First(long corpProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_N_A_First(corpProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCPI_N_A_First(long corpProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .fetchByCPI_N_A_First(corpProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public static LCSProject findByCPI_N_A_Last(long corpProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_N_A_Last(corpProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public static LCSProject fetchByCPI_N_A_Last(long corpProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence()
				   .fetchByCPI_N_A_Last(corpProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the l c s projects before and after the current l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the primary key of the current l c s project
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public static LCSProject[] findByCPI_N_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, java.lang.String name, boolean archived,
		OrderByComparator<LCSProject> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence()
				   .findByCPI_N_A_PrevAndNext(lcsProjectId, corpProjectId,
			name, archived, orderByComparator);
	}

	/**
	* Removes all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	*/
	public static void removeByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived) {
		getPersistence().removeByCPI_N_A(corpProjectId, name, archived);
	}

	/**
	* Returns the number of l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s projects
	*/
	public static int countByCPI_N_A(long corpProjectId, java.lang.String name,
		boolean archived) {
		return getPersistence().countByCPI_N_A(corpProjectId, name, archived);
	}

	/**
	* Caches the l c s project in the entity cache if it is enabled.
	*
	* @param lcsProject the l c s project
	*/
	public static void cacheResult(LCSProject lcsProject) {
		getPersistence().cacheResult(lcsProject);
	}

	/**
	* Caches the l c s projects in the entity cache if it is enabled.
	*
	* @param lcsProjects the l c s projects
	*/
	public static void cacheResult(List<LCSProject> lcsProjects) {
		getPersistence().cacheResult(lcsProjects);
	}

	/**
	* Creates a new l c s project with the primary key. Does not add the l c s project to the database.
	*
	* @param lcsProjectId the primary key for the new l c s project
	* @return the new l c s project
	*/
	public static LCSProject create(long lcsProjectId) {
		return getPersistence().create(lcsProjectId);
	}

	/**
	* Removes the l c s project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project that was removed
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public static LCSProject remove(long lcsProjectId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().remove(lcsProjectId);
	}

	public static LCSProject updateImpl(LCSProject lcsProject) {
		return getPersistence().updateImpl(lcsProject);
	}

	/**
	* Returns the l c s project with the primary key or throws a {@link NoSuchLCSProjectException} if it could not be found.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public static LCSProject findByPrimaryKey(long lcsProjectId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSProjectException {
		return getPersistence().findByPrimaryKey(lcsProjectId);
	}

	/**
	* Returns the l c s project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project, or <code>null</code> if a l c s project with the primary key could not be found
	*/
	public static LCSProject fetchByPrimaryKey(long lcsProjectId) {
		return getPersistence().fetchByPrimaryKey(lcsProjectId);
	}

	public static java.util.Map<java.io.Serializable, LCSProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s projects.
	*
	* @return the l c s projects
	*/
	public static List<LCSProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @return the range of l c s projects
	*/
	public static List<LCSProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s projects
	*/
	public static List<LCSProject> findAll(int start, int end,
		OrderByComparator<LCSProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s projects
	*/
	public static List<LCSProject> findAll(int start, int end,
		OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s projects.
	*
	* @return the number of l c s projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LCSProjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSProjectPersistence, LCSProjectPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSProjectPersistence.class);
}