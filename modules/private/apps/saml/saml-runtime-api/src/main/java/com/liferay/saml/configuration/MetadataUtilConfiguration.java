/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.saml.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Carlos Sierra Andrés
 */
@Meta.OCD(id = "com.liferay.saml.util.MetadataUtilConfiguration")
public interface MetadataUtilConfiguration {

	@Meta.AD(
		name = "connection.manager.timeout", deflt = "60000", required = false
	)
	public int getConnectionManagerTimeout();

	@Meta.AD(name = "so.timeout", deflt = "60000", required = false)
	public int getSoTimeout();

}