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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s cluster node uptime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterNodeUptimePersistenceImpl
 * @see LCSClusterNodeUptimeUtil
 * @generated
 */
@ProviderType
public interface LCSClusterNodeUptimePersistence extends BasePersistence<LCSClusterNodeUptime> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterNodeUptimeUtil} to access the l c s cluster node uptime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the matching l c s cluster node uptimes
	*/
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the first l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator);

	/**
	* Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the last l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator);

	/**
	* Returns the l c s cluster node uptimes before and after the current l c s cluster node uptime in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeUptimeId the primary key of the current l c s cluster node uptime
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public LCSClusterNodeUptime[] findByLCSClusterNodeId_PrevAndNext(
		long lcsClusterNodeUptimeId, long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator)
		throws NoSuchLCSClusterNodeUptimeException;

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator);

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
	public java.util.List<LCSClusterNodeUptime> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster node uptimes where lcsClusterNodeId = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	*/
	public void removeByLCSClusterNodeId(long lcsClusterNodeId);

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the number of matching l c s cluster node uptimes
	*/
	public int countByLCSClusterNodeId(long lcsClusterNodeId);

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = any &#63;.
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the number of matching l c s cluster node uptimes
	*/
	public int countByLCSClusterNodeId(long[] lcsClusterNodeIds);

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime findByLCNI_ST(long lcsClusterNodeId,
		long startTime) throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime);

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCNI_ST(long lcsClusterNodeId,
		long startTime, boolean retrieveFromCache);

	/**
	* Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and startTime = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the l c s cluster node uptime that was removed
	*/
	public LCSClusterNodeUptime removeByLCNI_ST(long lcsClusterNodeId,
		long startTime) throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and startTime = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param startTime the start time
	* @return the number of matching l c s cluster node uptimes
	*/
	public int countByLCNI_ST(long lcsClusterNodeId, long startTime);

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the matching l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime findByLCNI_ET(long lcsClusterNodeId,
		long endTime) throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime);

	/**
	* Returns the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster node uptime, or <code>null</code> if a matching l c s cluster node uptime could not be found
	*/
	public LCSClusterNodeUptime fetchByLCNI_ET(long lcsClusterNodeId,
		long endTime, boolean retrieveFromCache);

	/**
	* Removes the l c s cluster node uptime where lcsClusterNodeId = &#63; and endTime = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the l c s cluster node uptime that was removed
	*/
	public LCSClusterNodeUptime removeByLCNI_ET(long lcsClusterNodeId,
		long endTime) throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the number of l c s cluster node uptimes where lcsClusterNodeId = &#63; and endTime = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param endTime the end time
	* @return the number of matching l c s cluster node uptimes
	*/
	public int countByLCNI_ET(long lcsClusterNodeId, long endTime);

	/**
	* Caches the l c s cluster node uptime in the entity cache if it is enabled.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	*/
	public void cacheResult(LCSClusterNodeUptime lcsClusterNodeUptime);

	/**
	* Caches the l c s cluster node uptimes in the entity cache if it is enabled.
	*
	* @param lcsClusterNodeUptimes the l c s cluster node uptimes
	*/
	public void cacheResult(
		java.util.List<LCSClusterNodeUptime> lcsClusterNodeUptimes);

	/**
	* Creates a new l c s cluster node uptime with the primary key. Does not add the l c s cluster node uptime to the database.
	*
	* @param lcsClusterNodeUptimeId the primary key for the new l c s cluster node uptime
	* @return the new l c s cluster node uptime
	*/
	public LCSClusterNodeUptime create(long lcsClusterNodeUptimeId);

	/**
	* Removes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public LCSClusterNodeUptime remove(long lcsClusterNodeUptimeId)
		throws NoSuchLCSClusterNodeUptimeException;

	public LCSClusterNodeUptime updateImpl(
		LCSClusterNodeUptime lcsClusterNodeUptime);

	/**
	* Returns the l c s cluster node uptime with the primary key or throws a {@link NoSuchLCSClusterNodeUptimeException} if it could not be found.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime
	* @throws NoSuchLCSClusterNodeUptimeException if a l c s cluster node uptime with the primary key could not be found
	*/
	public LCSClusterNodeUptime findByPrimaryKey(long lcsClusterNodeUptimeId)
		throws NoSuchLCSClusterNodeUptimeException;

	/**
	* Returns the l c s cluster node uptime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime, or <code>null</code> if a l c s cluster node uptime with the primary key could not be found
	*/
	public LCSClusterNodeUptime fetchByPrimaryKey(long lcsClusterNodeUptimeId);

	@Override
	public java.util.Map<java.io.Serializable, LCSClusterNodeUptime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s cluster node uptimes.
	*
	* @return the l c s cluster node uptimes
	*/
	public java.util.List<LCSClusterNodeUptime> findAll();

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
	public java.util.List<LCSClusterNodeUptime> findAll(int start, int end);

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
	public java.util.List<LCSClusterNodeUptime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator);

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
	public java.util.List<LCSClusterNodeUptime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterNodeUptime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster node uptimes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s cluster node uptimes.
	*
	* @return the number of l c s cluster node uptimes
	*/
	public int countAll();
}