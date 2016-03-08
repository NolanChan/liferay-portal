/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.recognition.provider.wurfl.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.mobile.device.rules.recognition.provider.wurfl.configuration.WURFLEngineConfiguration"
)
public interface WURFLEngineConfiguration {

	@Meta.AD(
		deflt = "brand_name|can_assign_phone_number|columns|device_os|device_os_version|dual_orientation|has_cellular_radio|has_querty_keyboard|https_support|https_verisign_class3|is_smarttv|is_tablet|is_wireless_device|marketing_name|max_data_rate|mobile_browser|mobile_browser_version|model_name|physical_screen_height|physical_screen_width|pointing_method|preferred_markup|resolution_height|resolution_width|rows|ux_full_desktop|vpn|wifi|xhtml_can_embed_video<|xhtml_support_level",
		required = false
	)
	public String[] capabilityFilter();

	@Meta.AD(
		deflt = "performance", optionValues = {"accuracy", "performance"},
		required = false
	)
	public String engineTarget();

	@Meta.AD(deflt = "/META-INF/resources/wurfl-latest.zip", required = false)
	public String wurflDatabaseFileName();

	@Meta.AD(deflt = "/data/wurfl/patches", required = false)
	public String wurflDatabasePatchDirName();

}