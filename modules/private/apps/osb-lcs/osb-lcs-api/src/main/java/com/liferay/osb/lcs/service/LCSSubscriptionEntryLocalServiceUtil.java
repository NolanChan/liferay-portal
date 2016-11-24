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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LCSSubscriptionEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryLocalService
 * @see com.liferay.osb.lcs.service.base.LCSSubscriptionEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasLCSProjectElasticLCSSubscriptionEntry(
		long lcsProjectId) {
		return getService()
				   .hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	public static boolean hasLCSProjectLCSSubscriptionEntry(long lcsProjectId) {
		return getService().hasLCSProjectLCSSubscriptionEntry(lcsProjectId);
	}

	/**
	* Adds the l c s subscription entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was added
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry addLCSSubscriptionEntry(
		com.liferay.osb.lcs.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getService().addLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Creates a new l c s subscription entry with the primary key. Does not add the l c s subscription entry to the database.
	*
	* @param lcsSubscriptionEntryId the primary key for the new l c s subscription entry
	* @return the new l c s subscription entry
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry createLCSSubscriptionEntry(
		long lcsSubscriptionEntryId) {
		return getService().createLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Deletes the l c s subscription entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		com.liferay.osb.lcs.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getService().deleteLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	/**
	* Deletes the l c s subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry that was removed
	* @throws PortalException if a l c s subscription entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry deleteLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry fetchLCSProjectLCSSubscriptionEntry(
		long lcsProjectId, int processorCoresTotal,
		com.liferay.lcs.subscription.SubscriptionType subscriptionType) {
		return getService()
				   .fetchLCSProjectLCSSubscriptionEntry(lcsProjectId,
			processorCoresTotal, subscriptionType);
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry fetchLCSSubscriptionEntry(
		long lcsSubscriptionEntryId) {
		return getService().fetchLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Returns the l c s subscription entry with the primary key.
	*
	* @param lcsSubscriptionEntryId the primary key of the l c s subscription entry
	* @return the l c s subscription entry
	* @throws PortalException if a l c s subscription entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry getLCSSubscriptionEntry(
		long lcsSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSSubscriptionEntry(lcsSubscriptionEntryId);
	}

	/**
	* Updates the l c s subscription entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsSubscriptionEntry the l c s subscription entry
	* @return the l c s subscription entry that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry updateLCSSubscriptionEntry(
		com.liferay.osb.lcs.model.LCSSubscriptionEntry lcsSubscriptionEntry) {
		return getService().updateLCSSubscriptionEntry(lcsSubscriptionEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getActiveLCSSubscriptionEntriesCount() {
		return getService().getActiveLCSSubscriptionEntriesCount();
	}

	/**
	* Returns the number of l c s subscription entries.
	*
	* @return the number of l c s subscription entries
	*/
	public static int getLCSSubscriptionEntriesCount() {
		return getService().getLCSSubscriptionEntriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getActiveLCSSubscriptionEntries(
		int start, int end) {
		return getService().getActiveLCSSubscriptionEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId) {
		return getService().getLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean active) {
		return getService()
				   .getLCSProjectLCSSubscriptionEntries(lcsProjectId, active);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getLCSProjectLCSSubscriptionEntries(lcsProjectId, type,
			orderByComparator);
	}

	/**
	* Returns a range of all the l c s subscription entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSSubscriptionEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s subscription entries
	* @param end the upper bound of the range of l c s subscription entries (not inclusive)
	* @return the range of l c s subscription entries
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSSubscriptionEntries(
		int start, int end) {
		return getService().getLCSSubscriptionEntries(start, end);
	}

	public static java.util.List<java.lang.Long> getLCSSubscriptionEntryIds(
		long lcsProjectId) {
		return getService().getLCSSubscriptionEntryIds(lcsProjectId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void addLCSSubscriptionEntries(long lcsProjectId,
		java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addLCSSubscriptionEntries(lcsProjectId, lcsSubscriptionEntriesJSON);
	}

	public static void decrementServerUsed(long lcsProjectId,
		java.lang.String licenseType, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.decrementServerUsed(lcsProjectId, licenseType, processorCoresTotal);
	}

	public static void deleteLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	public static void incrementServerUsed(long lcsProjectId,
		java.lang.String licenseType, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.incrementServerUsed(lcsProjectId, licenseType, processorCoresTotal);
	}

	public static void refreshLCSProjectLCSSubscriptionEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().refreshLCSProjectLCSSubscriptionEntries();
	}

	public static void refreshLCSProjectLCSSubscriptionEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	public static void reorganizeLCSSubsriptionsServersUsed(long lcsProjectId) {
		getService().reorganizeLCSSubsriptionsServersUsed(lcsProjectId);
	}

	public static LCSSubscriptionEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSSubscriptionEntryLocalService, LCSSubscriptionEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSSubscriptionEntryLocalService.class);
}