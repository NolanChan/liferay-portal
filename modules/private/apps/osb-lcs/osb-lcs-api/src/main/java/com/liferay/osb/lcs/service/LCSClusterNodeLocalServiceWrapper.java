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
 * Provides a wrapper for {@link LCSClusterNodeLocalService}.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeLocalService
 * @generated
 */
@ProviderType
public class LCSClusterNodeLocalServiceWrapper
	implements LCSClusterNodeLocalService,
		ServiceWrapper<LCSClusterNodeLocalService> {
	public LCSClusterNodeLocalServiceWrapper(
		LCSClusterNodeLocalService lcsClusterNodeLocalService) {
		_lcsClusterNodeLocalService = lcsClusterNodeLocalService;
	}

	@Override
	public boolean hasLCSClusterEntryAllInactiveLCSClusterNodes(
		long lcsClusterEntryId) {
		return _lcsClusterNodeLocalService.hasLCSClusterEntryAllInactiveLCSClusterNodes(lcsClusterEntryId);
	}

	@Override
	public boolean hasLCSClusterEntryAllLCSClusterNodes(
		long lcsClusterEntryId, int status) {
		return _lcsClusterNodeLocalService.hasLCSClusterEntryAllLCSClusterNodes(lcsClusterEntryId,
			status);
	}

	/**
	* Returns <code>true</code> if any one of the user's corporate projects has
	* at least one LCS cluster node.
	*
	* @param userId the primary key of the user
	* @return <code>true</code> if any one of the user's corporate projects has
	at least one LCS cluster node; <code>false</code> otherwise
	* @since LCS 1.2
	*/
	@Override
	public boolean hasUserLCSClusterNodes(long userId) {
		return _lcsClusterNodeLocalService.hasUserLCSClusterNodes(userId);
	}

	/**
	* Adds the l c s cluster node to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode) {
		return _lcsClusterNodeLocalService.addLCSClusterNode(lcsClusterNode);
	}

	/**
	* Adds a new LCS cluster node.
	*
	* @param lcsClusterEntryId the primary key of the parent environment
	* @param name the portal instance's friendly name
	* @param description the portal instance's description
	* @param buildNumber the portal instance's build number
	* @param key the key of the portal instance provided by the license tool
	* @param location the portal instance's physical location
	* @param processorCoresTotal
	* @return the LCS cluster node
	* @throws PortalException if any LCS cluster node attributes were invalid
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, int buildNumber, java.lang.String key,
		java.lang.String location, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.addLCSClusterNode(lcsClusterEntryId,
			name, description, buildNumber, key, location, processorCoresTotal);
	}

	/**
	* Creates a new l c s cluster node with the primary key. Does not add the l c s cluster node to the database.
	*
	* @param lcsClusterNodeId the primary key for the new l c s cluster node
	* @return the new l c s cluster node
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode createLCSClusterNode(
		long lcsClusterNodeId) {
		return _lcsClusterNodeLocalService.createLCSClusterNode(lcsClusterNodeId);
	}

	/**
	* Deletes the l c s cluster node from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode deleteLCSClusterNode(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.deleteLCSClusterNode(lcsClusterNode);
	}

	/**
	* Deletes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws PortalException if a l c s cluster node with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode deleteLCSClusterNode(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.deleteLCSClusterNode(lcsClusterNodeId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode fetchLCSClusterNode(
		long lcsClusterNodeId) {
		return _lcsClusterNodeLocalService.fetchLCSClusterNode(lcsClusterNodeId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode fetchRandomSiblingLCSClusterNode(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.fetchRandomSiblingLCSClusterNode(lcsClusterNode);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		java.lang.String key, boolean details) {
		return _lcsClusterNodeLocalService.getLCSClusterNode(key, details);
	}

	/**
	* Returns the LCS cluster node matching the portal instance key.
	*
	* @param lcsClusterNodeKey the portal instance key provided by the license
	tool
	* @return the LCS cluster node matching the portal instance key
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		java.lang.String lcsClusterNodeKey) {
		return _lcsClusterNodeLocalService.getLCSClusterNode(lcsClusterNodeKey);
	}

	/**
	* Returns the l c s cluster node with the primary key.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node
	* @throws PortalException if a l c s cluster node with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.getLCSClusterNode(lcsClusterNodeId);
	}

	/**
	* Returns the LCS cluster node matching the primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @param details whether to include the LCS cluster node's transient
	details
	* @return the LCS cluster node matching the primary key
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		long lcsClusterNodeId, boolean details) {
		return _lcsClusterNodeLocalService.getLCSClusterNode(lcsClusterNodeId,
			details);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode mergeStatus(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode, int status) {
		return _lcsClusterNodeLocalService.mergeStatus(lcsClusterNode, status);
	}

	/**
	* Updates the l c s cluster node in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode updateLCSClusterNode(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode) {
		return _lcsClusterNodeLocalService.updateLCSClusterNode(lcsClusterNode);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode updateLCSClusterNode(
		long lcsClusterNodeId, java.lang.String name,
		java.lang.String description, int buildNumber, java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.updateLCSClusterNode(lcsClusterNodeId,
			name, description, buildNumber, location);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode updateStatus(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.updateStatus(lcsClusterNode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsClusterNodeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsClusterNodeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsClusterNodeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of l c s cluster nodes.
	*
	* @return the number of l c s cluster nodes
	*/
	@Override
	public int getLCSClusterNodesCount() {
		return _lcsClusterNodeLocalService.getLCSClusterNodesCount();
	}

	@Override
	public int getLCSClusterNodesCount(long lcsClusterEntryId) {
		return _lcsClusterNodeLocalService.getLCSClusterNodesCount(lcsClusterEntryId);
	}

	@Override
	public int getLCSClusterNodesCount(long[] lcsClusterEntryIds) {
		return _lcsClusterNodeLocalService.getLCSClusterNodesCount(lcsClusterEntryIds);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterNodeLocalService.getOSGiServiceIdentifier();
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
		return _lcsClusterNodeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterNodeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lcsClusterNodeLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getArchivedLCSClusterNodes(
		int buildNumber) {
		return _lcsClusterNodeLocalService.getArchivedLCSClusterNodes(buildNumber);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId) {
		return _lcsClusterNodeLocalService.getArchivedLCSProjectLCSClusterNodes(userId,
			lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean details) {
		return _lcsClusterNodeLocalService.getArchivedLCSProjectLCSClusterNodes(userId,
			lcsProjectId, details);
	}

	/**
	* Returns all LCS cluster nodes in the LCS cluster entry. The LCS cluster
	* nodes' transient details are excluded.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @return the LCS cluster nodes in the LCS cluster entry
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId) {
		return _lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);
	}

	/**
	* Returns all LCS cluster nodes in the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @param details whether to include the LCS cluster nodes' transient
	details
	* @return the LCS cluster nodes in the LCS cluster entry
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details) {
		return _lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId,
			details);
	}

	/**
	* Returns the LCS cluster nodes that have the specified portal instance
	* build number.
	*
	* @param buildNumber the portal instance build number
	* @return the LCS cluster nodes that have the portal instance build number
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodes(
		int buildNumber) {
		return _lcsClusterNodeLocalService.getLCSClusterNodes(buildNumber);
	}

	/**
	* Returns a range of all the l c s cluster nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s cluster nodes
	* @param end the upper bound of the range of l c s cluster nodes (not inclusive)
	* @return the range of l c s cluster nodes
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodes(
		int start, int end) {
		return _lcsClusterNodeLocalService.getLCSClusterNodes(start, end);
	}

	/**
	* Returns a range of the LCS cluster nodes.
	*
	* <p>
	* This method is useful when paginating results. The start and end values
	* are not primary keys: they are indices in the result set. Therefore, a
	* start value of <code>0</code> refers to the first result in the set. The
	* maximum number of instances returned is the end index minus the start
	* index. Setting both indices to {@link
	* QueryUtil#ALL_POS} returns the full
	* result set.
	* </p>
	*
	* @param start the lower bound of the range
	* @param end the upper bound of the range. This is not inclusive.
	* @param details whether to include the LCS cluster nodes' transient
	details
	* @return the range of the LCS cluster nodes
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodes(
		int start, int end, boolean details) {
		return _lcsClusterNodeLocalService.getLCSClusterNodes(start, end,
			details);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodes(
		java.lang.String lcsClusterEntryName, java.lang.String lcsProjectName,
		boolean andOperator, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.getLCSClusterNodes(lcsClusterEntryName,
			lcsProjectName, andOperator, details);
	}

	/**
	* Returns all LCS cluster nodes accessible to the user and belonging to the
	* corporate project. The LCS cluster nodes' transient details are excluded.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @return the LCS cluster nodes accessible to the user and belonging to the
	corporate project
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId) {
		return _lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(userId,
			lcsProjectId);
	}

	/**
	* Returns all LCS cluster nodes accessible to the user and belonging to the
	* corporate project, optionally including the LCS nodes' transient details.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @param details whether to include the LCS cluster nodes' transient
	details
	* @return the LCS cluster nodes accessible to the user and belonging to the
	corporate project
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean details) {
		return _lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(userId,
			lcsProjectId, details);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getSiblingLCSClusterNodes(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.getSiblingLCSClusterNodes(key);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getUserLCSClusterNodes(
		long userId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeLocalService.getUserLCSClusterNodes(userId,
			details);
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
		return _lcsClusterNodeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lcsClusterNodeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Deletes all LCS cluster nodes in the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @throws PortalException if an LCS cluster node could not be found in the
	LCS cluster entry
	* @since LCS 0.1
	*/
	@Override
	public void deleteLCSClusterEntryLCSClusterNodes(long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeLocalService.deleteLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);
	}

	@Override
	public LCSClusterNodeLocalService getWrappedService() {
		return _lcsClusterNodeLocalService;
	}

	@Override
	public void setWrappedService(
		LCSClusterNodeLocalService lcsClusterNodeLocalService) {
		_lcsClusterNodeLocalService = lcsClusterNodeLocalService;
	}

	private LCSClusterNodeLocalService _lcsClusterNodeLocalService;
}