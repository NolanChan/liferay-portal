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

import com.liferay.osb.lcs.model.LCSMessage;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the l c s message service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMessagePersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSMessagePersistenceImpl
 * @generated
 */
@ProviderType
public class LCSMessageUtil {
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
	public static void clearCache(LCSMessage lcsMessage) {
		getPersistence().clearCache(lcsMessage);
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
	public static List<LCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSMessage update(LCSMessage lcsMessage) {
		return getPersistence().update(lcsMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSMessage update(LCSMessage lcsMessage,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsMessage, serviceContext);
	}

	/**
	* Returns all the l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s messages
	*/
	public static List<LCSMessage> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static List<LCSMessage> findByC_C(long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public static LCSMessage findByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public static LCSMessage findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static LCSMessage[] findByC_C_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_PrevAndNext(lcsMessageId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Removes all the l c s messages where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of l c s messages where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @return the matching l c s messages
	*/
	public static List<LCSMessage> findByED_G(Date endDate, boolean global) {
		return getPersistence().findByED_G(endDate, global);
	}

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
	public static List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end) {
		return getPersistence().findByED_G(endDate, global, start, end);
	}

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
	public static List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .findByED_G(endDate, global, start, end, orderByComparator);
	}

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
	public static List<LCSMessage> findByED_G(Date endDate, boolean global,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByED_G(endDate, global, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public static LCSMessage findByED_G_First(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByED_G_First(endDate, global, orderByComparator);
	}

	/**
	* Returns the first l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByED_G_First(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByED_G_First(endDate, global, orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message
	* @throws NoSuchLCSMessageException if a matching l c s message could not be found
	*/
	public static LCSMessage findByED_G_Last(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByED_G_Last(endDate, global, orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByED_G_Last(Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByED_G_Last(endDate, global, orderByComparator);
	}

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
	public static LCSMessage[] findByED_G_PrevAndNext(long lcsMessageId,
		Date endDate, boolean global,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByED_G_PrevAndNext(lcsMessageId, endDate, global,
			orderByComparator);
	}

	/**
	* Removes all the l c s messages where endDate &ge; &#63; and global = &#63; from the database.
	*
	* @param endDate the end date
	* @param global the global
	*/
	public static void removeByED_G(Date endDate, boolean global) {
		getPersistence().removeByED_G(endDate, global);
	}

	/**
	* Returns the number of l c s messages where endDate &ge; &#63; and global = &#63;.
	*
	* @param endDate the end date
	* @param global the global
	* @return the number of matching l c s messages
	*/
	public static int countByED_G(Date endDate, boolean global) {
		return getPersistence().countByED_G(endDate, global);
	}

	/**
	* Returns all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s messages
	*/
	public static List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK) {
		return getPersistence()
				   .findByS_C_C(sourceSystemName, classNameId, classPK);
	}

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
	public static List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end) {
		return getPersistence()
				   .findByS_C_C(sourceSystemName, classNameId, classPK, start,
			end);
	}

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
	public static List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .findByS_C_C(sourceSystemName, classNameId, classPK, start,
			end, orderByComparator);
	}

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
	public static List<LCSMessage> findByS_C_C(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		int start, int end, OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByS_C_C(sourceSystemName, classNameId, classPK, start,
			end, orderByComparator, retrieveFromCache);
	}

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
	public static LCSMessage findByS_C_C_First(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByS_C_C_First(sourceSystemName, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the first l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByS_C_C_First(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByS_C_C_First(sourceSystemName, classNameId, classPK,
			orderByComparator);
	}

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
	public static LCSMessage findByS_C_C_Last(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByS_C_C_Last(sourceSystemName, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByS_C_C_Last(
		java.lang.String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByS_C_C_Last(sourceSystemName, classNameId, classPK,
			orderByComparator);
	}

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
	public static LCSMessage[] findByS_C_C_PrevAndNext(long lcsMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByS_C_C_PrevAndNext(lcsMessageId, sourceSystemName,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByS_C_C(java.lang.String sourceSystemName,
		long classNameId, long classPK) {
		getPersistence().removeByS_C_C(sourceSystemName, classNameId, classPK);
	}

	/**
	* Returns the number of l c s messages where sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public static int countByS_C_C(java.lang.String sourceSystemName,
		long classNameId, long classPK) {
		return getPersistence()
				   .countByS_C_C(sourceSystemName, classNameId, classPK);
	}

	/**
	* Returns all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching l c s messages
	*/
	public static List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type) {
		return getPersistence().findByC_C_T(classNameId, classPK, type);
	}

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
	public static List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) {
		return getPersistence()
				   .findByC_C_T(classNameId, classPK, type, start, end);
	}

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
	public static List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator);
	}

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
	public static List<LCSMessage> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end,
		OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static LCSMessage findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_T_First(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the first l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_T_First(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static LCSMessage findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the last l c s message in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static LCSMessage[] findByC_C_T_PrevAndNext(long lcsMessageId,
		long classNameId, long classPK, int type,
		OrderByComparator<LCSMessage> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByC_C_T_PrevAndNext(lcsMessageId, classNameId, classPK,
			type, orderByComparator);
	}

	/**
	* Removes all the l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	*/
	public static void removeByC_C_T(long classNameId, long classPK, int type) {
		getPersistence().removeByC_C_T(classNameId, classPK, type);
	}

	/**
	* Returns the number of l c s messages where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching l c s messages
	*/
	public static int countByC_C_T(long classNameId, long classPK, int type) {
		return getPersistence().countByC_C_T(classNameId, classPK, type);
	}

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
	public static LCSMessage findByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .findByS_S_C_C(sourceMessageId, sourceSystemName,
			classNameId, classPK);
	}

	/**
	* Returns the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching l c s message, or <code>null</code> if a matching l c s message could not be found
	*/
	public static LCSMessage fetchByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK) {
		return getPersistence()
				   .fetchByS_S_C_C(sourceMessageId, sourceSystemName,
			classNameId, classPK);
	}

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
	public static LCSMessage fetchByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByS_S_C_C(sourceMessageId, sourceSystemName,
			classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the l c s message where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the l c s message that was removed
	*/
	public static LCSMessage removeByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence()
				   .removeByS_S_C_C(sourceMessageId, sourceSystemName,
			classNameId, classPK);
	}

	/**
	* Returns the number of l c s messages where sourceMessageId = &#63; and sourceSystemName = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param sourceMessageId the source message ID
	* @param sourceSystemName the source system name
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching l c s messages
	*/
	public static int countByS_S_C_C(long sourceMessageId,
		java.lang.String sourceSystemName, long classNameId, long classPK) {
		return getPersistence()
				   .countByS_S_C_C(sourceMessageId, sourceSystemName,
			classNameId, classPK);
	}

	/**
	* Caches the l c s message in the entity cache if it is enabled.
	*
	* @param lcsMessage the l c s message
	*/
	public static void cacheResult(LCSMessage lcsMessage) {
		getPersistence().cacheResult(lcsMessage);
	}

	/**
	* Caches the l c s messages in the entity cache if it is enabled.
	*
	* @param lcsMessages the l c s messages
	*/
	public static void cacheResult(List<LCSMessage> lcsMessages) {
		getPersistence().cacheResult(lcsMessages);
	}

	/**
	* Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	*
	* @param lcsMessageId the primary key for the new l c s message
	* @return the new l c s message
	*/
	public static LCSMessage create(long lcsMessageId) {
		return getPersistence().create(lcsMessageId);
	}

	/**
	* Removes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message that was removed
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public static LCSMessage remove(long lcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence().remove(lcsMessageId);
	}

	public static LCSMessage updateImpl(LCSMessage lcsMessage) {
		return getPersistence().updateImpl(lcsMessage);
	}

	/**
	* Returns the l c s message with the primary key or throws a {@link NoSuchLCSMessageException} if it could not be found.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message
	* @throws NoSuchLCSMessageException if a l c s message with the primary key could not be found
	*/
	public static LCSMessage findByPrimaryKey(long lcsMessageId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMessageException {
		return getPersistence().findByPrimaryKey(lcsMessageId);
	}

	/**
	* Returns the l c s message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message, or <code>null</code> if a l c s message with the primary key could not be found
	*/
	public static LCSMessage fetchByPrimaryKey(long lcsMessageId) {
		return getPersistence().fetchByPrimaryKey(lcsMessageId);
	}

	public static java.util.Map<java.io.Serializable, LCSMessage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s messages.
	*
	* @return the l c s messages
	*/
	public static List<LCSMessage> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LCSMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LCSMessage> findAll(int start, int end,
		OrderByComparator<LCSMessage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LCSMessage> findAll(int start, int end,
		OrderByComparator<LCSMessage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s messages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s messages.
	*
	* @return the number of l c s messages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LCSMessagePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMessagePersistence, LCSMessagePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSMessagePersistence.class);
}