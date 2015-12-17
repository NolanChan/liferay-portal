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

package com.liferay.iframe.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.configuration.admin.ConfigurationAdmin;

/**
 * @author Peter Fellwock
 */
@ConfigurationAdmin(category = "web-experience-management")
@Meta.OCD(
	id = "com.liferay.iframe.web.configuration.IFrameConfiguration",
	localization = "content/Language", name = "%iframe.configuration.name"
)
public interface IFrameConfiguration {

	@Meta.AD(deflt = "false", required = false)
	public boolean auth();

	@Meta.AD(deflt = "basic", required = false)
	public String authType();

	@Meta.AD(deflt = "true", required = false)
	public boolean dynamicUrlEnabled();

	@Meta.AD(deflt = "post", required = false)
	public String formMethod();

	@Meta.AD(deflt = "var1=hello|var2=world", required = false)
	public String[] hiddenVariables();

}