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

import com.liferay.osb.lcs.model.LCSMetadata;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the l c s metadata service. This utility wraps {@link com.liferay.osb.lcs.service.persistence.impl.LCSMetadataPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMetadataPersistence
 * @see com.liferay.osb.lcs.service.persistence.impl.LCSMetadataPersistenceImpl
 * @generated
 */
@ProviderType
public class LCSMetadataUtil {
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
	public static void clearCache(LCSMetadata lcsMetadata) {
		getPersistence().clearCache(lcsMetadata);
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
	public static List<LCSMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LCSMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LCSMetadata> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LCSMetadata update(LCSMetadata lcsMetadata) {
		return getPersistence().update(lcsMetadata);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LCSMetadata update(LCSMetadata lcsMetadata,
		ServiceContext serviceContext) {
		return getPersistence().update(lcsMetadata, serviceContext);
	}

	/**
	* Returns all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @return the matching l c s metadatas
	*/
	public static List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition) {
		return getPersistence().findByBN_PE(buildNumber, portalEdition);
	}

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
	public static List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end) {
		return getPersistence()
				   .findByBN_PE(buildNumber, portalEdition, start, end);
	}

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
	public static List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return getPersistence()
				   .findByBN_PE(buildNumber, portalEdition, start, end,
			orderByComparator);
	}

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
	public static List<LCSMetadata> findByBN_PE(int buildNumber,
		java.lang.String portalEdition, int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBN_PE(buildNumber, portalEdition, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public static LCSMetadata findByBN_PE_First(int buildNumber,
		java.lang.String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence()
				   .findByBN_PE_First(buildNumber, portalEdition,
			orderByComparator);
	}

	/**
	* Returns the first l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public static LCSMetadata fetchByBN_PE_First(int buildNumber,
		java.lang.String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return getPersistence()
				   .fetchByBN_PE_First(buildNumber, portalEdition,
			orderByComparator);
	}

	/**
	* Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public static LCSMetadata findByBN_PE_Last(int buildNumber,
		java.lang.String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence()
				   .findByBN_PE_Last(buildNumber, portalEdition,
			orderByComparator);
	}

	/**
	* Returns the last l c s metadata in the ordered set where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public static LCSMetadata fetchByBN_PE_Last(int buildNumber,
		java.lang.String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return getPersistence()
				   .fetchByBN_PE_Last(buildNumber, portalEdition,
			orderByComparator);
	}

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
	public static LCSMetadata[] findByBN_PE_PrevAndNext(long lcsMetadataId,
		int buildNumber, java.lang.String portalEdition,
		OrderByComparator<LCSMetadata> orderByComparator)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence()
				   .findByBN_PE_PrevAndNext(lcsMetadataId, buildNumber,
			portalEdition, orderByComparator);
	}

	/**
	* Removes all the l c s metadatas where buildNumber = &#63; and portalEdition = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	*/
	public static void removeByBN_PE(int buildNumber,
		java.lang.String portalEdition) {
		getPersistence().removeByBN_PE(buildNumber, portalEdition);
	}

	/**
	* Returns the number of l c s metadatas where buildNumber = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param portalEdition the portal edition
	* @return the number of matching l c s metadatas
	*/
	public static int countByBN_PE(int buildNumber,
		java.lang.String portalEdition) {
		return getPersistence().countByBN_PE(buildNumber, portalEdition);
	}

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the matching l c s metadata
	* @throws NoSuchLCSMetadataException if a matching l c s metadata could not be found
	*/
	public static LCSMetadata findByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence()
				   .findByBN_GT_PE(buildNumber, gitTag, portalEdition);
	}

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public static LCSMetadata fetchByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition) {
		return getPersistence()
				   .fetchByBN_GT_PE(buildNumber, gitTag, portalEdition);
	}

	/**
	* Returns the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching l c s metadata, or <code>null</code> if a matching l c s metadata could not be found
	*/
	public static LCSMetadata fetchByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByBN_GT_PE(buildNumber, gitTag, portalEdition,
			retrieveFromCache);
	}

	/**
	* Removes the l c s metadata where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63; from the database.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the l c s metadata that was removed
	*/
	public static LCSMetadata removeByBN_GT_PE(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence()
				   .removeByBN_GT_PE(buildNumber, gitTag, portalEdition);
	}

	/**
	* Returns the number of l c s metadatas where buildNumber = &#63; and gitTag = &#63; and portalEdition = &#63;.
	*
	* @param buildNumber the build number
	* @param gitTag the git tag
	* @param portalEdition the portal edition
	* @return the number of matching l c s metadatas
	*/
	public static int countByBN_GT_PE(int buildNumber, java.lang.String gitTag,
		java.lang.String portalEdition) {
		return getPersistence()
				   .countByBN_GT_PE(buildNumber, gitTag, portalEdition);
	}

	/**
	* Caches the l c s metadata in the entity cache if it is enabled.
	*
	* @param lcsMetadata the l c s metadata
	*/
	public static void cacheResult(LCSMetadata lcsMetadata) {
		getPersistence().cacheResult(lcsMetadata);
	}

	/**
	* Caches the l c s metadatas in the entity cache if it is enabled.
	*
	* @param lcsMetadatas the l c s metadatas
	*/
	public static void cacheResult(List<LCSMetadata> lcsMetadatas) {
		getPersistence().cacheResult(lcsMetadatas);
	}

	/**
	* Creates a new l c s metadata with the primary key. Does not add the l c s metadata to the database.
	*
	* @param lcsMetadataId the primary key for the new l c s metadata
	* @return the new l c s metadata
	*/
	public static LCSMetadata create(long lcsMetadataId) {
		return getPersistence().create(lcsMetadataId);
	}

	/**
	* Removes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata that was removed
	* @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	*/
	public static LCSMetadata remove(long lcsMetadataId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence().remove(lcsMetadataId);
	}

	public static LCSMetadata updateImpl(LCSMetadata lcsMetadata) {
		return getPersistence().updateImpl(lcsMetadata);
	}

	/**
	* Returns the l c s metadata with the primary key or throws a {@link NoSuchLCSMetadataException} if it could not be found.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata
	* @throws NoSuchLCSMetadataException if a l c s metadata with the primary key could not be found
	*/
	public static LCSMetadata findByPrimaryKey(long lcsMetadataId)
		throws com.liferay.osb.lcs.exception.NoSuchLCSMetadataException {
		return getPersistence().findByPrimaryKey(lcsMetadataId);
	}

	/**
	* Returns the l c s metadata with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata, or <code>null</code> if a l c s metadata with the primary key could not be found
	*/
	public static LCSMetadata fetchByPrimaryKey(long lcsMetadataId) {
		return getPersistence().fetchByPrimaryKey(lcsMetadataId);
	}

	public static java.util.Map<java.io.Serializable, LCSMetadata> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the l c s metadatas.
	*
	* @return the l c s metadatas
	*/
	public static List<LCSMetadata> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LCSMetadata> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LCSMetadata> findAll(int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LCSMetadata> findAll(int start, int end,
		OrderByComparator<LCSMetadata> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the l c s metadatas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of l c s metadatas.
	*
	* @return the number of l c s metadatas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LCSMetadataPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMetadataPersistence, LCSMetadataPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LCSMetadataPersistence.class);
}