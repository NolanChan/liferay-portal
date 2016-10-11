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

import com.liferay.osb.lcs.model.LCSClusterEntry;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s cluster entry service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSClusterEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSClusterEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSClusterEntryUtil {
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
	public static void clearCache(LCSClusterEntry lcsClusterEntry) {
		getPersistence().clearCache(lcsClusterEntry);
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
	public static List<LCSClusterEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSClusterEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSClusterEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSClusterEntry update(LCSClusterEntry lcsClusterEntry) {
		return getPersistence().update(lcsClusterEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSClusterEntry update(LCSClusterEntry lcsClusterEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsClusterEntry, serviceContext);
	}

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s cluster entries
	*/
	public static List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId) {
		return getPersistence().findByLCSProjectId(lcsProjectId);
	}

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
	public static List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end) {
		return getPersistence().findByLCSProjectId(lcsProjectId, start, end);
	}

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
	public static List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator);
	}

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
	public static List<LCSClusterEntry> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the l c s cluster entries before and after the current l c s cluster entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsClusterEntryId the primary key of the current l c s cluster entry
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public static LCSClusterEntry[] findByLCSProjectId_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLCSProjectId_PrevAndNext(lcsClusterEntryId,
			lcsProjectId, orderByComparator);
	}

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public static void removeByLCSProjectId(long lcsProjectId) {
		getPersistence().removeByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s cluster entries
	*/
	public static int countByLCSProjectId(long lcsProjectId) {
		return getPersistence().countByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @return the matching l c s cluster entries
	*/
	public static List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name) {
		return getPersistence().findByLPI_N(lcsProjectId, name);
	}

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
	public static List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end) {
		return getPersistence().findByLPI_N(lcsProjectId, name, start, end);
	}

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
	public static List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_N(lcsProjectId, name, start, end,
			orderByComparator);
	}

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
	public static List<LCSClusterEntry> findByLPI_N(long lcsProjectId,
		java.lang.String name, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_N(lcsProjectId, name, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLPI_N_First(long lcsProjectId,
		java.lang.String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_First(lcsProjectId, name, orderByComparator);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_N_First(long lcsProjectId,
		java.lang.String name,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_N_First(lcsProjectId, name, orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLPI_N_Last(long lcsProjectId,
		java.lang.String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_Last(lcsProjectId, name, orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_N_Last(long lcsProjectId,
		java.lang.String name,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_N_Last(lcsProjectId, name, orderByComparator);
	}

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
	public static LCSClusterEntry[] findByLPI_N_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId, java.lang.String name,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_PrevAndNext(lcsClusterEntryId, lcsProjectId,
			name, orderByComparator);
	}

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	*/
	public static void removeByLPI_N(long lcsProjectId, java.lang.String name) {
		getPersistence().removeByLPI_N(lcsProjectId, name);
	}

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @return the number of matching l c s cluster entries
	*/
	public static int countByLPI_N(long lcsProjectId, java.lang.String name) {
		return getPersistence().countByLPI_N(lcsProjectId, name);
	}

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @return the matching l c s cluster entries
	*/
	public static List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType) {
		return getPersistence().findByLPI_ST(lcsProjectId, subscriptionType);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end) {
		return getPersistence()
				   .findByLPI_ST(lcsProjectId, subscriptionType, start, end);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_ST(lcsProjectId, subscriptionType, start, end,
			orderByComparator);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_ST(lcsProjectId, subscriptionType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLPI_ST_First(long lcsProjectId,
		java.lang.String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_First(lcsProjectId, subscriptionType,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_ST_First(long lcsProjectId,
		java.lang.String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_ST_First(lcsProjectId, subscriptionType,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry findByLPI_ST_Last(long lcsProjectId,
		java.lang.String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_Last(lcsProjectId, subscriptionType,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_ST_Last(long lcsProjectId,
		java.lang.String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_ST_Last(lcsProjectId, subscriptionType,
			orderByComparator);
	}

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
	public static LCSClusterEntry[] findByLPI_ST_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		java.lang.String subscriptionType,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_PrevAndNext(lcsClusterEntryId, lcsProjectId,
			subscriptionType, orderByComparator);
	}

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	*/
	public static void removeByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType) {
		getPersistence().removeByLPI_ST(lcsProjectId, subscriptionType);
	}

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @return the number of matching l c s cluster entries
	*/
	public static int countByLPI_ST(long lcsProjectId,
		java.lang.String subscriptionType) {
		return getPersistence().countByLPI_ST(lcsProjectId, subscriptionType);
	}

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @return the matching l c s cluster entries
	*/
	public static List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived) {
		return getPersistence().findByLPI_N_A(lcsProjectId, name, archived);
	}

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
	public static List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end) {
		return getPersistence()
				   .findByLPI_N_A(lcsProjectId, name, archived, start, end);
	}

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
	public static List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_N_A(lcsProjectId, name, archived, start, end,
			orderByComparator);
	}

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
	public static List<LCSClusterEntry> findByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived, int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_N_A(lcsProjectId, name, archived, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static LCSClusterEntry findByLPI_N_A_First(long lcsProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_A_First(lcsProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_N_A_First(long lcsProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_N_A_First(lcsProjectId, name, archived,
			orderByComparator);
	}

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
	public static LCSClusterEntry findByLPI_N_A_Last(long lcsProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_A_Last(lcsProjectId, name, archived,
			orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_N_A_Last(long lcsProjectId,
		java.lang.String name, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_N_A_Last(lcsProjectId, name, archived,
			orderByComparator);
	}

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
	public static LCSClusterEntry[] findByLPI_N_A_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId, java.lang.String name,
		boolean archived, OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_N_A_PrevAndNext(lcsClusterEntryId, lcsProjectId,
			name, archived, orderByComparator);
	}

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	*/
	public static void removeByLPI_N_A(long lcsProjectId,
		java.lang.String name, boolean archived) {
		getPersistence().removeByLPI_N_A(lcsProjectId, name, archived);
	}

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and name = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param name the name
	* @param archived the archived
	* @return the number of matching l c s cluster entries
	*/
	public static int countByLPI_N_A(long lcsProjectId, java.lang.String name,
		boolean archived) {
		return getPersistence().countByLPI_N_A(lcsProjectId, name, archived);
	}

	/**
	* Returns all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @return the matching l c s cluster entries
	*/
	public static List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived) {
		return getPersistence()
				   .findByLPI_ST_A(lcsProjectId, subscriptionType, archived);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start, int end) {
		return getPersistence()
				   .findByLPI_ST_A(lcsProjectId, subscriptionType, archived,
			start, end);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start,
		int end, OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_ST_A(lcsProjectId, subscriptionType, archived,
			start, end, orderByComparator);
	}

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
	public static List<LCSClusterEntry> findByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived, int start,
		int end, OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_ST_A(lcsProjectId, subscriptionType, archived,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static LCSClusterEntry findByLPI_ST_A_First(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_A_First(lcsProjectId, subscriptionType,
			archived, orderByComparator);
	}

	/**
	* Returns the first l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_ST_A_First(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_ST_A_First(lcsProjectId, subscriptionType,
			archived, orderByComparator);
	}

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
	public static LCSClusterEntry findByLPI_ST_A_Last(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_A_Last(lcsProjectId, subscriptionType,
			archived, orderByComparator);
	}

	/**
	* Returns the last l c s cluster entry in the ordered set where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s cluster entry, or <code>null</code> if a matching l c s cluster entry could not be found
	*/
	public static LCSClusterEntry fetchByLPI_ST_A_Last(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_ST_A_Last(lcsProjectId, subscriptionType,
			archived, orderByComparator);
	}

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
	public static LCSClusterEntry[] findByLPI_ST_A_PrevAndNext(
		long lcsClusterEntryId, long lcsProjectId,
		java.lang.String subscriptionType, boolean archived,
		OrderByComparator<LCSClusterEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence()
				   .findByLPI_ST_A_PrevAndNext(lcsClusterEntryId, lcsProjectId,
			subscriptionType, archived, orderByComparator);
	}

	/**
	* Removes all the l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	*/
	public static void removeByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived) {
		getPersistence()
			.removeByLPI_ST_A(lcsProjectId, subscriptionType, archived);
	}

	/**
	* Returns the number of l c s cluster entries where lcsProjectId = &#63; and subscriptionType = &#63; and archived = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param subscriptionType the subscription type
	* @param archived the archived
	* @return the number of matching l c s cluster entries
	*/
	public static int countByLPI_ST_A(long lcsProjectId,
		java.lang.String subscriptionType, boolean archived) {
		return getPersistence()
				   .countByLPI_ST_A(lcsProjectId, subscriptionType, archived);
	}

	/**
	* Caches the l c s cluster entry in the entity cache if it is enabled.
	*
	* @param lcsClusterEntry the l c s cluster entry
	*/
	public static void cacheResult(LCSClusterEntry lcsClusterEntry) {
		getPersistence().cacheResult(lcsClusterEntry);
	}

	/**
	* Caches the l c s cluster entries in the entity cache if it is enabled.
	*
	* @param lcsClusterEntries the l c s cluster entries
	*/
	public static void cacheResult(List<LCSClusterEntry> lcsClusterEntries) {
		getPersistence().cacheResult(lcsClusterEntries);
	}

	/**
	* Creates a new l c s cluster entry with the primary key. Does not add the l c s cluster entry to the database.
	*
	* @param lcsClusterEntryId the primary key for the new l c s cluster entry
	* @return the new l c s cluster entry
	*/
	public static LCSClusterEntry create(long lcsClusterEntryId) {
		return getPersistence().create(lcsClusterEntryId);
	}

	/**
	* Removes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry that was removed
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public static LCSClusterEntry remove(long lcsClusterEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence().remove(lcsClusterEntryId);
	}

	public static LCSClusterEntry updateImpl(LCSClusterEntry lcsClusterEntry) {
		return getPersistence().updateImpl(lcsClusterEntry);
	}

	/**
	* Returns the l c s cluster entry with the primary key or throws a {@link NoSuchLCSClusterEntryException} if it could not be found.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry
	* @throws NoSuchLCSClusterEntryException if a l c s cluster entry with the primary key could not be found
	*/
	public static LCSClusterEntry findByPrimaryKey(long lcsClusterEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException {
		return getPersistence().findByPrimaryKey(lcsClusterEntryId);
	}

	/**
	* Returns the l c s cluster entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry, or <code>null</code> if a l c s cluster entry with the primary key could not be found
	*/
	public static LCSClusterEntry fetchByPrimaryKey(long lcsClusterEntryId) {
		return getPersistence().fetchByPrimaryKey(lcsClusterEntryId);
	}

	public static java.util.Map<java.io.Serializable, LCSClusterEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s cluster entries.
	*
	* @return the l c s cluster entries
	*/
	public static List<LCSClusterEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LCSClusterEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LCSClusterEntry> findAll(int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LCSClusterEntry> findAll(int start, int end,
		OrderByComparator<LCSClusterEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s cluster entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s cluster entries.
	*
	* @return the number of l c s cluster entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSClusterEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterEntryPersistence, LCSClusterEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterEntryPersistence.class);
}