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

package com.liferay.osb.lcs.osbportlet.service.impl;

import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSLocalConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = OSBPortletService.class
)
public class LocalOSBPortletServiceImpl extends BaseOSBPortletServiceImpl {

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<User> getOrganizationUsers(long organizationId) {
		return userLocalService.getOrganizationUsers(organizationId);
	}

}