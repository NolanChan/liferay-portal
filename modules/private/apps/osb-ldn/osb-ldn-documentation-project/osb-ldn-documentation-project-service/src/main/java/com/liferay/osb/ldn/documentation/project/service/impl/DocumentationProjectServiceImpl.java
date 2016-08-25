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

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.base.DocumentationProjectServiceBaseImpl;
import com.liferay.osb.ldn.documentation.project.service.permission.ActionKeys;
import com.liferay.osb.ldn.documentation.project.service.permission.DocumentationProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * The implementation of the documentation project remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.osb.ldn.documentation.project.service.DocumentationProjectService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Ryan Park
 * @author Howie Chou
 * @see DocumentationProjectServiceBaseImpl
 * @see com.liferay.osb.ldn.documentation.project.service.DocumentationProjectServiceUtil
 */
@ProviderType
public class DocumentationProjectServiceImpl
	extends DocumentationProjectServiceBaseImpl {

	public DocumentationProject addDocumentationProject(
			long userId, String name, String description)
		throws PortalException {

		DocumentationProjectPermission.check(
			getPermissionChecker(), 0, ActionKeys.ADD_DOCUMENTATION_PROJECT);

		return documentationProjectLocalService.addDocumentationProject(
			userId, name, description);
	}

	public DocumentationProject updateDocumentationProject(
			long documentationProjectId, String name, String description)
		throws PortalException {

		DocumentationProjectPermission.check(
			getPermissionChecker(), 0, ActionKeys.UPDATE);

		return documentationProjectLocalService.updateDocumentationProject(
			documentationProjectId, name, description);
	}

}