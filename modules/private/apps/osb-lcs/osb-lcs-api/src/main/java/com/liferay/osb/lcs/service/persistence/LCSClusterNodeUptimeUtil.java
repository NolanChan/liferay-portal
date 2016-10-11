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

import com.liferay.osb.lcs.model.LCSClusterNodeUptime;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s cluster node uptime service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodeUptimePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimePersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodeUptimePersistenceImpl
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeUtil {
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
	public static void clearCache(LCSClusterNodeUptime lcsClusterNodeUptime) {
		getPersistence().clearCache(lcsClusterNodeUptime);
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
	public static List<LCSClusterNodeUptime> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSClusterNodeUptime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSClusterNodeUptime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSClusterNodeUptime update(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		return getPersistence().update(lcsClusterNodeUptime);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSClusterNodeUptime update(
		LCSClusterNodeUptime lcsClusterNodeUptime, ServiceContext serviceContext) {
		return getPersistence().update(lcsClusterNodeUptime, serviceContext);
	}

	/**
	* Returns all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId) {
		return getPersistence().findByLCSClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Returns a range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @return the range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence()
				   .findByLCSClusterNodeId_First(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterNodeId_First(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence()
				   .findByLCSClusterNodeId_Last(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterNodeId_Last(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the l c s cluster node uptimes before and after the current l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeUptimeId the primary key of the current l c s cluster node uptime
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public static LCSClusterNodeUptime[] findByLCSClusterNodeId_PrevAndNext(
		long lcsClusterNodeUptimeId, long lcsClusterNodeId,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence()
				   .findByLCSClusterNodeId_PrevAndNext(lcsClusterNodeUptimeId,
			lcsClusterNodeId, orderByComparator);
	}

	/**
	* Returns all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds) {
		return getPersistence().findByLCSClusterNodeId(lcsClusterNodeIds);
	}

	/**
	* Returns a range of all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @return the range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes where lcsClusterNodeId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster node uptimes where lcsClusterNodeId = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	*/
	public static void removeByLCSClusterNodeId(long lcsClusterNodeId) {
		getPersistence().removeByLCSClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the number of matching l c s cluster node uptimes
	*/
	public static int countByLCSClusterNodeId(long lcsClusterNodeId) {
		return getPersistence().countByLCSClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the number of matching l c s cluster node uptimes
	*/
	public static int countByLCSClusterNodeId(long[] lcsClusterNodeIds) {
		return getPersistence().countByLCSClusterNodeId(lcsClusterNodeIds);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime findByLCNI_ST(long lcsClusterNodeId,
		long startTime)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().findByLCNI_ST(lcsClusterNodeId, startTime);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime) {
		return getPersistence().fetchByLCNI_ST(lcsClusterNodeId, startTime);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByLCNI_ST(lcsClusterNodeId, startTime,
			retrieveFromCache);
	}

	/**
	* Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the l c s cluster node uptime that was removed
	*/
	public static LCSClusterNodeUptime removeByLCNI_ST(long lcsClusterNodeId,
		long startTime)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().removeByLCNI_ST(lcsClusterNodeId, startTime);
	}

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and startTime = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the number of matching l c s cluster node uptimes
	*/
	public static int countByLCNI_ST(long lcsClusterNodeId, long startTime) {
		return getPersistence().countByLCNI_ST(lcsClusterNodeId, startTime);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime findByLCNI_ET(long lcsClusterNodeId,
		long endTime)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().findByLCNI_ET(lcsClusterNodeId, endTime);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime) {
		return getPersistence().fetchByLCNI_ET(lcsClusterNodeId, endTime);
	}

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public static LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByLCNI_ET(lcsClusterNodeId, endTime, retrieveFromCache);
	}

	/**
	* Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the l c s cluster node uptime that was removed
	*/
	public static LCSClusterNodeUptime removeByLCNI_ET(long lcsClusterNodeId,
		long endTime)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().removeByLCNI_ET(lcsClusterNodeId, endTime);
	}

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and endTime = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the number of matching l c s cluster node uptimes
	*/
	public static int countByLCNI_ET(long lcsClusterNodeId, long endTime) {
		return getPersistence().countByLCNI_ET(lcsClusterNodeId, endTime);
	}

	/**
	* Caches the l c s cluster node uptime in the entity cache if it is enabled.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	*/
	public static void cacheResult(LCSClusterNodeUptime lcsClusterNodeUptime) {
		getPersistence().cacheResult(lcsClusterNodeUptime);
	}

	/**
	* Caches the l c s cluster node uptimes in the entity cache if it is enabled.
	*
	* @param lcsClusterNodeUptimes the l c s cluster node uptimes
	*/
	public static void cacheResult(
		List<LCSClusterNodeUptime> lcsClusterNodeUptimes) {
		getPersistence().cacheResult(lcsClusterNodeUptimes);
	}

	/**
	* Creates a new l c s cluster node uptime with the primary key. Does not add the l c s cluster node uptime to the database.
	*
	* @param lcsClusterNodeUptimeId the primary key for the new l c s cluster node uptime
	* @return the new l c s cluster node uptime
	*/
	public static LCSClusterNodeUptime create(long lcsClusterNodeUptimeId) {
		return getPersistence().create(lcsClusterNodeUptimeId);
	}

	/**
	* Removes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public static LCSClusterNodeUptime remove(long lcsClusterNodeUptimeId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().remove(lcsClusterNodeUptimeId);
	}

	public static LCSClusterNodeUptime updateImpl(
		LCSClusterNodeUptime lcsClusterNodeUptime) {
		return getPersistence().updateImpl(lcsClusterNodeUptime);
	}

	/**
	* Returns the l c s cluster node uptime with the primary key or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public static LCSClusterNodeUptime findByPrimaryKey(
		long lcsClusterNodeUptimeId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException {
		return getPersistence().findByPrimaryKey(lcsClusterNodeUptimeId);
	}

	/**
	* Returns the l c s cluster node uptime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime, or <code>null</code> if a l c s cluster node uptime with the primary key could not be found
	*/
	public static LCSClusterNodeUptime fetchByPrimaryKey(
		long lcsClusterNodeUptimeId) {
		return getPersistence().fetchByPrimaryKey(lcsClusterNodeUptimeId);
	}

	public static java.util.Map<java.io.Serializable, LCSClusterNodeUptime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s cluster node uptimes.
	*
	* @return the l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s cluster node uptimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @return the range of l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findAll(int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster node uptimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s cluster node uptimes
	*/
	public static List<LCSClusterNodeUptime> findAll(int start, int end,
		OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster node uptimes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s cluster node uptimes.
	*
	* @return the number of l c s cluster node uptimes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LCSClusterNodeUptimePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterNodeUptimePersistence, LCSClusterNodeUptimePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterNodeUptimePersistence.class);
}