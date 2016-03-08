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

package com.liferay.portal.reports.web.admin.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.reports.constants.ReportsPortletKeys;
import com.liferay.portal.reports.model.Definition;
import com.liferay.portal.reports.service.DefinitionService;
import com.liferay.portal.reports.util.ReportsUtil;

import java.io.InputStream;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ReportsPortletKeys.REPORTS_ADMIN,
		"mvc.command.name=editDefinition"
	},
	service = MVCActionCommand.class
)
public class EditDefinitionMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		InputStream inputStream = null;

		try {
			long definitionId = ParamUtil.getLong(
				uploadPortletRequest, "definitionId");

			Map<Locale, String> definitionNameMap =
				ReportsUtil.getLocalizationMap(uploadPortletRequest, "name");
			Map<Locale, String> definitionDescriptionMap =
				ReportsUtil.getLocalizationMap(
					uploadPortletRequest, "description");
			long sourceId = ParamUtil.getLong(uploadPortletRequest, "sourceId");
			String reportParameters = ParamUtil.getString(
				uploadPortletRequest, "reportParameters");
			String fileName = uploadPortletRequest.getFileName(
				"templateReport");
			inputStream = uploadPortletRequest.getFileAsStream(
				"templateReport");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Definition.class.getName(), actionRequest);

			if (definitionId <= 0) {
				_definitionService.addDefinition(
					themeDisplay.getScopeGroupId(), definitionNameMap,
					definitionDescriptionMap, sourceId, reportParameters,
					fileName, inputStream, serviceContext);
			}
			else {
				_definitionService.updateDefinition(
					definitionId, definitionNameMap, definitionDescriptionMap,
					sourceId, reportParameters, fileName, inputStream,
					serviceContext);
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	@Reference
	private DefinitionService _definitionService;

}