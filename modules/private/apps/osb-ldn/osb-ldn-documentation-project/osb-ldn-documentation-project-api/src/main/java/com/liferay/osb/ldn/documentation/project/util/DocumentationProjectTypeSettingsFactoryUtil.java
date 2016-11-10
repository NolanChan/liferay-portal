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

package com.liferay.osb.ldn.documentation.project.util;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectTypeSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component
public class DocumentationProjectTypeSettingsFactoryUtil {

	public static DocumentationProjectTypeSettings create(
		DocumentationProject documentationProject) {

		return _documentationProjectTypeSettingsFactory.create(
			documentationProject);
	}

	public static DocumentationProjectTypeSettings create(String type) {
		return _documentationProjectTypeSettingsFactory.create(type);
	}

	@Reference(unbind = "-")
	protected void setDocumentationProjectTypeSettingsFactory(
		DocumentationProjectTypeSettingsFactory
			documentationProjectTypeSettingsFactory) {

		_documentationProjectTypeSettingsFactory =
			documentationProjectTypeSettingsFactory;
	}

	private static DocumentationProjectTypeSettingsFactory
		_documentationProjectTypeSettingsFactory;

}