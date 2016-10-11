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

package com.liferay.osb.ldn.documentation.project.index.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.index.web.internal.constants.DocumentationProjectPortletKeys;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocumentationProjectPortletKeys.DOCUMENTATION_PROJECT_INDEX,
		"mvc.command.name=/serve_documentation_project_icon"
	},
	service = MVCResourceCommand.class
)
public class ServeDocumentationProjectIconMVCResourceCommand
	implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		long documentationProjectId = ParamUtil.getLong(
			resourceRequest, "documentationProjectId");

		try {
			DocumentationProject documentationProject =
				_documentationProjectLocalService.getDocumentationProject(
					documentationProjectId);

			String contentType = MimeTypesUtil.getContentType(
				documentationProject.getIconFileName());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse,
				documentationProject.getIconFileName(),
				documentationProject.getIconInputStream(), contentType);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to serve icon for documentation project " +
						documentationProjectId);
			}

			return true;
		}

		return false;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	private final Log _log = LogFactoryUtil.getLog(
		ServeDocumentationProjectIconMVCResourceCommand.class);

}