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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeProperties;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePropertiesService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePropertiesPersistence;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = LCSClusterNodePropertiesService.class)
public class LCSClusterNodePropertiesServiceImpl
	implements LCSClusterNodePropertiesService {

	@Override
	public LCSClusterNodeProperties fetchLCSClusterNodeProperties(String key) {
		LCSClusterNodeProperties lcsClusterNodeProperties =
			_lcsClusterNodePropertiesPersistence.fetchByKey(key);

		return lcsClusterNodeProperties;
	}

	public void setLCSClusterNodePropertiesPersistence(
		LCSClusterNodePropertiesPersistence
			lcsClusterNodePropertiesPersistence) {

		_lcsClusterNodePropertiesPersistence =
			lcsClusterNodePropertiesPersistence;
	}

	@Override
	public LCSClusterNodeProperties updateLCSClusterNodeProperties(
		String hashCode, String key, Date modifiedDate,
		Map<String, String> properties) {

		_lcsClusterNodePropertiesPersistence.updateHashCode(
			hashCode, key, modifiedDate);

		Set<String> mapKeys = properties.keySet();

		for (String mapKey : mapKeys) {
			_lcsClusterNodePropertiesPersistence.updatePropertiesMapColumn(
				key, mapKey, properties.get(mapKey));
		}

		removeDeletedLCSClusterNodeProperties(key, properties);

		LCSClusterNodeProperties lcsClusterNodeProperties =
			_lcsClusterNodePropertiesPersistence.create();

		lcsClusterNodeProperties.setHashCode(hashCode);
		lcsClusterNodeProperties.setKey(key);
		lcsClusterNodeProperties.setModifiedDate(modifiedDate);
		lcsClusterNodeProperties.setNew(false);
		lcsClusterNodeProperties.setProperties(properties);

		return lcsClusterNodeProperties;
	}

	protected void removeDeletedLCSClusterNodeProperties(
		String key, Map<String, String> properties) {

		LCSClusterNodeProperties oldLCSClusterNodeProperties =
			fetchLCSClusterNodeProperties(key);

		Map<String, String> oldProperties =
			oldLCSClusterNodeProperties.getProperties();

		Set<String> oldKeys = oldProperties.keySet();

		oldKeys.removeAll(properties.keySet());

		for (String oldKey : oldKeys) {
			_lcsClusterNodePropertiesPersistence.updatePropertiesMapColumn(
				key, oldKey, null);
		}
	}

	private LCSClusterNodePropertiesPersistence
		_lcsClusterNodePropertiesPersistence;

}