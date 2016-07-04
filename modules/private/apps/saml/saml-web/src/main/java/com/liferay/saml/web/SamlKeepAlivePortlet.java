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

package com.liferay.saml.web;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=false",
		"javax.portlet.display-name=SAML Keep Alive",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/keep_alive/view.jsp",
		"javax.portlet.name=com_liferay_saml_admin_portlet_SamlKeepAlivePortlet",
		"javax.portlet.portlet.info.keywords=SAML Keep Alive",
		"javax.portlet.portlet.info.short-title=SAML Keep Alive",
		"javax.portlet.portlet.info.title=SAML Keep Alive",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = {SamlKeepAlivePortlet.class, Portlet.class}
)
public class SamlKeepAlivePortlet extends MVCPortlet {
}