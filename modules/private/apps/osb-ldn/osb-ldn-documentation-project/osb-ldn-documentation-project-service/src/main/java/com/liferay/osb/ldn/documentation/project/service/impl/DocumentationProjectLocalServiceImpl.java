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

package com.liferay.osb.ldn.documentation.project.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.documentation.project.exception.DocumentationProjectDescriptionException;
import com.liferay.osb.ldn.documentation.project.exception.DocumentationProjectNameException;
import com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;

/**
 * @author Ryan Park
 * @author Yury Butrymovich
 */
@ProviderType
public class DocumentationProjectLocalServiceImpl
	extends DocumentationProjectLocalServiceBaseImpl {

	@Override
	public DocumentationProject addDocumentationProject(
			long userId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(name, description);

		long documentationProjectId = counterLocalService.increment();

		DocumentationProject documentationProject =
			documentationProjectPersistence.create(documentationProjectId);

		Date now = new Date();

		documentationProject.setUuid(serviceContext.getUuid());
		documentationProject.setCompanyId(user.getCompanyId());
		documentationProject.setUserId(userId);
		documentationProject.setUserName(user.getFullName());
		documentationProject.setCreateDate(serviceContext.getCreateDate(now));
		documentationProject.setModifiedDate(
			serviceContext.getModifiedDate(now));
		documentationProject.setName(name);
		documentationProject.setDescription(description);

		documentationProjectPersistence.update(documentationProject);

		return documentationProject;
	}

	@Override
	public DocumentationProject deleteDocumentationProject(
			long documentationProjectId)
		throws PortalException {

		DocumentationProject documentationProject =
			documentationProjectPersistence.findByPrimaryKey(
				documentationProjectId);

		return deleteDocumentationProject(documentationProject);
	}

	@Override
	public DocumentationProject getDocumentationProject(String name)
		throws NoSuchDocumentationProjectException {

		return documentationProjectPersistence.findByName(name);
	}

	@Override
	public DocumentationProject updateDocumentationProject(
			long userId, long documentationProjectId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		DocumentationProject documentationProject =
			documentationProjectPersistence.findByPrimaryKey(
				documentationProjectId);

		validate(name, description);

		Date now = new Date();

		documentationProject.setUuid(serviceContext.getUuid());
		documentationProject.setCompanyId(user.getCompanyId());
		documentationProject.setUserId(userId);
		documentationProject.setUserName(user.getFullName());
		documentationProject.setModifiedDate(
			serviceContext.getModifiedDate(now));
		documentationProject.setName(name);
		documentationProject.setDescription(description);

		return updateDocumentationProject(documentationProject);
	}

	protected void validate(String name, String description)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new DocumentationProjectNameException("Name is null");
		}

		if (Validator.isNull(description)) {
			throw new DocumentationProjectDescriptionException(
				"Description is null");
		}
	}

}