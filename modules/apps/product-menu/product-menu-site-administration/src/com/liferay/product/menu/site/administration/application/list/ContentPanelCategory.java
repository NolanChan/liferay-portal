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

package com.liferay.product.menu.site.administration.application.list;

import com.liferay.application.list.BaseJSPPanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION,
		"service.ranking:Integer=200"
	},
	service = PanelCategory.class
)
public class ContentPanelCategory extends BaseJSPPanelCategory {

	@Override
	public String getIconCssClass() {
		return "icon-file-text";
	}

	@Override
	public String getJspPath() {
		return "/META-INF/resources/content/content.jsp";
	}

	@Override
	public String getKey() {
		return PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "category.site_administration.content");
	}

	@Override
	public String getParentCategoryKey() {
		return PanelCategoryKeys.SITE_ADMINISTRATION;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.product.menu.site.administration.service)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}