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

package com.liferay.osb.lcs.nosql.cassandra.spring;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 * @author Ivica Cardic
 */
public class MapperFactoryBean<T> {

	public MapperFactoryBean(Class<T> clazz, MappingManager mappingManager) {
		_class = clazz;
		_mappingManager = mappingManager;
	}

	public Mapper<T> createMapper() {
		return _mappingManager.mapper(_class);
	}

	private final Class<T> _class;
	private final MappingManager _mappingManager;

}