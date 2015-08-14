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

package com.liferay.portalsettings.web.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author Philip Jones
 */
@Component(
	property = {
		"com.liferay.portlet.control-panel-entry-category=configuration",
	     "com.liferay.portlet.control-panel-entry-weight=1.0",
	     "com.liferay.portlet.css-class-wrapper=portlet-users-admin",
	     "com.liferay.portlet.footer-portlet-javascript=/users_admin/js/main.js",
	     "com.liferay.portlet.header-portlet-css=/css/main.css",
	     "com.liferay.portlet.header-portlet-css=/users_admin/css/main.css",
	     "com.liferay.portlet.icon=/icons/portal_settings.png",
	     "com.liferay.portlet.preferences-owned-by-group=true",
	     "com.liferay.portlet.private-request-attributes=false",
	     "com.liferay.portlet.private-session-attributes=false",
	     "com.liferay.portlet.render-weight=50",
	     "com.liferay.portlet.use-default-template=true",
	     "javax.portlet.display-name=Portal Settings",
	     "javax.portlet.expiration-cache=0",
	     "javax.portlet.init-param.template-path=/",
	     "javax.portlet.init-param.view-template=/edit_company.jsp",
	     "javax.portlet.resource-bundle=content.Language",
	     "javax.portlet.security-role-ref=administrator",
	     "javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class PortalSettingsPortlet extends MVCPortlet {
}
