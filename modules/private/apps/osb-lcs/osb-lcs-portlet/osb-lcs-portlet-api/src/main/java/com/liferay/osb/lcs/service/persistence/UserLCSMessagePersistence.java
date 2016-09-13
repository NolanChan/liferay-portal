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

import com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException;
import com.liferay.osb.lcs.model.UserLCSMessage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the user l c s message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.UserLCSMessagePersistenceImpl
 * @see UserLCSMessageUtil
 * @generated
 */
@ProviderType
public interface UserLCSMessagePersistence extends BasePersistence<UserLCSMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserLCSMessageUtil} to access the user l c s message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user l c s messages where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user l c s messages
	*/
	public java.util.List<UserLCSMessage> findByUserId(long userId);

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
	public java.util.List<UserLCSMessage> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<UserLCSMessage> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public java.util.List<UserLCSMessage> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where userId = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public UserLCSMessage[] findByUserId_PrevAndNext(long userLcsMessageId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Removes all the user l c s messages where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of user l c s messages where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user l c s messages
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the user l c s messages where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s messages
	*/
	public java.util.List<UserLCSMessage> findByLCSMessageId(long lcsMessageId);

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
	public java.util.List<UserLCSMessage> findByLCSMessageId(
		long lcsMessageId, int start, int end);

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
	public java.util.List<UserLCSMessage> findByLCSMessageId(
		long lcsMessageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public java.util.List<UserLCSMessage> findByLCSMessageId(
		long lcsMessageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByLCSMessageId_First(long lcsMessageId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the first user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByLCSMessageId_First(long lcsMessageId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByLCSMessageId_Last(long lcsMessageId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the last user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByLCSMessageId_Last(long lcsMessageId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the user l c s messages before and after the current user l c s message in the ordered set where lcsMessageId = &#63;.
	*
	* @param userLcsMessageId the primary key of the current user l c s message
	* @param lcsMessageId the lcs message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public UserLCSMessage[] findByLCSMessageId_PrevAndNext(
		long userLcsMessageId, long lcsMessageId,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Removes all the user l c s messages where lcsMessageId = &#63; from the database.
	*
	* @param lcsMessageId the lcs message ID
	*/
	public void removeByLCSMessageId(long lcsMessageId);

	/**
	* Returns the number of user l c s messages where lcsMessageId = &#63;.
	*
	* @param lcsMessageId the lcs message ID
	* @return the number of matching user l c s messages
	*/
	public int countByLCSMessageId(long lcsMessageId);

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByU_LMI(long userId, long lcsMessageId)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId);

	/**
	* Returns the user l c s message where userId = &#63; and lcsMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_LMI(long userId, long lcsMessageId,
		boolean retrieveFromCache);

	/**
	* Removes the user l c s message where userId = &#63; and lcsMessageId = &#63; from the database.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the user l c s message that was removed
	*/
	public UserLCSMessage removeByU_LMI(long userId, long lcsMessageId)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the number of user l c s messages where userId = &#63; and lcsMessageId = &#63;.
	*
	* @param userId the user ID
	* @param lcsMessageId the lcs message ID
	* @return the number of matching user l c s messages
	*/
	public int countByU_LMI(long userId, long lcsMessageId);

	/**
	* Returns all the user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @return the matching user l c s messages
	*/
	public java.util.List<UserLCSMessage> findByU_H(long userId, boolean hidden);

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
	public java.util.List<UserLCSMessage> findByU_H(long userId,
		boolean hidden, int start, int end);

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
	public java.util.List<UserLCSMessage> findByU_H(long userId,
		boolean hidden, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public java.util.List<UserLCSMessage> findByU_H(long userId,
		boolean hidden, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByU_H_First(long userId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_H_First(long userId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByU_H_Last(long userId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_H_Last(long userId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public UserLCSMessage[] findByU_H_PrevAndNext(long userLcsMessageId,
		long userId, boolean hidden,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Removes all the user l c s messages where userId = &#63; and hidden = &#63; from the database.
	*
	* @param userId the user ID
	* @param hidden the hidden
	*/
	public void removeByU_H(long userId, boolean hidden);

	/**
	* Returns the number of user l c s messages where userId = &#63; and hidden = &#63;.
	*
	* @param userId the user ID
	* @param hidden the hidden
	* @return the number of matching user l c s messages
	*/
	public int countByU_H(long userId, boolean hidden);

	/**
	* Returns all the user l c s messages where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @return the matching user l c s messages
	*/
	public java.util.List<UserLCSMessage> findByU_R(long userId, boolean read);

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
	public java.util.List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end);

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
	public java.util.List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public java.util.List<UserLCSMessage> findByU_R(long userId, boolean read,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByU_R_First(long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the first user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_R_First(long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message
	* @throws NoSuchUserLCSMessageException if a matching user l c s message could not be found
	*/
	public UserLCSMessage findByU_R_Last(long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the last user l c s message in the ordered set where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user l c s message, or <code>null</code> if a matching user l c s message could not be found
	*/
	public UserLCSMessage fetchByU_R_Last(long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public UserLCSMessage[] findByU_R_PrevAndNext(long userLcsMessageId,
		long userId, boolean read,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator)
		throws NoSuchUserLCSMessageException;

	/**
	* Removes all the user l c s messages where userId = &#63; and read = &#63; from the database.
	*
	* @param userId the user ID
	* @param read the read
	*/
	public void removeByU_R(long userId, boolean read);

	/**
	* Returns the number of user l c s messages where userId = &#63; and read = &#63;.
	*
	* @param userId the user ID
	* @param read the read
	* @return the number of matching user l c s messages
	*/
	public int countByU_R(long userId, boolean read);

	/**
	* Caches the user l c s message in the entity cache if it is enabled.
	*
	* @param userLCSMessage the user l c s message
	*/
	public void cacheResult(UserLCSMessage userLCSMessage);

	/**
	* Caches the user l c s messages in the entity cache if it is enabled.
	*
	* @param userLCSMessages the user l c s messages
	*/
	public void cacheResult(java.util.List<UserLCSMessage> userLCSMessages);

	/**
	* Creates a new user l c s message with the primary key. Does not add the user l c s message to the database.
	*
	* @param userLcsMessageId the primary key for the new user l c s message
	* @return the new user l c s message
	*/
	public UserLCSMessage create(long userLcsMessageId);

	/**
	* Removes the user l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message that was removed
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public UserLCSMessage remove(long userLcsMessageId)
		throws NoSuchUserLCSMessageException;

	public UserLCSMessage updateImpl(UserLCSMessage userLCSMessage);

	/**
	* Returns the user l c s message with the primary key or throws a {@link NoSuchUserLCSMessageException} if it could not be found.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message
	* @throws NoSuchUserLCSMessageException if a user l c s message with the primary key could not be found
	*/
	public UserLCSMessage findByPrimaryKey(long userLcsMessageId)
		throws NoSuchUserLCSMessageException;

	/**
	* Returns the user l c s message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userLcsMessageId the primary key of the user l c s message
	* @return the user l c s message, or <code>null</code> if a user l c s message with the primary key could not be found
	*/
	public UserLCSMessage fetchByPrimaryKey(long userLcsMessageId);

	@Override
	public java.util.Map<java.io.Serializable, UserLCSMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user l c s messages.
	*
	* @return the user l c s messages
	*/
	public java.util.List<UserLCSMessage> findAll();

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
	public java.util.List<UserLCSMessage> findAll(int start, int end);

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
	public java.util.List<UserLCSMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator);

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
	public java.util.List<UserLCSMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserLCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user l c s messages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user l c s messages.
	*
	* @return the number of user l c s messages
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}