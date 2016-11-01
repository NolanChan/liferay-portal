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

package com.liferay.twitter.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.twitter.configuration.TwitterConfiguration",
	localization = "content/Language",
	name = "twitter.service.configuration.name"
)
public interface TwitterGroupServiceConfiguration {

	@Meta.AD(
		deflt = "com.liferay.twitter.util.HttpTimelineProcessor",
		required = false
	)
	public String usersTimelineProcessor();

	@Meta.AD(deflt = "5", required = false)
	public int twitterSynchronizationInterval();

}