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

import com.liferay.osb.lcs.model.LCSSubscriptionEntry;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s subscription entry service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSSubscriptionEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryUtil {
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
	public static void clearCache(LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().clearCache(lcsSubscriptionEntry);
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
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSSubscriptionEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getPersistence().update(lcsSubscriptionEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSSubscriptionEntry update(
		LCSSubscriptionEntry lcsSubscriptionEntry, ServiceContext serviceContext) {
		return getPersistence().update(lcsSubscriptionEntry, serviceContext);
	}

	/**
	* Returns all the l c s subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public static List<LCSSubscriptionEntry> findByActive(boolean active) {
		return getPersistence().findByActive(active);
	}

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
	public static List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end) {
		return getPersistence().findByActive(active, start, end);
	}

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
	public static List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator);
	}

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
	public static List<LCSSubscriptionEntry> findByActive(boolean active,
		int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActive(active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByActive_First(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().findByActive_First(active, orderByComparator);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByActive_First(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByActive_First(active, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByActive_Last(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().findByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByActive_Last(boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence().fetchByActive_Last(active, orderByComparator);
	}

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where active = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry[] findByActive_PrevAndNext(
		long lcsSubscriptionEntryId, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByActive_PrevAndNext(lcsSubscriptionEntryId, active,
			orderByComparator);
	}

	/**
	* Removes all the l c s subscription entries where active = &#63; from the database.
	*
	* @param active the active
	*/
	public static void removeByActive(boolean active) {
		getPersistence().removeByActive(active);
	}

	/**
	* Returns the number of l c s subscription entries where active = &#63;.
	*
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public static int countByActive(boolean active) {
		return getPersistence().countByActive(active);
	}

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s subscription entries
	*/
	public static List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId) {
		return getPersistence().findByLCSProjectId(lcsProjectId);
	}

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
	public static List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end) {
		return getPersistence().findByLCSProjectId(lcsProjectId, start, end);
	}

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
	public static List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator);
	}

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
	public static List<LCSSubscriptionEntry> findByLCSProjectId(
		long lcsProjectId, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByLCSProjectId_First(
		long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLCSProjectId_First(
		long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByLCSProjectId_Last(
		long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLCSProjectId_Last(
		long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the l c s subscription entries before and after the current l c s subscription entry in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsSubscriptionEntryId the primary key of the current l c s subscription entry
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry[] findByLCSProjectId_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLCSProjectId_PrevAndNext(lcsSubscriptionEntryId,
			lcsProjectId, orderByComparator);
	}

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public static void removeByLCSProjectId(long lcsProjectId) {
		getPersistence().removeByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s subscription entries
	*/
	public static int countByLCSProjectId(long lcsProjectId) {
		return getPersistence().countByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public static List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active) {
		return getPersistence().findByLPI_A(lcsProjectId, active);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end) {
		return getPersistence().findByLPI_A(lcsProjectId, active, start, end);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_A(lcsProjectId, active, start, end,
			orderByComparator);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_A(long lcsProjectId,
		boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_A(lcsProjectId, active, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByLPI_A_First(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_A_First(lcsProjectId, active, orderByComparator);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLPI_A_First(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_A_First(lcsProjectId, active, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry findByLPI_A_Last(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_A_Last(lcsProjectId, active, orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLPI_A_Last(long lcsProjectId,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_A_Last(lcsProjectId, active, orderByComparator);
	}

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
	public static LCSSubscriptionEntry[] findByLPI_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_A_PrevAndNext(lcsSubscriptionEntryId,
			lcsProjectId, active, orderByComparator);
	}

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; and active = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	*/
	public static void removeByLPI_A(long lcsProjectId, boolean active) {
		getPersistence().removeByLPI_A(lcsProjectId, active);
	}

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public static int countByLPI_A(long lcsProjectId, boolean active) {
		return getPersistence().countByLPI_A(lcsProjectId, active);
	}

	/**
	* Returns all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @return the matching l c s subscription entries
	*/
	public static List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		java.lang.String type, boolean active) {
		return getPersistence().findByLPI_T_A(lcsProjectId, type, active);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		java.lang.String type, boolean active, int start, int end) {
		return getPersistence()
				   .findByLPI_T_A(lcsProjectId, type, active, start, end);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		java.lang.String type, boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .findByLPI_T_A(lcsProjectId, type, active, start, end,
			orderByComparator);
	}

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
	public static List<LCSSubscriptionEntry> findByLPI_T_A(long lcsProjectId,
		java.lang.String type, boolean active, int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLPI_T_A(lcsProjectId, type, active, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static LCSSubscriptionEntry findByLPI_T_A_First(long lcsProjectId,
		java.lang.String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_T_A_First(lcsProjectId, type, active,
			orderByComparator);
	}

	/**
	* Returns the first l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLPI_T_A_First(long lcsProjectId,
		java.lang.String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_T_A_First(lcsProjectId, type, active,
			orderByComparator);
	}

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
	public static LCSSubscriptionEntry findByLPI_T_A_Last(long lcsProjectId,
		java.lang.String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_T_A_Last(lcsProjectId, type, active,
			orderByComparator);
	}

	/**
	* Returns the last l c s subscription entry in the ordered set where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s subscription entry, or <code>null</code> if a matching l c s subscription entry could not be found
	*/
	public static LCSSubscriptionEntry fetchByLPI_T_A_Last(long lcsProjectId,
		java.lang.String type, boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLPI_T_A_Last(lcsProjectId, type, active,
			orderByComparator);
	}

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
	public static LCSSubscriptionEntry[] findByLPI_T_A_PrevAndNext(
		long lcsSubscriptionEntryId, long lcsProjectId, java.lang.String type,
		boolean active,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence()
				   .findByLPI_T_A_PrevAndNext(lcsSubscriptionEntryId,
			lcsProjectId, type, active, orderByComparator);
	}

	/**
	* Removes all the l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	*/
	public static void removeByLPI_T_A(long lcsProjectId,
		java.lang.String type, boolean active) {
		getPersistence().removeByLPI_T_A(lcsProjectId, type, active);
	}

	/**
	* Returns the number of l c s subscription entries where lcsProjectId = &#63; and type = &#63; and active = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param type the type
	* @param active the active
	* @return the number of matching l c s subscription entries
	*/
	public static int countByLPI_T_A(long lcsProjectId, java.lang.String type,
		boolean active) {
		return getPersistence().countByLPI_T_A(lcsProjectId, type, active);
	}

	/**
	* Caches the l c s subscription entry in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	*/
	public static void cacheResult(LCSSubscriptionEntry lcsSubscriptionEntry) {
		getPersistence().cacheResult(lcsSubscriptionEntry);
	}

	/**
	* Caches the l c s subscription entries in the entity cache if it is enabled.
	*
	* @param lcsSubscriptionEntries the l c s subscription entries
	*/
	public static void cacheResult(
		List<LCSSubscriptionEntry> lcsSubscriptionEntries) {
		getPersistence().cacheResult(lcsSubscriptionEntries);
	}

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public static LCSSubscriptionEntry create(long lcsSubscriptionEntryId) {
		return getPersistence().create(lcsSubscriptionEntryId);
	}

	/**
	* Removes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry remove(long lcsSubscriptionEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().remove(lcsSubscriptionEntryId);
	}

	public static LCSSubscriptionEntry updateImpl(
		LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getPersistence().updateImpl(lcsSubscriptionEntry);
	}

	/**
	* Returns the l c s subscription entry with the primary key or throws a {@link NoSuchLCSSubscriptionEntryException} if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws NoSuchLCSSubscriptionEntryException if a l c s subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry findByPrimaryKey(
		long lcsSubscriptionEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException {
		return getPersistence().findByPrimaryKey(lcsSubscriptionEntryId);
	}

	/**
	* Returns the l c s subscription entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry, or <code>null</code> if a l c s subscription entry with the primary key could not be found
	*/
	public static LCSSubscriptionEntry fetchByPrimaryKey(
		long lcsSubscriptionEntryId) {
		return getPersistence().fetchByPrimaryKey(lcsSubscriptionEntryId);
	}

	public static java.util.Map<java.io.Serializable, LCSSubscriptionEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s subscription entries.
	*
	* @return the l c s subscription entries
	*/
	public static List<LCSSubscriptionEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LCSSubscriptionEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LCSSubscriptionEntry> findAll(int start, int end,
		OrderByComparator<LCSSubscriptionEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s subscription entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSSubscriptionEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSSubscriptionEntryPersistence, LCSSubscriptionEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSSubscriptionEntryPersistence.class);
}