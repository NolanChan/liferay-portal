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

package com.liferay.portal.reports.engine.console.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.reports.engine.console.model.Entry;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the entry service. This utility wraps {@link com.liferay.portal.reports.engine.console.service.persistence.impl.EntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryPersistence
 * @see com.liferay.portal.reports.engine.console.service.persistence.impl.EntryPersistenceImpl
 * @generated
 */
@ProviderType
public class EntryUtil {
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
	public static void clearCache(Entry entry) {
		getPersistence().clearCache(entry);
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
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Entry update(Entry entry) {
		return getPersistence().update(entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Entry update(Entry entry, ServiceContext serviceContext) {
		return getPersistence().update(entry, serviceContext);
	}

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public static void cacheResult(Entry entry) {
		getPersistence().cacheResult(entry);
	}

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public static void cacheResult(List<Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry remove(long entryId)
		throws com.liferay.portal.reports.engine.console.exception.NoSuchEntryException {
		return getPersistence().remove(entryId);
	}

	public static Entry updateImpl(Entry entry) {
		return getPersistence().updateImpl(entry);
	}

	/**
	* Returns the entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry findByPrimaryKey(long entryId)
		throws com.liferay.portal.reports.engine.console.exception.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	*/
	public static Entry fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.Map<java.io.Serializable, Entry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entries.
	*
	* @return the entries
	*/
	public static List<Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	public static List<Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	*/
	public static List<Entry> findAll(int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of entries
	*/
	public static List<Entry> findAll(int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntryPersistence, EntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntryPersistence.class);
}