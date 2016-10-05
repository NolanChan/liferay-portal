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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;

import java.util.List;

/**
 * @author Igor Beslic
 * @author Peter Shin
 */
@ProviderType
public class LCSClusterEntryImpl extends LCSClusterEntryBaseImpl {

	public LCSClusterEntryImpl() {
	}

	@Override
	public boolean hasOfflineLCSClusterNode() {
		if (_hasOfflineLCSClusterNode != null) {
			return _hasOfflineLCSClusterNode;
		}

		List<LCSClusterNode> lcsClusterNodes =
			LCSClusterNodeLocalServiceUtil.getLCSClusterEntryLCSClusterNodes(
				getLcsClusterEntryId(), true);

		_hasOfflineLCSClusterNode = false;

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (lcsClusterNode.isOffline()) {
				_hasOfflineLCSClusterNode = true;
			}
		}

		return _hasOfflineLCSClusterNode;
	}

	@Override
	public boolean isCluster() {
		if (getType() == LCSConstants.LCS_CLUSTER_ENTRY_TYPE_CLUSTER) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isEnvironment() {
		if (getType() == LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT) {
			return true;
		}

		return false;
	}

	private Boolean _hasOfflineLCSClusterNode;

}