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

import com.liferay.osb.lcs.model.LCSClusterNode;

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
 * Provides the local service interface for LCSClusterNode. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterNodeLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterNodeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterNodeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterNodeLocalServiceUtil} to access the l c s cluster node local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterNodeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasLCSClusterEntryAllInactiveLCSClusterNodes(
		long lcsClusterEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasLCSClusterEntryAllLCSClusterNodes(
		long lcsClusterEntryId, int status);

	/**
	* Returns <code>true</code> if any one of the user's corporate projects has
	* at least one LCS cluster node.
	*
	* @param userId the primary key of the user
	* @return <code>true</code> if any one of the user's corporate projects has
	at least one LCS cluster node; <code>false</code> otherwise
	* @since LCS 1.2
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSClusterNodes(long userId);

	/**
	* Adds the l c s cluster node to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterNode addLCSClusterNode(LCSClusterNode lcsClusterNode);

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
	public LCSClusterNode addLCSClusterNode(long lcsClusterEntryId,
		java.lang.String name, java.lang.String description, int buildNumber,
		java.lang.String key, java.lang.String location, int processorCoresTotal)
		throws PortalException;

	/**
	* Creates a new l c s cluster node with the primary key. Does not add the l c s cluster node to the database.
	*
	* @param lcsClusterNodeId the primary key for the new l c s cluster node
	* @return the new l c s cluster node
	*/
	public LCSClusterNode createLCSClusterNode(long lcsClusterNodeId);

	/**
	* Deletes the l c s cluster node from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterNode deleteLCSClusterNode(LCSClusterNode lcsClusterNode)
		throws PortalException;

	/**
	* Deletes the l c s cluster node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node that was removed
	* @throws PortalException if a l c s cluster node with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSClusterNode deleteLCSClusterNode(long lcsClusterNodeId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode fetchLCSClusterNode(long lcsClusterNodeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode fetchRandomSiblingLCSClusterNode(
		LCSClusterNode lcsClusterNode) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(java.lang.String key,
		boolean details);

	/**
	* Returns the LCS cluster node matching the portal instance key.
	*
	* @param lcsClusterNodeKey the portal instance key provided by the license
	tool
	* @return the LCS cluster node matching the portal instance key
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(java.lang.String lcsClusterNodeKey);

	/**
	* Returns the l c s cluster node with the primary key.
	*
	* @param lcsClusterNodeId the primary key of the l c s cluster node
	* @return the l c s cluster node
	* @throws PortalException if a l c s cluster node with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(long lcsClusterNodeId)
		throws PortalException;

	/**
	* Returns the LCS cluster node matching the primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @param details whether to include the LCS cluster node's transient
	details
	* @return the LCS cluster node matching the primary key
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(long lcsClusterNodeId,
		boolean details);

	public LCSClusterNode mergeStatus(LCSClusterNode lcsClusterNode, int status);

	/**
	* Updates the l c s cluster node in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsClusterNode the l c s cluster node
	* @return the l c s cluster node that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSClusterNode updateLCSClusterNode(LCSClusterNode lcsClusterNode);

	public LCSClusterNode updateLCSClusterNode(long lcsClusterNodeId,
		java.lang.String name, java.lang.String description, int buildNumber,
		java.lang.String location) throws PortalException;

	public LCSClusterNode updateStatus(LCSClusterNode lcsClusterNode)
		throws PortalException;

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
	* Returns the number of l c s cluster nodes.
	*
	* @return the number of l c s cluster nodes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSClusterNodesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSClusterNodesCount(long lcsClusterEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSClusterNodesCount(long[] lcsClusterEntryIds);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getArchivedLCSClusterNodes(int buildNumber);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean details);

	/**
	* Returns all LCS cluster nodes in the LCS cluster entry. The LCS cluster
	* nodes' transient details are excluded.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @return the LCS cluster nodes in the LCS cluster entry
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId);

	/**
	* Returns all LCS cluster nodes in the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @param details whether to include the LCS cluster nodes' transient
	details
	* @return the LCS cluster nodes in the LCS cluster entry
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details);

	/**
	* Returns the LCS cluster nodes that have the specified portal instance
	* build number.
	*
	* @param buildNumber the portal instance build number
	* @return the LCS cluster nodes that have the portal instance build number
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterNodes(int buildNumber);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterNodes(int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterNodes(int start, int end,
		boolean details);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterNodes(
		java.lang.String lcsClusterEntryName, java.lang.String lcsProjectName,
		boolean andOperator, boolean details) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(long userId,
		long lcsProjectId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(long userId,
		long lcsProjectId, boolean details);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getSiblingLCSClusterNodes(java.lang.String key)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getUserLCSClusterNodes(long userId,
		boolean details) throws PortalException;

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

	/**
	* Deletes all LCS cluster nodes in the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @throws PortalException if an LCS cluster node could not be found in the
	LCS cluster entry
	* @since LCS 0.1
	*/
	public void deleteLCSClusterEntryLCSClusterNodes(long lcsClusterEntryId)
		throws PortalException;
}