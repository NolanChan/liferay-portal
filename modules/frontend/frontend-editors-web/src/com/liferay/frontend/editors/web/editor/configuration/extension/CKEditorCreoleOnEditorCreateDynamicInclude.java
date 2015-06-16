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

package com.liferay.frontend.editors.web.editor.configuration.extension;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.IOException;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Chema Balsas
 */
@Component(immediate = true, service = DynamicInclude.class)
public class CKEditorCreoleOnEditorCreateDynamicInclude
	implements DynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		Bundle bundle = _bundleContext.getBundle();

		URL entryURL = bundle.getEntry(_JS_DIALOG_DEFINITION_INCLUDE_PATH);

		StreamUtil.transfer(entryURL.openStream(), response.getOutputStream());

		String toolbarSet = (String)request.getAttribute(
			"liferay-ui:input-editor:toolbarSet");

		if (toolbarSet.equals("creole")) {
			entryURL = bundle.getEntry(_JS_DIALOG_SHOW_INCLUDE_PATH);

			StreamUtil.transfer(
				entryURL.openStream(), response.getOutputStream());
		}
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register("js#ckeditor_creole#onEditorCreate");
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private static final String _JS_DIALOG_DEFINITION_INCLUDE_PATH =
		"/META-INF/resources/html/editors/ckeditor/extension/" +
		"creole_dialog_definition.js";

	private static final String _JS_DIALOG_SHOW_INCLUDE_PATH =
		"/META-INF/resources/html/editors/ckeditor/extension/" +
		"creole_dialog_show.js";

	private static final Log _log = LogFactoryUtil.getLog(
		CKEditorCreoleOnEditorCreateDynamicInclude.class);

	private BundleContext _bundleContext;

}