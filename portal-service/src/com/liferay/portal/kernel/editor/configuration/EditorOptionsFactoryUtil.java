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

package com.liferay.portal.kernel.editor.configuration;

import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Map;

/**
 * @author Sergio González
 */
public class EditorOptionsFactoryUtil {

	public static EditorOptions getEditorOptions(
		String portletName, String editorConfigKey, String editorName,
		Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		LiferayPortletResponse liferayPortletResponse) {

		return getEditorOptionsFactory().getEditorOptions(
			portletName, editorConfigKey, editorName,
			inputEditorTaglibAttributes, themeDisplay, liferayPortletResponse);
	}

	public static EditorOptionsFactory getEditorOptionsFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			EditorOptionsFactoryUtil.class);

		return _editorOptionsFactory;
	}

	public void setEditorOptionsFactory(
		EditorOptionsFactory editorOptionsFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_editorOptionsFactory = editorOptionsFactory;
	}

	private static EditorOptionsFactory _editorOptionsFactory;

}