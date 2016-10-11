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

import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s notification audit entry service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSNotificationAuditEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntryPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSNotificationAuditEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryUtil {
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
	public static void clearCache(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		getPersistence().clearCache(lcsNotificationAuditEntry);
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
	public static List<LCSNotificationAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSNotificationAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSNotificationAuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSNotificationAuditEntry update(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return getPersistence().update(lcsNotificationAuditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSNotificationAuditEntry update(
		LCSNotificationAuditEntry lcsNotificationAuditEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsNotificationAuditEntry, serviceContext);
	}

	/**
	* Returns all the l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the matching l c s notification audit entries
	*/
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId) {
		return getPersistence().findByLCSClusterNodeId(lcsClusterNodeId);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long lcsClusterNodeId, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry findByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByLCSClusterNodeId_First(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the first l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry fetchByLCSClusterNodeId_First(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterNodeId_First(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry findByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByLCSClusterNodeId_Last(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the last l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry fetchByLCSClusterNodeId_Last(
		long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByLCSClusterNodeId_Last(lcsClusterNodeId,
			orderByComparator);
	}

	/**
	* Returns the l c s notification audit entries before and after the current l c s notification audit entry in the ordered set where lcsClusterNodeId = &#63;.
	*
	* @param lcsNotificationAuditEntryId the primary key of the current l c s notification audit entry
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public static LCSNotificationAuditEntry[] findByLCSClusterNodeId_PrevAndNext(
		long lcsNotificationAuditEntryId, long lcsClusterNodeId,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByLCSClusterNodeId_PrevAndNext(lcsNotificationAuditEntryId,
			lcsClusterNodeId, orderByComparator);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds) {
		return getPersistence().findByLCSClusterNodeId(lcsClusterNodeIds);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator);
	}

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
	public static List<LCSNotificationAuditEntry> findByLCSClusterNodeId(
		long[] lcsClusterNodeIds, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSClusterNodeId(lcsClusterNodeIds, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s notification audit entries where lcsClusterNodeId = &#63; from the database.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	*/
	public static void removeByLCSClusterNodeId(long lcsClusterNodeId) {
		getPersistence().removeByLCSClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Returns the number of l c s notification audit entries where lcsClusterNodeId = &#63;.
	*
	* @param lcsClusterNodeId the lcs cluster node ID
	* @return the number of matching l c s notification audit entries
	*/
	public static int countByLCSClusterNodeId(long lcsClusterNodeId) {
		return getPersistence().countByLCSClusterNodeId(lcsClusterNodeId);
	}

	/**
	* Returns the number of l c s notification audit entries where lcsClusterNodeId = any &#63;.
	*
	* @param lcsClusterNodeIds the lcs cluster node IDs
	* @return the number of matching l c s notification audit entries
	*/
	public static int countByLCSClusterNodeId(long[] lcsClusterNodeIds) {
		return getPersistence().countByLCSClusterNodeId(lcsClusterNodeIds);
	}

	/**
	* Returns all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @return the matching l c s notification audit entries
	*/
	public static List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type) {
		return getPersistence().findByU_LCNI_T(userId, lcsClusterNodeId, type);
	}

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
	public static List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end) {
		return getPersistence()
				   .findByU_LCNI_T(userId, lcsClusterNodeId, type, start, end);
	}

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
	public static List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .findByU_LCNI_T(userId, lcsClusterNodeId, type, start, end,
			orderByComparator);
	}

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
	public static List<LCSNotificationAuditEntry> findByU_LCNI_T(long userId,
		long lcsClusterNodeId, int type, int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_LCNI_T(userId, lcsClusterNodeId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static LCSNotificationAuditEntry findByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByU_LCNI_T_First(userId, lcsClusterNodeId, type,
			orderByComparator);
	}

	/**
	* Returns the first l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry fetchByU_LCNI_T_First(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_LCNI_T_First(userId, lcsClusterNodeId, type,
			orderByComparator);
	}

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
	public static LCSNotificationAuditEntry findByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByU_LCNI_T_Last(userId, lcsClusterNodeId, type,
			orderByComparator);
	}

	/**
	* Returns the last l c s notification audit entry in the ordered set where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification audit entry, or <code>null</code> if a matching l c s notification audit entry could not be found
	*/
	public static LCSNotificationAuditEntry fetchByU_LCNI_T_Last(long userId,
		long lcsClusterNodeId, int type,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_LCNI_T_Last(userId, lcsClusterNodeId, type,
			orderByComparator);
	}

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
	public static LCSNotificationAuditEntry[] findByU_LCNI_T_PrevAndNext(
		long lcsNotificationAuditEntryId, long userId, long lcsClusterNodeId,
		int type, OrderByComparator<LCSNotificationAuditEntry> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence()
				   .findByU_LCNI_T_PrevAndNext(lcsNotificationAuditEntryId,
			userId, lcsClusterNodeId, type, orderByComparator);
	}

	/**
	* Removes all the l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	*/
	public static void removeByU_LCNI_T(long userId, long lcsClusterNodeId,
		int type) {
		getPersistence().removeByU_LCNI_T(userId, lcsClusterNodeId, type);
	}

	/**
	* Returns the number of l c s notification audit entries where userId = &#63; and lcsClusterNodeId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param lcsClusterNodeId the lcs cluster node ID
	* @param type the type
	* @return the number of matching l c s notification audit entries
	*/
	public static int countByU_LCNI_T(long userId, long lcsClusterNodeId,
		int type) {
		return getPersistence().countByU_LCNI_T(userId, lcsClusterNodeId, type);
	}

	/**
	* Caches the l c s notification audit entry in the entity cache if it is enabled.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	*/
	public static void cacheResult(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		getPersistence().cacheResult(lcsNotificationAuditEntry);
	}

	/**
	* Caches the l c s notification audit entries in the entity cache if it is enabled.
	*
	* @param lcsNotificationAuditEntries the l c s notification audit entries
	*/
	public static void cacheResult(
		List<LCSNotificationAuditEntry> lcsNotificationAuditEntries) {
		getPersistence().cacheResult(lcsNotificationAuditEntries);
	}

	/**
	* Creates a new l c s notification audit entry with the primary key. Does not add the l c s notification audit entry to the database.
	*
	* @param lcsNotificationAuditEntryId the primary key for the new l c s notification audit entry
	* @return the new l c s notification audit entry
	*/
	public static LCSNotificationAuditEntry create(
		long lcsNotificationAuditEntryId) {
		return getPersistence().create(lcsNotificationAuditEntryId);
	}

	/**
	* Removes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public static LCSNotificationAuditEntry remove(
		long lcsNotificationAuditEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence().remove(lcsNotificationAuditEntryId);
	}

	public static LCSNotificationAuditEntry updateImpl(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return getPersistence().updateImpl(lcsNotificationAuditEntry);
	}

	/**
	* Returns the l c s notification audit entry with the primary key or throws a {@link NoSuchLCSNotificationAuditEntryException} if it could not be found.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry
	* @throws NoSuchLCSNotificationAuditEntryException if a l c s notification audit entry with the primary key could not be found
	*/
	public static LCSNotificationAuditEntry findByPrimaryKey(
		long lcsNotificationAuditEntryId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException {
		return getPersistence().findByPrimaryKey(lcsNotificationAuditEntryId);
	}

	/**
	* Returns the l c s notification audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry, or <code>null</code> if a l c s notification audit entry with the primary key could not be found
	*/
	public static LCSNotificationAuditEntry fetchByPrimaryKey(
		long lcsNotificationAuditEntryId) {
		return getPersistence().fetchByPrimaryKey(lcsNotificationAuditEntryId);
	}

	public static java.util.Map<java.io.Serializable, LCSNotificationAuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s notification audit entries.
	*
	* @return the l c s notification audit entries
	*/
	public static List<LCSNotificationAuditEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LCSNotificationAuditEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LCSNotificationAuditEntry> findAll(int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LCSNotificationAuditEntry> findAll(int start, int end,
		OrderByComparator<LCSNotificationAuditEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s notification audit entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s notification audit entries.
	*
	* @return the number of l c s notification audit entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSNotificationAuditEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSNotificationAuditEntryPersistence, LCSNotificationAuditEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSNotificationAuditEntryPersistence.class);
}