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

package com.liferay.osb.ldn.extensions.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.util.PortalInstances;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	property = {
		"before-filter=JSON Content Type Filter", "dispatcher=FORWARD",
		"dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=OSB LDN Inactive Group Filter",
		"url-pattern=/web/*"
	},
	service = Filter.class
)
public class InactiveGroupFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		long companyId = PortalInstances.getCompanyId(request);

		Group group = _groupLocalService.fetchFriendlyURLGroup(
			companyId, request.getPathInfo());

		if ((group != null) && !group.isActive()) {
			response.sendRedirect(PortalUtil.getHomeURL(request));

			return;
		}

		super.processFilter(request, response, filterChain);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InactiveGroupFilter.class);

	@Reference
	private GroupLocalService _groupLocalService;

}