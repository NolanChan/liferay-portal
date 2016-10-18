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

package com.liferay.osb.lcs.downloads.portlet;

import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-downloads" + OSBLCSPortletKeys.DOWNLOADS,
		"com.liferay.portlet.display-category=category.lcs",
		"javax.portlet.display-name=Downloads",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Downloads",
		"javax.portlet.info.short-title=Downloads",
		"javax.portlet.info.title=Downloads",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/downloads/",
		"javax.portlet.init-param.view-template=/downloads/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class DownloadsPortlet extends MVCPortlet {
}