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
 * Provides a wrapper for {@link LCSInvitationLocalService}.
 *
 * @author Igor Beslic
 * @see LCSInvitationLocalService
 * @generated
 */
@ProviderType
public class LCSInvitationLocalServiceWrapper
	implements LCSInvitationLocalService,
		ServiceWrapper<LCSInvitationLocalService> {
	public LCSInvitationLocalServiceWrapper(
		LCSInvitationLocalService lcsInvitationLocalService) {
		_lcsInvitationLocalService = lcsInvitationLocalService;
	}

	/**
	* Adds the l c s invitation to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation addLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return _lcsInvitationLocalService.addLCSInvitation(lcsInvitation);
	}

	/**
	* Creates a new l c s invitation with the primary key. Does not add the l c s invitation to the database.
	*
	* @param lcsInvitationId the primary key for the new l c s invitation
	* @return the new l c s invitation
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation createLCSInvitation(
		long lcsInvitationId) {
		return _lcsInvitationLocalService.createLCSInvitation(lcsInvitationId);
	}

	/**
	* Deletes the l c s invitation from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was removed
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return _lcsInvitationLocalService.deleteLCSInvitation(lcsInvitation);
	}

	/**
	* Deletes the l c s invitation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation that was removed
	* @throws PortalException if a l c s invitation with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationLocalService.deleteLCSInvitation(lcsInvitationId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSInvitation fetchLCSInvitation(
		long lcsInvitationId) {
		return _lcsInvitationLocalService.fetchLCSInvitation(lcsInvitationId);
	}

	/**
	* Returns the l c s invitation with the primary key.
	*
	* @param lcsInvitationId the primary key of the l c s invitation
	* @return the l c s invitation
	* @throws PortalException if a l c s invitation with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation getLCSInvitation(
		long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationLocalService.getLCSInvitation(lcsInvitationId);
	}

	/**
	* Updates the l c s invitation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsInvitation the l c s invitation
	* @return the l c s invitation that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSInvitation updateLCSInvitation(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation) {
		return _lcsInvitationLocalService.updateLCSInvitation(lcsInvitation);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsInvitationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsInvitationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsInvitationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s invitations.
	*
	* @return the number of l c s invitations
	*/
	@Override
	public int getLCSInvitationsCount() {
		return _lcsInvitationLocalService.getLCSInvitationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsInvitationLocalService.getOSGiServiceIdentifier();
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
		return _lcsInvitationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsInvitationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsInvitationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the l c s invitations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSInvitationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s invitations
	* @param end the upper bound of the range of l c s invitations (not inclusive)
	* @return the range of l c s invitations
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getLCSInvitations(
		int start, int end) {
		return _lcsInvitationLocalService.getLCSInvitations(start, end);
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
		return _lcsInvitationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsInvitationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public LCSInvitationLocalService getWrappedService() {
		return _lcsInvitationLocalService;
	}

	@Override
	public void setWrappedService(
		LCSInvitationLocalService lcsInvitationLocalService) {
		_lcsInvitationLocalService = lcsInvitationLocalService;
	}

	private LCSInvitationLocalService _lcsInvitationLocalService;
}