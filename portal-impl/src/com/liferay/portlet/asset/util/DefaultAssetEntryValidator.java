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

package com.liferay.portlet.asset.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultAssetEntryValidator extends BaseAssetEntryValidator {

	public void afterPropertiesSet() {
		_serviceTrackerMap = ServiceTrackerCollections.openSingleValueMap(
			AssetEntryValidator.class, "model.class.name");
	}

	public void destroy() {
		_serviceTrackerMap.close();
	}

	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] entryNames)
		throws PortalException {

		AssetEntryValidator assetEntryValidator = _serviceTrackerMap.getService(
			className);

		if (assetEntryValidator == null) {
			super.validate(
				groupId, className, classTypePK, categoryIds, entryNames);
		}
		else {
			assetEntryValidator.validate(
				groupId, className, classTypePK, categoryIds, entryNames);
		}
	}

	private ServiceTrackerMap<String, AssetEntryValidator> _serviceTrackerMap;

}