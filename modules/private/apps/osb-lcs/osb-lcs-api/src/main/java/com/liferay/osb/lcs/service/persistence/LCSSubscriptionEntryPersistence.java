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

import com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl
 * @see LCSSubscriptionEntryUtil
 * @generated
 */
@ProviderType
public interface LCSSubscriptionEntryPersistence extends BasePersistence<LCSSubscriptionEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSSubscriptionEntryUtil} to access the l c s subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByActive(boolean active);

	/**
	* Returns a range of all the l c s subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end);

	/**
	* Returns an ordered range of all the l c s subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s subscription entries where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the first l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the last l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the last l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry[] findByActive_PrevAndNext(
		long lcsSubscriptionEntryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Removes all the l c s subscription entries where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of l c s subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public int countByActive(boolean active);

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId);

	/**
	* Returns a range of all the l c s subscription entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry[] findByLCSProjectId_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public void removeByLCSProjectId(long lcsProjectId);

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s subscription entries
	*/
	public int countByLCSProjectId(long lcsProjectId);

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active);

	/**
	* Returns a range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLPI_A_First(long lcsProjectId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLPI_A_First(long lcsProjectId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLPI_A_Last(long lcsProjectId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLPI_A_Last(long lcsProjectId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry[] findByLPI_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; and active = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	*/
	public void removeByLPI_A(long lcsProjectId, boolean active);

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public int countByLPI_A(long lcsProjectId, boolean active);

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_T_A(
		long lcsProjectId, java.lang.String type, boolean active);

	/**
	* Returns a range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_T_A(
		long lcsProjectId, java.lang.String type, boolean active, int start,
		int end);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_T_A(
		long lcsProjectId, java.lang.String type, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findByLPI_T_A(
		long lcsProjectId, java.lang.String type, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLPI_T_A_First(long lcsProjectId,
		java.lang.String type, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLPI_T_A_First(long lcsProjectId,
		java.lang.String type, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry findByLPI_T_A_Last(long lcsProjectId,
		java.lang.String type, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public LCSSubscriptionEntry fetchByLPI_T_A_Last(long lcsProjectId,
		java.lang.String type, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry[] findByLPI_T_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, java.lang.String type,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	*/
	public void removeByLPI_T_A(long lcsProjectId, java.lang.String type,
		boolean active);

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public int countByLPI_T_A(long lcsProjectId, java.lang.String type,
		boolean active);

	/**
	* Caches the l c s subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	*/
	public void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry);

	/**
	* Caches the l c s subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the l c s subscription entries
	*/
	public void cacheResult(
		java.util.List<LCSSubscriptionEntry> lcsSubscriptionEntries);

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public LCSSubscriptionEntry create(long lcsSubscriptionEntryId);

	/**
	* Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException;

	public LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry);

	/**
	* Returns the l c s subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry findByPrimaryKey(long lcsSubscriptionEntryId)
		throws NoSuchLCSSubscriptionEntryException;

	/**
	* Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	*/
	public LCSSubscriptionEntry fetchByPrimaryKey(long lcsSubscriptionEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s subscription entries.
	*
	* @return the l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll();

	/**
	* Returns a range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s subscription entries
	*/
	public java.util.List<LCSSubscriptionEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s subscription entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}