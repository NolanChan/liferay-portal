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

import com.liferay.osb.lcs.model.LCSInvitation;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s invitation service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSInvitationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSInvitationPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSInvitationPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSInvitationUtil {
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
	public static void clearCache(LCSInvitation lcsInvitation) {
		getPersistence().clearCache(lcsInvitation);
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
	public static List<LCSInvitation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSInvitation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSInvitation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSInvitation update(LCSInvitation lcsInvitation) {
		return getPersistence().update(lcsInvitation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSInvitation update(LCSInvitation lcsInvitation,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsInvitation, serviceContext);
	}

	/**
	* Returns all the l c s invitations where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s invitations
	*/
	public static List<LCSInvitation> findByLCSProjectId(long lcsProjectId) {
		return getPersistence().findByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns a range of all the l c s invitations where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @return the range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end) {
		return getPersistence().findByLCSProjectId(lcsProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the l c s invitations where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s invitations where lcsProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lcsProjectId the lcs project ID
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end, OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLCSProjectId(lcsProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public static LCSInvitation findByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByLCSProjectId_First(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_First(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public static LCSInvitation findByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByLCSProjectId_Last(long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .fetchByLCSProjectId_Last(lcsProjectId, orderByComparator);
	}

	/**
	* Returns the l c s invitations before and after the current l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsInvitationId the primary key of the current l c s invitation
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public static LCSInvitation[] findByLCSProjectId_PrevAndNext(
		long lcsInvitationId, long lcsProjectId,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByLCSProjectId_PrevAndNext(lcsInvitationId,
			lcsProjectId, orderByComparator);
	}

	/**
	* Removes all the l c s invitations where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public static void removeByLCSProjectId(long lcsProjectId) {
		getPersistence().removeByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns the number of l c s invitations where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s invitations
	*/
	public static int countByLCSProjectId(long lcsProjectId) {
		return getPersistence().countByLCSProjectId(lcsProjectId);
	}

	/**
	* Returns all the l c s invitations where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the matching l c s invitations
	*/
	public static List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress) {
		return getPersistence().findByEmailAddress(emailAddress);
	}

	/**
	* Returns a range of all the l c s invitations where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @return the range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end) {
		return getPersistence().findByEmailAddress(emailAddress, start, end);
	}

	/**
	* Returns an ordered range of all the l c s invitations where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .findByEmailAddress(emailAddress, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s invitations where emailAddress = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param emailAddress the email address
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s invitations
	*/
	public static List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEmailAddress(emailAddress, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public static LCSInvitation findByEmailAddress_First(
		java.lang.String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByEmailAddress_First(emailAddress, orderByComparator);
	}

	/**
	* Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByEmailAddress_First(
		java.lang.String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .fetchByEmailAddress_First(emailAddress, orderByComparator);
	}

	/**
	* Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public static LCSInvitation findByEmailAddress_Last(
		java.lang.String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByEmailAddress_Last(emailAddress, orderByComparator);
	}

	/**
	* Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByEmailAddress_Last(
		java.lang.String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence()
				   .fetchByEmailAddress_Last(emailAddress, orderByComparator);
	}

	/**
	* Returns the l c s invitations before and after the current l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param lcsInvitationId the primary key of the current l c s invitation
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public static LCSInvitation[] findByEmailAddress_PrevAndNext(
		long lcsInvitationId, java.lang.String emailAddress,
		OrderByComparator<LCSInvitation> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence()
				   .findByEmailAddress_PrevAndNext(lcsInvitationId,
			emailAddress, orderByComparator);
	}

	/**
	* Removes all the l c s invitations where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	*/
	public static void removeByEmailAddress(java.lang.String emailAddress) {
		getPersistence().removeByEmailAddress(emailAddress);
	}

	/**
	* Returns the number of l c s invitations where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching l c s invitations
	*/
	public static int countByEmailAddress(java.lang.String emailAddress) {
		return getPersistence().countByEmailAddress(emailAddress);
	}

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public static LCSInvitation findByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence().findByLPI_EA(lcsProjectId, emailAddress);
	}

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress) {
		return getPersistence().fetchByLPI_EA(lcsProjectId, emailAddress);
	}

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public static LCSInvitation fetchByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByLPI_EA(lcsProjectId, emailAddress, retrieveFromCache);
	}

	/**
	* Removes the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the l c s invitation that was removed
	*/
	public static LCSInvitation removeByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence().removeByLPI_EA(lcsProjectId, emailAddress);
	}

	/**
	* Returns the number of l c s invitations where lcsProjectId = &#63; and emailAddress = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the number of matching l c s invitations
	*/
	public static int countByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress) {
		return getPersistence().countByLPI_EA(lcsProjectId, emailAddress);
	}

	/**
	* Caches the l c s invitation in the entity cache if it is enabled.
	*
	* @param lcsInvitation the l c s invitation
	*/
	public static void cacheResult(LCSInvitation lcsInvitation) {
		getPersistence().cacheResult(lcsInvitation);
	}

	/**
	* Caches the l c s invitations in the entity cache if it is enabled.
	*
	* @param lcsInvitations the l c s invitations
	*/
	public static void cacheResult(List<LCSInvitation> lcsInvitations) {
		getPersistence().cacheResult(lcsInvitations);
	}

	/**
	* Creates a new l c s invitation with the primary key. Does not add the l c s invitation to the database.
	*
	* @param lcsInvitationId the primary key for the new l c s invitation
	* @return the new l c s invitation
	*/
	public static LCSInvitation create(long lcsInvitationId) {
		return getPersistence().create(lcsInvitationId);
	}

	/**
	* Removes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation that was removed
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public static LCSInvitation remove(long lcsInvitationId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence().remove(lcsInvitationId);
	}

	public static LCSInvitation updateImpl(LCSInvitation lcsInvitation) {
		return getPersistence().updateImpl(lcsInvitation);
	}

	/**
	* Returns the l c s invitation with the primary key or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public static LCSInvitation findByPrimaryKey(long lcsInvitationId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSInvitationException {
		return getPersistence().findByPrimaryKey(lcsInvitationId);
	}

	/**
	* Returns the l c s invitation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation, or <code>null</code> if a l c s invitation with the primary key could not be found
	*/
	public static LCSInvitation fetchByPrimaryKey(long lcsInvitationId) {
		return getPersistence().fetchByPrimaryKey(lcsInvitationId);
	}

	public static java.util.Map<java.io.Serializable, LCSInvitation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s invitations.
	*
	* @return the l c s invitations
	*/
	public static List<LCSInvitation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the l c s invitations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @return the range of l c s invitations
	*/
	public static List<LCSInvitation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the l c s invitations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s invitations
	*/
	public static List<LCSInvitation> findAll(int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the l c s invitations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s invitations
	*/
	public static List<LCSInvitation> findAll(int start, int end,
		OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s invitations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s invitations.
	*
	* @return the number of l c s invitations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LCSInvitationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSInvitationPersistence, LCSInvitationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSInvitationPersistence.class);
}