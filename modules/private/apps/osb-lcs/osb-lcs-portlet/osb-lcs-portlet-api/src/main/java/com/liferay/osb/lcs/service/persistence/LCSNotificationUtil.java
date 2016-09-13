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

import com.liferay.osb.lcs.model.LCSNotification;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s notification service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSNotificationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSNotificationPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSNotificationUtil {
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
	public static void clearCache(LCSNotification lcsNotification) {
		getPersistence().clearCache(lcsNotification);
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
	public static List<LCSNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSNotification update(LCSNotification lcsNotification) {
		return getPersistence().update(lcsNotification);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSNotification update(LCSNotification lcsNotification,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsNotification, serviceContext);
	}

	/**
	* Returns all the l c s notifications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching l c s notifications
	*/
	public static List<LCSNotification> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the l c s notifications where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of matching l c s notifications
	*/
	public static List<LCSNotification> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s notifications where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByUserId(long userId, int start,
		int end, OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s notifications where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByUserId(long userId, int start,
		int end, OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByUserId_First(long userId,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByUserId_First(long userId,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByUserId_Last(long userId,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByUserId_Last(long userId,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the l c s notifications before and after the current l c s notification in the ordered set where userId = &#63;.
	*
	* @param lcsNotificationId the primary key of the current l c s notification
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification[] findByUserId_PrevAndNext(
		long lcsNotificationId, long userId,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByUserId_PrevAndNext(lcsNotificationId, userId,
			orderByComparator);
	}

	/**
	* Removes all the l c s notifications where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of l c s notifications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching l c s notifications
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s notifications
	*/
	public static List<LCSNotification> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of matching l c s notifications
	*/
	public static List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end) {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByC_C_First(long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByC_C_First(long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByC_C_Last(long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByC_C_Last(long classNameId,
		long classPK, OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the l c s notifications before and after the current l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param lcsNotificationId the primary key of the current l c s notification
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification[] findByC_C_PrevAndNext(
		long lcsNotificationId, long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByC_C_PrevAndNext(lcsNotificationId, classNameId,
			classPK, orderByComparator);
	}

	/**
	* Removes all the l c s notifications where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s notifications
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s notifications
	*/
	public static List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK) {
		return getPersistence().findByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns a range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of matching l c s notifications
	*/
	public static List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end) {
		return getPersistence()
				   .findByU_C_C(userId, classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .findByU_C_C(userId, classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s notifications
	*/
	public static List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_C_C(userId, classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByU_C_C_First(long userId,
		long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByU_C_C_First(userId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByU_C_C_First(long userId,
		long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_C_First(userId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByU_C_C_Last(long userId,
		long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByU_C_C_Last(userId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByU_C_C_Last(long userId,
		long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_C_Last(userId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the l c s notifications before and after the current l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param lcsNotificationId the primary key of the current l c s notification
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification[] findByU_C_C_PrevAndNext(
		long lcsNotificationId, long userId, long classNameId, long classPK,
		OrderByComparator<LCSNotification> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .findByU_C_C_PrevAndNext(lcsNotificationId, userId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByU_C_C(long userId, long classNameId, long classPK) {
		getPersistence().removeByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s notifications
	*/
	public static int countByU_C_C(long userId, long classNameId, long classPK) {
		return getPersistence().countByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or throws a {@link NoSuchLCSNotificationException} if it could not be found.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public static LCSNotification findByU_C_C_T(long userId, long classNameId,
		long classPK, int type)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence().findByU_C_C_T(userId, classNameId, classPK, type);
	}

	/**
	* Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type) {
		return getPersistence()
				   .fetchByU_C_C_T(userId, classNameId, classPK, type);
	}

	/**
	* Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public static LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_C_C_T(userId, classNameId, classPK, type,
			retrieveFromCache);
	}

	/**
	* Removes the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the l c s notification that was removed
	*/
	public static LCSNotification removeByU_C_C_T(long userId,
		long classNameId, long classPK, int type)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence()
				   .removeByU_C_C_T(userId, classNameId, classPK, type);
	}

	/**
	* Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching l c s notifications
	*/
	public static int countByU_C_C_T(long userId, long classNameId,
		long classPK, int type) {
		return getPersistence()
				   .countByU_C_C_T(userId, classNameId, classPK, type);
	}

	/**
	* Caches the l c s notification in the entity cache if it is enabled.
	*
	* @param lcsNotification the l c s notification
	*/
	public static void cacheResult(LCSNotification lcsNotification) {
		getPersistence().cacheResult(lcsNotification);
	}

	/**
	* Caches the l c s notifications in the entity cache if it is enabled.
	*
	* @param lcsNotifications the l c s notifications
	*/
	public static void cacheResult(List<LCSNotification> lcsNotifications) {
		getPersistence().cacheResult(lcsNotifications);
	}

	/**
	* Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	*
	* @param lcsNotificationId the primary key for the new l c s notification
	* @return the new l c s notification
	*/
	public static LCSNotification create(long lcsNotificationId) {
		return getPersistence().create(lcsNotificationId);
	}

	/**
	* Removes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification that was removed
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification remove(long lcsNotificationId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence().remove(lcsNotificationId);
	}

	public static LCSNotification updateImpl(LCSNotification lcsNotification) {
		return getPersistence().updateImpl(lcsNotification);
	}

	/**
	* Returns the l c s notification with the primary key or throws a {@link NoSuchLCSNotificationException} if it could not be found.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification findByPrimaryKey(long lcsNotificationId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSNotificationException {
		return getPersistence().findByPrimaryKey(lcsNotificationId);
	}

	/**
	* Returns the l c s notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification, or <code>null</code> if a l c s notification with the primary key could not be found
	*/
	public static LCSNotification fetchByPrimaryKey(long lcsNotificationId) {
		return getPersistence().fetchByPrimaryKey(lcsNotificationId);
	}

	public static java.util.Map<java.io.Serializable, LCSNotification> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s notifications.
	*
	* @return the l c s notifications
	*/
	public static List<LCSNotification> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @return the range of l c s notifications
	*/
	public static List<LCSNotification> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s notifications
	*/
	public static List<LCSNotification> findAll(int start, int end,
		OrderByComparator<LCSNotification> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notifications
	* @param end the upper bound of the range of l c s notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s notifications
	*/
	public static List<LCSNotification> findAll(int start, int end,
		OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s notifications from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s notifications.
	*
	* @return the number of l c s notifications
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSNotificationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSNotificationPersistence, LCSNotificationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSNotificationPersistence.class);
}