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
 * Provides the local service utility for LCSClusterEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryLocalService
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSClusterEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s cluster entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was added
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry) {
		return getService().addLCSClusterEntry(lcsClusterEntry);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		int highPageLoadTime, java.lang.String location,
		int mediumPageLoadTime, java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntry(lcsProjectId, name, description,
			highPageLoadTime, location, mediumPageLoadTime, subscriptionType,
			type);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntry(lcsProjectId, name, description,
			location, subscriptionType, type);
	}

	/**
	* Creates a new l c s cluster entry with the primary key. Does not add the l c s cluster entry to the database.
	*
	* @param lcsClusterEntryId the primary key for the new l c s cluster entry
	* @return the new l c s cluster entry
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry createLCSClusterEntry(
		long lcsClusterEntryId) {
		return getService().createLCSClusterEntry(lcsClusterEntryId);
	}

	/**
	* Deletes the l c s cluster entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSClusterEntry(lcsClusterEntry);
	}

	/**
	* Deletes the l c s cluster entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry that was removed
	* @throws PortalException if a l c s cluster entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSClusterEntry(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry fetchLCSClusterEntry(
		long lcsClusterEntryId) {
		return getService().fetchLCSClusterEntry(lcsClusterEntryId);
	}

	/**
	* Returns the l c s cluster entry with the primary key.
	*
	* @param lcsClusterEntryId the primary key of the l c s cluster entry
	* @return the l c s cluster entry
	* @throws PortalException if a l c s cluster entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry getLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSClusterEntry(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateElastic(
		long lcsClusterEntryId, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateElastic(lcsClusterEntryId, elastic);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateHighPageLoadTime(
		long lcsClusterEntryId, int highPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateHighPageLoadTime(lcsClusterEntryId, highPageLoadTime);
	}

	/**
	* Updates the l c s cluster entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntry the l c s cluster entry
	* @return the l c s cluster entry that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		com.liferay.osb.lcs.model.LCSClusterEntry lcsClusterEntry) {
		return getService().updateLCSClusterEntry(lcsClusterEntry);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, java.lang.String subscriptionType,
		java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLCSClusterEntry(lcsClusterEntryId, name, description,
			subscriptionType, location);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateMediumPageLoadTime(
		long lcsClusterEntryId, int mediumPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMediumPageLoadTime(lcsClusterEntryId,
			mediumPageLoadTime);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateSubscriptionType(
		long lcsClusterEntryId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSubscriptionType(lcsClusterEntryId, subscriptionType);
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

	/**
	* Returns the number of l c s cluster entries.
	*
	* @return the number of l c s cluster entries
	*/
	public static int getLCSClusterEntriesCount() {
		return getService().getLCSClusterEntriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType) {
		return getService()
				   .getArchivedLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	/**
	* Returns a range of all the l c s cluster entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entries
	* @param end the upper bound of the range of l c s cluster entries (not inclusive)
	* @return the range of l c s cluster entries
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSClusterEntries(
		int start, int end) {
		return getService().getLCSClusterEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId) {
		return getService().getLCSProjectLCSClusterEntries(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getLCSProjectLCSClusterEntries(lcsProjectId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType) {
		return getService()
				   .getLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		long userId) {
		return getService().getUserLCSClusterEntries(userId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		long userId, long lcsProjectId) {
		return getService().getUserLCSClusterEntries(userId, lcsProjectId);
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

	public static long[] getLCSProjectLCSClusterEntryIds(long lcsProjectId) {
		return getService().getLCSProjectLCSClusterEntryIds(lcsProjectId);
	}

	public static long[] getUserLCSClusterEntryIds(long userId,
		long lcsProjectId) {
		return getService().getUserLCSClusterEntryIds(userId, lcsProjectId);
	}

	public static void deleteLCSProjectClusters(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSProjectClusters(lcsProjectId);
	}

	public static LCSClusterEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterEntryLocalService, LCSClusterEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterEntryLocalService.class);
}