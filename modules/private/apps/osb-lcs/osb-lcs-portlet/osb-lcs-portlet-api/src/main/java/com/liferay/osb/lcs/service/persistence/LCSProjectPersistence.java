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

import com.liferay.osb.lcs.exception.NoSuchLCSProjectException;
import com.liferay.osb.lcs.model.LCSProject;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSProjectPersistenceImpl
 * @see LCSProjectUtil
 * @generated
 */
@ProviderType
public interface LCSProjectPersistence extends BasePersistence<LCSProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSProjectUtil} to access the l c s project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching l c s projects
	*/
	public java.util.List<LCSProject> findByName(java.lang.String name);

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
	public java.util.List<LCSProject> findByName(java.lang.String name,
		int start, int end);

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
	public java.util.List<LCSProject> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public java.util.List<LCSProject> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public LCSProject findByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the first l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

	/**
	* Returns the last l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public LCSProject findByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the last l c s project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

	/**
	* Returns the l c s projects before and after the current l c s project in the ordered set where name LIKE &#63;.
	*
	* @param lcsProjectId the primary key of the current l c s project
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public LCSProject[] findByName_PrevAndNext(long lcsProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Removes all the l c s projects where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public void removeByName(java.lang.String name);

	/**
	* Returns the number of l c s projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching l c s projects
	*/
	public int countByName(java.lang.String name);

	/**
	* Returns the l c s project where corpProjectId = &#63; or throws a {@link NoSuchLCSProjectException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @return the matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public LCSProject findByCorpProjectId(long corpProjectId)
		throws NoSuchLCSProjectException;

	/**
	* Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCorpProjectId(long corpProjectId);

	/**
	* Returns the l c s project where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache);

	/**
	* Removes the l c s project where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @return the l c s project that was removed
	*/
	public LCSProject removeByCorpProjectId(long corpProjectId)
		throws NoSuchLCSProjectException;

	/**
	* Returns the number of l c s projects where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching l c s projects
	*/
	public int countByCorpProjectId(long corpProjectId);

	/**
	* Returns all the l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @return the matching l c s projects
	*/
	public java.util.List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived);

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
	public java.util.List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end);

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
	public java.util.List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public java.util.List<LCSProject> findByCPI_A(long corpProjectId,
		boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public LCSProject findByCPI_A_First(long corpProjectId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCPI_A_First(long corpProjectId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project
	* @throws NoSuchLCSProjectException if a matching l c s project could not be found
	*/
	public LCSProject findByCPI_A_Last(long corpProjectId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCPI_A_Last(long corpProjectId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public LCSProject[] findByCPI_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Removes all the l c s projects where corpProjectId = &#63; and archived = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	*/
	public void removeByCPI_A(long corpProjectId, boolean archived);

	/**
	* Returns the number of l c s projects where corpProjectId = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param archived the archived
	* @return the number of matching l c s projects
	*/
	public int countByCPI_A(long corpProjectId, boolean archived);

	/**
	* Returns all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s projects
	*/
	public java.util.List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived);

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
	public java.util.List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end);

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
	public java.util.List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public java.util.List<LCSProject> findByCPI_N_A(long corpProjectId,
		java.lang.String name, boolean archived, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache);

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
	public LCSProject findByCPI_N_A_First(long corpProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the first l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCPI_N_A_First(long corpProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public LCSProject findByCPI_N_A_Last(long corpProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Returns the last l c s project in the ordered set where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s project, or <code>null</code> if a matching l c s project could not be found
	*/
	public LCSProject fetchByCPI_N_A_Last(long corpProjectId,
		java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public LCSProject[] findByCPI_N_A_PrevAndNext(long lcsProjectId,
		long corpProjectId, java.lang.String name, boolean archived,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator)
		throws NoSuchLCSProjectException;

	/**
	* Removes all the l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	*/
	public void removeByCPI_N_A(long corpProjectId, java.lang.String name,
		boolean archived);

	/**
	* Returns the number of l c s projects where corpProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s projects
	*/
	public int countByCPI_N_A(long corpProjectId, java.lang.String name,
		boolean archived);

	/**
	* Caches the l c s project in the entity cache if it is enabled.
	*
	* @param lcsProject the l c s project
	*/
	public void cacheResult(LCSProject lcsProject);

	/**
	* Caches the l c s projects in the entity cache if it is enabled.
	*
	* @param lcsProjects the l c s projects
	*/
	public void cacheResult(java.util.List<LCSProject> lcsProjects);

	/**
	* Creates a new l c s project with the primary key. Does not add the l c s project to the database.
	*
	* @param lcsProjectId the primary key for the new l c s project
	* @return the new l c s project
	*/
	public LCSProject create(long lcsProjectId);

	/**
	* Removes the l c s project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project that was removed
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public LCSProject remove(long lcsProjectId)
		throws NoSuchLCSProjectException;

	public LCSProject updateImpl(LCSProject lcsProject);

	/**
	* Returns the l c s project with the primary key or throws a {@link NoSuchLCSProjectException} if it could not be found.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project
	* @throws NoSuchLCSProjectException if a l c s project with the primary key could not be found
	*/
	public LCSProject findByPrimaryKey(long lcsProjectId)
		throws NoSuchLCSProjectException;

	/**
	* Returns the l c s project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project, or <code>null</code> if a l c s project with the primary key could not be found
	*/
	public LCSProject fetchByPrimaryKey(long lcsProjectId);

	@Override
	public java.util.Map<java.io.Serializable, LCSProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s projects.
	*
	* @return the l c s projects
	*/
	public java.util.List<LCSProject> findAll();

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
	public java.util.List<LCSProject> findAll(int start, int end);

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
	public java.util.List<LCSProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator);

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
	public java.util.List<LCSProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s projects.
	*
	* @return the number of l c s projects
	*/
	public int countAll();
}