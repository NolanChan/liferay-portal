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

package com.liferay.portal.servlet.taglib.ui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;

import java.util.Locale;

/**
 * @author Sergio González
 */
@OSGiBeanProperties(property = {"service.ranking:Integer=10"})
public class LayoutSetMobileDeviceRulesFormNavigatorEntry
	extends BaseLayoutSetFormNavigatorEntry {

	@Override
	public String getKey() {
		return "mobile-device-rules";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "mobile-device-rules");
	}

	@Override
	protected String getJspPath() {
		return "/html/portlet/layouts_admin/layout_set/mobile_device_rules.jsp";
	}

}