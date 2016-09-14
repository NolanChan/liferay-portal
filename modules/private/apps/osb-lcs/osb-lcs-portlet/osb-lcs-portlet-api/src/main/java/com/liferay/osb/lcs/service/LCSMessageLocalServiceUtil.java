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
 * Provides the local service utility for LCSMessage. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMessageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSMessageLocalService
 * @see com.liferay.osb.lcs.service.base.LCSMessageLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMessageLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSMessageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.lcs.model.LCSMessage addLCSClusterEntryLCSMessage(
		long lcsClusterEntryId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type,
		boolean addUserLCSMessages)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntryLCSMessage(lcsClusterEntryId,
			sourceMessageId, sourceSystemName, content, endDate, global,
			severityLevel, type, addUserLCSMessages);
	}

	public static com.liferay.osb.lcs.model.LCSMessage addLCSClusterNodeLCSMessage(
		long lcsClusterNodeId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterNodeLCSMessage(lcsClusterNodeId,
			sourceMessageId, sourceSystemName, content, endDate, global,
			severityLevel, type);
	}

	/**
	* Adds the l c s message to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was added
	*/
	public static com.liferay.osb.lcs.model.LCSMessage addLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return getService().addLCSMessage(lcsMessage);
	}

	public static com.liferay.osb.lcs.model.LCSMessage addLCSMessage(
		long sourceMessageId, java.lang.String sourceSystemName,
		long classNameId, long classPK, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type) {
		return getService()
				   .addLCSMessage(sourceMessageId, sourceSystemName,
			classNameId, classPK, content, endDate, global, severityLevel, type);
	}

	public static com.liferay.osb.lcs.model.LCSMessage addLCSProjectLCSMessage(
		long lcsProjectId, long sourceMessageId,
		java.lang.String sourceSystemName, java.lang.String content,
		java.util.Date endDate, boolean global, int severityLevel, int type,
		boolean adminsOnly, boolean generateUserLCSMessages)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSProjectLCSMessage(lcsProjectId, sourceMessageId,
			sourceSystemName, content, endDate, global, severityLevel, type,
			adminsOnly, generateUserLCSMessages);
	}

	/**
	* Creates a new l c s message with the primary key. Does not add the l c s message to the database.
	*
	* @param lcsMessageId the primary key for the new l c s message
	* @return the new l c s message
	*/
	public static com.liferay.osb.lcs.model.LCSMessage createLCSMessage(
		long lcsMessageId) {
		return getService().createLCSMessage(lcsMessageId);
	}

	/**
	* Deletes the l c s message from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSMessage deleteLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return getService().deleteLCSMessage(lcsMessage);
	}

	/**
	* Deletes the l c s message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message that was removed
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSMessage deleteLCSMessage(
		long lcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSMessage(lcsMessageId);
	}

	public static com.liferay.osb.lcs.model.LCSMessage fetchLCSMessage(
		long lcsMessageId) {
		return getService().fetchLCSMessage(lcsMessageId);
	}

	public static com.liferay.osb.lcs.model.LCSMessage fetchLastLCSProjectLCSMessage(
		long lcsProjectId, int type) {
		return getService().fetchLastLCSProjectLCSMessage(lcsProjectId, type);
	}

	/**
	* Returns the l c s message with the primary key.
	*
	* @param lcsMessageId the primary key of the l c s message
	* @return the l c s message
	* @throws PortalException if a l c s message with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSMessage getLCSMessage(
		long lcsMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSMessage(lcsMessageId);
	}

	/**
	* Updates the l c s message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsMessage the l c s message
	* @return the l c s message that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSMessage updateLCSMessage(
		com.liferay.osb.lcs.model.LCSMessage lcsMessage) {
		return getService().updateLCSMessage(lcsMessage);
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
	* Returns the number of l c s messages.
	*
	* @return the number of l c s messages
	*/
	public static int getLCSMessagesCount() {
		return getService().getLCSMessagesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s messages
	* @param end the upper bound of the range of l c s messages (not inclusive)
	* @return the range of l c s messages
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		int start, int end) {
		return getService().getLCSMessages(start, end);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSMessage> getLCSMessages(
		long userId, java.util.Date modifyDateGT, java.util.Date modifyDateLT)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSMessages(userId, modifyDateGT, modifyDateLT);
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

	public static void deleteLCSClusterEntryLCSMessages(long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSClusterEntryLCSMessages(lcsClusterEntryId);
	}

	public static void deleteLCSClusterNodeLCSMessages(long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSClusterNodeLCSMessages(lcsClusterNodeId);
	}

	public static void deleteLCSProjectLCSMessage(long lcsProjectId,
		long sourceMessageId, java.lang.String sourceSystemName,
		long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteLCSProjectLCSMessage(lcsProjectId, sourceMessageId,
			sourceSystemName, classNameId);
	}

	public static void deleteLCSProjectLCSMessages(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSProjectLCSMessages(lcsProjectId);
	}

	public static LCSMessageLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMessageLocalService, LCSMessageLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMessageLocalService.class);
}