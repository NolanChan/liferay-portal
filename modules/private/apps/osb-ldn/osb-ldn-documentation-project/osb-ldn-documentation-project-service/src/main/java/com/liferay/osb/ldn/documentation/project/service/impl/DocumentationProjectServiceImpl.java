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

import com.liferay.osb.ldn.documentation.project.constants.DocumentationProjectActionKeys;
import com.liferay.osb.ldn.documentation.project.internal.service.permission.DocumentationProjectPermission;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

/**
 * @author Howie Chou
 */
@ProviderType
public class DocumentationProjectServiceImpl
	extends DocumentationProjectServiceBaseImpl {

	@Override
	public DocumentationProject addDocumentationProject(
			String name, String description, String iconFileName, File iconFile,
			int status, ServiceContext serviceContext)
		throws PortalException {

		DocumentationProjectPermission.check(
			getPermissionChecker(),
			DocumentationProjectActionKeys.ADD_DOCUMENTATION_PROJECT);

		return documentationProjectLocalService.addDocumentationProject(
			getUserId(), name, description, iconFileName, iconFile, status,
			serviceContext);
	}

	@Override
	public DocumentationProject updateDocumentationProject(
			long documentationProjectId, String name, String description,
			String iconFileName, File iconFile, int status,
			ServiceContext serviceContext)
		throws PortalException {

		DocumentationProjectPermission.check(
			getPermissionChecker(),
			DocumentationProjectActionKeys.UPDATE_DOCUMENTATION_PROJECT);

		return documentationProjectLocalService.updateDocumentationProject(
			documentationProjectId, name, description, iconFileName, iconFile,
			status, serviceContext);
	}

}