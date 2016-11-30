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
 * Provides the local service utility for LCSClusterEntryToken. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenLocalService
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryTokenLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s cluster entry token to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was added
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return getService().addLCSClusterEntryToken(lcsClusterEntryToken);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		long userId, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntryToken(userId, lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	/**
	* Creates a new l c s cluster entry token with the primary key. Does not add the l c s cluster entry token to the database.
	*
	* @param lcsClusterEntryTokenId the primary key for the new l c s cluster entry token
	* @return the new l c s cluster entry token
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken createLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {
		return getService().createLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	/**
	* Deletes the l c s cluster entry token from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return getService().deleteLCSClusterEntryToken(lcsClusterEntryToken);
	}

	/**
	* Deletes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId) {
		return getService()
				   .fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId) {
		return getService().fetchLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	/**
	* Returns the l c s cluster entry token with the primary key.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken getLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken regenerateLCSClusterEntryToken(
		long userId, long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .regenerateLCSClusterEntryToken(userId, lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	/**
	* Updates the l c s cluster entry token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSClusterEntryToken updateLCSClusterEntryToken(
		com.liferay.osb.lcs.model.LCSClusterEntryToken lcsClusterEntryToken) {
		return getService().updateLCSClusterEntryToken(lcsClusterEntryToken);
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
	* Returns the number of l c s cluster entry tokens.
	*
	* @return the number of l c s cluster entry tokens
	*/
	public static int getLCSClusterEntryTokensCount() {
		return getService().getLCSClusterEntryTokensCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s cluster entry tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster entry tokens
	* @param end the upper bound of the range of l c s cluster entry tokens (not inclusive)
	* @return the range of l c s cluster entry tokens
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntryToken> getLCSClusterEntryTokens(
		int start, int end) {
		return getService().getLCSClusterEntryTokens(start, end);
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

	public static LCSClusterEntryTokenLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterEntryTokenLocalService, LCSClusterEntryTokenLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterEntryTokenLocalService.class);
}