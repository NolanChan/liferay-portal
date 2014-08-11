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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryHandler;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.repository.cmis.CMISRepository;
import com.liferay.portal.service.base.CMISRepositoryLocalServiceBaseImpl;

import org.apache.chemistry.opencmis.client.api.Document;

/**
 * @author Alexander Chow
 */
public class CMISRepositoryLocalServiceImpl
	extends CMISRepositoryLocalServiceBaseImpl {

	@Override
	public Object getSession(long repositoryId) throws PortalException {
		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		return cmisRepository.getSession();
	}

	@Override
	public FileEntry toFileEntry(long repositoryId, Object object)
		throws PortalException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		Document document = (Document)object;

		return cmisRepository.toFileEntry(document);
	}

	@Override
	public FileVersion toFileVersion(long repositoryId, Object object)
		throws PortalException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		Document document = (Document)object;

		return cmisRepository.toFileVersion(document);
	}

	@Override
	public Folder toFolder(long repositoryId, Object object)
		throws PortalException {

		CMISRepository cmisRepository = getCmisRepository(repositoryId);

		org.apache.chemistry.opencmis.client.api.Folder cmisFolder =
			(org.apache.chemistry.opencmis.client.api.Folder)object;

		return cmisRepository.toFolder(cmisFolder);
	}

	protected CMISRepository getCmisRepository(long repositoryId)
		throws PortalException {

		Repository repositoryImpl = repositoryLocalService.getRepositoryImpl(
			repositoryId);

		CMISRepositoryHandler cmisRepositoryHandler =
			repositoryImpl.getCapability(CMISRepositoryHandler.class);

		return (CMISRepository)cmisRepositoryHandler.getCmisRepository();
	}

}