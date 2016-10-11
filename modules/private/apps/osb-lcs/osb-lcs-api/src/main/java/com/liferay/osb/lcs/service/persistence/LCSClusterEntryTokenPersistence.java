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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryTokenException;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s cluster entry token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSClusterEntryTokenPersistenceImpl
 * @see LCSClusterEntryTokenUtil
 * @generated
 */
@ProviderType
public interface LCSClusterEntryTokenPersistence extends BasePersistence<LCSClusterEntryToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterEntryTokenUtil} to access the l c s cluster entry token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or throws a {@link NoSuchLCSClusterEntryTokenException} if it could not be found.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s cluster entry token
	* @throws NoSuchLCSClusterEntryTokenException if a matching l c s cluster entry token could not be found
	*/
	public LCSClusterEntryToken findByLCSClusterEntryId(long lcsClusterEntryId)
		throws NoSuchLCSClusterEntryTokenException;

	/**
	* Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the matching l c s cluster entry token, or <code>null</code> if a matching l c s cluster entry token could not be found
	*/
	public LCSClusterEntryToken fetchByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Returns the l c s cluster entry token where lcsClusterEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s cluster entry token, or <code>null</code> if a matching l c s cluster entry token could not be found
	*/
	public LCSClusterEntryToken fetchByLCSClusterEntryId(
		long lcsClusterEntryId, boolean retrieveFromCache);

	/**
	* Removes the l c s cluster entry token where lcsClusterEntryId = &#63; from the database.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the l c s cluster entry token that was removed
	*/
	public LCSClusterEntryToken removeByLCSClusterEntryId(
		long lcsClusterEntryId) throws NoSuchLCSClusterEntryTokenException;

	/**
	* Returns the number of l c s cluster entry tokens where lcsClusterEntryId = &#63;.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID
	* @return the number of matching l c s cluster entry tokens
	*/
	public int countByLCSClusterEntryId(long lcsClusterEntryId);

	/**
	* Caches the l c s cluster entry token in the entity cache if it is enabled.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	*/
	public void cacheResult(LCSClusterEntryToken lcsClusterEntryToken);

	/**
	* Caches the l c s cluster entry tokens in the entity cache if it is enabled.
	*
	* @param lcsClusterEntryTokens the l c s cluster entry tokens
	*/
	public void cacheResult(
		java.util.List<LCSClusterEntryToken> lcsClusterEntryTokens);

	/**
	* Creates a new l c s cluster entry token with the primary key. Does not add the l c s cluster entry token to the database.
	*
	* @param lcsClusterEntryTokenId the primary key for the new l c s cluster entry token
	* @return the new l c s cluster entry token
	*/
	public LCSClusterEntryToken create(long lcsClusterEntryTokenId);

	/**
	* Removes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	* @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	*/
	public LCSClusterEntryToken remove(long lcsClusterEntryTokenId)
		throws NoSuchLCSClusterEntryTokenException;

	public LCSClusterEntryToken updateImpl(
		LCSClusterEntryToken lcsClusterEntryToken);

	/**
	* Returns the l c s cluster entry token with the primary key or throws a {@link NoSuchLCSClusterEntryTokenException} if it could not be found.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token
	* @throws NoSuchLCSClusterEntryTokenException if a l c s cluster entry token with the primary key could not be found
	*/
	public LCSClusterEntryToken findByPrimaryKey(long lcsClusterEntryTokenId)
		throws NoSuchLCSClusterEntryTokenException;

	/**
	* Returns the l c s cluster entry token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token, or <code>null</code> if a l c s cluster entry token with the primary key could not be found
	*/
	public LCSClusterEntryToken fetchByPrimaryKey(long lcsClusterEntryTokenId);

	@Override
	public java.util.Map<java.io.Serializable, LCSClusterEntryToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s cluster entry tokens.
	*
	* @return the l c s cluster entry tokens
	*/
	public java.util.List<LCSClusterEntryToken> findAll();

	/**
	* Returns a range of all the l c s cluster entry tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entry tokens
	* @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	* @return the range of l c s cluster entry tokens
	*/
	public java.util.List<LCSClusterEntryToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s cluster entry tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entry tokens
	* @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s cluster entry tokens
	*/
	public java.util.List<LCSClusterEntryToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntryToken> orderByComparator);

	/**
	* Returns an ordered range of all the l c s cluster entry tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entry tokens
	* @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s cluster entry tokens
	*/
	public java.util.List<LCSClusterEntryToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSClusterEntryToken> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s cluster entry tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s cluster entry tokens.
	*
	* @return the number of l c s cluster entry tokens
	*/
	public int countAll();
}