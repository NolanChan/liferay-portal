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

package com.liferay.marketplace.store.web.configuration;

/**
 * @author Joan Kim
 * @author Ryan Park
 */
public class MarketplaceStoreWebConfigurationValues {

	public static final String MARKETPLACE_KEY =
		MarketplaceStoreWebConfigurationUtil.get(
			MarketplaceStoreWebConfigurationKeys.MARKETPLACE_KEY);

	public static final String MARKETPLACE_SECRET =
		MarketplaceStoreWebConfigurationUtil.get(
			MarketplaceStoreWebConfigurationKeys.MARKETPLACE_SECRET);

	public static final String MARKETPLACE_URL =
		MarketplaceStoreWebConfigurationUtil.get(
			MarketplaceStoreWebConfigurationKeys.MARKETPLACE_URL);

}