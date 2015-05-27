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

package com.liferay.portlet.exportimport.trash;

import com.liferay.portal.kernel.trash.BaseTrashRenderer;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.exportimport.model.ExportImportConfiguration;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Levente Hudák
 */
public class ExportImportConfigurationTrashRenderer extends BaseTrashRenderer {

	public static final String TYPE = "export_import_configuration";

	public ExportImportConfigurationTrashRenderer(
		ExportImportConfiguration exportImportConfiguration) {

		_exportImportConfiguration = exportImportConfiguration;
	}

	@Override
	public String getClassName() {
		return ExportImportConfiguration.class.getName();
	}

	@Override
	public long getClassPK() {
		return _exportImportConfiguration.getPrimaryKey();
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/configuration.png";
	}

	@Override
	public String getPortletId() {
		return PortletKeys.EXPORT_IMPORT;
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _exportImportConfiguration.getDescription();
	}

	@Override
	public String getTitle(Locale locale) {
		return _exportImportConfiguration.getName();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String render(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String template)
		throws Exception {

		portletRequest.setAttribute(
			WebKeys.EXPORT_IMPORT_CONFIGURATION_ID,
			_exportImportConfiguration.getExportImportConfigurationId());

		return "/html/portlet/export_import/view_configuration.jsp";
	}

	private final ExportImportConfiguration _exportImportConfiguration;

}