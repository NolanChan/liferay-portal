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

package com.liferay.portal.reports.engine.console.web.admin.portlet;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.reports.engine.console.constants.ReportsEngineConsolePortletKeys;
import com.liferay.portal.reports.engine.console.model.Definition;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.DefinitionLocalService;
import com.liferay.portal.reports.engine.console.service.SourceLocalService;
import com.liferay.portal.reports.engine.console.web.admin.configuration.ReportsEngineAdminWebConfiguration;
import com.liferay.portal.reports.engine.console.web.admin.internal.constants.ReportsEngineWebKeys;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gavin Wan
 */
@Component(
	configurationPid = "com.liferay.portal.reports.engine.console.web.admin.configuration.ReportsEngineAdminWebConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=reports-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.footer-portlet-javascript=/admin/js/main.js",
		"com.liferay.portlet.header-portlet-css=/admin/css/main.css",
		"com.liferay.portlet.icon=/icons/admin.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Report Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.config-template=/admin/configuration.jsp",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.mvc-action-command-package-prefix=com.liferay.portal.reports.engine.console.web.admin.portlet.action",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + ReportsEngineConsolePortletKeys.REPORTS_ADMIN,
		"javax.portlet.portlet-info.keywords=Reports Admin",
		"javax.portlet.portlet-info.short-title=Reports Admin",
		"javax.portlet.portlet-info.title=Reports Admin",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		try {
			setDefinitionRequestAttribute(request);

			setSourceRequestAttribute(request);

			request.setAttribute(
				ReportsEngineAdminWebConfiguration.class.getName(),
				_reportsEngineAdminWebConfiguration);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				hideDefaultErrorMessage(request);

				SessionErrors.add(request, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(request, response);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("download")) {
				serveDownload(resourceRequest, resourceResponse);
			}
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Reference(unbind = "-")
	public void setDefinitionLocalService(
		DefinitionLocalService definitionLocalService) {

		_definitionLocalService = definitionLocalService;
	}

	@Reference(unbind = "-")
	public void setSourceLocalService(SourceLocalService sourceLocalService) {
		_sourceLocalService = sourceLocalService;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_reportsEngineAdminWebConfiguration =
			ConfigurableUtil.createConfigurable(
				ReportsEngineAdminWebConfiguration.class, properties);
	}

	protected void serveDownload(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String fileName = ParamUtil.getString(resourceRequest, "fileName");

		String shortFileName = StringUtil.extractLast(
			fileName, StringPool.SLASH);
		InputStream inputStream = DLStoreUtil.getFileAsStream(
			themeDisplay.getCompanyId(), CompanyConstants.SYSTEM, fileName);
		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, shortFileName, inputStream,
			contentType);
	}

	protected void setDefinitionRequestAttribute(RenderRequest renderRequest)
		throws PortalException {

		long definitionId = ParamUtil.getLong(renderRequest, "definitionId");

		Definition definition = null;

		if (definitionId > 0) {
			definition = _definitionLocalService.getDefinition(definitionId);
		}

		renderRequest.setAttribute(ReportsEngineWebKeys.DEFINITION, definition);
	}

	protected void setSourceRequestAttribute(RenderRequest renderRequest)
		throws PortalException {

		long sourceId = ParamUtil.getLong(renderRequest, "sourceId");

		Source source = null;

		if (sourceId > 0) {
			source = _sourceLocalService.getSource(sourceId);
		}

		renderRequest.setAttribute(ReportsEngineWebKeys.SOURCE, source);
	}

	private DefinitionLocalService _definitionLocalService;
	private volatile ReportsEngineAdminWebConfiguration
		_reportsEngineAdminWebConfiguration;
	private SourceLocalService _sourceLocalService;

}