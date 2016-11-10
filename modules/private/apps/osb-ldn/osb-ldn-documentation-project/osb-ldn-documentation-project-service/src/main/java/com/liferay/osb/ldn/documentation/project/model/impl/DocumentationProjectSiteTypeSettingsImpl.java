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
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectSiteTypeSettings;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Ryan Park
 */
public class DocumentationProjectSiteTypeSettingsImpl
	implements DocumentationProjectSiteTypeSettings {

	public DocumentationProjectSiteTypeSettingsImpl() {
		_properties = new UnicodeProperties(true);
	}

	public DocumentationProjectSiteTypeSettingsImpl(
		DocumentationProject documentationProject) {

		_properties = new UnicodeProperties(true);

		_properties.fastLoad(documentationProject.getTypeSettings());
	}

	@Override
	public String getGitHubRepositoryName() {
		return GetterUtil.getString(
			_properties.getProperty("gitHubRepositoryName"));
	}

	@Override
	public String getGitHubRepositoryOwner() {
		return GetterUtil.getString(
			_properties.getProperty("gitHubRepositoryOwner"));
	}

	@Override
	public String getHeaderGradientEndColor() {
		return GetterUtil.getString(
			_properties.getProperty("headerGradientEndColor"));
	}

	@Override
	public String getHeaderGradientStartColor() {
		return GetterUtil.getString(
			_properties.getProperty("headerGradientStartColor"));
	}

	@Override
	public void setGitHubRepositoryName(String gitHubRepositoryName) {
		_properties.setProperty("gitHubRepositoryName", gitHubRepositoryName);
	}

	@Override
	public void setGitHubRepositoryOwner(String gitHubRepositoryOwner) {
		_properties.setProperty("gitHubRepositoryOwner", gitHubRepositoryOwner);
	}

	@Override
	public void setHeaderGradientEndColor(String headerGradientEndColor) {
		_properties.setProperty(
			"headerGradientEndColor", headerGradientEndColor);
	}

	@Override
	public void setHeaderGradientStartColor(String headerGradientStartColor) {
		_properties.setProperty(
			"headerGradientStartColor", headerGradientStartColor);
	}

	@Override
	public String toString() {
		return _properties.toString();
	}

	private final UnicodeProperties _properties;

}