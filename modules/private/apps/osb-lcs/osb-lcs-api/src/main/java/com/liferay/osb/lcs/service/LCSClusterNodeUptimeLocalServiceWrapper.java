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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LCSClusterNodeUptimeLocalService}.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimeLocalService
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeLocalServiceWrapper
	implements LCSClusterNodeUptimeLocalService,
		ServiceWrapper<LCSClusterNodeUptimeLocalService> {
	public LCSClusterNodeUptimeLocalServiceWrapper(
		LCSClusterNodeUptimeLocalService lcsClusterNodeUptimeLocalService) {
		_lcsClusterNodeUptimeLocalService = lcsClusterNodeUptimeLocalService;
	}

	/**
	* Adds the l c s cluster node uptime to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime addLCSClusterNodeUptime(
		com.liferay.osb.lcs.model.LCSClusterNodeUptime lcsClusterNodeUptime) {
		return _lcsClusterNodeUptimeLocalService.addLCSClusterNodeUptime(lcsClusterNodeUptime);
	}

	/**
	* Creates a new l c s cluster node uptime with the primary key. Does not add the l c s cluster node uptime to the database.
	*
	* @param lcsClusterNodeUptimeId the primary key for the new l c s cluster node uptime
	* @return the new l c s cluster node uptime
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime createLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId) {
		return _lcsClusterNodeUptimeLocalService.createLCSClusterNodeUptime(lcsClusterNodeUptimeId);
	}

	/**
	* Deletes the l c s cluster node uptime from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime deleteLCSClusterNodeUptime(
		com.liferay.osb.lcs.model.LCSClusterNodeUptime lcsClusterNodeUptime) {
		return _lcsClusterNodeUptimeLocalService.deleteLCSClusterNodeUptime(lcsClusterNodeUptime);
	}

	/**
	* Deletes the l c s cluster node uptime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime that was removed
	* @throws PortalException if a l c s cluster node uptime with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime deleteLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.deleteLCSClusterNodeUptime(lcsClusterNodeUptimeId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime fetchLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId) {
		return _lcsClusterNodeUptimeLocalService.fetchLCSClusterNodeUptime(lcsClusterNodeUptimeId);
	}

	/**
	* Returns the l c s cluster node uptime with the primary key.
	*
	* @param lcsClusterNodeUptimeId the primary key of the l c s cluster node uptime
	* @return the l c s cluster node uptime
	* @throws PortalException if a l c s cluster node uptime with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime getLCSClusterNodeUptime(
		long lcsClusterNodeUptimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getLCSClusterNodeUptime(lcsClusterNodeUptimeId);
	}

	/**
	* Updates the l c s cluster node uptime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeUptime the l c s cluster node uptime
	* @return the l c s cluster node uptime that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime updateLCSClusterNodeUptime(
		com.liferay.osb.lcs.model.LCSClusterNodeUptime lcsClusterNodeUptime) {
		return _lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptime(lcsClusterNodeUptime);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime updateLCSClusterNodeUptime(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptime(key);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNodeUptime updateLCSClusterNodeUptime(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptime(lcsClusterNodeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsClusterNodeUptimeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsClusterNodeUptimeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsClusterNodeUptimeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public double getMonthlyElasticLCSClusterNodeUptimeTotal(
		long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyElasticLCSClusterNodeUptimeTotal(lcsProjectId,
			month, year);
	}

	/**
	* Returns the number of l c s cluster node uptimes.
	*
	* @return the number of l c s cluster node uptimes
	*/
	@Override
	public int getLCSClusterNodeUptimesCount() {
		return _lcsClusterNodeUptimeLocalService.getLCSClusterNodeUptimesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterNodeUptimeLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsClusterNodeUptimeLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _lcsClusterNodeUptimeLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _lcsClusterNodeUptimeLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

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
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getLCSClusterNodeUptimes(
		int start, int end) {
		return _lcsClusterNodeUptimeLocalService.getLCSClusterNodeUptimes(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getLCSClusterNodeUptimes(
		int start, int end, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getLCSClusterNodeUptimes(start,
			end, details);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyElasticTotalLCSClusterNodeUptimes(
		long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyElasticTotalLCSClusterNodeUptimes(lcsProjectId,
			month, year);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
		int month, int year, boolean details, boolean elastic,
		com.liferay.lcs.subscription.SubscriptionType subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyLCSClusterNodeUptimes(lcsClusterEntryId,
			lcsClusterNodeId, lcsProjectId, month, year, details, elastic,
			subscriptionType);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
		int startMonth, int startYear, int endMonth, int endYear,
		boolean elastic,
		com.liferay.lcs.subscription.SubscriptionType subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyLCSClusterNodeUptimes(lcsClusterEntryId,
			lcsClusterNodeId, lcsProjectId, startMonth, startYear, endMonth,
			endYear, elastic, subscriptionType);
	}

	@Override
	public java.util.Map<java.util.Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		long lcsProjectId, int range)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyElasticLCSClusterNodeUptimeTotalMap(lcsProjectId,
			range);
	}

	@Override
	public java.util.Map<java.util.Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		long lcsProjectId, int startMonth, int startYear, int endMonth,
		int endYear) throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeLocalService.getMonthlyElasticLCSClusterNodeUptimeTotalMap(lcsProjectId,
			startMonth, startYear, endMonth, endYear);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsClusterNodeUptimeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _lcsClusterNodeUptimeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateLCSClusterNodeUptimes(java.lang.String key,
		java.lang.String uptimesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptimes(key,
			uptimesJSON);
	}

	@Override
	public LCSClusterNodeUptimeLocalService getWrappedService() {
		return _lcsClusterNodeUptimeLocalService;
	}

	@Override
	public void setWrappedService(
		LCSClusterNodeUptimeLocalService lcsClusterNodeUptimeLocalService) {
		_lcsClusterNodeUptimeLocalService = lcsClusterNodeUptimeLocalService;
	}

	private LCSClusterNodeUptimeLocalService _lcsClusterNodeUptimeLocalService;
}