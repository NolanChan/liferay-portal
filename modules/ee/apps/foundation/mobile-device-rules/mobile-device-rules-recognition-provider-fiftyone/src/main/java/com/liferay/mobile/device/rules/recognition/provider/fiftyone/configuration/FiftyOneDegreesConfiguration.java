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

package com.liferay.mobile.device.rules.recognition.provider.fiftyone.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.mobile.device.rules.recognition.provider.fiftyone.configuration.FiftyOneDegreesConfiguration",
	localization = "content/Language",
	name = "51.degrees.device.detection.configuration.name"
)
public interface FiftyOneDegreesConfiguration {

	@Meta.AD(deflt = "51Degrees-PremiumV3_2.dat.gz", required = false)
	public String fiftyOneDegreesDataFileName();

}