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

package com.liferay.portlet.display.template.web.ddm;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.util.BaseDDMDisplay;
import com.liferay.portlet.dynamicdatamapping.util.DDMDisplay;
import com.liferay.portlet.dynamicdatamapping.util.DDMPermissionHandler;

import java.util.Locale;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Garcia
 */
@Component(
	property = {"javax.portlet.name=" + PortletKeys.PORTLET_DISPLAY_TEMPLATE},
	service = DDMDisplay.class
)
public class PortletDisplayTemplateDDMDisplay extends BaseDDMDisplay {

	@Override
	public DDMPermissionHandler getDDMPermissionHandler() {
		return _ddmPermissionHandler;
	}

	@Override
	public String getEditTemplateBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classNameId,
			long classPK, String portletResource)
		throws Exception {

		String redirect = ParamUtil.getString(
			liferayPortletRequest, "redirect");

		if (Validator.isNull(redirect)) {
			return getViewTemplatesURL(
				liferayPortletRequest, liferayPortletResponse, classNameId,
				classPK);
		}

		return redirect;
	}

	@Override
	public String getPortletId() {
		return PortletKeys.PORTLET_DISPLAY_TEMPLATE;
	}

	@Override
	public long[] getTemplateClassPKs(
			long companyId, long classNameId, long classPK)
		throws Exception {

		return null;
	}

	@Override
	public long[] getTemplateGroupIds(
			ThemeDisplay themeDisplay, boolean includeAncestorTemplates)
		throws Exception {

		if (includeAncestorTemplates) {
			return PortalUtil.getCurrentAndAncestorSiteGroupIds(
				themeDisplay.getScopeGroupId());
		}

		return new long[] {
			_portletDisplayTemplate.getDDMTemplateGroupId(
				themeDisplay.getScopeGroupId())
			};
	}

	@Override
	public String getTemplateType() {
		return DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
	}

	@Override
	public String getTemplateType(DDMTemplate template, Locale locale) {
		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(
				template.getClassNameId());

		return templateHandler.getName(locale);
	}

	@Override
	public String getViewTemplatesBackURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classPK)
		throws Exception {

		return StringPool.BLANK;
	}

	@Override
	public Set<String> getViewTemplatesExcludedColumnNames() {
		return _viewTemplateExcludedColumnNames;
	}

	@Override
	public String getViewTemplatesTitle(
		DDMStructure structure, boolean controlPanel, boolean search,
		Locale locale) {

		if (search) {
			return LanguageUtil.get(locale, "templates");
		}

		if (controlPanel) {
			return StringPool.BLANK;
		}

		return super.getViewTemplatesTitle(
			structure, controlPanel, search, locale);
	}

	@Override
	protected String getDefaultEditTemplateTitle(Locale locale) {
		return LanguageUtil.get(locale, "new-application-display-template");
	}

	@Override
	protected String getDefaultViewTemplateTitle(Locale locale) {
		return LanguageUtil.get(locale, "application-display-templates");
	}

	@Reference
	protected void setPortletDisplayTemplate(
		PortletDisplayTemplate portletDisplayTemplate) {

		_portletDisplayTemplate = portletDisplayTemplate;
	}

	protected PortletDisplayTemplate _portletDisplayTemplate;

	private static final Set<String> _viewTemplateExcludedColumnNames =
		SetUtil.fromArray(new String[] {"language", "mode", "structure"});

	private final DDMPermissionHandler _ddmPermissionHandler =
		new PortletDisplayTemplateDDMPermissionHandler();

}