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

package com.liferay.dynamic.data.mapping.util.impl;

import com.liferay.dynamic.data.mapping.configuration.DDMConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 *
 * @author Lino Alves
 *
 */

@Component(immediate = true)
public class DDMComponentProvider {

	public static DDMComponentProvider getDDMComponentProvider() {
		return _ddmComponentProvider;
	}

	@Activate
	public void activate() {
		_ddmComponentProvider = this;
	}

	@Deactivate
	public void deactivate() {
		_ddmComponentProvider = null;
	}

	public DDMConfiguration getDDMConfiguration() {
		return _ddmConfiguration;
	}

	@Reference
	public void setDDMConfiguration(DDMConfiguration ddmConfiguration) {
		_ddmConfiguration = ddmConfiguration;
	}

	protected void unsetDDMConfiguration(DDMConfiguration DDMConfiguration) {
		_ddmConfiguration = null;
	}

	private static DDMComponentProvider _ddmComponentProvider;

	private DDMConfiguration _ddmConfiguration;

}