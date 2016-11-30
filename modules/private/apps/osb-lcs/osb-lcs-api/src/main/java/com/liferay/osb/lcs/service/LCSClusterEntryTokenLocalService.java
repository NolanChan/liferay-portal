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

import com.liferay.osb.lcs.model.LCSClusterEntryToken;

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
import java.util.Map;

/**
 * Provides the local service interface for LCSClusterEntryToken. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryTokenLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterEntryTokenLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterEntryTokenLocalServiceUtil} to access the l c s cluster entry token local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the l c s cluster entry token to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterEntryToken addLCSClusterEntryToken(
		LCSClusterEntryToken lcsClusterEntryToken);

	public LCSClusterEntryToken addLCSClusterEntryToken(long userId,
		long lcsClusterEntryId,
		Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws PortalException;

	/**
	* Creates a new l c s cluster entry token with the primary key. Does not add the l c s cluster entry token to the database.
	*
	* @param lcsClusterEntryTokenId the primary key for the new l c s cluster entry token
	* @return the new l c s cluster entry token
	*/
	public LCSClusterEntryToken createLCSClusterEntryToken(
		long lcsClusterEntryTokenId);

	/**
	* Deletes the l c s cluster entry token from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
		LCSClusterEntryToken lcsClusterEntryToken);

	/**
	* Deletes the l c s cluster entry token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token that was removed
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId);

	/**
	* Returns the l c s cluster entry token with the primary key.
	*
	* @param lcsClusterEntryTokenId the primary key of the l c s cluster entry token
	* @return the l c s cluster entry token
	* @throws PortalException if a l c s cluster entry token with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterEntryToken getLCSClusterEntryToken(
		long lcsClusterEntryTokenId) throws PortalException;

	public LCSClusterEntryToken regenerateLCSClusterEntryToken(long userId,
		long lcsClusterEntryId,
		Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws PortalException;

	/**
	* Updates the l c s cluster entry token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterEntryToken the l c s cluster entry token
	* @return the l c s cluster entry token that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterEntryToken updateLCSClusterEntryToken(
		LCSClusterEntryToken lcsClusterEntryToken);

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
	* Returns the number of l c s cluster entry tokens.
	*
	* @return the number of l c s cluster entry tokens
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSClusterEntryTokensCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterEntryTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterEntryToken> getLCSClusterEntryTokens(int start,
		int end);

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