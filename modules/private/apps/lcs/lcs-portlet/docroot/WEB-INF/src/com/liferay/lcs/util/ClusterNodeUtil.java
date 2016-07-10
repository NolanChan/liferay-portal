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

package com.liferay.lcs.util;

import com.liferay.lcs.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.advisor.InstallationEnvironmentAdvisorFactory;
import com.liferay.lcs.rest.LCSClusterNode;
import com.liferay.lcs.rest.LCSClusterNodeServiceUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class ClusterNodeUtil {

	public static Map<String, Object> getClusterNodeInfo() {
		if (_log.isDebugEnabled()) {
			_log.debug("Get cluster node information");
		}

		Map<String, Object> clusterNodeInfo = new HashMap<>();

		try {
			clusterNodeInfo.put("key", KeyGeneratorUtil.getKey());

			if (LCSUtil.getCredentialsStatus() == LCSUtil.CREDENTIALS_SET) {
				LCSUtil.setUpJSONWebServiceClientCredentials();

				clusterNodeInfo.put(
					"registered", LCSUtil.isLCSClusterNodeRegistered());
			}
			else {
				clusterNodeInfo.put("registered", false);
			}

			clusterNodeInfo.put("ready", LCSConnectionManagerUtil.isReady());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Cluster node " + MapUtil.toString(clusterNodeInfo));
		}

		return clusterNodeInfo;
	}

	public static List<Map<String, Object>> getClusterNodeInfos()
		throws Exception {

		List<Map<String, Object>> clusterNodeInfos = new ArrayList<>();

		try {
			ClusterNode localClusterNode =
				ClusterExecutorUtil.getLocalClusterNode();

			String localClusterNodeId = localClusterNode.getClusterNodeId();

			List<ClusterNode> clusterNodes =
				ClusterExecutorUtil.getClusterNodes();

			for (ClusterNode clusterNode : clusterNodes) {
				String clusterNodeId = clusterNode.getClusterNodeId();

				if (clusterNodeId.equals(localClusterNodeId)) {
					continue;
				}

				Map<String, Object> clusterNodeInfo = new HashMap<>();

				if (_hasClusterNodeLCSPortletServletContext(clusterNodeId)) {
					clusterNodeInfo = _getClusterNodeInfo(clusterNodeId);
				}
				else {
					clusterNodeInfo.put("lcsPortletMissing", null);
					clusterNodeInfo.put("registered", false);
				}

				clusterNodeInfo.put("clusterNodeId", clusterNodeId);

				clusterNodeInfos.add(clusterNodeInfo);
			}
		}
		catch (ClassNotFoundException cnfe) {
			if (_log.isDebugEnabled()) {
				_log.debug(cnfe.getMessage(), cnfe);
			}
		}

		return clusterNodeInfos;
	}

	public static List<String> getRegisteredClusterNodeKeys() throws Exception {
		List<String> clusterNodeKeys = new ArrayList<>();

		if (!ClusterExecutorUtil.isEnabled()) {
			return clusterNodeKeys;
		}

		List<Map<String, Object>> clusterNodeInfos = getClusterNodeInfos();

		for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
			if (GetterUtil.getBoolean(clusterNodeInfo.get("registered"))) {
				clusterNodeKeys.add((String)clusterNodeInfo.get("key"));
			}
		}

		return clusterNodeKeys;
	}

	public static String registerClusterNode(long lcsClusterEntryId)
		throws Exception {

		return registerClusterNode(
			lcsClusterEntryId, _generateLCSClusterNodeName(), StringPool.BLANK,
			StringPool.BLANK);
	}

	public static String registerClusterNode(
			long lcsClusterEntryId, String name, String description,
			String location)
		throws Exception {

		InstallationEnvironmentAdvisor installationEnvironmentAdvisor =
			InstallationEnvironmentAdvisorFactory.getInstance();

		LCSClusterNode lcsClusterNode =
			LCSClusterNodeServiceUtil.addLCSClusterNode(
				lcsClusterEntryId, name, description,
				ReleaseInfo.getBuildNumber(), KeyGeneratorUtil.getKey(),
				location,
				installationEnvironmentAdvisor.getProcessorCoresTotal());

		LCSUtil.sendServiceAvailabilityNotification(
			LCSPortletState.NO_SUBSCRIPTION);

		return lcsClusterNode.getKey();
	}

	public static void restartPosts(boolean applyToSiblingClusterNodes)
		throws Exception {

		stopPosts(applyToSiblingClusterNodes);

		try {
			Thread.sleep(5000);
		}
		catch (Exception e) {
		}

		startPosts(applyToSiblingClusterNodes);
	}

	public static void startPosts(boolean applyToSiblingClusterNodes)
		throws Exception {

		LCSConnectionManagerUtil.start();

		if (!applyToSiblingClusterNodes || !ClusterExecutorUtil.isEnabled()) {
			return;
		}

		_invokeOnSiblingClusterNodes(_startPostsMethodKey);
	}

	public static void stopPosts(boolean applyToSiblingClusterNodes)
		throws Exception {

		LCSConnectionManagerUtil.stop();

		if (!applyToSiblingClusterNodes || !ClusterExecutorUtil.isEnabled()) {
			return;
		}

		_invokeOnSiblingClusterNodes(_stopPostsMethodKey);
	}

	private static String _generateLCSClusterNodeName() {
		return LicenseManagerUtil.getHostName() + StringPool.DASH +
			System.currentTimeMillis();
	}

	private static Map<String, Object> _getClusterNodeInfo(String clusterNodeId)
		throws Exception {

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			_getClusterNodeInfoMethodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		ClusterNodeResponses clusterNodeResponses = futureClusterResponses.get(
			20000, TimeUnit.MILLISECONDS);

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNodeId);

		return (Map<String, Object>)clusterNodeResponse.getResult();
	}

	private static String _getSiblingKey() throws Exception {
		List<Map<String, Object>> clusterNodeInfos = getClusterNodeInfos();

		for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
			if (GetterUtil.getBoolean(clusterNodeInfo.get("registered"))) {
				return (String)clusterNodeInfo.get("key");
			}
		}

		return null;
	}

	private static List<String> _getUnregisteredClusterNodeIds()
		throws Exception {

		List<String> unregisteredClusterNodeIds = new ArrayList<>();

		List<Map<String, Object>> clusterNodeInfos = getClusterNodeInfos();

		for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
			if (clusterNodeInfo.containsKey("lcsPortletMissing")) {
				continue;
			}

			boolean registered = (Boolean)clusterNodeInfo.get("registered");

			if (!registered) {
				String clusterNodeId = (String)clusterNodeInfo.get(
					"clusterNodeId");

				unregisteredClusterNodeIds.add(clusterNodeId);
			}
		}

		return unregisteredClusterNodeIds;
	}

	private static boolean _hasClusterNodeLCSPortletServletContext(
			String clusterNodeId)
		throws Exception {

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			_containsKeyMethodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		ClusterNodeResponses clusterNodeResponses = futureClusterResponses.get(
			20000, TimeUnit.MILLISECONDS);

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNodeId);

		return (Boolean)clusterNodeResponse.getResult();
	}

	private static void _invokeOnSiblingClusterNodes(MethodKey remoteMethodKey)
		throws Exception {

		ClusterNode localClusterNode =
			ClusterExecutorUtil.getLocalClusterNode();

		String localClusterNodeId = localClusterNode.getClusterNodeId();

		List<String> registeredClusterNodeIds = new ArrayList<>();

		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		if (_log.isDebugEnabled()) {
			_log.debug("Filter registered cluster nodes");
		}

		for (ClusterNode clusterNode : clusterNodes) {
			String clusterNodeId = clusterNode.getClusterNodeId();

			if (!clusterNodeId.equals(localClusterNodeId)) {
				Map<String, Object> clusterNodeInfo = _getClusterNodeInfo(
					clusterNodeId);

				if ((Boolean)clusterNodeInfo.get("registered")) {
					registeredClusterNodeIds.add(clusterNodeId);
				}
			}
		}

		MethodHandler remoteMethodHandler = new MethodHandler(remoteMethodKey);

		for (String clusterNodeId : registeredClusterNodeIds) {
			ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
				remoteMethodHandler, clusterNodeId);

			ClusterExecutorUtil.execute(clusterRequest);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Invoked " + remoteMethodKey.getMethodName() +
						" on cluster node " + clusterNodeId);
			}
		}
	}

	@SuppressWarnings("unused")
	private static void _restartPosts() {
		if (_log.isDebugEnabled()) {
			_log.debug("Restart posting data from this cluster node");
		}

		try {
			LCSConnectionManagerUtil.restart();

			if (_log.isDebugEnabled()) {
				_log.debug("Restarted posts");
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@SuppressWarnings("unused")
	private static void _startPosts() {
		if (_log.isDebugEnabled()) {
			_log.debug("Start posting data from this cluster node");
		}

		try {
			LCSUtil.setUpJSONWebServiceClientCredentials();

			LCSConnectionManagerUtil.start();

			if (_log.isDebugEnabled()) {
				_log.debug("Started posts");
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@SuppressWarnings("unused")
	private static void _stopPosts() {
		if (_log.isDebugEnabled()) {
			_log.debug("Stop posting data from this cluster node");
		}

		try {
			LCSConnectionManagerUtil.stop();

			if (_log.isDebugEnabled()) {
				_log.debug("Stopped posts");
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClusterNodeUtil.class);

	private static final MethodHandler _containsKeyMethodHandler =
		new MethodHandler(
			new MethodKey(
				ServletContextPool.class.getName(), "containsKey",
				String.class),
			"lcs-portlet");
	private static final MethodHandler _getClusterNodeInfoMethodHandler =
		new MethodHandler(
			new MethodKey(ClusterNodeUtil.class, "getClusterNodeInfo"));
	private static final MethodKey _restartPostsMethodKey = new MethodKey(
		ClusterNodeUtil.class, "_restartPosts");
	private static final MethodKey _startPostsMethodKey = new MethodKey(
		ClusterNodeUtil.class, "_startPosts");
	private static final MethodKey _stopPostsMethodKey = new MethodKey(
		ClusterNodeUtil.class, "_stopPosts");

}