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
import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.osb.lcs.advisor.CommandMessageAdvisor;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.osb.lcs.exception.LCSClusterNodeBuildNumberException;
import com.liferay.osb.lcs.exception.LCSClusterNodeKeyException;
import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException;
import com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.lcs.exception.RequiredLCSClusterNodeNameException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesService;
import com.liferay.osb.lcs.service.base.LCSClusterNodeLocalServiceBaseImpl;
import com.liferay.osb.lcs.storage.StorageManager;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Beslic
 * @see    LCSClusterNodeLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil
 */
@ProviderType
public class LCSClusterNodeLocalServiceImpl
	extends LCSClusterNodeLocalServiceBaseImpl {

	/**
	 * Adds a new LCS cluster node.
	 *
	 * @param  lcsClusterEntryId the primary key of the parent environment
	 * @param  name the portal instance's friendly name
	 * @param  description the portal instance's description
	 * @param  buildNumber the portal instance's build number
	 * @param  key the key of the portal instance provided by the license tool
	 * @param  location the portal instance's physical location
	 * @param  processorCoresTotal
	 * @return the LCS cluster node
	 * @throws PortalException if any LCS cluster node attributes were invalid
	 * @since  LCS 0.1
	 */
	@Override
	public LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			int buildNumber, String key, String location,
			int processorCoresTotal)
		throws PortalException {

		validate(lcsClusterEntryId, name, buildNumber, key, null);

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

		LCSClusterNode node = lcsClusterNodePersistence.create(
			counterLocalService.increment());

		node.setLcsClusterEntryId(lcsClusterEntry.getLcsClusterEntryId());

		if (lcsClusterEntry.isCluster()) {
			node.setInstallationId(lcsClusterEntryId);
		}
		else {
			node.setInstallationId(counterLocalService.increment());
		}

		node.setName(name);
		node.setDescription(description);
		node.setBuildNumber(buildNumber);
		node.setKey(key);
		node.setLocation(location);
		node.setProcessorCoresTotal(processorCoresTotal);

		node = lcsClusterNodePersistence.update(node);

		SubscriptionType subscriptionType = SubscriptionType.valueOf(
			lcsClusterEntry.getSubscriptionType());

		if (subscriptionType == SubscriptionType.UNDEFINED) {
			return node;
		}

		LCSSubscriptionEntry subscriptionEntry =
			lcsSubscriptionEntryLocalService.
				fetchLCSProjectLCSSubscriptionEntry(
					lcsClusterEntry.getLcsProjectId(), processorCoresTotal,
					subscriptionType);

		if (subscriptionEntry == null) {
			throw new NoSuchLCSSubscriptionEntryException(
				_stringAdvisor.concat(
					"No entity exists with the key {lcsProject:",
					lcsClusterEntry.getLcsProjectId(), ", subscriptionType:",
					subscriptionType.name(), ", processorCoresTotal:",
					processorCoresTotal, StringPool.CLOSE_CURLY_BRACE));
		}

		if (!lcsClusterEntry.isElastic()) {
			lcsSubscriptionEntryLocalService.incrementServerUsed(
				lcsClusterEntry.getLcsProjectId(),
				lcsClusterEntry.getSubscriptionType(),
				node.getProcessorCoresTotal());
		}

		return node;
	}

	/**
	 * Deletes all LCS cluster nodes in the LCS cluster entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the LCS cluster entry
	 * @throws PortalException if an LCS cluster node could not be found in the
	 *         LCS cluster entry
	 * @since  LCS 0.1
	 */
	@Override
	public void deleteLCSClusterEntryLCSClusterNodes(long lcsClusterEntryId)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodePersistence.findByLCSClusterEntryId(
				lcsClusterEntryId);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			deleteLCSClusterNode(lcsClusterNode);
		}
	}

	/**
	 * Deletes the LCS cluster node.
	 *
	 * <p>
	 * This method assures that the LCS cluster node connected to LCS is
	 * properly disconnected. All NoSQL data is deleted.
	 * </p>
	 *
	 * @param  lcsClusterNode the LCS cluster node
	 * @return the deleted LCS cluster node
	 * @throws PortalException if a portal exception occurred
	 * @since  LCS 0.1
	 */
	@Override
	public LCSClusterNode deleteLCSClusterNode(LCSClusterNode lcsClusterNode)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.fetchByPrimaryKey(
				lcsClusterNode.getLcsClusterEntryId());

		SubscriptionType subscriptionType = SubscriptionType.UNDEFINED;

		if (lcsClusterEntry != null) {
			subscriptionType = SubscriptionType.valueOf(
				lcsClusterEntry.getSubscriptionType());
		}

		if (subscriptionType == SubscriptionType.UNDEFINED) {
			lcsClusterNode = lcsClusterNodePersistence.remove(lcsClusterNode);

			lcsClusterNodeUptimePersistence.removeByLCSClusterNodeId(
				lcsClusterNode.getLcsClusterNodeId());
		}
		else {
			if (lcsClusterEntry.isElastic()) {
				lcsClusterNode.setArchived(true);

				lcsClusterNode = lcsClusterNodePersistence.update(
					lcsClusterNode);
			}
			else {
				List<LCSSubscriptionEntry> lcsSubscriptionEntries =
					lcsSubscriptionEntryLocalService.
						getLCSProjectLCSSubscriptionEntries(
							lcsClusterEntry.getLcsProjectId(),
							lcsClusterEntry.getSubscriptionType(), null);

				if (!lcsSubscriptionEntries.isEmpty()) {
					lcsSubscriptionEntryLocalService.decrementServerUsed(
						lcsClusterEntry.getLcsProjectId(),
						lcsClusterEntry.getSubscriptionType(),
						lcsClusterNode.getProcessorCoresTotal());
				}

				lcsClusterNode = lcsClusterNodePersistence.remove(
					lcsClusterNode);
			}
		}

		lcsMessageLocalService.deleteLCSClusterNodeLCSMessages(
			lcsClusterNode.getLcsClusterNodeId());

		lcsNotificationLocalService.deleteLCSClusterNodeLCSNotification(
			lcsClusterNode.getLcsClusterNodeId());

		lcsNotificationAuditEntryPersistence.removeByLCSClusterNodeId(
			lcsClusterNode.getLcsClusterNodeId());

		_commandMessageAdvisor.deregister(lcsClusterNode.getKey());

		return lcsClusterNode;
	}

	@Override
	public LCSClusterNode fetchRandomSiblingLCSClusterNode(
			LCSClusterNode lcsClusterNode)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.findByPrimaryKey(
				lcsClusterNode.getLcsClusterEntryId());

		if (!lcsClusterEntry.isCluster()) {
			return null;
		}

		List<LCSClusterNode> lcsClusterNodes = getSiblingLCSClusterNodes(
			lcsClusterNode.getKey());

		if (lcsClusterNodes.isEmpty()) {
			return null;
		}

		return lcsClusterNodes.get(0);
	}

	@Override
	public List<LCSClusterNode> getArchivedLCSClusterNodes(int buildNumber) {
		return lcsClusterNodePersistence.findByBN_A(buildNumber, true);
	}

	@Override
	public List<LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId) {

		return getLCSProjectLCSClusterNodes(userId, lcsProjectId, true, false);
	}

	@Override
	public List<LCSClusterNode> getArchivedLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean details) {

		return getLCSProjectLCSClusterNodes(
			userId, lcsProjectId, true, details);
	}

	/**
	 * Returns all LCS cluster nodes in the LCS cluster entry. The LCS cluster
	 * nodes' transient details are excluded.
	 *
	 * @param  lcsClusterEntryId the primary key of the LCS cluster entry
	 * @return the LCS cluster nodes in the LCS cluster entry
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId) {

		return getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId, false);
	}

	/**
	 * Returns all LCS cluster nodes in the LCS cluster entry.
	 *
	 * @param  lcsClusterEntryId the primary key of the LCS cluster entry
	 * @param  details whether to include the LCS cluster nodes' transient
	 *         details
	 * @return the LCS cluster nodes in the LCS cluster entry
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean details) {

		return getLCSClusterEntryLCSClusterNodes(
			lcsClusterEntryId, false, details);
	}

	/**
	 * Returns the LCS cluster node matching the primary key.
	 *
	 * @param  lcsClusterNodeId the primary key of the LCS cluster node
	 * @param  details whether to include the LCS cluster node's transient
	 *         details
	 * @return the LCS cluster node matching the primary key
	 * @since  LCS 0.1
	 */
	@Override
	public LCSClusterNode getLCSClusterNode(
		long lcsClusterNodeId, boolean details) {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.fetchByPrimaryKey(lcsClusterNodeId);

		if (details) {
			List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

			lcsClusterNodes.add(lcsClusterNode);

			addDetails(lcsClusterNodes);
		}

		return lcsClusterNode;
	}

	/**
	 * Returns the LCS cluster node matching the portal instance key.
	 *
	 * @param  lcsClusterNodeKey the portal instance key provided by the license
	 *         tool
	 * @return the LCS cluster node matching the portal instance key
	 * @since  LCS 0.1
	 */
	@Override
	public LCSClusterNode getLCSClusterNode(String lcsClusterNodeKey) {
		return lcsClusterNodePersistence.fetchByKey(lcsClusterNodeKey);
	}

	@Override
	public LCSClusterNode getLCSClusterNode(String key, boolean details) {
		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.fetchByKey(
			key);

		if (details) {
			List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

			lcsClusterNodes.add(lcsClusterNode);

			addDetails(lcsClusterNodes);
		}

		return lcsClusterNode;
	}

	/**
	 * Returns the LCS cluster nodes that have the specified portal instance
	 * build number.
	 *
	 * @param  buildNumber the portal instance build number
	 * @return the LCS cluster nodes that have the portal instance build number
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSClusterNodes(int buildNumber) {
		return lcsClusterNodePersistence.findByBN_A(buildNumber, false);
	}

	/**
	 * Returns a range of the LCS cluster nodes.
	 *
	 * <p>
	 * This method is useful when paginating results. The start and end values
	 * are not primary keys: they are indices in the result set. Therefore, a
	 * start value of <code>0</code> refers to the first result in the set. The
	 * maximum number of instances returned is the end index minus the start
	 * index. Setting both indices to {@link QueryUtil#ALL_POS} returns the full
	 * result set.
	 * </p>
	 *
	 * @param  start the lower bound of the range
	 * @param  end the upper bound of the range. This is not inclusive.
	 * @param  details whether to include the LCS cluster nodes' transient
	 *         details
	 * @return the range of the LCS cluster nodes
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSClusterNodes(
		int start, int end, boolean details) {

		List<LCSClusterNode> lcsClusterNodes = getLCSClusterNodes(start, end);

		if (details) {
			lcsClusterNodes = addDetails(lcsClusterNodes);
		}

		return lcsClusterNodes;
	}

	@Override
	public List<LCSClusterNode> getLCSClusterNodes(
			String lcsClusterEntryName, String lcsProjectName,
			boolean andOperator, boolean details)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodeFinder.findByLCEN_LPN(
				lcsClusterEntryName, lcsProjectName, andOperator,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (!details) {
			return lcsClusterNodes;
		}

		return addDetails(lcsClusterNodes);
	}

	@Override
	public int getLCSClusterNodesCount(long lcsClusterEntryId) {
		return lcsClusterNodePersistence.countByLCSClusterEntryId(
			lcsClusterEntryId);
	}

	@Override
	public int getLCSClusterNodesCount(long[] lcsClusterEntryIds) {
		return lcsClusterNodePersistence.countByLCSClusterEntryId(
			lcsClusterEntryIds);
	}

	/**
	 * Returns all LCS cluster nodes accessible to the user and belonging to the
	 * corporate project. The LCS cluster nodes' transient details are excluded.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return the LCS cluster nodes accessible to the user and belonging to the
	 *         corporate project
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId) {

		return getLCSProjectLCSClusterNodes(userId, lcsProjectId, false, false);
	}

	/**
	 * Returns all LCS cluster nodes accessible to the user and belonging to the
	 * corporate project, optionally including the LCS nodes' transient details.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @param  details whether to include the LCS cluster nodes' transient
	 *         details
	 * @return the LCS cluster nodes accessible to the user and belonging to the
	 *         corporate project
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean details) {

		return getLCSProjectLCSClusterNodes(
			userId, lcsProjectId, false, details);
	}

	@Override
	public List<LCSClusterNode> getSiblingLCSClusterNodes(String key)
		throws PortalException {

		LCSClusterNode currentLCSClusterNode =
			lcsClusterNodePersistence.findByKey(key);

		long lcsClusterEntryId = currentLCSClusterNode.getLcsClusterEntryId();

		if (lcsClusterNodePersistence.countByLCEI_A(lcsClusterEntryId, false) <=
				1) {

			return Collections.emptyList();
		}

		List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

		for (LCSClusterNode lcsClusterNode :
				lcsClusterNodePersistence.findByLCEI_A(
					lcsClusterEntryId, false)) {

			if (currentLCSClusterNode.getLcsClusterNodeId() ==
					lcsClusterNode.getLcsClusterNodeId()) {

				continue;
			}

			lcsClusterNodes.add(lcsClusterNode);
		}

		return lcsClusterNodes;
	}

	@Override
	public List<LCSClusterNode> getUserLCSClusterNodes(
			long userId, boolean details)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes = lcsClusterNodeFinder.findByU_A(
			userId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (!details) {
			return lcsClusterNodes;
		}

		return addDetails(lcsClusterNodes);
	}

	@Override
	public boolean hasLCSClusterEntryAllInactiveLCSClusterNodes(
		long lcsClusterEntryId) {

		List<LCSClusterNode> lcsClusterNodes =
			getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId, false, true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (!lcsClusterNode.isOffline()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean hasLCSClusterEntryAllLCSClusterNodes(
		long lcsClusterEntryId, int status) {

		List<LCSClusterNode> lcsClusterNodes =
			getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId, false, true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (!lcsClusterNode.hasStatus(status)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns <code>true</code> if any one of the user's corporate projects has
	 * at least one LCS cluster node.
	 *
	 * @param  userId the primary key of the user
	 * @return <code>true</code> if any one of the user's corporate projects has
	 *         at least one LCS cluster node; <code>false</code> otherwise
	 * @since  LCS 1.2
	 */
	@Override
	public boolean hasUserLCSClusterNodes(long userId) {
		List<LCSRole> lcsRoles = lcsRolePersistence.findByUserId(userId);

		Set<Long> lcsClusterEntryIds = new HashSet<>();

		for (LCSRole lcsRole : lcsRoles) {
			if (lcsRole.getLcsClusterEntryId() != 0) {
				lcsClusterEntryIds.add(lcsRole.getLcsClusterEntryId());

				continue;
			}

			List<LCSClusterEntry> lcsClusterEntries =
				lcsClusterEntryPersistence.findByLCSProjectId(
					lcsRole.getLcsProjectId());

			for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
				lcsClusterEntryIds.add(lcsClusterEntry.getLcsClusterEntryId());
			}
		}

		if (lcsClusterEntryIds.isEmpty() ||
			(lcsClusterNodePersistence.countByLCSClusterEntryId(
				ArrayUtil.toLongArray(lcsClusterEntryIds)) == 0)) {

			return false;
		}

		return true;
	}

	@Override
	public LCSClusterNode mergeStatus(
		LCSClusterNode lcsClusterNode, int status) {

		LCSClusterNodeStatus[] lcsClusterNodeStatuses =
			LCSClusterNodeStatus.getLCSClusterNodeStatuses(status);

		int newStatus = lcsClusterNodeStatuses[0].merge(
			_lcsClusterNodeDetailsService.getStatus(lcsClusterNode.getKey()));

		_lcsClusterNodeDetailsService.updateStatus(
			lcsClusterNode.getKey(), newStatus);

		lcsClusterNode.setStatus(newStatus);

		return updateLCSClusterNode(lcsClusterNode);
	}

	@Override
	public LCSClusterNode updateLCSClusterNode(
			long lcsClusterNodeId, String name, String description,
			int buildNumber, String location)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

		validate(
			lcsClusterNode.getLcsClusterEntryId(), name, buildNumber,
			lcsClusterNode.getKey(), lcsClusterNode);

		lcsClusterNode.setName(name);
		lcsClusterNode.setDescription(description);
		lcsClusterNode.setBuildNumber(buildNumber);
		lcsClusterNode.setLocation(location);

		return updateLCSClusterNode(lcsClusterNode);
	}

	@Override
	public LCSClusterNode updateStatus(LCSClusterNode lcsClusterNode)
		throws PortalException {

		int status = _lcsClusterNodeDetailsService.getStatus(
			lcsClusterNode.getKey());

		lcsClusterNode.setStatus(status);

		lcsClusterNode = updateLCSClusterNode(lcsClusterNode);

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryLocalService.getLCSClusterEntry(
				lcsClusterNode.getLcsClusterEntryId());

		LCSProject lcsProject = lcsProjectLocalService.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		if (lcsProject.isSubscriptionActive()) {
			lcsSubscriptionEntryLocalService.
				reorganizeLCSSubsriptionsServersUsed(
					lcsProject.getLcsProjectId());
		}

		if (lcsClusterNode.hasStatus(LCSClusterNodeStatus.ACTIVE.getStatus())) {
			return lcsClusterNode;
		}

		if (lcsClusterNode.hasStatus(
				LCSClusterNodeStatus.SERVER_MANUALLY_SHUT_DOWN.getStatus())) {

			lcsMembersLocalService.fireLCSEvent(
				lcsClusterNode.getKey(), LCSEventType.SERVER_MANUALLY_SHUTDOWN);
		}
		else {
			lcsMembersLocalService.fireLCSEvent(
				lcsClusterNode.getKey(),
				LCSEventType.SERVER_UNEXPECTEDLY_SHUTDOWN);
		}

		return lcsClusterNode;
	}

	/**
	 * Adds transient data to each LCS cluster node. Transient data originates
	 * from the NoSQL database.
	 *
	 * @param  lcsClusterNodes the LCS cluster nodes to add transient data to
	 * @return the LCS cluster nodes
	 * @since  LCS 0.1
	 */
	protected List<LCSClusterNode> addDetails(
		List<LCSClusterNode> lcsClusterNodes) {

		List<String> keys = new ArrayList<>();

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			keys.add(lcsClusterNode.getKey());
		}

		List<LCSClusterNodeDetails> lcsClusterNodeDetailsList =
			(List<LCSClusterNodeDetails>)_lcsClusterNodeDetailsService.
				getLCSClusterNodeDetailsList(keys);
		List<LCSClusterNodePatches> lcsClusterNodePatchesList =
			(List<LCSClusterNodePatches>)_lcsClusterNodePatchesService.
				getLCSClusterNodesPatchesList(keys);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			String key = lcsClusterNode.getKey();

			for (LCSClusterNodeDetails lcsClusterNodeDetails :
					lcsClusterNodeDetailsList) {

				if (key.equals(lcsClusterNodeDetails.getKey())) {
					lcsClusterNode.setConfigurationModifiedDate(
						lcsClusterNodeDetails.getConfigurationModifiedDate());
					lcsClusterNode.setPatchingToolVersion(
						lcsClusterNodeDetails.getPatchingToolVersion());
					lcsClusterNode.setPortalEdition(
						lcsClusterNodeDetails.getPortalEdition());
					lcsClusterNode.setStatus(lcsClusterNodeDetails.getStatus());

					break;
				}
			}

			for (LCSClusterNodePatches lcsClusterNodePatches :
					lcsClusterNodePatchesList) {

				if (key.equals(lcsClusterNodePatches.getKey())) {
					lcsClusterNode.setPatchIdsStatuses(
						lcsClusterNodePatches.getPatchIdsStatuses());

					List<String> installablePatchNames = new ArrayList<>();

					for (String patchId :
							lcsClusterNodePatches.getInstallablePatchIds()) {

						installablePatchNames.add("fix-pack-" + patchId);
					}

					lcsClusterNode.setInstallablePatchNames(
						installablePatchNames);

					break;
				}
			}
		}

		return lcsClusterNodes;
	}

	protected List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
		long lcsClusterEntryId, boolean archived, boolean details) {

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodePersistence.findByLCEI_A(lcsClusterEntryId, archived);

		if (lcsClusterNodes.isEmpty()) {
			return lcsClusterNodes;
		}

		if (!details) {
			return lcsClusterNodes;
		}

		return addDetails(lcsClusterNodes);
	}

	protected List<LCSClusterNode> getLCSProjectLCSClusterNodes(
		long userId, long lcsProjectId, boolean archived, boolean details) {

		long[] lcsClusterEntryIds =
			lcsClusterEntryLocalService.getUserLCSClusterEntryIds(
				userId, lcsProjectId);

		if (lcsClusterEntryIds.length == 0) {
			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSClusterNode.class, this.getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"lcsClusterEntryId", ArrayUtil.toArray(lcsClusterEntryIds)));

		if (archived) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", true));
		}
		else {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", false));
		}

		List<LCSClusterNode> lcsClusterNodes =
			lcsClusterNodeLocalService.dynamicQuery(dynamicQuery);

		if (details) {
			lcsClusterNodes = addDetails(lcsClusterNodes);
		}

		return lcsClusterNodes;
	}

	/**
	 * Validates the LCS cluster node's attributes.
	 *
	 * <p>
	 * This method validates the portal instance's name, build number, and key.
	 * An environment's portal instance names must be unique. If new portal
	 * instance attributes are validated or an existing instance is moved to a
	 * new environment, this method checks for the presence of that instance's
	 * name to ensure its uniqueness.
	 * </p>
	 *
	 * @param  lcsClusterEntryId primary key of the LCS cluster node's parent
	 *         environment
	 * @param  name the portal instance's friendly name
	 * @param  buildNumber the portal instance's build number
	 * @param  key the portal instance's key provided by the license tool
	 * @param  oldLcsClusterNode the portal instance's former parent
	 *         environment. This is optional.
	 * @throws PortalException if the expected LCS cluster environment could not
	 *         be found, the name was <code>null</code>, the build number was
	 *         not greater than zero, the key was <code>null</code>, or there
	 *         was already an LCS cluster node in the parent LCS cluster entry
	 *         with the same name
	 * @since  LCS 0.1
	 */
	protected void validate(
			long lcsClusterEntryId, String name, int buildNumber, String key,
			LCSClusterNode oldLcsClusterNode)
		throws PortalException {

		if (lcsClusterEntryId <= 0) {
			throw new NoSuchLCSClusterEntryException();
		}

		if (Validator.isNull(name)) {
			throw new RequiredLCSClusterNodeNameException();
		}

		if (buildNumber <= 0) {
			throw new LCSClusterNodeBuildNumberException();
		}

		if (Validator.isNull(key)) {
			throw new LCSClusterNodeKeyException();
		}

		if (oldLcsClusterNode != null) {
			if (StringUtil.equalsIgnoreCase(
					name, oldLcsClusterNode.getName())) {

				return;
			}
		}

		int lcsClusterEntryLCSClusterNodesCount =
			lcsClusterNodePersistence.countByLCEI_N_A(
				lcsClusterEntryId, name, false);

		if (lcsClusterEntryLCSClusterNodesCount > 0) {
			throw new DuplicateLCSClusterNodeNameException();
		}
	}

	@ServiceReference(type = CommandMessageAdvisor.class)
	private CommandMessageAdvisor _commandMessageAdvisor;

	@ServiceReference(type = LCSClusterNodeDetailsService.class)
	private LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;

	@ServiceReference(type = LCSClusterNodePatchesService.class)
	private LCSClusterNodePatchesService _lcsClusterNodePatchesService;

	@ServiceReference(type = StorageManager.class)
	private StorageManager _storageManager;

	@ServiceReference(type = StringAdvisor.class)
	private StringAdvisor _stringAdvisor;

}