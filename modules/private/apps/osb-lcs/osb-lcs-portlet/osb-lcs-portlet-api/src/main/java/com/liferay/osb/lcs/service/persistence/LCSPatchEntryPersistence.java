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

import com.liferay.osb.lcs.exception.NoSuchLCSPatchEntryException;
import com.liferay.osb.lcs.model.LCSPatchEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s patch entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSPatchEntryPersistenceImpl
 * @see LCSPatchEntryUtil
 * @generated
 */
@ProviderType
public interface LCSPatchEntryPersistence extends BasePersistence<LCSPatchEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSPatchEntryUtil} to access the l c s patch entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the l c s patch entry where patchId = &#63; or throws a {@link NoSuchLCSPatchEntryException} if it could not be found.
	*
	* @param patchId the patch ID
	* @return the matching l c s patch entry
	* @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry findByPatchId(java.lang.String patchId)
		throws NoSuchLCSPatchEntryException;

	/**
	* Returns the l c s patch entry where patchId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param patchId the patch ID
	* @return the matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry fetchByPatchId(java.lang.String patchId);

	/**
	* Returns the l c s patch entry where patchId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param patchId the patch ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry fetchByPatchId(java.lang.String patchId,
		boolean retrieveFromCache);

	/**
	* Removes the l c s patch entry where patchId = &#63; from the database.
	*
	* @param patchId the patch ID
	* @return the l c s patch entry that was removed
	*/
	public LCSPatchEntry removeByPatchId(java.lang.String patchId)
		throws NoSuchLCSPatchEntryException;

	/**
	* Returns the number of l c s patch entries where patchId = &#63;.
	*
	* @param patchId the patch ID
	* @return the number of matching l c s patch entries
	*/
	public int countByPatchId(java.lang.String patchId);

	/**
	* Returns all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @return the matching l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findByPTV_Product(
		int patchingToolVersion, java.lang.String product);

	/**
	* Returns a range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @return the range of matching l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findByPTV_Product(
		int patchingToolVersion, java.lang.String product, int start, int end);

	/**
	* Returns an ordered range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findByPTV_Product(
		int patchingToolVersion, java.lang.String product, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findByPTV_Product(
		int patchingToolVersion, java.lang.String product, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s patch entry
	* @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry findByPTV_Product_First(int patchingToolVersion,
		java.lang.String product,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException;

	/**
	* Returns the first l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry fetchByPTV_Product_First(int patchingToolVersion,
		java.lang.String product,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator);

	/**
	* Returns the last l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s patch entry
	* @throws NoSuchLCSPatchEntryException if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry findByPTV_Product_Last(int patchingToolVersion,
		java.lang.String product,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException;

	/**
	* Returns the last l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s patch entry, or <code>null</code> if a matching l c s patch entry could not be found
	*/
	public LCSPatchEntry fetchByPTV_Product_Last(int patchingToolVersion,
		java.lang.String product,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator);

	/**
	* Returns the l c s patch entries before and after the current l c s patch entry in the ordered set where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param lcsPatchEntryId the primary key of the current l c s patch entry
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s patch entry
	* @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	*/
	public LCSPatchEntry[] findByPTV_Product_PrevAndNext(long lcsPatchEntryId,
		int patchingToolVersion, java.lang.String product,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator)
		throws NoSuchLCSPatchEntryException;

	/**
	* Removes all the l c s patch entries where patchingToolVersion &le; &#63; and product = &#63; from the database.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	*/
	public void removeByPTV_Product(int patchingToolVersion,
		java.lang.String product);

	/**
	* Returns the number of l c s patch entries where patchingToolVersion &le; &#63; and product = &#63;.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the product
	* @return the number of matching l c s patch entries
	*/
	public int countByPTV_Product(int patchingToolVersion,
		java.lang.String product);

	/**
	* Caches the l c s patch entry in the entity cache if it is enabled.
	*
	* @param lcsPatchEntry the l c s patch entry
	*/
	public void cacheResult(LCSPatchEntry lcsPatchEntry);

	/**
	* Caches the l c s patch entries in the entity cache if it is enabled.
	*
	* @param lcsPatchEntries the l c s patch entries
	*/
	public void cacheResult(java.util.List<LCSPatchEntry> lcsPatchEntries);

	/**
	* Creates a new l c s patch entry with the primary key. Does not add the l c s patch entry to the database.
	*
	* @param lcsPatchEntryId the primary key for the new l c s patch entry
	* @return the new l c s patch entry
	*/
	public LCSPatchEntry create(long lcsPatchEntryId);

	/**
	* Removes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry that was removed
	* @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	*/
	public LCSPatchEntry remove(long lcsPatchEntryId)
		throws NoSuchLCSPatchEntryException;

	public LCSPatchEntry updateImpl(LCSPatchEntry lcsPatchEntry);

	/**
	* Returns the l c s patch entry with the primary key or throws a {@link NoSuchLCSPatchEntryException} if it could not be found.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry
	* @throws NoSuchLCSPatchEntryException if a l c s patch entry with the primary key could not be found
	*/
	public LCSPatchEntry findByPrimaryKey(long lcsPatchEntryId)
		throws NoSuchLCSPatchEntryException;

	/**
	* Returns the l c s patch entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry, or <code>null</code> if a l c s patch entry with the primary key could not be found
	*/
	public LCSPatchEntry fetchByPrimaryKey(long lcsPatchEntryId);

	@Override
	public java.util.Map<java.io.Serializable, LCSPatchEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s patch entries.
	*
	* @return the l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findAll();

	/**
	* Returns a range of all the l c s patch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @return the range of l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s patch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator);

	/**
	* Returns an ordered range of all the l c s patch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s patch entries
	*/
	public java.util.List<LCSPatchEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSPatchEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s patch entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s patch entries.
	*
	* @return the number of l c s patch entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}