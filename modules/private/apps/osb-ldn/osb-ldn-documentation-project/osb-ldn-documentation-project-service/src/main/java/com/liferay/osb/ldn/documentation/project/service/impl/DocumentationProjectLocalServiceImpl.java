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
import com.liferay.osb.ldn.documentation.project.exception.DocumentationProjectIconException;
import com.liferay.osb.ldn.documentation.project.exception.DocumentationProjectIconExtensionException;
import com.liferay.osb.ldn.documentation.project.exception.DocumentationProjectNameException;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.Arrays;
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
			long userId, String name, String description, File icon)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name, description, icon);

		long documentationProjectId = counterLocalService.increment();

		DocumentationProject documentationProject =
			documentationProjectPersistence.create(documentationProjectId);

		documentationProject.setCompanyId(user.getCompanyId());
		documentationProject.setUserId(userId);
		documentationProject.setUserName(user.getFullName());
		documentationProject.setCreateDate(now);
		documentationProject.setModifiedDate(now);
		documentationProject.setName(name);
		documentationProject.setDescription(description);
		documentationProject.setIconFileName(getIconFileName(icon));

		documentationProjectPersistence.update(documentationProject);

		// File

		// TODO: Write file.

		return documentationProject;
	}

	@Override
	public DocumentationProject updateDocumentationProject(
			long documentationProjectId, String name, String description,
			File icon)
		throws PortalException {

		DocumentationProject documentationProject =
			documentationProjectPersistence.findByPrimaryKey(
				documentationProjectId);

		validate(name, description, icon);

		documentationProject.setModifiedDate(new Date());
		documentationProject.setName(name);
		documentationProject.setDescription(description);

		if ((icon != null) && icon.exists()) {
			documentationProject.setIconFileName(getIconFileName(icon));
		}

		documentationProjectPersistence.update(documentationProject);

		// File

		// TODO: Write file.

		return documentationProject;
	}

	protected String getIconFileName(File file) {
		return null;
	}

	protected void validate(String name, String description, File icon)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new DocumentationProjectNameException("Name is null");
		}

		if (Validator.isNull(description)) {
			throw new DocumentationProjectDescriptionException(
				"Description is null");
		}

		if ((icon == null) || !icon.exists()) {
			throw new DocumentationProjectIconException("Icon doesn't exist");
		}

		String[] validIconExtensions = {"svg", "png"};

		boolean validExtension = false;

		for (String extension : validIconExtensions) {
			if (icon.getName().endsWith(extension)) {
				validExtension = true;
				break;
			}
		}

		if (!validExtension) {
			throw new DocumentationProjectIconExtensionException(
				"Invalid icon file extension. Valid extensions: " +
					Arrays.toString(validIconExtensions));
		}
	}

	private static final String _ICON_DIR =
		"osb-ldn-documentation-project/icons";

}