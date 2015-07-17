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

package com.liferay.shopping.util;

import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.shopping.configuration.ShoppingGroupServiceConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(immediate = true)
public class ShoppingServiceComponentProvider {

	public static ShoppingServiceComponentProvider
		getShoppingServiceComponentProvider() {

		return _shoppingServiceComponentProvider;
	}

	public SettingsFactory getSettingsFactory() {
		return _settingsFactory;
	}

	public ShoppingGroupServiceConfiguration
		getShoppingGroupServiceConfiguration() {

		return _shoppingGroupServiceConfiguration;
	}

	@Activate
	protected void activate() {
		_shoppingServiceComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_shoppingServiceComponentProvider = null;
	}

	@Reference(unbind = "-")
	protected void setSettingsFactory(SettingsFactory settingsFactory) {
		_settingsFactory = settingsFactory;
	}

	@Reference
	protected void setShoppingGroupServiceConfiguration(
		ShoppingGroupServiceConfiguration shoppingGroupServiceConfiguration) {

		_shoppingGroupServiceConfiguration = shoppingGroupServiceConfiguration;
	}

	protected void unsetShoppingGroupServiceConfiguration(
		ShoppingGroupServiceConfiguration shoppingGroupServiceConfiguration) {

		_shoppingGroupServiceConfiguration = null;
	}

	private static ShoppingServiceComponentProvider
		_shoppingServiceComponentProvider;

	private SettingsFactory _settingsFactory;
	private ShoppingGroupServiceConfiguration
		_shoppingGroupServiceConfiguration;

}