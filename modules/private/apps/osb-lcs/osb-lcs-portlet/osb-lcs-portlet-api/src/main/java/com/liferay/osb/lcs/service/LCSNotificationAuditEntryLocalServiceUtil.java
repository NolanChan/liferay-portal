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
 * Provides the local service utility for LCSNotificationAuditEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSNotificationAuditEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntryLocalService
 * @see com.liferay.osb.lcs.service.base.LCSNotificationAuditEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSNotificationAuditEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSNotificationAuditEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s notification audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was added
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry addLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return getService()
				   .addLCSNotificationAuditEntry(lcsNotificationAuditEntry);
	}

	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry addLCSNotificationAuditEntry(
		long userId, long lcsClusterNodeId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSNotificationAuditEntry(userId, lcsClusterNodeId, type);
	}

	/**
	* Creates a new l c s notification audit entry with the primary key. Does not add the l c s notification audit entry to the database.
	*
	* @param lcsNotificationAuditEntryId the primary key for the new l c s notification audit entry
	* @return the new l c s notification audit entry
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry createLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId) {
		return getService()
				   .createLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Deletes the l c s notification audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry deleteLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return getService()
				   .deleteLCSNotificationAuditEntry(lcsNotificationAuditEntry);
	}

	/**
	* Deletes the l c s notification audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry that was removed
	* @throws PortalException if a l c s notification audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry deleteLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry fetchLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId) {
		return getService()
				   .fetchLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Returns the l c s notification audit entry with the primary key.
	*
	* @param lcsNotificationAuditEntryId the primary key of the l c s notification audit entry
	* @return the l c s notification audit entry
	* @throws PortalException if a l c s notification audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry getLCSNotificationAuditEntry(
		long lcsNotificationAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSNotificationAuditEntry(lcsNotificationAuditEntryId);
	}

	/**
	* Updates the l c s notification audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsNotificationAuditEntry the l c s notification audit entry
	* @return the l c s notification audit entry that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSNotificationAuditEntry updateLCSNotificationAuditEntry(
		com.liferay.osb.lcs.model.LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return getService()
				   .updateLCSNotificationAuditEntry(lcsNotificationAuditEntry);
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
	* Returns the number of l c s notification audit entries.
	*
	* @return the number of l c s notification audit entries
	*/
	public static int getLCSNotificationAuditEntriesCount() {
		return getService().getLCSNotificationAuditEntriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the l c s notification audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSNotificationAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s notification audit entries
	* @param end the upper bound of the range of l c s notification audit entries (not inclusive)
	* @return the range of l c s notification audit entries
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
		int start, int end) {
		return getService().getLCSNotificationAuditEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
		int start, int end, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSNotificationAuditEntries(start, end, details);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
		long corpProjectId, long lcsClusterEntryId, long lcsClusterNodeId,
		boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSNotificationAuditEntries(corpProjectId,
			lcsClusterEntryId, lcsClusterNodeId, details);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getLCSNotificationAuditEntries(
		long lcsProjectId, long lcsClusterEntryId, long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSNotificationAuditEntries(lcsProjectId,
			lcsClusterEntryId, lcsClusterNodeId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSNotificationAuditEntry> getUserLCSNotificationAuditEntries(
		long userId, long lcsClusterNodeId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getUserLCSNotificationAuditEntries(userId,
			lcsClusterNodeId, type);
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

	public static LCSNotificationAuditEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSNotificationAuditEntryLocalService, LCSNotificationAuditEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSNotificationAuditEntryLocalService.class);
}