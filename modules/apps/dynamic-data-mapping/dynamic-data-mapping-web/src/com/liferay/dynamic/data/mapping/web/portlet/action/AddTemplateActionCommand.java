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

package com.liferay.dynamic.data.mapping.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.dynamicdatamapping.TemplateScriptException;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService;

import java.io.File;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = {
		"action.command.name=addTemplate",
		"javax.portlet.name=" + PortletKeys.DYNAMIC_DATA_MAPPING,
		"javax.portlet.name=" + PortletKeys.PORTLET_DISPLAY_TEMPLATES
	},
	service = ActionCommand.class
)
public class AddTemplateActionCommand extends DDMBaseActionCommand {

	protected DDMTemplate addTemplate(PortletRequest portletRequest)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(portletRequest);

		long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId");
		long classNameId = ParamUtil.getLong(
			uploadPortletRequest, "classNameId");
		long classPK = ParamUtil.getLong(uploadPortletRequest, "classPK");
		long resourceClassNameId = ParamUtil.getLong(
			uploadPortletRequest, "resourceClassNameId");
		String templateKey = ParamUtil.getString(
			uploadPortletRequest, "templateKey");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			uploadPortletRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				uploadPortletRequest, "description");
		String type = ParamUtil.getString(uploadPortletRequest, "type");
		String mode = ParamUtil.getString(uploadPortletRequest, "mode");
		String language = ParamUtil.getString(
			uploadPortletRequest, "language", TemplateConstants.LANG_TYPE_VM);

		String script = getScript(uploadPortletRequest);

		boolean cacheable = ParamUtil.getBoolean(
			uploadPortletRequest, "cacheable");
		boolean smallImage = ParamUtil.getBoolean(
			uploadPortletRequest, "smallImage");
		String smallImageURL = ParamUtil.getString(
			uploadPortletRequest, "smallImageURL");
		File smallImageFile = uploadPortletRequest.getFile("smallImageFile");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), uploadPortletRequest);

		return _ddmTemplateService.addTemplate(
			groupId, classNameId, classPK, resourceClassNameId, templateKey,
			nameMap, descriptionMap, type, mode, language, script, cacheable,
			smallImage, smallImageURL, smallImageFile, serviceContext);
	}

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		DDMTemplate template = addTemplate(portletRequest);

		updatePortletPreferences(portletRequest, template);

		setRedirectAttribute(portletRequest, template);
	}

	protected String getFileScriptContent(
			UploadPortletRequest uploadPortletRequest)
		throws Exception {

		File file = uploadPortletRequest.getFile("script");

		if (file == null) {
			return null;
		}

		String fileScriptContent = FileUtil.read(file);

		if (Validator.isNotNull(fileScriptContent) && !isValidFile(file)) {
			throw new TemplateScriptException();
		}

		return fileScriptContent;
	}

	protected String getScript(UploadPortletRequest uploadPortletRequest)
		throws Exception {

		String fileScriptContent = getFileScriptContent(uploadPortletRequest);

		if (Validator.isNotNull(fileScriptContent)) {
			return fileScriptContent;
		}

		return ParamUtil.getString(uploadPortletRequest, "scriptContent");
	}

	protected boolean isValidFile(File file) {
		String contentType = MimeTypesUtil.getContentType(file);

		if (contentType.equals(ContentTypes.APPLICATION_XSLT_XML) ||
			contentType.startsWith(ContentTypes.TEXT)) {

			return true;
		}

		return false;
	}

	@Reference
	protected void setDDMTemplateService(
		DDMTemplateService ddmTemplateService) {

		_ddmTemplateService = ddmTemplateService;
	}

	private DDMTemplateService _ddmTemplateService;

}