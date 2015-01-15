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

package com.liferay.portal.template.freemarker.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Peter Fellwock
 */
@Meta.OCD(id = "com.liferay.portal.template.freemarker")
public interface FreemarkerEngineConfiguration {

	public static final String FREEMARKER_ENGINE_TEMPLATE_PARSERS =
		"freemarker.engine.template.parsers";

	@Meta.AD(deflt = "false", required = false)
	public boolean getLocalizedLookup();

	@Meta.AD(
		deflt = "FTL_liferay.ftl as liferay", required = false)
	public String getMacroLibrary();

	@Meta.AD(deflt = "60", required = false)
	public int getResourceModificationCheck();

	@Meta.AD(
		deflt = "java.lang.Class|java.lang.ClassLoader|java.lang.Thread",
		required = false)
	public String[] getRestrictedClasses();

	@Meta.AD(
		deflt = "", required = false)
	public String[] getRestrictedPackages();

	@Meta.AD(
		deflt = "serviceLocator", required = false)
	public String[] getRestrictedVariables();

	@Meta.AD(deflt = "rethrow", required = false)
	public String getTemplateExceptionHandler();

	@Meta.AD(
		deflt = "com.liferay.portal.freemarker.FreeMarkerServletResourceParser|com.liferay.portal.template.ThemeResourceParser|com.liferay.portal.template.DDMTemplateResourceParser|com.liferay.portal.template.ClassLoaderResourceParser",
		required = false)
	public String[] getTemplateParsers();

}