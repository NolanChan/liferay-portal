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

import com.liferay.osb.lcs.model.LCSRole;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s role service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSRolePersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSRolePersistenceImpl
 * @generated
 */
@ProviderType
public class LCSRoleUtil {
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
	public static void clearCache(LCSRole lcsRole) {
		getPersistence().clearCache(lcsRole);
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
	public static List<LCSRole> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSRole update(LCSRole lcsRole) {
		return getPersistence().update(lcsRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSRole update(LCSRole lcsRole, ServiceContext serviceContext) {
		return getPersistence().update(lcsRole, serviceContext);
	}

	/**
	* Returns all the l c s roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the l c s roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByUserId(long userId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByUserId_First(long userId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByUserId_First(long userId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByUserId_Last(long userId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByUserId_Last(long userId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByUserId_PrevAndNext(long lcsRoleId,
		long userId, OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByUserId_PrevAndNext(lcsRoleId, userId,
			orderByComparator);
	}

	/**
	* Removes all the l c s roles where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of l c s roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching l c s roles
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the l c s roles where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByLCSProjectId(long lcsProjectId) {
		return getPersistence().findByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns a range of all the l c s roles where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end) {
		return getPersistence().findByLCSProjectId(lcsProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByLCSProjectId_PrevAndNext(long lcsRoleId,
		long lcsProjectId, OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSProjectId_PrevAndNext(lcsRoleId, lcsProjectId,
			orderByComparator);
	}

	/**
	* Removes all the l c s roles where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public static void removeByLCSProjectId(long lcsProjectId) {
		getPersistence().removeByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns the number of l c s roles where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s roles
	*/
	public static int countByLCSProjectId(long lcsProjectId) {
		return getPersistence().countByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns all the l c s roles where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByLCSClusterEntryId(long lcsClusterEntryId) {
		return getPersistence().findByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns a range of all the l c s roles where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLCSClusterEntryId_First(
		long lcsClusterEntryId, OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSClusterEntryId_First(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLCSClusterEntryId_First(
		long lcsClusterEntryId, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterEntryId_First(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLCSClusterEntryId_Last(long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSClusterEntryId_Last(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLCSClusterEntryId_Last(
		long lcsClusterEntryId, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterEntryId_Last(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByLCSClusterEntryId_PrevAndNext(
		long lcsRoleId, long lcsClusterEntryId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLCSClusterEntryId_PrevAndNext(lcsRoleId,
			lcsClusterEntryId, orderByComparator);
	}

	/**
	* Removes all the l c s roles where lcsClusterEntryId = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	*/
	public static void removeByLCSClusterEntryId(long lcsClusterEntryId) {
		getPersistence().removeByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns the number of l c s roles where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s roles
	*/
	public static int countByLCSClusterEntryId(long lcsClusterEntryId) {
		return getPersistence().countByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByU_LPI(long userId, long lcsProjectId) {
		return getPersistence().findByU_LPI(userId, lcsProjectId);
	}

	/**
	* Returns a range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end) {
		return getPersistence().findByU_LPI(userId, lcsProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByU_LPI(userId, lcsProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_LPI(userId, lcsProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByU_LPI_First(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByU_LPI_First(userId, lcsProjectId, orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_LPI_First(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByU_LPI_First(userId, lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByU_LPI_Last(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByU_LPI_Last(userId, lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_LPI_Last(long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByU_LPI_Last(userId, lcsProjectId, orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByU_LPI_PrevAndNext(long lcsRoleId,
		long userId, long lcsProjectId,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByU_LPI_PrevAndNext(lcsRoleId, userId, lcsProjectId,
			orderByComparator);
	}

	/**
	* Removes all the l c s roles where userId = &#63; and lcsProjectId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	*/
	public static void removeByU_LPI(long userId, long lcsProjectId) {
		getPersistence().removeByU_LPI(userId, lcsProjectId);
	}

	/**
	* Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s roles
	*/
	public static int countByU_LPI(long userId, long lcsProjectId) {
		return getPersistence().countByU_LPI(userId, lcsProjectId);
	}

	/**
	* Returns all the l c s roles where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByU_R(long userId, int role) {
		return getPersistence().findByU_R(userId, role);
	}

	/**
	* Returns a range of all the l c s roles where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByU_R(long userId, int role, int start,
		int end) {
		return getPersistence().findByU_R(userId, role, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where userId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByU_R(long userId, int role, int start,
		int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R(userId, role, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByU_R_First(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().findByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_R_First(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence().fetchByU_R_First(userId, role, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByU_R_Last(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().findByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_R_Last(long userId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence().fetchByU_R_Last(userId, role, orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByU_R_PrevAndNext(long lcsRoleId, long userId,
		int role, OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByU_R_PrevAndNext(lcsRoleId, userId, role,
			orderByComparator);
	}

	/**
	* Removes all the l c s roles where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public static void removeByU_R(long userId, int role) {
		getPersistence().removeByU_R(userId, role);
	}

	/**
	* Returns the number of l c s roles where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching l c s roles
	*/
	public static int countByU_R(long userId, int role) {
		return getPersistence().countByU_R(userId, role);
	}

	/**
	* Returns all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @return the matching l c s roles
	*/
	public static List<LCSRole> findByLPI_R(long lcsProjectId, int role) {
		return getPersistence().findByLPI_R(lcsProjectId, role);
	}

	/**
	* Returns a range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of matching l c s roles
	*/
	public static List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end) {
		return getPersistence().findByLPI_R(lcsProjectId, role, start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end, OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .findByLPI_R(lcsProjectId, role, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s roles
	*/
	public static List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end, OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_R(lcsProjectId, role, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLPI_R_First(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLPI_R_First(lcsProjectId, role, orderByComparator);
	}

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLPI_R_First(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_R_First(lcsProjectId, role, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByLPI_R_Last(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLPI_R_Last(lcsProjectId, role, orderByComparator);
	}

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByLPI_R_Last(long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_R_Last(lcsProjectId, role, orderByComparator);
	}

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole[] findByLPI_R_PrevAndNext(long lcsRoleId,
		long lcsProjectId, int role,
		OrderByComparator<LCSRole> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByLPI_R_PrevAndNext(lcsRoleId, lcsProjectId, role,
			orderByComparator);
	}

	/**
	* Removes all the l c s roles where lcsProjectId = &#63; and role = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	*/
	public static void removeByLPI_R(long lcsProjectId, int role) {
		getPersistence().removeByLPI_R(lcsProjectId, role);
	}

	/**
	* Returns the number of l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @return the number of matching l c s roles
	*/
	public static int countByLPI_R(long lcsProjectId, int role) {
		return getPersistence().countByLPI_R(lcsProjectId, role);
	}

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or throws a {@link NoSuchLCSRoleException} if it could not be found.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public static LCSRole findByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .findByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId);
	}

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) {
		return getPersistence()
				   .fetchByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId);
	}

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public static LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId,
			retrieveFromCache);
	}

	/**
	* Removes the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the l c s role that was removed
	*/
	public static LCSRole removeByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence()
				   .removeByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId);
	}

	/**
	* Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s roles
	*/
	public static int countByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) {
		return getPersistence()
				   .countByU_LPI_LCEI(userId, lcsProjectId, lcsClusterEntryId);
	}

	/**
	* Caches the l c s role in the entity cache if it is enabled.
	*
	* @param lcsRole the l c s role
	*/
	public static void cacheResult(LCSRole lcsRole) {
		getPersistence().cacheResult(lcsRole);
	}

	/**
	* Caches the l c s roles in the entity cache if it is enabled.
	*
	* @param lcsRoles the l c s roles
	*/
	public static void cacheResult(List<LCSRole> lcsRoles) {
		getPersistence().cacheResult(lcsRoles);
	}

	/**
	* Creates a new l c s role with the primary key. Does not add the l c s role to the database.
	*
	* @param lcsRoleId the primary key for the new l c s role
	* @return the new l c s role
	*/
	public static LCSRole create(long lcsRoleId) {
		return getPersistence().create(lcsRoleId);
	}

	/**
	* Removes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role that was removed
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole remove(long lcsRoleId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().remove(lcsRoleId);
	}

	public static LCSRole updateImpl(LCSRole lcsRole) {
		return getPersistence().updateImpl(lcsRole);
	}

	/**
	* Returns the l c s role with the primary key or throws a {@link NoSuchLCSRoleException} if it could not be found.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public static LCSRole findByPrimaryKey(long lcsRoleId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSRoleException {
		return getPersistence().findByPrimaryKey(lcsRoleId);
	}

	/**
	* Returns the l c s role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role, or <code>null</code> if a l c s role with the primary key could not be found
	*/
	public static LCSRole fetchByPrimaryKey(long lcsRoleId) {
		return getPersistence().fetchByPrimaryKey(lcsRoleId);
	}

	public static java.util.Map<java.io.Serializable, LCSRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s roles.
	*
	* @return the l c s roles
	*/
	public static List<LCSRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of l c s roles
	*/
	public static List<LCSRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s roles
	*/
	public static List<LCSRole> findAll(int start, int end,
		OrderByComparator<LCSRole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s roles
	*/
	public static List<LCSRole> findAll(int start, int end,
		OrderByComparator<LCSRole> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s roles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s roles.
	*
	* @return the number of l c s roles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LCSRolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSRolePersistence, LCSRolePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSRolePersistence.class);
}