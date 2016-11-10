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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.ldn.documentation.project.internal.file.util.DocumentationProjectFileUtil;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectTypeSettings;
import com.liferay.osb.ldn.documentation.project.util.DocumentationProjectTypeSettingsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

/**
 * @author Ryan Park
 */
@ProviderType
public class DocumentationProjectImpl extends DocumentationProjectBaseImpl {

	public DocumentationProjectImpl() {
	}

	@Override
	public DocumentationProjectTypeSettings
		getDocumentationProjectTypeSettings() {

		if (_documentationProjectTypeSettings == null) {
			_documentationProjectTypeSettings =
				DocumentationProjectTypeSettingsFactoryUtil.create(this);
		}

		return _documentationProjectTypeSettings;
	}

	@Override
	public InputStream getIconInputStream() throws PortalException {
		return DocumentationProjectFileUtil.getDocumentProjectFileAsStream(
			getDocumentationProjectId(), getIconFileName());
	}

	@Override
	public String getTypeSettings() {
		if (_documentationProjectTypeSettings == null) {
			return super.getTypeSettings();
		}
		else {
			return _documentationProjectTypeSettings.toString();
		}
	}

	private DocumentationProjectTypeSettings _documentationProjectTypeSettings;

}