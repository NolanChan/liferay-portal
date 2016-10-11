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
 * Provides the local service utility for LCSMetadata. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMetadataLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSMetadataLocalService
 * @see com.liferay.osb.lcs.service.base.LCSMetadataLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMetadataLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSMetadataLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMetadataLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s metadata to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was added
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata addLCSMetadata(
		com.liferay.osb.lcs.model.LCSMetadata lcsMetadata) {
		return getService().addLCSMetadata(lcsMetadata);
	}

	/**
	* Adds new LCS metadata.
	*
	* @param buildNumber the portal instance's build number
	* @param gitTag the Git tag of the portal instance's source code
	* @param portalEdition the portal instance's edition
	* @param supportedLCSPortlet the latest supported LCS portlet's build
	number
	* @param supportedPatchingTool the latest supported patching tool's build
	number
	* @return the LCS metadata
	* @since LCS 1.1
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata addLCSMetadata(
		int buildNumber, java.lang.String gitTag,
		java.lang.String portalEdition, int supportedLCSPortlet,
		int supportedPatchingTool) {
		return getService()
				   .addLCSMetadata(buildNumber, gitTag, portalEdition,
			supportedLCSPortlet, supportedPatchingTool);
	}

	/**
	* Creates a new l c s metadata with the primary key. Does not add the l c s metadata to the database.
	*
	* @param lcsMetadataId the primary key for the new l c s metadata
	* @return the new l c s metadata
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata createLCSMetadata(
		long lcsMetadataId) {
		return getService().createLCSMetadata(lcsMetadataId);
	}

	/**
	* Deletes the l c s metadata from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata deleteLCSMetadata(
		com.liferay.osb.lcs.model.LCSMetadata lcsMetadata) {
		return getService().deleteLCSMetadata(lcsMetadata);
	}

	/**
	* Deletes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata that was removed
	* @throws PortalException if a l c s metadata with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata deleteLCSMetadata(
		long lcsMetadataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSMetadata(lcsMetadataId);
	}

	/**
	* Returns the LCS metadata matching the build number, Git tag, and portal
	* edition.
	*
	* @param buildNumber the portal instance's build number
	* @param gitTag the Git tag of the portal instance's source code
	* @param portalEdition the portal instance's edition
	* @return the matching LCS metadata
	* @since LCS 1.1
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata fetchLCSMetadata(
		int buildNumber, java.lang.String gitTag, java.lang.String portalEdition) {
		return getService().fetchLCSMetadata(buildNumber, gitTag, portalEdition);
	}

	public static com.liferay.osb.lcs.model.LCSMetadata fetchLCSMetadata(
		long lcsMetadataId) {
		return getService().fetchLCSMetadata(lcsMetadataId);
	}

	/**
	* Returns the l c s metadata with the primary key.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata
	* @throws PortalException if a l c s metadata with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata getLCSMetadata(
		long lcsMetadataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSMetadata(lcsMetadataId);
	}

	/**
	* Updates the availability index bit mask for the LCS metadata that matches
	* the LCS metadata ID.
	*
	* <p>
	* If the availability index {@link
	* com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex} bit is turned on,
	* a particular service is available either within the LCS metadata or
	* {@link com.liferay.osb.lcs.nosql.model.LCSMetadataDetails}. Use {@link
	* com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex#merge(long)} to
	* encode the bit mask, and {@link
	* com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex#isAvailable(long)}
	* to decode it.
	* </p>
	*
	* @param lcsMetadataId the primary key of the LCS metadata
	* @param availabilityIndex the bit mask
	* @return the updated LCS metadata
	* @since LCS 1.1
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata updateAvailabilityIndex(
		long lcsMetadataId, long availabilityIndex) {
		return getService()
				   .updateAvailabilityIndex(lcsMetadataId, availabilityIndex);
	}

	/**
	* Updates the l c s metadata in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata updateLCSMetadata(
		com.liferay.osb.lcs.model.LCSMetadata lcsMetadata) {
		return getService().updateLCSMetadata(lcsMetadata);
	}

	/**
	* Updates the supported LCS portlet build number in the LCS metadata
	* matching the primary key.
	*
	* @param lcsMetadataId the primary key of the LCS metadata
	* @param supportedLCSPortlet the supported LCS portlet's build number
	* @return the updated LCS metadata
	* @since LCS 1.1
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata updateSupportedLCSPortlet(
		long lcsMetadataId, int supportedLCSPortlet) {
		return getService()
				   .updateSupportedLCSPortlet(lcsMetadataId, supportedLCSPortlet);
	}

	/**
	* Updates the latest supported portal patching tool's build number in the
	* LCS metadata matching the primary key.
	*
	* @param lcsMetadataId the primary key of the LCS metadata
	* @param supportedPatchingToolVersion the latest supported patching tool's
	build number
	* @return the updated LCS metadata
	* @since LCS 1.1
	*/
	public static com.liferay.osb.lcs.model.LCSMetadata updateSupportedPatchingTool(
		long lcsMetadataId, int supportedPatchingToolVersion) {
		return getService()
				   .updateSupportedPatchingTool(lcsMetadataId,
			supportedPatchingToolVersion);
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
	* Returns the number of l c s metadatas.
	*
	* @return the number of l c s metadatas
	*/
	public static int getLCSMetadatasCount() {
		return getService().getLCSMetadatasCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns all LCS metadata that match the build number and portal edition.
	*
	* @param buildNumber the portal instance's build number
	* @param portalEdition the portal instance's edition
	* @return the matching LCS metadata
	* @since LCS 1.1
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSMetadata> getLCSMetadatas(
		int buildNumber, java.lang.String portalEdition) {
		return getService().getLCSMetadatas(buildNumber, portalEdition);
	}

	/**
	* Returns a range of all the l c s metadatas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s metadatas
	* @param end the upper bound of the range of l c s metadatas (not inclusive)
	* @return the range of l c s metadatas
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSMetadata> getLCSMetadatas(
		int start, int end) {
		return getService().getLCSMetadatas(start, end);
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

	public static LCSMetadataLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMetadataLocalService, LCSMetadataLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMetadataLocalService.class);
}