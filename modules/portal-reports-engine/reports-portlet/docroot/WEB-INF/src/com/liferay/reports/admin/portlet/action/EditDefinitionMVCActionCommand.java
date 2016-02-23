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

package com.liferay.reports.admin.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.reports.model.Definition;
import com.liferay.reports.service.DefinitionServiceUtil;
import com.liferay.reports.util.ReportsUtil;

import java.io.InputStream;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
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
				DefinitionServiceUtil.addDefinition(
					themeDisplay.getScopeGroupId(), definitionNameMap,
					definitionDescriptionMap, sourceId, reportParameters,
					fileName, inputStream, serviceContext);
			}
			else {
				DefinitionServiceUtil.updateDefinition(
					definitionId, definitionNameMap, definitionDescriptionMap,
					sourceId, reportParameters, fileName, inputStream,
					serviceContext);
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

}