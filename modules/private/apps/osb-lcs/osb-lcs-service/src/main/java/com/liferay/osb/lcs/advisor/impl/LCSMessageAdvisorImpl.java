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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.LCSMessageAdvisor;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.service.LCSMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matija Petanjek
 */
@Component(immediate = true, service = LCSMessageAdvisor.class)
public class LCSMessageAdvisorImpl implements LCSMessageAdvisor {

	@Override
	public void addClusterLCSMessage(
			boolean cluster, LCSClusterNode lcsClusterNode,
			LCSEventType lcsEventType)
		throws PortalException {

		if (cluster) {
			addLCSClusterEntryLCSMessage(
				true, lcsClusterNode.getLcsClusterEntryId(), lcsEventType);
		}
		else {
			addLCSClusterNodeLCSMessage(
				lcsClusterNode.getLcsClusterNodeId(), lcsEventType);
		}
	}

	@Override
	public LCSMessage addLCSClusterEntryLCSMessage(
			boolean generateUserLCSMessages, long lcsClusterEntryId,
			LCSEventType lcsEventType)
		throws PortalException {

		return LCSMessageLocalServiceUtil.addLCSClusterEntryLCSMessage(
			lcsClusterEntryId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, null,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType(),
			generateUserLCSMessages);
	}

	public LCSMessage addLCSClusterNodeLCSMessage(
			long lcsClusterNodeId, LCSEventType lcsEventType)
		throws PortalException {

		return LCSMessageLocalServiceUtil.addLCSClusterNodeLCSMessage(
			lcsClusterNodeId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, null,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType());
	}

	@Override
	public void addLCSMessage(
			boolean cluster, LCSClusterNode lcsClusterNode,
			LCSEventType lcsEventType)
		throws PortalException {

		if (lcsEventType == LCSEventType.MONITORING_UNAVAILABLE) {
			addLCSClusterNodeLCSMessage(
				lcsClusterNode.getLcsClusterNodeId(), lcsEventType);
		}
		else if (lcsEventType == LCSEventType.NEW_LCS_PORTLET_AVAILABLE) {
			addClusterLCSMessage(cluster, lcsClusterNode, lcsEventType);
		}
		else if (lcsEventType == LCSEventType.NEW_PATCH_AVAILABLE) {
			addClusterLCSMessage(cluster, lcsClusterNode, lcsEventType);
		}
		else if (lcsEventType == LCSEventType.NEW_PATCHING_TOOL_AVAILABLE) {
			addClusterLCSMessage(cluster, lcsClusterNode, lcsEventType);
		}
		else if (lcsEventType == LCSEventType.PATCHING_TOOL_UNAVAILABLE) {
			addLCSClusterNodeLCSMessage(
				lcsClusterNode.getLcsClusterNodeId(), lcsEventType);
		}
		else if (lcsEventType == LCSEventType.SERVER_MANUALLY_SHUTDOWN) {
			addLCSClusterNodeLCSMessage(
				lcsClusterNode.getLcsClusterNodeId(), lcsEventType);
		}
		else if (lcsEventType == LCSEventType.SERVER_UNEXPECTEDLY_SHUTDOWN) {
			addLCSClusterNodeLCSMessage(
				lcsClusterNode.getLcsClusterNodeId(), lcsEventType);
		}
	}

	@Override
	public LCSMessage addLCSProjectLCSMessage(
			boolean adminsOnly, String content, boolean generateUserLCSMessages,
			LCSEventType lcsEventType, long lcsProjectId)
		throws PortalException {

		return LCSMessageLocalServiceUtil.addLCSProjectLCSMessage(
			lcsProjectId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, content,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType(), adminsOnly,
			generateUserLCSMessages);
	}

}