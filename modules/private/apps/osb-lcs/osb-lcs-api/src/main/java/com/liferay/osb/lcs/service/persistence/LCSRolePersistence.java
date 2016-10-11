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

import com.liferay.osb.lcs.exception.NoSuchLCSRoleException;
import com.liferay.osb.lcs.model.LCSRole;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSRolePersistenceImpl
 * @see LCSRoleUtil
 * @generated
 */
@ProviderType
public interface LCSRolePersistence extends BasePersistence<LCSRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSRoleUtil} to access the l c s role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByUserId(long userId);

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
	public java.util.List<LCSRole> findByUserId(long userId, int start, int end);

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
	public java.util.List<LCSRole> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where userId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public LCSRole[] findByUserId_PrevAndNext(long lcsRoleId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of l c s roles where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching l c s roles
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the l c s roles where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByLCSProjectId(long lcsProjectId);

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
	public java.util.List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end);

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
	public java.util.List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public LCSRole[] findByLCSProjectId_PrevAndNext(long lcsRoleId,
		long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public void removeByLCSProjectId(long lcsProjectId);

	/**
	* Returns the number of l c s roles where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s roles
	*/
	public int countByLCSProjectId(long lcsProjectId);

	/**
	* Returns all the l c s roles where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId);

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
	public java.util.List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end);

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
	public java.util.List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByLCSClusterEntryId(
		long lcsClusterEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLCSClusterEntryId_First(long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLCSClusterEntryId_First(long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLCSClusterEntryId_Last(long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLCSClusterEntryId_Last(long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the l c s roles before and after the current l c s role in the ordered set where lcsClusterEntryId = &#63;.
	*
	* @param lcsRoleId the primary key of the current l c s role
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public LCSRole[] findByLCSClusterEntryId_PrevAndNext(long lcsRoleId,
		long lcsClusterEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where lcsClusterEntryId = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	*/
	public void removeByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Returns the number of l c s roles where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s roles
	*/
	public int countByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Returns all the l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByU_LPI(long userId, long lcsProjectId);

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
	public java.util.List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end);

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
	public java.util.List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByU_LPI(long userId, long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByU_LPI_First(long userId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_LPI_First(long userId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByU_LPI_Last(long userId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_LPI_Last(long userId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public LCSRole[] findByU_LPI_PrevAndNext(long lcsRoleId, long userId,
		long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where userId = &#63; and lcsProjectId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	*/
	public void removeByU_LPI(long userId, long lcsProjectId);

	/**
	* Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s roles
	*/
	public int countByU_LPI(long userId, long lcsProjectId);

	/**
	* Returns all the l c s roles where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByU_R(long userId, int role);

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
	public java.util.List<LCSRole> findByU_R(long userId, int role, int start,
		int end);

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
	public java.util.List<LCSRole> findByU_R(long userId, int role, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByU_R(long userId, int role, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_R_First(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_R_Last(long userId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public LCSRole[] findByU_R_PrevAndNext(long lcsRoleId, long userId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where userId = &#63; and role = &#63; from the database.
	*
	* @param userId the user ID
	* @param role the role
	*/
	public void removeByU_R(long userId, int role);

	/**
	* Returns the number of l c s roles where userId = &#63; and role = &#63;.
	*
	* @param userId the user ID
	* @param role the role
	* @return the number of matching l c s roles
	*/
	public int countByU_R(long userId, int role);

	/**
	* Returns all the l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @return the matching l c s roles
	*/
	public java.util.List<LCSRole> findByLPI_R(long lcsProjectId, int role);

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
	public java.util.List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end);

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
	public java.util.List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findByLPI_R(long lcsProjectId, int role,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLPI_R_First(long lcsProjectId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the first l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLPI_R_First(long lcsProjectId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByLPI_R_Last(long lcsProjectId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Returns the last l c s role in the ordered set where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByLPI_R_Last(long lcsProjectId, int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public LCSRole[] findByLPI_R_PrevAndNext(long lcsRoleId, long lcsProjectId,
		int role,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator)
		throws NoSuchLCSRoleException;

	/**
	* Removes all the l c s roles where lcsProjectId = &#63; and role = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	*/
	public void removeByLPI_R(long lcsProjectId, int role);

	/**
	* Returns the number of l c s roles where lcsProjectId = &#63; and role = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param role the role
	* @return the number of matching l c s roles
	*/
	public int countByLPI_R(long lcsProjectId, int role);

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or throws a {@link NoSuchLCSRoleException} if it could not be found.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s role
	* @throws NoSuchLCSRoleException if a matching l c s role could not be found
	*/
	public LCSRole findByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) throws NoSuchLCSRoleException;

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId);

	/**
	* Returns the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s role, or <code>null</code> if a matching l c s role could not be found
	*/
	public LCSRole fetchByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId, boolean retrieveFromCache);

	/**
	* Removes the l c s role where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the l c s role that was removed
	*/
	public LCSRole removeByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId) throws NoSuchLCSRoleException;

	/**
	* Returns the number of l c s roles where userId = &#63; and lcsProjectId = &#63; and lcsClusterEntryId = &#63;.
	*
	* @param userId the user ID
	* @param lcsProjectId the lcs project ID
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s roles
	*/
	public int countByU_LPI_LCEI(long userId, long lcsProjectId,
		long lcsClusterEntryId);

	/**
	* Caches the l c s role in the entity cache if it is enabled.
	*
	* @param lcsRole the l c s role
	*/
	public void cacheResult(LCSRole lcsRole);

	/**
	* Caches the l c s roles in the entity cache if it is enabled.
	*
	* @param lcsRoles the l c s roles
	*/
	public void cacheResult(java.util.List<LCSRole> lcsRoles);

	/**
	* Creates a new l c s role with the primary key. Does not add the l c s role to the database.
	*
	* @param lcsRoleId the primary key for the new l c s role
	* @return the new l c s role
	*/
	public LCSRole create(long lcsRoleId);

	/**
	* Removes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role that was removed
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public LCSRole remove(long lcsRoleId) throws NoSuchLCSRoleException;

	public LCSRole updateImpl(LCSRole lcsRole);

	/**
	* Returns the l c s role with the primary key or throws a {@link NoSuchLCSRoleException} if it could not be found.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role
	* @throws NoSuchLCSRoleException if a l c s role with the primary key could not be found
	*/
	public LCSRole findByPrimaryKey(long lcsRoleId)
		throws NoSuchLCSRoleException;

	/**
	* Returns the l c s role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role, or <code>null</code> if a l c s role with the primary key could not be found
	*/
	public LCSRole fetchByPrimaryKey(long lcsRoleId);

	@Override
	public java.util.Map<java.io.Serializable, LCSRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s roles.
	*
	* @return the l c s roles
	*/
	public java.util.List<LCSRole> findAll();

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
	public java.util.List<LCSRole> findAll(int start, int end);

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
	public java.util.List<LCSRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator);

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
	public java.util.List<LCSRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s roles.
	*
	* @return the number of l c s roles
	*/
	public int countAll();
}