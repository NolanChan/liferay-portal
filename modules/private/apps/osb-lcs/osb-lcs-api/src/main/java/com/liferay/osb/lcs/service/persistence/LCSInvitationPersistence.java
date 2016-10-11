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

import com.liferay.osb.lcs.exception.NoSuchLCSInvitationException;
import com.liferay.osb.lcs.model.LCSInvitation;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s invitation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSInvitationPersistenceImpl
 * @see LCSInvitationUtil
 * @generated
 */
@ProviderType
public interface LCSInvitationPersistence extends BasePersistence<LCSInvitation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSInvitationUtil} to access the l c s invitation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s invitations where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the matching l c s invitations
	*/
	public java.util.List<LCSInvitation> findByLCSProjectId(long lcsProjectId);

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
	public java.util.List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end);

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
	public java.util.List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

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
	public java.util.List<LCSInvitation> findByLCSProjectId(long lcsProjectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public LCSInvitation findByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Returns the first l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByLCSProjectId_First(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

	/**
	* Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public LCSInvitation findByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Returns the last l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByLCSProjectId_Last(long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

	/**
	* Returns the l c s invitations before and after the current l c s invitation in the ordered set where lcsProjectId = &#63;.
	*
	* @param lcsInvitationId the primary key of the current l c s invitation
	* @param lcsProjectId the lcs project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public LCSInvitation[] findByLCSProjectId_PrevAndNext(
		long lcsInvitationId, long lcsProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Removes all the l c s invitations where lcsProjectId = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	*/
	public void removeByLCSProjectId(long lcsProjectId);

	/**
	* Returns the number of l c s invitations where lcsProjectId = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @return the number of matching l c s invitations
	*/
	public int countByLCSProjectId(long lcsProjectId);

	/**
	* Returns all the l c s invitations where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the matching l c s invitations
	*/
	public java.util.List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress);

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
	public java.util.List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end);

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
	public java.util.List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

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
	public java.util.List<LCSInvitation> findByEmailAddress(
		java.lang.String emailAddress, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public LCSInvitation findByEmailAddress_First(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Returns the first l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByEmailAddress_First(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

	/**
	* Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public LCSInvitation findByEmailAddress_Last(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Returns the last l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByEmailAddress_Last(
		java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

	/**
	* Returns the l c s invitations before and after the current l c s invitation in the ordered set where emailAddress = &#63;.
	*
	* @param lcsInvitationId the primary key of the current l c s invitation
	* @param emailAddress the email address
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public LCSInvitation[] findByEmailAddress_PrevAndNext(
		long lcsInvitationId, java.lang.String emailAddress,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator)
		throws NoSuchLCSInvitationException;

	/**
	* Removes all the l c s invitations where emailAddress = &#63; from the database.
	*
	* @param emailAddress the email address
	*/
	public void removeByEmailAddress(java.lang.String emailAddress);

	/**
	* Returns the number of l c s invitations where emailAddress = &#63;.
	*
	* @param emailAddress the email address
	* @return the number of matching l c s invitations
	*/
	public int countByEmailAddress(java.lang.String emailAddress);

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the matching l c s invitation
	* @throws NoSuchLCSInvitationException if a matching l c s invitation could not be found
	*/
	public LCSInvitation findByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress) throws NoSuchLCSInvitationException;

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress);

	/**
	* Returns the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s invitation, or <code>null</code> if a matching l c s invitation could not be found
	*/
	public LCSInvitation fetchByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress, boolean retrieveFromCache);

	/**
	* Removes the l c s invitation where lcsProjectId = &#63; and emailAddress = &#63; from the database.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the l c s invitation that was removed
	*/
	public LCSInvitation removeByLPI_EA(long lcsProjectId,
		java.lang.String emailAddress) throws NoSuchLCSInvitationException;

	/**
	* Returns the number of l c s invitations where lcsProjectId = &#63; and emailAddress = &#63;.
	*
	* @param lcsProjectId the lcs project ID
	* @param emailAddress the email address
	* @return the number of matching l c s invitations
	*/
	public int countByLPI_EA(long lcsProjectId, java.lang.String emailAddress);

	/**
	* Caches the l c s invitation in the entity cache if it is enabled.
	*
	* @param lcsInvitation the l c s invitation
	*/
	public void cacheResult(LCSInvitation lcsInvitation);

	/**
	* Caches the l c s invitations in the entity cache if it is enabled.
	*
	* @param lcsInvitations the l c s invitations
	*/
	public void cacheResult(java.util.List<LCSInvitation> lcsInvitations);

	/**
	* Creates a new l c s invitation with the primary key. Does not add the l c s invitation to the database.
	*
	* @param lcsInvitationId the primary key for the new l c s invitation
	* @return the new l c s invitation
	*/
	public LCSInvitation create(long lcsInvitationId);

	/**
	* Removes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation that was removed
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public LCSInvitation remove(long lcsInvitationId)
		throws NoSuchLCSInvitationException;

	public LCSInvitation updateImpl(LCSInvitation lcsInvitation);

	/**
	* Returns the l c s invitation with the primary key or throws a {@link NoSuchLCSInvitationException} if it could not be found.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation
	* @throws NoSuchLCSInvitationException if a l c s invitation with the primary key could not be found
	*/
	public LCSInvitation findByPrimaryKey(long lcsInvitationId)
		throws NoSuchLCSInvitationException;

	/**
	* Returns the l c s invitation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation, or <code>null</code> if a l c s invitation with the primary key could not be found
	*/
	public LCSInvitation fetchByPrimaryKey(long lcsInvitationId);

	@Override
	public java.util.Map<java.io.Serializable, LCSInvitation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s invitations.
	*
	* @return the l c s invitations
	*/
	public java.util.List<LCSInvitation> findAll();

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
	public java.util.List<LCSInvitation> findAll(int start, int end);

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
	public java.util.List<LCSInvitation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator);

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
	public java.util.List<LCSInvitation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSInvitation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s invitations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s invitations.
	*
	* @return the number of l c s invitations
	*/
	public int countAll();
}