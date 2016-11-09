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

package com.liferay.osb.lcs.admin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Igor Beslic
 */
public class LCSClusterNodeDisplayTerms extends DisplayTerms {

	public static final String LCS_CLUSTER_ENTRY_NAME = "lcsClusterEntryName";

	public static final String LCS_CLUSTER_NODE_STATUS = "lcsClusterNodeStatus";

	public static final String LCS_PROJECT_NAME = "lcsProjectName";

	public LCSClusterNodeDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		lcsClusterEntryName = ParamUtil.getString(
			portletRequest, LCS_CLUSTER_ENTRY_NAME);
		lcsClusterNodeStatus = ParamUtil.getString(
			portletRequest, LCS_CLUSTER_NODE_STATUS);
		lcsProjectName = ParamUtil.getString(portletRequest, LCS_PROJECT_NAME);
	}

	public String getLCSClusterEntryName() {
		return lcsClusterEntryName;
	}

	public String getLCSClusterNodeStatus() {
		return lcsClusterNodeStatus;
	}

	public String getLCSProjectName() {
		return lcsProjectName;
	}

	protected String lcsClusterEntryName;
	protected String lcsClusterNodeStatus;
	protected String lcsProjectName;

}