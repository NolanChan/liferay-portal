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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException;
import com.liferay.osb.lcs.model.LCSClusterNode;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s cluster node service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodePersistenceImpl
 * @see LCSClusterNodeUtil
 * @generated
 */
@ProviderType
public interface LCSClusterNodePersistence extends BasePersistence<LCSClusterNode> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterNodeUtil} to access the l c s cluster node persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCSClusterEntryId_First(
		long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCSClusterEntryId_Last(long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCSClusterEntryId_Last(
		long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public LCSClusterNode[] findByLCSClusterEntryId_PrevAndNext(
		long lcsClusterNodeId, long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCSClusterEntryId(
		long[] lcsClusterEntryIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	*/
	public void removeByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63;.
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCSClusterEntryId(long[] lcsClusterEntryIds);

	/**
	* Returns all the l c s cluster nodes where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByBuildNumber(int buildNumber);

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
	public java.util.List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end);

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
	public java.util.List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByBuildNumber(int buildNumber,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByBuildNumber_First(int buildNumber,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByBuildNumber_First(int buildNumber,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByBuildNumber_Last(int buildNumber,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByBuildNumber_Last(int buildNumber,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the l c s cluster nodes before and after the current l c s cluster node in the ordered set where buildNumber = &#63;.
	*
	* @param lcsClusterNodeId the primary key of the current l c s cluster node
	* @param buildNumber the build number
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public LCSClusterNode[] findByBuildNumber_PrevAndNext(
		long lcsClusterNodeId, int buildNumber,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Removes all the l c s cluster nodes where buildNumber = &#63; from the database.
	*
	* @param buildNumber the build number
	*/
	public void removeByBuildNumber(int buildNumber);

	/**
	* Returns the number of l c s cluster nodes where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the number of matching l c s cluster nodes
	*/
	public int countByBuildNumber(int buildNumber);

	/**
	* Returns the l c s cluster node where key = &#63; or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	*
	* @param key the key
	* @return the matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByKey(java.lang.String key)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByKey(java.lang.String key);

	/**
	* Returns the l c s cluster node where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByKey(java.lang.String key,
		boolean retrieveFromCache);

	/**
	* Removes the l c s cluster node where key = &#63; from the database.
	*
	* @param key the key
	* @return the l c s cluster node that was removed
	*/
	public LCSClusterNode removeByKey(java.lang.String key)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the number of l c s cluster nodes where key = &#63;.
	*
	* @param key the key
	* @return the number of matching l c s cluster nodes
	*/
	public int countByKey(java.lang.String key);

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name);

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
	public java.util.List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCEI_N(long lcsClusterEntryId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCEI_N_First(long lcsClusterEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_N_First(long lcsClusterEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCEI_N_Last(long lcsClusterEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_N_Last(long lcsClusterEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public LCSClusterNode[] findByLCEI_N_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	*/
	public void removeByLCEI_N(long lcsClusterEntryId, java.lang.String name);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCEI_N(long lcsClusterEntryId, java.lang.String name);

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(long lcsClusterEntryId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCEI_A_First(long lcsClusterEntryId,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_A_First(long lcsClusterEntryId,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_A_Last(long lcsClusterEntryId,
		boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public LCSClusterNode[] findByLCEI_A_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

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
	public java.util.List<LCSClusterNode> findByLCEI_A(
		long[] lcsClusterEntryIds, boolean archived);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(
		long[] lcsClusterEntryIds, boolean archived, int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(
		long[] lcsClusterEntryIds, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCEI_A(
		long[] lcsClusterEntryIds, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	*/
	public void removeByLCEI_A(long lcsClusterEntryId, boolean archived);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCEI_A(long lcsClusterEntryId, boolean archived);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = any &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryIds the lcs cluster entry IDs
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCEI_A(long[] lcsClusterEntryIds, boolean archived);

	/**
	* Returns all the l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived);

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
	public java.util.List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end);

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
	public java.util.List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByBN_A(int buildNumber,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByBN_A_First(int buildNumber, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByBN_A_First(int buildNumber, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode findByBN_A_Last(int buildNumber, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByBN_A_Last(int buildNumber, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public LCSClusterNode[] findByBN_A_PrevAndNext(long lcsClusterNodeId,
		int buildNumber, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Removes all the l c s cluster nodes where buildNumber = &#63; and archived = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param archived the archived
	*/
	public void removeByBN_A(int buildNumber, boolean archived);

	/**
	* Returns the number of l c s cluster nodes where buildNumber = &#63; and archived = &#63;.
	*
	* @param buildNumber the build number
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public int countByBN_A(int buildNumber, boolean archived);

	/**
	* Returns all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findByLCEI_N_A(
		long lcsClusterEntryId, java.lang.String name, boolean archived);

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
	public java.util.List<LCSClusterNode> findByLCEI_N_A(
		long lcsClusterEntryId, java.lang.String name, boolean archived,
		int start, int end);

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
	public java.util.List<LCSClusterNode> findByLCEI_N_A(
		long lcsClusterEntryId, java.lang.String name, boolean archived,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findByLCEI_N_A(
		long lcsClusterEntryId, java.lang.String name, boolean archived,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

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
	public LCSClusterNode findByLCEI_N_A_First(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the first l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_N_A_First(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public LCSClusterNode findByLCEI_N_A_Last(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the last l c s cluster node in the ordered set where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node, or <code>null</code> if a matching l c s cluster node could not be found
	*/
	public LCSClusterNode fetchByLCEI_N_A_Last(long lcsClusterEntryId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public LCSClusterNode[] findByLCEI_N_A_PrevAndNext(long lcsClusterNodeId,
		long lcsClusterEntryId, java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator)
		throws NoSuchLCSClusterNodeException;

	/**
	* Removes all the l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	*/
	public void removeByLCEI_N_A(long lcsClusterEntryId, java.lang.String name,
		boolean archived);

	/**
	* Returns the number of l c s cluster nodes where lcsClusterEntryId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s cluster nodes
	*/
	public int countByLCEI_N_A(long lcsClusterEntryId, java.lang.String name,
		boolean archived);

	/**
	* Caches the l c s cluster node in the entity cache if it is enabled.
	*
	* @param lcsClusterNode the l c s cluster node
	*/
	public void cacheResult(LCSClusterNode lcsClusterNode);

	/**
	* Caches the l c s cluster nodes in the entity cache if it is enabled.
	*
	* @param lcsClusterNodes the l c s cluster nodes
	*/
	public void cacheResult(java.util.List<LCSClusterNode> lcsClusterNodes);

	/**
	* Creates a new l c s cluster node with the primary key. Does not add the l c s cluster node to the database.
	*
	* @param lcsClusterNodeId the primary key for the new l c s cluster node
	* @return the new l c s cluster node
	*/
	public LCSClusterNode create(long lcsClusterNodeId);

	/**
	* Removes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public LCSClusterNode remove(long lcsClusterNodeId)
		throws NoSuchLCSClusterNodeException;

	public LCSClusterNode updateImpl(LCSClusterNode lcsClusterNode);

	/**
	* Returns the l c s cluster node with the primary key or throws a {@link NoSuchLCSClusterNodeException} if it could not be found.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node
	* @throws NoSuchLCSClusterNodeException if a l c s cluster node with the primary key could not be found
	*/
	public LCSClusterNode findByPrimaryKey(long lcsClusterNodeId)
		throws NoSuchLCSClusterNodeException;

	/**
	* Returns the l c s cluster node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node, or <code>null</code> if a l c s cluster node with the primary key could not be found
	*/
	public LCSClusterNode fetchByPrimaryKey(long lcsClusterNodeId);

	@Override
	public java.util.Map<java.io.Serializable, LCSClusterNode> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s cluster nodes.
	*
	* @return the l c s cluster nodes
	*/
	public java.util.List<LCSClusterNode> findAll();

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
	public java.util.List<LCSClusterNode> findAll(int start, int end);

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
	public java.util.List<LCSClusterNode> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator);

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
	public java.util.List<LCSClusterNode> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNode> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster nodes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s cluster nodes.
	*
	* @return the number of l c s cluster nodes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}