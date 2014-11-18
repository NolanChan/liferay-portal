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

package com.liferay.wiki.web.wiki.portlet.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.FindAction;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPageResource;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.service.WikiPageResourceLocalServiceUtil;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Kong
 */
public class FindPageAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(primaryKey);

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		return node.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "pageResourcePrimKey";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		if (portletId.equals(WikiPortletKeys.WIKI_ADMIN)) {
			return "/wiki_admin/view";
		}
		else if (portletId.equals(WikiPortletKeys.WIKI)) {
			return "/wiki/view";
		}
		else {
			return "/wiki_display/view";
		}
	}

	@Override
	protected String[] initPortletIds() {

		// Order is important. See LPS-23770.

		return new String[] {
			WikiPortletKeys.WIKI_ADMIN, WikiPortletKeys.WIKI,
			WikiPortletKeys.WIKI_DISPLAY
		};
	}

	@Override
	protected PortletURL processPortletURL(
			HttpServletRequest request, PortletURL portletURL)
		throws Exception {

		long pageResourcePrimKey = ParamUtil.getLong(
			request, getPrimaryKeyParameterName());

		WikiPageResource pageResource =
			WikiPageResourceLocalServiceUtil.getPageResource(
				pageResourcePrimKey);

		WikiNode node = WikiNodeLocalServiceUtil.getNode(
			pageResource.getNodeId());

		portletURL.setParameter("nodeName", node.getName());
		portletURL.setParameter("title", pageResource.getTitle());

		return portletURL;
	}

}