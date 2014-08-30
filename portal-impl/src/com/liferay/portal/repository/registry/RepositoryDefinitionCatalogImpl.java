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

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.repository.registry.RepositoryCreator;
import com.liferay.portal.kernel.repository.registry.RepositoryDefiner;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.repository.external.LegacyExternalRepositoryDefiner;
import com.liferay.portal.repository.util.ExternalRepositoryFactory;
import com.liferay.portal.repository.util.ExternalRepositoryFactoryImpl;
import com.liferay.portal.repository.util.ExternalRepositoryFactoryUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.StringServiceRegistrationMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Adolfo Pérez
 */
public class RepositoryDefinitionCatalogImpl
	implements RepositoryDefinitionCatalog {

	@Override
	public Collection<String> getExternalRepositoryClassNames() {
		return _externalRepositoriesClassNames;
	}

	@Override
	public RepositoryDefinition getRepositoryDefinition(String className) {
		return _repositoryDefinitions.get(className);
	}

	public void loadDefaultRepositoryDefiners() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			RepositoryDefiner.class,
			new RepositoryDefinerServiceTrackerCustomizer());

		_serviceTracker.open();

		registerRepositoryDefiner(_liferayRepositoryDefiner);

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		for (String className : PropsValues.DL_REPOSITORY_IMPL) {
			ExternalRepositoryFactory externalRepositoryFactory =
				new ExternalRepositoryFactoryImpl(className, classLoader);

			registerLegacyExternalRepositoryFactory(
				className, externalRepositoryFactory);
		}
	}

	@Override
	public void registerLegacyExternalRepositoryFactory(
		String className, ExternalRepositoryFactory externalRepositoryFactory) {

		ExternalRepositoryFactoryUtil.registerExternalRepositoryFactory(
			className, externalRepositoryFactory);

		RepositoryDefiner repositoryDefiner =
			new LegacyExternalRepositoryDefiner(
				className, _legacyExternalRepositoryCreator);

		ServiceRegistration<RepositoryDefiner> serviceRegistration =
			registerRepositoryDefiner(repositoryDefiner);

		_serviceRegistrations.put(className, serviceRegistration);
	}

	public void setLegacyExternalRepositoryCreator(
		RepositoryCreator legacyExternalRepositoryCreator) {

		_legacyExternalRepositoryCreator = legacyExternalRepositoryCreator;
	}

	public void setLiferayRepositoryDefiner(
		RepositoryDefiner liferayRepositoryDefiner) {

		_liferayRepositoryDefiner = liferayRepositoryDefiner;
	}

	@Override
	public void unregisterLegacyExternalRepositoryFactory(String className) {
		ExternalRepositoryFactoryUtil.unregisterExternalRepositoryFactory(
			className);

		ServiceRegistration<RepositoryDefiner> serviceRegistration =
			_serviceRegistrations.remove(className);

		serviceRegistration.unregister();

		unregisterRepositoryDefiner(className);
	}

	protected RepositoryDefinition createRepositoryDefinition(
		RepositoryDefiner repositoryDefiner) {

		DefaultRepositoryDefinition defaultRepositoryDefinition =
			new DefaultRepositoryDefinition();

		repositoryDefiner.registerCapabilities(defaultRepositoryDefinition);
		repositoryDefiner.registerRepositoryCreator(
			defaultRepositoryDefinition);
		repositoryDefiner.registerRepositoryEventListeners(
			defaultRepositoryDefinition);

		return defaultRepositoryDefinition;
	}

	protected ServiceRegistration<RepositoryDefiner>
		registerRepositoryDefiner(
			RepositoryDefiner repositoryDefiner) {

		Registry registry = RegistryUtil.getRegistry();

		return registry.registerService(
			RepositoryDefiner.class, repositoryDefiner);
	}

	protected void unregisterRepositoryDefiner(String className) {
		_externalRepositoriesClassNames.remove(className);

		_repositoryDefinitions.remove(className);
	}

	private Set<String> _externalRepositoriesClassNames =
		new ConcurrentHashSet<String>();
	private RepositoryCreator _legacyExternalRepositoryCreator;
	private RepositoryDefiner _liferayRepositoryDefiner;
	private Map<String, RepositoryDefinition> _repositoryDefinitions =
		new ConcurrentHashMap<String, RepositoryDefinition>();
	private StringServiceRegistrationMap<RepositoryDefiner>
		_serviceRegistrations =
			new StringServiceRegistrationMap<RepositoryDefiner>();
	private ServiceTracker<RepositoryDefiner, RepositoryDefiner>
		_serviceTracker;

	private class RepositoryDefinerServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<RepositoryDefiner, RepositoryDefiner> {

		@Override
		public RepositoryDefiner addingService(
			ServiceReference<RepositoryDefiner> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			RepositoryDefiner repositoryDefiner = registry.getService(
				serviceReference);

			String className = repositoryDefiner.getClassName();

			if (repositoryDefiner.isExternalRepository()) {
				_externalRepositoriesClassNames.add(className);
			}

			_repositoryDefinitions.put(
				className, createRepositoryDefinition(repositoryDefiner));

			return repositoryDefiner;
		}

		@Override
		public void modifiedService(
			ServiceReference<RepositoryDefiner> serviceReference,
			RepositoryDefiner repositoryDefiner) {

			String className = repositoryDefiner.getClassName();

			if (repositoryDefiner.isExternalRepository()) {
				_externalRepositoriesClassNames.add(className);
			}
			else {
				_externalRepositoriesClassNames.remove(className);
			}

			_repositoryDefinitions.put(
				repositoryDefiner.getClassName(),
				createRepositoryDefinition(repositoryDefiner));
		}

		@Override
		public void removedService(
			ServiceReference<RepositoryDefiner> serviceReference,
			RepositoryDefiner repositoryDefiner) {

			unregisterRepositoryDefiner(repositoryDefiner.getClassName());
		}

	}

}