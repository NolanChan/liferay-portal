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

import com.liferay.lcs.subscription.SubscriptionType;

import com.liferay.osb.lcs.model.LCSClusterNodeUptime;

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
import java.util.Map;

/**
 * Provides the local service interface for LCSClusterNodeUptime. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimeLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterNodeUptimeLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterNodeUptimeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterNodeUptimeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterNodeUptimeLocalServiceUtil} to access the l c s cluster node uptime local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterNodeUptimeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the l c s cluster node uptime to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterNodeUptime addLCSClusterNodeUptime(
		LCSClusterNodeUptime lcsClusterNodeUptime);

	/**
	* Creates a new l c s cluster node uptime with the primary key. Does not add the l c s cluster node uptime to the database.
	*
	* @param lcsClusterNodeUptimeId the primary key for the new l c s cluster node uptime
	* @return the new l c s cluster node uptime
	*/
	public LCSClusterNodeUptime createLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId);

	/**
	* Deletes the l c s cluster node uptime from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterNodeUptime deleteLCSClusterNodeUptime(
		LCSClusterNodeUptime lcsClusterNodeUptime);

	/**
	* Deletes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	* @throws PortalException if a l c s cluster node uptime with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterNodeUptime deleteLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNodeUptime fetchLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId);

	/**
	* Returns the l c s cluster node uptime with the primary key.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime
	* @throws PortalException if a l c s cluster node uptime with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNodeUptime getLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId) throws PortalException;

	/**
	* Updates the l c s cluster node uptime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterNodeUptime updateLCSClusterNodeUptime(
		LCSClusterNodeUptime lcsClusterNodeUptime);

	public LCSClusterNodeUptime updateLCSClusterNodeUptime(java.lang.String key)
		throws PortalException;

	public LCSClusterNodeUptime updateLCSClusterNodeUptime(
		long lcsClusterNodeId) throws PortalException;

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public double getMonthlyElasticLCSClusterNodeUptimeTotal(
		long lcsProjectId, int month, int year) throws PortalException;

	/**
	* Returns the number of l c s cluster node uptimes.
	*
	* @return the number of l c s cluster node uptimes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSClusterNodeUptimesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the l c s cluster node uptimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster node uptimes
	* @param end the upper bound of the range of l c s cluster node uptimes (not inclusive)
	* @return the range of l c s cluster node uptimes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNodeUptime> getLCSClusterNodeUptimes(int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNodeUptime> getLCSClusterNodeUptimes(int start,
		int end, boolean details) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNodeUptime> getMonthlyElasticTotalLCSClusterNodeUptimes(
		long lcsProjectId, int month, int year) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
		int month, int year, boolean details, boolean elastic,
		SubscriptionType subscriptionType) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
		int startMonth, int startYear, int endMonth, int endYear,
		boolean elastic, SubscriptionType subscriptionType)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		long lcsProjectId, int range) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		long lcsProjectId, int startMonth, int startYear, int endMonth,
		int endYear) throws PortalException;

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

	public void updateLCSClusterNodeUptimes(java.lang.String key,
		java.lang.String uptimesJSON) throws PortalException;
}