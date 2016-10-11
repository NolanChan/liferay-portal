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

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationException;
import com.liferay.osb.lcs.model.LCSNotification;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSNotificationPersistenceImpl
 * @see LCSNotificationUtil
 * @generated
 */
@ProviderType
public interface LCSNotificationPersistence extends BasePersistence<LCSNotification> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSNotificationUtil} to access the l c s notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s notifications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching l c s notifications
	*/
	public java.util.List<LCSNotification> findByUserId(long userId);

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
	public java.util.List<LCSNotification> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<LCSNotification> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public java.util.List<LCSNotification> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public LCSNotification findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public LCSNotification findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

	/**
	* Returns the l c s notifications before and after the current l c s notification in the ordered set where userId = &#63;.
	*
	* @param lcsNotificationId the primary key of the current l c s notification
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public LCSNotification[] findByUserId_PrevAndNext(long lcsNotificationId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Removes all the l c s notifications where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of l c s notifications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching l c s notifications
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s notifications
	*/
	public java.util.List<LCSNotification> findByC_C(long classNameId,
		long classPK);

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
	public java.util.List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end);

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
	public java.util.List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public java.util.List<LCSNotification> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public LCSNotification findByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the first l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

	/**
	* Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification
	* @throws NoSuchLCSNotificationException if a matching l c s notification could not be found
	*/
	public LCSNotification findByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the last l c s notification in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public LCSNotification[] findByC_C_PrevAndNext(long lcsNotificationId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Removes all the l c s notifications where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of l c s notifications where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s notifications
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s notifications
	*/
	public java.util.List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK);

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
	public java.util.List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public java.util.List<LCSNotification> findByU_C_C(long userId,
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache);

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
	public LCSNotification findByU_C_C_First(long userId, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the first l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByU_C_C_First(long userId, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public LCSNotification findByU_C_C_Last(long userId, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the last l c s notification in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByU_C_C_Last(long userId, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public LCSNotification[] findByU_C_C_PrevAndNext(long lcsNotificationId,
		long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator)
		throws NoSuchLCSNotificationException;

	/**
	* Removes all the l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByU_C_C(long userId, long classNameId, long classPK);

	/**
	* Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s notifications
	*/
	public int countByU_C_C(long userId, long classNameId, long classPK);

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
	public LCSNotification findByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws NoSuchLCSNotificationException;

	/**
	* Returns the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching l c s notification, or <code>null</code> if a matching l c s notification could not be found
	*/
	public LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type);

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
	public LCSNotification fetchByU_C_C_T(long userId, long classNameId,
		long classPK, int type, boolean retrieveFromCache);

	/**
	* Removes the l c s notification where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the l c s notification that was removed
	*/
	public LCSNotification removeByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws NoSuchLCSNotificationException;

	/**
	* Returns the number of l c s notifications where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching l c s notifications
	*/
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type);

	/**
	* Caches the l c s notification in the entity cache if it is enabled.
	*
	* @param lcsNotification the l c s notification
	*/
	public void cacheResult(LCSNotification lcsNotification);

	/**
	* Caches the l c s notifications in the entity cache if it is enabled.
	*
	* @param lcsNotifications the l c s notifications
	*/
	public void cacheResult(java.util.List<LCSNotification> lcsNotifications);

	/**
	* Creates a new l c s notification with the primary key. Does not add the l c s notification to the database.
	*
	* @param lcsNotificationId the primary key for the new l c s notification
	* @return the new l c s notification
	*/
	public LCSNotification create(long lcsNotificationId);

	/**
	* Removes the l c s notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification that was removed
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public LCSNotification remove(long lcsNotificationId)
		throws NoSuchLCSNotificationException;

	public LCSNotification updateImpl(LCSNotification lcsNotification);

	/**
	* Returns the l c s notification with the primary key or throws a {@link NoSuchLCSNotificationException} if it could not be found.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification
	* @throws NoSuchLCSNotificationException if a l c s notification with the primary key could not be found
	*/
	public LCSNotification findByPrimaryKey(long lcsNotificationId)
		throws NoSuchLCSNotificationException;

	/**
	* Returns the l c s notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsNotificationId the primary key of the l c s notification
	* @return the l c s notification, or <code>null</code> if a l c s notification with the primary key could not be found
	*/
	public LCSNotification fetchByPrimaryKey(long lcsNotificationId);

	@Override
	public java.util.Map<java.io.Serializable, LCSNotification> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s notifications.
	*
	* @return the l c s notifications
	*/
	public java.util.List<LCSNotification> findAll();

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
	public java.util.List<LCSNotification> findAll(int start, int end);

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
	public java.util.List<LCSNotification> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator);

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
	public java.util.List<LCSNotification> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSNotification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s notifications from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s notifications.
	*
	* @return the number of l c s notifications
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}