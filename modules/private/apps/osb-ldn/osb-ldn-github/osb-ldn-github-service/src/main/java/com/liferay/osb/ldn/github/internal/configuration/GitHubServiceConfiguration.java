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

package com.liferay.osb.ldn.github.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Howie Chou
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.ldn.github.internal.configuration.GitHubServiceConfiguration",
	localization = "content/Language",
	name = "osb.ldn.github.configuration.name"
)
public interface GitHubServiceConfiguration {

	@Meta.AD(deflt = "", description = "api-key", required = true)
	public String apiKey();

	@Meta.AD(deflt = "10", name = "top-contributor-count", required = true)
	public int topContributorCount();

	@Meta.AD(deflt = "24", name = "update-interval-hours", required = true)
	public int updateIntervalHours();

	@Meta.AD(
		deflt = "600000", name = "update-window-milliseconds", required = true
	)
	public int updateWindowMilliseconds();

}