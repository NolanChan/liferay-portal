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

package com.liferay.portal.repository.registry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.registry.RepositoryRegistryPlugin;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.repository.util.ExternalRepositoryFactory;

import java.util.Collection;

/**
 * @author Adolfo Pérez
 */
public class RepositoryCatalogUtil {

	public static Collection<String> getExternalRepositoryClassNames() {
		return _repositoryCatalog.getExternalRepositoryClassNames();
	}

	public static void registerLegacyExternalRepositoryFactory(
			String className,
			ExternalRepositoryFactory externalRepositoryFactory)
		throws PortalException {

		_repositoryCatalog.registerLegacyExternalRepositoryFactory(
			className, externalRepositoryFactory);
	}

	public static void registerRepositoryRegistryPlugin(
			RepositoryRegistryPlugin repositoryRegistryPlugin)
		throws PortalException {

		_repositoryCatalog.registerRepositoryRegistryPlugin(
			repositoryRegistryPlugin);
	}

	public static void unregisterLegacyExternalRepositoryFactory(
		String className) {

		_repositoryCatalog.unregisterLegacyExternalRepositoryFactory(className);
	}

	public static void unregisterRepositoryRegistryPlugin(String className) {
		_repositoryCatalog.unregisterRepositoryRegistryPlugin(className);
	}

	public void setRepositoryCatalog(RepositoryCatalog repositoryCatalog) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_repositoryCatalog = repositoryCatalog;
	}

	private static RepositoryCatalog _repositoryCatalog;

}