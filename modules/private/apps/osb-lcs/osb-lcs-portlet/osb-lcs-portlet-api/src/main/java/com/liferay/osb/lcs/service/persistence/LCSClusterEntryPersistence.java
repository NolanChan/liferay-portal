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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException;
import com.liferay.osb.lcs.model.LCSClusterEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s cluster entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterEntryPersistenceImpl
 * @see LCSClusterEntryUtil
 * @generated
 */
@ProviderType
public interface LCSClusterEntryPersistence extends BasePersistence<LCSClusterEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterEntryUtil} to access the l c s cluster entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId);

	/**
	* Returns a range of all the l c s cluster entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry[] findByLCSProjectId_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public void removeByLCSProjectId(long lcsProjectId);

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s cluster entries
	*/
	public int countByLCSProjectId(long lcsProjectId);

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @return the matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name);

	/**
	* Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_N_First(long lcsProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_N_First(long lcsProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_N_Last(long lcsProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_N_Last(long lcsProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry[] findByLPI_N_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	*/
	public void removeByLPI_N(long lcsProjectId, java.lang.String name);

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @return the number of matching l c s cluster entries
	*/
	public int countByLPI_N(long lcsProjectId, java.lang.String name);

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @return the matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType);

	/**
	* Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_ST_First(long lcsProjectId,
		java.lang.String subscriptionType,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_ST_First(long lcsProjectId,
		java.lang.String subscriptionType,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_ST_Last(long lcsProjectId,
		java.lang.String subscriptionType,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_ST_Last(long lcsProjectId,
		java.lang.String subscriptionType,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry[] findByLPI_ST_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, java.lang.String subscriptionType,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	*/
	public void removeByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType);

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @return the number of matching l c s cluster entries
	*/
	public int countByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType);

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived);

	/**
	* Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_N_A_First(long lcsProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_N_A_First(long lcsProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_N_A_Last(long lcsProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_N_A_Last(long lcsProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry[] findByLPI_N_A_PrevAndNext(long lcsClusterEntryId,
		long lcsProjectId, java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	*/
	public void removeByLPI_N_A(long lcsProjectId, java.lang.String name,
		boolean archived);

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s cluster entries
	*/
	public int countByLPI_N_A(long lcsProjectId, java.lang.String name,
		boolean archived);

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @return the matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived);

	/**
	* Returns a range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_ST_A_First(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_ST_A_First(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry findByLPI_ST_A_Last(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public LCSClusterEntry fetchByLPI_ST_A_Last(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry[] findByLPI_ST_A_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator)
		throws NoSuchLCSClusterEntryException;

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	*/
	public void removeByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived);

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @return the number of matching l c s cluster entries
	*/
	public int countByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived);

	/**
	* Caches the l c s cluster entry in the entity cache if it is enabled.
	*
	* @param lcsClusterEntry the l c s cluster entry
	*/
	public void cacheResult(LCSClusterEntry lcsClusterEntry);

	/**
	* Caches the l c s cluster entries in the entity cache if it is enabled.
	*
	* @param lcsClusterEntries the l c s cluster entries
	*/
	public void cacheResult(java.util.List<LCSClusterEntry> lcsClusterEntries);

	/**
	* Creates a new l c s cluster entry with the primary key. Does not add the l c s cluster entry to the database.
	*
	* @param lcsClusterEntryId the primary key for the new l c s cluster entry
	* @return the new l c s cluster entry
	*/
	public LCSClusterEntry create(long lcsClusterEntryId);

	/**
	* Removes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry that was removed
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry remove(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryException;

	public LCSClusterEntry updateImpl(LCSClusterEntry lcsClusterEntry);

	/**
	* Returns the l c s cluster entry with the primary key or throws a {@link NoSuchLCSClusterEntryException} if it could not be found.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry findByPrimaryKey(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryException;

	/**
	* Returns the l c s cluster entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry, or <code>null</code> if a l c s cluster entry with the primary key could not be found
	*/
	public LCSClusterEntry fetchByPrimaryKey(long lcsClusterEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LCSClusterEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s cluster entries.
	*
	* @return the l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findAll();

	/**
	* Returns a range of all the l c s cluster entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s cluster entries
	*/
	public java.util.List<LCSClusterEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s cluster entries.
	*
	* @return the number of l c s cluster entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}