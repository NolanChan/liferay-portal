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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link LayoutBranchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchLocalService
 * @generated
 */
@ProviderType
public class LayoutBranchLocalServiceWrapper implements LayoutBranchLocalService,
	ServiceWrapper<LayoutBranchLocalService> {
	public LayoutBranchLocalServiceWrapper(
		LayoutBranchLocalService layoutBranchLocalService) {
		_layoutBranchLocalService = layoutBranchLocalService;
	}

	/**
	* Adds the layout branch to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutBranch the layout branch
	* @return the layout branch that was added
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch addLayoutBranch(
		com.liferay.portal.model.LayoutBranch layoutBranch) {
		return _layoutBranchLocalService.addLayoutBranch(layoutBranch);
	}

	/**
	* Creates a new layout branch with the primary key. Does not add the layout branch to the database.
	*
	* @param LayoutBranchId the primary key for the new layout branch
	* @return the new layout branch
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch createLayoutBranch(
		long LayoutBranchId) {
		return _layoutBranchLocalService.createLayoutBranch(LayoutBranchId);
	}

	/**
	* Deletes the layout branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param LayoutBranchId the primary key of the layout branch
	* @return the layout branch that was removed
	* @throws PortalException if a layout branch with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch deleteLayoutBranch(
		long LayoutBranchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.deleteLayoutBranch(LayoutBranchId);
	}

	/**
	* Deletes the layout branch from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutBranch the layout branch
	* @return the layout branch that was removed
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch deleteLayoutBranch(
		com.liferay.portal.model.LayoutBranch layoutBranch) {
		return _layoutBranchLocalService.deleteLayoutBranch(layoutBranch);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _layoutBranchLocalService.dynamicQuery();
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
		return _layoutBranchLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _layoutBranchLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _layoutBranchLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _layoutBranchLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _layoutBranchLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch fetchLayoutBranch(
		long LayoutBranchId) {
		return _layoutBranchLocalService.fetchLayoutBranch(LayoutBranchId);
	}

	/**
	* Returns the layout branch with the primary key.
	*
	* @param LayoutBranchId the primary key of the layout branch
	* @return the layout branch
	* @throws PortalException if a layout branch with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch getLayoutBranch(
		long LayoutBranchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.getLayoutBranch(LayoutBranchId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _layoutBranchLocalService.getActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the layout branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.LayoutBranchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of layout branchs
	* @param end the upper bound of the range of layout branchs (not inclusive)
	* @return the range of layout branchs
	*/
	@Override
	public java.util.List<com.liferay.portal.model.LayoutBranch> getLayoutBranchs(
		int start, int end) {
		return _layoutBranchLocalService.getLayoutBranchs(start, end);
	}

	/**
	* Returns the number of layout branchs.
	*
	* @return the number of layout branchs
	*/
	@Override
	public int getLayoutBranchsCount() {
		return _layoutBranchLocalService.getLayoutBranchsCount();
	}

	/**
	* Updates the layout branch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param layoutBranch the layout branch
	* @return the layout branch that was updated
	*/
	@Override
	public com.liferay.portal.model.LayoutBranch updateLayoutBranch(
		com.liferay.portal.model.LayoutBranch layoutBranch) {
		return _layoutBranchLocalService.updateLayoutBranch(layoutBranch);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _layoutBranchLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutBranchLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch addLayoutBranch(
		long layoutSetBranchId, long plid, java.lang.String name,
		java.lang.String description, boolean master,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.addLayoutBranch(layoutSetBranchId,
			plid, name, description, master, serviceContext);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch addLayoutBranch(
		long layoutRevisionId, java.lang.String name,
		java.lang.String description, boolean master,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.addLayoutBranch(layoutRevisionId,
			name, description, master, serviceContext);
	}

	@Override
	public void deleteLayoutSetBranchLayoutBranches(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_layoutBranchLocalService.deleteLayoutSetBranchLayoutBranches(layoutSetBranchId);
	}

	@Override
	public java.util.List<com.liferay.portal.model.LayoutBranch> getLayoutBranches(
		long layoutSetBranchId, long plid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _layoutBranchLocalService.getLayoutBranches(layoutSetBranchId,
			plid, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.portal.model.LayoutBranch> getLayoutSetBranchLayoutBranches(
		long layoutSetBranchId) {
		return _layoutBranchLocalService.getLayoutSetBranchLayoutBranches(layoutSetBranchId);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch getMasterLayoutBranch(
		long layoutSetBranchId, long plid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.getMasterLayoutBranch(layoutSetBranchId,
			plid);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch getMasterLayoutBranch(
		long layoutSetBranchId, long plid,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.getMasterLayoutBranch(layoutSetBranchId,
			plid, serviceContext);
	}

	@Override
	public com.liferay.portal.model.LayoutBranch updateLayoutBranch(
		long layoutBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _layoutBranchLocalService.updateLayoutBranch(layoutBranchId,
			name, description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public LayoutBranchLocalService getWrappedLayoutBranchLocalService() {
		return _layoutBranchLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedLayoutBranchLocalService(
		LayoutBranchLocalService layoutBranchLocalService) {
		_layoutBranchLocalService = layoutBranchLocalService;
	}

	@Override
	public LayoutBranchLocalService getWrappedService() {
		return _layoutBranchLocalService;
	}

	@Override
	public void setWrappedService(
		LayoutBranchLocalService layoutBranchLocalService) {
		_layoutBranchLocalService = layoutBranchLocalService;
	}

	private LayoutBranchLocalService _layoutBranchLocalService;
}