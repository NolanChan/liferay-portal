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

package com.liferay.portal.portlet.toolbar.contributor.locator;

import com.liferay.portal.kernel.portlet.toolbar.contributor.locator.PortletToolbarContributorLocator;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = PortletToolbarContributorLocator.class)
public class MVCPortletToolbarContributorLocator
	extends BasePortletToolbarContributorLocator {

	@Activate
	protected void activate() {
		super.activate();
	}

	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	protected String getParameterName() {
		return "mvcPath";
	}

	@Override
	protected String getPropertyName() {
		return "mvc.path";
	}

	@Reference(target = "(original.bean=*)", unbind = "-")
	protected void setServletContext(ServletContext servletContext) {
	}

}