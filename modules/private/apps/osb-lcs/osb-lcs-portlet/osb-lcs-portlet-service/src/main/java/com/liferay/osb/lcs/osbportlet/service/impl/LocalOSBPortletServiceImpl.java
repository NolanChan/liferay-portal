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

import com.liferay.jsonwebserviceclient.JSONWebServiceClient;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class LocalOSBPortletServiceImpl extends BaseOSBPortletServiceImpl {

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<User> getOrganizationUsers(long organizationId)
		throws SystemException {

		return UserLocalServiceUtil.getOrganizationUsers(organizationId);
	}

}