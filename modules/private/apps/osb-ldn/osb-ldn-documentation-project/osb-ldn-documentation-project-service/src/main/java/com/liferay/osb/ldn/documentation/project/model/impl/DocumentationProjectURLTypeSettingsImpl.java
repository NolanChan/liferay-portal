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

package com.liferay.osb.ldn.documentation.project.model.impl;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectURLTypeSettings;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Ryan Park
 */
public class DocumentationProjectURLTypeSettingsImpl
	implements DocumentationProjectURLTypeSettings {

	public DocumentationProjectURLTypeSettingsImpl() {
		_properties = new UnicodeProperties(true);
	}

	public DocumentationProjectURLTypeSettingsImpl(
		DocumentationProject documentationProject) {

		_properties = new UnicodeProperties(true);

		_properties.fastLoad(documentationProject.getTypeSettings());
	}

	@Override
	public String getURL() {
		return GetterUtil.getString(_properties.getProperty("url"));
	}

	@Override
	public void setURL(String url) {
		_properties.setProperty("url", url);
	}

	@Override
	public String toString() {
		return _properties.toString();
	}

	private final UnicodeProperties _properties;

}