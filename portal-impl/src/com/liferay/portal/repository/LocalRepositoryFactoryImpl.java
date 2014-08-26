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

package com.liferay.portal.repository;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.LocalRepositoryFactory;
import com.liferay.portal.kernel.repository.registry.RepositoryCreator;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.capabilities.CapabilityLocalRepository;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.repository.registry.RepositoryDefinition;
import com.liferay.portal.repository.registry.RepositoryDefinitionCatalog;
import com.liferay.portal.service.ClassNameLocalService;
import com.liferay.portal.service.RepositoryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionService;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalService;
import com.liferay.portlet.documentlibrary.service.DLFolderService;

/**
 * @author Adolfo Pérez
 */
public class LocalRepositoryFactoryImpl implements LocalRepositoryFactory {

	public LocalRepository createLocalRepository(long repositoryId)
		throws PortalException {

		long classNameId = getRepositoryClassNameId(repositoryId);

		if (classNameId == getDefaultClassNameId()) {
			return createInternalRepository(repositoryId);
		}
		else {
			return createExternalRepository(repositoryId, classNameId);
		}
	}

	protected LocalRepository createExternalRepository(
			long repositoryId, long classNameId)
		throws PortalException {

		RepositoryDefinition repositoryDefinition = getRepositoryDefinition(
			classNameId);

		RepositoryCreator repositoryCreator =
			repositoryDefinition.getRepositoryCreator();

		LocalRepository localRepository =
			repositoryCreator.createLocalRepository(repositoryId);

		return new CapabilityLocalRepository(
			localRepository, repositoryDefinition.getSupportedCapabilities(),
			repositoryDefinition.getExportedCapabilities(),
			repositoryDefinition.getRepositoryEventTrigger());
	}

	protected LocalRepository createInternalRepository(long repositoryId)
		throws PortalException {

		RepositoryDefinition repositoryDefinition = getRepositoryDefinition(
			getDefaultClassNameId());

		RepositoryCreator repositoryCreator =
			repositoryDefinition.getRepositoryCreator();

		LocalRepository localRepository =
			repositoryCreator.createLocalRepository(repositoryId);

		return new CapabilityLocalRepository(
			localRepository, repositoryDefinition.getSupportedCapabilities(),
			repositoryDefinition.getExportedCapabilities(),
			repositoryDefinition.getRepositoryEventTrigger());
	}

	protected long getDefaultClassNameId() {
		if (_defaultClassNameId == 0) {
			_defaultClassNameId = _classNameLocalService.getClassNameId(
				LiferayRepository.class.getName());
		}

		return _defaultClassNameId;
	}

	protected DLFileEntryLocalService getDlFileEntryLocalService() {
		return _dlFileEntryLocalService;
	}

	protected DLFileEntryService getDlFileEntryService() {
		return _dlFileEntryService;
	}

	protected DLFileVersionLocalService getDlFileVersionLocalService() {
		return _dlFileVersionLocalService;
	}

	protected DLFileVersionService getDlFileVersionService() {
		return _dlFileVersionService;
	}

	protected DLFolderLocalService getDlFolderLocalService() {
		return _dlFolderLocalService;
	}

	protected DLFolderService getDlFolderService() {
		return _dlFolderService;
	}

	protected Repository getRepository(long repositoryId) {
		RepositoryLocalService repositoryLocalService =
			getRepositoryLocalService();

		return repositoryLocalService.fetchRepository(repositoryId);
	}

	protected long getRepositoryClassNameId(long repositoryId) {
		Repository repository = _repositoryLocalService.fetchRepository(
			repositoryId);

		if (repository != null) {
			return repository.getClassNameId();
		}

		return _classNameLocalService.getClassNameId(
			LiferayRepository.class.getName());
	}

	protected RepositoryDefinition getRepositoryDefinition(long classNameId)
		throws PortalException {

		ClassName className = _classNameLocalService.getClassName(classNameId);

		return _repositoryDefinitionCatalog.getRepositoryDefinition(
			className.getClassName());
	}

	protected RepositoryLocalService getRepositoryLocalService() {
		return _repositoryLocalService;
	}

	@BeanReference(type = ClassNameLocalService.class)
	private ClassNameLocalService _classNameLocalService;

	private long _defaultClassNameId;

	@BeanReference(type = DLFileEntryLocalService.class)
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@BeanReference(type = DLFileEntryService.class)
	private DLFileEntryService _dlFileEntryService;

	@BeanReference(type = DLFileVersionLocalService.class)
	private DLFileVersionLocalService _dlFileVersionLocalService;

	@BeanReference(type = DLFileVersionService.class)
	private DLFileVersionService _dlFileVersionService;

	@BeanReference(type = DLFolderLocalService.class)
	private DLFolderLocalService _dlFolderLocalService;

	@BeanReference(type = DLFolderService.class)
	private DLFolderService _dlFolderService;

	@BeanReference(type = RepositoryDefinitionCatalog.class)
	private RepositoryDefinitionCatalog _repositoryDefinitionCatalog;

	@BeanReference(type = RepositoryLocalService.class)
	private RepositoryLocalService _repositoryLocalService;

}