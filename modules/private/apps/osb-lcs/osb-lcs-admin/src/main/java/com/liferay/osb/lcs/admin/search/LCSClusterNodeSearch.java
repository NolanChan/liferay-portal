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

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Igor Beslic
 */
public class LCSClusterNodeSearch extends SearchContainer<LCSClusterNode> {

	public LCSClusterNodeSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new LCSClusterNodeDisplayTerms(portletRequest),
			new LCSClusterNodeSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, null);

		LCSClusterNodeSearchTerms lcsClusterNodeSearchTerms =
			(LCSClusterNodeSearchTerms)getSearchTerms();

		iteratorURL.setParameter(
			LCSClusterNodeDisplayTerms.KEYWORDS,
			lcsClusterNodeSearchTerms.getKeywords());
		iteratorURL.setParameter(
			LCSClusterNodeDisplayTerms.LCS_CLUSTER_ENTRY_NAME,
			lcsClusterNodeSearchTerms.getLCSClusterEntryName());
		iteratorURL.setParameter(
			LCSClusterNodeDisplayTerms.LCS_CLUSTER_NODE_STATUS,
			lcsClusterNodeSearchTerms.getLCSClusterNodeStatus());
		iteratorURL.setParameter(
			LCSClusterNodeDisplayTerms.LCS_PROJECT_NAME,
			lcsClusterNodeSearchTerms.getLCSProjectName());

		setIteratorURL(iteratorURL);
	}

}