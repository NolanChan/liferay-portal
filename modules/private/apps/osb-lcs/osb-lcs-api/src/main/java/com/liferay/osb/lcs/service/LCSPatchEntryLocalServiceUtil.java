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
 * Provides the local service utility for LCSPatchEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSPatchEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSPatchEntryLocalService
 * @see com.liferay.osb.lcs.service.base.LCSPatchEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSPatchEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSPatchEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSPatchEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the l c s patch entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was added
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry addLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return getService().addLCSPatchEntry(lcsPatchEntry);
	}

	/**
	* Adds a new LCS patch entry.
	*
	* @param patchId the patch ID specified by Liferay support
	* @param name the patch name
	* @param description the patch description
	* @param patchingToolVersion the minimum version of the patching tool
	compatible with this patch
	* @param incremental whether the patch is incremental
	* @param singular whether the patch is singular
	* @param version the patch version
	* @param size
	* @param rank the patch rank. This is necessary during the install phase
	to determine the chronology of the fixes.
	* @param requirements the patch requirements. This is only applicable when
	the patch isn't singular.
	* @param component the patch component. Examples include hotfix, portal,
	and collaboration.
	* @param compatibleBuild the build number of the portal instance
	compatible with this patch. This is used for patches to Liferay
	6.1.20 and older.
	* @param product the build number of the portal instance compatible with
	this patch. This is used for patches to Liferay versions newer
	than 6.1.20.
	* @param fixedIssues the comma delimited list of JIRA ticket IDs addressed
	in the patch
	* @param moduleName the portal module name fixed by the patch
	* @param moduleId the portal module ID
	* @param tunnelWeb whether the patch requires the Tunnel Web plugin. This
	isn't used in patches newer than 6.1.20.
	* @param buildDate the patch's build date
	* @param builtFor the name of the customer the patch was built for
	* @return the new LCS patch entry
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry addLCSPatchEntry(
		java.lang.String patchId, java.lang.String name,
		java.lang.String description, int patchingToolVersion,
		boolean incremental, boolean singular, int version, long size,
		long rank, java.lang.String requirements, java.lang.String component,
		java.lang.String compatibleBuild, java.lang.String product,
		java.lang.String fixedIssues, java.lang.String moduleName,
		java.lang.String moduleId, boolean tunnelWeb, java.util.Date buildDate,
		java.lang.String builtFor) {
		return getService()
				   .addLCSPatchEntry(patchId, name, description,
			patchingToolVersion, incremental, singular, version, size, rank,
			requirements, component, compatibleBuild, product, fixedIssues,
			moduleName, moduleId, tunnelWeb, buildDate, builtFor);
	}

	/**
	* Creates a new l c s patch entry with the primary key. Does not add the l c s patch entry to the database.
	*
	* @param lcsPatchEntryId the primary key for the new l c s patch entry
	* @return the new l c s patch entry
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry createLCSPatchEntry(
		long lcsPatchEntryId) {
		return getService().createLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Deletes the l c s patch entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was removed
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry deleteLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return getService().deleteLCSPatchEntry(lcsPatchEntry);
	}

	/**
	* Deletes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry that was removed
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry deleteLCSPatchEntry(
		long lcsPatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSPatchEntry(lcsPatchEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSPatchEntry fetchLCSPatchEntry(
		long lcsPatchEntryId) {
		return getService().fetchLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Returns the LCS patch entry matching the patch ID.
	*
	* @param patchId the patch ID defined by Liferay support
	* @return the LCS patch entry matching the patch ID
	* @since LCS 0.1
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry getLCSPatchEntry(
		java.lang.String patchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSPatchEntry(patchId);
	}

	/**
	* Returns the l c s patch entry with the primary key.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry getLCSPatchEntry(
		long lcsPatchEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSPatchEntry(lcsPatchEntryId);
	}

	/**
	* Updates the l c s patch entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was updated
	*/
	public static com.liferay.osb.lcs.model.LCSPatchEntry updateLCSPatchEntry(
		com.liferay.osb.lcs.model.LCSPatchEntry lcsPatchEntry) {
		return getService().updateLCSPatchEntry(lcsPatchEntry);
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
	* Returns the number of l c s patch entries.
	*
	* @return the number of l c s patch entries
	*/
	public static int getLCSPatchEntriesCount() {
		return getService().getLCSPatchEntriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns all LCS patch entries matching the patching tool version and
	* product.
	*
	* @param patchingToolVersion the patching tool version
	* @param product the portal instance build number
	* @return all LCS patch entries matching the patching tool version and
	product
	* @since LCS 0.1
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSPatchEntry> getLCSPatchEntries(
		int patchingToolVersion, java.lang.String product) {
		return getService().getLCSPatchEntries(patchingToolVersion, product);
	}

	/**
	* Returns a range of all the l c s patch entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSPatchEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s patch entries
	* @param end the upper bound of the range of l c s patch entries (not inclusive)
	* @return the range of l c s patch entries
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSPatchEntry> getLCSPatchEntries(
		int start, int end) {
		return getService().getLCSPatchEntries(start, end);
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

	public static LCSPatchEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSPatchEntryLocalService, LCSPatchEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSPatchEntryLocalService.class);
}