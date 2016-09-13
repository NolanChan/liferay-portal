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

import com.liferay.osb.lcs.exception.NoSuchLCSMetadataException;
import com.liferay.osb.lcs.model.LCSMetadata;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the l c s metadata service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSMetadataPersistenceImpl
 * @see LCSMetadataUtil
 * @generated
 */
@ProviderType
public interface LCSMetadataPersistence extends BasePersistence<LCSMetadata> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMetadataUtil} to access the l c s metadata persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @return the matching l c s metadatas
	*/
	public java.util.List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition);

	/**
	* Returns a range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @return the range of matching l c s metadatas
	*/
	public java.util.List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end);

	/**
	* Returns an ordered range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching l c s metadatas
	*/
	public java.util.List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator);

	/**
	* Returns an ordered range of all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching l c s metadatas
	*/
	public java.util.List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public LCSMetadata findByBN_PE_First(int buildNumber,
		java.lang.String portalEdition,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException;

	/**
	* Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public LCSMetadata fetchByBN_PE_First(int buildNumber,
		java.lang.String portalEdition,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator);

	/**
	* Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public LCSMetadata findByBN_PE_Last(int buildNumber,
		java.lang.String portalEdition,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException;

	/**
	* Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public LCSMetadata fetchByBN_PE_Last(int buildNumber,
		java.lang.String portalEdition,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator);

	/**
	* Returns the l c s metadatas before and after the current l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param lcsMetadataId the primary key of the current l c s metadata
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next l c s metadata
	* @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	*/
	public LCSMetadata[] findByBN_PE_PrevAndNext(long lcsMetadataId,
		int buildNumber, java.lang.String portalEdition,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator)
		throws NoSuchLCSMetadataException;

	/**
	* Removes all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	*/
	public void removeByBN_PE(int buildNumber, java.lang.String portalEdition);

	/**
	* Returns the number of l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @return the number of matching l c s metadatas
	*/
	public int countByBN_PE(int buildNumber, java.lang.String portalEdition);

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public LCSMetadata findByBN_GT_PE(int buildNumber, java.lang.String gitTag,
		java.lang.String portalEdition) throws NoSuchLCSMetadataException;

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public LCSMetadata fetchByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition);

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public LCSMetadata fetchByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition,
		boolean retrieveFromCache);

	/**
	* Removes the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the l c s metadata that was removed
	*/
	public LCSMetadata removeByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition)
		throws NoSuchLCSMetadataException;

	/**
	* Returns the number of l c s metadatas where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the number of matching l c s metadatas
	*/
	public int countByBN_GT_PE(int buildNumber, java.lang.String gitTag,
		java.lang.String portalEdition);

	/**
	* Caches the l c s metadata in the entity cache if it is enabled.
	*
	* @param lcsMetadata the l c s metadata
	*/
	public void cacheResult(LCSMetadata lcsMetadata);

	/**
	* Caches the l c s metadatas in the entity cache if it is enabled.
	*
	* @param lcsMetadatas the l c s metadatas
	*/
	public void cacheResult(java.util.List<LCSMetadata> lcsMetadatas);

	/**
	* Creates a new l c s metadata with the primary key. Does not add the l c s metadata to the database.
	*
	* @param lcsMetadataId the primary key for the new l c s metadata
	* @return the new l c s metadata
	*/
	public LCSMetadata create(long lcsMetadataId);

	/**
	* Removes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata that was removed
	* @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	*/
	public LCSMetadata remove(long lcsMetadataId)
		throws NoSuchLCSMetadataException;

	public LCSMetadata updateImpl(LCSMetadata lcsMetadata);

	/**
	* Returns the l c s metadata with the primary key or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata
	* @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	*/
	public LCSMetadata findByPrimaryKey(long lcsMetadataId)
		throws NoSuchLCSMetadataException;

	/**
	* Returns the l c s metadata with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata, or <code>null</code> if a l c s metadata with the primary key could not be found
	*/
	public LCSMetadata fetchByPrimaryKey(long lcsMetadataId);

	@Override
	public java.util.Map<java.io.Serializable, LCSMetadata> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the l c s metadatas.
	*
	* @return the l c s metadatas
	*/
	public java.util.List<LCSMetadata> findAll();

	/**
	* Returns a range of all the l c s metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @return the range of l c s metadatas
	*/
	public java.util.List<LCSMetadata> findAll(int start, int end);

	/**
	* Returns an ordered range of all the l c s metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of l c s metadatas
	*/
	public java.util.List<LCSMetadata> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator);

	/**
	* Returns an ordered range of all the l c s metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of l c s metadatas
	*/
	public java.util.List<LCSMetadata> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LCSMetadata> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the l c s metadatas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of l c s metadatas.
	*
	* @return the number of l c s metadatas
	*/
	public int countAll();
}