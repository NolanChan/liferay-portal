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

import com.liferay.osb.lcs.model.LCSClusterNode;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s cluster node service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterNodePersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodePersistenceImpl
 * @generated
 */
@ProviderType
public class LCSClusterNodeUtil {
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
	public static void clearCache(LCSClusterNode lcsClusterNode) {
		getPersistence().clearCache(lcsClusterNode);
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
	public static List<LCSClusterNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSClusterNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSClusterNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSClusterNode update(LCSClusterNode lcsClusterNode) {
		return getPersistence().update(lcsClusterNode);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSClusterNode update(LCSClusterNode lcsClusterNode,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsClusterNode, serviceContext);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId) {
		return getPersistence().findByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCSClusterEntryId_First(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterEntryId_First(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCSClusterEntryId_Last(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCSClusterEntryId_Last(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCSClusterEntryId_Last(
		long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterEntryId_Last(lcsClusterEntryId,
			orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByLCSClusterEntryId_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCSClusterEntryId_PrevAndNext(lcsClusterNodeId,
			lcsClusterEntryId, orderByComparator);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds) {
		return getPersistence().findByLCSClusterEntryId(lcsClusterEntryIds);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryIds, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryIds, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterEntryId(lcsClusterEntryIds, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	*/
	public static void removeByLCSClusterEntryId(long lcsClusterEntryId) {
		getPersistence().removeByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCSClusterEntryId(long lcsClusterEntryId) {
		return getPersistence().countByLCSClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63;.
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCSClusterEntryId(long[] lcsClusterEntryIds) {
		return getPersistence().countByLCSClusterEntryId(lcsClusterEntryIds);
	}

	/**
	* Returns all the l c s cluster nodes where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBuildNumber(int buildNumber) {
		return getPersistence().findByBuildNumber(buildNumber);
	}

	/**
	* Returns a range of all the l c s cluster nodes where buildNumber = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end) {
		return getPersistence().findByBuildNumber(buildNumber, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end, OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByBuildNumber(buildNumber, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBuildNumber(buildNumber, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByBuildNumber_First(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBuildNumber_First(buildNumber, orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByBuildNumber_First(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByBuildNumber_First(buildNumber, orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByBuildNumber_Last(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBuildNumber_Last(buildNumber, orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByBuildNumber_Last(int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByBuildNumber_Last(buildNumber, orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByBuildNumber_PrevAndNext(
		long lcsClusterNodeId, int buildNumber,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBuildNumber_PrevAndNext(lcsClusterNodeId,
			buildNumber, orderByComparator);
	}

	/**
	* Removes all the l c s cluster nodes where buildNumber = &#63; from the database.
	*
	* @param buildNumber the build number
	*/
	public static void removeByBuildNumber(int buildNumber) {
		getPersistence().removeByBuildNumber(buildNumber);
	}

	/**
	* Returns the number of l c s cluster nodes where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByBuildNumber(int buildNumber) {
		return getPersistence().countByBuildNumber(buildNumber);
	}

	/**
	* Returns the l c s cluster node where key = &#63; or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	*
	* @param key the key
	* @return the matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByKey(java.lang.String key)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByKey(java.lang.String key) {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByKey(java.lang.String key,
		boolean retrieveFromCache) {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Removes the l c s cluster node where key = &#63; from the database.
	*
	* @param key the key
	* @return the l c s cluster node that was removed
	*/
	public static LCSClusterNode removeByKey(java.lang.String key)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Returns the number of l c s cluster nodes where key = &#63;.
	*
	* @param key the key
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByKey(java.lang.String key) {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name) {
		return getPersistence().findByLCEI_N(lcsClusterEntryId, name);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end) {
		return getPersistence().findByLCEI_N(lcsClusterEntryId, name, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCEI_N(lcsClusterEntryId, name, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCEI_N(lcsClusterEntryId, name, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_N_First(long lcsClusterEntryId,
		java.lang.String name,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_First(lcsClusterEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_N_First(long lcsClusterEntryId,
		java.lang.String name,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_N_First(lcsClusterEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_N_Last(long lcsClusterEntryId,
		java.lang.String name,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_Last(lcsClusterEntryId, name, orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_N_Last(long lcsClusterEntryId,
		java.lang.String name,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_N_Last(lcsClusterEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByLCEI_N_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId, java.lang.String name,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_PrevAndNext(lcsClusterNodeId,
			lcsClusterEntryId, name, orderByComparator);
	}

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	*/
	public static void removeByLCEI_N(long lcsClusterEntryId,
		java.lang.String name) {
		getPersistence().removeByLCEI_N(lcsClusterEntryId, name);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCEI_N(long lcsClusterEntryId,
		java.lang.String name) {
		return getPersistence().countByLCEI_N(lcsClusterEntryId, name);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived) {
		return getPersistence().findByLCEI_A(lcsClusterEntryId, archived);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryId, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryId, archived, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryId, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_A_First(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_A_First(lcsClusterEntryId, archived,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_A_First(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_A_First(lcsClusterEntryId, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_A_Last(lcsClusterEntryId, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_A_Last(lcsClusterEntryId, archived,
			orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByLCEI_A_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_A_PrevAndNext(lcsClusterNodeId,
			lcsClusterEntryId, archived, orderByComparator);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived) {
		return getPersistence().findByLCEI_A(lcsClusterEntryIds, archived);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryIds, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryIds, archived, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_A(long[] lcsClusterEntryIds,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCEI_A(lcsClusterEntryIds, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	*/
	public static void removeByLCEI_A(long lcsClusterEntryId, boolean archived) {
		getPersistence().removeByLCEI_A(lcsClusterEntryId, archived);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCEI_A(long lcsClusterEntryId, boolean archived) {
		return getPersistence().countByLCEI_A(lcsClusterEntryId, archived);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCEI_A(long[] lcsClusterEntryIds, boolean archived) {
		return getPersistence().countByLCEI_A(lcsClusterEntryIds, archived);
	}

	/**
	* Returns all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived) {
		return getPersistence().findByBN_A(buildNumber, archived);
	}

	/**
	* Returns a range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end) {
		return getPersistence().findByBN_A(buildNumber, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByBN_A(buildNumber, archived, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBN_A(buildNumber, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByBN_A_First(int buildNumber,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBN_A_First(buildNumber, archived, orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByBN_A_First(int buildNumber,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByBN_A_First(buildNumber, archived, orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByBN_A_Last(int buildNumber,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBN_A_Last(buildNumber, archived, orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByBN_A_Last(int buildNumber,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByBN_A_Last(buildNumber, archived, orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByBN_A_PrevAndNext(
		long lcsClusterNodeId, int buildNumber, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByBN_A_PrevAndNext(lcsClusterNodeId, buildNumber,
			archived, orderByComparator);
	}

	/**
	* Removes all the l c s cluster nodes where buildNumber = &#63; and archived = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param archived the archived
	*/
	public static void removeByBN_A(int buildNumber, boolean archived) {
		getPersistence().removeByBN_A(buildNumber, archived);
	}

	/**
	* Returns the number of l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByBN_A(int buildNumber, boolean archived) {
		return getPersistence().countByBN_A(buildNumber, archived);
	}

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived) {
		return getPersistence().findByLCEI_N_A(lcsClusterEntryId, name, archived);
	}

	/**
	* Returns a range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived, int start, int end) {
		return getPersistence()
				   .findByLCEI_N_A(lcsClusterEntryId, name, archived, start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .findByLCEI_N_A(lcsClusterEntryId, name, archived, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster nodes
	*/
	public static List<LCSClusterNode> findByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCEI_N_A(lcsClusterEntryId, name, archived, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_N_A_First(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_A_First(lcsClusterEntryId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_N_A_First(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_N_A_First(lcsClusterEntryId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode findByLCEI_N_A_Last(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_A_Last(lcsClusterEntryId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public static LCSClusterNode fetchByLCEI_N_A_Last(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence()
				   .fetchByLCEI_N_A_Last(lcsClusterEntryId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode[] findByLCEI_N_A_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId, java.lang.String name,
		boolean archived, OrderByComparator<LCSClusterNode> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence()
				   .findByLCEI_N_A_PrevAndNext(lcsClusterNodeId,
			lcsClusterEntryId, name, archived, orderByComparator);
	}

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	*/
	public static void removeByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived) {
		getPersistence().removeByLCEI_N_A(lcsClusterEntryId, name, archived);
	}

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public static int countByLCEI_N_A(long lcsClusterEntryId,
		java.lang.String name, boolean archived) {
		return getPersistence()
				   .countByLCEI_N_A(lcsClusterEntryId, name, archived);
	}

	/**
	* Caches the l c s cluster node in the entity cache if it is enabled.
	*
	* @param lcsClusterNode the l c s cluster node
	*/
	public static void cacheResult(LCSClusterNode lcsClusterNode) {
		getPersistence().cacheResult(lcsClusterNode);
	}

	/**
	* Caches the l c s cluster nodes in the entity cache if it is enabled.
	*
	* @param lcsClusterNodes the l c s cluster nodes
	*/
	public static void cacheResult(List<LCSClusterNode> lcsClusterNodes) {
		getPersistence().cacheResult(lcsClusterNodes);
	}

	/**
	* Creates a new l c s cluster node with the primary key. Does not add the l c s cluster node to the database.
	*
	* @param lcsClusterNodeId the primary key for the new l c s cluster node
	* @return the new l c s cluster node
	*/
	public static LCSClusterNode create(long lcsClusterNodeId) {
		return getPersistence().create(lcsClusterNodeId);
	}

	/**
	* Removes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode remove(long lcsClusterNodeId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence().remove(lcsClusterNodeId);
	}

	public static LCSClusterNode updateImpl(LCSClusterNode lcsClusterNode) {
		return getPersistence().updateImpl(lcsClusterNode);
	}

	/**
	* Returns the l c s cluster node with the primary key or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode findByPrimaryKey(long lcsClusterNodeId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException {
		return getPersistence().findByPrimaryKey(lcsClusterNodeId);
	}

	/**
	* Returns the l c s cluster node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node, or <code>null</code> if a l c s cluster node with the primary key could not be found
	*/
	public static LCSClusterNode fetchByPrimaryKey(long lcsClusterNodeId) {
		return getPersistence().fetchByPrimaryKey(lcsClusterNodeId);
	}

	public static java.util.Map<java.io.Serializable, LCSClusterNode> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s cluster nodes.
	*
	* @return the l c s cluster nodes
	*/
	public static List<LCSClusterNode> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s cluster nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of l c s cluster nodes
	*/
	public static List<LCSClusterNode> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s cluster nodes
	*/
	public static List<LCSClusterNode> findAll(int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s cluster nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s cluster nodes
	*/
	public static List<LCSClusterNode> findAll(int start, int end,
		OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster nodes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s cluster nodes.
	*
	* @return the number of l c s cluster nodes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSClusterNodePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterNodePersistence, LCSClusterNodePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterNodePersistence.class);
}