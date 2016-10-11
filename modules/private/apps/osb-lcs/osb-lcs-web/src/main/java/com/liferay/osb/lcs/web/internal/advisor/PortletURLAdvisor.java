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

package com.liferay.osb.lcs.web.internal.advisor;

import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PublicRenderParameter;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletQNameUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.xml.QName;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true)
public class PortletURLAdvisor {

	public LiferayPortletURL getPortletRenderURL(
			long companyId, String layoutFriendlyURL, String portletId,
			boolean privateLayout, HttpServletRequest request)
		throws PortalException {

		return getPortletRenderURL(
			companyId, layoutFriendlyURL, portletId, privateLayout, request,
			null);
	}

	public LiferayPortletURL getPortletRenderURL(
			long companyId, String layoutFriendlyURL, String portletId,
			boolean privateLayout, HttpServletRequest request,
			String... parameters)
		throws PortalException {

		Group group = _groupLocalService.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = _layoutLocalService.getFriendlyURLLayout(
			group.getGroupId(), privateLayout, layoutFriendlyURL);

		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, portletId, layout.getPlid(), PortletRequest.RENDER_PHASE);

		if (ArrayUtil.isEmpty(parameters)) {
			return liferayPortletURL;
		}

		for (int i = 0; (i + 1) < parameters.length; i = i + 2) {
			liferayPortletURL.setParameter(parameters[i], parameters[i + 1]);
		}

		return liferayPortletURL;
	}

	public String getPublicRenderParameterName(String parameterName) {
		Portlet portlet = _portletLocalService.getPortletById(
			OSBLCSPortletKeys.NAVIGATION);

		PublicRenderParameter publicRenderParameter =
			portlet.getPublicRenderParameter(parameterName);

		if (publicRenderParameter == null) {
			return parameterName;
		}

		QName qName = publicRenderParameter.getQName();

		return PortletQNameUtil.getPublicRenderParameterName(qName);
	}

	public Map<String, String> getPublicRenderParametersMap(
		String... parameters) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		for (int i = 0; (i + 1) < parameters.length;) {
			publicRenderParameters.put(parameters[i], parameters[i + 1]);

			i = i + 2;
		}

		return publicRenderParameters;
	}

	@Reference(unbind = "-")
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		_layoutLocalService = layoutLocalService;
	}

	public void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private PortletLocalService _portletLocalService;

}