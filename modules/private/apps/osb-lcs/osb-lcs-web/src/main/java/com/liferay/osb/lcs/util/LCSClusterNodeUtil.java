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

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true)
public class LCSClusterNodeUtil {

	public List<LCSClusterNode> getLCSClusterNodes(
			long layoutLCSClusterEntryId, long layoutLCSClusterNodeId,
			long layoutLCSProjectId)
		throws PortalException {

		List<LCSClusterNode> lcsClusterNodes = null;

		if ((layoutLCSClusterEntryId <= 0) && (layoutLCSClusterNodeId <= 0) &&
			(layoutLCSProjectId > 0)) {

			lcsClusterNodes = getLCSProjectLCSClusterNodes(layoutLCSProjectId);
		}
		else if ((layoutLCSClusterEntryId > 0) &&
				 (layoutLCSClusterNodeId <= 0)) {

			lcsClusterNodes = getLCSClusterEntryLCSClusterNodes(
				layoutLCSClusterEntryId);
		}
		else if (layoutLCSClusterNodeId > 0) {
			lcsClusterNodes = new ArrayList<>();

			LCSClusterNode lcsClusterNode = getLCSClusterNode(
				layoutLCSClusterNodeId);

			lcsClusterNodes.add(lcsClusterNode);
		}
		else {
			lcsClusterNodes = _lcsClusterNodeService.getUserLCSClusterNodes(
				false);
		}

		return lcsClusterNodes;
	}

	public List<LCSClusterNode> getLCSProjectLCSClusterNodes(long lcsProjectId)
		throws PortalException {

		return _lcsClusterNodeService.getLCSProjectLCSClusterNodes(
			lcsProjectId, true);
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeService(
		LCSClusterNodeService lcsClusterNodeService) {

		_lcsClusterNodeService = lcsClusterNodeService;
	}

	protected List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws PortalException {

		return _lcsClusterNodeService.getLCSClusterEntryLCSClusterNodes(
			lcsClusterEntryId, true);
	}

	protected LCSClusterNode getLCSClusterNode(long lcsClusterNodeId)
		throws PortalException {

		return _lcsClusterNodeService.getLCSClusterNode(lcsClusterNodeId, true);
	}

	private LCSClusterNodeService _lcsClusterNodeService;

}