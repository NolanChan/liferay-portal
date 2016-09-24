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
 * Provides a wrapper for {@link LCSProjectLocalService}.
 *
 * @author Igor Beslic
 * @see LCSProjectLocalService
 * @generated
 */
@ProviderType
public class LCSProjectLocalServiceWrapper implements LCSProjectLocalService,
	ServiceWrapper<LCSProjectLocalService> {
	public LCSProjectLocalServiceWrapper(
		LCSProjectLocalService lcsProjectLocalService) {
		_lcsProjectLocalService = lcsProjectLocalService;
	}

	@Override
	public boolean checkUserAccountEntryLCSProject(
		com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.checkUserAccountEntryLCSProject(user);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject addLCSProject(
		com.liferay.osb.lcs.model.CorpProject corpProject, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.addLCSProject(corpProject, userId);
	}

	/**
	* Adds the l c s project to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsProject the l c s project
	* @return the l c s project that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject addLCSProject(
		com.liferay.osb.lcs.model.LCSProject lcsProject) {
		return _lcsProjectLocalService.addLCSProject(lcsProject);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject addLCSProject(
		java.lang.String sourceSystemName, java.lang.String name, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.addLCSProject(sourceSystemName, name,
			userId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject addLCSProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.addLCSProject(corpProjectId);
	}

	/**
	* Creates a new l c s project with the primary key. Does not add the l c s project to the database.
	*
	* @param lcsProjectId the primary key for the new l c s project
	* @return the new l c s project
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject createLCSProject(
		long lcsProjectId) {
		return _lcsProjectLocalService.createLCSProject(lcsProjectId);
	}

	/**
	* Deletes the l c s project from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsProject the l c s project
	* @return the l c s project that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject deleteLCSProject(
		com.liferay.osb.lcs.model.LCSProject lcsProject) {
		return _lcsProjectLocalService.deleteLCSProject(lcsProject);
	}

	/**
	* Deletes the l c s project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project that was removed
	* @throws PortalException if a l c s project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject deleteLCSProject(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.deleteLCSProject(lcsProjectId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject fetchByCorpProject(
		long corpProjectId) {
		return _lcsProjectLocalService.fetchByCorpProject(corpProjectId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject fetchLCSProject(
		long lcsProjectId) {
		return _lcsProjectLocalService.fetchLCSProject(lcsProjectId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject findByCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.findByCorpProject(corpProjectId);
	}

	/**
	* Returns the l c s project with the primary key.
	*
	* @param lcsProjectId the primary key of the l c s project
	* @return the l c s project
	* @throws PortalException if a l c s project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject getLCSProject(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.getLCSProject(lcsProjectId);
	}

	/**
	* Updates the l c s project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsProject the l c s project
	* @return the l c s project that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSProject updateLCSProject(
		com.liferay.osb.lcs.model.LCSProject lcsProject) {
		return _lcsProjectLocalService.updateLCSProject(lcsProject);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSProject updateSubscriptionActive(
		long lcsProjectId, boolean subscriptionActive)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.updateSubscriptionActive(lcsProjectId,
			subscriptionActive);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsProjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsProjectLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsProjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s projects.
	*
	* @return the number of l c s projects
	*/
	@Override
	public int getLCSProjectsCount() {
		return _lcsProjectLocalService.getLCSProjectsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsProjectLocalService.getOSGiServiceIdentifier();
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
		return _lcsProjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsProjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsProjectLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> findByName(
		java.lang.String name) {
		return _lcsProjectLocalService.findByName(name);
	}

	/**
	* Returns a range of all the l c s projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s projects
	* @param end the upper bound of the range of l c s projects (not inclusive)
	* @return the range of l c s projects
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> getLCSProjects(
		int start, int end) {
		return _lcsProjectLocalService.getLCSProjects(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserDomainLCSProjects(
		com.liferay.portal.kernel.model.User user) {
		return _lcsProjectLocalService.getUserDomainLCSProjects(user);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.getUserLCSProjects(userId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects(
		long userId, boolean lcsRole)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.getUserLCSProjects(userId, lcsRole);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects(
		long userId, boolean lcsRole, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsProjectLocalService.getUserLCSProjects(userId, lcsRole, role);
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
		return _lcsProjectLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsProjectLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public long[] getLocalCorpProjectIds() {
		return _lcsProjectLocalService.getLocalCorpProjectIds();
	}

	@Override
	public LCSProjectLocalService getWrappedService() {
		return _lcsProjectLocalService;
	}

	@Override
	public void setWrappedService(LCSProjectLocalService lcsProjectLocalService) {
		_lcsProjectLocalService = lcsProjectLocalService;
	}

	private LCSProjectLocalService _lcsProjectLocalService;
}