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
 * Provides the local service utility for LCSInvitation. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSInvitationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSInvitationLocalService
 * @see com.liferay.osb.lcs.service.base.LCSInvitationLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSInvitationLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSInvitationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSInvitationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasUserLCSInvitation(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasUserLCSInvitation(userId);
	}

	/**
	* Adds the l c s invitation to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was added
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation addLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return getService().addLCSInvitation(lcsInvitation);
	}

	public static com.liferay.osb.lcs.model.LCSInvitation addLCSInvitation(
		long userId, long lcsProjectId, java.lang.String emailAddress,
		long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSInvitation(userId, lcsProjectId, emailAddress,
			lcsClusterEntryId, role);
	}

	/**
	* Creates a new l c s invitation with the primary key. Does not add the l c s invitation to the database.
	*
	* @param lcsInvitationId the primary key for the new l c s invitation
	* @return the new l c s invitation
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation createLCSInvitation(
		long lcsInvitationId) {
		return getService().createLCSInvitation(lcsInvitationId);
	}

	/**
	* Deletes the l c s invitation from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return getService().deleteLCSInvitation(lcsInvitation);
	}

	/**
	* Deletes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation that was removed
	* @throws PortalException if a l c s invitation with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSInvitation(lcsInvitationId);
	}

	public static com.liferay.osb.lcs.model.LCSInvitation fetchLCSInvitation(
		long lcsInvitationId) {
		return getService().fetchLCSInvitation(lcsInvitationId);
	}

	/**
	* Returns the l c s invitation with the primary key.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation
	* @throws PortalException if a l c s invitation with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation getLCSInvitation(
		long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSInvitation(lcsInvitationId);
	}

	public static com.liferay.osb.lcs.model.LCSInvitation getLCSProjectLCSInvitation(
		long lcsProjectId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSProjectLCSInvitation(lcsProjectId, emailAddress);
	}

	/**
	* Updates the l c s invitation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSInvitation updateLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return getService().updateLCSInvitation(lcsInvitation);
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
	* Returns the number of l c s invitations.
	*
	* @return the number of l c s invitations
	*/
	public static int getLCSInvitationsCount() {
		return getService().getLCSInvitationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s invitations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @return the range of l c s invitations
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getLCSInvitations(
		int start, int end) {
		return getService().getLCSInvitations(start, end);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getLCSProjectLCSInvitations(
		long lcsProjectId) {
		return getService().getLCSProjectLCSInvitations(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getUserLCSInvitations(
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSInvitations(emailAddress);
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

	public static LCSInvitationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSInvitationLocalService, LCSInvitationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSInvitationLocalService.class);
}