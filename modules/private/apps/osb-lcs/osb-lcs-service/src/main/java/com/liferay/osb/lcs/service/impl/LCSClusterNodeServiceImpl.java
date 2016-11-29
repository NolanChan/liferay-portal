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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.service.base.LCSClusterNodeServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the remote service for accessing, adding, deleting, updating and
 * validating LCS cluster nodes. Its methods include permission checks.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 0.1
 */
@ProviderType
public class LCSClusterNodeServiceImpl extends LCSClusterNodeServiceBaseImpl {

	@Override
	public LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			int buildNumber, String key, String location,
			int processorCoresTotal)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId,
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterNodeLocalService.addLCSClusterNode(
			lcsClusterEntryId, name, description, buildNumber, key, location,
			processorCoresTotal);
	}

	@Override
	public LCSClusterNode addLCSClusterNode(
			String siblingKey, String name, String description, String key,
			String location, int processorCoresTotal)
		throws PortalException {

		LCSClusterNode siblingLCSClusterNode = getLCSClusterNode(siblingKey);

		LCSClusterEntryPermission.check(
			getPermissionChecker(),
			siblingLCSClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterNodeLocalService.addLCSClusterNode(
			siblingLCSClusterNode.getLcsClusterEntryId(), name, description,
			siblingLCSClusterNode.getBuildNumber(), key, location,
			processorCoresTotal);
	}

	/**
	 * Deletes the LCS cluster node.
	 *
	 * <p>
	 * <p> This method assures that the LCS cluster node connected to LCS is
	 * properly disconnected. All NoSQL data is deleted.
	 * </p>
	 *
	 * @param  lcsClusterNodeId the primary key of the LCS cluster node
	 * @throws PortalException if an LCS cluster node associated with the LCS
	 *         cluster entry could not be found or an operation was not allowed
	 *         by the LCS cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public void deleteLCSClusterNode(long lcsClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		lcsClusterNodeLocalService.deleteLCSClusterNode(lcsClusterNode);
	}

	/**
	 * Returns an LCS cluster node matching the key.
	 *
	 * @param  key the portal instance key provided by the licence tool
	 * @return an LCS cluster node matching the key or <code>null</code> if no
	 *         matching LCS cluster node was found
	 * @throws PortalException if operation was not allowed by the LCS cluster
	 *         entry membership policy
	 * @since  LCS 1.0
	 */
	@Override
	public LCSClusterNode fetchLCSClusterNode(String key)
		throws PortalException {

		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.fetchByKey(
			key);

		if (lcsClusterNode != null) {
			LCSClusterEntryPermission.check(
				getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
				OSBLCSActionKeys.VIEW);
		}

		return lcsClusterNode;
	}

	@Override
	public LCSClusterNode fetchRandomSiblingLCSClusterNode(String key)
		throws PortalException {

		LCSClusterNode lcsClusterNode = fetchLCSClusterNode(key);

		return lcsClusterNodeLocalService.fetchRandomSiblingLCSClusterNode(
			lcsClusterNode);
	}

	/**
	 * Returns all LCS cluster nodes (excluding their transient details)
	 * belonging to the LCS cluster entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the LCS cluster entry
	 * @return the LCS cluster nodes belonging to the LCS cluster entry
	 * @throws PortalException if an operation was not allowed by the LCS
	 *         cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
			lcsClusterEntryId);
	}

	/**
	 * Returns all LCS cluster nodes belonging to the LCS cluster entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the LCS cluster entry
	 * @param  details whether to include transient details in the nodes
	 * @return the LCS cluster nodes belonging to the LCS cluster entry
	 * @throws PortalException if an operation was not allowed by the LCS
	 *         cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId, boolean details)
		throws PortalException {

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterEntryId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
			lcsClusterEntryId, details);
	}

	/**
	 * Returns the LCS cluster node (excluding its transient details) with
	 * primary key.
	 *
	 * @param  lcsClusterNodeId the primary key of the LCS cluster node
	 * @return the LCS cluster node with primary key
	 * @throws PortalException if an LCS cluster node associated with the LCS
	 *         cluster entry could not be found or an operation was not allowed
	 *         by the LCS cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterNode getLCSClusterNode(long lcsClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsClusterNode;
	}

	/**
	 * Returns the LCS cluster node with primary key.
	 *
	 * @param  lcsClusterNodeId the primary key of the LCS cluster node
	 * @param  details whether to include transient details in the nodes
	 * @return the LCS cluster node with primary key
	 * @throws PortalException if an LCS cluster node associated with the LCS
	 *         cluster entry could not be found or an operation was not allowed
	 *         by the LCS cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterNode getLCSClusterNode(
			long lcsClusterNodeId, boolean details)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodeLocalService.getLCSClusterNode(
				lcsClusterNodeId, details);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsClusterNode;
	}

	/**
	 * Returns the LCS cluster node matching the key.
	 *
	 * @param  key the portal instance key provided by the licence tool
	 * @return the LCS cluster node matching the key
	 * @throws PortalException if an LCS cluster node associated with the LCS
	 *         cluster entry could not be found or an operation was not allowed
	 *         by the LCS cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@Override
	public LCSClusterNode getLCSClusterNode(String key) throws PortalException {
		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.findByKey(
			key);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsClusterNode;
	}

	@Override
	public LCSClusterNode getLCSClusterNode(String key, boolean details)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodeLocalService.getLCSClusterNode(key, details);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.VIEW);

		return lcsClusterNode;
	}

	@Override
	public List<LCSClusterNode> getLCSClusterNodeSiblingNodes(String key)
		throws PortalException {

		LCSClusterNode lcsClusterNode = getLCSClusterNode(key);

		return lcsClusterNodeLocalService.getSiblingLCSClusterNodes(
			lcsClusterNode.getKey());
	}

	/**
	 * Returns all LCS cluster nodes (excluding their transient details)
	 * accessible to the user and belonging to the LCS project.
	 *
	 * @param  lcsProjectId the primary key of LCS project
	 * @return the LCS cluster nodes belonging to the LCS project and accessible
	 *         to the current user
	 * @throws PortalException if an operation was not allowed by the LCS
	 *         cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(long lcsProjectId)
		throws PortalException {

		return lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(
			getUserId(), lcsProjectId, false);
	}

	/**
	 * Returns all LCS cluster nodes accessible to the current user and
	 * belonging to the corp project.
	 *
	 * @param  lcsProjectId the primary key of corp project
	 * @param  details whether to include transient details in the nodes
	 * @return the LCS cluster nodes belonging to the corp project and
	 *         accessible to the current user
	 * @throws PortalException if an operation was not allowed by the LCS
	 *         cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(
			long lcsProjectId, boolean details)
		throws PortalException {

		return lcsClusterNodeLocalService.getLCSProjectLCSClusterNodes(
			getUserId(), lcsProjectId, details);
	}

	@Override
	public List<LCSClusterNode> getUserLCSClusterNodes(boolean details)
		throws PortalException {

		return lcsClusterNodeLocalService.getUserLCSClusterNodes(
			getUserId(), details);
	}

	@Override
	public void mergeStatus(String key, int status) throws PortalException {
		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.findByKey(
			key);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		lcsClusterNodeLocalService.mergeStatus(lcsClusterNode, status);
	}

	@Override
	public void updateBuildNumber(long lcsClusterNodeId, int buildNumber)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		lcsClusterNodeLocalService.updateLCSClusterNode(
			lcsClusterNodeId, lcsClusterNode.getName(),
			lcsClusterNode.getDescription(), buildNumber,
			lcsClusterNode.getLocation());
	}

	@Override
	public void updateBuildNumber(String key, int buildNumber)
		throws PortalException {

		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.findByKey(
			key);

		updateBuildNumber(lcsClusterNode.getLcsClusterNodeId(), buildNumber);
	}

	/**
	 * Updates the LCS cluster node.
	 *
	 * @param  lcsClusterNodeId the primary key of LCS cluster node
	 * @param  name the portal instance friendly name
	 * @param  description the portal instance description
	 * @param  location the portal instance location
	 * @return the LCS cluster node
	 * @throws PortalException if a matching LCS cluster node could not be
	 *         found, or new attributes were invalid, or an operation was not
	 *         allowed by the LCS cluster entry membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSClusterNode updateLCSClusterNode(
			long lcsClusterNodeId, String name, String description,
			String location)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		LCSClusterEntryPermission.check(
			getPermissionChecker(), lcsClusterNode.getLcsClusterEntryId(),
			OSBLCSActionKeys.MANAGE_ENTRY);

		return lcsClusterNodeLocalService.updateLCSClusterNode(
			lcsClusterNodeId, name, description,
			lcsClusterNode.getBuildNumber(), location);
	}

	@Override
	public void verifyLCSClusterNodeClusterLink(String key, String siblingKeys)
		throws PortalException {

		User user = getUser();

		if (!StringUtil.equalsIgnoreCase(
				user.getEmailAddress(), "system@liferay.com")) {

			throw new PrincipalException();
		}

		LCSClusterNode lcsClusterNode = getLCSClusterNode(key, true);

		List<LCSClusterNode> siblingLCSClusterNodes =
			lcsClusterNodeLocalService.getLCSClusterEntryLCSClusterNodes(
				lcsClusterNode.getLcsClusterEntryId(), true);

		List<String> missingSiblingKeys = new ArrayList<>();

		for (LCSClusterNode siblingLCSClusterNode : siblingLCSClusterNodes) {
			if (LCSClusterNodeStatus.isActive(
					siblingLCSClusterNode.getStatus())) {

				if (siblingKeys.contains(siblingLCSClusterNode.getKey())) {
					continue;
				}

				missingSiblingKeys.add(siblingLCSClusterNode.getKey());
			}
		}

		missingSiblingKeys.remove(key);

		int status = lcsClusterNode.getStatus();

		boolean lcsClusterNodeClusterLinkHealthy =
			LCSClusterNodeStatus.LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY.
				hasStatus(status);

		if (missingSiblingKeys.isEmpty()) {
			if (!lcsClusterNodeClusterLinkHealthy) {
				_lcsClusterNodeDetailsService.updateStatus(
					key,
					LCSClusterNodeStatus.LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY.
						merge(status));
			}

			return;
		}
		else if (!lcsClusterNodeClusterLinkHealthy) {
			return;
		}

		_lcsClusterNodeDetailsService.updateStatus(
			key,
			LCSClusterNodeStatus.LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY.reset(
				status));

		Message message = new Message();

		message.put("key", key);
		message.put("siblingKeys", missingSiblingKeys);

		message.setPayload(LCSEventType.LCS_CLUSTER_NODE_CLUSTER_LINK_FAILED);

		MessageBusUtil.sendMessage("liferay/osb_lcs_events", message);
	}

	@ServiceReference(type = LCSClusterNodeDetailsService.class)
	private LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;

}