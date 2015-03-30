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

package com.liferay.site.navigation.language.web.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Eudaldo Alonso
 */
public class LanguageWebConfigurationValues {

	public static final String DDM_TEMPLATE_KEY_DEFAULT = GetterUtil.getString(
		LanguageWebConfigurationUtil.get("ddm.template.key.default"));

	public static final String DISPLAY_TEMPLATES_CONFIG = GetterUtil.getString(
		LanguageWebConfigurationUtil.get("display.templates.config"));

}