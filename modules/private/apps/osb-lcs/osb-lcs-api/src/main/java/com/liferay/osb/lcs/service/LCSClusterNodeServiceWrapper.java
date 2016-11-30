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
 * Provides a wrapper for {@link LCSClusterNodeService}.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeService
 * @generated
 */
@ProviderType
public class LCSClusterNodeServiceWrapper implements LCSClusterNodeService,
	ServiceWrapper<LCSClusterNodeService> {
	public LCSClusterNodeServiceWrapper(
		LCSClusterNodeService lcsClusterNodeService) {
		_lcsClusterNodeService = lcsClusterNodeService;
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		java.lang.String siblingKey, java.lang.String name,
		java.lang.String description, java.lang.String key,
		java.lang.String location, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.addLCSClusterNode(siblingKey, name,
			description, key, location, processorCoresTotal);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode addLCSClusterNode(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, int buildNumber, java.lang.String key,
		java.lang.String location, int processorCoresTotal)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.addLCSClusterNode(lcsClusterEntryId,
			name, description, buildNumber, key, location, processorCoresTotal);
	}

	/**
	* Deletes the LCS cluster node.
	*
	* <p>
	* <p> This method assures that the LCS cluster node connected to LCS is
	* properly disconnected. All NoSQL data is deleted.
	* </p>
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @throws PortalException if an LCS cluster node associated with the LCS
	cluster entry could not be found or an operation was not allowed
	by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode deleteLCSClusterNode(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.deleteLCSClusterNode(lcsClusterNodeId);
	}

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
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode fetchLCSClusterNode(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.fetchLCSClusterNode(key);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode fetchRandomSiblingLCSClusterNode(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.fetchRandomSiblingLCSClusterNode(key);
	}

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
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterNode(key);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		java.lang.String key, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterNode(key, details);
	}

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
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		long lcsClusterNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterNode(lcsClusterNodeId);
	}

	/**
	* Returns the LCS cluster node with primary key.
	*
	* @param lcsClusterNodeId the primary key of the LCS cluster node
	* @param details whether to include transient details in the nodes
	* @return the LCS cluster node with primary key
	* @throws PortalException if an LCS cluster node associated with the LCS
	cluster entry could not be found or an operation was not allowed
	by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode getLCSClusterNode(
		long lcsClusterNodeId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterNode(lcsClusterNodeId,
			details);
	}

	/**
	* Updates the LCS cluster node.
	*
	* @param lcsClusterNodeId the primary key of LCS cluster node
	* @param name the portal instance friendly name
	* @param description the portal instance description
	* @param location the portal instance location
	* @return the LCS cluster node
	* @throws PortalException if a matching LCS cluster node could not be
	found, or new attributes were invalid, or an operation was not
	allowed by the LCS cluster entry membership policy
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSClusterNode updateLCSClusterNode(
		long lcsClusterNodeId, java.lang.String name,
		java.lang.String description, java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.updateLCSClusterNode(lcsClusterNodeId,
			name, description, location);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterNodeService.getOSGiServiceIdentifier();
	}

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
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);
	}

	/**
	* Returns all LCS cluster nodes belonging to the LCS cluster entry.
	*
	* @param lcsClusterEntryId the primary key of the LCS cluster entry
	* @param details whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the LCS cluster entry
	* @throws PortalException if an operation was not allowed by the LCS
	cluster entry membership policy
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId,
			details);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSClusterNodeSiblingNodes(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSClusterNodeSiblingNodes(key);
	}

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
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSProjectLCSClusterNodes(lcsProjectId);
	}

	/**
	* Returns all LCS cluster nodes accessible to the current user and
	* belonging to the corp project.
	*
	* @param lcsProjectId the primary key of corp project
	* @param details whether to include transient details in the nodes
	* @return the LCS cluster nodes belonging to the corp project and
	accessible to the current user
	* @throws PortalException if an operation was not allowed by the LCS
	cluster entry membership policy
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getLCSProjectLCSClusterNodes(
		long lcsProjectId, boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getLCSProjectLCSClusterNodes(lcsProjectId,
			details);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNode> getUserLCSClusterNodes(
		boolean details)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeService.getUserLCSClusterNodes(details);
	}

	@Override
	public void mergeStatus(java.lang.String key, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeService.mergeStatus(key, status);
	}

	@Override
	public void updateBuildNumber(java.lang.String key, int buildNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeService.updateBuildNumber(key, buildNumber);
	}

	@Override
	public void updateBuildNumber(long lcsClusterNodeId, int buildNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeService.updateBuildNumber(lcsClusterNodeId, buildNumber);
	}

	@Override
	public void verifyLCSClusterNodeClusterLink(java.lang.String key,
		java.lang.String siblingKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeService.verifyLCSClusterNodeClusterLink(key, siblingKeys);
	}

	@Override
	public LCSClusterNodeService getWrappedService() {
		return _lcsClusterNodeService;
	}

	@Override
	public void setWrappedService(LCSClusterNodeService lcsClusterNodeService) {
		_lcsClusterNodeService = lcsClusterNodeService;
	}

	private LCSClusterNodeService _lcsClusterNodeService;
}