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

package com.liferay.portlet.configuration.icon.configuration;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ConfigurationPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public ConfigurationPortletConfigurationIcon(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getCssClass() {
		return "portlet-configuration portlet-configuration-icon";
	}

	@Override
	public String getImage() {
		return "../aui/wrench";
	}

	@Override
	public String getMessage() {
		return "configuration";
	}

	@Override
	public String getMethod() {
		return "get";
	}

	@Override
	public String getOnClick() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		StringBuilder sb = new StringBuilder(13);

		sb.append("Liferay.Portlet.openWindow('#p_p_id_");
		sb.append(portletDisplay.getId());
		sb.append("_', '");
		sb.append(portletDisplay.getId());
		sb.append("', '");
		sb.append(HtmlUtil.escapeJS(portletDisplay.getURLConfiguration()));
		sb.append("', '");
		sb.append(portletDisplay.getNamespace());
		sb.append("', '");
		sb.append(LanguageUtil.get(themeDisplay.getLocale(), "configuration"));
		sb.append("', '");
		sb.append(
			PropsValues.PORTLET_CONFIG_SHOW_PORTLET_ID ?
				portletDisplay.getId() : StringPool.BLANK);
		sb.append("'); return false;");

		return sb.toString();
	}

	@Override
	public String getURL() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getURLConfiguration();
	}

	@Override
	public boolean isShow() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.isShowConfigurationIcon();
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

}