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

package com.liferay.osb.lcs.util;

import com.liferay.osb.lcs.constants.OSBLCSWebKeys;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeUtil {

	public static List<LCSClusterNode> getLCSClusterNodes(
			HttpServletRequest request, long layoutLCSClusterEntryId,
			long layoutLCSClusterNodeId, long layoutLCSProjectId)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes = null;

		if ((layoutLCSClusterEntryId <= 0) && (layoutLCSClusterNodeId <= 0) &&
			(layoutLCSProjectId > 0)) {

			lcsClusterNodes = getLCSProjectLCSClusterNodes(
				request, layoutLCSProjectId);
		}
		else if ((layoutLCSClusterEntryId > 0) &&
				 (layoutLCSClusterNodeId <= 0)) {

			lcsClusterNodes = getLCSClusterEntryLCSClusterNodes(
				request, layoutLCSClusterEntryId);
		}
		else if (layoutLCSClusterNodeId > 0) {
			lcsClusterNodes = new ArrayList<>();

			LCSClusterNode lcsClusterNode = getLCSClusterNode(
				request, layoutLCSClusterNodeId);

			lcsClusterNodes.add(lcsClusterNode);
		}
		else {
			lcsClusterNodes = getLCSProjectsLCSClusterNodes(request);
		}

		return lcsClusterNodes;
	}

	public static List<LCSClusterNode> getLCSProjectLCSClusterNodes(
			HttpServletRequest request, long lcsProjectId)
		throws PortalException {

		String key = OSBLCSWebKeys.CORP_ENTRY_LCS_CLUSTER_NODES.concat(
			String.valueOf(lcsProjectId));

		List<LCSClusterNode> lcsClusterNodes =
			(List<LCSClusterNode>)request.getAttribute(key);

		if (lcsClusterNodes != null) {
			return lcsClusterNodes;
		}

		lcsClusterNodes =
			LCSClusterNodeServiceUtil.getLCSProjectLCSClusterNodes(
				lcsProjectId, true);

		request.setAttribute(key, lcsClusterNodes);

		return lcsClusterNodes;
	}

	protected static List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			HttpServletRequest request, long lcsClusterEntryId)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes =
			(List<LCSClusterNode>)request.getAttribute(
				OSBLCSWebKeys.LCS_CLUSTER_ENTRY_LCS_CLUSTER_NODES);

		if (lcsClusterNodes != null) {
			return lcsClusterNodes;
		}

		lcsClusterNodes =
			LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntryId, true);

		request.setAttribute(
			OSBLCSWebKeys.LCS_CLUSTER_ENTRY_LCS_CLUSTER_NODES, lcsClusterNodes);

		return lcsClusterNodes;
	}

	protected static LCSClusterNode getLCSClusterNode(
			HttpServletRequest request, long lcsClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode = (LCSClusterNode)request.getAttribute(
			OSBLCSWebKeys.LCS_CLUSTER_NODE);

		if (lcsClusterNode != null) {
			return lcsClusterNode;
		}

		lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(
			lcsClusterNodeId, true);

		request.setAttribute(OSBLCSWebKeys.LCS_CLUSTER_NODE, lcsClusterNode);

		return lcsClusterNode;
	}

	protected static List<LCSClusterNode> getLCSProjectsLCSClusterNodes(
			HttpServletRequest request)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

		List<LCSProject> lcsProjects = (List<LCSProject>)request.getAttribute(
			OSBLCSWebKeys.LCS_PROJECTS);

		if (lcsProjects != null) {
			lcsProjects = LCSProjectServiceUtil.getUserLCSProjects();

			request.setAttribute(OSBLCSWebKeys.LCS_PROJECTS, lcsProjects);
		}

		for (LCSProject lcsProject : lcsProjects) {
			List<LCSClusterNode> curLCSClusterNodes =
				getLCSProjectLCSClusterNodes(
					request, lcsProject.getLcsProjectId());

			lcsClusterNodes.addAll(curLCSClusterNodes);
		}

		return lcsClusterNodes;
	}

}