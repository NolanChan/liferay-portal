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

import com.liferay.osb.lcs.model.LCSMetadata;

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

import java.util.List;

/**
 * Provides the local service interface for LCSMetadata. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSMetadataLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSMetadataLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMetadataLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSMetadataLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSMetadataLocalServiceUtil} to access the l c s metadata local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMetadataLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the l c s metadata to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSMetadata addLCSMetadata(LCSMetadata lcsMetadata);

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
	public LCSMetadata addLCSMetadata(int buildNumber, java.lang.String gitTag,
		java.lang.String portalEdition, int supportedLCSPortlet,
		int supportedPatchingTool);

	/**
	* Creates a new l c s metadata with the primary key. Does not add the l c s metadata to the database.
	*
	* @param lcsMetadataId the primary key for the new l c s metadata
	* @return the new l c s metadata
	*/
	public LCSMetadata createLCSMetadata(long lcsMetadataId);

	/**
	* Deletes the l c s metadata from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSMetadata deleteLCSMetadata(LCSMetadata lcsMetadata);

	/**
	* Deletes the l c s metadata with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata that was removed
	* @throws PortalException if a l c s metadata with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSMetadata deleteLCSMetadata(long lcsMetadataId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMetadata fetchLCSMetadata(int buildNumber,
		java.lang.String gitTag, java.lang.String portalEdition);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMetadata fetchLCSMetadata(long lcsMetadataId);

	/**
	* Returns the l c s metadata with the primary key.
	*
	* @param lcsMetadataId the primary key of the l c s metadata
	* @return the l c s metadata
	* @throws PortalException if a l c s metadata with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSMetadata getLCSMetadata(long lcsMetadataId)
		throws PortalException;

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
	public LCSMetadata updateAvailabilityIndex(long lcsMetadataId,
		long availabilityIndex);

	/**
	* Updates the l c s metadata in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsMetadata the l c s metadata
	* @return the l c s metadata that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSMetadata updateLCSMetadata(LCSMetadata lcsMetadata);

	/**
	* Updates the supported LCS portlet build number in the LCS metadata
	* matching the primary key.
	*
	* @param lcsMetadataId the primary key of the LCS metadata
	* @param supportedLCSPortlet the supported LCS portlet's build number
	* @return the updated LCS metadata
	* @since LCS 1.1
	*/
	public LCSMetadata updateSupportedLCSPortlet(long lcsMetadataId,
		int supportedLCSPortlet);

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
	public LCSMetadata updateSupportedPatchingTool(long lcsMetadataId,
		int supportedPatchingToolVersion);

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
	* Returns the number of l c s metadatas.
	*
	* @return the number of l c s metadatas
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSMetadatasCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns all LCS metadata that match the build number and portal edition.
	*
	* @param buildNumber the portal instance's build number
	* @param portalEdition the portal instance's edition
	* @return the matching LCS metadata
	* @since LCS 1.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMetadata> getLCSMetadatas(int buildNumber,
		java.lang.String portalEdition);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSMetadata> getLCSMetadatas(int start, int end);

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