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

package com.liferay.portal.properties.swapper.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(enabled = false, immediate = true)
public class DefaultGuestGroupLogoSwapper {

	@Activate
	public void activate(BundleContext bundleContext)
		throws PortalException, IOException {

		Bundle bundle = bundleContext.getBundle();

		URL siteLogoURL = bundle.getResource(_SITE_LOGO_FILE_NAME);
		InputStream siteLogoIs = siteLogoURL.openStream();

		List<Company> companies = _companyLocalService.getCompanies(0, 1);
		Company company = companies.get(0);

		Group guestGroup = _groupLocalService.getGroup(
			company.getCompanyId(), GroupConstants.GUEST);

		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			guestGroup.getGroupId(), false);

		if (layoutSet.getLogoId() == 0) {
			_layoutSetLocalService.updateLogo(
				guestGroup.getGroupId(), false, true, siteLogoIs);
		}
	}

	@Reference(unbind = "-")
	public void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	public void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {

		_layoutSetLocalService = layoutSetLocalService;
	}

	private static final String _SITE_LOGO_FILE_NAME =
		"com/liferay/portal/properties/swapper/internal/site_dxp_logo.png";

	private CompanyLocalService _companyLocalService;
	private GroupLocalService _groupLocalService;
	private LayoutSetLocalService _layoutSetLocalService;

}