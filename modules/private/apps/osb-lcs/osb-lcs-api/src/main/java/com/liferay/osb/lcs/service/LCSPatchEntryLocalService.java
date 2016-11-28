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

import com.liferay.osb.lcs.model.LCSPatchEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for LCSPatchEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSPatchEntryLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSPatchEntryLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSPatchEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSPatchEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSPatchEntryLocalServiceUtil} to access the l c s patch entry local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSPatchEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the l c s patch entry to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSPatchEntry addLCSPatchEntry(LCSPatchEntry lcsPatchEntry);

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
	public LCSPatchEntry addLCSPatchEntry(java.lang.String patchId,
		java.lang.String name, java.lang.String description,
		int patchingToolVersion, boolean incremental, boolean singular,
		int version, long size, long rank, java.lang.String requirements,
		java.lang.String component, java.lang.String compatibleBuild,
		java.lang.String product, java.lang.String fixedIssues,
		java.lang.String moduleName, java.lang.String moduleId,
		boolean tunnelWeb, Date buildDate, java.lang.String builtFor);

	/**
	* Creates a new l c s patch entry with the primary key. Does not add the l c s patch entry to the database.
	*
	* @param lcsPatchEntryId the primary key for the new l c s patch entry
	* @return the new l c s patch entry
	*/
	public LCSPatchEntry createLCSPatchEntry(long lcsPatchEntryId);

	/**
	* Deletes the l c s patch entry from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSPatchEntry deleteLCSPatchEntry(LCSPatchEntry lcsPatchEntry);

	/**
	* Deletes the l c s patch entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry that was removed
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSPatchEntry deleteLCSPatchEntry(long lcsPatchEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSPatchEntry fetchLCSPatchEntry(long lcsPatchEntryId);

	/**
	* Returns the LCS patch entry matching the patch ID.
	*
	* @param patchId the patch ID defined by Liferay support
	* @return the LCS patch entry matching the patch ID
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSPatchEntry getLCSPatchEntry(java.lang.String patchId)
		throws PortalException;

	/**
	* Returns the l c s patch entry with the primary key.
	*
	* @param lcsPatchEntryId the primary key of the l c s patch entry
	* @return the l c s patch entry
	* @throws PortalException if a l c s patch entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSPatchEntry getLCSPatchEntry(long lcsPatchEntryId)
		throws PortalException;

	/**
	* Updates the l c s patch entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsPatchEntry the l c s patch entry
	* @return the l c s patch entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSPatchEntry updateLCSPatchEntry(LCSPatchEntry lcsPatchEntry);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of l c s patch entries.
	*
	* @return the number of l c s patch entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSPatchEntriesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSPatchEntry> getLCSPatchEntries(int patchingToolVersion,
		java.lang.String product);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSPatchEntry> getLCSPatchEntries(int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}