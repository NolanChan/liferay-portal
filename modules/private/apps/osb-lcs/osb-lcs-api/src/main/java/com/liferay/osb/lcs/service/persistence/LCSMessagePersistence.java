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

import com.liferay.osb.lcs.exception.NoSuchLCSMessageException;
import com.liferay.osb.lcs.model.LCSMessage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the l c s message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSMessagePersistenceImpl
 * @see LCSMessageUtil
 * @generated
 */
@ProviderType
public interface LCSMessagePersistence extends BasePersistence<LCSMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMessageUtil} to access the l c s message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C(long classNameId, long classPK);

	/**
	* Returns a range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end);

	/**
	* Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the l c s messages before and after the current l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param lcsMessageId the primary key of the current l c s message
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage[] findByC_C_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Removes all the l c s messages where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @return the matching l c s messages
	*/
	public java.util.List<LCSMessage> findByED_G(Date endDate, boolean global);

	/**
	* Returns a range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param global the global
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end);

	/**
	* Returns an ordered range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param global the global
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns an ordered range of all the l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param global the global
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByED_G_First(Date endDate, boolean global,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByED_G_First(Date endDate, boolean global,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByED_G_Last(Date endDate, boolean global,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByED_G_Last(Date endDate, boolean global,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the l c s messages before and after the current l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param lcsMessageId the primary key of the current l c s message
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage[] findByED_G_PrevAndNext(long lcsMessageId, Date endDate,
		boolean global,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Removes all the l c s messages where endDate &ge; &#63; and global = &#63; from the database.
	*
	* @param endDate the end date
	* @param global the global
	*/
	public void removeByED_G(Date endDate, boolean global);

	/**
	* Returns the number of l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @return the number of matching l c s messages
	*/
	public int countByED_G(Date endDate, boolean global);

	/**
	* Returns all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s messages
	*/
	public java.util.List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK);

	/**
	* Returns a range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end);

	/**
	* Returns an ordered range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns an ordered range of all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByS_C_C_First(java.lang.String sourceSystemName,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the first l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByS_C_C_First(java.lang.String sourceSystemName,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the last l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByS_C_C_Last(java.lang.String sourceSystemName,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the last l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByS_C_C_Last(java.lang.String sourceSystemName,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the l c s messages before and after the current l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param lcsMessageId the primary key of the current l c s message
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage[] findByS_C_C_PrevAndNext(long lcsMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Removes all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByS_C_C(java.lang.String sourceSystemName,
		long classNameId, long classPK);

	/**
	* Returns the number of l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public int countByS_C_C(java.lang.String sourceSystemName,
		long classNameId, long classPK);

	/**
	* Returns all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C_T(long classNameId,
		long classPK, int type);

	/**
	* Returns a range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end);

	/**
	* Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns an ordered range of all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s messages
	*/
	public java.util.List<LCSMessage> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByC_C_T_First(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByC_C_T_First(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByC_C_T_Last(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByC_C_T_Last(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns the l c s messages before and after the current l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param lcsMessageId the primary key of the current l c s message
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage[] findByC_C_T_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator)
		throws NoSuchLCSMessageException;

	/**
	* Removes all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type);

	/**
	* Returns the number of l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching l c s messages
	*/
	public int countByC_C_T(long classNameId, long classPK, int type);

	/**
	* Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchLCSMessageException} if it could not be found.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public LCSMessage findByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK)
		throws NoSuchLCSMessageException;

	/**
	* Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK);

	/**
	* Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public LCSMessage fetchByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the l c s message that was removed
	*/
	public LCSMessage removeByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK)
		throws NoSuchLCSMessageException;

	/**
	* Returns the number of l c s messages where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public int countByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK);

	/**
	* Caches the l c s message in the entity cache if it is enabled.
	*
	* @param lcsMessage the l c s message
	*/
	public void cacheResult(LCSMessage lcsMessage);

	/**
	* Caches the l c s messages in the entity cache if it is enabled.
	*
	* @param lcsMessages the l c s messages
	*/
	public void cacheResult(java.util.List<LCSMessage> lcsMessages);

	/**
	* Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	*
	* @param lcsMessageId the primary key for the new l c s message
	* @return the new l c s message
	*/
	public LCSMessage create(long lcsMessageId);

	/**
	* Removes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message that was removed
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage remove(long lcsMessageId)
		throws NoSuchLCSMessageException;

	public LCSMessage updateImpl(LCSMessage lcsMessage);

	/**
	* Returns the l c s message with the primary key or throws a {@link NoSuchLCSMessageException} if it could not be found.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public LCSMessage findByPrimaryKey(long lcsMessageId)
		throws NoSuchLCSMessageException;

	/**
	* Returns the l c s message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message, or <code>null</code> if a l c s message with the primary key could not be found
	*/
	public LCSMessage fetchByPrimaryKey(long lcsMessageId);

	@Override
	public java.util.Map<java.io.Serializable, LCSMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s messages.
	*
	* @return the l c s messages
	*/
	public java.util.List<LCSMessage> findAll();

	/**
	* Returns a range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of l c s messages
	*/
	public java.util.List<LCSMessage> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s messages
	*/
	public java.util.List<LCSMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator);

	/**
	* Returns an ordered range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s messages
	*/
	public java.util.List<LCSMessage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s messages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s messages.
	*
	* @return the number of l c s messages
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}