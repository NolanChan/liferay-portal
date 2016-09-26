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
import com.liferay.osb.ldn.documentation.project.internal.file.util.DocumentationProjectFileUtil;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

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
			long userId, String name, String description, String iconFileName,
			File iconFile, int status)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name, description, iconFileName, iconFile);

		// Documentation project

		long documentationProjectId = counterLocalService.increment();

		DocumentationProject documentationProject =
			documentationProjectPersistence.create(documentationProjectId);

		Group group = _groupLocalService.addGroup(
			userId, GroupConstants.DEFAULT_PARENT_GROUP_ID,
			DocumentationProject.class.getName(), documentationProjectId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, null, null,
			GroupConstants.TYPE_SITE_OPEN, true,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
			StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(name), false,
			false, true, null);

		documentationProject.setGroupId(group.getGroupId());

		documentationProject.setCompanyId(user.getCompanyId());
		documentationProject.setUserId(userId);
		documentationProject.setUserName(user.getFullName());
		documentationProject.setCreateDate(now);
		documentationProject.setModifiedDate(now);
		documentationProject.setName(name);
		documentationProject.setDescription(description);
		documentationProject.setIconFileName(getIconFileName(iconFileName));
		documentationProject.setStatus(status);

		documentationProjectPersistence.update(documentationProject);

		// Files

		DocumentationProjectFileUtil.initDocumentationProjectDirectory(
			documentationProjectId);

		DocumentationProjectFileUtil.addDocumentationProjectFile(
			documentationProjectId, documentationProject.getIconFileName(),
			iconFile);

		return documentationProject;
	}

	@Override
	public DocumentationProject deleteDocumentationProject(
			DocumentationProject documentationProject)
		throws PortalException {

		// Documentation project

		_groupLocalService.deleteGroup(documentationProject.getGroupId());

		documentationProjectPersistence.remove(documentationProject);

		// Files

		DocumentationProjectFileUtil.destroyDocumentationProjectDirectory(
			documentationProject.getDocumentationProjectId());

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
	public DocumentationProject updateDocumentationProject(
			long documentationProjectId, String name, String description,
			String iconFileName, File iconFile, int status)
		throws PortalException {

		DocumentationProject documentationProject =
			documentationProjectPersistence.findByPrimaryKey(
				documentationProjectId);

		String oldIconFileName = documentationProject.getIconFileName();

		boolean skipIconUpdate = false;

		if ((iconFile == null) && Validator.isNull(iconFileName)) {
			iconFile = DocumentationProjectFileUtil.getDocumentProjectFile(
				documentationProjectId, documentationProject.getIconFileName());
			iconFileName = documentationProject.getIconFileName();

			skipIconUpdate = true;
		}

		validate(name, description, iconFileName, iconFile);

		documentationProject.setModifiedDate(new Date());
		documentationProject.setName(name);
		documentationProject.setDescription(description);
		documentationProject.setStatus(status);

		if (!skipIconUpdate) {
			documentationProject.setIconFileName(getIconFileName(iconFileName));
		}

		documentationProjectPersistence.update(documentationProject);

		// Files

		if (!skipIconUpdate) {
			DocumentationProjectFileUtil.deleteDocumentationProjectFile(
				documentationProjectId, oldIconFileName);

			DocumentationProjectFileUtil.addDocumentationProjectFile(
				documentationProjectId, documentationProject.getIconFileName(),
				iconFile);
		}

		return documentationProject;
	}

	protected String getIconFileName(String fileName) {
		String extension = FileUtil.getExtension(fileName);

		return "icon." + extension;
	}

	protected void validate(
			String name, String description, String iconFileName, File iconFile)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new DocumentationProjectNameException("Name is null");
		}

		if (Validator.isNull(description)) {
			throw new DocumentationProjectDescriptionException(
				"Description is null");
		}

		if ((iconFile == null) || !iconFile.exists()) {
			throw new DocumentationProjectIconException(
				"Icon file doesn't exist");
		}

		String extension = FileUtil.getExtension(iconFileName);

		if (!ArrayUtil.contains(_ICON_EXTENSIONS, extension)) {
			throw new DocumentationProjectIconExtensionException(
				"Invalid icon file extension. Valid extensions: " +
					Arrays.toString(_ICON_EXTENSIONS));
		}
	}

	private static final String[] _ICON_EXTENSIONS = {"svg", "png"};

	@ServiceReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

}