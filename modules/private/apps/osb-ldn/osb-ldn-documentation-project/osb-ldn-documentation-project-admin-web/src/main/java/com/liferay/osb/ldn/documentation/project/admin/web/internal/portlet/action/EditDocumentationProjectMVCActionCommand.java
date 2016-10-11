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

package com.liferay.osb.ldn.documentation.project.admin.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.admin.web.internal.constants.DocumentationProjectPortletKeys;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(
	property = {
		"javax.portlet.name=" + DocumentationProjectPortletKeys.DOCUMENTATION_PROJECT_ADMIN,
		"mvc.command.name=/edit_documentation_project"
	},
	service = MVCActionCommand.class
)
public class EditDocumentationProjectMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long documentationProjectId = ParamUtil.getLong(
			uploadPortletRequest, "documentationProjectId");

		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");
		String iconFileName = uploadPortletRequest.getFileName("icon");
		File iconFile = uploadPortletRequest.getFile("icon");
		int status = ParamUtil.getInteger(uploadPortletRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (documentationProjectId > 0) {
			_documentationProjectService.updateDocumentationProject(
				documentationProjectId, name, description, iconFileName,
				iconFile, status, serviceContext);
		}
		else {
			_documentationProjectService.addDocumentationProject(
				name, description, iconFileName, iconFile, status,
				serviceContext);
		}
	}

	@Reference
	private DocumentationProjectService _documentationProjectService;

}