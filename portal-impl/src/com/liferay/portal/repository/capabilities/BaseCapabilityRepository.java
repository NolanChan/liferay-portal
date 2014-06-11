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

package com.liferay.portal.repository.capabilities;

import com.liferay.portal.kernel.repository.capabilities.BaseCapabilityProvider;
import com.liferay.portal.kernel.repository.capabilities.Capability;

import java.util.Map;
import java.util.Set;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseCapabilityRepository<T>
	extends BaseCapabilityProvider {

	public BaseCapabilityRepository(
		T repository,
		Map<Class<? extends Capability>, Capability> supportedCapabilitiesMap,
		Set<Class<? extends Capability>> exportedCapabilityClasses) {

		super(supportedCapabilitiesMap, exportedCapabilityClasses);
		_repository = repository;
	}

	@Override
	protected String getProviderId() {
		return String.format(
			"%s:%s", getRepository().getClass(), getRepositoryId());
	}

	protected T getRepository() {
		return _repository;
	}

	protected abstract long getRepositoryId();

	private final T _repository;

}