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

import com.liferay.osb.ldn.documentation.project.constants.DocumentationProjectConstants;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectTypeSettings;
import com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectSiteTypeSettingsImpl;
import com.liferay.osb.ldn.documentation.project.model.impl.DocumentationProjectURLTypeSettingsImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(service = DocumentationProjectTypeSettingsFactory.class)
public class DocumentationProjectTypeSettingsFactoryImpl
	implements DocumentationProjectTypeSettingsFactory {

	public DocumentationProjectTypeSettings create(
		DocumentationProject documentationProject) {

		String type = documentationProject.getType();

		if (type.equals(DocumentationProjectConstants.TYPE_SITE)) {
			return new DocumentationProjectSiteTypeSettingsImpl(
				documentationProject);
		}
		else if (type.equals(DocumentationProjectConstants.TYPE_URL)) {
			return new DocumentationProjectURLTypeSettingsImpl(
				documentationProject);
		}

		return null;
	}

	public DocumentationProjectTypeSettings create(String type) {
		if (type.equals(DocumentationProjectConstants.TYPE_SITE)) {
			return new DocumentationProjectSiteTypeSettingsImpl();
		}
		else if (type.equals(DocumentationProjectConstants.TYPE_URL)) {
			return new DocumentationProjectURLTypeSettingsImpl();
		}

		return null;
	}

}