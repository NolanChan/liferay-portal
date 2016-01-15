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

package com.liferay.wiki.web.portlet.configuration.icon;

import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

/**
 * @author Roberto Díaz
 */
public class PrintPagePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public PrintPagePortletConfigurationIcon(
		PortletRequest portletRequest, WikiNode node, WikiPage page) {

		super(portletRequest);

		_node = node;
		_page = page;
	}

	@Override
	public String getMessage() {
		return "print";
	}

	@Override
	public String getOnClick() {
		try {
			PortletURL printPageURL = PortalUtil.getControlPanelPortletURL(
				portletRequest, WikiPortletKeys.WIKI_ADMIN,
				PortletRequest.RENDER_PHASE);

			printPageURL.setParameter("mvcRenderCommandName", "/wiki/view");
			printPageURL.setParameter("nodeName", _node.getName());
			printPageURL.setParameter("title", _page.getTitle());
			printPageURL.setParameter("viewMode", Constants.PRINT);
			printPageURL.setWindowState(LiferayWindowState.POP_UP);

			StringBundler sb = new StringBundler(7);

			sb.append("window.open('");
			sb.append(printPageURL.toString());
			sb.append("', '', ");
			sb.append("'directories=0,height=480,left=80,location=1,");
			sb.append("menubar=1,resizable=1,scrollbars=yes,status=0,");
			sb.append("toolbar=0,top=180,width=640'");
			sb.append(")");

			return sb.toString();
		}
		catch (WindowStateException wes) {
		}

		return StringPool.BLANK;
	}

	@Override
	public String getURL() {
		return "javascript:;";
	}

	@Override
	public boolean isShow() {
		return true;
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	private final WikiNode _node;
	private final WikiPage _page;

}