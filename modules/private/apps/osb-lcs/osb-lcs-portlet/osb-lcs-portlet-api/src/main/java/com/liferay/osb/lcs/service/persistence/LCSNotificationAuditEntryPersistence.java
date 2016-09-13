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

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException;
import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s notification audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSNotificationAuditEntryPersistenceImpl
 * @see LCSNotificationAuditEntryUtil
 * @generated
 */
@ProviderType
public interface LCSNotificationAuditEntryPersistence extends BasePersistence<LCSNotificationAuditEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSNotificationAuditEntryUtil} to access the l c s notification audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId);

	/**
	* Returns a range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end);

	/**
	* Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns the l c s notification audit entries before and after the current l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsNotificationAuditEntryId the primary key of the current l c s notification audit entry
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public LCSNotificationAuditEntry[] findByLCSClusterNodeId_PrevAndNext(
		long lcsNotificationAuditEntryId, long lcsClusterNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds);

	/**
	* Returns a range of all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end);

	/**
	* Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s notification audit entries where lcsClusterNodeId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s notification audit entries where lcsClusterNodeId = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	*/
	public void removeByLCSClusterNodeId(long lcsClusterNodeId);

	/**
	* Returns the number of l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the number of matching l c s notification audit entries
	*/
	public int countByLCSClusterNodeId(long lcsClusterNodeId);

	/**
	* Returns the number of l c s notification audit entries where lcsClusterNodeId = any &#63;.
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the number of matching l c s notification audit entries
	*/
	public int countByLCSClusterNodeId(long[] lcsClusterNodeIds);

	/**
	* Returns all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @return the matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByU_LCNI_T(
		long userId, long lcsClusterNodeId, int type);

	/**
	* Returns a range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByU_LCNI_T(
		long userId, long lcsClusterNodeId, int type, int start, int end);

	/**
	* Returns an ordered range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByU_LCNI_T(
		long userId, long lcsClusterNodeId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findByU_LCNI_T(
		long userId, long lcsClusterNodeId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry findByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns the first l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry fetchByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns the last l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry findByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns the last l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public LCSNotificationAuditEntry fetchByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns the l c s notification audit entries before and after the current l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param lcsNotificationAuditEntryId the primary key of the current l c s notification audit entry
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public LCSNotificationAuditEntry[] findByU_LCNI_T_PrevAndNext(
		long lcsNotificationAuditEntryId, long userId, long lcsClusterNodeId,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Removes all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	*/
	public void removeByU_LCNI_T(long userId, long lcsClusterNodeId, int type);

	/**
	* Returns the number of l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @return the number of matching l c s notification audit entries
	*/
	public int countByU_LCNI_T(long userId, long lcsClusterNodeId, int type);

	/**
	* Caches the l c s notification audit entry in the entity cache if it is enabled.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	*/
	public void cacheResult(LCSNotificationAuditEntry lcsNotificationAuditEntry);

	/**
	* Caches the l c s notification audit entries in the entity cache if it is enabled.
	*
	* @param lcsNotificationAuditEntries the l c s notification audit entries
	*/
	public void cacheResult(
		java.util.List<LCSNotificationAuditEntry> lcsNotificationAuditEntries);

	/**
	* Creates a new l c s notification audit entry with the primary key. Does not add the l c s notification audit entry to the database.
	*
	* @param lcsNotificationAuditEntryId the primary key for the new l c s notification audit entry
	* @return the new l c s notification audit entry
	*/
	public LCSNotificationAuditEntry create(long lcsNotificationAuditEntryId);

	/**
	* Removes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public LCSNotificationAuditEntry remove(long lcsNotificationAuditEntryId)
		throws NoSuchLCSNotificationAuditEntryException;

	public LCSNotificationAuditEntry updateImpl(
		LCSNotificationAuditEntry lcsNotificationAuditEntry);

	/**
	* Returns the l c s notification audit entry with the primary key or throws a {@link NoSuchLCSNotificationAuditEntryException} if it could not be found.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public LCSNotificationAuditEntry findByPrimaryKey(
		long lcsNotificationAuditEntryId)
		throws NoSuchLCSNotificationAuditEntryException;

	/**
	* Returns the l c s notification audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry, or <code>null</code> if a l c s notification audit entry with the primary key could not be found
	*/
	public LCSNotificationAuditEntry fetchByPrimaryKey(
		long lcsNotificationAuditEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LCSNotificationAuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s notification audit entries.
	*
	* @return the l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findAll();

	/**
	* Returns a range of all the l c s notification audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s notification audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s notification audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s notification audit entries
	*/
	public java.util.List<LCSNotificationAuditEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s notification audit entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s notification audit entries.
	*
	* @return the number of l c s notification audit entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}