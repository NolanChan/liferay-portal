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

package com.liferay.dynamic.data.mapping.web.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Rafael Praxedes
 */
public class DDMWebConfigurationValues {
	
	public static final boolean DYNAMIC_DATA_MAPPING_STRUCTURE_FORCE_AUTOGENERATE_KEY = 
		GetterUtil.getBoolean(
			DDMWebConfigurationUtil.get(
				DDMWebConfigurationKeys.DYNAMIC_DATA_MAPPING_STRUCTURE_FORCE_AUTOGENERATE_KEY));

	public static final boolean DYNAMIC_DATA_MAPPING_TEMPLATE_FORCE_AUTOGENERATE_KEY = 
		GetterUtil.getBoolean(
			DDMWebConfigurationUtil.get(
				DDMWebConfigurationKeys.DYNAMIC_DATA_MAPPING_TEMPLATE_FORCE_AUTOGENERATE_KEY));

	public static final String DYNAMIC_DATA_MAPPING_TEMPLATE_LANGUAGE_DEFAULT =
		GetterUtil.getString(
			DDMWebConfigurationUtil.get(
				DDMWebConfigurationKeys.DYNAMIC_DATA_MAPPING_TEMPLATE_LANGUAGE_DEFAULT));

}