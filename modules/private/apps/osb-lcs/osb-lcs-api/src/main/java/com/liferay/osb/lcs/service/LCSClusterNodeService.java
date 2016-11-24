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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for LCSClusterNode. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSClusterNodeServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterNodeServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSClusterNode"}, service = LCSClusterNodeService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSClusterNodeService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSClusterNodeServiceUtil} to access the l c s cluster node remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterNodeServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public LCSClusterNode addLCSClusterNode(java.lang.String siblingKey,
		java.lang.String name, java.lang.String description,
		java.lang.String key, java.lang.String location, int processorCoresTotal)
		throws PortalException;

	public LCSClusterNode addLCSClusterNode(long lcsClusterEntryId,
		java.lang.String name, java.lang.String description, int buildNumber,
		java.lang.String key, java.lang.String location, int processorCoresTotal)
		throws PortalException;

	/**
	* Returns an LCS cluster node matching the key.
	*
	* @param key the portal instance key provided by the licence tool
	* @return an LCS cluster node matching the key or <code>null</code> if no
	matching LCS cluster node was found
	* @throws PortalException if operation was not allowed by the LCS cluster
	        entry membership policy
	* @since LCS 1.0
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode fetchLCSClusterNode(java.lang.String key)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode fetchRandomSiblingLCSClusterNode(java.lang.String key)
		throws PortalException;

	/**
	* Returns the LCS cluster node matching the key.
	*
	* @param key the portal instance key provided by the licence tool
	* @return the LCS cluster node matching the key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(java.lang.String key)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(java.lang.String key,
		boolean details) throws PortalException;

	/**
	* Returns the LCS cluster node (excluding its transient details) with
	* primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @return the LCS cluster node with primary key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(long lcsClusterNodeId)
		throws PortalException;

	/**
	* Returns the LCS cluster node with primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @param details          whether to include transient details in the nodes
	* @return the LCS cluster node with primary key
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSClusterNode getLCSClusterNode(long lcsClusterNodeId,
		boolean details) throws PortalException;

	/**
	* Updates the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of LCS cluster node
	* @param name             the portal instance friendly name
	* @param description      the portal instance description
	* @param location         the portal instance location
	* @return the LCS cluster node
	* @throws PortalException if a matching LCS cluster node could not be
	        found, or new attributes were invalid, or an operation was not
	        allowed by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSClusterNode updateLCSClusterNode(long lcsClusterNodeId,
		java.lang.String name, java.lang.String description,
		java.lang.String location) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns all LCS cluster nodes (excluding their transient details)
	* belonging to the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @return the LCS cluster nodes belonging to the LCS cluster entry
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId) throws PortalException;

	/**
	* Returns all LCS cluster nodes belonging to the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @param details           whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the LCS cluster entry
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSClusterNodeSiblingNodes(
		java.lang.String key) throws PortalException;

	/**
	* Returns all LCS cluster nodes (excluding their transient details)
	* accessible to the user and belonging to the LCS project.
	*
	* @param lcsProjectId the primary key of LCS project
	* @return the LCS cluster nodes belonging to the LCS project and accessible
	to the current user
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(long lcsProjectId)
		throws PortalException;

	/**
	* Returns all LCS cluster nodes accessible to the current user and
	* belonging to the corp project.
	*
	* @param lcsProjectId the primary key of corp project
	* @param details      whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the corp project and
	accessible to the current user
	* @throws PortalException if an operation was not allowed by the LCS
	        cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(
		long lcsProjectId, boolean details) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSClusterNode> getUserLCSClusterNodes(boolean details)
		throws PortalException;

	/**
	* Deletes the LCS cluster node.
	* <p>
	* <p>
	* This method assures that the LCS cluster node connected to LCS is
	* properly disconnected. All NoSQL data is deleted.
	* </p>
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @throws PortalException if an LCS cluster node associated with the LCS
	        cluster entry could not be found or an operation was not allowed
	        by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public void deleteLCSClusterNode(long lcsClusterNodeId)
		throws PortalException;

	public void mergeStatus(java.lang.String key, int status)
		throws PortalException;

	public void updateBuildNumber(java.lang.String key, int buildNumber)
		throws PortalException;

	public void updateBuildNumber(long lcsClusterNodeId, int buildNumber)
		throws PortalException;

	public void verifyLCSClusterEntryLCSClusterNodesPropertiesDifferences(
		java.lang.String key) throws PortalException;

	public void verifyLCSClusterNodeClusterLink(java.lang.String key,
		java.lang.String siblingKeys) throws PortalException;
}