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

import com.liferay.osb.lcs.model.UserLCSMessage;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user l c s message service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.UserLCSMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see UserLCSMessagePersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.UserLCSMessagePersistenceImpl
 * @generated
 */
@ProviderType
public class UserLCSMessageUtil {
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
	public static void clearCache(UserLCSMessage userLCSMessage) {
		getPersistence().clearCache(userLCSMessage);
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
	public static List<UserLCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserLCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserLCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserLCSMessage update(UserLCSMessage userLCSMessage) {
		return getPersistence().update(userLCSMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserLCSMessage update(UserLCSMessage userLCSMessage,
		ServiceContext serviceContext) {
		return getPersistence().update(userLCSMessage, serviceContext);
	}

	/**
	* Returns all the user l c s messages where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user l c s messages
	*/
	public static List<UserLCSMessage> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the user l c s messages where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByUserId(long userId, int start,
		int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByUserId(long userId, int start,
		int end, OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByUserId_First(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByUserId_First(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByUserId_Last(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByUserId_Last(long userId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage[] findByUserId_PrevAndNext(
		long userLcsMessageId, long userId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByUserId_PrevAndNext(userLcsMessageId, userId,
			orderByComparator);
	}

	/**
	* Removes all the user l c s messages where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of user l c s messages where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user l c s messages
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the user l c s messages where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s messages
	*/
	public static List<UserLCSMessage> findByLCSMessageId(long lcsMessageId) {
		return getPersistence().findByLCSMessageId(lcsMessageId);
	}

	/**
	* Returns a range of all the user l c s messages where lcsMessageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsMessageId the lcs message ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end) {
		return getPersistence().findByLCSMessageId(lcsMessageId, start, end);
	}

	/**
	* Returns an ordered range of all the user l c s messages where lcsMessageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsMessageId the lcs message ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .findByLCSMessageId(lcsMessageId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user l c s messages where lcsMessageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsMessageId the lcs message ID
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByLCSMessageId(long lcsMessageId,
		int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSMessageId(lcsMessageId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByLCSMessageId_First(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByLCSMessageId_First(lcsMessageId, orderByComparator);
	}

	/**
	* Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByLCSMessageId_First(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByLCSMessageId_First(lcsMessageId, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByLCSMessageId_Last(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByLCSMessageId_Last(lcsMessageId, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByLCSMessageId_Last(long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByLCSMessageId_Last(lcsMessageId, orderByComparator);
	}

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage[] findByLCSMessageId_PrevAndNext(
		long userLcsMessageId, long lcsMessageId,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByLCSMessageId_PrevAndNext(userLcsMessageId,
			lcsMessageId, orderByComparator);
	}

	/**
	* Removes all the user l c s messages where lcsMessageId = &#63; from the database.
	*
	* @param lcsMessageId the lcs message ID
	*/
	public static void removeByLCSMessageId(long lcsMessageId) {
		getPersistence().removeByLCSMessageId(lcsMessageId);
	}

	/**
	* Returns the number of user l c s messages where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @return the number of matching user l c s messages
	*/
	public static int countByLCSMessageId(long lcsMessageId) {
		return getPersistence().countByLCSMessageId(lcsMessageId);
	}

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByU_LMI(long userId, long lcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByU_LMI(userId, lcsMessageId);
	}

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId) {
		return getPersistence().fetchByU_LMI(userId, lcsMessageId);
	}

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_LMI(userId, lcsMessageId, retrieveFromCache);
	}

	/**
	* Removes the user l c s message where userId = &#63; and lcsMessageId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the user l c s message that was removed
	*/
	public static UserLCSMessage removeByU_LMI(long userId, long lcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().removeByU_LMI(userId, lcsMessageId);
	}

	/**
	* Returns the number of user l c s messages where userId = &#63; and lcsMessageId = &#63;.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the number of matching user l c s messages
	*/
	public static int countByU_LMI(long userId, long lcsMessageId) {
		return getPersistence().countByU_LMI(userId, lcsMessageId);
	}

	/**
	* Returns all the user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @return the matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_H(long userId, boolean hidden) {
		return getPersistence().findByU_H(userId, hidden);
	}

	/**
	* Returns a range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end) {
		return getPersistence().findByU_H(userId, hidden, start, end);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .findByU_H(userId, hidden, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_H(long userId, boolean hidden,
		int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_H(userId, hidden, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByU_H_First(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByU_H_First(userId, hidden, orderByComparator);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_H_First(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByU_H_First(userId, hidden, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByU_H_Last(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByU_H_Last(userId, hidden, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_H_Last(long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByU_H_Last(userId, hidden, orderByComparator);
	}

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage[] findByU_H_PrevAndNext(
		long userLcsMessageId, long userId, boolean hidden,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByU_H_PrevAndNext(userLcsMessageId, userId, hidden,
			orderByComparator);
	}

	/**
	* Removes all the user l c s messages where userId = &#63; and hidden = &#63; from the database.
	*
	* @param userId the user ID
	* @param hidden the hidden
	*/
	public static void removeByU_H(long userId, boolean hidden) {
		getPersistence().removeByU_H(userId, hidden);
	}

	/**
	* Returns the number of user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @return the number of matching user l c s messages
	*/
	public static int countByU_H(long userId, boolean hidden) {
		return getPersistence().countByU_H(userId, hidden);
	}

	/**
	* Returns all the user l c s messages where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @return the matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_R(long userId, boolean read) {
		return getPersistence().findByU_R(userId, read);
	}

	/**
	* Returns a range of all the user l c s messages where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end) {
		return getPersistence().findByU_R(userId, read, start, end);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end, OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence()
				   .findByU_R(userId, read, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user l c s messages where userId = &#63; and read = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param read the read
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user l c s messages
	*/
	public static List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_R(userId, read, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByU_R_First(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByU_R_First(userId, read, orderByComparator);
	}

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_R_First(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence().fetchByU_R_First(userId, read, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public static UserLCSMessage findByU_R_Last(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByU_R_Last(userId, read, orderByComparator);
	}

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public static UserLCSMessage fetchByU_R_Last(long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence().fetchByU_R_Last(userId, read, orderByComparator);
	}

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage[] findByU_R_PrevAndNext(
		long userLcsMessageId, long userId, boolean read,
		OrderByComparator<UserLCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence()
				   .findByU_R_PrevAndNext(userLcsMessageId, userId, read,
			orderByComparator);
	}

	/**
	* Removes all the user l c s messages where userId = &#63; and read = &#63; from the database.
	*
	* @param userId the user ID
	* @param read the read
	*/
	public static void removeByU_R(long userId, boolean read) {
		getPersistence().removeByU_R(userId, read);
	}

	/**
	* Returns the number of user l c s messages where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @return the number of matching user l c s messages
	*/
	public static int countByU_R(long userId, boolean read) {
		return getPersistence().countByU_R(userId, read);
	}

	/**
	* Caches the user l c s message in the entity cache if it is enabled.
	*
	* @param userLCSMessage the user l c s message
	*/
	public static void cacheResult(UserLCSMessage userLCSMessage) {
		getPersistence().cacheResult(userLCSMessage);
	}

	/**
	* Caches the user l c s messages in the entity cache if it is enabled.
	*
	* @param userLCSMessages the user l c s messages
	*/
	public static void cacheResult(List<UserLCSMessage> userLCSMessages) {
		getPersistence().cacheResult(userLCSMessages);
	}

	/**
	* Creates a new user l c s message with the primary key. Does not add the user l c s message to the database.
	*
	* @param userLcsMessageId the primary key for the new user l c s message
	* @return the new user l c s message
	*/
	public static UserLCSMessage create(long userLcsMessageId) {
		return getPersistence().create(userLcsMessageId);
	}

	/**
	* Removes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message that was removed
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage remove(long userLcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().remove(userLcsMessageId);
	}

	public static UserLCSMessage updateImpl(UserLCSMessage userLCSMessage) {
		return getPersistence().updateImpl(userLCSMessage);
	}

	/**
	* Returns the user l c s message with the primary key or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage findByPrimaryKey(long userLcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException {
		return getPersistence().findByPrimaryKey(userLcsMessageId);
	}

	/**
	* Returns the user l c s message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message, or <code>null</code> if a user l c s message with the primary key could not be found
	*/
	public static UserLCSMessage fetchByPrimaryKey(long userLcsMessageId) {
		return getPersistence().fetchByPrimaryKey(userLcsMessageId);
	}

	public static java.util.Map<java.io.Serializable, UserLCSMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user l c s messages.
	*
	* @return the user l c s messages
	*/
	public static List<UserLCSMessage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @return the range of user l c s messages
	*/
	public static List<UserLCSMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user l c s messages
	*/
	public static List<UserLCSMessage> findAll(int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserLCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user l c s messages
	* @param end the upper bound of the range of user l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user l c s messages
	*/
	public static List<UserLCSMessage> findAll(int start, int end,
		OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user l c s messages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user l c s messages.
	*
	* @return the number of user l c s messages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserLCSMessagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserLCSMessagePersistence, UserLCSMessagePersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserLCSMessagePersistence.class);
}