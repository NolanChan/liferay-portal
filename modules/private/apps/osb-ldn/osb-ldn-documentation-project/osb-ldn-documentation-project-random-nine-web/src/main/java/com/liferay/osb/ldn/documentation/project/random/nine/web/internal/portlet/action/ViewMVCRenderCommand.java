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

package com.liferay.osb.ldn.documentation.project.random.nine.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.random.nine.web.internal.constants.DocumentationProjectPortletKeys;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocumentationProjectPortletKeys.DOCUMENTATION_PROJECT_RANDOM_NINE,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		List<Map<String, Object>> documentationProjects =
			getRandomDocumentationProjectList();

		template.put("documentationProjects", documentationProjects);

		return "view";
	}

	protected List<Map<String, Object>> getRandomDocumentationProjectList() {
		List<Map<String, Object>> documentationProjectList = new ArrayList<>(9);

		List<DocumentationProject> documentationProjects =
			_documentationProjectLocalService.getDocumentationProjects(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		documentationProjects = ListUtil.copy(documentationProjects);

		Collections.shuffle(documentationProjects);

		for (int i = 0; (i < 9) && (i < documentationProjects.size()); i++) {
			DocumentationProject documentationProject =
				documentationProjects.get(i);

			Map<String, Object> documentationProjectMap = new HashMap<>(2);

			documentationProjectMap.put("iconURL", null);
			documentationProjectMap.put("name", documentationProject.getName());
			documentationProjectMap.put("siteURL", null);

			documentationProjectList.add(documentationProjectMap);
		}

		return documentationProjectList;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

}